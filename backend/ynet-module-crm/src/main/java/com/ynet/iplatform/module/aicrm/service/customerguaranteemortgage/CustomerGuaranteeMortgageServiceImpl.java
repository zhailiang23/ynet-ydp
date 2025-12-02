package com.ynet.iplatform.module.aicrm.service.customerguaranteemortgage;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customerguaranteemortgage.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerguaranteemortgage.CustomerGuaranteeMortgageDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.customerguaranteemortgage.CustomerGuaranteeMortgageMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户抵押物信息表（零售+对公共用） Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class CustomerGuaranteeMortgageServiceImpl implements CustomerGuaranteeMortgageService {

    @Resource
    private CustomerGuaranteeMortgageMapper customerGuaranteeMortgageMapper;

    @Override
    public Long createCustomerGuaranteeMortgage(CustomerGuaranteeMortgageSaveReqVO createReqVO) {
        // 插入
        CustomerGuaranteeMortgageDO customerGuaranteeMortgage = BeanUtils.toBean(createReqVO, CustomerGuaranteeMortgageDO.class);
        customerGuaranteeMortgageMapper.insert(customerGuaranteeMortgage);

        // 返回
        return customerGuaranteeMortgage.getId();
    }

    @Override
    public void updateCustomerGuaranteeMortgage(CustomerGuaranteeMortgageSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerGuaranteeMortgageExists(updateReqVO.getId());
        // 更新
        CustomerGuaranteeMortgageDO updateObj = BeanUtils.toBean(updateReqVO, CustomerGuaranteeMortgageDO.class);
        customerGuaranteeMortgageMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerGuaranteeMortgage(Long id) {
        // 校验存在
        validateCustomerGuaranteeMortgageExists(id);
        // 删除
        customerGuaranteeMortgageMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerGuaranteeMortgageListByIds(List<Long> ids) {
        // 删除
        customerGuaranteeMortgageMapper.deleteByIds(ids);
        }


    private void validateCustomerGuaranteeMortgageExists(Long id) {
        if (customerGuaranteeMortgageMapper.selectById(id) == null) {
            throw exception(CUSTOMER_GUARANTEE_MORTGAGE_NOT_EXISTS);
        }
    }

    @Override
    public CustomerGuaranteeMortgageDO getCustomerGuaranteeMortgage(Long id) {
        return customerGuaranteeMortgageMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerGuaranteeMortgageDO> getCustomerGuaranteeMortgagePage(CustomerGuaranteeMortgagePageReqVO pageReqVO) {
        return customerGuaranteeMortgageMapper.selectPage(pageReqVO);
    }

}