package com.ynet.iplatform.module.aicrm.controller.admin.customerdelegation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 客户托管记录 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CustomerDelegationRespVO {

    @Schema(description = "托管ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("托管ID")
    private Long id;

    @Schema(description = "客户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1001")
    @ExcelProperty("客户ID")
    private Long customerId;

    @Schema(description = "客户名称", example = "张三")
    @ExcelProperty("客户名称")
    private String customerName;

    @Schema(description = "托管方客户经理ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2001")
    @ExcelProperty("托管方客户经理ID")
    private Long fromUserId;

    @Schema(description = "托管方客户经理姓名", example = "李四")
    @ExcelProperty("托管方客户经理姓名")
    private String fromUserName;

    @Schema(description = "受托方客户经理ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3001")
    @ExcelProperty("受托方客户经理ID")
    private Long toUserId;

    @Schema(description = "受托方客户经理姓名", example = "王五")
    @ExcelProperty("受托方客户经理姓名")
    private String toUserName;

    @Schema(description = "托管开始日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("托管开始日期")
    private LocalDate startDate;

    @Schema(description = "托管结束日期（计划）")
    @ExcelProperty("托管结束日期（计划）")
    private LocalDate endDate;

    @Schema(description = "实际结束日期")
    @ExcelProperty("实际结束日期")
    private LocalDate actualEndDate;

    @Schema(description = "托管原因", example = "临时出差")
    @ExcelProperty("托管原因")
    private String delegationReason;

    @Schema(description = "托管状态（0=已结束，1=托管中，2=已取消）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("托管状态")
    private Integer delegationStatus;

    @Schema(description = "托管状态描述", example = "托管中")
    @ExcelProperty("托管状态描述")
    private String delegationStatusStr;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
