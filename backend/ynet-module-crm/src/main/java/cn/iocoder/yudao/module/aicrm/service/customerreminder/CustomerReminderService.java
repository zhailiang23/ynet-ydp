package cn.iocoder.yudao.module.aicrm.service.customerreminder;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerreminder.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerreminder.CustomerReminderDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

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