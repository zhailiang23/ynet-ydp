package com.ynet.iplatform.module.grid.service.huinongstation;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.grid.controller.admin.huinongstation.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.huinongstation.GridHuinongStationDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 惠农站点信息 Service 接口
 *
 * @author 易诚源码
 */
public interface GridHuinongStationService {

    /**
     * 创建惠农站点信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createHuinongStation(@Valid GridHuinongStationSaveReqVO createReqVO);

    /**
     * 更新惠农站点信息
     *
     * @param updateReqVO 更新信息
     */
    void updateHuinongStation(@Valid GridHuinongStationSaveReqVO updateReqVO);

    /**
     * 删除惠农站点信息
     *
     * @param id 编号
     */
    void deleteHuinongStation(Long id);

    /**
    * 批量删除惠农站点信息
    *
    * @param ids 编号
    */
    void deleteHuinongStationListByIds(List<Long> ids);

    /**
     * 获得惠农站点信息
     *
     * @param id 编号
     * @return 惠农站点信息
     */
    GridHuinongStationDO getHuinongStation(Long id);

    /**
     * 获得惠农站点列表（用于下拉选择）
     *
     * @return 惠农站点列表
     */
    List<GridHuinongStationDO> getHuinongStationList();

    /**
     * 获得惠农站点信息分页
     *
     * @param pageReqVO 分页查询
     * @return 惠农站点信息分页
     */
    PageResult<GridHuinongStationDO> getHuinongStationPage(GridHuinongStationPageReqVO pageReqVO);

    /**
     * 获取惠农站点地图数据
     *
     * @param id 站点ID
     * @return 地图数据
     */
    GridHuinongStationMapVO getHuinongStationMapData(Long id);

    /**
     * 导入惠农站点信息列表
     *
     * @param importStations 导入的站点列表
     * @param updateSupport 是否支持更新
     * @return 导入结果
     */
    GridHuinongStationImportRespVO importHuinongStationList(List<GridHuinongStationImportVO> importStations, Boolean updateSupport);

    /**
     * 获取站点下的营销信息地图标记列表
     *
     * @param stationId 站点ID
     * @return 营销信息标记列表
     */
    List<GridHuinongMarketingMarkerVO> getMarketingMarkers(Long stationId);

    /**
     * 获取站点下的客户地图标记列表
     *
     * @param stationId 站点ID
     * @return 客户标记列表
     */
    List<GridHuinongCustomerMarkerVO> getCustomerMarkers(Long stationId);

}