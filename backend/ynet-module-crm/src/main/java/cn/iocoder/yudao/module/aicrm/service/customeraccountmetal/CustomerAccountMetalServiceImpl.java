package cn.iocoder.yudao.module.aicrm.service.customeraccountmetal;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customeraccountmetal.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customeraccountmetal.CustomerAccountMetalDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.aicrm.dal.mysql.customeraccountmetal.CustomerAccountMetalMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户贵金属账户信息表（零售+对公共用） Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CustomerAccountMetalServiceImpl implements CustomerAccountMetalService {

    @Resource
    private CustomerAccountMetalMapper customerAccountMetalMapper;

    @Override
    public Long createCustomerAccountMetal(CustomerAccountMetalSaveReqVO createReqVO) {
        // 插入
        CustomerAccountMetalDO customerAccountMetal = BeanUtils.toBean(createReqVO, CustomerAccountMetalDO.class);
        customerAccountMetalMapper.insert(customerAccountMetal);

        // 返回
        return customerAccountMetal.getId();
    }

    @Override
    public void updateCustomerAccountMetal(CustomerAccountMetalSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerAccountMetalExists(updateReqVO.getId());
        // 更新
        CustomerAccountMetalDO updateObj = BeanUtils.toBean(updateReqVO, CustomerAccountMetalDO.class);
        customerAccountMetalMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerAccountMetal(Long id) {
        // 校验存在
        validateCustomerAccountMetalExists(id);
        // 删除
        customerAccountMetalMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerAccountMetalListByIds(List<Long> ids) {
        // 删除
        customerAccountMetalMapper.deleteByIds(ids);
        }


    private void validateCustomerAccountMetalExists(Long id) {
        if (customerAccountMetalMapper.selectById(id) == null) {
            throw exception(CUSTOMER_ACCOUNT_METAL_NOT_EXISTS);
        }
    }

    @Override
    public CustomerAccountMetalDO getCustomerAccountMetal(Long id) {
        return customerAccountMetalMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerAccountMetalDO> getCustomerAccountMetalPage(CustomerAccountMetalPageReqVO pageReqVO) {
        return customerAccountMetalMapper.selectPage(pageReqVO);
    }

}