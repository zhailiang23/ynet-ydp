package com.ynet.iplatform.module.aicrm.service.customermarketingactivity;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customermarketingactivity.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customermarketingactivity.CustomerMarketingActivityDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.customermarketingactivity.CustomerMarketingActivityMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户营销活动信息 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CustomerMarketingActivityServiceImpl implements CustomerMarketingActivityService {

    @Resource
    private CustomerMarketingActivityMapper customerMarketingActivityMapper;

    @Override
    public Long createCustomerMarketingActivity(CustomerMarketingActivitySaveReqVO createReqVO) {
        // 插入
        CustomerMarketingActivityDO customerMarketingActivity = BeanUtils.toBean(createReqVO, CustomerMarketingActivityDO.class);
        customerMarketingActivityMapper.insert(customerMarketingActivity);

        // 返回
        return customerMarketingActivity.getId();
    }

    @Override
    public void updateCustomerMarketingActivity(CustomerMarketingActivitySaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerMarketingActivityExists(updateReqVO.getId());
        // 更新
        CustomerMarketingActivityDO updateObj = BeanUtils.toBean(updateReqVO, CustomerMarketingActivityDO.class);
        customerMarketingActivityMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerMarketingActivity(Long id) {
        // 校验存在
        validateCustomerMarketingActivityExists(id);
        // 删除
        customerMarketingActivityMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerMarketingActivityListByIds(List<Long> ids) {
        // 删除
        customerMarketingActivityMapper.deleteByIds(ids);
        }


    private void validateCustomerMarketingActivityExists(Long id) {
        if (customerMarketingActivityMapper.selectById(id) == null) {
            throw exception(CUSTOMER_MARKETING_ACTIVITY_NOT_EXISTS);
        }
    }

    @Override
    public CustomerMarketingActivityDO getCustomerMarketingActivity(Long id) {
        return customerMarketingActivityMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerMarketingActivityDO> getCustomerMarketingActivityPage(CustomerMarketingActivityPageReqVO pageReqVO) {
        return customerMarketingActivityMapper.selectPage(pageReqVO);
    }

}