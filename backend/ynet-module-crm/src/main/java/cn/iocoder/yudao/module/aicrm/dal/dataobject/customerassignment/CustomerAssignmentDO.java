package cn.iocoder.yudao.module.aicrm.dal.dataobject.customerassignment;

import lombok.*;
import java.time.LocalDate;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户归属关系表（零售+对公共用，支持主协办模式） DO
 *
 * @author 芋道源码
 */
@TableName("crm_customer_assignment")
@KeySequence("crm_customer_assignment_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAssignmentDO extends BaseDO {

    /**
     * 归属关系主键
     */
    @TableId
    private Long id;
    /**
     * 客户ID（关联 crm_customer 主表）
     */
    private Long customerId;
    /**
     * 归属类型（1=主办，2=协办）
     */
    private Integer assignmentType;
    /**
     * 归属部门ID（关联 system_dept.id）
     */
    private Long deptId;
    /**
     * 客户经理用户ID（关联 system_users.id）
     */
    private Long userId;
    /**
     * 是否有查看权限
     */
    private Boolean hasViewRight;
    /**
     * 是否有维护权限
     */
    private Boolean hasMaintainRight;
    /**
     * 分配日期
     */
    private LocalDate assignDate;
    /**
     * 生效日期
     */
    private LocalDate effectiveDate;
    /**
     * 失效日期（NULL表示长期有效）
     */
    private LocalDate expiryDate;
    /**
     * 分配操作人用户ID（关联 system_users.id）
     */
    private Long assignOperatorId;
    /**
     * 归属状态（0=已失效，1=生效中，2=待生效）
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;


}