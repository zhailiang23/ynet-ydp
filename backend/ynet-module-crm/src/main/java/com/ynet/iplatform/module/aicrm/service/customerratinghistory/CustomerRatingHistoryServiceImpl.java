package com.ynet.iplatform.module.aicrm.service.customerratinghistory;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customerratinghistory.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerratinghistory.CustomerRatingHistoryDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.customerratinghistory.CustomerRatingHistoryMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户评级调整历史 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CustomerRatingHistoryServiceImpl implements CustomerRatingHistoryService {

    @Resource
    private CustomerRatingHistoryMapper customerRatingHistoryMapper;

    @Override
    public Long createCustomerRatingHistory(CustomerRatingHistorySaveReqVO createReqVO) {
        // 插入
        CustomerRatingHistoryDO customerRatingHistory = BeanUtils.toBean(createReqVO, CustomerRatingHistoryDO.class);
        customerRatingHistoryMapper.insert(customerRatingHistory);

        // 返回
        return customerRatingHistory.getId();
    }

    @Override
    public void updateCustomerRatingHistory(CustomerRatingHistorySaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerRatingHistoryExists(updateReqVO.getId());
        // 更新
        CustomerRatingHistoryDO updateObj = BeanUtils.toBean(updateReqVO, CustomerRatingHistoryDO.class);
        customerRatingHistoryMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerRatingHistory(Long id) {
        // 校验存在
        validateCustomerRatingHistoryExists(id);
        // 删除
        customerRatingHistoryMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerRatingHistoryListByIds(List<Long> ids) {
        // 删除
        customerRatingHistoryMapper.deleteByIds(ids);
        }


    private void validateCustomerRatingHistoryExists(Long id) {
        if (customerRatingHistoryMapper.selectById(id) == null) {
            throw exception(CUSTOMER_RATING_HISTORY_NOT_EXISTS);
        }
    }

    @Override
    public CustomerRatingHistoryDO getCustomerRatingHistory(Long id) {
        return customerRatingHistoryMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerRatingHistoryDO> getCustomerRatingHistoryPage(CustomerRatingHistoryPageReqVO pageReqVO) {
        return customerRatingHistoryMapper.selectPage(pageReqVO);
    }

}