package cn.iocoder.yudao.module.aicrm.controller.admin.customeraccounttrust.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.time.LocalDate;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 客户信托账户信息表（零售+对公共用） Response VO")
@Data
@ExcelIgnoreUnannotated
public class CustomerAccountTrustRespVO {

    @Schema(description = "信托账户主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "28386")
    @ExcelProperty("信托账户主键")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer 主表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "26368")
    @ExcelProperty("客户ID（关联 crm_customer 主表）")
    private Long customerId;

    @Schema(description = "信托账号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("信托账号")
    private String accountNo;

    @Schema(description = "信托合同号")
    @ExcelProperty("信托合同号")
    private String trustContractNo;

    @Schema(description = "信托产品名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("信托产品名称")
    private String productName;

    @Schema(description = "委托人姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("委托人姓名")
    private String accountName;

    @Schema(description = "成立日期（开户日期）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("成立日期（开户日期）")
    private LocalDate openDate;

    @Schema(description = "终止日期（销户日期）")
    @ExcelProperty("终止日期（销户日期）")
    private LocalDate closeDate;

    @Schema(description = "账户状态（字典: aicrm_trust_status，valid=有效，matured=已到期，terminated=已终止）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("账户状态（字典: aicrm_trust_status，valid=有效，matured=已到期，terminated=已终止）")
    private String accountStatus;

    @Schema(description = "信托类型（字典: aicrm_trust_type，single=单一信托，collective=集合信托，property=财产权信托）", example = "2")
    @ExcelProperty("信托类型（字典: aicrm_trust_type，single=单一信托，collective=集合信托，property=财产权信托）")
    private String trustType;

    @Schema(description = "信托公司")
    @ExcelProperty("信托公司")
    private String trustCompany;

    @Schema(description = "信托目的")
    @ExcelProperty("信托目的")
    private String trustPurpose;

    @Schema(description = "预期收益率（年化%）")
    @ExcelProperty("预期收益率（年化%）")
    private BigDecimal expectedReturnRate;

    @Schema(description = "信托期限（如：2年、3年、5年）")
    @ExcelProperty("信托期限（如：2年、3年、5年）")
    private String trustTerm;

    @Schema(description = "币种", example = "1")
    @ExcelProperty("币种")
    private String currencyType;

    @Schema(description = "信托金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("信托金额")
    private BigDecimal trustAmount;

    @Schema(description = "已支付金额")
    @ExcelProperty("已支付金额")
    private BigDecimal paidAmount;

    @Schema(description = "当前价值", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("当前价值")
    private BigDecimal currentValue;

    @Schema(description = "累计收益")
    @ExcelProperty("累计收益")
    private BigDecimal accumulatedIncome;

    @Schema(description = "账户余额", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("账户余额")
    private BigDecimal balance;

    @Schema(description = "受益人姓名", example = "赵六")
    @ExcelProperty("受益人姓名")
    private String beneficiaryName;

    @Schema(description = "与委托人关系")
    @ExcelProperty("与委托人关系")
    private String beneficiaryRelation;

    @Schema(description = "生效日期")
    @ExcelProperty("生效日期")
    private LocalDate effectiveDate;

    @Schema(description = "到期日")
    @ExcelProperty("到期日")
    private LocalDate matureDate;

    @Schema(description = "下次分配日")
    @ExcelProperty("下次分配日")
    private LocalDate nextDistributionDate;

    @Schema(description = "销售机构ID", example = "3215")
    @ExcelProperty("销售机构ID")
    private Long deptId;

    @Schema(description = "销售机构名称", example = "李四")
    @ExcelProperty("销售机构名称")
    private String deptName;

    @Schema(description = "信托顾问用户ID", example = "19325")
    @ExcelProperty("信托顾问用户ID")
    private Long managerUserId;

    @Schema(description = "备注", example = "随便")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}