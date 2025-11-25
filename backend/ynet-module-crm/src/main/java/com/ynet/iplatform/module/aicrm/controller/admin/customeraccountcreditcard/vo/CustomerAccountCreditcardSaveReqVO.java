package com.ynet.iplatform.module.aicrm.controller.admin.customeraccountcreditcard.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(description = "管理后台 - 客户信用卡账户信息表（仅限零售客户）新增/修改 Request VO")
@Data
public class CustomerAccountCreditcardSaveReqVO {

    @Schema(description = "信用卡账户主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "15181")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer 主表，仅限零售客户）", requiredMode = Schema.RequiredMode.REQUIRED, example = "6373")
    @NotNull(message = "客户ID（关联 crm_customer 主表，仅限零售客户）不能为空")
    private Long customerId;

    @Schema(description = "信用卡账号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "信用卡账号不能为空")
    private String accountNo;

    @Schema(description = "信用卡号（加密存储）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "信用卡号（加密存储）不能为空")
    private String cardNo;

    @Schema(description = "信用卡产品名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "信用卡产品名称不能为空")
    private String productName;

    @Schema(description = "持卡人姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "持卡人姓名不能为空")
    private String accountName;

    @Schema(description = "开卡日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "开卡日期不能为空")
    private LocalDate openDate;

    @Schema(description = "销卡日期")
    private LocalDate closeDate;

    @Schema(description = "账户状态（字典: aicrm_creditcard_status，normal=正常，frozen=冻结，overdue=逾期，closed=已销卡）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "账户状态（字典: aicrm_creditcard_status，normal=正常，frozen=冻结，overdue=逾期，closed=已销卡）不能为空")
    private String accountStatus;

    @Schema(description = "卡片类型（字典: aicrm_card_type，standard=普卡，gold=金卡，platinum=白金卡，diamond=钻石卡）", example = "1")
    private String cardType;

    @Schema(description = "卡等级")
    private String cardLevel;

    @Schema(description = "卡组织（字典: aicrm_card_brand，visa=VISA，mastercard=万事达，unionpay=银联）")
    private String cardBrand;

    @Schema(description = "是否主卡（0=附属卡，1=主卡）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否主卡（0=附属卡，1=主卡）不能为空")
    private Boolean isMainCard;

    @Schema(description = "主卡卡号（附属卡关联）")
    private String mainCardNo;

    @Schema(description = "币种", example = "2")
    private String currencyType;

    @Schema(description = "信用额度", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "信用额度不能为空")
    private BigDecimal creditLimit;

    @Schema(description = "可用额度", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "可用额度不能为空")
    private BigDecimal availableLimit;

    @Schema(description = "临时额度")
    private BigDecimal temporaryLimit;

    @Schema(description = "取现额度")
    private BigDecimal cashLimit;

    @Schema(description = "已用额度")
    private BigDecimal usedAmount;

    @Schema(description = "当前欠款余额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "当前欠款余额不能为空")
    private BigDecimal balance;

    @Schema(description = "账单日（每月几号）")
    private Integer billingDay;

    @Schema(description = "还款日（每月几号）")
    private Integer paymentDueDay;

    @Schema(description = "本期账单金额")
    private BigDecimal currentBillAmount;

    @Schema(description = "最低还款额")
    private BigDecimal minPaymentAmount;

    @Schema(description = "未还金额")
    private BigDecimal unpaidAmount;

    @Schema(description = "上次还款日期")
    private LocalDate lastPaymentDate;

    @Schema(description = "上次还款金额")
    private BigDecimal lastPaymentAmount;

    @Schema(description = "逾期天数")
    private Integer overdueDays;

    @Schema(description = "逾期金额")
    private BigDecimal overdueAmount;

    @Schema(description = "逾期利息")
    private BigDecimal overdueInterest;

    @Schema(description = "累计逾期次数")
    private Integer overdueTimes;

    @Schema(description = "累计积分")
    private Long totalPoints;

    @Schema(description = "可用积分")
    private Long availablePoints;

    @Schema(description = "积分到期日")
    private LocalDate pointsExpireDate;

    @Schema(description = "卡片有效期")
    private LocalDate expireDate;

    @Schema(description = "激活日期")
    private LocalDate activateDate;

    @Schema(description = "最后交易日期")
    private LocalDate lastTransactionDate;

    @Schema(description = "发卡机构ID", example = "4937")
    private Long deptId;

    @Schema(description = "发卡机构名称", example = "赵六")
    private String deptName;

    @Schema(description = "客户经理用户ID", example = "4761")
    private Long managerUserId;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

}