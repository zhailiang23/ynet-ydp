package com.ynet.iplatform.module.aicrm.service.customeroffbalanceoverview;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customeroffbalanceoverview.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customeroffbalanceoverview.CustomerOffbalanceOverviewDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.customeroffbalanceoverview.CustomerOffbalanceOverviewMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户表外业务概览 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CustomerOffbalanceOverviewServiceImpl implements CustomerOffbalanceOverviewService {

    @Resource
    private CustomerOffbalanceOverviewMapper customerOffbalanceOverviewMapper;

    @Override
    public Long createCustomerOffbalanceOverview(CustomerOffbalanceOverviewSaveReqVO createReqVO) {
        // 插入
        CustomerOffbalanceOverviewDO customerOffbalanceOverview = BeanUtils.toBean(createReqVO, CustomerOffbalanceOverviewDO.class);
        customerOffbalanceOverviewMapper.insert(customerOffbalanceOverview);

        // 返回
        return customerOffbalanceOverview.getId();
    }

    @Override
    public void updateCustomerOffbalanceOverview(CustomerOffbalanceOverviewSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerOffbalanceOverviewExists(updateReqVO.getId());
        // 更新
        CustomerOffbalanceOverviewDO updateObj = BeanUtils.toBean(updateReqVO, CustomerOffbalanceOverviewDO.class);
        customerOffbalanceOverviewMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerOffbalanceOverview(Long id) {
        // 校验存在
        validateCustomerOffbalanceOverviewExists(id);
        // 删除
        customerOffbalanceOverviewMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerOffbalanceOverviewListByIds(List<Long> ids) {
        // 删除
        customerOffbalanceOverviewMapper.deleteByIds(ids);
        }


    private void validateCustomerOffbalanceOverviewExists(Long id) {
        if (customerOffbalanceOverviewMapper.selectById(id) == null) {
            throw exception(CUSTOMER_OFFBALANCE_OVERVIEW_NOT_EXISTS);
        }
    }

    @Override
    public CustomerOffbalanceOverviewDO getCustomerOffbalanceOverview(Long id) {
        return customerOffbalanceOverviewMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerOffbalanceOverviewDO> getCustomerOffbalanceOverviewPage(CustomerOffbalanceOverviewPageReqVO pageReqVO) {
        return customerOffbalanceOverviewMapper.selectPage(pageReqVO);
    }

}