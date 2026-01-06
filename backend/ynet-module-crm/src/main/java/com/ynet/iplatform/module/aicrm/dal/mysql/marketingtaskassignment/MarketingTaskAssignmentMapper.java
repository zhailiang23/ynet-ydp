package com.ynet.iplatform.module.aicrm.dal.mysql.marketingtaskassignment;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.marketingtaskassignment.MarketingTaskAssignmentDO;
import com.ynet.iplatform.module.aicrm.controller.admin.marketingtaskassignment.vo.MarketingTaskAssignmentRespVO;
import com.ynet.iplatform.module.aicrm.controller.admin.marketingtaskassignment.vo.MarketingTaskAssignmentPageReqVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 营销活动任务下发 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface MarketingTaskAssignmentMapper extends BaseMapperX<MarketingTaskAssignmentDO> {

    default PageResult<MarketingTaskAssignmentDO> selectPage(MarketingTaskAssignmentPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MarketingTaskAssignmentDO>()
                .likeIfPresent(MarketingTaskAssignmentDO::getTaskName, reqVO.getTaskName())
                .eqIfPresent(MarketingTaskAssignmentDO::getMarketingActivityId, reqVO.getMarketingActivityId())
                .betweenIfPresent(MarketingTaskAssignmentDO::getStartTime, reqVO.getStartTime())
                .betweenIfPresent(MarketingTaskAssignmentDO::getEndTime, reqVO.getEndTime())
                .eqIfPresent(MarketingTaskAssignmentDO::getTargetType, reqVO.getTargetType())
                .eqIfPresent(MarketingTaskAssignmentDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(MarketingTaskAssignmentDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MarketingTaskAssignmentDO::getId));
    }

    /**
     * 分页查询任务下发列表（关联营销活动名称）
     */
    @Select("<script>" +
            "SELECT t.*, a.activity_name AS marketingActivityName " +
            "FROM aicrm_marketing_task_assignment t " +
            "LEFT JOIN crm_customer_marketing_activity a ON t.marketing_activity_id = a.id " +
            "WHERE t.deleted = 0 " +
            "<if test='taskName != null and taskName != \"\"'>" +
            "  AND t.task_name LIKE CONCAT('%', #{taskName}, '%') " +
            "</if>" +
            "<if test='marketingActivityId != null'>" +
            "  AND t.marketing_activity_id = #{marketingActivityId} " +
            "</if>" +
            "<if test='targetType != null and targetType != \"\"'>" +
            "  AND t.target_type = #{targetType} " +
            "</if>" +
            "<if test='status != null and status != \"\"'>" +
            "  AND t.status = #{status} " +
            "</if>" +
            "ORDER BY t.id DESC " +
            "LIMIT #{offset}, #{pageSize}" +
            "</script>")
    List<MarketingTaskAssignmentRespVO> selectPageWithActivity(MarketingTaskAssignmentPageReqVO reqVO);

    /**
     * 查询总数
     */
    @Select("<script>" +
            "SELECT COUNT(*) " +
            "FROM aicrm_marketing_task_assignment t " +
            "WHERE t.deleted = 0 " +
            "<if test='taskName != null and taskName != \"\"'>" +
            "  AND t.task_name LIKE CONCAT('%', #{taskName}, '%') " +
            "</if>" +
            "<if test='marketingActivityId != null'>" +
            "  AND t.marketing_activity_id = #{marketingActivityId} " +
            "</if>" +
            "<if test='targetType != null and targetType != \"\"'>" +
            "  AND t.target_type = #{targetType} " +
            "</if>" +
            "<if test='status != null and status != \"\"'>" +
            "  AND t.status = #{status} " +
            "</if>" +
            "</script>")
    Long selectPageCountWithActivity(MarketingTaskAssignmentPageReqVO reqVO);

}
