package cn.iocoder.yudao.module.aicrm.service.customeridentity;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customeridentity.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customeridentity.CustomerIdentityDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.aicrm.dal.mysql.customeridentity.CustomerIdentityMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户证件信息表（零售客户特有，1对多关系，支持多证件） Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CustomerIdentityServiceImpl implements CustomerIdentityService {

    @Resource
    private CustomerIdentityMapper customerIdentityMapper;

    @Override
    public Long createCustomerIdentity(CustomerIdentitySaveReqVO createReqVO) {
        // 插入
        CustomerIdentityDO customerIdentity = BeanUtils.toBean(createReqVO, CustomerIdentityDO.class);
        customerIdentityMapper.insert(customerIdentity);

        // 返回
        return customerIdentity.getId();
    }

    @Override
    public void updateCustomerIdentity(CustomerIdentitySaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerIdentityExists(updateReqVO.getId());
        // 更新
        CustomerIdentityDO updateObj = BeanUtils.toBean(updateReqVO, CustomerIdentityDO.class);
        customerIdentityMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerIdentity(Long id) {
        // 校验存在
        validateCustomerIdentityExists(id);
        // 删除
        customerIdentityMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerIdentityListByIds(List<Long> ids) {
        // 删除
        customerIdentityMapper.deleteByIds(ids);
        }


    private void validateCustomerIdentityExists(Long id) {
        if (customerIdentityMapper.selectById(id) == null) {
            throw exception(CUSTOMER_IDENTITY_NOT_EXISTS);
        }
    }

    @Override
    public CustomerIdentityDO getCustomerIdentity(Long id) {
        return customerIdentityMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerIdentityDO> getCustomerIdentityPage(CustomerIdentityPageReqVO pageReqVO) {
        return customerIdentityMapper.selectPage(pageReqVO);
    }

}