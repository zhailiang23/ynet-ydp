package com.ynet.iplatform.module.aicrm.controller.admin.customerproductrecommendation.vo;

import java.time.LocalDate;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 客户产品推荐分页 Request VO")
@Data
public class CustomerProductRecommendationPageReqVO extends PageParam {

    @Schema(description = "客户ID（关联 crm_customer 主表）", example = "30884")
    private Long customerId;

    @Schema(description = "序号")
    private Integer sequenceNo;

    @Schema(description = "产品编号")
    private String productCode;

    @Schema(description = "产品名称", example = "芋艿")
    private String productName;

    @Schema(description = "产品类目ID（关联 crm_product_category 表）", example = "23699")
    private Long productCategoryId;

    @Schema(description = "产品类型（字典: aicrm_product_type）", example = "1")
    private String productType;

    @Schema(description = "推荐编号")
    private String recommendationNo;

    @Schema(description = "推荐类型（字典: aicrm_recommendation_type，intelligent=智能推荐，manual=人工推荐，hot_sale=热销产品，match_customer=匹配客户，cross_sell=交叉销售）", example = "2")
    private String recommendationType;

    @Schema(description = "推荐来源（字典: aicrm_recommendation_source，system_auto=系统自动，ai_analysis=AI分析，manager_recommend=客户经理推荐，data_mining=数据挖掘）")
    private String recommendationSource;

    @Schema(description = "推荐理由", example = "不对")
    private String recommendationReason;

    @Schema(description = "推荐评分（0-100分）")
    private BigDecimal recommendationScore;

    @Schema(description = "推荐日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] recommendationDate;

    @Schema(description = "推荐时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] recommendationTime;

    @Schema(description = "有效开始日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] validStartDate;

    @Schema(description = "有效结束日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] validEndDate;

    @Schema(description = "是否热销产品")
    private Boolean isHotSale;

    @Schema(description = "是否匹配客户")
    private Boolean isMatchCustomer;

    @Schema(description = "匹配度（字典: aicrm_match_degree，high=高度匹配，medium=中等匹配，low=低度匹配）")
    private String matchDegree;

    @Schema(description = "推荐状态（字典: aicrm_recommendation_status，pending=待推送，pushed=已推送，viewed=已查看，clicked=已点击，applied=已申请，purchased=已购买，rejected=已拒绝，expired=已过期）", example = "1")
    private String recommendationStatus;

    @Schema(description = "推送时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] pushTime;

    @Schema(description = "推送渠道（字典: aicrm_push_channel，mobile_banking=手机银行，wechat=微信银行，sms=短信，email=邮件，call=电话，visit=上门拜访）")
    private String pushChannel;

    @Schema(description = "查看时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] viewTime;

    @Schema(description = "查看次数", example = "362")
    private Integer viewCount;

    @Schema(description = "点击时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] clickTime;

    @Schema(description = "点击次数", example = "23841")
    private Integer clickCount;

    @Schema(description = "申请时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] applyTime;

    @Schema(description = "购买时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] purchaseTime;

    @Schema(description = "购买金额")
    private BigDecimal purchaseAmount;

    @Schema(description = "拒绝时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] rejectTime;

    @Schema(description = "拒绝原因", example = "不喜欢")
    private String rejectReason;

    @Schema(description = "推荐人ID", example = "26257")
    private Long recommenderUserId;

    @Schema(description = "推荐人姓名", example = "芋艿")
    private String recommenderUserName;

    @Schema(description = "推荐部门ID", example = "15709")
    private Long recommenderDeptId;

    @Schema(description = "推荐部门名称", example = "芋艿")
    private String recommenderDeptName;

    @Schema(description = "利率/收益率（%）")
    private BigDecimal interestRate;

    @Schema(description = "期限（天）")
    private Integer termDays;

    @Schema(description = "起购金额")
    private BigDecimal minAmount;

    @Schema(description = "最大金额")
    private BigDecimal maxAmount;

    @Schema(description = "风险等级（字典: aicrm_risk_level）")
    private String riskLevel;

    @Schema(description = "产品特点")
    private String productFeatures;

    @Schema(description = "产品优势")
    private String productAdvantage;

    @Schema(description = "营销主题")
    private String marketingTheme;

    @Schema(description = "营销内容")
    private String marketingContent;

    @Schema(description = "促销类型（字典: aicrm_promotion_type）", example = "2")
    private String promotionType;

    @Schema(description = "促销内容")
    private String promotionContent;

    @Schema(description = "转化率（%）")
    private BigDecimal conversionRate;

    @Schema(description = "是否有效")
    private Boolean isEffective;

    @Schema(description = "有效性评分（0-100分）")
    private BigDecimal effectivenessScore;

    @Schema(description = "反馈内容")
    private String feedbackContent;

    @Schema(description = "反馈时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] feedbackTime;

    @Schema(description = "优先级（数字越大优先级越高）")
    private Integer priority;

    @Schema(description = "显示顺序")
    private Integer displayOrder;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}