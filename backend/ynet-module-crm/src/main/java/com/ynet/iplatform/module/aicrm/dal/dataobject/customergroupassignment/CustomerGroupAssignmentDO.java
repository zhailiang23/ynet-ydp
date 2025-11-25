package com.ynet.iplatform.module.aicrm.dal.dataobject.customergroupassignment;

import lombok.*;
import java.util.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户归属客群关系表（只记录关系，客群信息通过关联查询） DO
 *
 * @author 芋道源码
 */
@TableName("crm_customer_group_assignment")
@KeySequence("crm_customer_group_assignment_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerGroupAssignmentDO extends BaseDO {

    /**
     * 归属客群关系主键
     */
    @TableId
    private Long id;
    /**
     * 客户ID（关联 crm_customer 主表）
     */
    private Long customerId;
    /**
     * 客群ID（关联 crm_customer_group_info.id）
     */
    private Long groupId;
    /**
     * 分配日期
     */
    private LocalDate assignDate;
    /**
     * 分配操作人用户ID（关联 system_users.id）
     */
    private Long assignOperatorId;
    /**
     * 归属状态（0=已失效，1=生效中）
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;


}
