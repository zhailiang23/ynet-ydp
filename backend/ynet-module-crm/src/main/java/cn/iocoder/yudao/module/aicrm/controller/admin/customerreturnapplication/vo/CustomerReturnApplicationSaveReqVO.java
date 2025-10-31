package cn.iocoder.yudao.module.aicrm.controller.admin.customerreturnapplication.vo;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 客户退回申请新增/修改 Request VO")
@Data
public class CustomerReturnApplicationSaveReqVO {

    @Schema(description = "申请ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "21813")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer）", requiredMode = Schema.RequiredMode.REQUIRED, example = "5368")
    @NotNull(message = "客户ID（关联 crm_customer）不能为空")
    private Long customerId;

    @Schema(description = "申请人(客户经理)ID（关联 system_users.id）", requiredMode = Schema.RequiredMode.REQUIRED, example = "13476")
    @NotNull(message = "申请人(客户经理)ID（关联 system_users.id）不能为空")
    private Long applicantUserId;

    @Schema(description = "退回给主管ID（关联 system_users.id）", requiredMode = Schema.RequiredMode.REQUIRED, example = "14892")
    @NotNull(message = "退回给主管ID（关联 system_users.id）不能为空")
    private Long returnToUserId;

    @Schema(description = "申请日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "申请日期不能为空")
    private LocalDate applyDate;

    @Schema(description = "退回原因", example = "不好")
    private String returnReason;

    @Schema(description = "BPM流程实例ID", example = "18304")
    private String processInstanceId;

    @Schema(description = "流程状态（1=审批中，2=已通过，3=已拒绝，4=已取消）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "流程状态（1=审批中，2=已通过，3=已拒绝，4=已取消）不能为空")
    private Integer processStatus;

}