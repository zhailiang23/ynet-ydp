package com.ynet.iplatform.module.grid.dal.mysql.lobbygrid;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.grid.dal.dataobject.info.GridInfoDO;
import com.ynet.iplatform.module.grid.controller.admin.lobbygrid.vo.LobbyGridPageReqVO;

import java.util.List;
import java.util.Map;

/**
 * 厅堂网格 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface LobbyGridMapper extends BaseMapperX<GridInfoDO> {

    /**
     * 分页查询厅堂网格（包含部门层级）
     *
     * @param reqVO 查询条件
     * @return 厅堂网格列表
     */
    List<Map<String, Object>> selectLobbyGridPageWithJoin(@Param("reqVO") LobbyGridPageReqVO reqVO);

    /**
     * 查询总数
     *
     * @param reqVO 查询条件
     * @return 总数
     */
    Long selectLobbyGridCount(@Param("reqVO") LobbyGridPageReqVO reqVO);

    /**
     * 插入厅堂网格（处理 GEOMETRY 字段）
     *
     * @param params 参数Map
     * @return 影响行数
     */
    int insertLobbyGrid(Map<String, Object> params);

    /**
     * 更新厅堂网格（处理 GEOMETRY 字段）
     *
     * @param params 参数Map
     * @return 影响行数
     */
    int updateLobbyGridById(Map<String, Object> params);

    /**
     * 查询厅堂网格详情（包含边界）
     *
     * @param id 网格ID
     * @return 网格详情
     */
    Map<String, Object> selectLobbyGridById(@Param("id") Long id);

}
