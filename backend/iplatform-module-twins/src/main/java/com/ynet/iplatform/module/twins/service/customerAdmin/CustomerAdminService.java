package com.ynet.iplatform.module.twins.service.customerAdmin;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.twins.controller.admin.customerAdmin.vo.*;
import com.ynet.iplatform.module.twins.dal.dataobject.customerAdmin.CustomerAdminDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 客服信息 Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerAdminService {

    /**
     * 创建客服信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createCustomerAdmin(@Valid CustomerAdminSaveReqVO createReqVO);

    /**
     * 更新客服信息
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerAdmin(@Valid CustomerAdminSaveReqVO updateReqVO);

    /**
     * 删除客服信息
     *
     * @param id 编号
     */
    void deleteCustomerAdmin(Integer id);

    /**
    * 批量删除客服信息
    *
    * @param ids 编号
    */
    void deleteCustomerAdminListByIds(List<Integer> ids);

    /**
     * 获得客服信息
     *
     * @param id 编号
     * @return 客服信息
     */
    CustomerAdminDO getCustomerAdmin(Integer id);

    /**
     * 获得客服信息分页
     *
     * @param pageReqVO 分页查询
     * @return 客服信息分页
     */
    PageResult<CustomerAdminDO> getCustomerAdminPage(CustomerAdminPageReqVO pageReqVO);

    /**
     * 检查客服是否已存在
     *
     * @param customerId 客户 ID (用户 ID)
     * @return 是否存在
     */
    Boolean checkCustomerAdminExists(Integer customerId);

}