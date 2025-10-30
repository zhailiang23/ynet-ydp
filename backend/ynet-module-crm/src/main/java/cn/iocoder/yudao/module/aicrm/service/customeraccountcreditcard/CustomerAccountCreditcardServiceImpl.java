package cn.iocoder.yudao.module.aicrm.service.customeraccountcreditcard;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customeraccountcreditcard.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customeraccountcreditcard.CustomerAccountCreditcardDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.aicrm.dal.mysql.customeraccountcreditcard.CustomerAccountCreditcardMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户信用卡账户信息表（仅限零售客户） Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CustomerAccountCreditcardServiceImpl implements CustomerAccountCreditcardService {

    @Resource
    private CustomerAccountCreditcardMapper customerAccountCreditcardMapper;

    @Override
    public Long createCustomerAccountCreditcard(CustomerAccountCreditcardSaveReqVO createReqVO) {
        // 插入
        CustomerAccountCreditcardDO customerAccountCreditcard = BeanUtils.toBean(createReqVO, CustomerAccountCreditcardDO.class);
        customerAccountCreditcardMapper.insert(customerAccountCreditcard);

        // 返回
        return customerAccountCreditcard.getId();
    }

    @Override
    public void updateCustomerAccountCreditcard(CustomerAccountCreditcardSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerAccountCreditcardExists(updateReqVO.getId());
        // 更新
        CustomerAccountCreditcardDO updateObj = BeanUtils.toBean(updateReqVO, CustomerAccountCreditcardDO.class);
        customerAccountCreditcardMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerAccountCreditcard(Long id) {
        // 校验存在
        validateCustomerAccountCreditcardExists(id);
        // 删除
        customerAccountCreditcardMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerAccountCreditcardListByIds(List<Long> ids) {
        // 删除
        customerAccountCreditcardMapper.deleteByIds(ids);
        }


    private void validateCustomerAccountCreditcardExists(Long id) {
        if (customerAccountCreditcardMapper.selectById(id) == null) {
            throw exception(CUSTOMER_ACCOUNT_CREDITCARD_NOT_EXISTS);
        }
    }

    @Override
    public CustomerAccountCreditcardDO getCustomerAccountCreditcard(Long id) {
        return customerAccountCreditcardMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerAccountCreditcardDO> getCustomerAccountCreditcardPage(CustomerAccountCreditcardPageReqVO pageReqVO) {
        return customerAccountCreditcardMapper.selectPage(pageReqVO);
    }

}