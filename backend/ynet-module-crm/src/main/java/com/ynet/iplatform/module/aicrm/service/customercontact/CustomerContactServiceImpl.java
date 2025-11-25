package com.ynet.iplatform.module.aicrm.service.customercontact;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customercontact.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customercontact.CustomerContactDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.customercontact.CustomerContactMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户接触信息 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CustomerContactServiceImpl implements CustomerContactService {

    @Resource
    private CustomerContactMapper customerContactMapper;

    @Override
    public Long createCustomerContact(CustomerContactSaveReqVO createReqVO) {
        // 插入
        CustomerContactDO customerContact = BeanUtils.toBean(createReqVO, CustomerContactDO.class);
        customerContactMapper.insert(customerContact);

        // 返回
        return customerContact.getId();
    }

    @Override
    public void updateCustomerContact(CustomerContactSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerContactExists(updateReqVO.getId());
        // 更新
        CustomerContactDO updateObj = BeanUtils.toBean(updateReqVO, CustomerContactDO.class);
        customerContactMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerContact(Long id) {
        // 校验存在
        validateCustomerContactExists(id);
        // 删除
        customerContactMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerContactListByIds(List<Long> ids) {
        // 删除
        customerContactMapper.deleteByIds(ids);
        }


    private void validateCustomerContactExists(Long id) {
        if (customerContactMapper.selectById(id) == null) {
            throw exception(CUSTOMER_CONTACT_NOT_EXISTS);
        }
    }

    @Override
    public CustomerContactDO getCustomerContact(Long id) {
        return customerContactMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerContactDO> getCustomerContactPage(CustomerContactPageReqVO pageReqVO) {
        return customerContactMapper.selectPage(pageReqVO);
    }

}