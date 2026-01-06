package com.ynet.iplatform.module.aicrm.service.marketingtaskassignment;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.aicrm.controller.admin.marketingtaskassignment.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.marketingtaskassignment.MarketingTaskAssignmentDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;

/**
 * 营销活动任务下发 Service 接口
 *
 * @author 易诚源码
 */
public interface MarketingTaskAssignmentService {

    /**
     * 创建营销活动任务下发
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMarketingTaskAssignment(@Valid MarketingTaskAssignmentSaveReqVO createReqVO);

    /**
     * 更新营销活动任务下发
     *
     * @param updateReqVO 更新信息
     */
    void updateMarketingTaskAssignment(@Valid MarketingTaskAssignmentSaveReqVO updateReqVO);

    /**
     * 删除营销活动任务下发
     *
     * @param id 编号
     */
    void deleteMarketingTaskAssignment(Long id);

    /**
     * 批量删除营销活动任务下发
     *
     * @param ids 编号列表
     */
    void deleteMarketingTaskAssignmentListByIds(List<Long> ids);

    /**
     * 获得营销活动任务下发
     *
     * @param id 编号
     * @return 营销活动任务下发
     */
    MarketingTaskAssignmentDO getMarketingTaskAssignment(Long id);

    /**
     * 获得营销活动任务下发分页
     *
     * @param pageReqVO 分页查询
     * @return 营销活动任务下发分页
     */
    PageResult<MarketingTaskAssignmentDO> getMarketingTaskAssignmentPage(MarketingTaskAssignmentPageReqVO pageReqVO);

    /**
     * 获得营销活动任务下发分页（含关联活动名称）
     *
     * @param pageReqVO 分页查询
     * @return 营销活动任务下发分页
     */
    PageResult<MarketingTaskAssignmentRespVO> getMarketingTaskAssignmentPageWithActivity(MarketingTaskAssignmentPageReqVO pageReqVO);

}
