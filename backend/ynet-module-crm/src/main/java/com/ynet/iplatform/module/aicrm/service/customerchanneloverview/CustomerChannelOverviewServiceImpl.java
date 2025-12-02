package com.ynet.iplatform.module.aicrm.service.customerchanneloverview;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customerchanneloverview.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerchanneloverview.CustomerChannelOverviewDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.customerchanneloverview.CustomerChannelOverviewMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户渠道业务概览 Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class CustomerChannelOverviewServiceImpl implements CustomerChannelOverviewService {

    @Resource
    private CustomerChannelOverviewMapper customerChannelOverviewMapper;

    @Override
    public Long createCustomerChannelOverview(CustomerChannelOverviewSaveReqVO createReqVO) {
        // 插入
        CustomerChannelOverviewDO customerChannelOverview = BeanUtils.toBean(createReqVO, CustomerChannelOverviewDO.class);
        customerChannelOverviewMapper.insert(customerChannelOverview);

        // 返回
        return customerChannelOverview.getId();
    }

    @Override
    public void updateCustomerChannelOverview(CustomerChannelOverviewSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerChannelOverviewExists(updateReqVO.getId());
        // 更新
        CustomerChannelOverviewDO updateObj = BeanUtils.toBean(updateReqVO, CustomerChannelOverviewDO.class);
        customerChannelOverviewMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerChannelOverview(Long id) {
        // 校验存在
        validateCustomerChannelOverviewExists(id);
        // 删除
        customerChannelOverviewMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerChannelOverviewListByIds(List<Long> ids) {
        // 删除
        customerChannelOverviewMapper.deleteByIds(ids);
        }


    private void validateCustomerChannelOverviewExists(Long id) {
        if (customerChannelOverviewMapper.selectById(id) == null) {
            throw exception(CUSTOMER_CHANNEL_OVERVIEW_NOT_EXISTS);
        }
    }

    @Override
    public CustomerChannelOverviewDO getCustomerChannelOverview(Long id) {
        return customerChannelOverviewMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerChannelOverviewDO> getCustomerChannelOverviewPage(CustomerChannelOverviewPageReqVO pageReqVO) {
        return customerChannelOverviewMapper.selectPage(pageReqVO);
    }

}