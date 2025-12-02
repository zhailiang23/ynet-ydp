package com.ynet.iplatform.module.aicrm.dal.dataobject.customerassignmenthistory;

import lombok.*;
import java.time.LocalDate;
import java.util.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户归属调整历史表（零售+对公共用，记录所有归属变更历史） DO
 *
 * @author 易诚源码
 */
@TableName("crm_customer_assignment_history")
@KeySequence("crm_customer_assignment_history_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAssignmentHistoryDO extends BaseDO {

    /**
     * 调整历史主键
     */
    @TableId
    private Long id;
    /**
     * 客户ID（关联 crm_customer 主表）
     */
    private Long customerId;
    /**
     * 调整日期
     */
    private LocalDate transferDate;
    /**
     * 调整级别（字典: aicrm_transfer_level，如：支行内调整、跨支行调整、跨分行调整）
     */
    private String transferLevel;
    /**
     * 调整原因
     */
    private String transferReason;
    /**
     * 调整前归属类型（1=主办，2=协办）
     */
    private Integer beforeAssignmentType;
    /**
     * 调整前部门ID（关联 system_dept.id）
     */
    private Long beforeDeptId;
    /**
     * 调整前客户经理用户ID（关联 system_users.id）
     */
    private Long beforeUserId;
    /**
     * 调整后归属类型（1=主办，2=协办）
     */
    private Integer afterAssignmentType;
    /**
     * 调整后部门ID（关联 system_dept.id）
     */
    private Long afterDeptId;
    /**
     * 调整后客户经理用户ID（关联 system_users.id）
     */
    private Long afterUserId;
    /**
     * 调整操作人用户ID（关联 system_users.id）
     */
    private Long assignOperatorId;
    /**
     * 分配日期
     */
    private LocalDate assignDate;
    /**
     * 操作类型（字典: aicrm_assignment_operation_type）
     */
    private String operationType;
    /**
     * 是否托管操作
     */
    private Boolean isDelegateOperation;
    /**
     * BPM 流程实例ID（关联 bpm_process_instance.id）
     */
    private String processInstanceId;
    /**
     * 备注
     */
    private String remark;


}
