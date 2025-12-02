package com.ynet.iplatform.module.aicrm.dal.dataobject.customerdemand;

import java.time.LocalDate;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户需求信息 DO
 *
 * @author 易诚源码
 */
@TableName("crm_customer_demand")
@KeySequence("crm_customer_demand_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDemandDO extends BaseDO {

    /**
     * 需求主键
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
     * 需求渠道（字典: aicrm_demand_channel，enterprise_banking=企业网银，crm=CRM系统，mobile_banking=手机银行，counter=柜面，call_center=呼叫中心，account_manager=客户经理等）
     */
    private String demandChannel;
    /**
     * 需求内容
     */
    private String demandContent;
    /**
     * 提交时间
     */
    private LocalDateTime submitTime;
    /**
     * 是否处理（0=未处理，1=已处理）
     */
    private Boolean isProcessed;
    /**
     * 需求编号
     */
    private String demandNo;
    /**
     * 需求类型（字典: aicrm_demand_type，product_consult=产品咨询，business_handle=业务办理，complaint_suggest=投诉建议，account_query=账户查询，loan_apply=贷款申请，wealth_manage=理财咨询等）
     */
    private String demandType;
    /**
     * 需求状态（字典: aicrm_demand_status，pending=未处理，processing=处理中，processed=已处理，closed=已关闭）
     */
    private String demandStatus;
    /**
     * 需求优先级（字典: aicrm_demand_priority，urgent=紧急，high=高，normal=普通，low=低）
     */
    private String demandPriority;
    /**
     * 需求来源（字典: aicrm_demand_source，customer_active=客户主动，marketing_activity=营销活动，data_analysis=数据分析，other=其他）
     */
    private String demandSource;
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
     * 处理结果
     */
    private String processResult;
    /**
     * 客户满意度（1-5星）
     */
    private Integer customerSatisfaction;
    /**
     * 满意度反馈
     */
    private String satisfactionFeedback;
    /**
     * 是否需要跟进
     */
    private Boolean followUpRequired;
    /**
     * 跟进时间
     */
    private LocalDateTime followUpTime;
    /**
     * 跟进内容
     */
    private String followUpContent;
    /**
     * 相关产品
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
     * 是否已关闭
     */
    private Boolean isClosed;
    /**
     * 关闭时间
     */
    private LocalDateTime closeTime;
    /**
     * 关闭原因
     */
    private String closeReason;
    /**
     * 备注
     */
    private String remark;


}