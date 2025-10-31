package cn.iocoder.yudao.module.aicrm.controller.admin.customerdelegation.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 客户托管记录分页 Request VO")
@Data
public class CustomerDelegationPageReqVO extends PageParam {

    @Schema(description = "客户ID（关联 crm_customer）", example = "1568")
    private Long customerId;

    @Schema(description = "托管方客户经理ID（关联 system_users.id）", example = "26034")
    private Long fromUserId;

    @Schema(description = "受托方客户经理ID（关联 system_users.id）", example = "23215")
    private Long toUserId;

    @Schema(description = "托管开始日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] startDate;

    @Schema(description = "托管结束日期（计划）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] endDate;

    @Schema(description = "实际结束日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] actualEndDate;

    @Schema(description = "托管原因", example = "不对")
    private String delegationReason;

    @Schema(description = "托管状态（0=已结束，1=托管中，2=已取消）", example = "1")
    private Integer delegationStatus;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}