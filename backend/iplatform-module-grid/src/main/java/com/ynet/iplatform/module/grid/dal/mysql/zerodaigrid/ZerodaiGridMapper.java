package com.ynet.iplatform.module.grid.dal.mysql.zerodaigrid;

import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.grid.dal.dataobject.info.GridInfoDO;
import com.ynet.iplatform.module.grid.controller.admin.zerodaigrid.vo.ZerodaiGridPageReqVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 零贷网格 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface ZerodaiGridMapper extends BaseMapperX<GridInfoDO> {

    /**
     * 分页查询零贷网格（包含关联数据）
     */
    List<Map<String, Object>> selectZerodaiGridPageWithJoin(@Param("reqVO") ZerodaiGridPageReqVO reqVO);

    /**
     * 查询零贷网格总数
     */
    Long selectZerodaiGridCount(@Param("reqVO") ZerodaiGridPageReqVO reqVO);

    /**
     * 插入零贷网格（使用 ST_GeomFromGeoJSON 转换边界）
     */
    int insertZerodaiGrid(Map<String, Object> params);

    /**
     * 更新零贷网格（使用 ST_GeomFromGeoJSON 转换边界）
     */
    int updateZerodaiGridById(Map<String, Object> params);

    /**
     * 查询零贷网格（使用 ST_AsGeoJSON 转换边界）
     */
    Map<String, Object> selectZerodaiGridById(@Param("id") Long id);

}
