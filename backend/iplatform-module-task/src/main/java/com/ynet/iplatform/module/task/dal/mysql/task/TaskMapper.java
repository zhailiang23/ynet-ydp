package com.ynet.iplatform.module.task.dal.mysql.task;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.module.task.controller.admin.task.vo.TaskPageReqVO;
import com.ynet.iplatform.module.task.controller.app.task.vo.AppTaskPageReqVO;
import com.ynet.iplatform.module.task.dal.dataobject.task.TaskDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

/**
 * AI智能任务 Mapper
 *
 * @author iplatform
 */
@Mapper
public interface TaskMapper extends BaseMapperX<TaskDO> {

    default PageResult<TaskDO> selectPage(TaskPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TaskDO>()
                .likeIfPresent(TaskDO::getTitle, reqVO.getTitle())
                .eqIfPresent(TaskDO::getTaskType, reqVO.getTaskType())
                .eqIfPresent(TaskDO::getPriority, reqVO.getPriority())
                .eqIfPresent(TaskDO::getStatus, reqVO.getStatus())
                .eqIfPresent(TaskDO::getCategory, reqVO.getCategory())
                .eqIfPresent(TaskDO::getBusinessValue, reqVO.getBusinessValue())
                .eqIfPresent(TaskDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(TaskDO::getResponsibleUserId, reqVO.getResponsibleUserId())
                .betweenIfPresent(TaskDO::getDeadline, reqVO.getDeadlineStart(), reqVO.getDeadlineEnd())
                .betweenIfPresent(TaskDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TaskDO::getComprehensiveScore));
    }

    default PageResult<TaskDO> selectPage(AppTaskPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TaskDO>()
                .eqIfPresent(TaskDO::getStatus, reqVO.getStatus())
                .eqIfPresent(TaskDO::getPriority, reqVO.getPriority())
                .eqIfPresent(TaskDO::getAiGenerated, reqVO.getAiGenerated())
                .likeIfPresent(TaskDO::getCustomerName, reqVO.getCustomerName())
                .geIfPresent(TaskDO::getComprehensiveScore, reqVO.getMinBusinessValue())
                .eqIfPresent(TaskDO::getResponsibleUserId, reqVO.getResponsibleUserId())
                .orderByDesc(TaskDO::getComprehensiveScore)
                .orderByDesc(TaskDO::getCreateTime));
    }

    default List<TaskDO> selectListByCustomerId(Long customerId) {
        return selectList(new LambdaQueryWrapperX<TaskDO>()
                .eq(TaskDO::getCustomerId, customerId)
                .orderByDesc(TaskDO::getComprehensiveScore)
                .orderByDesc(TaskDO::getCreateTime));
    }

    /**
     * 根据客户 ID 查询客户名称
     */
    @Select("SELECT customer_name FROM crm_customer WHERE id = #{customerId} AND deleted = 0 LIMIT 1")
    String selectCustomerNameById(Long customerId);

    /**
     * 检查是否存在相同的任务（用于去重）
     * 去重条件：同样的客户、同样的负责人、同样的任务名称、同样的截止时间
     */
    default boolean existsDuplicateTask(Long customerId, Long responsibleUserId, String title, LocalDateTime deadline) {
        LambdaQueryWrapperX<TaskDO> wrapper = new LambdaQueryWrapperX<TaskDO>()
                .eq(TaskDO::getCustomerId, customerId)
                .eq(TaskDO::getResponsibleUserId, responsibleUserId)
                .eq(TaskDO::getTitle, title);

        // 特殊处理 deadline 为 null 的情况
        if (deadline == null) {
            wrapper.isNull(TaskDO::getDeadline);
        } else {
            wrapper.eq(TaskDO::getDeadline, deadline);
        }

        Long count = selectCount(wrapper);
        return count != null && count > 0;
    }

}
