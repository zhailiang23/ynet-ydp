package com.ynet.iplatform.module.aicrm.dal.dataobject.companybond;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
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
 * 对公客户债券信息 DO
 *
 * @author 易诚源码
 */
@TableName("crm_company_bond")
@KeySequence("crm_company_bond_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyBondDO extends BaseDO {

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
     * 债券代码
     */
    private String bondCode;
    /**
     * 债券名称
     */
    private String bondName;
    /**
     * 债券类型（国债、地方政府债、金融债、企业债、公司债、中期票据、短期融资券、可转债等）
     */
    private String bondType;
    /**
     * 债券子类型（一般企业债、高收益债、绿色债券、资产证券化等）
     */
    private String bondSubtype;
    /**
     * 发行日期
     */
    private LocalDate issueDate;
    /**
     * 发行金额（元）
     */
    private BigDecimal issueAmount;
    /**
     * 发行价格（面值百元）
     */
    private BigDecimal issuePrice;
    /**
     * 面值（元）
     */
    private BigDecimal parValue;
    /**
     * 发行规模（张）
     */
    private Long issueScale;
    /**
     * 到期日期
     */
    private LocalDate maturityDate;
    /**
     * 债券期限（年）
     */
    private BigDecimal termYears;
    /**
     * 票面利率（%）
     */
    private BigDecimal couponRate;
    /**
     * 计息方式（固定利率、浮动利率、零息、累进利率等）
     */
    private String interestType;
    /**
     * 付息频率（年付、半年付、季付、月付等）
     */
    private String paymentFrequency;
    /**
     * 债券信用评级（AAA、AA+、AA等）
     */
    private String creditRating;
    /**
     * 评级机构（中诚信、联合资信、大公国际等）
     */
    private String ratingAgency;
    /**
     * 评级日期
     */
    private LocalDate ratingDate;
    /**
     * 主承销商
     */
    private String underwriter;
    /**
     * 上市交易所（上交所、深交所、银行间市场等）
     */
    private String listingExchange;
    /**
     * 上市日期
     */
    private LocalDate listingDate;
    /**
     * 债券状态（1=正常 2=暂停交易 3=提前赎回 4=违约 5=已到期）
     */
    private Integer bondStatus;
    /**
     * 担保方式（无担保、抵押、质押、保证担保等）
     */
    private String guaranteeType;
    /**
     * 担保方
     */
    private String guarantor;
    /**
     * 增信措施
     */
    private String enhancementMeasures;
    /**
     * 是否可转债（0=否 1=是）
     */
    private Integer isConvertible;
    /**
     * 转股价格
     */
    private BigDecimal conversionPrice;
    /**
     * 转股起始日
     */
    private LocalDate conversionStartDate;
    /**
     * 转股截止日
     */
    private LocalDate conversionEndDate;
    /**
     * 转股代码
     */
    private String conversionStockCode;
    /**
     * 当前价格
     */
    private BigDecimal currentPrice;
    /**
     * 到期收益率（%）
     */
    private BigDecimal yieldToMaturity;
    /**
     * 未偿还余额（元）
     */
    private BigDecimal outstandingAmount;
    /**
     * 价格更新时间
     */
    private LocalDateTime priceUpdateTime;
    /**
     * 募集资金用途
     */
    private String useOfProceeds;
    /**
     * 是否绿色债券（0=否 1=是）
     */
    private Integer isGreenBond;
    /**
     * 特殊条款（回售条款、赎回条款、调整票面利率条款等）
     */
    private String specialClause;
    /**
     * 数据来源（手工录入、Wind、中债网、中证网等）
     */
    private String dataSource;
    /**
     * 备注
     */
    private String remark;


}