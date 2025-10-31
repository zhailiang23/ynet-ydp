package cn.iocoder.yudao.module.aicrm.controller.admin.customerdepositoverview.vo;

import java.time.LocalDate;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 客户存款业务概览分页 Request VO")
@Data
public class CustomerDepositOverviewPageReqVO extends PageParam {

    @Schema(description = "客户ID", example = "23181")
    private Long customerId;

    @Schema(description = "客户编号")
    private String customerNo;

    @Schema(description = "统计日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] statisticsDate;

    @Schema(description = "存款类型", example = "1")
    private String depositType;

    @Schema(description = "币种")
    private String currency;

    @Schema(description = "账号")
    private String accountNo;

    @Schema(description = "卡号")
    private String cardNo;

    @Schema(description = "存款余额")
    private BigDecimal depositBalance;

    @Schema(description = "业务金额")
    private BigDecimal businessAmount;

    @Schema(description = "原币金额")
    private BigDecimal originalAmount;

    @Schema(description = "存款账户数", example = "21549")
    private Integer depositAccountCount;

    @Schema(description = "账户状态", example = "1")
    private String accountStatus;

    @Schema(description = "科目代码")
    private String subjectCode;

    @Schema(description = "产品ID", example = "17101")
    private String productId;

    @Schema(description = "本年余额日均")
    private BigDecimal balanceYearAvg;

    @Schema(description = "本年实际余额日均")
    private BigDecimal realBalanceYearAvg;

    @Schema(description = "本年累计存款")
    private BigDecimal depositCumulativeYear;

    @Schema(description = "本季度余额日均")
    private BigDecimal balanceQuarterAvg;

    @Schema(description = "本季度实际余额日均")
    private BigDecimal realBalanceQuarterAvg;

    @Schema(description = "本季度累计存款")
    private BigDecimal depositCumulativeQuarter;

    @Schema(description = "本月余额日均")
    private BigDecimal balanceMonthAvg;

    @Schema(description = "本月实际余额日均")
    private BigDecimal realBalanceMonthAvg;

    @Schema(description = "本月累计存款")
    private BigDecimal depositCumulativeMonth;

    @Schema(description = "月度总流入")
    private BigDecimal monthTotalIn;

    @Schema(description = "月度总流出")
    private BigDecimal monthTotalOut;

    @Schema(description = "购买金额")
    private BigDecimal buyAmount;

    @Schema(description = "存款利率")
    private BigDecimal interestRate;

    @Schema(description = "FTP定价", example = "788")
    private BigDecimal ftpPrice;

    @Schema(description = "存款利润贡献")
    private BigDecimal depositProfit;

    @Schema(description = "开户日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] openDate;

    @Schema(description = "起息日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] startInterestDate;

    @Schema(description = "到期日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] matureDate;

    @Schema(description = "销户日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] logoutDate;

    @Schema(description = "机构编号")
    private String orgNo;

    @Schema(description = "机构名称", example = "李四")
    private String orgName;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}