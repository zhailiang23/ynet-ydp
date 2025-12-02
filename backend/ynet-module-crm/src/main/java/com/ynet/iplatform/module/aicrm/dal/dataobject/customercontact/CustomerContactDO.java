package com.ynet.iplatform.module.aicrm.dal.dataobject.customercontact;

import java.time.LocalDate;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户接触信息 DO
 *
 * @author 易诚源码
 */
@TableName("crm_customer_contact")
@KeySequence("crm_customer_contact_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerContactDO extends BaseDO {

    /**
     * 接触主键
     */
    @TableId
    private Long id;
    /**
     * 客户ID（关联 crm_customer 主表）
     */
    private Long customerId;
    /**
     * 评估时间
     */
    private LocalDateTime evaluationTime;
    /**
     * 接触类型（字典: aicrm_contact_type，customer_callback=客户反馈，phone=电话，visit=拜访，email=邮件，wechat=微信，meeting=会议等）
     */
    private String contactType;
    /**
     * 客户经理
     */
    private String accountManager;
    /**
     * 接触目的
     */
    private String contactPurpose;
    /**
     * 接触反馈
     */
    private String contactFeedback;
    /**
     * 接触ID（老系统）
     */
    private Integer cstVstId;
    /**
     * 客户ID（老系统）
     */
    private String cstId;
    /**
     * 接触方式代码
     */
    private String vstMthCd;
    /**
     * 接触时间
     */
    private LocalDateTime vstTm;
    /**
     * 客户意向代码
     */
    private String cstInintCd;
    /**
     * 接触人员（我方参与人员）
     */
    private String vstPsnLst;
    /**
     * 客户人员（客户方参与人员）
     */
    private String cstPsnLst;
    /**
     * 接触地址
     */
    private String vstAdr;
    /**
     * 接触结果
     */
    private String vstRsltDsc;
    /**
     * 跟进时间
     */
    private LocalDateTime fuaTm;
    /**
     * 待办事项
     */
    private String toDoDsc;
    /**
     * 其他描述
     */
    private String othDsc;
    /**
     * 创建用户ID（老系统）
     */
    private String crtUsrId;
    /**
     * 创建时间（老系统）
     */
    private LocalDateTime crtTm;
    /**
     * 更新用户ID（老系统）
     */
    private String udtUsrId;
    /**
     * 更新时间（老系统）
     */
    private LocalDateTime udtTm;
    /**
     * 接触编号
     */
    private String contactNo;
    /**
     * 接触状态（字典: aicrm_contact_status，planned=已计划，completed=已完成，cancelled=已取消）
     */
    private String contactStatus;
    /**
     * 接触渠道（字典: aicrm_contact_channel，active=主动接触，passive=被动接触）
     */
    private String contactChannel;
    /**
     * 接触结果（字典: aicrm_contact_result，success=成功，partial=部分成功，failed=失败）
     */
    private String contactResult;
    /**
     * 客户意向（字典: aicrm_customer_intention，high=意向强烈，medium=意向一般，low=意向较弱，none=无意向）
     */
    private String customerIntention;
    /**
     * 接触时长（分钟）
     */
    private Integer contactDuration;
    /**
     * 接触地点
     */
    private String contactLocation;
    /**
     * 接触主题
     */
    private String contactSubject;
    /**
     * 接触摘要
     */
    private String contactSummary;
    /**
     * 下次接触时间
     */
    private LocalDateTime nextContactTime;
    /**
     * 下次接触计划
     */
    private String nextContactPlan;
    /**
     * 是否需要跟进
     */
    private Boolean isNeedFollowup;
    /**
     * 跟进人ID
     */
    private Long followupUserId;
    /**
     * 跟进人姓名
     */
    private String followupUserName;
    /**
     * 跟进状态（字典: aicrm_followup_status，pending=待跟进，following=跟进中，completed=已完成）
     */
    private String followupStatus;
    /**
     * 关联营销活动ID
     */
    private Long relatedActivityId;
    /**
     * 关联营销活动名称
     */
    private String relatedActivityName;
    /**
     * 关联产品
     */
    private String relatedProduct;
    /**
     * 预期金额（元）
     */
    private BigDecimal expectedAmount;
    /**
     * 实际成交金额（元）
     */
    private BigDecimal actualAmount;
    /**
     * 是否已转化
     */
    private Boolean isConverted;
    /**
     * 转化时间
     */
    private LocalDateTime convertTime;
    /**
     * 客户满意度（1-5星）
     */
    private Integer customerSatisfaction;
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
     * 附件列表（JSON格式）
     */
    private String attachments;
    /**
     * 备注
     */
    private String remark;


}