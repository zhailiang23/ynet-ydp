package com.ynet.iplatform.module.grid.service.gridmap;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ynet.iplatform.module.grid.controller.admin.gridmap.vo.GridMapDataReqVO;
import com.ynet.iplatform.module.grid.controller.admin.gridmap.vo.GridMapDataRespVO;
import com.ynet.iplatform.module.grid.dal.dataobject.community.CommunityInfoDO;
import com.ynet.iplatform.module.grid.dal.dataobject.competitorinfo.CompetitorInfoDO;
import com.ynet.iplatform.module.grid.dal.mysql.community.CommunityMapper;
import com.ynet.iplatform.module.grid.dal.mysql.competitorinfo.CompetitorInfoMapper;
import com.ynet.iplatform.module.grid.dal.mysql.customer.GridCustomerMapper;
import com.ynet.iplatform.module.grid.dal.mysql.info.GridInfoMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 网格营销综合地图 Service 实现类
 *
 * @author 易诚原生智能平台
 */
@Service
@Slf4j
public class GridMapServiceImpl implements GridMapService {

    @Resource
    private GridInfoMapper gridInfoMapper;

    @Resource
    private GridCustomerMapper gridCustomerMapper;

    @Resource
    private CommunityMapper communityMapper;

    @Resource
    private CompetitorInfoMapper competitorInfoMapper;

    @Override
    public GridMapDataRespVO getMapData(GridMapDataReqVO reqVO) {
        GridMapDataRespVO respVO = new GridMapDataRespVO();

        // 1. 查询网格数据
        respVO.setGrids(getGridData(reqVO.getGridTypes()));

        // 2. 查询客户数据
        respVO.setCustomers(getCustomerData(reqVO.getCustomerTypes()));

        // 3. 查询社区数据
        if (Boolean.TRUE.equals(reqVO.getIncludeCommunity())) {
            respVO.setCommunities(getCommunityData());
        } else {
            respVO.setCommunities(new ArrayList<>());
        }

        // 4. 查询同业数据
        if (Boolean.TRUE.equals(reqVO.getIncludeCompetitor())) {
            respVO.setCompetitors(getCompetitorData());
        } else {
            respVO.setCompetitors(new ArrayList<>());
        }

        return respVO;
    }

    /**
     * 获取网格数据
     */
    private List<GridMapDataRespVO.GridData> getGridData(List<String> gridTypes) {
        List<GridMapDataRespVO.GridData> result = new ArrayList<>();

        if (CollUtil.isEmpty(gridTypes)) {
            return result;
        }

        // 使用 Mapper 方法查询（通过 ST_AsGeoJSON() 直接转换为 GeoJSON 字符串）
        List<Map<String, Object>> gridMapList = gridInfoMapper.selectGridsForMap(gridTypes);

        for (Map<String, Object> gridMap : gridMapList) {
            GridMapDataRespVO.GridData gridData = new GridMapDataRespVO.GridData();
            gridData.setId(((Number) gridMap.get("id")).longValue());
            gridData.setGridName((String) gridMap.get("gridName"));
            gridData.setGridType((String) gridMap.get("gridType"));
            gridData.setBoundaryGeometry((String) gridMap.get("boundaryGeometry"));

            // 经纬度可能是 Double 或 BigDecimal
            Object longitude = gridMap.get("longitude");
            Object latitude = gridMap.get("latitude");
            if (longitude != null) {
                if (longitude instanceof BigDecimal) {
                    gridData.setLongitude((BigDecimal) longitude);
                } else {
                    gridData.setLongitude(BigDecimal.valueOf(((Number) longitude).doubleValue()));
                }
            }
            if (latitude != null) {
                if (latitude instanceof BigDecimal) {
                    gridData.setLatitude((BigDecimal) latitude);
                } else {
                    gridData.setLatitude(BigDecimal.valueOf(((Number) latitude).doubleValue()));
                }
            }

            // 管理人员 ID 可能为空
            Object managerUserId = gridMap.get("managerUserId");
            gridData.setManagerUserId(managerUserId != null ? ((Number) managerUserId).longValue() : null);

            result.add(gridData);
        }

        log.info("查询到 {} 个网格，网格类型: {}", result.size(), gridTypes);
        return result;
    }

    /**
     * 获取客户数据
     */
    private List<GridMapDataRespVO.CustomerData> getCustomerData(List<String> customerTypes) {
        if (CollUtil.isEmpty(customerTypes)) {
            return new ArrayList<>();
        }

        // 直接使用 Mapper 方法查询（已经通过 ST_X() 和 ST_Y() 提取经纬度）
        return gridCustomerMapper.selectCustomersForMap(customerTypes);
    }

    /**
     * 获取社区数据
     */
    private List<GridMapDataRespVO.CommunityData> getCommunityData() {
        List<GridMapDataRespVO.CommunityData> result = new ArrayList<>();

        // 查询社区信息
        LambdaQueryWrapper<CommunityInfoDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.isNotNull(CommunityInfoDO::getLongitude)
                .isNotNull(CommunityInfoDO::getLatitude)
                .eq(CommunityInfoDO::getStatus, "ACTIVE");

        List<CommunityInfoDO> communityList = communityMapper.selectList(wrapper);

        for (CommunityInfoDO community : communityList) {
            GridMapDataRespVO.CommunityData communityData = new GridMapDataRespVO.CommunityData();
            communityData.setId(community.getId());
            communityData.setCommunityName(community.getCommunityName());
            communityData.setLongitude(community.getLongitude());
            communityData.setLatitude(community.getLatitude());
            communityData.setAddress(community.getAddress());

            result.add(communityData);
        }

        return result;
    }

    /**
     * 获取同业数据
     */
    private List<GridMapDataRespVO.CompetitorData> getCompetitorData() {
        List<GridMapDataRespVO.CompetitorData> result = new ArrayList<>();

        // 查询同业信息
        LambdaQueryWrapper<CompetitorInfoDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.isNotNull(CompetitorInfoDO::getLongitude)
                .isNotNull(CompetitorInfoDO::getLatitude);

        List<CompetitorInfoDO> competitorList = competitorInfoMapper.selectList(wrapper);

        for (CompetitorInfoDO competitor : competitorList) {
            GridMapDataRespVO.CompetitorData competitorData = new GridMapDataRespVO.CompetitorData();
            competitorData.setId(competitor.getId());
            competitorData.setCompetitorName(competitor.getCompetitorName());
            competitorData.setLongitude(competitor.getLongitude());
            competitorData.setLatitude(competitor.getLatitude());
            competitorData.setAddress(competitor.getAddress());

            result.add(competitorData);
        }

        return result;
    }

}
