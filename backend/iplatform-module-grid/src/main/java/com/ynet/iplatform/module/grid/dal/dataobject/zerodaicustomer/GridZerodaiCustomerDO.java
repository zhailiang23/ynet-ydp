package com.ynet.iplatform.module.grid.dal.dataobject.zerodaicustomer;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 零贷客户 DO
 *
 * @author 易诚源码
 */
@TableName("grid_zerodai_customer")
@KeySequence("grid_zerodai_customer_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GridZerodaiCustomerDO extends BaseDO {

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
     * 业务类型
     */
    private String businessType;
    /**
     * 年营业额
     */
    private BigDecimal annualRevenue;
    /**
     * 信用等级
     */
    private String creditRating;
    /**
     * 是否已申请
     */
    private Boolean isApplied;
    /**
     * 是否已完成
     */
    private Boolean isCompleted;
    /**
     * 贷款金额
     */
    private BigDecimal loanAmount;
    /**
     * 贷款余额
     */
    private BigDecimal loanBalance;
    /**
     * 档案保护标识
     */
    private Boolean archiveProtected;

}
