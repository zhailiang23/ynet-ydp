package cn.iocoder.yudao.module.aicrm.controller.admin.customerguaranteepledge.vo;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 客户质押物信息表（零售+对公共用） Response VO")
@Data
@ExcelIgnoreUnannotated
public class CustomerGuaranteePledgeRespVO {

    @Schema(description = "质押物主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "16767")
    @ExcelProperty("质押物主键")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer 主表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1168")
    @ExcelProperty("客户ID（关联 crm_customer 主表）")
    private Long customerId;

    @Schema(description = "授信ID（关联 crm_customer_credit.id）", example = "19181")
    @ExcelProperty("授信ID（关联 crm_customer_credit.id）")
    private Long creditId;

    @Schema(description = "押品编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("押品编号")
    private String collateralNo;

    @Schema(description = "押品类型（字典: aicrm_pledge_type）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("押品类型（字典: aicrm_pledge_type）")
    private String collateralType;

    @Schema(description = "担保类型（字典: aicrm_guarantee_method，pledge=质押）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("担保类型（字典: aicrm_guarantee_method，pledge=质押）")
    private String guaranteeType;

    @Schema(description = "质押人证件类型（字典: aicrm_identity_type）", example = "2")
    @ExcelProperty("质押人证件类型（字典: aicrm_identity_type）")
    private String pledgorIdType;

    @Schema(description = "质押人证件号码（加密）")
    @ExcelProperty("质押人证件号码（加密）")
    private String pledgorIdNo;

    @Schema(description = "质押人姓名/名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("质押人姓名/名称")
    private String pledgorName;

    @Schema(description = "质押人类型（字典: aicrm_guarantor_type，person=个人，company=企业）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("质押人类型（字典: aicrm_guarantor_type，person=个人，company=企业）")
    private String pledgorType;

    @Schema(description = "与被担保人关系（字典: aicrm_relation_type）")
    @ExcelProperty("与被担保人关系（字典: aicrm_relation_type）")
    private String relationWithBorrower;

    @Schema(description = "担保本金限权金额（万元）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("担保本金限权金额（万元）")
    private BigDecimal guaranteeAmount;

    @Schema(description = "质押率（%）")
    @ExcelProperty("质押率（%）")
    private BigDecimal pledgeRate;

    @Schema(description = "质押物名称", example = "王五")
    @ExcelProperty("质押物名称")
    private String collateralName;

    @Schema(description = "质押物价值（万元）")
    @ExcelProperty("质押物价值（万元）")
    private BigDecimal collateralValue;

    @Schema(description = "质押状态（字典: aicrm_pledge_status，effective=有效，released=已解押，invalid=无效）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("质押状态（字典: aicrm_pledge_status，effective=有效，released=已解押，invalid=无效）")
    private String pledgeStatus;

    @Schema(description = "质押登记日期")
    @ExcelProperty("质押登记日期")
    private LocalDate pledgeDate;

    @Schema(description = "解押日期")
    @ExcelProperty("解押日期")
    private LocalDate releaseDate;

    @Schema(description = "管理机构ID（关联 system_dept.id）", example = "1335")
    @ExcelProperty("管理机构ID（关联 system_dept.id）")
    private Long managementBranchId;

    @Schema(description = "管理机构名称", example = "赵六")
    @ExcelProperty("管理机构名称")
    private String managementBranchName;

    @Schema(description = "备注", example = "你猜")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}