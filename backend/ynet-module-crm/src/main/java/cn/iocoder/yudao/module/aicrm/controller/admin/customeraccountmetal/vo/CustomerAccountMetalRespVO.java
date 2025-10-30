package cn.iocoder.yudao.module.aicrm.controller.admin.customeraccountmetal.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.time.LocalDate;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 客户贵金属账户信息表（零售+对公共用） Response VO")
@Data
@ExcelIgnoreUnannotated
public class CustomerAccountMetalRespVO {

    @Schema(description = "贵金属账户主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "2315")
    @ExcelProperty("贵金属账户主键")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer 主表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "13055")
    @ExcelProperty("客户ID（关联 crm_customer 主表）")
    private Long customerId;

    @Schema(description = "贵金属账号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("贵金属账号")
    private String accountNo;

    @Schema(description = "产品名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("产品名称")
    private String productName;

    @Schema(description = "户名", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("户名")
    private String accountName;

    @Schema(description = "开户日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("开户日期")
    private LocalDate openDate;

    @Schema(description = "销户日期")
    @ExcelProperty("销户日期")
    private LocalDate closeDate;

    @Schema(description = "账户状态（字典: aicrm_metal_account_status，normal=正常，closed=已销户）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("账户状态（字典: aicrm_metal_account_status，normal=正常，closed=已销户）")
    private String accountStatus;

    @Schema(description = "贵金属类型（字典: aicrm_metal_type，gold=黄金，silver=白银，platinum=铂金，palladium=钯金）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("贵金属类型（字典: aicrm_metal_type，gold=黄金，silver=白银，platinum=铂金，palladium=钯金）")
    private String metalType;

    @Schema(description = "产品类别（字典: aicrm_metal_category，physical=实物，paper=纸黄金，td=T+D，futures=期货）")
    @ExcelProperty("产品类别（字典: aicrm_metal_category，physical=实物，paper=纸黄金，td=T+D，futures=期货）")
    private String metalCategory;

    @Schema(description = "产品代码")
    @ExcelProperty("产品代码")
    private String productCode;

    @Schema(description = "持有重量（克）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("持有重量（克）")
    private BigDecimal holdingWeight;

    @Schema(description = "平均成本（元/克）")
    @ExcelProperty("平均成本（元/克）")
    private BigDecimal averageCost;

    @Schema(description = "当前价格（元/克）", example = "18815")
    @ExcelProperty("当前价格（元/克）")
    private BigDecimal currentPrice;

    @Schema(description = "当前市值（元）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("当前市值（元）")
    private BigDecimal currentValue;

    @Schema(description = "累计收益（元）")
    @ExcelProperty("累计收益（元）")
    private BigDecimal accumulatedIncome;

    @Schema(description = "收益率（%）")
    @ExcelProperty("收益率（%）")
    private BigDecimal profitRate;

    @Schema(description = "账户余额（元）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("账户余额（元）")
    private BigDecimal balance;

    @Schema(description = "累计买入重量（克）")
    @ExcelProperty("累计买入重量（克）")
    private BigDecimal totalBuyWeight;

    @Schema(description = "累计买入金额（元）")
    @ExcelProperty("累计买入金额（元）")
    private BigDecimal totalBuyAmount;

    @Schema(description = "累计卖出重量（克）")
    @ExcelProperty("累计卖出重量（克）")
    private BigDecimal totalSellWeight;

    @Schema(description = "累计卖出金额（元）")
    @ExcelProperty("累计卖出金额（元）")
    private BigDecimal totalSellAmount;

    @Schema(description = "币种", example = "2")
    @ExcelProperty("币种")
    private String currencyType;

    @Schema(description = "开户机构ID", example = "3063")
    @ExcelProperty("开户机构ID")
    private Long deptId;

    @Schema(description = "开户机构名称", example = "王五")
    @ExcelProperty("开户机构名称")
    private String deptName;

    @Schema(description = "客户经理用户ID", example = "4662")
    @ExcelProperty("客户经理用户ID")
    private Long managerUserId;

    @Schema(description = "备注", example = "你猜")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}