package com.ynet.iplatform.module.aicrm.controller.admin.customerreminder.vo;

import java.time.LocalDate;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 客户提醒信息分页 Request VO")
@Data
public class CustomerReminderPageReqVO extends PageParam {

    @Schema(description = "客户ID（关联 crm_customer 主表）", example = "3380")
    private Long customerId;

    @Schema(description = "序号")
    private Integer sequenceNo;

    @Schema(description = "提醒类别名称", example = "王五")
    private String reminderCategoryName;

    @Schema(description = "提醒生成日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] reminderGenerateDate;

    @Schema(description = "提醒到期日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] reminderDueDate;

    @Schema(description = "提醒内容")
    private String reminderContent;

    @Schema(description = "提醒编号")
    private String reminderNo;

    @Schema(description = "提醒类型（字典: aicrm_reminder_type，account_balance=账户余额变动，credit_expire=授信到期，customer_contact=客户接触，customer_birthday=客户生日，loan_expire=贷款到期，product_expire=产品到期，payment_remind=还款提醒等）", example = "2")
    private String reminderType;

    @Schema(description = "提醒级别（字典: aicrm_reminder_level，urgent=紧急，important=重要，normal=普通，info=提示）")
    private String reminderLevel;

    @Schema(description = "提醒状态（字典: aicrm_reminder_status，pending=待处理，processing=处理中，completed=已完成，expired=已过期，cancelled=已取消）", example = "2")
    private String reminderStatus;

    @Schema(description = "提醒来源（字典: aicrm_reminder_source，system_auto=系统自动，manual_create=手动创建，rule_trigger=规则触发，data_analysis=数据分析）")
    private String reminderSource;

    @Schema(description = "是否已发送")
    private Boolean isSent;

    @Schema(description = "发送时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] sendTime;

    @Schema(description = "发送渠道（字典: aicrm_reminder_channel，sms=短信，email=邮件，wechat=微信，system=系统消息，phone=电话）")
    private String sendChannel;

    @Schema(description = "接收人ID", example = "15506")
    private Long recipientUserId;

    @Schema(description = "接收人姓名", example = "芋艿")
    private String recipientUserName;

    @Schema(description = "接收部门ID", example = "11983")
    private Long recipientDeptId;

    @Schema(description = "接收部门名称", example = "赵六")
    private String recipientDeptName;

    @Schema(description = "处理人ID", example = "11034")
    private Long handlerUserId;

    @Schema(description = "处理人姓名", example = "芋艿")
    private String handlerUserName;

    @Schema(description = "处理时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] handleTime;

    @Schema(description = "处理结果")
    private String handleResult;

    @Schema(description = "是否重复提醒")
    private Boolean isRepeat;

    @Schema(description = "重复规则（如：每天、每周、每月等）")
    private String repeatRule;

    @Schema(description = "下次提醒时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] nextRemindTime;

    @Schema(description = "是否已读")
    private Boolean isRead;

    @Schema(description = "阅读时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] readTime;

    @Schema(description = "优先级（数字越大优先级越高）")
    private Integer priority;

    @Schema(description = "关联业务类型（字典: aicrm_business_type）", example = "2")
    private String relatedBusinessType;

    @Schema(description = "关联业务ID", example = "22420")
    private Long relatedBusinessId;

    @Schema(description = "过期时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] expireTime;

    @Schema(description = "是否已过期")
    private Boolean isExpired;

    @Schema(description = "是否已取消")
    private Boolean isCancelled;

    @Schema(description = "取消时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] cancelTime;

    @Schema(description = "取消原因", example = "不对")
    private String cancelReason;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}