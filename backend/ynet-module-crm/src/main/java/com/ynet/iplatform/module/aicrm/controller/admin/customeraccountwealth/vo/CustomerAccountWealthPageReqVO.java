package com.ynet.iplatform.module.aicrm.controller.admin.customeraccountwealth.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.time.LocalDate;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 客户理财账户信息表（零售+对公共用）分页 Request VO")
@Data
public class CustomerAccountWealthPageReqVO extends PageParam {

    @Schema(description = "客户ID（关联 crm_customer 主表）", example = "9632")
    private Long customerId;

    @Schema(description = "理财账号")
    private String accountNo;

    @Schema(description = "理财产品代码")
    private String productCode;

    @Schema(description = "理财产品名称", example = "王五")
    private String productName;

    @Schema(description = "户名", example = "王五")
    private String accountName;

    @Schema(description = "开户日期（购买日期）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] openDate;

    @Schema(description = "销户日期（赎回/到期日期）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] closeDate;

    @Schema(description = "账户状态（字典: aicrm_wealth_account_status，holding=持有中，matured=已到期，redeemed=已赎回）", example = "2")
    private String accountStatus;

    @Schema(description = "理财类型（字典: aicrm_wealth_product_type，fixed=固定收益，floating=浮动收益，structured=结构性）", example = "1")
    private String productType;

    @Schema(description = "风险等级（字典: aicrm_risk_level，R1-R5）")
    private String riskLevel;

    @Schema(description = "预期收益率（年化%）")
    private BigDecimal expectedReturnRate;

    @Schema(description = "实际收益率（年化%）")
    private BigDecimal actualReturnRate;

    @Schema(description = "理财期限（如：90天、180天、1年）")
    private String wealthTerm;

    @Schema(description = "币种", example = "1")
    private String currencyType;

    @Schema(description = "购买金额")
    private BigDecimal purchaseAmount;

    @Schema(description = "当前市值")
    private BigDecimal currentValue;

    @Schema(description = "累计收益")
    private BigDecimal accumulatedIncome;

    @Schema(description = "持有份额/余额")
    private BigDecimal balance;

    @Schema(description = "起息日")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] valueDate;

    @Schema(description = "到期日")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] matureDate;

    @Schema(description = "下次开放日（开放式理财）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] nextOpenDate;

    @Schema(description = "销售机构ID", example = "27002")
    private Long deptId;

    @Schema(description = "销售机构名称", example = "李四")
    private String deptName;

    @Schema(description = "理财顾问用户ID", example = "25208")
    private Long managerUserId;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}