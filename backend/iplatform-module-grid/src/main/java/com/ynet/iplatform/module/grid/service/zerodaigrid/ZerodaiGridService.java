package com.ynet.iplatform.module.grid.service.zerodaigrid;

import com.ynet.iplatform.module.grid.controller.admin.zerodaigrid.vo.*;
import com.ynet.iplatform.framework.common.pojo.PageResult;

/**
 * 零贷网格 Service 接口
 *
 * @author 易诚源码
 */
public interface ZerodaiGridService {

    /**
     * 获得零贷网格分页
     *
     * @param pageReqVO 分页查询
     * @return 零贷网格分页
     */
    PageResult<ZerodaiGridRespVO> getZerodaiGridPage(ZerodaiGridPageReqVO pageReqVO);

    /**
     * 获得零贷网格详情
     *
     * @param id 网格ID
     * @return 零贷网格
     */
    ZerodaiGridRespVO getZerodaiGrid(Long id);

    /**
     * 创建零贷网格
     *
     * @param createReqVO 创建信息
     * @return 网格ID
     */
    Long createZerodaiGrid(ZerodaiGridSaveReqVO createReqVO);

    /**
     * 更新零贷网格
     *
     * @param updateReqVO 更新信息
     */
    void updateZerodaiGrid(ZerodaiGridSaveReqVO updateReqVO);

    /**
     * 删除零贷网格
     *
     * @param id 网格ID
     */
    void deleteZerodaiGrid(Long id);

}
