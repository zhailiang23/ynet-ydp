package com.ynet.iplatform.module.grid.service.customer;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.grid.controller.admin.customer.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.customer.GridCustomerDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 网格客户档案 Service 接口
 *
 * @author 易诚源码
 */
public interface GridCustomerService {

    /**
     * 创建网格客户档案
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomer(@Valid GridCustomerSaveReqVO createReqVO);

    /**
     * 更新网格客户档案
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomer(@Valid GridCustomerSaveReqVO updateReqVO);

    /**
     * 删除网格客户档案
     *
     * @param id 编号
     */
    void deleteCustomer(Long id);

    /**
    * 批量删除网格客户档案
    *
    * @param ids 编号
    */
    void deleteCustomerListByIds(List<Long> ids);

    /**
     * 获得网格客户档案
     *
     * @param id 编号
     * @return 网格客户档案
     */
    GridCustomerDO getCustomer(Long id);

    /**
     * 获得网格客户档案分页
     *
     * @param pageReqVO 分页查询
     * @return 网格客户档案分页
     */
    PageResult<GridCustomerDO> getCustomerPage(GridCustomerPageReqVO pageReqVO);

}