package com.ynet.iplatform.module.aicrm.service.customergrouphistory;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customergrouphistory.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customergrouphistory.CustomerGroupHistoryDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.customergrouphistory.CustomerGroupHistoryMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户归属客群调整历史表（记录调整当时的客群信息快照） Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CustomerGroupHistoryServiceImpl implements CustomerGroupHistoryService {

    @Resource
    private CustomerGroupHistoryMapper customerGroupHistoryMapper;

    @Override
    public Long createCustomerGroupHistory(CustomerGroupHistorySaveReqVO createReqVO) {
        // 插入
        CustomerGroupHistoryDO customerGroupHistory = BeanUtils.toBean(createReqVO, CustomerGroupHistoryDO.class);
        customerGroupHistoryMapper.insert(customerGroupHistory);

        // 返回
        return customerGroupHistory.getId();
    }

    @Override
    public void updateCustomerGroupHistory(CustomerGroupHistorySaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerGroupHistoryExists(updateReqVO.getId());
        // 更新
        CustomerGroupHistoryDO updateObj = BeanUtils.toBean(updateReqVO, CustomerGroupHistoryDO.class);
        customerGroupHistoryMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerGroupHistory(Long id) {
        // 校验存在
        validateCustomerGroupHistoryExists(id);
        // 删除
        customerGroupHistoryMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerGroupHistoryListByIds(List<Long> ids) {
        // 删除
        customerGroupHistoryMapper.deleteByIds(ids);
        }


    private void validateCustomerGroupHistoryExists(Long id) {
        if (customerGroupHistoryMapper.selectById(id) == null) {
            throw exception(CUSTOMER_GROUP_HISTORY_NOT_EXISTS);
        }
    }

    @Override
    public CustomerGroupHistoryDO getCustomerGroupHistory(Long id) {
        return customerGroupHistoryMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerGroupHistoryDO> getCustomerGroupHistoryPage(CustomerGroupHistoryPageReqVO pageReqVO) {
        return customerGroupHistoryMapper.selectPage(pageReqVO);
    }

}