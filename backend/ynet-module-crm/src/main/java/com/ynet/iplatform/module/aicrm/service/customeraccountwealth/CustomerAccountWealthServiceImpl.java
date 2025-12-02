package com.ynet.iplatform.module.aicrm.service.customeraccountwealth;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customeraccountwealth.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customeraccountwealth.CustomerAccountWealthDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.customeraccountwealth.CustomerAccountWealthMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户理财账户信息表（零售+对公共用） Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class CustomerAccountWealthServiceImpl implements CustomerAccountWealthService {

    @Resource
    private CustomerAccountWealthMapper customerAccountWealthMapper;

    @Override
    public Long createCustomerAccountWealth(CustomerAccountWealthSaveReqVO createReqVO) {
        // 插入
        CustomerAccountWealthDO customerAccountWealth = BeanUtils.toBean(createReqVO, CustomerAccountWealthDO.class);
        customerAccountWealthMapper.insert(customerAccountWealth);

        // 返回
        return customerAccountWealth.getId();
    }

    @Override
    public void updateCustomerAccountWealth(CustomerAccountWealthSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerAccountWealthExists(updateReqVO.getId());
        // 更新
        CustomerAccountWealthDO updateObj = BeanUtils.toBean(updateReqVO, CustomerAccountWealthDO.class);
        customerAccountWealthMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerAccountWealth(Long id) {
        // 校验存在
        validateCustomerAccountWealthExists(id);
        // 删除
        customerAccountWealthMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerAccountWealthListByIds(List<Long> ids) {
        // 删除
        customerAccountWealthMapper.deleteByIds(ids);
        }


    private void validateCustomerAccountWealthExists(Long id) {
        if (customerAccountWealthMapper.selectById(id) == null) {
            throw exception(CUSTOMER_ACCOUNT_WEALTH_NOT_EXISTS);
        }
    }

    @Override
    public CustomerAccountWealthDO getCustomerAccountWealth(Long id) {
        return customerAccountWealthMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerAccountWealthDO> getCustomerAccountWealthPage(CustomerAccountWealthPageReqVO pageReqVO) {
        return customerAccountWealthMapper.selectPage(pageReqVO);
    }

}