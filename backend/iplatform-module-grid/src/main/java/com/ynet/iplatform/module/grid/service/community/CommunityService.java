package com.ynet.iplatform.module.grid.service.community;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.module.grid.controller.admin.community.vo.CommunityPageReqVO;
import com.ynet.iplatform.module.grid.controller.admin.community.vo.CommunityRespVO;
import com.ynet.iplatform.module.grid.controller.admin.community.vo.CommunitySaveReqVO;

import jakarta.validation.Valid;

/**
 * 社区信息 Service 接口
 *
 * @author 易诚源码
 */
public interface CommunityService {

    /**
     * 创建社区信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCommunity(@Valid CommunitySaveReqVO createReqVO);

    /**
     * 更新社区信息
     *
     * @param updateReqVO 更新信息
     */
    void updateCommunity(@Valid CommunitySaveReqVO updateReqVO);

    /**
     * 删除社区信息
     *
     * @param id 编号
     */
    void deleteCommunity(Long id);

    /**
     * 获得社区信息
     *
     * @param id 编号
     * @return 社区信息
     */
    CommunityRespVO getCommunity(Long id);

    /**
     * 获得社区信息分页
     *
     * @param pageReqVO 分页查询
     * @return 社区信息分页
     */
    PageResult<CommunityRespVO> getCommunityPage(CommunityPageReqVO pageReqVO);

}
