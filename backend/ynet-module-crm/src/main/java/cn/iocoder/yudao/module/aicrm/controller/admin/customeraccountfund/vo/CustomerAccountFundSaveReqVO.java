package cn.iocoder.yudao.module.aicrm.controller.admin.customeraccountfund.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(description = "管理后台 - 客户基金账户信息表（零售+对公共用）新增/修改 Request VO")
@Data
public class CustomerAccountFundSaveReqVO {

    @Schema(description = "基金账户主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "18364")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer 主表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2173")
    @NotNull(message = "客户ID（关联 crm_customer 主表）不能为空")
    private Long customerId;

    @Schema(description = "基金账号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "基金账号不能为空")
    private String accountNo;

    @Schema(description = "基金代码")
    private String fundCode;

    @Schema(description = "基金名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "基金名称不能为空")
    private String productName;

    @Schema(description = "户名", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "户名不能为空")
    private String accountName;

    @Schema(description = "开户日期（首次申购日期）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "开户日期（首次申购日期）不能为空")
    private LocalDate openDate;

    @Schema(description = "销户日期（全部赎回日期）")
    private LocalDate closeDate;

    @Schema(description = "账户状态（字典: aicrm_fund_account_status，holding=持有中，redeemed=已赎回）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "账户状态（字典: aicrm_fund_account_status，holding=持有中，redeemed=已赎回）不能为空")
    private String accountStatus;

    @Schema(description = "基金类型（字典: aicrm_fund_type，stock=股票型，bond=债券型，mixed=混合型，money=货币型，index=指数型）", example = "2")
    private String fundType;

    @Schema(description = "基金公司")
    private String fundCompany;

    @Schema(description = "风险等级（字典: aicrm_risk_level）")
    private String riskLevel;

    @Schema(description = "币种", example = "1")
    private String currencyType;

    @Schema(description = "持有份额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "持有份额不能为空")
    private BigDecimal holdingShares;

    @Schema(description = "累计申购金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "累计申购金额不能为空")
    private BigDecimal purchaseAmount;

    @Schema(description = "当前净值")
    private BigDecimal currentNav;

    @Schema(description = "当前市值", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "当前市值不能为空")
    private BigDecimal currentValue;

    @Schema(description = "累计收益")
    private BigDecimal accumulatedIncome;

    @Schema(description = "账户余额（现金部分）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "账户余额（现金部分）不能为空")
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

}