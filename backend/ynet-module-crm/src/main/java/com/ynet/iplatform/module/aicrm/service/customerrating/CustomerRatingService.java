package com.ynet.iplatform.module.aicrm.service.customerrating;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customerrating.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerrating.CustomerRatingDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 客户评级信息表（与客户主表1对1关系） Service 接口
 *
 * @author 易诚源码
 */
public interface CustomerRatingService {

    /**
     * 创建客户评级信息表（与客户主表1对1关系）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerRating(@Valid CustomerRatingSaveReqVO createReqVO);

    /**
     * 更新客户评级信息表（与客户主表1对1关系）
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerRating(@Valid CustomerRatingSaveReqVO updateReqVO);

    /**
     * 删除客户评级信息表（与客户主表1对1关系）
     *
     * @param id 编号
     */
    void deleteCustomerRating(Long id);

    /**
    * 批量删除客户评级信息表（与客户主表1对1关系）
    *
    * @param ids 编号
    */
    void deleteCustomerRatingListByIds(List<Long> ids);

    /**
     * 获得客户评级信息表（与客户主表1对1关系）
     *
     * @param id 编号
     * @return 客户评级信息表（与客户主表1对1关系）
     */
    CustomerRatingDO getCustomerRating(Long id);

    /**
     * 获得客户评级信息表（与客户主表1对1关系）分页
     *
     * @param pageReqVO 分页查询
     * @return 客户评级信息表（与客户主表1对1关系）分页
     */
    PageResult<CustomerRatingDO> getCustomerRatingPage(CustomerRatingPageReqVO pageReqVO);

}