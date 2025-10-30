package cn.iocoder.yudao.module.aicrm.service.customerimportantevent;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerimportantevent.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerimportantevent.CustomerImportantEventDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.aicrm.dal.mysql.customerimportantevent.CustomerImportantEventMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户重要事件表（零售+对公共用） Service 实现类
 *
 * @author 芋道源码
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