package cn.iocoder.yudao.module.aicrm.dal.dataobject.customergridhistory;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户归属网格调整历史表（记录调整当时的网格信息快照） DO
 *
 * @author 芋道源码
 */
@TableName("crm_customer_grid_history")
@KeySequence("crm_customer_grid_history_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerGridHistoryDO extends BaseDO {

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
     * 调整前网格ID
     */
    private Long beforeGridId;
    /**
     * 调整前网格编号
     */
    private String beforeGridCode;
    /**
     * 调整前网格名称
     */
    private String beforeGridName;
    /**
     * 调整前网格类型
     */
    private String beforeGridType;
    /**
     * 调整前网格管理员用户ID
     */
    private Long beforeGridManagerUserId;
    /**
     * 调整后网格ID
     */
    private Long afterGridId;
    /**
     * 调整后网格编号
     */
    private String afterGridCode;
    /**
     * 调整后网格名称
     */
    private String afterGridName;
    /**
     * 调整后网格类型
     */
    private String afterGridType;
    /**
     * 调整后网格管理员用户ID
     */
    private Long afterGridManagerUserId;
    /**
     * 调整操作人用户ID（关联 system_users.id）
     */
    private Long adjustOperatorId;
    /**
     * 备注
     */
    private String remark;


}
