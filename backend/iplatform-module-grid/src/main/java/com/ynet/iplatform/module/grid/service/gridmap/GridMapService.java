package com.ynet.iplatform.module.grid.service.gridmap;

import com.ynet.iplatform.module.grid.controller.admin.gridmap.vo.GridMapDataReqVO;
import com.ynet.iplatform.module.grid.controller.admin.gridmap.vo.GridMapDataRespVO;

/**
 * 网格营销综合地图 Service 接口
 *
 * @author 易诚原生智能平台
 */
public interface GridMapService {

    /**
     * 获取综合地图数据
     *
     * @param reqVO 请求参数
     * @return 地图数据
     */
    GridMapDataRespVO getMapData(GridMapDataReqVO reqVO);

}
