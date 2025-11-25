package com.ynet.iplatform.module.aicrm.service.customerimportantevent;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customerimportantevent.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerimportantevent.CustomerImportantEventDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 客户重要事件表（零售+对公共用） Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerImportantEventService {

    /**
     * 创建客户重要事件表（零售+对公共用）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerImportantEvent(@Valid CustomerImportantEventSaveReqVO createReqVO);

    /**
     * 更新客户重要事件表（零售+对公共用）
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerImportantEvent(@Valid CustomerImportantEventSaveReqVO updateReqVO);

    /**
     * 删除客户重要事件表（零售+对公共用）
     *
     * @param id 编号
     */
    void deleteCustomerImportantEvent(Long id);

    /**
    * 批量删除客户重要事件表（零售+对公共用）
    *
    * @param ids 编号
    */
    void deleteCustomerImportantEventListByIds(List<Long> ids);

    /**
     * 获得客户重要事件表（零售+对公共用）
     *
     * @param id 编号
     * @return 客户重要事件表（零售+对公共用）
     */
    CustomerImportantEventDO getCustomerImportantEvent(Long id);

    /**
     * 获得客户重要事件表（零售+对公共用）分页
     *
     * @param pageReqVO 分页查询
     * @return 客户重要事件表（零售+对公共用）分页
     */
    PageResult<CustomerImportantEventDO> getCustomerImportantEventPage(CustomerImportantEventPageReqVO pageReqVO);

}