package cn.iocoder.yudao.module.aicrm.dal.dataobject.customergridassignment;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户归属网格关系表（只记录关系，网格信息通过关联查询） DO
 *
 * @author 芋道源码
 */
@TableName("crm_customer_grid_assignment")
@KeySequence("crm_customer_grid_assignment_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerGridAssignmentDO extends BaseDO {

    /**
     * 归属网格关系主键
     */
    @TableId
    private Long id;
    /**
     * 客户ID（关联 crm_customer 主表）
     */
    private Long customerId;
    /**
     * 网格ID（关联 crm_grid_info.id）
     */
    private Long gridId;
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