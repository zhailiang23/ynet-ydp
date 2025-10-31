package cn.iocoder.yudao.module.aicrm.controller.admin.customerclaimapplication.vo;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 客户认领申请新增/修改 Request VO")
@Data
public class CustomerClaimApplicationSaveReqVO {

    @Schema(description = "申请ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25435")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer）", requiredMode = Schema.RequiredMode.REQUIRED, example = "20942")
    @NotNull(message = "客户ID（关联 crm_customer）不能为空")
    private Long customerId;

    @Schema(description = "申请人(客户经理)ID（关联 system_users.id）", requiredMode = Schema.RequiredMode.REQUIRED, example = "25358")
    @NotNull(message = "申请人(客户经理)ID（关联 system_users.id）不能为空")
    private Long applicantUserId;

    @Schema(description = "申请人部门ID（关联 system_dept.id）", requiredMode = Schema.RequiredMode.REQUIRED, example = "4897")
    @NotNull(message = "申请人部门ID（关联 system_dept.id）不能为空")
    private Long applicantDeptId;

    @Schema(description = "申请日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "申请日期不能为空")
    private LocalDate applyDate;

    @Schema(description = "申请理由", example = "不喜欢")
    private String applyReason;

    @Schema(description = "BPM流程实例ID", example = "8038")
    private String processInstanceId;

    @Schema(description = "流程状态（1=审批中，2=已通过，3=已拒绝，4=已取消）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "流程状态（1=审批中，2=已通过，3=已拒绝，4=已取消）不能为空")
    private Integer processStatus;

}