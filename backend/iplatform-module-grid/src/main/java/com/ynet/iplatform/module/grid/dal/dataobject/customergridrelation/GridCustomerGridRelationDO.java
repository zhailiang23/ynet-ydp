package com.ynet.iplatform.module.grid.dal.dataobject.customergridrelation;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ynet.iplatform.framework.tenant.core.db.TenantBaseDO;
import lombok.*;

import java.time.LocalDate;

/**
 * 客户-网格关系 DO
 *
 * @author ynet
 */
@TableName("grid_customer_grid_relation")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GridCustomerGridRelationDO extends TenantBaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;

    /**
     * 客户ID (关联 grid_customer.id)
     */
    private Long customerId;

    /**
     * 网格ID (关联 grid_info.id)
     */
    private Long gridId;

    /**
     * 网格编号（冗余字段，提升查询性能）
     */
    private String gridCode;

    /**
     * 网格名称（冗余字段）
     */
    private String gridName;

    /**
     * 网格类型（冗余字段）
     */
    private String gridType;

    /**
     * 分配日期
     */
    private LocalDate assignDate;

    /**
     * 分配操作人ID
     */
    private Long assignOperatorId;
}
