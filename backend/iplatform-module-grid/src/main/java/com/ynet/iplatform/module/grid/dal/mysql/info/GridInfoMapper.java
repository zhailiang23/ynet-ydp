package com.ynet.iplatform.module.grid.dal.mysql.info;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.grid.dal.dataobject.info.GridInfoDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.ynet.iplatform.module.grid.controller.admin.info.vo.*;

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

}