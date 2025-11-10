package cn.iocoder.yudao.module.aicrm.service.practiceuserrecord;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.practiceuserrecord.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.practiceuserrecord.PracticeUserRecordDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.aicrm.dal.mysql.practiceuserrecord.PracticeUserRecordMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

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

    @Override
    public Long createPracticeUserRecord(PracticeUserRecordSaveReqVO createReqVO) {
        // 插入
        PracticeUserRecordDO practiceUserRecord = BeanUtils.toBean(createReqVO, PracticeUserRecordDO.class);
        practiceUserRecordMapper.insert(practiceUserRecord);

        // 返回
        return practiceUserRecord.getId();
    }

    @Override
    public void updatePracticeUserRecord(PracticeUserRecordSaveReqVO updateReqVO) {
        // 校验存在
        validatePracticeUserRecordExists(updateReqVO.getId());
        // 更新
        PracticeUserRecordDO updateObj = BeanUtils.toBean(updateReqVO, PracticeUserRecordDO.class);
        practiceUserRecordMapper.updateById(updateObj);
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

}