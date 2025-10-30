package cn.iocoder.yudao.module.aicrm.controller.admin.customeraccountfund.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.time.LocalDate;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 客户基金账户信息表（零售+对公共用） Response VO")
@Data
@ExcelIgnoreUnannotated
public class CustomerAccountFundRespVO {

    @Schema(description = "基金账户主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "18364")
    @ExcelProperty("基金账户主键")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer 主表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2173")
    @ExcelProperty("客户ID（关联 crm_customer 主表）")
    private Long customerId;

    @Schema(description = "基金账号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("基金账号")
    private String accountNo;

    @Schema(description = "基金代码")
    @ExcelProperty("基金代码")
    private String fundCode;

    @Schema(description = "基金名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("基金名称")
    private String productName;

    @Schema(description = "户名", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("户名")
    private String accountName;

    @Schema(description = "开户日期（首次申购日期）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("开户日期（首次申购日期）")
    private LocalDate openDate;

    @Schema(description = "销户日期（全部赎回日期）")
    @ExcelProperty("销户日期（全部赎回日期）")
    private LocalDate closeDate;

    @Schema(description = "账户状态（字典: aicrm_fund_account_status，holding=持有中，redeemed=已赎回）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("账户状态（字典: aicrm_fund_account_status，holding=持有中，redeemed=已赎回）")
    private String accountStatus;

    @Schema(description = "基金类型（字典: aicrm_fund_type，stock=股票型，bond=债券型，mixed=混合型，money=货币型，index=指数型）", example = "2")
    @ExcelProperty("基金类型（字典: aicrm_fund_type，stock=股票型，bond=债券型，mixed=混合型，money=货币型，index=指数型）")
    private String fundType;

    @Schema(description = "基金公司")
    @ExcelProperty("基金公司")
    private String fundCompany;

    @Schema(description = "风险等级（字典: aicrm_risk_level）")
    @ExcelProperty("风险等级（字典: aicrm_risk_level）")
    private String riskLevel;

    @Schema(description = "币种", example = "1")
    @ExcelProperty("币种")
    private String currencyType;

    @Schema(description = "持有份额", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("持有份额")
    private BigDecimal holdingShares;

    @Schema(description = "累计申购金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("累计申购金额")
    private BigDecimal purchaseAmount;

    @Schema(description = "当前净值")
    @ExcelProperty("当前净值")
    private BigDecimal currentNav;

    @Schema(description = "当前市值", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("当前市值")
    private BigDecimal currentValue;

    @Schema(description = "累计收益")
    @ExcelProperty("累计收益")
    private BigDecimal accumulatedIncome;

    @Schema(description = "账户余额（现金部分）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("账户余额（现金部分）")
    private BigDecimal balance;

    @Schema(description = "成本价", example = "24764")
    @ExcelProperty("成本价")
    private BigDecimal costPrice;

    @Schema(description = "收益率（%）")
    @ExcelProperty("收益率（%）")
    private BigDecimal profitRate;

    @Schema(description = "今日收益")
    @ExcelProperty("今日收益")
    private BigDecimal todayIncome;

    @Schema(description = "昨日收益")
    @ExcelProperty("昨日收益")
    private BigDecimal yesterdayIncome;

    @Schema(description = "销售机构ID", example = "7510")
    @ExcelProperty("销售机构ID")
    private Long deptId;

    @Schema(description = "销售机构名称", example = "李四")
    @ExcelProperty("销售机构名称")
    private String deptName;

    @Schema(description = "基金顾问用户ID", example = "2887")
    @ExcelProperty("基金顾问用户ID")
    private Long managerUserId;

    @Schema(description = "备注", example = "你说的对")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}