package cn.iocoder.yudao.module.aicrm.controller.admin.customergridhistory.vo;
import java.time.LocalDate;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 客户归属网格调整历史表（记录调整当时的网格信息快照）新增/修改 Request VO")
@Data
public class CustomerGridHistorySaveReqVO {

    @Schema(description = "调整历史主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "5296")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer 主表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "20140")
    @NotNull(message = "客户ID（关联 crm_customer 主表）不能为空")
    private Long customerId;

    @Schema(description = "调整日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "调整日期不能为空")
    private LocalDate adjustDate;

    @Schema(description = "调整原因", example = "不喜欢")
    private String adjustReason;

    @Schema(description = "调整前网格ID", example = "3915")
    private Long beforeGridId;

    @Schema(description = "调整前网格编号")
    private String beforeGridCode;

    @Schema(description = "调整前网格名称", example = "张三")
    private String beforeGridName;

    @Schema(description = "调整前网格类型", example = "2")
    private String beforeGridType;

    @Schema(description = "调整前网格管理员用户ID", example = "10407")
    private Long beforeGridManagerUserId;

    @Schema(description = "调整后网格ID", example = "17421")
    private Long afterGridId;

    @Schema(description = "调整后网格编号")
    private String afterGridCode;

    @Schema(description = "调整后网格名称", example = "王五")
    private String afterGridName;

    @Schema(description = "调整后网格类型", example = "2")
    private String afterGridType;

    @Schema(description = "调整后网格管理员用户ID", example = "4332")
    private Long afterGridManagerUserId;

    @Schema(description = "调整操作人用户ID（关联 system_users.id）", example = "15494")
    private Long adjustOperatorId;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

}