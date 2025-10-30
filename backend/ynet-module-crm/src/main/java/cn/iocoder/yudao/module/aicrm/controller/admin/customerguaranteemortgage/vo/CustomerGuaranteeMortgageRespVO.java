package cn.iocoder.yudao.module.aicrm.controller.admin.customerguaranteemortgage.vo;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 客户抵押物信息表（零售+对公共用） Response VO")
@Data
@ExcelIgnoreUnannotated
public class CustomerGuaranteeMortgageRespVO {

    @Schema(description = "抵押物主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "10662")
    @ExcelProperty("抵押物主键")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer 主表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "19458")
    @ExcelProperty("客户ID（关联 crm_customer 主表）")
    private Long customerId;

    @Schema(description = "授信ID（关联 crm_customer_credit.id）", example = "29071")
    @ExcelProperty("授信ID（关联 crm_customer_credit.id）")
    private Long creditId;

    @Schema(description = "押品编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("押品编号")
    private String collateralNo;

    @Schema(description = "押品名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("押品名称")
    private String collateralName;

    @Schema(description = "押品类型（字典: aicrm_mortgage_type）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("押品类型（字典: aicrm_mortgage_type）")
    private String collateralType;

    @Schema(description = "权证号")
    @ExcelProperty("权证号")
    private String certificateNo;

    @Schema(description = "担保类型（字典: aicrm_guarantee_method，mortgage=抵押）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("担保类型（字典: aicrm_guarantee_method，mortgage=抵押）")
    private String guaranteeType;

    @Schema(description = "抵押人姓名/名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("抵押人姓名/名称")
    private String mortgagorName;

    @Schema(description = "抵押人类型（字典: aicrm_guarantor_type，person=个人，company=企业）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("抵押人类型（字典: aicrm_guarantor_type，person=个人，company=企业）")
    private String mortgagorType;

    @Schema(description = "与被担保人关系（字典: aicrm_relation_type）")
    @ExcelProperty("与被担保人关系（字典: aicrm_relation_type）")
    private String relationWithBorrower;

    @Schema(description = "担保本金限权金额（万元）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("担保本金限权金额（万元）")
    private BigDecimal guaranteeAmount;

    @Schema(description = "押品管理机构ID（关联 system_dept.id）", example = "2281")
    @ExcelProperty("押品管理机构ID（关联 system_dept.id）")
    private Long managementBranchId;

    @Schema(description = "押品管理机构名称", example = "芋艿")
    @ExcelProperty("押品管理机构名称")
    private String managementBranchName;

    @Schema(description = "抵押人证件类型（字典: aicrm_identity_type）", example = "2")
    @ExcelProperty("抵押人证件类型（字典: aicrm_identity_type）")
    private String mortgagorIdType;

    @Schema(description = "抵押人证件号码（加密）")
    @ExcelProperty("抵押人证件号码（加密）")
    private String mortgagorIdNo;

    @Schema(description = "抵押物地址")
    @ExcelProperty("抵押物地址")
    private String collateralAddress;

    @Schema(description = "抵押物面积（平方米）")
    @ExcelProperty("抵押物面积（平方米）")
    private BigDecimal collateralArea;

    @Schema(description = "评估价值（万元）")
    @ExcelProperty("评估价值（万元）")
    private BigDecimal evaluationValue;

    @Schema(description = "评估日期")
    @ExcelProperty("评估日期")
    private LocalDate evaluationDate;

    @Schema(description = "评估机构")
    @ExcelProperty("评估机构")
    private String evaluationAgency;

    @Schema(description = "抵押率（%）")
    @ExcelProperty("抵押率（%）")
    private BigDecimal mortgageRate;

    @Schema(description = "抵押状态（字典: aicrm_mortgage_status，effective=有效，released=已解押，invalid=无效）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("抵押状态（字典: aicrm_mortgage_status，effective=有效，released=已解押，invalid=无效）")
    private String mortgageStatus;

    @Schema(description = "抵押登记日期")
    @ExcelProperty("抵押登记日期")
    private LocalDate mortgageDate;

    @Schema(description = "解押日期")
    @ExcelProperty("解押日期")
    private LocalDate releaseDate;

    @Schema(description = "备注", example = "随便")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}