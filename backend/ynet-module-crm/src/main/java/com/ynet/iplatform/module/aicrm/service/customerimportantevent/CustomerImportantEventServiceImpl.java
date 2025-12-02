package com.ynet.iplatform.module.aicrm.service.customerimportantevent;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customerimportantevent.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerimportantevent.CustomerImportantEventDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.customerimportantevent.CustomerImportantEventMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户重要事件表（零售+对公共用） Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class CustomerImportantEventServiceImpl implements CustomerImportantEventService {

    @Resource
    private CustomerImportantEventMapper customerImportantEventMapper;

    @Override
    public Long createCustomerImportantEvent(CustomerImportantEventSaveReqVO createReqVO) {
        // 插入
        CustomerImportantEventDO customerImportantEvent = BeanUtils.toBean(createReqVO, CustomerImportantEventDO.class);
        customerImportantEventMapper.insert(customerImportantEvent);

        // 返回
        return customerImportantEvent.getId();
    }

    @Override
    public void updateCustomerImportantEvent(CustomerImportantEventSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerImportantEventExists(updateReqVO.getId());
        // 更新
        CustomerImportantEventDO updateObj = BeanUtils.toBean(updateReqVO, CustomerImportantEventDO.class);
        customerImportantEventMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerImportantEvent(Long id) {
        // 校验存在
        validateCustomerImportantEventExists(id);
        // 删除
        customerImportantEventMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerImportantEventListByIds(List<Long> ids) {
        // 删除
        customerImportantEventMapper.deleteByIds(ids);
        }


    private void validateCustomerImportantEventExists(Long id) {
        if (customerImportantEventMapper.selectById(id) == null) {
            throw exception(CUSTOMER_IMPORTANT_EVENT_NOT_EXISTS);
        }
    }

    @Override
    public CustomerImportantEventDO getCustomerImportantEvent(Long id) {
        return customerImportantEventMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerImportantEventDO> getCustomerImportantEventPage(CustomerImportantEventPageReqVO pageReqVO) {
        return customerImportantEventMapper.selectPage(pageReqVO);
    }

}