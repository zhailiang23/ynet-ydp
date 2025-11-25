package com.ynet.iplatform.module.aicrm.controller.admin.customeraccounttrust.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(description = "管理后台 - 客户信托账户信息表（零售+对公共用）新增/修改 Request VO")
@Data
public class CustomerAccountTrustSaveReqVO {

    @Schema(description = "信托账户主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "28386")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer 主表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "26368")
    @NotNull(message = "客户ID（关联 crm_customer 主表）不能为空")
    private Long customerId;

    @Schema(description = "信托账号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "信托账号不能为空")
    private String accountNo;

    @Schema(description = "信托合同号")
    private String trustContractNo;

    @Schema(description = "信托产品名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "信托产品名称不能为空")
    private String productName;

    @Schema(description = "委托人姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "委托人姓名不能为空")
    private String accountName;

    @Schema(description = "成立日期（开户日期）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "成立日期（开户日期）不能为空")
    private LocalDate openDate;

    @Schema(description = "终止日期（销户日期）")
    private LocalDate closeDate;

    @Schema(description = "账户状态（字典: aicrm_trust_status，valid=有效，matured=已到期，terminated=已终止）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "账户状态（字典: aicrm_trust_status，valid=有效，matured=已到期，terminated=已终止）不能为空")
    private String accountStatus;

    @Schema(description = "信托类型（字典: aicrm_trust_type，single=单一信托，collective=集合信托，property=财产权信托）", example = "2")
    private String trustType;

    @Schema(description = "信托公司")
    private String trustCompany;

    @Schema(description = "信托目的")
    private String trustPurpose;

    @Schema(description = "预期收益率（年化%）")
    private BigDecimal expectedReturnRate;

    @Schema(description = "信托期限（如：2年、3年、5年）")
    private String trustTerm;

    @Schema(description = "币种", example = "1")
    private String currencyType;

    @Schema(description = "信托金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "信托金额不能为空")
    private BigDecimal trustAmount;

    @Schema(description = "已支付金额")
    private BigDecimal paidAmount;

    @Schema(description = "当前价值", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "当前价值不能为空")
    private BigDecimal currentValue;

    @Schema(description = "累计收益")
    private BigDecimal accumulatedIncome;

    @Schema(description = "账户余额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "账户余额不能为空")
    private BigDecimal balance;

    @Schema(description = "受益人姓名", example = "赵六")
    private String beneficiaryName;

    @Schema(description = "与委托人关系")
    private String beneficiaryRelation;

    @Schema(description = "生效日期")
    private LocalDate effectiveDate;

    @Schema(description = "到期日")
    private LocalDate matureDate;

    @Schema(description = "下次分配日")
    private LocalDate nextDistributionDate;

    @Schema(description = "销售机构ID", example = "3215")
    private Long deptId;

    @Schema(description = "销售机构名称", example = "李四")
    private String deptName;

    @Schema(description = "信托顾问用户ID", example = "19325")
    private Long managerUserId;

    @Schema(description = "备注", example = "随便")
    private String remark;

}