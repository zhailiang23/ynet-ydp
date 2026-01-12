package com.ynet.iplatform.module.grid.service.statistics;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.grid.controller.admin.statistics.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.statistics.GridStatisticsDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 网格统计报表 Service 接口
 *
 * @author 易诚源码
 */
public interface GridStatisticsService {

    /**
     * 创建网格统计报表
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createStatistics(@Valid GridStatisticsSaveReqVO createReqVO);

    /**
     * 更新网格统计报表
     *
     * @param updateReqVO 更新信息
     */
    void updateStatistics(@Valid GridStatisticsSaveReqVO updateReqVO);

    /**
     * 删除网格统计报表
     *
     * @param id 编号
     */
    void deleteStatistics(Long id);

    /**
    * 批量删除网格统计报表
    *
    * @param ids 编号
    */
    void deleteStatisticsListByIds(List<Long> ids);

    /**
     * 获得网格统计报表
     *
     * @param id 编号
     * @return 网格统计报表
     */
    GridStatisticsDO getStatistics(Long id);

    /**
     * 获得网格统计报表分页
     *
     * @param pageReqVO 分页查询
     * @return 网格统计报表分页
     */
    PageResult<GridStatisticsDO> getStatisticsPage(GridStatisticsPageReqVO pageReqVO);

}