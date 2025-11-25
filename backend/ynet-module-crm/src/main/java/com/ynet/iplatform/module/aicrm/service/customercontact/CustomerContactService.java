package com.ynet.iplatform.module.aicrm.service.customercontact;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customercontact.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customercontact.CustomerContactDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 客户接触信息 Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerContactService {

    /**
     * 创建客户接触信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerContact(@Valid CustomerContactSaveReqVO createReqVO);

    /**
     * 更新客户接触信息
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerContact(@Valid CustomerContactSaveReqVO updateReqVO);

    /**
     * 删除客户接触信息
     *
     * @param id 编号
     */
    void deleteCustomerContact(Long id);

    /**
    * 批量删除客户接触信息
    *
    * @param ids 编号
    */
    void deleteCustomerContactListByIds(List<Long> ids);

    /**
     * 获得客户接触信息
     *
     * @param id 编号
     * @return 客户接触信息
     */
    CustomerContactDO getCustomerContact(Long id);

    /**
     * 获得客户接触信息分页
     *
     * @param pageReqVO 分页查询
     * @return 客户接触信息分页
     */
    PageResult<CustomerContactDO> getCustomerContactPage(CustomerContactPageReqVO pageReqVO);

}