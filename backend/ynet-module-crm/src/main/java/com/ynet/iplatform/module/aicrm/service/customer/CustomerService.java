package com.ynet.iplatform.module.aicrm.service.customer;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customer.vo.*;
import com.ynet.iplatform.module.aicrm.controller.app.customer.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customer.CustomerDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * CRM客户主表(零售+对公共用) Service 接口
 *
 * @author 易诚源码
 */
public interface CustomerService {

    /**
     * 创建CRM客户主表(零售+对公共用)
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomer(@Valid CustomerSaveReqVO createReqVO);

    /**
     * 更新CRM客户主表(零售+对公共用)
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomer(@Valid CustomerSaveReqVO updateReqVO);

    /**
     * 删除CRM客户主表(零售+对公共用)
     *
     * @param id 编号
     */
    void deleteCustomer(Long id);

    /**
    * 批量删除CRM客户主表(零售+对公共用)
    *
    * @param ids 编号
    */
    void deleteCustomerListByIds(List<Long> ids);

    /**
     * 获得CRM客户主表(零售+对公共用)
     *
     * @param id 编号
     * @return CRM客户主表(零售+对公共用)
     */
    CustomerDO getCustomer(Long id);

    /**
     * 获得CRM客户主表(零售+对公共用)分页
     *
     * @param pageReqVO 分页查询
     * @return CRM客户主表(零售+对公共用)分页
     */
    PageResult<CustomerDO> getCustomerPage(CustomerPageReqVO pageReqVO);

    // ==================== 移动端接口 ====================

    /**
     * 获得客户分页（移动端）
     *
     * @param pageReqVO 分页查询
     * @return 客户分页
     */
    PageResult<AppCustomerRespVO> getAppCustomerPage(AppCustomerPageReqVO pageReqVO);

    /**
     * 获得客户详情（移动端）
     *
     * @param id 编号
     * @return 客户详情
     */
    AppCustomerRespVO getAppCustomer(Long id);

    /**
     * 根据手机号或证件号查询客户（客户识别）
     *
     * @param mobile 手机号（可选）
     * @param idCardNo 证件号（可选）
     * @return 客户DO（找到）或 null（未找到）
     */
    CustomerDO searchCustomerByMobileOrIdCard(String mobile, String idCardNo);

    /**
     * 获得客户简单列表（用于下拉选择）
     *
     * @return 客户列表（仅包含 id、customerName、customerNo 等基本信息）
     */
    List<CustomerDO> getSimpleCustomerList();

}