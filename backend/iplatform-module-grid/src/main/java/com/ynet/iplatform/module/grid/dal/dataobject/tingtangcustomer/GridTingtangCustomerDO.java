package com.ynet.iplatform.module.grid.dal.dataobject.tingtangcustomer;

import lombok.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 厅堂客户 DO
 *
 * @author 易诚源码
 */
@TableName("grid_tingtang_customer")
@KeySequence("grid_tingtang_customer_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GridTingtangCustomerDO extends BaseDO {

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
     * 性别
     */
    private String gender;
    /**
     * 客户分群
     */
    private String customerGroup;
    /**
     * 是否有CRM编号
     */
    private Boolean hasCrmNumber;
    /**
     * CRM客户ID
     */
    private String crmCustomerId;

}
