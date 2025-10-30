package cn.iocoder.yudao.module.aicrm.controller.admin.customerassignmenthistory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Schema(description = "管理后台 - 客户归属调整历史表（零售+对公共用，记录所有归属变更历史）新增/修改 Request VO")
@Data
public class CustomerAssignmentHistorySaveReqVO {

    @Schema(description = "调整历史主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "22695")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer 主表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "20532")
    @NotNull(message = "客户ID（关联 crm_customer 主表）不能为空")
    private Long customerId;

    @Schema(description = "调整日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "调整日期不能为空")
    private LocalDate transferDate;

    @Schema(description = "调整级别（字典: aicrm_transfer_level，如：支行内调整、跨支行调整、跨分行调整）")
    private String transferLevel;

    @Schema(description = "调整原因", example = "不好")
    private String transferReason;

    @Schema(description = "调整前归属类型（1=主办，2=协办）", example = "2")
    private Integer beforeAssignmentType;

    @Schema(description = "调整前部门ID（关联 system_dept.id）", example = "26928")
    private Long beforeDeptId;

    @Schema(description = "调整前客户经理用户ID（关联 system_users.id）", example = "28151")
    private Long beforeUserId;

    @Schema(description = "调整后归属类型（1=主办，2=协办）", example = "1")
    private Integer afterAssignmentType;

    @Schema(description = "调整后部门ID（关联 system_dept.id）", example = "19442")
    private Long afterDeptId;

    @Schema(description = "调整后客户经理用户ID（关联 system_users.id）", example = "1401")
    private Long afterUserId;

    @Schema(description = "调整操作人用户ID（关联 system_users.id）", example = "17544")
    private Long assignOperatorId;

    @Schema(description = "分配日期")
    private LocalDate assignDate;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

}