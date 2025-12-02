package com.ynet.iplatform.module.aicrm.service.customerguarantor;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customerguarantor.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerguarantor.CustomerGuarantorDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.customerguarantor.CustomerGuarantorMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户担保人信息表（零售+对公共用） Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class CustomerGuarantorServiceImpl implements CustomerGuarantorService {

    @Resource
    private CustomerGuarantorMapper customerGuarantorMapper;

    @Override
    public Long createCustomerGuarantor(CustomerGuarantorSaveReqVO createReqVO) {
        // 插入
        CustomerGuarantorDO customerGuarantor = BeanUtils.toBean(createReqVO, CustomerGuarantorDO.class);
        customerGuarantorMapper.insert(customerGuarantor);

        // 返回
        return customerGuarantor.getId();
    }

    @Override
    public void updateCustomerGuarantor(CustomerGuarantorSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerGuarantorExists(updateReqVO.getId());
        // 更新
        CustomerGuarantorDO updateObj = BeanUtils.toBean(updateReqVO, CustomerGuarantorDO.class);
        customerGuarantorMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerGuarantor(Long id) {
        // 校验存在
        validateCustomerGuarantorExists(id);
        // 删除
        customerGuarantorMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerGuarantorListByIds(List<Long> ids) {
        // 删除
        customerGuarantorMapper.deleteByIds(ids);
        }


    private void validateCustomerGuarantorExists(Long id) {
        if (customerGuarantorMapper.selectById(id) == null) {
            throw exception(CUSTOMER_GUARANTOR_NOT_EXISTS);
        }
    }

    @Override
    public CustomerGuarantorDO getCustomerGuarantor(Long id) {
        return customerGuarantorMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerGuarantorDO> getCustomerGuarantorPage(CustomerGuarantorPageReqVO pageReqVO) {
        return customerGuarantorMapper.selectPage(pageReqVO);
    }

}