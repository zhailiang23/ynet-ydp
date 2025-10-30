package cn.iocoder.yudao.module.aicrm.service.customerguaranteemortgage;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerguaranteemortgage.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerguaranteemortgage.CustomerGuaranteeMortgageDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.aicrm.dal.mysql.customerguaranteemortgage.CustomerGuaranteeMortgageMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户抵押物信息表（零售+对公共用） Service 实现类
 *
 * @author 芋道源码
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