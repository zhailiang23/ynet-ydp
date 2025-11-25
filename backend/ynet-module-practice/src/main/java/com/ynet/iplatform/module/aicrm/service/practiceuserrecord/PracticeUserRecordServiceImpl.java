package com.ynet.iplatform.module.aicrm.service.practiceuserrecord;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.practiceuserrecord.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.practiceuserrecord.PracticeUserRecordDO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.practicecourse.PracticeCourseDO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.practicescript.PracticeScriptDO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.practicematerial.PracticeMaterialDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;
import com.ynet.iplatform.framework.security.core.util.SecurityFrameworkUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.practiceuserrecord.PracticeUserRecordMapper;
import com.ynet.iplatform.module.aicrm.dal.mysql.practicecourse.PracticeCourseMapper;
import com.ynet.iplatform.module.aicrm.dal.mysql.practicescript.PracticeScriptMapper;
import com.ynet.iplatform.module.aicrm.dal.mysql.practicematerial.PracticeMaterialMapper;
import com.ynet.iplatform.module.aicrm.config.PracticeProperties;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * CRM智能陪练-用户陪练记录 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class PracticeUserRecordServiceImpl implements PracticeUserRecordService {

    @Resource
    private PracticeUserRecordMapper practiceUserRecordMapper;

    @Resource
    private PracticeCourseMapper practiceCourseMapper;

    @Resource
    private PracticeScriptMapper practiceScriptMapper;

    @Resource
    private PracticeMaterialMapper practiceMaterialMapper;

    @Resource
    private PracticeProperties practiceProperties;

    @Override
    public Long createPracticeUserRecord(PracticeUserRecordSaveReqVO createReqVO) {
        // 转换实体
        PracticeUserRecordDO practiceUserRecord = BeanUtils.toBean(createReqVO, PracticeUserRecordDO.class);

        // 自动设置其他字段
        practiceUserRecord.setUserId(SecurityFrameworkUtils.getLoginUserId()); // 参与用户默认为当前登录用户
        practiceUserRecord.setStartTime(LocalDateTime.now()); // 开始时间为当前时间
        practiceUserRecord.setRecordNo(IdUtil.fastSimpleUUID()); // 随机生成记录编号
        practiceUserRecord.setStatus("in_progress"); // 创建时状态为进行中

        // 插入
        practiceUserRecordMapper.insert(practiceUserRecord);

        // 返回
        return practiceUserRecord.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePracticeUserRecord(PracticeUserRecordSaveReqVO updateReqVO) {
        // 校验存在
        validatePracticeUserRecordExists(updateReqVO.getId());

        // 获取原记录状态
        PracticeUserRecordDO oldRecord = practiceUserRecordMapper.selectById(updateReqVO.getId());
        String oldStatus = oldRecord.getStatus();
        String newStatus = updateReqVO.getStatus();

        // 更新
        PracticeUserRecordDO updateObj = BeanUtils.toBean(updateReqVO, PracticeUserRecordDO.class);
        practiceUserRecordMapper.updateById(updateObj);

        // 如果状态从非 completed 变更为 completed,则累加培训人数
        if (!"completed".equals(oldStatus) && "completed".equals(newStatus)) {
            incrementTrainingCount(oldRecord.getCourseId(), oldRecord.getUserId());
        }
    }

    /**
     * 累加培训人数
     * @param courseId 课程ID
     * @param userId 用户ID
     */
    private void incrementTrainingCount(Long courseId, Long userId) {
        if (courseId == null) {
            return;
        }

        // 如果不允许重复累加,检查该用户是否已完成过该课程
        if (!practiceProperties.getTrainingCount().getAllowDuplicate()) {
            QueryWrapper<PracticeUserRecordDO> wrapper = new QueryWrapper<>();
            wrapper.eq("course_id", courseId)
                   .eq("user_id", userId)
                   .eq("status", "completed")
                   .last("LIMIT 2"); // 查询2条,如果有2条说明已经完成过了
            long count = practiceUserRecordMapper.selectCount(wrapper);
            if (count > 1) {
                // 已经完成过该课程,不累加培训人数
                return;
            }
        }

        // 1. 课程培训人数+1
        PracticeCourseDO course = practiceCourseMapper.selectById(courseId);
        if (course != null) {
            course.setTrainingCount(course.getTrainingCount() == null ? 1 : course.getTrainingCount() + 1);
            practiceCourseMapper.updateById(course);

            // 2. 剧本培训人数+1
            if (course.getScriptId() != null) {
                PracticeScriptDO script = practiceScriptMapper.selectById(course.getScriptId());
                if (script != null) {
                    script.setTrainingCount(script.getTrainingCount() == null ? 1 : script.getTrainingCount() + 1);
                    practiceScriptMapper.updateById(script);

                    // 3. 培训文件培训人数+1
                    if (StrUtil.isNotBlank(script.getMaterialIds())) {
                        String[] materialIdArray = script.getMaterialIds().split(",");
                        for (String materialIdStr : materialIdArray) {
                            try {
                                Long materialId = Long.parseLong(materialIdStr.trim());
                                PracticeMaterialDO material = practiceMaterialMapper.selectById(materialId);
                                if (material != null) {
                                    material.setTrainingCount(material.getTrainingCount() == null ? 1 : material.getTrainingCount() + 1);
                                    practiceMaterialMapper.updateById(material);
                                }
                            } catch (NumberFormatException e) {
                                // 忽略无效的ID
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void deletePracticeUserRecord(Long id) {
        // 校验存在
        validatePracticeUserRecordExists(id);
        // 删除
        practiceUserRecordMapper.deleteById(id);
    }

    @Override
        public void deletePracticeUserRecordListByIds(List<Long> ids) {
        // 删除
        practiceUserRecordMapper.deleteByIds(ids);
        }


    private void validatePracticeUserRecordExists(Long id) {
        if (practiceUserRecordMapper.selectById(id) == null) {
            throw exception(PRACTICE_USER_RECORD_NOT_EXISTS);
        }
    }

    @Override
    public PracticeUserRecordDO getPracticeUserRecord(Long id) {
        return practiceUserRecordMapper.selectById(id);
    }

    @Override
    public PageResult<PracticeUserRecordDO> getPracticeUserRecordPage(PracticeUserRecordPageReqVO pageReqVO) {
        return practiceUserRecordMapper.selectPage(pageReqVO);
    }

    @Override
    public PracticeUserRecordDO findUnfinishedRecord(Long courseId, Long vcustomerId, Long userId) {
        return practiceUserRecordMapper.findUnfinishedRecord(courseId, vcustomerId, userId);
    }

}