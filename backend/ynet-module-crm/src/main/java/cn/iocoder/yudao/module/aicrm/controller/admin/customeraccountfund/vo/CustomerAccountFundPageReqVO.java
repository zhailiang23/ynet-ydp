package cn.iocoder.yudao.module.aicrm.controller.admin.customeraccountfund.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.time.LocalDate;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 客户基金账户信息表（零售+对公共用）分页 Request VO")
@Data
public class CustomerAccountFundPageReqVO extends PageParam {

    @Schema(description = "客户ID（关联 crm_customer 主表）", example = "2173")
    private Long customerId;

    @Schema(description = "基金账号")
    private String accountNo;

    @Schema(description = "基金代码")
    private String fundCode;

    @Schema(description = "基金名称", example = "芋艿")
    private String productName;

    @Schema(description = "户名", example = "李四")
    private String accountName;

    @Schema(description = "开户日期（首次申购日期）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] openDate;

    @Schema(description = "销户日期（全部赎回日期）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] closeDate;

    @Schema(description = "账户状态（字典: aicrm_fund_account_status，holding=持有中，redeemed=已赎回）", example = "2")
    private String accountStatus;

    @Schema(description = "基金类型（字典: aicrm_fund_type，stock=股票型，bond=债券型，mixed=混合型，money=货币型，index=指数型）", example = "2")
    private String fundType;

    @Schema(description = "基金公司")
    private String fundCompany;

    @Schema(description = "风险等级（字典: aicrm_risk_level）")
    private String riskLevel;

    @Schema(description = "币种", example = "1")
    private String currencyType;

    @Schema(description = "持有份额")
    private BigDecimal holdingShares;

    @Schema(description = "累计申购金额")
    private BigDecimal purchaseAmount;

    @Schema(description = "当前净值")
    private BigDecimal currentNav;

    @Schema(description = "当前市值")
    private BigDecimal currentValue;

    @Schema(description = "累计收益")
    private BigDecimal accumulatedIncome;

    @Schema(description = "账户余额（现金部分）")
    private BigDecimal balance;

    @Schema(description = "成本价", example = "24764")
    private BigDecimal costPrice;

    @Schema(description = "收益率（%）")
    private BigDecimal profitRate;

    @Schema(description = "今日收益")
    private BigDecimal todayIncome;

    @Schema(description = "昨日收益")
    private BigDecimal yesterdayIncome;

    @Schema(description = "销售机构ID", example = "7510")
    private Long deptId;

    @Schema(description = "销售机构名称", example = "李四")
    private String deptName;

    @Schema(description = "基金顾问用户ID", example = "2887")
    private Long managerUserId;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}