package com.ynet.iplatform.module.aicrm.controller.admin.customerclaim.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 客户认领申请 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CustomerClaimApplicationRespVO {

    @Schema(description = "申请ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("申请ID")
    private Long id;

    @Schema(description = "客户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1001")
    @ExcelProperty("客户ID")
    private Long customerId;

    @Schema(description = "客户名称", example = "张三")
    @ExcelProperty("客户名称")
    private String customerName;

    @Schema(description = "申请人(客户经理)ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2001")
    @ExcelProperty("申请人ID")
    private Long applicantUserId;

    @Schema(description = "申请人姓名", example = "李四")
    @ExcelProperty("申请人姓名")
    private String applicantUserName;

    @Schema(description = "申请人部门ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3001")
    @ExcelProperty("申请人部门ID")
    private Long applicantDeptId;

    @Schema(description = "申请人部门名称", example = "营业部")
    @ExcelProperty("申请人部门名称")
    private String applicantDeptName;

    @Schema(description = "申请日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("申请日期")
    private LocalDate applyDate;

    @Schema(description = "申请理由", example = "该客户属于我的潜在客户")
    @ExcelProperty("申请理由")
    private String applyReason;

    @Schema(description = "BPM流程实例ID", example = "12345")
    @ExcelProperty("流程实例ID")
    private String processInstanceId;

    @Schema(description = "流程状态（1=审批中，2=已通过，3=已拒绝，4=已取消）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("流程状态")
    private Integer processStatus;

    @Schema(description = "流程状态描述", example = "审批中")
    @ExcelProperty("流程状态描述")
    private String processStatusStr;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
