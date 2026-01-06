package com.ynet.iplatform.module.aicrm.dal.dataobject.marketingtaskassignment;

import lombok.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;
import com.ynet.iplatform.framework.mybatis.core.type.LongSetTypeHandler;

import java.util.Set;

/**
 * 营销活动任务下发 DO
 *
 * @author 易诚源码
 */
@TableName(value = "aicrm_marketing_task_assignment", autoResultMap = true)
@KeySequence("aicrm_marketing_task_assignment_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarketingTaskAssignmentDO extends BaseDO {

    /**
     * 任务下发主键
     */
    @TableId
    private Long id;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 关联营销活动ID（关联 crm_customer_marketing_activity 表）
     */
    private Long marketingActivityId;

    /**
     * 任务开始时间
     */
    private LocalDateTime startTime;

    /**
     * 任务结束时间
     */
    private LocalDateTime endTime;

    /**
     * 任务目标类型（字典: aicrm_task_target_type，view=浏览量，promotion=推广量）
     */
    private String targetType;

    /**
     * 目标值
     */
    private Integer targetValue;

    /**
     * 任务话术
     */
    private String taskScript;

    /**
     * 推广海报URL
     */
    private String posterUrl;

    /**
     * 任务派发对象（存储多个用户ID，逗号分隔）
     */
    @TableField(typeHandler = LongSetTypeHandler.class)
    private Set<Long> assignedUserIds;

    /**
     * 派发人数（冗余字段，便于统计）
     */
    private Integer assignedUserCount;

    /**
     * 任务状态（字典: aicrm_task_assignment_status，active=进行中，completed=已完成，cancelled=已取消）
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

}
