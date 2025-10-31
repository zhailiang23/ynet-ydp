package cn.iocoder.yudao.module.aicrm.controller.admin.customerreturnapplication.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 客户退回申请 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CustomerReturnApplicationRespVO {

    @Schema(description = "申请ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "21813")
    @ExcelProperty("申请ID")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer）", requiredMode = Schema.RequiredMode.REQUIRED, example = "5368")
    @ExcelProperty("客户ID（关联 crm_customer）")
    private Long customerId;

    @Schema(description = "申请人(客户经理)ID（关联 system_users.id）", requiredMode = Schema.RequiredMode.REQUIRED, example = "13476")
    @ExcelProperty("申请人(客户经理)ID（关联 system_users.id）")
    private Long applicantUserId;

    @Schema(description = "退回给主管ID（关联 system_users.id）", requiredMode = Schema.RequiredMode.REQUIRED, example = "14892")
    @ExcelProperty("退回给主管ID（关联 system_users.id）")
    private Long returnToUserId;

    @Schema(description = "申请日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("申请日期")
    private LocalDate applyDate;

    @Schema(description = "退回原因", example = "不好")
    @ExcelProperty("退回原因")
    private String returnReason;

    @Schema(description = "BPM流程实例ID", example = "18304")
    @ExcelProperty("BPM流程实例ID")
    private String processInstanceId;

    @Schema(description = "流程状态（1=审批中，2=已通过，3=已拒绝，4=已取消）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("流程状态（1=审批中，2=已通过，3=已拒绝，4=已取消）")
    private Integer processStatus;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}