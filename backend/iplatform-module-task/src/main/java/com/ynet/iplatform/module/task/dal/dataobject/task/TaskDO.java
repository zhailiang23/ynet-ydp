package com.ynet.iplatform.module.task.dal.dataobject.task;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ynet.iplatform.framework.tenant.core.db.TenantBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * AI智能任务 DO
 *
 * @author iplatform
 */
@TableName("aicrm_task")
@KeySequence("aicrm_task_seq")
@Data
@EqualsAndHashCode(callSuper = true)
public class TaskDO extends TenantBaseDO {

    /**
     * 任务编号
     */
    @TableId
    private Long id;

    /**
     * 任务类型
     */
    private String taskType;

    /**
     * 任务标题
     */
    private String title;

    /**
     * 任务描述
     */
    private String description;

    /**
     * 优先级
     */
    private String priority;

    /**
     * 综合评分（用于排序）
     */
    private BigDecimal comprehensiveScore;

    /**
     * 任务分类
     */
    private String category;

    /**
     * 关联客户ID
     */
    private Long customerId;

    /**
     * 客户姓名
     */
    private String customerName;

    /**
     * 任务负责人ID
     */
    private Long responsibleUserId;

    /**
     * 截止时间
     */
    private LocalDateTime deadline;

    /**
     * 完成时间
     */
    private LocalDateTime completedTime;

    /**
     * 业务价值
     */
    private String businessValue;

    /**
     * 预期收益（元）
     */
    private BigDecimal expectedRevenue;

    /**
     * 任务状态（0=待办 1=进行中 2=已完成 3=已取消）
     */
    private Integer status;

    /**
     * 是否AI生成（0=否 1=是）
     */
    private Integer aiGenerated;

    /**
     * AI生成原因
     */
    private String aiReason;

    /**
     * AI建议
     */
    private String aiSuggestion;

    /**
     * 触发来源
     */
    private String triggerSource;

    /**
     * 是否紧急（0=否 1=是）
     */
    private Integer isUrgent;

}
