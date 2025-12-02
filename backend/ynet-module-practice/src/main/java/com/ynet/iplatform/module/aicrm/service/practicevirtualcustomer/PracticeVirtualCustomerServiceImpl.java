package com.ynet.iplatform.module.aicrm.service.practicevirtualcustomer;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.practicevirtualcustomer.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.practicevirtualcustomer.PracticeVirtualCustomerDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.practicevirtualcustomer.PracticeVirtualCustomerMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * CRM智能陪练-虚拟客户 Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class PracticeVirtualCustomerServiceImpl implements PracticeVirtualCustomerService {

    @Resource
    private PracticeVirtualCustomerMapper practiceVirtualCustomerMapper;

    @Override
    public Long createPracticeVirtualCustomer(PracticeVirtualCustomerSaveReqVO createReqVO) {
        // 插入
        PracticeVirtualCustomerDO practiceVirtualCustomer = BeanUtils.toBean(createReqVO, PracticeVirtualCustomerDO.class);
        // 管理端创建的虚拟客户设置为公开
        practiceVirtualCustomer.setIsPublic(true);
        practiceVirtualCustomerMapper.insert(practiceVirtualCustomer);

        // 返回
        return practiceVirtualCustomer.getId();
    }

    @Override
    public void updatePracticeVirtualCustomer(PracticeVirtualCustomerSaveReqVO updateReqVO) {
        // 校验存在
        validatePracticeVirtualCustomerExists(updateReqVO.getId());
        // 更新
        PracticeVirtualCustomerDO updateObj = BeanUtils.toBean(updateReqVO, PracticeVirtualCustomerDO.class);
        practiceVirtualCustomerMapper.updateById(updateObj);
    }

    @Override
    public void deletePracticeVirtualCustomer(Long id) {
        // 校验存在
        validatePracticeVirtualCustomerExists(id);
        // 删除
        practiceVirtualCustomerMapper.deleteById(id);
    }

    @Override
        public void deletePracticeVirtualCustomerListByIds(List<Long> ids) {
        // 删除
        practiceVirtualCustomerMapper.deleteByIds(ids);
        }


    private void validatePracticeVirtualCustomerExists(Long id) {
        if (practiceVirtualCustomerMapper.selectById(id) == null) {
            throw exception(PRACTICE_VIRTUAL_CUSTOMER_NOT_EXISTS);
        }
    }

    @Override
    public PracticeVirtualCustomerDO getPracticeVirtualCustomer(Long id) {
        return practiceVirtualCustomerMapper.selectById(id);
    }

    @Override
    public PageResult<PracticeVirtualCustomerDO> getPracticeVirtualCustomerPage(PracticeVirtualCustomerPageReqVO pageReqVO) {
        return practiceVirtualCustomerMapper.selectPage(pageReqVO);
    }

    @Override
    public PracticeVirtualCustomerDO quickCreateVirtualCustomer(String name, String gender, Integer age,
                                                                  String occupation, String industry,
                                                                  String personalityType, String riskPreference,
                                                                  String memo) {
        // 创建虚拟客户记录
        PracticeVirtualCustomerDO customer = new PracticeVirtualCustomerDO();
        customer.setName(name);
        customer.setGender(gender);
        customer.setAge(age);
        customer.setOccupation(occupation);
        customer.setIndustry(industry);
        customer.setPersonalityType(personalityType);
        customer.setRiskPreference(riskPreference);
        customer.setMemo(memo);
        // 用户创建的虚拟客户(用于个性化课程)设置为不公开
        customer.setIsPublic(false);

        // 插入数据库
        practiceVirtualCustomerMapper.insert(customer);

        return customer;
    }

}