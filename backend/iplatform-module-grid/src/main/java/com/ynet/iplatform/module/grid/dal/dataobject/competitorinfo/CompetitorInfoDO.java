package com.ynet.iplatform.module.grid.dal.dataobject.competitorinfo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 同业信息 DO
 *
 * @author 易诚原生智能平台
 */
@TableName("grid_competitor_info")
@KeySequence("grid_competitor_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompetitorInfoDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 同业网点名称
     */
    private String competitorName;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 经度
     */
    private BigDecimal longitude;
    /**
     * 纬度
     */
    private BigDecimal latitude;
    /**
     * 主要经营策略
     */
    private String businessStrategy;
    /**
     * 核心客户群
     */
    private String coreCustomers;
    /**
     * 产品优势
     */
    private String productAdvantages;
    /**
     * 产品劣势
     */
    private String productDisadvantages;
    /**
     * 员工工号
     */
    private String employeeCode;
    /**
     * 员工姓名
     */
    private String employeeName;


}