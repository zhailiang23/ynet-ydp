package cn.iocoder.yudao.module.aicrm.service.customertransactionmock;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customertransactionmock.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customertransactionmock.CustomerTransactionMockDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.aicrm.dal.mysql.customertransactionmock.CustomerTransactionMockMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户交易明细信息表（Mock数据） Service 实现类
 *
 * @author 芋道源码
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