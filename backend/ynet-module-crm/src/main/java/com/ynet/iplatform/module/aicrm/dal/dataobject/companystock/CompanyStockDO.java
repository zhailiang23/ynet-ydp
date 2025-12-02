package com.ynet.iplatform.module.aicrm.dal.dataobject.companystock;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 对公客户股票发行人信息 DO
 *
 * @author 易诚源码
 */
@TableName("crm_company_stock")
@KeySequence("crm_company_stock_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyStockDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 客户ID（关联crm_customer表）
     */
    private Long customerId;
    /**
     * 股票代码
     */
    private String stockCode;
    /**
     * 股票简称
     */
    private String stockName;
    /**
     * 股票类型（A股、B股、H股、港股、美股、科创板等）
     */
    private String stockType;
    /**
     * 上市交易所（上交所、深交所、港交所、纳斯达克、纽交所等）
     */
    private String listingExchange;
    /**
     * 上市日期
     */
    private LocalDate listingDate;
    /**
     * 上市状态（1=正常上市 2=暂停上市 3=终止上市 4=退市）
     */
    private Integer listingStatus;
    /**
     * 发行价格
     */
    private BigDecimal issuePrice;
    /**
     * 发行数量（股）
     */
    private Long issueQuantity;
    /**
     * 发行总额（元）
     */
    private BigDecimal issueAmount;
    /**
     * 发行市盈率
     */
    private BigDecimal issuePeRatio;
    /**
     * 当前股价
     */
    private BigDecimal currentPrice;
    /**
     * 总市值（元）
     */
    private BigDecimal marketValue;
    /**
     * 流通市值（元）
     */
    private BigDecimal circulatingMarketValue;
    /**
     * 总股本（股）
     */
    private Long totalShares;
    /**
     * 流通股本（股）
     */
    private Long circulatingShares;
    /**
     * 行业分类（申万行业、证监会行业等）
     */
    private String industryCategory;
    /**
     * 所属板块
     */
    private String sector;
    /**
     * 是否ST股（0=否 1=是）
     */
    private Integer isSt;
    /**
     * 是否*ST股（0=否 1=是）
     */
    private Integer isStar;
    /**
     * 股票评级（买入、增持、中性、减持、卖出）
     */
    private String stockRating;
    /**
     * 价格更新时间
     */
    private LocalDateTime priceUpdateTime;
    /**
     * 数据来源（手工录入、Wind、同花顺、东方财富等）
     */
    private String dataSource;
    /**
     * 备注
     */
    private String remark;


}