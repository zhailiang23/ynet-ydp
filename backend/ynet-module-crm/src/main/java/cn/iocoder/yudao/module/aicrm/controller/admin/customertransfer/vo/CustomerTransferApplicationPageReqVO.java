package cn.iocoder.yudao.module.aicrm.controller.admin.customertransfer.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY;

@Schema(description = "管理后台 - 客户移交申请分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CustomerTransferApplicationPageReqVO extends PageParam {

    @Schema(description = "客户ID", example = "1001")
    private Long customerId;

    @Schema(description = "申请人用户ID", example = "2001")
    private Long applicantUserId;

    @Schema(description = "接收方用户ID", example = "3001")
    private Long toUserId;

    @Schema(description = "审批状态（1=审批中，2=已通过，3=已拒绝，4=已取消）", example = "1")
    private Integer processStatus;

    @Schema(description = "申请日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY)
    private LocalDate[] applyDate;

}
