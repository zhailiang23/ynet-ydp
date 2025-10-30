package cn.iocoder.yudao.module.aicrm.service.customeraccountfund;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customeraccountfund.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customeraccountfund.CustomerAccountFundDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.aicrm.dal.mysql.customeraccountfund.CustomerAccountFundMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户基金账户信息表（零售+对公共用） Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CustomerAccountFundServiceImpl implements CustomerAccountFundService {

    @Resource
    private CustomerAccountFundMapper customerAccountFundMapper;

    @Override
    public Long createCustomerAccountFund(CustomerAccountFundSaveReqVO createReqVO) {
        // 插入
        CustomerAccountFundDO customerAccountFund = BeanUtils.toBean(createReqVO, CustomerAccountFundDO.class);
        customerAccountFundMapper.insert(customerAccountFund);

        // 返回
        return customerAccountFund.getId();
    }

    @Override
    public void updateCustomerAccountFund(CustomerAccountFundSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerAccountFundExists(updateReqVO.getId());
        // 更新
        CustomerAccountFundDO updateObj = BeanUtils.toBean(updateReqVO, CustomerAccountFundDO.class);
        customerAccountFundMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerAccountFund(Long id) {
        // 校验存在
        validateCustomerAccountFundExists(id);
        // 删除
        customerAccountFundMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerAccountFundListByIds(List<Long> ids) {
        // 删除
        customerAccountFundMapper.deleteByIds(ids);
        }


    private void validateCustomerAccountFundExists(Long id) {
        if (customerAccountFundMapper.selectById(id) == null) {
            throw exception(CUSTOMER_ACCOUNT_FUND_NOT_EXISTS);
        }
    }

    @Override
    public CustomerAccountFundDO getCustomerAccountFund(Long id) {
        return customerAccountFundMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerAccountFundDO> getCustomerAccountFundPage(CustomerAccountFundPageReqVO pageReqVO) {
        return customerAccountFundMapper.selectPage(pageReqVO);
    }

}