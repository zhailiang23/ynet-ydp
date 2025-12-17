package com.ynet.iplatform.module.grid.dal.mysql.huinongstation;

import java.util.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.grid.dal.dataobject.huinongstation.GridHuinongStationDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.ynet.iplatform.module.grid.controller.admin.huinongstation.vo.*;

/**
 * 惠农站点信息 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface GridHuinongStationMapper extends BaseMapperX<GridHuinongStationDO> {

    default PageResult<GridHuinongStationDO> selectPage(GridHuinongStationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GridHuinongStationDO>()
                .eqIfPresent(GridHuinongStationDO::getStationCode, reqVO.getStationCode())
                .likeIfPresent(GridHuinongStationDO::getStationName, reqVO.getStationName())
                .eqIfPresent(GridHuinongStationDO::getGridMarketingFlag, reqVO.getGridMarketingFlag())
                .orderByDesc(GridHuinongStationDO::getCreateTime));
    }

    /**
     * 查询带关联信息的站点分页列表
     * @param page MyBatis Plus 分页对象
     * @param reqVO 查询参数
     * @return 分页结果
     */
    IPage<GridHuinongStationRespVO> selectPageWithRelations(
            @Param("page") IPage<GridHuinongStationRespVO> page,
            @Param("reqVO") GridHuinongStationPageReqVO reqVO);

    /**
     * 查询站点地图数据
     * @param id 站点ID
     * @return 地图数据
     */
    GridHuinongStationMapVO selectMapDataById(@Param("id") Long id);

    /**
     * 根据站点编号查询站点
     * @param stationCode 站点编号
     * @return 站点信息
     */
    default GridHuinongStationDO selectByStationCode(String stationCode) {
        return selectOne(GridHuinongStationDO::getStationCode, stationCode);
    }

    /**
     * 插入站点并设置位置坐标
     * @param station 站点信息
     * @param longitude 经度
     * @param latitude 纬度
     * @return 影响行数
     */
    int insertWithLocation(@Param("station") GridHuinongStationDO station,
                          @Param("longitude") Double longitude,
                          @Param("latitude") Double latitude);

    /**
     * 更新站点并设置位置坐标
     * @param station 站点信息
     * @param longitude 经度
     * @param latitude 纬度
     * @return 影响行数
     */
    int updateWithLocation(@Param("station") GridHuinongStationDO station,
                          @Param("longitude") Double longitude,
                          @Param("latitude") Double latitude);

}