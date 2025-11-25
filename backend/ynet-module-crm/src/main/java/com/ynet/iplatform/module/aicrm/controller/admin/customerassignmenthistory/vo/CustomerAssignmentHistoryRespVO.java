package com.ynet.iplatform.module.aicrm.controller.admin.customerassignmenthistory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDate;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 客户归属调整历史表（零售+对公共用，记录所有归属变更历史） Response VO")
@Data
@ExcelIgnoreUnannotated
public class CustomerAssignmentHistoryRespVO {

    @Schema(description = "调整历史主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "22695")
    @ExcelProperty("调整历史主键")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer 主表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "20532")
    @ExcelProperty("客户ID（关联 crm_customer 主表）")
    private Long customerId;

    @Schema(description = "调整日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("调整日期")
    private LocalDate transferDate;

    @Schema(description = "调整级别（字典: aicrm_transfer_level，如：支行内调整、跨支行调整、跨分行调整）")
    @ExcelProperty("调整级别（字典: aicrm_transfer_level，如：支行内调整、跨支行调整、跨分行调整）")
    private String transferLevel;

    @Schema(description = "调整原因", example = "不好")
    @ExcelProperty("调整原因")
    private String transferReason;

    @Schema(description = "调整前归属类型（1=主办，2=协办）", example = "2")
    @ExcelProperty("调整前归属类型（1=主办，2=协办）")
    private Integer beforeAssignmentType;

    @Schema(description = "调整前部门ID（关联 system_dept.id）", example = "26928")
    @ExcelProperty("调整前部门ID（关联 system_dept.id）")
    private Long beforeDeptId;

    @Schema(description = "调整前部门名称", example = "营业一部")
    @ExcelProperty("调整前部门名称")
    private String beforeDeptName;

    @Schema(description = "调整前客户经理用户ID（关联 system_users.id）", example = "28151")
    @ExcelProperty("调整前客户经理用户ID（关联 system_users.id）")
    private Long beforeUserId;

    @Schema(description = "调整前客户经理姓名", example = "李四")
    @ExcelProperty("调整前客户经理姓名")
    private String beforeUserName;

    @Schema(description = "调整后归属类型（1=主办，2=协办）", example = "1")
    @ExcelProperty("调整后归属类型（1=主办，2=协办）")
    private Integer afterAssignmentType;

    @Schema(description = "调整后部门ID（关联 system_dept.id）", example = "19442")
    @ExcelProperty("调整后部门ID（关联 system_dept.id）")
    private Long afterDeptId;

    @Schema(description = "调整后部门名称", example = "营业二部")
    @ExcelProperty("调整后部门名称")
    private String afterDeptName;

    @Schema(description = "调整后客户经理用户ID（关联 system_users.id）", example = "1401")
    @ExcelProperty("调整后客户经理用户ID（关联 system_users.id）")
    private Long afterUserId;

    @Schema(description = "调整后客户经理姓名", example = "王五")
    @ExcelProperty("调整后客户经理姓名")
    private String afterUserName;

    @Schema(description = "调整操作人用户ID（关联 system_users.id）", example = "17544")
    @ExcelProperty("调整操作人用户ID（关联 system_users.id）")
    private Long assignOperatorId;

    @Schema(description = "分配日期")
    @ExcelProperty("分配日期")
    private LocalDate assignDate;

    @Schema(description = "备注", example = "你说的对")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
