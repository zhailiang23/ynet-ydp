package com.ynet.iplatform.module.grid.service.communitygrid;

import com.ynet.iplatform.module.grid.controller.admin.communitygrid.vo.*;
import com.ynet.iplatform.framework.common.pojo.PageResult;

/**
 * 社区网格 Service 接口
 *
 * @author 易诚源码
 */
public interface CommunityGridService {

    /**
     * 获得社区网格分页
     *
     * @param pageReqVO 分页查询
     * @return 社区网格分页
     */
    PageResult<CommunityGridRespVO> getCommunityGridPage(CommunityGridPageReqVO pageReqVO);

    /**
     * 获得社区网格详情
     *
     * @param id 网格ID
     * @return 社区网格
     */
    CommunityGridRespVO getCommunityGrid(Long id);

    /**
     * 创建社区网格
     *
     * @param createReqVO 创建信息
     * @return 网格ID
     */
    Long createCommunityGrid(CommunityGridSaveReqVO createReqVO);

    /**
     * 更新社区网格
     *
     * @param updateReqVO 更新信息
     */
    void updateCommunityGrid(CommunityGridSaveReqVO updateReqVO);

    /**
     * 删除社区网格
     *
     * @param id 网格ID
     */
    void deleteCommunityGrid(Long id);

}
