package com.ynet.iplatform.module.aicrm.service.companycustomer;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.aicrm.controller.admin.companycustomer.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.companycustomer.CompanyCustomerDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * CRM对公客户扩展表(对公客户特有基本信息) Service 接口
 *
 * @author 易诚源码
 */
public interface CompanyCustomerService {

    /**
     * 创建CRM对公客户扩展表(对公客户特有基本信息)
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCompanyCustomer(@Valid CompanyCustomerSaveReqVO createReqVO);

    /**
     * 更新CRM对公客户扩展表(对公客户特有基本信息)
     *
     * @param updateReqVO 更新信息
     */
    void updateCompanyCustomer(@Valid CompanyCustomerSaveReqVO updateReqVO);

    /**
     * 删除CRM对公客户扩展表(对公客户特有基本信息)
     *
     * @param id 编号
     */
    void deleteCompanyCustomer(Long id);

    /**
    * 批量删除CRM对公客户扩展表(对公客户特有基本信息)
    *
    * @param ids 编号
    */
    void deleteCompanyCustomerListByIds(List<Long> ids);

    /**
     * 获得CRM对公客户扩展表(对公客户特有基本信息)
     * 包含关联的客户共有信息
     *
     * @param id 编号
     * @return CRM对公客户扩展表(对公客户特有基本信息) + 客户共有信息
     */
    CompanyCustomerRespVO getCompanyCustomer(Long id);

    /**
     * 获得CRM对公客户扩展表(对公客户特有基本信息)分页
     *
     * @param pageReqVO 分页查询
     * @return CRM对公客户扩展表(对公客户特有基本信息)分页
     */
    PageResult<CompanyCustomerDO> getCompanyCustomerPage(CompanyCustomerPageReqVO pageReqVO);

}