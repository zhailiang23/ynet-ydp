package com.ynet.iplatform.module.aicrm.service.customerreminder;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customerreminder.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerreminder.CustomerReminderDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.customerreminder.CustomerReminderMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户提醒信息 Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class CustomerReminderServiceImpl implements CustomerReminderService {

    @Resource
    private CustomerReminderMapper customerReminderMapper;

    @Override
    public Long createCustomerReminder(CustomerReminderSaveReqVO createReqVO) {
        // 插入
        CustomerReminderDO customerReminder = BeanUtils.toBean(createReqVO, CustomerReminderDO.class);
        customerReminderMapper.insert(customerReminder);

        // 返回
        return customerReminder.getId();
    }

    @Override
    public void updateCustomerReminder(CustomerReminderSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerReminderExists(updateReqVO.getId());
        // 更新
        CustomerReminderDO updateObj = BeanUtils.toBean(updateReqVO, CustomerReminderDO.class);
        customerReminderMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerReminder(Long id) {
        // 校验存在
        validateCustomerReminderExists(id);
        // 删除
        customerReminderMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerReminderListByIds(List<Long> ids) {
        // 删除
        customerReminderMapper.deleteByIds(ids);
        }


    private void validateCustomerReminderExists(Long id) {
        if (customerReminderMapper.selectById(id) == null) {
            throw exception(CUSTOMER_REMINDER_NOT_EXISTS);
        }
    }

    @Override
    public CustomerReminderDO getCustomerReminder(Long id) {
        return customerReminderMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerReminderDO> getCustomerReminderPage(CustomerReminderPageReqVO pageReqVO) {
        return customerReminderMapper.selectPage(pageReqVO);
    }

}