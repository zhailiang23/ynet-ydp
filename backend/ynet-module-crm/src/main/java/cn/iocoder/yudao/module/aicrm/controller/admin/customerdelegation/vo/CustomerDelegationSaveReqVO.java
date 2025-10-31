package cn.iocoder.yudao.module.aicrm.controller.admin.customerdelegation.vo;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 客户托管记录新增/修改 Request VO")
@Data
public class CustomerDelegationSaveReqVO {

    @Schema(description = "托管ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "24747")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1568")
    @NotNull(message = "客户ID（关联 crm_customer）不能为空")
    private Long customerId;

    @Schema(description = "托管方客户经理ID（关联 system_users.id）", requiredMode = Schema.RequiredMode.REQUIRED, example = "26034")
    @NotNull(message = "托管方客户经理ID（关联 system_users.id）不能为空")
    private Long fromUserId;

    @Schema(description = "受托方客户经理ID（关联 system_users.id）", requiredMode = Schema.RequiredMode.REQUIRED, example = "23215")
    @NotNull(message = "受托方客户经理ID（关联 system_users.id）不能为空")
    private Long toUserId;

    @Schema(description = "托管开始日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "托管开始日期不能为空")
    private LocalDate startDate;

    @Schema(description = "托管结束日期（计划）")
    private LocalDate endDate;

    @Schema(description = "实际结束日期")
    private LocalDate actualEndDate;

    @Schema(description = "托管原因", example = "不对")
    private String delegationReason;

    @Schema(description = "托管状态（0=已结束，1=托管中，2=已取消）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "托管状态（0=已结束，1=托管中，2=已取消）不能为空")
    private Integer delegationStatus;

}