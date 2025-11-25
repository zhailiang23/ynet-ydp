package com.ynet.iplatform.module.aicrm.dal.dataobject.customerreturn;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户退回申请表 DO
 *
 * @author 芋道源码
 */
@TableName("crm_customer_return_application")
@KeySequence("crm_customer_return_application_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerReturnApplicationDO extends BaseDO {

    /**
     * 申请ID
     */
    @TableId
    private Long id;
    /**
     * 客户ID（关联 crm_customer）
     */
    private Long customerId;
    /**
     * 申请人(客户经理)ID（关联 system_users.id）
     */
    private Long applicantUserId;
    /**
     * 退回给主管ID（关联 system_users.id）
     */
    private Long returnToUserId;
    /**
     * 申请日期
     */
    private LocalDate applyDate;
    /**
     * 退回原因
     */
    private String returnReason;
    /**
     * BPM流程实例ID
     */
    private String processInstanceId;
    /**
     * 流程状态（1=审批中，2=已通过，3=已拒绝，4=已取消）
     */
    private Integer processStatus;

}
