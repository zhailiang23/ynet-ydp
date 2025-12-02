package com.ynet.iplatform.module.aicrm.service.customeraccountinsurance;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customeraccountinsurance.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customeraccountinsurance.CustomerAccountInsuranceDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.customeraccountinsurance.CustomerAccountInsuranceMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户保险账户信息表（零售+对公共用） Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class CustomerAccountInsuranceServiceImpl implements CustomerAccountInsuranceService {

    @Resource
    private CustomerAccountInsuranceMapper customerAccountInsuranceMapper;

    @Override
    public Long createCustomerAccountInsurance(CustomerAccountInsuranceSaveReqVO createReqVO) {
        // 插入
        CustomerAccountInsuranceDO customerAccountInsurance = BeanUtils.toBean(createReqVO, CustomerAccountInsuranceDO.class);
        customerAccountInsuranceMapper.insert(customerAccountInsurance);

        // 返回
        return customerAccountInsurance.getId();
    }

    @Override
    public void updateCustomerAccountInsurance(CustomerAccountInsuranceSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerAccountInsuranceExists(updateReqVO.getId());
        // 更新
        CustomerAccountInsuranceDO updateObj = BeanUtils.toBean(updateReqVO, CustomerAccountInsuranceDO.class);
        customerAccountInsuranceMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerAccountInsurance(Long id) {
        // 校验存在
        validateCustomerAccountInsuranceExists(id);
        // 删除
        customerAccountInsuranceMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerAccountInsuranceListByIds(List<Long> ids) {
        // 删除
        customerAccountInsuranceMapper.deleteByIds(ids);
        }


    private void validateCustomerAccountInsuranceExists(Long id) {
        if (customerAccountInsuranceMapper.selectById(id) == null) {
            throw exception(CUSTOMER_ACCOUNT_INSURANCE_NOT_EXISTS);
        }
    }

    @Override
    public CustomerAccountInsuranceDO getCustomerAccountInsurance(Long id) {
        return customerAccountInsuranceMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerAccountInsuranceDO> getCustomerAccountInsurancePage(CustomerAccountInsurancePageReqVO pageReqVO) {
        return customerAccountInsuranceMapper.selectPage(pageReqVO);
    }

}