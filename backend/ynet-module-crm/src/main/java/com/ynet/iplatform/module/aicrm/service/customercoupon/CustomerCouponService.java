package com.ynet.iplatform.module.aicrm.service.customercoupon;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customercoupon.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customercoupon.CustomerCouponDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 客户卡券信息表（零售客户特有） Service 接口
 *
 * @author 易诚源码
 */
public interface CustomerCouponService {

    /**
     * 创建客户卡券信息表（零售客户特有）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerCoupon(@Valid CustomerCouponSaveReqVO createReqVO);

    /**
     * 更新客户卡券信息表（零售客户特有）
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerCoupon(@Valid CustomerCouponSaveReqVO updateReqVO);

    /**
     * 删除客户卡券信息表（零售客户特有）
     *
     * @param id 编号
     */
    void deleteCustomerCoupon(Long id);

    /**
    * 批量删除客户卡券信息表（零售客户特有）
    *
    * @param ids 编号
    */
    void deleteCustomerCouponListByIds(List<Long> ids);

    /**
     * 获得客户卡券信息表（零售客户特有）
     *
     * @param id 编号
     * @return 客户卡券信息表（零售客户特有）
     */
    CustomerCouponDO getCustomerCoupon(Long id);

    /**
     * 获得客户卡券信息表（零售客户特有）分页
     *
     * @param pageReqVO 分页查询
     * @return 客户卡券信息表（零售客户特有）分页
     */
    PageResult<CustomerCouponDO> getCustomerCouponPage(CustomerCouponPageReqVO pageReqVO);

}