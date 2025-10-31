package cn.iocoder.yudao.module.aicrm.controller.admin.customerdelegation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 客户托管记录 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CustomerDelegationRespVO {

    @Schema(description = "托管ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "24747")
    @ExcelProperty("托管ID")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1568")
    @ExcelProperty("客户ID（关联 crm_customer）")
    private Long customerId;

    @Schema(description = "托管方客户经理ID（关联 system_users.id）", requiredMode = Schema.RequiredMode.REQUIRED, example = "26034")
    @ExcelProperty("托管方客户经理ID（关联 system_users.id）")
    private Long fromUserId;

    @Schema(description = "受托方客户经理ID（关联 system_users.id）", requiredMode = Schema.RequiredMode.REQUIRED, example = "23215")
    @ExcelProperty("受托方客户经理ID（关联 system_users.id）")
    private Long toUserId;

    @Schema(description = "托管开始日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("托管开始日期")
    private LocalDate startDate;

    @Schema(description = "托管结束日期（计划）")
    @ExcelProperty("托管结束日期（计划）")
    private LocalDate endDate;

    @Schema(description = "实际结束日期")
    @ExcelProperty("实际结束日期")
    private LocalDate actualEndDate;

    @Schema(description = "托管原因", example = "不对")
    @ExcelProperty("托管原因")
    private String delegationReason;

    @Schema(description = "托管状态（0=已结束，1=托管中，2=已取消）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("托管状态（0=已结束，1=托管中，2=已取消）")
    private Integer delegationStatus;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}