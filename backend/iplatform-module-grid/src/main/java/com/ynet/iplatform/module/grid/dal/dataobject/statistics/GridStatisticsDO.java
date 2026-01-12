package com.ynet.iplatform.module.grid.dal.dataobject.statistics;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 网格统计报表 DO
 *
 * @author 易诚源码
 */
@TableName("grid_statistics")
@KeySequence("grid_statistics_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GridStatisticsDO extends BaseDO {

    /**
     * 统计ID
     */
    @TableId
    private Long id;
    /**
     * 网格ID
     */
    private Long gridId;
    /**
     * 统计日期
     */
    private LocalDate statDate;
    /**
     * 统计类型: DAILY/WEEKLY/MONTHLY
     */
    private String statType;
    /**
     * 客户总数
     */
    private Integer customerCount;
    /**
     * 新增客户数
     */
    private Integer newCustomerCount;
    /**
     * 活跃客户数
     */
    private Integer activeCustomerCount;
    /**
     * 活动次数
     */
    private Integer activityCount;
    /**
     * 贷款申请数
     */
    private Integer loanApplicationCount;
    /**
     * 贷款批准数
     */
    private Integer loanApprovalCount;
    /**
     * 贷款金额
     */
    private BigDecimal loanAmount;
    /**
     * 存款金额
     */
    private BigDecimal depositAmount;
    /**
     * 绩效评分
     */
    private BigDecimal performanceScore;


}