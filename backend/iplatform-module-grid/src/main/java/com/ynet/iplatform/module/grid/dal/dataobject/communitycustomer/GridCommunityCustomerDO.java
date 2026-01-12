package com.ynet.iplatform.module.grid.dal.dataobject.communitycustomer;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 社区客户 DO
 *
 * @author 易诚源码
 */
@TableName("grid_community_customer")
@KeySequence("grid_community_customer_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GridCommunityCustomerDO extends BaseDO {

    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 客户ID
     */
    private Long customerId;
    /**
     * 家庭成员数
     */
    private Integer familyMembers;
    /**
     * 住房类型
     */
    private String housingType;
    /**
     * 月收入
     */
    private BigDecimal monthlyIncome;

}
