package com.ynet.iplatform.module.grid.dal.dataobject.customerarchiveprotection;

import lombok.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户档案保护 DO
 *
 * @author 易诚源码
 */
@TableName("grid_customer_archive_protection")
@KeySequence("grid_customer_archive_protection_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GridCustomerArchiveProtectionDO extends BaseDO {

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
     * 保护类型
     */
    private String protectionType;
    /**
     * 原客户经理ID
     */
    private Long originalManagerId;
    /**
     * 认领时间
     */
    private LocalDateTime claimTime;
    /**
     * 保护开始时间
     */
    private LocalDateTime protectionStartTime;
    /**
     * 保护结束时间
     */
    private LocalDateTime protectionEndTime;
    /**
     * 是否保护中
     */
    private Boolean isProtected;
    /**
     * 释放原因
     */
    private String releaseReason;

}
