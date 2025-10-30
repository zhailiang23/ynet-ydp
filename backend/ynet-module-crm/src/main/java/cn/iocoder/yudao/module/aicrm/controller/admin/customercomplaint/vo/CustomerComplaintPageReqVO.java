package cn.iocoder.yudao.module.aicrm.controller.admin.customercomplaint.vo;

import java.time.LocalDate;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 客户投诉信息分页 Request VO")
@Data
public class CustomerComplaintPageReqVO extends PageParam {

    @Schema(description = "客户ID（关联 crm_customer 主表）", example = "30408")
    private Long customerId;

    @Schema(description = "序号")
    private Integer sequenceNo;

    @Schema(description = "工单编号")
    private String workOrderNo;

    @Schema(description = "ECIF客户号")
    private String ecifCustomerNo;

    @Schema(description = "客户姓名", example = "芋艿")
    private String customerName;

    @Schema(description = "工单处理状态（字典: aicrm_complaint_status）", example = "2")
    private String workOrderStatus;

    @Schema(description = "最近处理时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] lastProcessTime;

    @Schema(description = "投诉渠道（字典: aicrm_complaint_channel，online_banking=网银，mobile_banking=手机银行，counter=柜面，call_center=呼叫中心，account_manager=客户经理）")
    private String complaintChannel;

    @Schema(description = "投诉类型（字典: aicrm_complaint_type）", example = "1")
    private String complaintType;

    @Schema(description = "投诉内容")
    private String complaintContent;

    @Schema(description = "投诉时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] complaintTime;

    @Schema(description = "投诉级别（字典: aicrm_complaint_level，urgent=紧急，important=重要，normal=普通）")
    private String complaintLevel;

    @Schema(description = "处理人ID", example = "14019")
    private Long handlerUserId;

    @Schema(description = "处理人姓名", example = "李四")
    private String handlerUserName;

    @Schema(description = "处理部门ID", example = "10808")
    private Long handlerDeptId;

    @Schema(description = "处理部门名称", example = "王五")
    private String handlerDeptName;

    @Schema(description = "处理结果")
    private String processResult;

    @Schema(description = "开始处理时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] processStartTime;

    @Schema(description = "处理完成时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] processEndTime;

    @Schema(description = "处理时长（分钟）")
    private Integer processDuration;

    @Schema(description = "客户满意度（1-5星）")
    private Integer customerSatisfaction;

    @Schema(description = "满意度反馈")
    private String satisfactionFeedback;

    @Schema(description = "是否已关闭")
    private Boolean isClosed;

    @Schema(description = "关闭时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] closeTime;

    @Schema(description = "是否重新打开")
    private Boolean isReopened;

    @Schema(description = "重新打开时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] reopenTime;

    @Schema(description = "重新打开原因", example = "不对")
    private String reopenReason;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}