package com.ynet.iplatform.module.twins.service.customerUser;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.twins.controller.admin.customerUser.vo.*;
import com.ynet.iplatform.module.twins.dal.dataobject.customerUser.CustomerUserDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 接入用户 Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerUserService {

    /**
     * 创建接入用户
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createCustomerUser(@Valid CustomerUserSaveReqVO createReqVO);

    /**
     * 更新接入用户
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerUser(@Valid CustomerUserSaveReqVO updateReqVO);

    /**
     * 删除接入用户
     *
     * @param id 编号
     */
    void deleteCustomerUser(Integer id);

    /**
    * 批量删除接入用户
    *
    * @param ids 编号
    */
    void deleteCustomerUserListByIds(List<Integer> ids);

    /**
     * 获得接入用户
     *
     * @param id 编号
     * @return 接入用户
     */
    CustomerUserDO getCustomerUser(Integer id);

    /**
     * 获得接入用户分页
     *
     * @param pageReqVO 分页查询
     * @return 接入用户分页
     */
    PageResult<CustomerUserDO> getCustomerUserPage(CustomerUserPageReqVO pageReqVO);

}