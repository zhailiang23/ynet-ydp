package com.ynet.iplatform.module.grid.dal.mysql.info;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.grid.dal.dataobject.info.GridInfoDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.ynet.iplatform.module.grid.controller.admin.info.vo.*;
import com.ynet.iplatform.module.grid.controller.admin.huinongstation.vo.*;

/**
 * 网格信息 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface GridInfoMapper extends BaseMapperX<GridInfoDO> {

    default PageResult<GridInfoDO> selectPage(GridInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GridInfoDO>()
                .eqIfPresent(GridInfoDO::getGridCode, reqVO.getGridCode())
                .likeIfPresent(GridInfoDO::getGridName, reqVO.getGridName())
                .eqIfPresent(GridInfoDO::getGridType, reqVO.getGridType())
                .eqIfPresent(GridInfoDO::getOrgId, reqVO.getOrgId())
                .eqIfPresent(GridInfoDO::getParentId, reqVO.getParentId())
                .eqIfPresent(GridInfoDO::getCenterPoint, reqVO.getCenterPoint())
                .eqIfPresent(GridInfoDO::getBoundaryGeometry, reqVO.getBoundaryGeometry())
                .eqIfPresent(GridInfoDO::getRadiusMeters, reqVO.getRadiusMeters())
                .eqIfPresent(GridInfoDO::getResidentCount, reqVO.getResidentCount())
                .eqIfPresent(GridInfoDO::getMerchantCount, reqVO.getMerchantCount())
                .eqIfPresent(GridInfoDO::getStatus, reqVO.getStatus())
                .eqIfPresent(GridInfoDO::getCreator, reqVO.getCreatorId())
                .betweenIfPresent(GridInfoDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(GridInfoDO::getUpdater, reqVO.getUpdaterId())
                .orderByDesc(GridInfoDO::getId));
    }

    /**
     * 插入圆形网格并设置空间数据
     * @param grid 网格信息
     * @param centerLongitude 中心点经度
     * @param centerLatitude 中心点纬度
     * @param radiusMeters 半径（米）
     * @return 影响行数
     */
    int insertCircularGrid(@Param("grid") GridInfoDO grid,
                          @Param("centerLongitude") Double centerLongitude,
                          @Param("centerLatitude") Double centerLatitude,
                          @Param("radiusMeters") Integer radiusMeters);

    // ==================== 惠农站点相关查询方法 ====================

    /**
     * 查询带关联信息的惠农站点分页列表
     * @param page MyBatis Plus 分页对象
     * @param reqVO 查询参数
     * @return 分页结果
     */
    com.baomidou.mybatisplus.core.metadata.IPage<GridHuinongStationRespVO> selectHuinongStationPageWithRelations(
            @Param("page") com.baomidou.mybatisplus.core.metadata.IPage<GridHuinongStationRespVO> page,
            @Param("reqVO") GridHuinongStationPageReqVO reqVO);

    /**
     * 根据ID查询带关联信息的惠农站点详情
     * @param id 站点ID
     * @return 站点完整信息
     */
    GridHuinongStationRespVO selectHuinongStationByIdWithRelations(@Param("id") Long id);

    /**
     * 查询惠农站点地图数据
     * @param id 站点ID
     * @return 地图数据
     */
    GridHuinongStationMapVO selectHuinongStationMapDataById(@Param("id") Long id);

    /**
     * 根据站点编号查询惠农站点
     * @param stationCode 站点编号
     * @return 站点信息
     */
    default GridInfoDO selectHuinongStationByCode(String stationCode) {
        return selectOne(new LambdaQueryWrapperX<GridInfoDO>()
                .eq(GridInfoDO::getGridCode, stationCode)
                .eq(GridInfoDO::getGridType, "HUINONG"));
    }

    /**
     * 插入惠农站点并设置位置坐标
     * @param station 站点信息
     * @param longitude 经度
     * @param latitude 纬度
     * @return 影响行数
     */
    int insertHuinongStationWithLocation(@Param("station") GridInfoDO station,
                                        @Param("longitude") Double longitude,
                                        @Param("latitude") Double latitude);

    /**
     * 更新惠农站点并设置位置坐标
     * @param station 站点信息
     * @param longitude 经度
     * @param latitude 纬度
     * @return 影响行数
     */
    int updateHuinongStationWithLocation(@Param("station") GridInfoDO station,
                                        @Param("longitude") Double longitude,
                                        @Param("latitude") Double latitude);

    /**
     * 更新网格的边界几何（圆形缓冲区）
     * @param id 网格ID
     * @param longitude 经度
     * @param latitude 纬度
     * @param radiusMeters 半径（米）
     * @return 影响行数
     */
    int updateBoundaryGeometry(@Param("id") Long id,
                              @Param("longitude") Double longitude,
                              @Param("latitude") Double latitude,
                              @Param("radiusMeters") Integer radiusMeters);

    /**
     * 根据坐标查找包含该点的社区网格
     * 使用 ST_Contains 空间函数判断点是否在网格边界内
     * @param longitude 经度
     * @param latitude 纬度
     * @return 包含该点的社区网格列表（可能有多个）
     */
    List<GridInfoDO> selectCommunityGridsByLocation(@Param("longitude") Double longitude,
                                                     @Param("latitude") Double latitude);

    /**
     * 根据坐标查找包含该点的惠农网格
     * 使用 ST_Contains 空间函数判断点是否在网格边界内
     * @param longitude 经度
     * @param latitude 纬度
     * @return 包含该点的惠农网格列表（可能有多个）
     */
    List<GridInfoDO> selectHuinongGridsByLocation(@Param("longitude") Double longitude,
                                                   @Param("latitude") Double latitude);

    /**
     * 根据坐标查找包含该点的零贷网格
     * 使用 ST_Contains 空间函数判断点是否在网格边界内
     * @param longitude 经度
     * @param latitude 纬度
     * @return 包含该点的零贷网格列表（可能有多个）
     */
    List<GridInfoDO> selectZerodaiGridsByLocation(@Param("longitude") Double longitude,
                                                   @Param("latitude") Double latitude);

    /**
     * 根据坐标查找包含该点的厅堂（LOBBY）网格
     * 使用 ST_Contains 空间函数判断点是否在网格边界内
     * @param longitude 经度
     * @param latitude 纬度
     * @return 包含该点的厅堂网格列表（可能有多个）
     */
    List<GridInfoDO> selectLobbyGridsByLocation(@Param("longitude") Double longitude,
                                                 @Param("latitude") Double latitude);

    /**
     * 查询用于地图显示的网格数据（包含 GeoJSON 格式的边界）
     * @param gridTypes 网格类型列表
     * @return 网格数据（boundary_geometry 已转换为 GeoJSON字符串）
     */
    List<Map<String, Object>> selectGridsForMap(@Param("gridTypes") List<String> gridTypes);

}