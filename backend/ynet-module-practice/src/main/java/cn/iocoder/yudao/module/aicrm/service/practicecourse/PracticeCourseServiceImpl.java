package cn.iocoder.yudao.module.aicrm.service.practicecourse;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.practicecourse.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.practicecourse.PracticeCourseDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.aicrm.dal.mysql.practicecourse.PracticeCourseMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * CRM智能陪练-陪练课程 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class PracticeCourseServiceImpl implements PracticeCourseService {

    @Resource
    private PracticeCourseMapper practiceCourseMapper;

    @Resource
    private cn.iocoder.yudao.module.aicrm.dal.mysql.practicescript.PracticeScriptMapper practiceScriptMapper;

    @Override
    public Long createPracticeCourse(PracticeCourseSaveReqVO createReqVO) {
        // 插入
        PracticeCourseDO practiceCourse = BeanUtils.toBean(createReqVO, PracticeCourseDO.class);
        practiceCourseMapper.insert(practiceCourse);

        // 返回
        return practiceCourse.getId();
    }

    @Override
    public void updatePracticeCourse(PracticeCourseSaveReqVO updateReqVO) {
        // 校验存在
        validatePracticeCourseExists(updateReqVO.getId());
        // 更新
        PracticeCourseDO updateObj = BeanUtils.toBean(updateReqVO, PracticeCourseDO.class);
        practiceCourseMapper.updateById(updateObj);
    }

    @Override
    public void deletePracticeCourse(Long id) {
        // 校验存在
        validatePracticeCourseExists(id);
        // 删除
        practiceCourseMapper.deleteById(id);
    }

    @Override
        public void deletePracticeCourseListByIds(List<Long> ids) {
        // 删除
        practiceCourseMapper.deleteByIds(ids);
        }


    private void validatePracticeCourseExists(Long id) {
        if (practiceCourseMapper.selectById(id) == null) {
            throw exception(PRACTICE_COURSE_NOT_EXISTS);
        }
    }

    @Override
    public PracticeCourseDO getPracticeCourse(Long id) {
        return practiceCourseMapper.selectById(id);
    }

    @Override
    public PageResult<PracticeCourseRespVO> getPracticeCoursePage(PracticeCoursePageReqVO pageReqVO) {
        // 查询分页数据
        PageResult<PracticeCourseDO> pageResult = practiceCourseMapper.selectPage(pageReqVO);

        // 转换为 VO
        PageResult<PracticeCourseRespVO> voPageResult = BeanUtils.toBean(pageResult, PracticeCourseRespVO.class);

        // 收集所有的剧本 ID
        Set<Long> scriptIds = new HashSet<>();
        for (PracticeCourseRespVO vo : voPageResult.getList()) {
            if (vo.getScriptId() != null) {
                scriptIds.add(vo.getScriptId());
            }
        }

        // 批量查询剧本信息
        Map<Long, cn.iocoder.yudao.module.aicrm.dal.dataobject.practicescript.PracticeScriptDO> scriptMap = new HashMap<>();
        if (!scriptIds.isEmpty()) {
            List<cn.iocoder.yudao.module.aicrm.dal.dataobject.practicescript.PracticeScriptDO> scripts =
                practiceScriptMapper.selectBatchIds(scriptIds);
            for (cn.iocoder.yudao.module.aicrm.dal.dataobject.practicescript.PracticeScriptDO script : scripts) {
                scriptMap.put(script.getId(), script);
            }
        }

        // 填充剧本名称和版本
        for (PracticeCourseRespVO vo : voPageResult.getList()) {
            if (vo.getScriptId() != null) {
                cn.iocoder.yudao.module.aicrm.dal.dataobject.practicescript.PracticeScriptDO script = scriptMap.get(vo.getScriptId());
                if (script != null) {
                    vo.setScriptName(script.getName());
                    vo.setScriptVersion(script.getVersion());
                }
            }
        }

        return voPageResult;
    }

}