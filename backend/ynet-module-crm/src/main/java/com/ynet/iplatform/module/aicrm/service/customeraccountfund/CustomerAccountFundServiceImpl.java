package com.ynet.iplatform.module.aicrm.service.customeraccountfund;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customeraccountfund.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customeraccountfund.CustomerAccountFundDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.customeraccountfund.CustomerAccountFundMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户基金账户信息表（零售+对公共用） Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class CustomerAccountFundServiceImpl implements CustomerAccountFundService {

    @Resource
    private CustomerAccountFundMapper customerAccountFundMapper;

    @Override
    public Long createCustomerAccountFund(CustomerAccountFundSaveReqVO createReqVO) {
        // 插入
        CustomerAccountFundDO customerAccountFund = BeanUtils.toBean(createReqVO, CustomerAccountFundDO.class);
        customerAccountFundMapper.insert(customerAccountFund);

        // 返回
        return customerAccountFund.getId();
    }

    @Override
    public void updateCustomerAccountFund(CustomerAccountFundSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerAccountFundExists(updateReqVO.getId());
        // 更新
        CustomerAccountFundDO updateObj = BeanUtils.toBean(updateReqVO, CustomerAccountFundDO.class);
        customerAccountFundMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerAccountFund(Long id) {
        // 校验存在
        validateCustomerAccountFundExists(id);
        // 删除
        customerAccountFundMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerAccountFundListByIds(List<Long> ids) {
        // 删除
        customerAccountFundMapper.deleteByIds(ids);
        }


    private void validateCustomerAccountFundExists(Long id) {
        if (customerAccountFundMapper.selectById(id) == null) {
            throw exception(CUSTOMER_ACCOUNT_FUND_NOT_EXISTS);
        }
    }

    @Override
    public CustomerAccountFundDO getCustomerAccountFund(Long id) {
        return customerAccountFundMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerAccountFundDO> getCustomerAccountFundPage(CustomerAccountFundPageReqVO pageReqVO) {
        return customerAccountFundMapper.selectPage(pageReqVO);
    }

}