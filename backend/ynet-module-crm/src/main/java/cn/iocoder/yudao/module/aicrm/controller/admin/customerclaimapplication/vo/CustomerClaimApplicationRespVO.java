package cn.iocoder.yudao.module.aicrm.controller.admin.customerclaimapplication.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 客户认领申请 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CustomerClaimApplicationRespVO {

    @Schema(description = "申请ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25435")
    @ExcelProperty("申请ID")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer）", requiredMode = Schema.RequiredMode.REQUIRED, example = "20942")
    @ExcelProperty("客户ID（关联 crm_customer）")
    private Long customerId;

    @Schema(description = "申请人(客户经理)ID（关联 system_users.id）", requiredMode = Schema.RequiredMode.REQUIRED, example = "25358")
    @ExcelProperty("申请人(客户经理)ID（关联 system_users.id）")
    private Long applicantUserId;

    @Schema(description = "申请人部门ID（关联 system_dept.id）", requiredMode = Schema.RequiredMode.REQUIRED, example = "4897")
    @ExcelProperty("申请人部门ID（关联 system_dept.id）")
    private Long applicantDeptId;

    @Schema(description = "申请日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("申请日期")
    private LocalDate applyDate;

    @Schema(description = "申请理由", example = "不喜欢")
    @ExcelProperty("申请理由")
    private String applyReason;

    @Schema(description = "BPM流程实例ID", example = "8038")
    @ExcelProperty("BPM流程实例ID")
    private String processInstanceId;

    @Schema(description = "流程状态（1=审批中，2=已通过，3=已拒绝，4=已取消）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("流程状态（1=审批中，2=已通过，3=已拒绝，4=已取消）")
    private Integer processStatus;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}