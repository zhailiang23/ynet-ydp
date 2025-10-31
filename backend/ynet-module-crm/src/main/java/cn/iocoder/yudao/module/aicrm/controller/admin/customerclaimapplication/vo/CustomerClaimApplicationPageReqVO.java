package cn.iocoder.yudao.module.aicrm.controller.admin.customerclaimapplication.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 客户认领申请分页 Request VO")
@Data
public class CustomerClaimApplicationPageReqVO extends PageParam {

    @Schema(description = "客户ID（关联 crm_customer）", example = "20942")
    private Long customerId;

    @Schema(description = "申请人(客户经理)ID（关联 system_users.id）", example = "25358")
    private Long applicantUserId;

    @Schema(description = "申请人部门ID（关联 system_dept.id）", example = "4897")
    private Long applicantDeptId;

    @Schema(description = "申请日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] applyDate;

    @Schema(description = "申请理由", example = "不喜欢")
    private String applyReason;

    @Schema(description = "BPM流程实例ID", example = "8038")
    private String processInstanceId;

    @Schema(description = "流程状态（1=审批中，2=已通过，3=已拒绝，4=已取消）", example = "1")
    private Integer processStatus;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}