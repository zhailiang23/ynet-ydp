package com.ynet.iplatform.module.grid.service.lobbygrid;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.module.grid.controller.admin.lobbygrid.vo.LobbyGridPageReqVO;
import com.ynet.iplatform.module.grid.controller.admin.lobbygrid.vo.LobbyGridRespVO;
import com.ynet.iplatform.module.grid.controller.admin.lobbygrid.vo.LobbyGridSaveReqVO;

/**
 * 厅堂网格 Service 接口
 *
 * @author 易诚源码
 */
public interface LobbyGridService {

    /**
     * 创建厅堂网格
     *
     * @param createReqVO 创建信息
     * @return 网格ID
     */
    Long createLobbyGrid(LobbyGridSaveReqVO createReqVO);

    /**
     * 更新厅堂网格
     *
     * @param updateReqVO 更新信息
     */
    void updateLobbyGrid(LobbyGridSaveReqVO updateReqVO);

    /**
     * 删除厅堂网格
     *
     * @param id 网格ID
     */
    void deleteLobbyGrid(Long id);

    /**
     * 获得厅堂网格详情
     *
     * @param id 网格ID
     * @return 网格详情
     */
    LobbyGridRespVO getLobbyGrid(Long id);

    /**
     * 获得厅堂网格分页
     *
     * @param pageReqVO 分页查询
     * @return 网格分页
     */
    PageResult<LobbyGridRespVO> getLobbyGridPage(LobbyGridPageReqVO pageReqVO);

}
