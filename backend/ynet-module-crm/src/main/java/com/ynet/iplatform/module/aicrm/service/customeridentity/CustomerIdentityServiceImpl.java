package com.ynet.iplatform.module.aicrm.service.customeridentity;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customeridentity.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customeridentity.CustomerIdentityDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;

import com.ynet.iplatform.module.aicrm.dal.mysql.customeridentity.CustomerIdentityMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户证件信息表（零售客户特有，1对多关系，支持多证件） Service 实现类
 *
 * @author 易诚源码
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
    @Transactional(rollbackFor = Exception.class)
    public void updateCustomerIdentity(CustomerIdentitySaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerIdentityExists(updateReqVO.getId());

        // 如果设置为默认证件,需要先取消同一客户的其他默认证件
        if (Boolean.TRUE.equals(updateReqVO.getIsPrimary())) {
            CustomerIdentityDO currentIdentity = customerIdentityMapper.selectById(updateReqVO.getId());
            if (currentIdentity != null) {
                // 查询该客户的所有默认证件
                List<CustomerIdentityDO> primaryIdentities = customerIdentityMapper.selectList(
                    new LambdaQueryWrapperX<CustomerIdentityDO>()
                        .eq(CustomerIdentityDO::getCustomerId, currentIdentity.getCustomerId())
                        .eq(CustomerIdentityDO::getIsPrimary, true)
                        .ne(CustomerIdentityDO::getId, updateReqVO.getId())
                );

                // 将其他默认证件设为非默认
                if (CollUtil.isNotEmpty(primaryIdentities)) {
                    primaryIdentities.forEach(identity -> {
                        identity.setIsPrimary(false);
                        customerIdentityMapper.updateById(identity);
                    });
                }
            }
        }

        // 更新当前证件
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