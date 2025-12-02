package com.ynet.iplatform.module.aicrm.service.customercontract;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customercontract.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customercontract.CustomerContractDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.customercontract.CustomerContractMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户签约信息表（零售+对公共用） Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class CustomerContractServiceImpl implements CustomerContractService {

    @Resource
    private CustomerContractMapper customerContractMapper;

    @Override
    public Long createCustomerContract(CustomerContractSaveReqVO createReqVO) {
        // 插入
        CustomerContractDO customerContract = BeanUtils.toBean(createReqVO, CustomerContractDO.class);
        customerContractMapper.insert(customerContract);

        // 返回
        return customerContract.getId();
    }

    @Override
    public void updateCustomerContract(CustomerContractSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerContractExists(updateReqVO.getId());
        // 更新
        CustomerContractDO updateObj = BeanUtils.toBean(updateReqVO, CustomerContractDO.class);
        customerContractMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerContract(Long id) {
        // 校验存在
        validateCustomerContractExists(id);
        // 删除
        customerContractMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerContractListByIds(List<Long> ids) {
        // 删除
        customerContractMapper.deleteByIds(ids);
        }


    private void validateCustomerContractExists(Long id) {
        if (customerContractMapper.selectById(id) == null) {
            throw exception(CUSTOMER_CONTRACT_NOT_EXISTS);
        }
    }

    @Override
    public CustomerContractDO getCustomerContract(Long id) {
        return customerContractMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerContractDO> getCustomerContractPage(CustomerContractPageReqVO pageReqVO) {
        return customerContractMapper.selectPage(pageReqVO);
    }

}