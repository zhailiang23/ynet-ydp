package com.ynet.iplatform.module.aicrm.dal.dataobject.customerreminder;

import java.time.LocalDate;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户提醒信息 DO
 *
 * @author 易诚源码
 */
@TableName("crm_customer_reminder")
@KeySequence("crm_customer_reminder_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerReminderDO extends BaseDO {

    /**
     * 提醒主键
     */
    @TableId
    private Long id;
    /**
     * 客户ID（关联 crm_customer 主表）
     */
    private Long customerId;
    /**
     * 序号
     */
    private Integer sequenceNo;
    /**
     * 提醒类别名称
     */
    private String reminderCategoryName;
    /**
     * 提醒生成日期
     */
    private LocalDate reminderGenerateDate;
    /**
     * 提醒到期日期
     */
    private LocalDate reminderDueDate;
    /**
     * 提醒内容
     */
    private String reminderContent;
    /**
     * 提醒编号
     */
    private String reminderNo;
    /**
     * 提醒类型（字典: aicrm_reminder_type，account_balance=账户余额变动，credit_expire=授信到期，customer_contact=客户接触，customer_birthday=客户生日，loan_expire=贷款到期，product_expire=产品到期，payment_remind=还款提醒等）
     */
    private String reminderType;
    /**
     * 提醒级别（字典: aicrm_reminder_level，urgent=紧急，important=重要，normal=普通，info=提示）
     */
    private String reminderLevel;
    /**
     * 提醒状态（字典: aicrm_reminder_status，pending=待处理，processing=处理中，completed=已完成，expired=已过期，cancelled=已取消）
     */
    private String reminderStatus;
    /**
     * 提醒来源（字典: aicrm_reminder_source，system_auto=系统自动，manual_create=手动创建，rule_trigger=规则触发，data_analysis=数据分析）
     */
    private String reminderSource;
    /**
     * 是否已发送
     */
    private Boolean isSent;
    /**
     * 发送时间
     */
    private LocalDateTime sendTime;
    /**
     * 发送渠道（字典: aicrm_reminder_channel，sms=短信，email=邮件，wechat=微信，system=系统消息，phone=电话）
     */
    private String sendChannel;
    /**
     * 接收人ID
     */
    private Long recipientUserId;
    /**
     * 接收人姓名
     */
    private String recipientUserName;
    /**
     * 接收部门ID
     */
    private Long recipientDeptId;
    /**
     * 接收部门名称
     */
    private String recipientDeptName;
    /**
     * 处理人ID
     */
    private Long handlerUserId;
    /**
     * 处理人姓名
     */
    private String handlerUserName;
    /**
     * 处理时间
     */
    private LocalDateTime handleTime;
    /**
     * 处理结果
     */
    private String handleResult;
    /**
     * 是否重复提醒
     */
    private Boolean isRepeat;
    /**
     * 重复规则（如：每天、每周、每月等）
     */
    private String repeatRule;
    /**
     * 下次提醒时间
     */
    private LocalDateTime nextRemindTime;
    /**
     * 是否已读
     */
    private Boolean isRead;
    /**
     * 阅读时间
     */
    private LocalDateTime readTime;
    /**
     * 优先级（数字越大优先级越高）
     */
    private Integer priority;
    /**
     * 关联业务类型（字典: aicrm_business_type）
     */
    private String relatedBusinessType;
    /**
     * 关联业务ID
     */
    private Long relatedBusinessId;
    /**
     * 过期时间
     */
    private LocalDateTime expireTime;
    /**
     * 是否已过期
     */
    private Boolean isExpired;
    /**
     * 是否已取消
     */
    private Boolean isCancelled;
    /**
     * 取消时间
     */
    private LocalDateTime cancelTime;
    /**
     * 取消原因
     */
    private String cancelReason;
    /**
     * 备注
     */
    private String remark;


}