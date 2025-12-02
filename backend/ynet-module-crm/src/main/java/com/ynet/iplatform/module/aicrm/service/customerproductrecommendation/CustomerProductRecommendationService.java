package com.ynet.iplatform.module.aicrm.service.customerproductrecommendation;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customerproductrecommendation.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerproductrecommendation.CustomerProductRecommendationDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 客户产品推荐 Service 接口
 *
 * @author 易诚源码
 */
public interface CustomerProductRecommendationService {

    /**
     * 创建客户产品推荐
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerProductRecommendation(@Valid CustomerProductRecommendationSaveReqVO createReqVO);

    /**
     * 更新客户产品推荐
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerProductRecommendation(@Valid CustomerProductRecommendationSaveReqVO updateReqVO);

    /**
     * 删除客户产品推荐
     *
     * @param id 编号
     */
    void deleteCustomerProductRecommendation(Long id);

    /**
    * 批量删除客户产品推荐
    *
    * @param ids 编号
    */
    void deleteCustomerProductRecommendationListByIds(List<Long> ids);

    /**
     * 获得客户产品推荐
     *
     * @param id 编号
     * @return 客户产品推荐
     */
    CustomerProductRecommendationDO getCustomerProductRecommendation(Long id);

    /**
     * 获得客户产品推荐分页
     *
     * @param pageReqVO 分页查询
     * @return 客户产品推荐分页
     */
    PageResult<CustomerProductRecommendationDO> getCustomerProductRecommendationPage(CustomerProductRecommendationPageReqVO pageReqVO);

}