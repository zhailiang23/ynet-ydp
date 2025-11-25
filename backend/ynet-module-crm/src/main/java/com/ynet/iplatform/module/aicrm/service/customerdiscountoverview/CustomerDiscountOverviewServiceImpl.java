package com.ynet.iplatform.module.aicrm.service.customerdiscountoverview;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customerdiscountoverview.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerdiscountoverview.CustomerDiscountOverviewDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.customerdiscountoverview.CustomerDiscountOverviewMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户贴现业务概览 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CustomerDiscountOverviewServiceImpl implements CustomerDiscountOverviewService {

    @Resource
    private CustomerDiscountOverviewMapper customerDiscountOverviewMapper;

    @Override
    public Long createCustomerDiscountOverview(CustomerDiscountOverviewSaveReqVO createReqVO) {
        // 插入
        CustomerDiscountOverviewDO customerDiscountOverview = BeanUtils.toBean(createReqVO, CustomerDiscountOverviewDO.class);
        customerDiscountOverviewMapper.insert(customerDiscountOverview);

        // 返回
        return customerDiscountOverview.getId();
    }

    @Override
    public void updateCustomerDiscountOverview(CustomerDiscountOverviewSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerDiscountOverviewExists(updateReqVO.getId());
        // 更新
        CustomerDiscountOverviewDO updateObj = BeanUtils.toBean(updateReqVO, CustomerDiscountOverviewDO.class);
        customerDiscountOverviewMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerDiscountOverview(Long id) {
        // 校验存在
        validateCustomerDiscountOverviewExists(id);
        // 删除
        customerDiscountOverviewMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerDiscountOverviewListByIds(List<Long> ids) {
        // 删除
        customerDiscountOverviewMapper.deleteByIds(ids);
        }


    private void validateCustomerDiscountOverviewExists(Long id) {
        if (customerDiscountOverviewMapper.selectById(id) == null) {
            throw exception(CUSTOMER_DISCOUNT_OVERVIEW_NOT_EXISTS);
        }
    }

    @Override
    public CustomerDiscountOverviewDO getCustomerDiscountOverview(Long id) {
        return customerDiscountOverviewMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerDiscountOverviewDO> getCustomerDiscountOverviewPage(CustomerDiscountOverviewPageReqVO pageReqVO) {
        return customerDiscountOverviewMapper.selectPage(pageReqVO);
    }

}