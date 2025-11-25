package com.ynet.iplatform.module.aicrm.service.customeraccounttrust;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customeraccounttrust.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customeraccounttrust.CustomerAccountTrustDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.customeraccounttrust.CustomerAccountTrustMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户信托账户信息表（零售+对公共用） Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CustomerAccountTrustServiceImpl implements CustomerAccountTrustService {

    @Resource
    private CustomerAccountTrustMapper customerAccountTrustMapper;

    @Override
    public Long createCustomerAccountTrust(CustomerAccountTrustSaveReqVO createReqVO) {
        // 插入
        CustomerAccountTrustDO customerAccountTrust = BeanUtils.toBean(createReqVO, CustomerAccountTrustDO.class);
        customerAccountTrustMapper.insert(customerAccountTrust);

        // 返回
        return customerAccountTrust.getId();
    }

    @Override
    public void updateCustomerAccountTrust(CustomerAccountTrustSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerAccountTrustExists(updateReqVO.getId());
        // 更新
        CustomerAccountTrustDO updateObj = BeanUtils.toBean(updateReqVO, CustomerAccountTrustDO.class);
        customerAccountTrustMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerAccountTrust(Long id) {
        // 校验存在
        validateCustomerAccountTrustExists(id);
        // 删除
        customerAccountTrustMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerAccountTrustListByIds(List<Long> ids) {
        // 删除
        customerAccountTrustMapper.deleteByIds(ids);
        }


    private void validateCustomerAccountTrustExists(Long id) {
        if (customerAccountTrustMapper.selectById(id) == null) {
            throw exception(CUSTOMER_ACCOUNT_TRUST_NOT_EXISTS);
        }
    }

    @Override
    public CustomerAccountTrustDO getCustomerAccountTrust(Long id) {
        return customerAccountTrustMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerAccountTrustDO> getCustomerAccountTrustPage(CustomerAccountTrustPageReqVO pageReqVO) {
        return customerAccountTrustMapper.selectPage(pageReqVO);
    }

}