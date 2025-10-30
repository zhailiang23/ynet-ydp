package cn.iocoder.yudao.module.aicrm.controller.admin.customeraccountmetal.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(description = "管理后台 - 客户贵金属账户信息表（零售+对公共用）新增/修改 Request VO")
@Data
public class CustomerAccountMetalSaveReqVO {

    @Schema(description = "贵金属账户主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "2315")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer 主表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "13055")
    @NotNull(message = "客户ID（关联 crm_customer 主表）不能为空")
    private Long customerId;

    @Schema(description = "贵金属账号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "贵金属账号不能为空")
    private String accountNo;

    @Schema(description = "产品名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "产品名称不能为空")
    private String productName;

    @Schema(description = "户名", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "户名不能为空")
    private String accountName;

    @Schema(description = "开户日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "开户日期不能为空")
    private LocalDate openDate;

    @Schema(description = "销户日期")
    private LocalDate closeDate;

    @Schema(description = "账户状态（字典: aicrm_metal_account_status，normal=正常，closed=已销户）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "账户状态（字典: aicrm_metal_account_status，normal=正常，closed=已销户）不能为空")
    private String accountStatus;

    @Schema(description = "贵金属类型（字典: aicrm_metal_type，gold=黄金，silver=白银，platinum=铂金，palladium=钯金）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "贵金属类型（字典: aicrm_metal_type，gold=黄金，silver=白银，platinum=铂金，palladium=钯金）不能为空")
    private String metalType;

    @Schema(description = "产品类别（字典: aicrm_metal_category，physical=实物，paper=纸黄金，td=T+D，futures=期货）")
    private String metalCategory;

    @Schema(description = "产品代码")
    private String productCode;

    @Schema(description = "持有重量（克）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "持有重量（克）不能为空")
    private BigDecimal holdingWeight;

    @Schema(description = "平均成本（元/克）")
    private BigDecimal averageCost;

    @Schema(description = "当前价格（元/克）", example = "18815")
    private BigDecimal currentPrice;

    @Schema(description = "当前市值（元）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "当前市值（元）不能为空")
    private BigDecimal currentValue;

    @Schema(description = "累计收益（元）")
    private BigDecimal accumulatedIncome;

    @Schema(description = "收益率（%）")
    private BigDecimal profitRate;

    @Schema(description = "账户余额（元）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "账户余额（元）不能为空")
    private BigDecimal balance;

    @Schema(description = "累计买入重量（克）")
    private BigDecimal totalBuyWeight;

    @Schema(description = "累计买入金额（元）")
    private BigDecimal totalBuyAmount;

    @Schema(description = "累计卖出重量（克）")
    private BigDecimal totalSellWeight;

    @Schema(description = "累计卖出金额（元）")
    private BigDecimal totalSellAmount;

    @Schema(description = "币种", example = "2")
    private String currencyType;

    @Schema(description = "开户机构ID", example = "3063")
    private Long deptId;

    @Schema(description = "开户机构名称", example = "王五")
    private String deptName;

    @Schema(description = "客户经理用户ID", example = "4662")
    private Long managerUserId;

    @Schema(description = "备注", example = "你猜")
    private String remark;

}