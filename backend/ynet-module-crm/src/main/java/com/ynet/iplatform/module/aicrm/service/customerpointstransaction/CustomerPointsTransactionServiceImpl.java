package com.ynet.iplatform.module.aicrm.service.customerpointstransaction;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customerpointstransaction.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerpointstransaction.CustomerPointsTransactionDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.customerpointstransaction.CustomerPointsTransactionMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户积分消费明细表（积分交易流水表） Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class CustomerPointsTransactionServiceImpl implements CustomerPointsTransactionService {

    @Resource
    private CustomerPointsTransactionMapper customerPointsTransactionMapper;

    @Override
    public Long createCustomerPointsTransaction(CustomerPointsTransactionSaveReqVO createReqVO) {
        // 插入
        CustomerPointsTransactionDO customerPointsTransaction = BeanUtils.toBean(createReqVO, CustomerPointsTransactionDO.class);
        customerPointsTransactionMapper.insert(customerPointsTransaction);

        // 返回
        return customerPointsTransaction.getId();
    }

    @Override
    public void updateCustomerPointsTransaction(CustomerPointsTransactionSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerPointsTransactionExists(updateReqVO.getId());
        // 更新
        CustomerPointsTransactionDO updateObj = BeanUtils.toBean(updateReqVO, CustomerPointsTransactionDO.class);
        customerPointsTransactionMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerPointsTransaction(Long id) {
        // 校验存在
        validateCustomerPointsTransactionExists(id);
        // 删除
        customerPointsTransactionMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerPointsTransactionListByIds(List<Long> ids) {
        // 删除
        customerPointsTransactionMapper.deleteByIds(ids);
        }


    private void validateCustomerPointsTransactionExists(Long id) {
        if (customerPointsTransactionMapper.selectById(id) == null) {
            throw exception(CUSTOMER_POINTS_TRANSACTION_NOT_EXISTS);
        }
    }

    @Override
    public CustomerPointsTransactionDO getCustomerPointsTransaction(Long id) {
        return customerPointsTransactionMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerPointsTransactionDO> getCustomerPointsTransactionPage(CustomerPointsTransactionPageReqVO pageReqVO) {
        return customerPointsTransactionMapper.selectPage(pageReqVO);
    }

}