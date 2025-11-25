package com.ynet.iplatform.module.aicrm.service.retailcustomer;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.aicrm.controller.admin.retailcustomer.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.retailcustomer.RetailCustomerDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * CRM零售客户扩展表(零售客户特有基本信息) Service 接口
 *
 * @author 芋道源码
 */
public interface RetailCustomerService {

    /**
     * 创建CRM零售客户扩展表(零售客户特有基本信息)
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRetailCustomer(@Valid RetailCustomerSaveReqVO createReqVO);

    /**
     * 更新CRM零售客户扩展表(零售客户特有基本信息)
     *
     * @param updateReqVO 更新信息
     */
    void updateRetailCustomer(@Valid RetailCustomerSaveReqVO updateReqVO);

    /**
     * 删除CRM零售客户扩展表(零售客户特有基本信息)
     *
     * @param id 编号
     */
    void deleteRetailCustomer(Long id);

    /**
    * 批量删除CRM零售客户扩展表(零售客户特有基本信息)
    *
    * @param ids 编号
    */
    void deleteRetailCustomerListByIds(List<Long> ids);

    /**
     * 获得CRM零售客户扩展表(零售客户特有基本信息)
     * 包含关联的客户共有信息
     *
     * @param id 编号
     * @return CRM零售客户扩展表(零售客户特有基本信息) + 客户共有信息
     */
    RetailCustomerRespVO getRetailCustomer(Long id);

    /**
     * 获得CRM零售客户扩展表(零售客户特有基本信息)分页
     *
     * @param pageReqVO 分页查询
     * @return CRM零售客户扩展表(零售客户特有基本信息)分页
     */
    PageResult<RetailCustomerDO> getRetailCustomerPage(RetailCustomerPageReqVO pageReqVO);

    /**
     * 获取零售客户概况信息
     *
     * @param customerId 客户ID
     * @param startDate 开始日期 (可选)
     * @param endDate 结束日期 (可选)
     * @return 客户概况信息
     */
    RetailCustomerOverviewRespVO getCustomerOverview(Long customerId, String startDate, String endDate);

}