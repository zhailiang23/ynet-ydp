package com.ynet.iplatform.module.aicrm.controller.admin.customercomplaint.vo;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 客户投诉信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CustomerComplaintRespVO {

    @Schema(description = "投诉主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "24384")
    @ExcelProperty("投诉主键")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer 主表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "30408")
    @ExcelProperty("客户ID（关联 crm_customer 主表）")
    private Long customerId;

    @Schema(description = "序号")
    @ExcelProperty("序号")
    private Integer sequenceNo;

    @Schema(description = "工单编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("工单编号")
    private String workOrderNo;

    @Schema(description = "ECIF客户号")
    @ExcelProperty("ECIF客户号")
    private String ecifCustomerNo;

    @Schema(description = "客户姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("客户姓名")
    private String customerName;

    @Schema(description = "工单处理状态（字典: aicrm_complaint_status）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("工单处理状态（字典: aicrm_complaint_status）")
    private String workOrderStatus;

    @Schema(description = "最近处理时间")
    @ExcelProperty("最近处理时间")
    private LocalDateTime lastProcessTime;

    @Schema(description = "投诉渠道（字典: aicrm_complaint_channel，online_banking=网银，mobile_banking=手机银行，counter=柜面，call_center=呼叫中心，account_manager=客户经理）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("投诉渠道（字典: aicrm_complaint_channel，online_banking=网银，mobile_banking=手机银行，counter=柜面，call_center=呼叫中心，account_manager=客户经理）")
    private String complaintChannel;

    @Schema(description = "投诉类型（字典: aicrm_complaint_type）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("投诉类型（字典: aicrm_complaint_type）")
    private String complaintType;

    @Schema(description = "投诉内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("投诉内容")
    private String complaintContent;

    @Schema(description = "投诉时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("投诉时间")
    private LocalDateTime complaintTime;

    @Schema(description = "投诉级别（字典: aicrm_complaint_level，urgent=紧急，important=重要，normal=普通）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("投诉级别（字典: aicrm_complaint_level，urgent=紧急，important=重要，normal=普通）")
    private String complaintLevel;

    @Schema(description = "处理人ID", example = "14019")
    @ExcelProperty("处理人ID")
    private Long handlerUserId;

    @Schema(description = "处理人姓名", example = "李四")
    @ExcelProperty("处理人姓名")
    private String handlerUserName;

    @Schema(description = "处理部门ID", example = "10808")
    @ExcelProperty("处理部门ID")
    private Long handlerDeptId;

    @Schema(description = "处理部门名称", example = "王五")
    @ExcelProperty("处理部门名称")
    private String handlerDeptName;

    @Schema(description = "处理结果")
    @ExcelProperty("处理结果")
    private String processResult;

    @Schema(description = "开始处理时间")
    @ExcelProperty("开始处理时间")
    private LocalDateTime processStartTime;

    @Schema(description = "处理完成时间")
    @ExcelProperty("处理完成时间")
    private LocalDateTime processEndTime;

    @Schema(description = "处理时长（分钟）")
    @ExcelProperty("处理时长（分钟）")
    private Integer processDuration;

    @Schema(description = "客户满意度（1-5星）")
    @ExcelProperty("客户满意度（1-5星）")
    private Integer customerSatisfaction;

    @Schema(description = "满意度反馈")
    @ExcelProperty("满意度反馈")
    private String satisfactionFeedback;

    @Schema(description = "是否已关闭", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("是否已关闭")
    private Boolean isClosed;

    @Schema(description = "关闭时间")
    @ExcelProperty("关闭时间")
    private LocalDateTime closeTime;

    @Schema(description = "是否重新打开", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("是否重新打开")
    private Boolean isReopened;

    @Schema(description = "重新打开时间")
    @ExcelProperty("重新打开时间")
    private LocalDateTime reopenTime;

    @Schema(description = "重新打开原因", example = "不对")
    @ExcelProperty("重新打开原因")
    private String reopenReason;

    @Schema(description = "备注", example = "你说的对")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}