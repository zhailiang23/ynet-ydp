package com.ynet.iplatform.module.twins.service.customerUser;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.twins.controller.admin.customerUser.vo.*;
import com.ynet.iplatform.module.twins.dal.dataobject.customerUser.CustomerUserDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.twins.dal.mysql.customerUser.CustomerUserMapper;
import com.ynet.iplatform.framework.tenant.core.context.TenantContextHolder;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.twins.enums.ErrorCodeConstants.*;

/**
 * 接入用户 Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class CustomerUserServiceImpl implements CustomerUserService {

    @Resource
    private CustomerUserMapper customerUserMapper;

    @Override
    public Integer createCustomerUser(CustomerUserSaveReqVO createReqVO) {
        // 插入
        CustomerUserDO customerUser = BeanUtils.toBean(createReqVO, CustomerUserDO.class);
        // 将新接入用户的 customer_id 设置为当前登录用户的租户 ID
        Long tenantId = TenantContextHolder.getTenantId();
        if (tenantId != null) {
            customerUser.setCustomerId(tenantId.intValue());
        }
        customerUserMapper.insert(customerUser);

        // 返回
        return customerUser.getId();
    }

    @Override
    public void updateCustomerUser(CustomerUserSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerUserExists(updateReqVO.getId());
        // 更新
        CustomerUserDO updateObj = BeanUtils.toBean(updateReqVO, CustomerUserDO.class);
        customerUserMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerUser(Integer id) {
        // 校验存在
        validateCustomerUserExists(id);
        // 删除
        customerUserMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerUserListByIds(List<Integer> ids) {
        // 删除
        customerUserMapper.deleteByIds(ids);
        }


    private void validateCustomerUserExists(Integer id) {
        if (customerUserMapper.selectById(id) == null) {
            throw exception(CUSTOMER_USER_NOT_EXISTS);
        }
    }

    @Override
    public CustomerUserDO getCustomerUser(Integer id) {
        return customerUserMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerUserDO> getCustomerUserPage(CustomerUserPageReqVO pageReqVO) {
        return customerUserMapper.selectPage(pageReqVO);
    }

}