package com.ynet.iplatform.module.aicrm.service.practicecase;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.practicecase.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.practicecase.PracticeCaseDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.practicecase.PracticeCaseMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * CRM智能陪练-销售案例 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class PracticeCaseServiceImpl implements PracticeCaseService {

    @Resource
    private PracticeCaseMapper practiceCaseMapper;

    @Override
    public Long createPracticeCase(PracticeCaseSaveReqVO createReqVO) {
        // 插入
        PracticeCaseDO practiceCase = BeanUtils.toBean(createReqVO, PracticeCaseDO.class);
        practiceCaseMapper.insert(practiceCase);

        // 返回
        return practiceCase.getId();
    }

    @Override
    public void updatePracticeCase(PracticeCaseSaveReqVO updateReqVO) {
        // 校验存在
        validatePracticeCaseExists(updateReqVO.getId());
        // 更新
        PracticeCaseDO updateObj = BeanUtils.toBean(updateReqVO, PracticeCaseDO.class);
        practiceCaseMapper.updateById(updateObj);
    }

    @Override
    public void deletePracticeCase(Long id) {
        // 校验存在
        validatePracticeCaseExists(id);
        // 删除
        practiceCaseMapper.deleteById(id);
    }

    @Override
        public void deletePracticeCaseListByIds(List<Long> ids) {
        // 删除
        practiceCaseMapper.deleteByIds(ids);
        }


    private void validatePracticeCaseExists(Long id) {
        if (practiceCaseMapper.selectById(id) == null) {
            throw exception(PRACTICE_CASE_NOT_EXISTS);
        }
    }

    @Override
    public PracticeCaseDO getPracticeCase(Long id) {
        return practiceCaseMapper.selectById(id);
    }

    @Override
    public PageResult<PracticeCaseDO> getPracticeCasePage(PracticeCasePageReqVO pageReqVO) {
        return practiceCaseMapper.selectPage(pageReqVO);
    }

}