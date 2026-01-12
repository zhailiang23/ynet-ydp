package com.ynet.iplatform.module.grid.dal.mysql.communitygrid;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.grid.dal.dataobject.info.GridInfoDO;
import com.ynet.iplatform.module.grid.controller.admin.communitygrid.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 社区网格 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface CommunityGridMapper extends BaseMapperX<GridInfoDO> {

    /**
     * 分页查询社区网格（包含关联数据）
     */
    List<Map<String, Object>> selectCommunityGridPageWithJoin(@Param("reqVO") CommunityGridPageReqVO reqVO);

    /**
     * 查询社区网格总数
     */
    Long selectCommunityGridCount(@Param("reqVO") CommunityGridPageReqVO reqVO);

    /**
     * 插入社区网格（使用 ST_GeomFromGeoJSON 转换边界）
     */
    int insertCommunityGrid(Map<String, Object> params);

    /**
     * 更新社区网格（使用 ST_GeomFromGeoJSON 转换边界）
     */
    int updateCommunityGridById(Map<String, Object> params);

    /**
     * 查询社区网格（使用 ST_AsGeoJSON 转换边界）
     */
    Map<String, Object> selectCommunityGridById(@Param("id") Long id);

}
