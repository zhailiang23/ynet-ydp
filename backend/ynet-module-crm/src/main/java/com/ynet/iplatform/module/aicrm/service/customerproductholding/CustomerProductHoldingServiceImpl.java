package com.ynet.iplatform.module.aicrm.service.customerproductholding;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customerproductholding.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerproductholding.CustomerProductHoldingDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.customerproductholding.CustomerProductHoldingMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户产品持有情况表（客户与产品的多对多关系） Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CustomerProductHoldingServiceImpl implements CustomerProductHoldingService {

    @Resource
    private CustomerProductHoldingMapper customerProductHoldingMapper;

    @Override
    public Long createCustomerProductHolding(CustomerProductHoldingSaveReqVO createReqVO) {
        // 插入
        CustomerProductHoldingDO customerProductHolding = BeanUtils.toBean(createReqVO, CustomerProductHoldingDO.class);
        customerProductHoldingMapper.insert(customerProductHolding);

        // 返回
        return customerProductHolding.getId();
    }

    @Override
    public void updateCustomerProductHolding(CustomerProductHoldingSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerProductHoldingExists(updateReqVO.getId());
        // 更新
        CustomerProductHoldingDO updateObj = BeanUtils.toBean(updateReqVO, CustomerProductHoldingDO.class);
        customerProductHoldingMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerProductHolding(Long id) {
        // 校验存在
        validateCustomerProductHoldingExists(id);
        // 删除
        customerProductHoldingMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerProductHoldingListByIds(List<Long> ids) {
        // 删除
        customerProductHoldingMapper.deleteByIds(ids);
        }


    private void validateCustomerProductHoldingExists(Long id) {
        if (customerProductHoldingMapper.selectById(id) == null) {
            throw exception(CUSTOMER_PRODUCT_HOLDING_NOT_EXISTS);
        }
    }

    @Override
    public CustomerProductHoldingDO getCustomerProductHolding(Long id) {
        return customerProductHoldingMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerProductHoldingDO> getCustomerProductHoldingPage(CustomerProductHoldingPageReqVO pageReqVO) {
        return customerProductHoldingMapper.selectPage(pageReqVO);
    }

}