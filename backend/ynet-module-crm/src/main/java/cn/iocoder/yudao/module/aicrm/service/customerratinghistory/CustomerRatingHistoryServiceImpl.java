package cn.iocoder.yudao.module.aicrm.service.customerratinghistory;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerratinghistory.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerratinghistory.CustomerRatingHistoryDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.aicrm.dal.mysql.customerratinghistory.CustomerRatingHistoryMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

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