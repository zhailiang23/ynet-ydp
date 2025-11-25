package com.ynet.iplatform.module.aicrm.service.customerguaranteepledge;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customerguaranteepledge.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerguaranteepledge.CustomerGuaranteePledgeDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.customerguaranteepledge.CustomerGuaranteePledgeMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户质押物信息表（零售+对公共用） Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CustomerGuaranteePledgeServiceImpl implements CustomerGuaranteePledgeService {

    @Resource
    private CustomerGuaranteePledgeMapper customerGuaranteePledgeMapper;

    @Override
    public Long createCustomerGuaranteePledge(CustomerGuaranteePledgeSaveReqVO createReqVO) {
        // 插入
        CustomerGuaranteePledgeDO customerGuaranteePledge = BeanUtils.toBean(createReqVO, CustomerGuaranteePledgeDO.class);
        customerGuaranteePledgeMapper.insert(customerGuaranteePledge);

        // 返回
        return customerGuaranteePledge.getId();
    }

    @Override
    public void updateCustomerGuaranteePledge(CustomerGuaranteePledgeSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerGuaranteePledgeExists(updateReqVO.getId());
        // 更新
        CustomerGuaranteePledgeDO updateObj = BeanUtils.toBean(updateReqVO, CustomerGuaranteePledgeDO.class);
        customerGuaranteePledgeMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerGuaranteePledge(Long id) {
        // 校验存在
        validateCustomerGuaranteePledgeExists(id);
        // 删除
        customerGuaranteePledgeMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerGuaranteePledgeListByIds(List<Long> ids) {
        // 删除
        customerGuaranteePledgeMapper.deleteByIds(ids);
        }


    private void validateCustomerGuaranteePledgeExists(Long id) {
        if (customerGuaranteePledgeMapper.selectById(id) == null) {
            throw exception(CUSTOMER_GUARANTEE_PLEDGE_NOT_EXISTS);
        }
    }

    @Override
    public CustomerGuaranteePledgeDO getCustomerGuaranteePledge(Long id) {
        return customerGuaranteePledgeMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerGuaranteePledgeDO> getCustomerGuaranteePledgePage(CustomerGuaranteePledgePageReqVO pageReqVO) {
        return customerGuaranteePledgeMapper.selectPage(pageReqVO);
    }

}