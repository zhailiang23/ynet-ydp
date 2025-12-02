package com.ynet.iplatform.module.aicrm.dal.dataobject.customercomplaint;

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
 * 客户投诉信息 DO
 *
 * @author 易诚源码
 */
@TableName("crm_customer_complaint")
@KeySequence("crm_customer_complaint_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerComplaintDO extends BaseDO {

    /**
     * 投诉主键
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
     * 工单编号
     */
    private String workOrderNo;
    /**
     * ECIF客户号
     */
    private String ecifCustomerNo;
    /**
     * 客户姓名
     */
    private String customerName;
    /**
     * 工单处理状态（字典: aicrm_complaint_status）
     */
    private String workOrderStatus;
    /**
     * 最近处理时间
     */
    private LocalDateTime lastProcessTime;
    /**
     * 投诉渠道（字典: aicrm_complaint_channel，online_banking=网银，mobile_banking=手机银行，counter=柜面，call_center=呼叫中心，account_manager=客户经理）
     */
    private String complaintChannel;
    /**
     * 投诉类型（字典: aicrm_complaint_type）
     */
    private String complaintType;
    /**
     * 投诉内容
     */
    private String complaintContent;
    /**
     * 投诉时间
     */
    private LocalDateTime complaintTime;
    /**
     * 投诉级别（字典: aicrm_complaint_level，urgent=紧急，important=重要，normal=普通）
     */
    private String complaintLevel;
    /**
     * 处理人ID
     */
    private Long handlerUserId;
    /**
     * 处理人姓名
     */
    private String handlerUserName;
    /**
     * 处理部门ID
     */
    private Long handlerDeptId;
    /**
     * 处理部门名称
     */
    private String handlerDeptName;
    /**
     * 处理结果
     */
    private String processResult;
    /**
     * 开始处理时间
     */
    private LocalDateTime processStartTime;
    /**
     * 处理完成时间
     */
    private LocalDateTime processEndTime;
    /**
     * 处理时长（分钟）
     */
    private Integer processDuration;
    /**
     * 客户满意度（1-5星）
     */
    private Integer customerSatisfaction;
    /**
     * 满意度反馈
     */
    private String satisfactionFeedback;
    /**
     * 是否已关闭
     */
    private Boolean isClosed;
    /**
     * 关闭时间
     */
    private LocalDateTime closeTime;
    /**
     * 是否重新打开
     */
    private Boolean isReopened;
    /**
     * 重新打开时间
     */
    private LocalDateTime reopenTime;
    /**
     * 重新打开原因
     */
    private String reopenReason;
    /**
     * 备注
     */
    private String remark;


}