package cn.iocoder.yudao.module.aicrm.controller.admin.companybond.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.time.LocalDate;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 对公客户债券信息分页 Request VO")
@Data
public class CompanyBondPageReqVO extends PageParam {

    @Schema(description = "客户ID（关联crm_customer表）", example = "6696")
    private Long customerId;

    @Schema(description = "债券代码")
    private String bondCode;

    @Schema(description = "债券名称", example = "李四")
    private String bondName;

    @Schema(description = "债券类型（国债、地方政府债、金融债、企业债、公司债、中期票据、短期融资券、可转债等）", example = "1")
    private String bondType;

    @Schema(description = "债券子类型（一般企业债、高收益债、绿色债券、资产证券化等）", example = "2")
    private String bondSubtype;

    @Schema(description = "发行日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] issueDate;

    @Schema(description = "发行金额（元）")
    private BigDecimal issueAmount;

    @Schema(description = "发行价格（面值百元）", example = "14575")
    private BigDecimal issuePrice;

    @Schema(description = "面值（元）")
    private BigDecimal parValue;

    @Schema(description = "发行规模（张）")
    private Long issueScale;

    @Schema(description = "到期日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] maturityDate;

    @Schema(description = "债券期限（年）")
    private BigDecimal termYears;

    @Schema(description = "票面利率（%）")
    private BigDecimal couponRate;

    @Schema(description = "计息方式（固定利率、浮动利率、零息、累进利率等）", example = "2")
    private String interestType;

    @Schema(description = "付息频率（年付、半年付、季付、月付等）")
    private String paymentFrequency;

    @Schema(description = "债券信用评级（AAA、AA+、AA等）")
    private String creditRating;

    @Schema(description = "评级机构（中诚信、联合资信、大公国际等）")
    private String ratingAgency;

    @Schema(description = "评级日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] ratingDate;

    @Schema(description = "主承销商")
    private String underwriter;

    @Schema(description = "上市交易所（上交所、深交所、银行间市场等）")
    private String listingExchange;

    @Schema(description = "上市日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] listingDate;

    @Schema(description = "债券状态（1=正常 2=暂停交易 3=提前赎回 4=违约 5=已到期）", example = "1")
    private Integer bondStatus;

    @Schema(description = "担保方式（无担保、抵押、质押、保证担保等）", example = "1")
    private String guaranteeType;

    @Schema(description = "担保方")
    private String guarantor;

    @Schema(description = "增信措施")
    private String enhancementMeasures;

    @Schema(description = "是否可转债（0=否 1=是）")
    private Integer isConvertible;

    @Schema(description = "转股价格", example = "10821")
    private BigDecimal conversionPrice;

    @Schema(description = "转股起始日")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] conversionStartDate;

    @Schema(description = "转股截止日")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] conversionEndDate;

    @Schema(description = "转股代码")
    private String conversionStockCode;

    @Schema(description = "当前价格", example = "9423")
    private BigDecimal currentPrice;

    @Schema(description = "到期收益率（%）")
    private BigDecimal yieldToMaturity;

    @Schema(description = "未偿还余额（元）")
    private BigDecimal outstandingAmount;

    @Schema(description = "价格更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] priceUpdateTime;

    @Schema(description = "募集资金用途")
    private String useOfProceeds;

    @Schema(description = "是否绿色债券（0=否 1=是）")
    private Integer isGreenBond;

    @Schema(description = "特殊条款（回售条款、赎回条款、调整票面利率条款等）")
    private String specialClause;

    @Schema(description = "数据来源（手工录入、Wind、中债网、中证网等）")
    private String dataSource;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}