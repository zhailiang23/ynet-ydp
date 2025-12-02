package com.ynet.iplatform.module.aicrm.service.customertransactionmock;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customertransactionmock.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customertransactionmock.CustomerTransactionMockDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.customertransactionmock.CustomerTransactionMockMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户交易明细信息表（Mock数据） Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class CustomerTransactionMockServiceImpl implements CustomerTransactionMockService {

    @Resource
    private CustomerTransactionMockMapper customerTransactionMockMapper;

    @Override
    public Long createCustomerTransactionMock(CustomerTransactionMockSaveReqVO createReqVO) {
        // 插入
        CustomerTransactionMockDO customerTransactionMock = BeanUtils.toBean(createReqVO, CustomerTransactionMockDO.class);
        customerTransactionMockMapper.insert(customerTransactionMock);

        // 返回
        return customerTransactionMock.getId();
    }

    @Override
    public void updateCustomerTransactionMock(CustomerTransactionMockSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerTransactionMockExists(updateReqVO.getId());
        // 更新
        CustomerTransactionMockDO updateObj = BeanUtils.toBean(updateReqVO, CustomerTransactionMockDO.class);
        customerTransactionMockMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerTransactionMock(Long id) {
        // 校验存在
        validateCustomerTransactionMockExists(id);
        // 删除
        customerTransactionMockMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerTransactionMockListByIds(List<Long> ids) {
        // 删除
        customerTransactionMockMapper.deleteByIds(ids);
        }


    private void validateCustomerTransactionMockExists(Long id) {
        if (customerTransactionMockMapper.selectById(id) == null) {
            throw exception(CUSTOMER_TRANSACTION_MOCK_NOT_EXISTS);
        }
    }

    @Override
    public CustomerTransactionMockDO getCustomerTransactionMock(Long id) {
        return customerTransactionMockMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerTransactionMockDO> getCustomerTransactionMockPage(CustomerTransactionMockPageReqVO pageReqVO) {
        return customerTransactionMockMapper.selectPage(pageReqVO);
    }

}