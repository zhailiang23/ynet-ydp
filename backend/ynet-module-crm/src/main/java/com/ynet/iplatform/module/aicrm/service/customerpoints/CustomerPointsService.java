package com.ynet.iplatform.module.aicrm.service.customerpoints;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customerpoints.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerpoints.CustomerPointsDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 客户积分信息 Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerPointsService {

    /**
     * 创建客户积分信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerPoints(@Valid CustomerPointsSaveReqVO createReqVO);

    /**
     * 更新客户积分信息
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerPoints(@Valid CustomerPointsSaveReqVO updateReqVO);

    /**
     * 删除客户积分信息
     *
     * @param id 编号
     */
    void deleteCustomerPoints(Long id);

    /**
    * 批量删除客户积分信息
    *
    * @param ids 编号
    */
    void deleteCustomerPointsListByIds(List<Long> ids);

    /**
     * 获得客户积分信息
     *
     * @param id 编号
     * @return 客户积分信息
     */
    CustomerPointsDO getCustomerPoints(Long id);

    /**
     * 获得客户积分信息分页
     *
     * @param pageReqVO 分页查询
     * @return 客户积分信息分页
     */
    PageResult<CustomerPointsDO> getCustomerPointsPage(CustomerPointsPageReqVO pageReqVO);

}