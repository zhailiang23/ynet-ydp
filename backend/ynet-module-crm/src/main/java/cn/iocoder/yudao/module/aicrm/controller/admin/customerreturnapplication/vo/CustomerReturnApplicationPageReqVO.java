package cn.iocoder.yudao.module.aicrm.controller.admin.customerreturnapplication.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 客户退回申请分页 Request VO")
@Data
public class CustomerReturnApplicationPageReqVO extends PageParam {

    @Schema(description = "客户ID（关联 crm_customer）", example = "5368")
    private Long customerId;

    @Schema(description = "申请人(客户经理)ID（关联 system_users.id）", example = "13476")
    private Long applicantUserId;

    @Schema(description = "退回给主管ID（关联 system_users.id）", example = "14892")
    private Long returnToUserId;

    @Schema(description = "申请日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] applyDate;

    @Schema(description = "退回原因", example = "不好")
    private String returnReason;

    @Schema(description = "BPM流程实例ID", example = "18304")
    private String processInstanceId;

    @Schema(description = "流程状态（1=审批中，2=已通过，3=已拒绝，4=已取消）", example = "1")
    private Integer processStatus;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}