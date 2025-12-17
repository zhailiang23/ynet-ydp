package com.ynet.iplatform.module.grid.service.tingtangcustomer;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.grid.controller.admin.tingtangcustomer.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.tingtangcustomer.GridTingtangCustomerDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 厅堂客户扩展 Service 接口
 *
 * @author 易诚源码
 */
public interface GridTingtangCustomerService {

    /**
     * 创建厅堂客户扩展
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTingtangCustomer(@Valid GridTingtangCustomerSaveReqVO createReqVO);

    /**
     * 更新厅堂客户扩展
     *
     * @param updateReqVO 更新信息
     */
    void updateTingtangCustomer(@Valid GridTingtangCustomerSaveReqVO updateReqVO);

    /**
     * 删除厅堂客户扩展
     *
     * @param id 编号
     */
    void deleteTingtangCustomer(Long id);

    /**
    * 批量删除厅堂客户扩展
    *
    * @param ids 编号
    */
    void deleteTingtangCustomerListByIds(List<Long> ids);

    /**
     * 获得厅堂客户扩展
     *
     * @param id 编号
     * @return 厅堂客户扩展
     */
    GridTingtangCustomerDO getTingtangCustomer(Long id);

    /**
     * 获得厅堂客户扩展分页
     *
     * @param pageReqVO 分页查询
     * @return 厅堂客户扩展分页
     */
    PageResult<GridTingtangCustomerDO> getTingtangCustomerPage(GridTingtangCustomerPageReqVO pageReqVO);

}