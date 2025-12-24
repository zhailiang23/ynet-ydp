package com.ynet.iplatform.module.grid.service.huinongcustomerloan;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.grid.controller.admin.huinongcustomerloan.vo.*;
import com.ynet.iplatform.framework.common.pojo.PageResult;

/**
 * 惠农贷款客户扩展 Service 接口
 *
 * @author 易诚源码
 */
public interface GridHuinongCustomerLoanService {

    /**
     * 创建惠农贷款客户扩展
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createHuinongCustomerLoan(@Valid GridHuinongCustomerLoanSaveReqVO createReqVO);

    /**
     * 更新惠农贷款客户扩展
     *
     * @param updateReqVO 更新信息
     */
    void updateHuinongCustomerLoan(@Valid GridHuinongCustomerLoanSaveReqVO updateReqVO);

    /**
     * 删除惠农贷款客户扩展
     *
     * @param id 编号
     */
    void deleteHuinongCustomerLoan(Long id);

    /**
    * 批量删除惠农贷款客户扩展
    *
    * @param ids 编号
    */
    void deleteHuinongCustomerLoanListByIds(List<Long> ids);

    /**
     * 获得惠农贷款客户扩展（包含关联的客户信息和站点信息）
     *
     * @param id 编号
     * @return 惠农贷款客户扩展
     */
    GridHuinongCustomerLoanRespVO getHuinongCustomerLoan(Long id);

    /**
     * 获得惠农贷款客户扩展分页
     *
     * @param pageReqVO 分页查询
     * @return 惠农贷款客户扩展分页
     */
    PageResult<GridHuinongCustomerLoanRespVO> getHuinongCustomerLoanPage(GridHuinongCustomerLoanPageReqVO pageReqVO);

    /**
     * 获取惠农贷款目标客户热力图数据
     *
     * @param reqVO 请求参数
     * @return 热力图数据列表
     */
    List<GridHuinongCustomerLoanHeatmapDataVO> getHeatmapData(GridHuinongCustomerLoanHeatmapReqVO reqVO);

    /**
     * 获取惠农贷款目标客户标记列表
     *
     * @return 客户标记列表
     */
    List<GridHuinongCustomerLoanCustomerMarkerVO> getCustomerMarkers();

}