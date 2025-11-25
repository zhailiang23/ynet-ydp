package com.ynet.iplatform.module.aicrm.controller.admin.companystock.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.time.LocalDate;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 对公客户股票发行人信息分页 Request VO")
@Data
public class CompanyStockPageReqVO extends PageParam {

    @Schema(description = "客户ID（关联crm_customer表）", example = "28463")
    private Long customerId;

    @Schema(description = "股票代码")
    private String stockCode;

    @Schema(description = "股票简称", example = "李四")
    private String stockName;

    @Schema(description = "股票类型（A股、B股、H股、港股、美股、科创板等）", example = "2")
    private String stockType;

    @Schema(description = "上市交易所（上交所、深交所、港交所、纳斯达克、纽交所等）")
    private String listingExchange;

    @Schema(description = "上市日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] listingDate;

    @Schema(description = "上市状态（1=正常上市 2=暂停上市 3=终止上市 4=退市）", example = "2")
    private Integer listingStatus;

    @Schema(description = "发行价格", example = "4222")
    private BigDecimal issuePrice;

    @Schema(description = "发行数量（股）")
    private Long issueQuantity;

    @Schema(description = "发行总额（元）")
    private BigDecimal issueAmount;

    @Schema(description = "发行市盈率")
    private BigDecimal issuePeRatio;

    @Schema(description = "当前股价", example = "1633")
    private BigDecimal currentPrice;

    @Schema(description = "总市值（元）")
    private BigDecimal marketValue;

    @Schema(description = "流通市值（元）")
    private BigDecimal circulatingMarketValue;

    @Schema(description = "总股本（股）")
    private Long totalShares;

    @Schema(description = "流通股本（股）")
    private Long circulatingShares;

    @Schema(description = "行业分类（申万行业、证监会行业等）")
    private String industryCategory;

    @Schema(description = "所属板块")
    private String sector;

    @Schema(description = "是否ST股（0=否 1=是）")
    private Integer isSt;

    @Schema(description = "是否*ST股（0=否 1=是）")
    private Integer isStar;

    @Schema(description = "股票评级（买入、增持、中性、减持、卖出）")
    private String stockRating;

    @Schema(description = "价格更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] priceUpdateTime;

    @Schema(description = "数据来源（手工录入、Wind、同花顺、东方财富等）")
    private String dataSource;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}