package cn.iocoder.yudao.module.aicrm.service.customeraccounttrust;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customeraccounttrust.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customeraccounttrust.CustomerAccountTrustDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.aicrm.dal.mysql.customeraccounttrust.CustomerAccountTrustMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

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