package com.ynet.iplatform.module.aicrm.dal.dataobject.customerproductrecommendation;

import java.time.LocalDate;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户产品推荐 DO
 *
 * @author 易诚源码
 */
@TableName("crm_customer_product_recommendation")
@KeySequence("crm_customer_product_recommendation_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerProductRecommendationDO extends BaseDO {

    /**
     * 推荐主键
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
     * 产品编号
     */
    private String productCode;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 产品类目ID（关联 crm_product_category 表）
     */
    private Long productCategoryId;
    /**
     * 产品类型（字典: aicrm_product_type）
     */
    private String productType;
    /**
     * 推荐编号
     */
    private String recommendationNo;
    /**
     * 推荐类型（字典: aicrm_recommendation_type，intelligent=智能推荐，manual=人工推荐，hot_sale=热销产品，match_customer=匹配客户，cross_sell=交叉销售）
     */
    private String recommendationType;
    /**
     * 推荐来源（字典: aicrm_recommendation_source，system_auto=系统自动，ai_analysis=AI分析，manager_recommend=客户经理推荐，data_mining=数据挖掘）
     */
    private String recommendationSource;
    /**
     * 推荐理由
     */
    private String recommendationReason;
    /**
     * 推荐评分（0-100分）
     */
    private BigDecimal recommendationScore;
    /**
     * 推荐日期
     */
    private LocalDate recommendationDate;
    /**
     * 推荐时间
     */
    private LocalDateTime recommendationTime;
    /**
     * 有效开始日期
     */
    private LocalDate validStartDate;
    /**
     * 有效结束日期
     */
    private LocalDate validEndDate;
    /**
     * 是否热销产品
     */
    private Boolean isHotSale;
    /**
     * 是否匹配客户
     */
    private Boolean isMatchCustomer;
    /**
     * 匹配度（字典: aicrm_match_degree，high=高度匹配，medium=中等匹配，low=低度匹配）
     */
    private String matchDegree;
    /**
     * 推荐状态（字典: aicrm_recommendation_status，pending=待推送，pushed=已推送，viewed=已查看，clicked=已点击，applied=已申请，purchased=已购买，rejected=已拒绝，expired=已过期）
     */
    private String recommendationStatus;
    /**
     * 推送时间
     */
    private LocalDateTime pushTime;
    /**
     * 推送渠道（字典: aicrm_push_channel，mobile_banking=手机银行，wechat=微信银行，sms=短信，email=邮件，call=电话，visit=上门拜访）
     */
    private String pushChannel;
    /**
     * 查看时间
     */
    private LocalDateTime viewTime;
    /**
     * 查看次数
     */
    private Integer viewCount;
    /**
     * 点击时间
     */
    private LocalDateTime clickTime;
    /**
     * 点击次数
     */
    private Integer clickCount;
    /**
     * 申请时间
     */
    private LocalDateTime applyTime;
    /**
     * 购买时间
     */
    private LocalDateTime purchaseTime;
    /**
     * 购买金额
     */
    private BigDecimal purchaseAmount;
    /**
     * 拒绝时间
     */
    private LocalDateTime rejectTime;
    /**
     * 拒绝原因
     */
    private String rejectReason;
    /**
     * 推荐人ID
     */
    private Long recommenderUserId;
    /**
     * 推荐人姓名
     */
    private String recommenderUserName;
    /**
     * 推荐部门ID
     */
    private Long recommenderDeptId;
    /**
     * 推荐部门名称
     */
    private String recommenderDeptName;
    /**
     * 利率/收益率（%）
     */
    private BigDecimal interestRate;
    /**
     * 期限（天）
     */
    private Integer termDays;
    /**
     * 起购金额
     */
    private BigDecimal minAmount;
    /**
     * 最大金额
     */
    private BigDecimal maxAmount;
    /**
     * 风险等级（字典: aicrm_risk_level）
     */
    private String riskLevel;
    /**
     * 产品特点
     */
    private String productFeatures;
    /**
     * 产品优势
     */
    private String productAdvantage;
    /**
     * 营销主题
     */
    private String marketingTheme;
    /**
     * 营销内容
     */
    private String marketingContent;
    /**
     * 促销类型（字典: aicrm_promotion_type）
     */
    private String promotionType;
    /**
     * 促销内容
     */
    private String promotionContent;
    /**
     * 转化率（%）
     */
    private BigDecimal conversionRate;
    /**
     * 是否有效
     */
    private Boolean isEffective;
    /**
     * 有效性评分（0-100分）
     */
    private BigDecimal effectivenessScore;
    /**
     * 反馈内容
     */
    private String feedbackContent;
    /**
     * 反馈时间
     */
    private LocalDateTime feedbackTime;
    /**
     * 优先级（数字越大优先级越高）
     */
    private Integer priority;
    /**
     * 显示顺序
     */
    private Integer displayOrder;
    /**
     * 备注
     */
    private String remark;


}