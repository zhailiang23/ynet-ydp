package com.ynet.iplatform.module.aicrm.controller.admin.customeraccountwealth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.time.LocalDate;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 客户理财账户信息表（零售+对公共用） Response VO")
@Data
@ExcelIgnoreUnannotated
public class CustomerAccountWealthRespVO {

    @Schema(description = "理财账户主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "15989")
    @ExcelProperty("理财账户主键")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer 主表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "9632")
    @ExcelProperty("客户ID（关联 crm_customer 主表）")
    private Long customerId;

    @Schema(description = "理财账号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("理财账号")
    private String accountNo;

    @Schema(description = "理财产品代码")
    @ExcelProperty("理财产品代码")
    private String productCode;

    @Schema(description = "理财产品名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("理财产品名称")
    private String productName;

    @Schema(description = "户名", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("户名")
    private String accountName;

    @Schema(description = "开户日期（购买日期）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("开户日期（购买日期）")
    private LocalDate openDate;

    @Schema(description = "销户日期（赎回/到期日期）")
    @ExcelProperty("销户日期（赎回/到期日期）")
    private LocalDate closeDate;

    @Schema(description = "账户状态（字典: aicrm_wealth_account_status，holding=持有中，matured=已到期，redeemed=已赎回）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("账户状态（字典: aicrm_wealth_account_status，holding=持有中，matured=已到期，redeemed=已赎回）")
    private String accountStatus;

    @Schema(description = "理财类型（字典: aicrm_wealth_product_type，fixed=固定收益，floating=浮动收益，structured=结构性）", example = "1")
    @ExcelProperty("理财类型（字典: aicrm_wealth_product_type，fixed=固定收益，floating=浮动收益，structured=结构性）")
    private String productType;

    @Schema(description = "风险等级（字典: aicrm_risk_level，R1-R5）")
    @ExcelProperty("风险等级（字典: aicrm_risk_level，R1-R5）")
    private String riskLevel;

    @Schema(description = "预期收益率（年化%）")
    @ExcelProperty("预期收益率（年化%）")
    private BigDecimal expectedReturnRate;

    @Schema(description = "实际收益率（年化%）")
    @ExcelProperty("实际收益率（年化%）")
    private BigDecimal actualReturnRate;

    @Schema(description = "理财期限（如：90天、180天、1年）")
    @ExcelProperty("理财期限（如：90天、180天、1年）")
    private String wealthTerm;

    @Schema(description = "币种", example = "1")
    @ExcelProperty("币种")
    private String currencyType;

    @Schema(description = "购买金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("购买金额")
    private BigDecimal purchaseAmount;

    @Schema(description = "当前市值", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("当前市值")
    private BigDecimal currentValue;

    @Schema(description = "累计收益")
    @ExcelProperty("累计收益")
    private BigDecimal accumulatedIncome;

    @Schema(description = "持有份额/余额", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("持有份额/余额")
    private BigDecimal balance;

    @Schema(description = "起息日")
    @ExcelProperty("起息日")
    private LocalDate valueDate;

    @Schema(description = "到期日")
    @ExcelProperty("到期日")
    private LocalDate matureDate;

    @Schema(description = "下次开放日（开放式理财）")
    @ExcelProperty("下次开放日（开放式理财）")
    private LocalDate nextOpenDate;

    @Schema(description = "销售机构ID", example = "27002")
    @ExcelProperty("销售机构ID")
    private Long deptId;

    @Schema(description = "销售机构名称", example = "李四")
    @ExcelProperty("销售机构名称")
    private String deptName;

    @Schema(description = "理财顾问用户ID", example = "25208")
    @ExcelProperty("理财顾问用户ID")
    private Long managerUserId;

    @Schema(description = "备注", example = "你猜")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}