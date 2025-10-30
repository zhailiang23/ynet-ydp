package cn.iocoder.yudao.module.aicrm.controller.admin.customeraccountloan.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.time.LocalDate;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 客户贷款账户信息表（零售+对公共用） Response VO")
@Data
@ExcelIgnoreUnannotated
public class CustomerAccountLoanRespVO {

    @Schema(description = "贷款账户主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "12867")
    @ExcelProperty("贷款账户主键")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer 主表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "6982")
    @ExcelProperty("客户ID（关联 crm_customer 主表）")
    private Long customerId;

    @Schema(description = "贷款账号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("贷款账号")
    private String accountNo;

    @Schema(description = "合同号")
    @ExcelProperty("合同号")
    private String contractNo;

    @Schema(description = "协议号")
    @ExcelProperty("协议号")
    private String agrNo;

    @Schema(description = "贷款产品名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("贷款产品名称")
    private String productName;

    @Schema(description = "产品ID", example = "1853")
    @ExcelProperty("产品ID")
    private String productId;

    @Schema(description = "借款人姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("借款人姓名")
    private String accountName;

    @Schema(description = "放款日期（开户日期）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("放款日期（开户日期）")
    private LocalDate openDate;

    @Schema(description = "结清日期（销户日期）")
    @ExcelProperty("结清日期（销户日期）")
    private LocalDate closeDate;

    @Schema(description = "账户状态（字典: aicrm_loan_account_status，normal=正常，overdue=逾期，settled=已结清，writeoff=核销）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("账户状态（字典: aicrm_loan_account_status，normal=正常，overdue=逾期，settled=已结清，writeoff=核销）")
    private String accountStatus;

    @Schema(description = "合同金额（授信额度）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("合同金额（授信额度）")
    private BigDecimal contractAmount;

    @Schema(description = "贷款金额（实际发放金额）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("贷款金额（实际发放金额）")
    private BigDecimal loanAmount;

    @Schema(description = "贷款余额", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("贷款余额")
    private BigDecimal balance;

    @Schema(description = "币种（字典: aicrm_currency_type）", example = "2")
    @ExcelProperty("币种（字典: aicrm_currency_type）")
    private String currencyType;

    @Schema(description = "贷款利率（年化%）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("贷款利率（年化%）")
    private BigDecimal interestRate;

    @Schema(description = "贷款期限（月）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("贷款期限（月）")
    private BigDecimal loanTerm;

    @Schema(description = "期限单位（字典: aicrm_loan_term_unit，day=天，month=月，year=年）")
    @ExcelProperty("期限单位（字典: aicrm_loan_term_unit，day=天，month=月，year=年）")
    private String loanTermUnit;

    @Schema(description = "到期日", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("到期日")
    private LocalDate matureDate;

    @Schema(description = "还款方式（字典: aicrm_repayment_mode）")
    @ExcelProperty("还款方式（字典: aicrm_repayment_mode）")
    private String repaymentMode;

    @Schema(description = "贷款用途")
    @ExcelProperty("贷款用途")
    private String loanPurpose;

    @Schema(description = "贷款类型（字典: aicrm_loan_type）", example = "2")
    @ExcelProperty("贷款类型（字典: aicrm_loan_type）")
    private String loanType;

    @Schema(description = "担保方式（字典: aicrm_guarantee_type）", example = "2")
    @ExcelProperty("担保方式（字典: aicrm_guarantee_type）")
    private String guaranteeType;

    @Schema(description = "业务类型", example = "2")
    @ExcelProperty("业务类型")
    private String businessType;

    @Schema(description = "五级分类（字典: aicrm_five_level_class，normal=正常，concern=关注，secondary=次级，doubtful=可疑，loss=损失）")
    @ExcelProperty("五级分类（字典: aicrm_five_level_class，normal=正常，concern=关注，secondary=次级，doubtful=可疑，loss=损失）")
    private String fiveLevelClass;

    @Schema(description = "逾期天数")
    @ExcelProperty("逾期天数")
    private Integer overdueDays;

    @Schema(description = "逾期本金")
    @ExcelProperty("逾期本金")
    private BigDecimal overduePrincipal;

    @Schema(description = "逾期利息")
    @ExcelProperty("逾期利息")
    private BigDecimal overdueInterest;

    @Schema(description = "累计逾期次数")
    @ExcelProperty("累计逾期次数")
    private Integer overdueTimes;

    @Schema(description = "放款机构ID（关联 system_dept.id）", example = "27527")
    @ExcelProperty("放款机构ID（关联 system_dept.id）")
    private Long deptId;

    @Schema(description = "放款机构名称", example = "张三")
    @ExcelProperty("放款机构名称")
    private String deptName;

    @Schema(description = "客户经理用户ID（关联 system_users.id）", example = "30648")
    @ExcelProperty("客户经理用户ID（关联 system_users.id）")
    private Long managerUserId;

    @Schema(description = "月日均余额")
    @ExcelProperty("月日均余额")
    private BigDecimal monthAvgBalance;

    @Schema(description = "季日均余额")
    @ExcelProperty("季日均余额")
    private BigDecimal quarterAvgBalance;

    @Schema(description = "年日均余额")
    @ExcelProperty("年日均余额")
    private BigDecimal yearAvgBalance;

    @Schema(description = "累计还款金额")
    @ExcelProperty("累计还款金额")
    private BigDecimal totalRepaidAmount;

    @Schema(description = "累计还款利息")
    @ExcelProperty("累计还款利息")
    private BigDecimal totalRepaidInterest;

    @Schema(description = "备注", example = "你说的对")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}