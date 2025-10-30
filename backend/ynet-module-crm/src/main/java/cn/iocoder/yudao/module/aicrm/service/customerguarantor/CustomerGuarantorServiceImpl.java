package cn.iocoder.yudao.module.aicrm.service.customerguarantor;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerguarantor.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerguarantor.CustomerGuarantorDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.aicrm.dal.mysql.customerguarantor.CustomerGuarantorMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户担保人信息表（零售+对公共用） Service 实现类
 *
 * @author 芋道源码
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