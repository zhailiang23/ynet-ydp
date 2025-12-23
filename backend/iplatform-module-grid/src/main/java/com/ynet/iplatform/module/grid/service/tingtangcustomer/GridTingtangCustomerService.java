package com.ynet.iplatform.module.grid.service.tingtangcustomer;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.grid.controller.admin.tingtangcustomer.vo.*;
import com.ynet.iplatform.framework.common.pojo.PageResult;

/**
 * 厅堂客户 Service 接口
 *
 * @author 易诚源码
 */
public interface GridTingtangCustomerService {

    /**
     * 创建厅堂客户
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTingtangCustomer(@Valid GridTingtangCustomerSaveReqVO createReqVO);

    /**
     * 更新厅堂客户
     *
     * @param updateReqVO 更新信息
     */
    void updateTingtangCustomer(@Valid GridTingtangCustomerSaveReqVO updateReqVO);

    /**
     * 删除厅堂客户
     *
     * @param id 编号
     */
    void deleteTingtangCustomer(Long id);

    /**
    * 批量删除厅堂客户
    *
    * @param ids 编号
    */
    void deleteTingtangCustomerListByIds(List<Long> ids);

    /**
     * 获得厅堂客户
     *
     * @param id 编号
     * @return 厅堂客户
     */
    GridTingtangCustomerRespVO getTingtangCustomer(Long id);

    /**
     * 获得厅堂客户分页
     *
     * @param pageReqVO 分页查询
     * @return 厅堂客户分页
     */
    PageResult<GridTingtangCustomerRespVO> getTingtangCustomerPage(GridTingtangCustomerPageReqVO pageReqVO);

}
