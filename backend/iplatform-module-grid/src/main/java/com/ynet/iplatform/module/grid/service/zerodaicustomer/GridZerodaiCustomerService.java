package com.ynet.iplatform.module.grid.service.zerodaicustomer;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.grid.controller.admin.zerodaicustomer.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.zerodaicustomer.GridZerodaiCustomerDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 零贷客户扩展 Service 接口
 *
 * @author 易诚源码
 */
public interface GridZerodaiCustomerService {

    /**
     * 创建零贷客户扩展
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createZerodaiCustomer(@Valid GridZerodaiCustomerSaveReqVO createReqVO);

    /**
     * 更新零贷客户扩展
     *
     * @param updateReqVO 更新信息
     */
    void updateZerodaiCustomer(@Valid GridZerodaiCustomerSaveReqVO updateReqVO);

    /**
     * 删除零贷客户扩展
     *
     * @param id 编号
     */
    void deleteZerodaiCustomer(Long id);

    /**
    * 批量删除零贷客户扩展
    *
    * @param ids 编号
    */
    void deleteZerodaiCustomerListByIds(List<Long> ids);

    /**
     * 获得零贷客户扩展
     *
     * @param id 编号
     * @return 零贷客户扩展
     */
    GridZerodaiCustomerDO getZerodaiCustomer(Long id);

    /**
     * 获得零贷客户扩展分页
     *
     * @param pageReqVO 分页查询
     * @return 零贷客户扩展分页
     */
    PageResult<GridZerodaiCustomerDO> getZerodaiCustomerPage(GridZerodaiCustomerPageReqVO pageReqVO);

}