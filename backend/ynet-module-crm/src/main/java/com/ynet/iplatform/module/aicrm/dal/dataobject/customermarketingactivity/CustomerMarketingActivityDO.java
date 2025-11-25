package com.ynet.iplatform.module.aicrm.dal.dataobject.customermarketingactivity;

import java.time.LocalDate;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户营销活动信息 DO
 *
 * @author 芋道源码
 */
@TableName("crm_customer_marketing_activity")
@KeySequence("crm_customer_marketing_activity_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerMarketingActivityDO extends BaseDO {

    /**
     * 营销活动主键
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
     * 活动名称
     */
    private String activityName;
    /**
     * 活动编号
     */
    private String activityNo;
    /**
     * 活动形式（字典: aicrm_activity_form，parent_child=亲子沙龙，salon=线下沙龙，visit=客户拜访，phone=电话营销等）
     */
    private String activityForm;
    /**
     * 活动状态（字典: aicrm_activity_status，draft=草稿，pending=待执行，executing=执行中，completed=已完成，closed=已关闭）
     */
    private String activityStatus;
    /**
     * 审批状态（字典: aicrm_approval_status，pending_submit=待提交，approving=审批中，approved=已通过，rejected=已驳回）
     */
    private String approvalStatus;
    /**
     * 活动订购时间
     */
    private LocalDateTime activityOrderTime;
    /**
     * 活动结束时间
     */
    private LocalDateTime activityEndTime;
    /**
     * 是否发送客户短信
     */
    private Boolean isSendSms;
    /**
     * 机会ID（老系统）
     */
    private String opporId;
    /**
     * 机会名称（老系统）
     */
    private String opporNm;
    /**
     * 开始日期
     */
    private LocalDate startDt;
    /**
     * 结束日期
     */
    private LocalDate endDt;
    /**
     * 机会状态代码
     */
    private String opporStcd;
    /**
     * 自动分配规则代码
     */
    private String autoAcltRlcd;
    /**
     * 机会业务类型代码
     */
    private String opporBsnTpcd;
    /**
     * 机会分配对象类型代码
     */
    private String opporAlctObjTpcd;
    /**
     * 机会客户类型代码
     */
    private String opporCustTpcd;
    /**
     * 机会来源代码
     */
    private String opporSrccd;
    /**
     * 机会创建方式代码
     */
    private String opporCrtMthcd;
    /**
     * 创建机构ID
     */
    private String crtOrgId;
    /**
     * 机会描述
     */
    private String opporDsc;
    /**
     * 活动类型（字典: aicrm_activity_type，product_promotion=产品推广，customer_maintain=客户维护，market_research=市场调研，brand_publicity=品牌宣传等）
     */
    private String activityType;
    /**
     * 活动地点
     */
    private String activityLocation;
    /**
     * 活动预算（元）
     */
    private BigDecimal activityBudget;
    /**
     * 活动实际费用（元）
     */
    private BigDecimal activityCost;
    /**
     * 目标客户数量
     */
    private Integer targetCustomerCount;
    /**
     * 实际参与客户数量
     */
    private Integer actualCustomerCount;
    /**
     * 预期效果
     */
    private String expectedEffect;
    /**
     * 实际效果
     */
    private String actualEffect;
    /**
     * 负责人ID
     */
    private Long handlerUserId;
    /**
     * 负责人姓名
     */
    private String handlerUserName;
    /**
     * 负责部门ID
     */
    private Long handlerDeptId;
    /**
     * 负责部门名称
     */
    private String handlerDeptName;
    /**
     * 审批人ID
     */
    private Long approverUserId;
    /**
     * 审批人姓名
     */
    private String approverUserName;
    /**
     * 审批时间
     */
    private LocalDateTime approvalTime;
    /**
     * 审批意见
     */
    private String approvalComment;
    /**
     * 短信发送时间
     */
    private LocalDateTime smsSendTime;
    /**
     * 短信内容
     */
    private String smsContent;
    /**
     * 活动评分（1-5星）
     */
    private Integer activityScore;
    /**
     * 客户反馈
     */
    private String customerFeedback;
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