package com.ynet.iplatform.module.aicrm.service.customercreditdetail;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customercreditdetail.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customercreditdetail.CustomerCreditDetailDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 客户授信使用明细表（零售+对公共用） Service 接口
 *
 * @author 易诚源码
 */
public interface CustomerCreditDetailService {

    /**
     * 创建客户授信使用明细表（零售+对公共用）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerCreditDetail(@Valid CustomerCreditDetailSaveReqVO createReqVO);

    /**
     * 更新客户授信使用明细表（零售+对公共用）
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerCreditDetail(@Valid CustomerCreditDetailSaveReqVO updateReqVO);

    /**
     * 删除客户授信使用明细表（零售+对公共用）
     *
     * @param id 编号
     */
    void deleteCustomerCreditDetail(Long id);

    /**
    * 批量删除客户授信使用明细表（零售+对公共用）
    *
    * @param ids 编号
    */
    void deleteCustomerCreditDetailListByIds(List<Long> ids);

    /**
     * 获得客户授信使用明细表（零售+对公共用）
     *
     * @param id 编号
     * @return 客户授信使用明细表（零售+对公共用）
     */
    CustomerCreditDetailDO getCustomerCreditDetail(Long id);

    /**
     * 获得客户授信使用明细表（零售+对公共用）分页
     *
     * @param pageReqVO 分页查询
     * @return 客户授信使用明细表（零售+对公共用）分页
     */
    PageResult<CustomerCreditDetailDO> getCustomerCreditDetailPage(CustomerCreditDetailPageReqVO pageReqVO);

}