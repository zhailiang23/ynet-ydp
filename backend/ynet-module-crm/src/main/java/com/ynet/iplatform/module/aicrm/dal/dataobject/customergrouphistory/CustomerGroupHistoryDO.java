package com.ynet.iplatform.module.aicrm.dal.dataobject.customergrouphistory;

import lombok.*;
import java.util.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户归属客群调整历史表（记录调整当时的客群信息快照） DO
 *
 * @author 易诚源码
 */
@TableName("crm_customer_group_history")
@KeySequence("crm_customer_group_history_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerGroupHistoryDO extends BaseDO {

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
    private LocalDate adjustDate;
    /**
     * 调整原因
     */
    private String adjustReason;
    /**
     * 调整前客群ID
     */
    private Long beforeGroupId;
    /**
     * 调整前客户群编号
     */
    private String beforeGroupCode;
    /**
     * 调整前客户群名称
     */
    private String beforeGroupName;
    /**
     * 调整前客户群分类
     */
    private String beforeGroupCategory;
    /**
     * 调整前客群管理员用户ID
     */
    private Long beforeManagerUserId;
    /**
     * 调整后客群ID
     */
    private Long afterGroupId;
    /**
     * 调整后客户群编号
     */
    private String afterGroupCode;
    /**
     * 调整后客户群名称
     */
    private String afterGroupName;
    /**
     * 调整后客户群分类
     */
    private String afterGroupCategory;
    /**
     * 调整后客群管理员用户ID
     */
    private Long afterManagerUserId;
    /**
     * 调整操作人用户ID（关联 system_users.id）
     */
    private Long adjustOperatorId;
    /**
     * 备注
     */
    private String remark;


}
