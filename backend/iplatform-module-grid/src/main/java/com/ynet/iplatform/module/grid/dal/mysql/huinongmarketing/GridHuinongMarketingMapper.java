package com.ynet.iplatform.module.grid.dal.mysql.huinongmarketing;

import java.util.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.grid.dal.dataobject.huinongmarketing.GridHuinongMarketingDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.ynet.iplatform.module.grid.controller.admin.huinongmarketing.vo.*;
import com.ynet.iplatform.module.grid.controller.admin.huinongstation.vo.GridHuinongMarketingMarkerVO;

/**
 * 惠农网格营销信息 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface GridHuinongMarketingMapper extends BaseMapperX<GridHuinongMarketingDO> {

    /**
     * 查询带关联信息的营销信息分页列表
     * @param page MyBatis Plus 分页对象
     * @param reqVO 查询参数
     * @return 分页结果
     */
    IPage<GridHuinongMarketingRespVO> selectPageWithRelations(
            @Param("page") IPage<GridHuinongMarketingRespVO> page,
            @Param("reqVO") GridHuinongMarketingPageReqVO reqVO);

    /**
     * 查询站点下的营销信息地图标记列表
     * @param stationId 站点ID
     * @return 营销信息标记列表
     */
    List<GridHuinongMarketingMarkerVO> selectMarkersByStationId(@Param("stationId") Long stationId);

    /**
     * 查询热力图营销信息列表（带站点关联）
     * @param reqVO 请求参数
     * @return 营销信息列表
     */
    List<GridHuinongMarketingDO> selectHeatmapMarketingList(@Param("reqVO") GridHuinongMarketingHeatmapReqVO reqVO);

    /**
     * 查询热力图数据（按位置分组统计）
     * @param reqVO 请求参数
     * @return 热力图数据点列表
     */
    List<GridHuinongMarketingHeatmapDataVO> selectHeatmapData(@Param("reqVO") GridHuinongMarketingHeatmapReqVO reqVO);

}