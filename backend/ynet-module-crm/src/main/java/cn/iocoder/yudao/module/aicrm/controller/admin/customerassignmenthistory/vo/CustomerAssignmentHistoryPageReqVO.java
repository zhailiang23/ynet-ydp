package cn.iocoder.yudao.module.aicrm.controller.admin.customerassignmenthistory.vo;

import lombok.*;
import java.time.LocalDate;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 客户归属调整历史表（零售+对公共用，记录所有归属变更历史）分页 Request VO")
@Data
public class CustomerAssignmentHistoryPageReqVO extends PageParam {

    @Schema(description = "客户ID（关联 crm_customer 主表）", example = "20532")
    private Long customerId;

    @Schema(description = "调整日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] transferDate;

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
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] assignDate;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}