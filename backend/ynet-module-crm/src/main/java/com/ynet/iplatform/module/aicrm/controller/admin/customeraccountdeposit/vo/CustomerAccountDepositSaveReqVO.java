package com.ynet.iplatform.module.aicrm.controller.admin.customeraccountdeposit.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(description = "管理后台 - 客户存款账户信息表（零售+对公共用）新增/修改 Request VO")
@Data
public class CustomerAccountDepositSaveReqVO {

    @Schema(description = "存款账户主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "27255")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer 主表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "25492")
    @NotNull(message = "客户ID（关联 crm_customer 主表）不能为空")
    private Long customerId;

    @Schema(description = "账号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "账号不能为空")
    private String accountNo;

    @Schema(description = "产品名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "产品名称不能为空")
    private String productName;

    @Schema(description = "户名", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "户名不能为空")
    private String accountName;

    @Schema(description = "开户日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "开户日期不能为空")
    private LocalDate openDate;

    @Schema(description = "销户日期")
    private LocalDate closeDate;

    @Schema(description = "账户状态（字典: aicrm_account_status，normal=正常，closed=销户，frozen=冻结）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "账户状态（字典: aicrm_account_status，normal=正常，closed=销户，frozen=冻结）不能为空")
    private String accountStatus;

    @Schema(description = "利率（%）")
    private BigDecimal interestRate;

    @Schema(description = "存期（如：1年、6个月、3个月、活期）")
    private String depositTerm;

    @Schema(description = "余额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "余额不能为空")
    private BigDecimal balance;

    @Schema(description = "协议号")
    private String agrNo;

    @Schema(description = "产品ID", example = "30718")
    private String productId;

    @Schema(description = "卡号")
    private String cardNo;

    @Schema(description = "存款类型（1=活期，2=定期）", example = "2")
    private String depositType;

    @Schema(description = "币种（字典: aicrm_currency_type）", example = "1")
    private String currencyType;

    @Schema(description = "原始金额（开户金额）")
    private BigDecimal originalAmount;

    @Schema(description = "到期日")
    private LocalDate matureDate;

    @Schema(description = "起息日")
    private LocalDate startInterestDate;

    @Schema(description = "开户机构ID（关联 system_dept.id）", example = "32726")
    private Long deptId;

    @Schema(description = "开户机构名称", example = "赵六")
    private String deptName;

    @Schema(description = "客户经理用户ID（关联 system_users.id）", example = "20539")
    private Long managerUserId;

    @Schema(description = "月日均余额")
    private BigDecimal monthAvgBalance;

    @Schema(description = "季日均余额")
    private BigDecimal quarterAvgBalance;

    @Schema(description = "年日均余额")
    private BigDecimal yearAvgBalance;

    @Schema(description = "月累计转入")
    private BigDecimal monthTotalIn;

    @Schema(description = "月累计转出")
    private BigDecimal monthTotalOut;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

}