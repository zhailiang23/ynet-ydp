package com.ynet.iplatform.module.aicrm.service.customerassignmenthistory;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customerassignmenthistory.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerassignmenthistory.CustomerAssignmentHistoryDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.customerassignmenthistory.CustomerAssignmentHistoryMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户归属调整历史表（零售+对公共用，记录所有归属变更历史） Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CustomerAssignmentHistoryServiceImpl implements CustomerAssignmentHistoryService {

    @Resource
    private CustomerAssignmentHistoryMapper customerAssignmentHistoryMapper;

    @Override
    public Long createCustomerAssignmentHistory(CustomerAssignmentHistorySaveReqVO createReqVO) {
        // 插入
        CustomerAssignmentHistoryDO customerAssignmentHistory = BeanUtils.toBean(createReqVO, CustomerAssignmentHistoryDO.class);
        customerAssignmentHistoryMapper.insert(customerAssignmentHistory);

        // 返回
        return customerAssignmentHistory.getId();
    }

    @Override
    public void updateCustomerAssignmentHistory(CustomerAssignmentHistorySaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerAssignmentHistoryExists(updateReqVO.getId());
        // 更新
        CustomerAssignmentHistoryDO updateObj = BeanUtils.toBean(updateReqVO, CustomerAssignmentHistoryDO.class);
        customerAssignmentHistoryMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerAssignmentHistory(Long id) {
        // 校验存在
        validateCustomerAssignmentHistoryExists(id);
        // 删除
        customerAssignmentHistoryMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerAssignmentHistoryListByIds(List<Long> ids) {
        // 删除
        customerAssignmentHistoryMapper.deleteByIds(ids);
        }


    private void validateCustomerAssignmentHistoryExists(Long id) {
        if (customerAssignmentHistoryMapper.selectById(id) == null) {
            throw exception(CUSTOMER_ASSIGNMENT_HISTORY_NOT_EXISTS);
        }
    }

    @Override
    public CustomerAssignmentHistoryDO getCustomerAssignmentHistory(Long id) {
        return customerAssignmentHistoryMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerAssignmentHistoryDO> getCustomerAssignmentHistoryPage(CustomerAssignmentHistoryPageReqVO pageReqVO) {
        return customerAssignmentHistoryMapper.selectPage(pageReqVO);
    }

}