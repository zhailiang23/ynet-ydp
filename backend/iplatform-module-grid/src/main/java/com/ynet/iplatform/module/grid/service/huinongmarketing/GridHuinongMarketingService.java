package com.ynet.iplatform.module.grid.service.huinongmarketing;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.grid.controller.admin.huinongmarketing.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.huinongmarketing.GridHuinongMarketingDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 惠农网格营销信息 Service 接口
 *
 * @author 易诚源码
 */
public interface GridHuinongMarketingService {

    /**
     * 创建惠农网格营销信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createHuinongMarketing(@Valid GridHuinongMarketingSaveReqVO createReqVO);

    /**
     * 更新惠农网格营销信息
     *
     * @param updateReqVO 更新信息
     */
    void updateHuinongMarketing(@Valid GridHuinongMarketingSaveReqVO updateReqVO);

    /**
     * 删除惠农网格营销信息
     *
     * @param id 编号
     */
    void deleteHuinongMarketing(Long id);

    /**
    * 批量删除惠农网格营销信息
    *
    * @param ids 编号
    */
    void deleteHuinongMarketingListByIds(List<Long> ids);

    /**
     * 获得惠农网格营销信息
     *
     * @param id 编号
     * @return 惠农网格营销信息
     */
    GridHuinongMarketingDO getHuinongMarketing(Long id);

    /**
     * 获得惠农网格营销信息分页
     *
     * @param pageReqVO 分页查询
     * @return 惠农网格营销信息分页
     */
    PageResult<GridHuinongMarketingRespVO> getHuinongMarketingPage(GridHuinongMarketingPageReqVO pageReqVO);

    /**
     * 获取热力图统计数据
     *
     * @param reqVO 请求参数
     * @return 统计数据
     */
    GridHuinongMarketingHeatmapStatisticsVO getHeatmapStatistics(GridHuinongMarketingHeatmapReqVO reqVO);

    /**
     * 获取热力图数据
     *
     * @param reqVO 请求参数
     * @return 热力图数据点列表
     */
    List<GridHuinongMarketingHeatmapDataVO> getHeatmapData(GridHuinongMarketingHeatmapReqVO reqVO);

}