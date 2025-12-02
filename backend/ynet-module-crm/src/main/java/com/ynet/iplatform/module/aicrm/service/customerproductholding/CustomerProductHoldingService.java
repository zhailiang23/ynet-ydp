package com.ynet.iplatform.module.aicrm.service.customerproductholding;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customerproductholding.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerproductholding.CustomerProductHoldingDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 客户产品持有情况表（客户与产品的多对多关系） Service 接口
 *
 * @author 易诚源码
 */
public interface CustomerProductHoldingService {

    /**
     * 创建客户产品持有情况表（客户与产品的多对多关系）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerProductHolding(@Valid CustomerProductHoldingSaveReqVO createReqVO);

    /**
     * 更新客户产品持有情况表（客户与产品的多对多关系）
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerProductHolding(@Valid CustomerProductHoldingSaveReqVO updateReqVO);

    /**
     * 删除客户产品持有情况表（客户与产品的多对多关系）
     *
     * @param id 编号
     */
    void deleteCustomerProductHolding(Long id);

    /**
    * 批量删除客户产品持有情况表（客户与产品的多对多关系）
    *
    * @param ids 编号
    */
    void deleteCustomerProductHoldingListByIds(List<Long> ids);

    /**
     * 获得客户产品持有情况表（客户与产品的多对多关系）
     *
     * @param id 编号
     * @return 客户产品持有情况表（客户与产品的多对多关系）
     */
    CustomerProductHoldingDO getCustomerProductHolding(Long id);

    /**
     * 获得客户产品持有情况表（客户与产品的多对多关系）分页
     *
     * @param pageReqVO 分页查询
     * @return 客户产品持有情况表（客户与产品的多对多关系）分页
     */
    PageResult<CustomerProductHoldingDO> getCustomerProductHoldingPage(CustomerProductHoldingPageReqVO pageReqVO);

}