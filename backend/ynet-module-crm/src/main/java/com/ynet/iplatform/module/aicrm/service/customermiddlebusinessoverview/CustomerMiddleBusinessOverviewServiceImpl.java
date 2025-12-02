package com.ynet.iplatform.module.aicrm.service.customermiddlebusinessoverview;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customermiddlebusinessoverview.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customermiddlebusinessoverview.CustomerMiddleBusinessOverviewDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.customermiddlebusinessoverview.CustomerMiddleBusinessOverviewMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户中间业务概览 Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class CustomerMiddleBusinessOverviewServiceImpl implements CustomerMiddleBusinessOverviewService {

    @Resource
    private CustomerMiddleBusinessOverviewMapper customerMiddleBusinessOverviewMapper;

    @Override
    public Long createCustomerMiddleBusinessOverview(CustomerMiddleBusinessOverviewSaveReqVO createReqVO) {
        // 插入
        CustomerMiddleBusinessOverviewDO customerMiddleBusinessOverview = BeanUtils.toBean(createReqVO, CustomerMiddleBusinessOverviewDO.class);
        customerMiddleBusinessOverviewMapper.insert(customerMiddleBusinessOverview);

        // 返回
        return customerMiddleBusinessOverview.getId();
    }

    @Override
    public void updateCustomerMiddleBusinessOverview(CustomerMiddleBusinessOverviewSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerMiddleBusinessOverviewExists(updateReqVO.getId());
        // 更新
        CustomerMiddleBusinessOverviewDO updateObj = BeanUtils.toBean(updateReqVO, CustomerMiddleBusinessOverviewDO.class);
        customerMiddleBusinessOverviewMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerMiddleBusinessOverview(Long id) {
        // 校验存在
        validateCustomerMiddleBusinessOverviewExists(id);
        // 删除
        customerMiddleBusinessOverviewMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerMiddleBusinessOverviewListByIds(List<Long> ids) {
        // 删除
        customerMiddleBusinessOverviewMapper.deleteByIds(ids);
        }


    private void validateCustomerMiddleBusinessOverviewExists(Long id) {
        if (customerMiddleBusinessOverviewMapper.selectById(id) == null) {
            throw exception(CUSTOMER_MIDDLE_BUSINESS_OVERVIEW_NOT_EXISTS);
        }
    }

    @Override
    public CustomerMiddleBusinessOverviewDO getCustomerMiddleBusinessOverview(Long id) {
        return customerMiddleBusinessOverviewMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerMiddleBusinessOverviewDO> getCustomerMiddleBusinessOverviewPage(CustomerMiddleBusinessOverviewPageReqVO pageReqVO) {
        return customerMiddleBusinessOverviewMapper.selectPage(pageReqVO);
    }

}