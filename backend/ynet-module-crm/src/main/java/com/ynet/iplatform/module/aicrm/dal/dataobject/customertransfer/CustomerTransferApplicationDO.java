package com.ynet.iplatform.module.aicrm.dal.dataobject.customertransfer;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户移交申请表 DO
 *
 * @author 易诚源码
 */
@TableName("crm_customer_transfer_application")
@KeySequence("crm_customer_transfer_application_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库,可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerTransferApplicationDO extends BaseDO {

    /**
     * 申请ID
     */
    @TableId
    private Long id;
    /**
     * 客户ID（关联 crm_customer 主表）
     */
    private Long customerId;
    /**
     * 申请人用户ID（移交方客户经理）
     */
    private Long applicantUserId;
    /**
     * 申请人部门ID
     */
    private Long applicantDeptId;
    /**
     * 接收方客户经理ID
     */
    private Long toUserId;
    /**
     * 接收方部门ID
     */
    private Long toDeptId;
    /**
     * 申请日期
     */
    private LocalDate applyDate;
    /**
     * 移交原因
     */
    private String transferReason;
    /**
     * BPM 流程实例ID（关联 bpm_process_instance.id）
     */
    private String processInstanceId;
    /**
     * 审批状态（字典: aicrm_process_status，1=审批中，2=已通过，3=已拒绝，4=已取消）
     */
    private Integer processStatus;
    /**
     * 审批意见
     */
    private String approverComment;
    /**
     * 审批时间
     */
    private LocalDateTime approveTime;

}
