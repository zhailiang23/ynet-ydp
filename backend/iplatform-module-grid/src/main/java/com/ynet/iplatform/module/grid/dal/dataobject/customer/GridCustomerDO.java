package com.ynet.iplatform.module.grid.dal.dataobject.customer;

import lombok.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 网格客户 DO
 *
 * @author 易诚源码
 */
@TableName("grid_customer")
@KeySequence("grid_customer_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GridCustomerDO extends BaseDO {

    /**
     * 客户ID
     */
    @TableId
    private Long id;
    /**
     * 网格ID
     */
    private Long gridId;
    /**
     * 客户类型
     */
    private String customerType;
    /**
     * 客户姓名
     */
    private String customerName;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 手机号是否验证
     */
    private Boolean phoneVerified;
    /**
     * 地址
     */
    private String address;
    /**
     * 位置坐标
     */
    private byte[] location;
    /**
     * 客户经理ID
     */
    private Long managerId;
    /**
     * 来源
     */
    private String source;
    /**
     * 状态
     */
    private String status;

}
