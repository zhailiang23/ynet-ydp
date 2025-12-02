package com.ynet.iplatform.module.twins.service.customerAdmin;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.twins.controller.admin.customerAdmin.vo.*;
import com.ynet.iplatform.module.twins.dal.dataobject.customerAdmin.CustomerAdminDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.twins.dal.mysql.customerAdmin.CustomerAdminMapper;
import com.ynet.iplatform.framework.tenant.core.context.TenantContextHolder;
import com.ynet.iplatform.module.system.api.user.AdminUserApi;
import com.ynet.iplatform.module.system.api.user.dto.AdminUserRespDTO;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.twins.enums.ErrorCodeConstants.*;

/**
 * 客服信息 Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class CustomerAdminServiceImpl implements CustomerAdminService {

    @Resource
    private CustomerAdminMapper customerAdminMapper;

    @Resource
    private AdminUserApi adminUserApi;

    @Override
    public Integer createCustomerAdmin(CustomerAdminSaveReqVO createReqVO) {
        // 插入
        CustomerAdminDO customerAdmin = BeanUtils.toBean(createReqVO, CustomerAdminDO.class);
        // 将新客服的 customer_id 设置为当前登录用户的租户 ID
        Long tenantId = TenantContextHolder.getTenantId();
        if (tenantId != null) {
            customerAdmin.setCustomerId(tenantId.intValue());
        }
        customerAdminMapper.insert(customerAdmin);

        // 返回
        return customerAdmin.getId();
    }

    @Override
    public void updateCustomerAdmin(CustomerAdminSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerAdminExists(updateReqVO.getId());
        // 更新
        CustomerAdminDO updateObj = BeanUtils.toBean(updateReqVO, CustomerAdminDO.class);
        customerAdminMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerAdmin(Integer id) {
        // 校验存在
        validateCustomerAdminExists(id);
        // 删除
        customerAdminMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerAdminListByIds(List<Integer> ids) {
        // 删除
        customerAdminMapper.deleteByIds(ids);
        }


    private void validateCustomerAdminExists(Integer id) {
        if (customerAdminMapper.selectById(id) == null) {
            throw exception(CUSTOMER_ADMIN_NOT_EXISTS);
        }
    }

    @Override
    public CustomerAdminDO getCustomerAdmin(Integer id) {
        return customerAdminMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerAdminDO> getCustomerAdminPage(CustomerAdminPageReqVO pageReqVO) {
        return customerAdminMapper.selectPage(pageReqVO);
    }

    @Override
    public Boolean checkCustomerAdminExists(Integer customerId) {
        // customerId 参数实际是 system_user 表的 ID
        // 1. 查询 system_user 的 nickname
        AdminUserRespDTO user = adminUserApi.getUser(Long.valueOf(customerId));
        if (user == null) {
            return false;
        }

        // 2. 检查 customer_admins 表中是否已存在相同 username 的记录
        return customerAdminMapper.selectOne(CustomerAdminDO::getUsername, user.getNickname()) != null;
    }

}