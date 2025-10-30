package cn.iocoder.yudao.module.aicrm.controller.admin.customergridhistory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDate;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 客户归属网格调整历史表（记录调整当时的网格信息快照） Response VO")
@Data
@ExcelIgnoreUnannotated
public class CustomerGridHistoryRespVO {

    @Schema(description = "调整历史主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "5296")
    @ExcelProperty("调整历史主键")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer 主表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "20140")
    @ExcelProperty("客户ID（关联 crm_customer 主表）")
    private Long customerId;

    @Schema(description = "调整日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("调整日期")
    private LocalDate adjustDate;

    @Schema(description = "调整原因", example = "不喜欢")
    @ExcelProperty("调整原因")
    private String adjustReason;

    @Schema(description = "调整前网格ID", example = "3915")
    @ExcelProperty("调整前网格ID")
    private Long beforeGridId;

    @Schema(description = "调整前网格编号")
    @ExcelProperty("调整前网格编号")
    private String beforeGridCode;

    @Schema(description = "调整前网格名称", example = "张三")
    @ExcelProperty("调整前网格名称")
    private String beforeGridName;

    @Schema(description = "调整前网格类型", example = "2")
    @ExcelProperty("调整前网格类型")
    private String beforeGridType;

    @Schema(description = "调整前网格管理员用户ID", example = "10407")
    @ExcelProperty("调整前网格管理员用户ID")
    private Long beforeGridManagerUserId;

    @Schema(description = "调整前网格管理员姓名", example = "张三")
    @ExcelProperty("调整前网格管理员姓名")
    private String beforeGridManagerName;

    @Schema(description = "调整后网格ID", example = "17421")
    @ExcelProperty("调整后网格ID")
    private Long afterGridId;

    @Schema(description = "调整后网格编号")
    @ExcelProperty("调整后网格编号")
    private String afterGridCode;

    @Schema(description = "调整后网格名称", example = "王五")
    @ExcelProperty("调整后网格名称")
    private String afterGridName;

    @Schema(description = "调整后网格类型", example = "2")
    @ExcelProperty("调整后网格类型")
    private String afterGridType;

    @Schema(description = "调整后网格管理员用户ID", example = "4332")
    @ExcelProperty("调整后网格管理员用户ID")
    private Long afterGridManagerUserId;

    @Schema(description = "调整后网格管理员姓名", example = "李四")
    @ExcelProperty("调整后网格管理员姓名")
    private String afterGridManagerName;

    @Schema(description = "调整操作人用户ID（关联 system_users.id）", example = "15494")
    @ExcelProperty("调整操作人用户ID（关联 system_users.id）")
    private Long adjustOperatorId;

    @Schema(description = "调整操作人姓名", example = "王五")
    @ExcelProperty("调整操作人姓名")
    private String adjustOperatorName;

    @Schema(description = "备注", example = "你说的对")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}