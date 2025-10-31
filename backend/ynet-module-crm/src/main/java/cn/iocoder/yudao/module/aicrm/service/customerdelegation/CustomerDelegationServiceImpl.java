package cn.iocoder.yudao.module.aicrm.service.customerdelegation;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerdelegation.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerdelegation.CustomerDelegationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.aicrm.dal.mysql.customerdelegation.CustomerDelegationMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户托管记录 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CustomerDelegationServiceImpl implements CustomerDelegationService {

    @Resource
    private CustomerDelegationMapper customerDelegationMapper;

    @Override
    public Long createCustomerDelegation(CustomerDelegationSaveReqVO createReqVO) {
        // 插入
        CustomerDelegationDO customerDelegation = BeanUtils.toBean(createReqVO, CustomerDelegationDO.class);
        customerDelegationMapper.insert(customerDelegation);

        // 返回
        return customerDelegation.getId();
    }

    @Override
    public void updateCustomerDelegation(CustomerDelegationSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerDelegationExists(updateReqVO.getId());
        // 更新
        CustomerDelegationDO updateObj = BeanUtils.toBean(updateReqVO, CustomerDelegationDO.class);
        customerDelegationMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerDelegation(Long id) {
        // 校验存在
        validateCustomerDelegationExists(id);
        // 删除
        customerDelegationMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerDelegationListByIds(List<Long> ids) {
        // 删除
        customerDelegationMapper.deleteByIds(ids);
        }


    private void validateCustomerDelegationExists(Long id) {
        if (customerDelegationMapper.selectById(id) == null) {
            throw exception(CUSTOMER_DELEGATION_NOT_EXISTS);
        }
    }

    @Override
    public CustomerDelegationDO getCustomerDelegation(Long id) {
        return customerDelegationMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerDelegationDO> getCustomerDelegationPage(CustomerDelegationPageReqVO pageReqVO) {
        return customerDelegationMapper.selectPage(pageReqVO);
    }

}