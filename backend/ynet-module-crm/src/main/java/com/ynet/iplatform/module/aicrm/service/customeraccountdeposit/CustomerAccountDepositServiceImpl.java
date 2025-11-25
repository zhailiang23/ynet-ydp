package com.ynet.iplatform.module.aicrm.service.customeraccountdeposit;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customeraccountdeposit.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customeraccountdeposit.CustomerAccountDepositDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.customeraccountdeposit.CustomerAccountDepositMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户存款账户信息表（零售+对公共用） Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CustomerAccountDepositServiceImpl implements CustomerAccountDepositService {

    @Resource
    private CustomerAccountDepositMapper customerAccountDepositMapper;

    @Override
    public Long createCustomerAccountDeposit(CustomerAccountDepositSaveReqVO createReqVO) {
        // 插入
        CustomerAccountDepositDO customerAccountDeposit = BeanUtils.toBean(createReqVO, CustomerAccountDepositDO.class);
        customerAccountDepositMapper.insert(customerAccountDeposit);

        // 返回
        return customerAccountDeposit.getId();
    }

    @Override
    public void updateCustomerAccountDeposit(CustomerAccountDepositSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerAccountDepositExists(updateReqVO.getId());
        // 更新
        CustomerAccountDepositDO updateObj = BeanUtils.toBean(updateReqVO, CustomerAccountDepositDO.class);
        customerAccountDepositMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerAccountDeposit(Long id) {
        // 校验存在
        validateCustomerAccountDepositExists(id);
        // 删除
        customerAccountDepositMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerAccountDepositListByIds(List<Long> ids) {
        // 删除
        customerAccountDepositMapper.deleteByIds(ids);
        }


    private void validateCustomerAccountDepositExists(Long id) {
        if (customerAccountDepositMapper.selectById(id) == null) {
            throw exception(CUSTOMER_ACCOUNT_DEPOSIT_NOT_EXISTS);
        }
    }

    @Override
    public CustomerAccountDepositDO getCustomerAccountDeposit(Long id) {
        return customerAccountDepositMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerAccountDepositDO> getCustomerAccountDepositPage(CustomerAccountDepositPageReqVO pageReqVO) {
        return customerAccountDepositMapper.selectPage(pageReqVO);
    }

}