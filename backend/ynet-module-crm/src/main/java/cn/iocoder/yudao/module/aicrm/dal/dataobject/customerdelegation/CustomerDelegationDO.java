package cn.iocoder.yudao.module.aicrm.dal.dataobject.customerdelegation;

import lombok.*;
import java.util.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户托管记录 DO
 *
 * @author 芋道源码
 */
@TableName("crm_customer_delegation")
@KeySequence("crm_customer_delegation_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDelegationDO extends BaseDO {

    /**
     * 托管ID
     */
    @TableId
    private Long id;
    /**
     * 客户ID（关联 crm_customer）
     */
    private Long customerId;
    /**
     * 托管方客户经理ID（关联 system_users.id）
     */
    private Long fromUserId;
    /**
     * 受托方客户经理ID（关联 system_users.id）
     */
    private Long toUserId;
    /**
     * 托管开始日期
     */
    private LocalDate startDate;
    /**
     * 托管结束日期（计划）
     */
    private LocalDate endDate;
    /**
     * 实际结束日期
     */
    private LocalDate actualEndDate;
    /**
     * 托管原因
     */
    private String delegationReason;
    /**
     * 托管状态（0=已结束，1=托管中，2=已取消）
     */
    private Integer delegationStatus;


}