package cn.iocoder.yudao.module.aicrm.controller.admin.customeraccountloan.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(description = "管理后台 - 客户贷款账户信息表（零售+对公共用）新增/修改 Request VO")
@Data
public class CustomerAccountLoanSaveReqVO {

    @Schema(description = "贷款账户主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "12867")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer 主表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "6982")
    @NotNull(message = "客户ID（关联 crm_customer 主表）不能为空")
    private Long customerId;

    @Schema(description = "贷款账号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "贷款账号不能为空")
    private String accountNo;

    @Schema(description = "合同号")
    private String contractNo;

    @Schema(description = "协议号")
    private String agrNo;

    @Schema(description = "贷款产品名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "贷款产品名称不能为空")
    private String productName;

    @Schema(description = "产品ID", example = "1853")
    private String productId;

    @Schema(description = "借款人姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "借款人姓名不能为空")
    private String accountName;

    @Schema(description = "放款日期（开户日期）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "放款日期（开户日期）不能为空")
    private LocalDate openDate;

    @Schema(description = "结清日期（销户日期）")
    private LocalDate closeDate;

    @Schema(description = "账户状态（字典: aicrm_loan_account_status，normal=正常，overdue=逾期，settled=已结清，writeoff=核销）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "账户状态（字典: aicrm_loan_account_status，normal=正常，overdue=逾期，settled=已结清，writeoff=核销）不能为空")
    private String accountStatus;

    @Schema(description = "合同金额（授信额度）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "合同金额（授信额度）不能为空")
    private BigDecimal contractAmount;

    @Schema(description = "贷款金额（实际发放金额）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "贷款金额（实际发放金额）不能为空")
    private BigDecimal loanAmount;

    @Schema(description = "贷款余额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "贷款余额不能为空")
    private BigDecimal balance;

    @Schema(description = "币种（字典: aicrm_currency_type）", example = "2")
    private String currencyType;

    @Schema(description = "贷款利率（年化%）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "贷款利率（年化%）不能为空")
    private BigDecimal interestRate;

    @Schema(description = "贷款期限（月）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "贷款期限（月）不能为空")
    private BigDecimal loanTerm;

    @Schema(description = "期限单位（字典: aicrm_loan_term_unit，day=天，month=月，year=年）")
    private String loanTermUnit;

    @Schema(description = "到期日", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "到期日不能为空")
    private LocalDate matureDate;

    @Schema(description = "还款方式（字典: aicrm_repayment_mode）")
    private String repaymentMode;

    @Schema(description = "贷款用途")
    private String loanPurpose;

    @Schema(description = "贷款类型（字典: aicrm_loan_type）", example = "2")
    private String loanType;

    @Schema(description = "担保方式（字典: aicrm_guarantee_type）", example = "2")
    private String guaranteeType;

    @Schema(description = "业务类型", example = "2")
    private String businessType;

    @Schema(description = "五级分类（字典: aicrm_five_level_class，normal=正常，concern=关注，secondary=次级，doubtful=可疑，loss=损失）")
    private String fiveLevelClass;

    @Schema(description = "逾期天数")
    private Integer overdueDays;

    @Schema(description = "逾期本金")
    private BigDecimal overduePrincipal;

    @Schema(description = "逾期利息")
    private BigDecimal overdueInterest;

    @Schema(description = "累计逾期次数")
    private Integer overdueTimes;

    @Schema(description = "放款机构ID（关联 system_dept.id）", example = "27527")
    private Long deptId;

    @Schema(description = "放款机构名称", example = "张三")
    private String deptName;

    @Schema(description = "客户经理用户ID（关联 system_users.id）", example = "30648")
    private Long managerUserId;

    @Schema(description = "月日均余额")
    private BigDecimal monthAvgBalance;

    @Schema(description = "季日均余额")
    private BigDecimal quarterAvgBalance;

    @Schema(description = "年日均余额")
    private BigDecimal yearAvgBalance;

    @Schema(description = "累计还款金额")
    private BigDecimal totalRepaidAmount;

    @Schema(description = "累计还款利息")
    private BigDecimal totalRepaidInterest;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

}