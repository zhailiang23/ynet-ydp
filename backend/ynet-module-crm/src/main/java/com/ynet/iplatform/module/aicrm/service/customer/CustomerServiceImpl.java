package com.ynet.iplatform.module.aicrm.service.customer;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customer.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customer.CustomerDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.customer.CustomerMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * CRM客户主表(零售+对公共用) Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerMapper customerMapper;

    @Override
    public Long createCustomer(CustomerSaveReqVO createReqVO) {
        // 插入
        CustomerDO customer = BeanUtils.toBean(createReqVO, CustomerDO.class);
        customerMapper.insert(customer);

        // 返回
        return customer.getId();
    }

    @Override
    public void updateCustomer(CustomerSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerExists(updateReqVO.getId());
        // 更新
        CustomerDO updateObj = BeanUtils.toBean(updateReqVO, CustomerDO.class);
        customerMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomer(Long id) {
        // 校验存在
        validateCustomerExists(id);
        // 删除
        customerMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerListByIds(List<Long> ids) {
        // 删除
        customerMapper.deleteByIds(ids);
        }


    private void validateCustomerExists(Long id) {
        if (customerMapper.selectById(id) == null) {
            throw exception(CUSTOMER_NOT_EXISTS);
        }
    }

    @Override
    public CustomerDO getCustomer(Long id) {
        return customerMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerDO> getCustomerPage(CustomerPageReqVO pageReqVO) {
        return customerMapper.selectPage(pageReqVO);
    }

}