package com.ynet.iplatform.module.aicrm.service.customerdepositoverview;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customerdepositoverview.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerdepositoverview.CustomerDepositOverviewDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.customerdepositoverview.CustomerDepositOverviewMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户存款业务概览 Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class CustomerDepositOverviewServiceImpl implements CustomerDepositOverviewService {

    @Resource
    private CustomerDepositOverviewMapper customerDepositOverviewMapper;

    @Override
    public Long createCustomerDepositOverview(CustomerDepositOverviewSaveReqVO createReqVO) {
        // 插入
        CustomerDepositOverviewDO customerDepositOverview = BeanUtils.toBean(createReqVO, CustomerDepositOverviewDO.class);
        customerDepositOverviewMapper.insert(customerDepositOverview);

        // 返回
        return customerDepositOverview.getId();
    }

    @Override
    public void updateCustomerDepositOverview(CustomerDepositOverviewSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerDepositOverviewExists(updateReqVO.getId());
        // 更新
        CustomerDepositOverviewDO updateObj = BeanUtils.toBean(updateReqVO, CustomerDepositOverviewDO.class);
        customerDepositOverviewMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerDepositOverview(Long id) {
        // 校验存在
        validateCustomerDepositOverviewExists(id);
        // 删除
        customerDepositOverviewMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerDepositOverviewListByIds(List<Long> ids) {
        // 删除
        customerDepositOverviewMapper.deleteByIds(ids);
        }


    private void validateCustomerDepositOverviewExists(Long id) {
        if (customerDepositOverviewMapper.selectById(id) == null) {
            throw exception(CUSTOMER_DEPOSIT_OVERVIEW_NOT_EXISTS);
        }
    }

    @Override
    public CustomerDepositOverviewDO getCustomerDepositOverview(Long id) {
        return customerDepositOverviewMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerDepositOverviewDO> getCustomerDepositOverviewPage(CustomerDepositOverviewPageReqVO pageReqVO) {
        return customerDepositOverviewMapper.selectPage(pageReqVO);
    }

}