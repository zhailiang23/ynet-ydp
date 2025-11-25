package com.ynet.iplatform.module.aicrm.service.customerreminder;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customerreminder.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerreminder.CustomerReminderDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 客户提醒信息 Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerReminderService {

    /**
     * 创建客户提醒信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerReminder(@Valid CustomerReminderSaveReqVO createReqVO);

    /**
     * 更新客户提醒信息
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerReminder(@Valid CustomerReminderSaveReqVO updateReqVO);

    /**
     * 删除客户提醒信息
     *
     * @param id 编号
     */
    void deleteCustomerReminder(Long id);

    /**
    * 批量删除客户提醒信息
    *
    * @param ids 编号
    */
    void deleteCustomerReminderListByIds(List<Long> ids);

    /**
     * 获得客户提醒信息
     *
     * @param id 编号
     * @return 客户提醒信息
     */
    CustomerReminderDO getCustomerReminder(Long id);

    /**
     * 获得客户提醒信息分页
     *
     * @param pageReqVO 分页查询
     * @return 客户提醒信息分页
     */
    PageResult<CustomerReminderDO> getCustomerReminderPage(CustomerReminderPageReqVO pageReqVO);

}