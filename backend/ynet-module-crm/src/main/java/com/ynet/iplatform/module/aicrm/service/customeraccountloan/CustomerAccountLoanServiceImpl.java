package com.ynet.iplatform.module.aicrm.service.customeraccountloan;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customeraccountloan.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customeraccountloan.CustomerAccountLoanDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.customeraccountloan.CustomerAccountLoanMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户贷款账户信息表（零售+对公共用） Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class CustomerAccountLoanServiceImpl implements CustomerAccountLoanService {

    @Resource
    private CustomerAccountLoanMapper customerAccountLoanMapper;

    @Override
    public Long createCustomerAccountLoan(CustomerAccountLoanSaveReqVO createReqVO) {
        // 插入
        CustomerAccountLoanDO customerAccountLoan = BeanUtils.toBean(createReqVO, CustomerAccountLoanDO.class);
        customerAccountLoanMapper.insert(customerAccountLoan);

        // 返回
        return customerAccountLoan.getId();
    }

    @Override
    public void updateCustomerAccountLoan(CustomerAccountLoanSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerAccountLoanExists(updateReqVO.getId());
        // 更新
        CustomerAccountLoanDO updateObj = BeanUtils.toBean(updateReqVO, CustomerAccountLoanDO.class);
        customerAccountLoanMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerAccountLoan(Long id) {
        // 校验存在
        validateCustomerAccountLoanExists(id);
        // 删除
        customerAccountLoanMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerAccountLoanListByIds(List<Long> ids) {
        // 删除
        customerAccountLoanMapper.deleteByIds(ids);
        }


    private void validateCustomerAccountLoanExists(Long id) {
        if (customerAccountLoanMapper.selectById(id) == null) {
            throw exception(CUSTOMER_ACCOUNT_LOAN_NOT_EXISTS);
        }
    }

    @Override
    public CustomerAccountLoanDO getCustomerAccountLoan(Long id) {
        return customerAccountLoanMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerAccountLoanDO> getCustomerAccountLoanPage(CustomerAccountLoanPageReqVO pageReqVO) {
        return customerAccountLoanMapper.selectPage(pageReqVO);
    }

}