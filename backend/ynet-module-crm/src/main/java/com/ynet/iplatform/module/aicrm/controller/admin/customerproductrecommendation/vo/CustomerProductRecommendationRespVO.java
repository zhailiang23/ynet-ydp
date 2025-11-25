package com.ynet.iplatform.module.aicrm.controller.admin.customerproductrecommendation.vo;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 客户产品推荐 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CustomerProductRecommendationRespVO {

    @Schema(description = "推荐主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "31749")
    @ExcelProperty("推荐主键")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer 主表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "30884")
    @ExcelProperty("客户ID（关联 crm_customer 主表）")
    private Long customerId;

    @Schema(description = "序号")
    @ExcelProperty("序号")
    private Integer sequenceNo;

    @Schema(description = "产品编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("产品编号")
    private String productCode;

    @Schema(description = "产品名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("产品名称")
    private String productName;

    @Schema(description = "产品类目ID（关联 crm_product_category 表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "23699")
    @ExcelProperty("产品类目ID（关联 crm_product_category 表）")
    private Long productCategoryId;

    @Schema(description = "产品类型（字典: aicrm_product_type）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("产品类型（字典: aicrm_product_type）")
    private String productType;

    @Schema(description = "推荐编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("推荐编号")
    private String recommendationNo;

    @Schema(description = "推荐类型（字典: aicrm_recommendation_type，intelligent=智能推荐，manual=人工推荐，hot_sale=热销产品，match_customer=匹配客户，cross_sell=交叉销售）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("推荐类型（字典: aicrm_recommendation_type，intelligent=智能推荐，manual=人工推荐，hot_sale=热销产品，match_customer=匹配客户，cross_sell=交叉销售）")
    private String recommendationType;

    @Schema(description = "推荐来源（字典: aicrm_recommendation_source，system_auto=系统自动，ai_analysis=AI分析，manager_recommend=客户经理推荐，data_mining=数据挖掘）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("推荐来源（字典: aicrm_recommendation_source，system_auto=系统自动，ai_analysis=AI分析，manager_recommend=客户经理推荐，data_mining=数据挖掘）")
    private String recommendationSource;

    @Schema(description = "推荐理由", example = "不对")
    @ExcelProperty("推荐理由")
    private String recommendationReason;

    @Schema(description = "推荐评分（0-100分）")
    @ExcelProperty("推荐评分（0-100分）")
    private BigDecimal recommendationScore;

    @Schema(description = "推荐日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("推荐日期")
    private LocalDate recommendationDate;

    @Schema(description = "推荐时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("推荐时间")
    private LocalDateTime recommendationTime;

    @Schema(description = "有效开始日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("有效开始日期")
    private LocalDate validStartDate;

    @Schema(description = "有效结束日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("有效结束日期")
    private LocalDate validEndDate;

    @Schema(description = "是否热销产品", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("是否热销产品")
    private Boolean isHotSale;

    @Schema(description = "是否匹配客户", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("是否匹配客户")
    private Boolean isMatchCustomer;

    @Schema(description = "匹配度（字典: aicrm_match_degree，high=高度匹配，medium=中等匹配，low=低度匹配）")
    @ExcelProperty("匹配度（字典: aicrm_match_degree，high=高度匹配，medium=中等匹配，low=低度匹配）")
    private String matchDegree;

    @Schema(description = "推荐状态（字典: aicrm_recommendation_status，pending=待推送，pushed=已推送，viewed=已查看，clicked=已点击，applied=已申请，purchased=已购买，rejected=已拒绝，expired=已过期）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("推荐状态（字典: aicrm_recommendation_status，pending=待推送，pushed=已推送，viewed=已查看，clicked=已点击，applied=已申请，purchased=已购买，rejected=已拒绝，expired=已过期）")
    private String recommendationStatus;

    @Schema(description = "推送时间")
    @ExcelProperty("推送时间")
    private LocalDateTime pushTime;

    @Schema(description = "推送渠道（字典: aicrm_push_channel，mobile_banking=手机银行，wechat=微信银行，sms=短信，email=邮件，call=电话，visit=上门拜访）")
    @ExcelProperty("推送渠道（字典: aicrm_push_channel，mobile_banking=手机银行，wechat=微信银行，sms=短信，email=邮件，call=电话，visit=上门拜访）")
    private String pushChannel;

    @Schema(description = "查看时间")
    @ExcelProperty("查看时间")
    private LocalDateTime viewTime;

    @Schema(description = "查看次数", requiredMode = Schema.RequiredMode.REQUIRED, example = "362")
    @ExcelProperty("查看次数")
    private Integer viewCount;

    @Schema(description = "点击时间")
    @ExcelProperty("点击时间")
    private LocalDateTime clickTime;

    @Schema(description = "点击次数", requiredMode = Schema.RequiredMode.REQUIRED, example = "23841")
    @ExcelProperty("点击次数")
    private Integer clickCount;

    @Schema(description = "申请时间")
    @ExcelProperty("申请时间")
    private LocalDateTime applyTime;

    @Schema(description = "购买时间")
    @ExcelProperty("购买时间")
    private LocalDateTime purchaseTime;

    @Schema(description = "购买金额")
    @ExcelProperty("购买金额")
    private BigDecimal purchaseAmount;

    @Schema(description = "拒绝时间")
    @ExcelProperty("拒绝时间")
    private LocalDateTime rejectTime;

    @Schema(description = "拒绝原因", example = "不喜欢")
    @ExcelProperty("拒绝原因")
    private String rejectReason;

    @Schema(description = "推荐人ID", example = "26257")
    @ExcelProperty("推荐人ID")
    private Long recommenderUserId;

    @Schema(description = "推荐人姓名", example = "芋艿")
    @ExcelProperty("推荐人姓名")
    private String recommenderUserName;

    @Schema(description = "推荐部门ID", example = "15709")
    @ExcelProperty("推荐部门ID")
    private Long recommenderDeptId;

    @Schema(description = "推荐部门名称", example = "芋艿")
    @ExcelProperty("推荐部门名称")
    private String recommenderDeptName;

    @Schema(description = "利率/收益率（%）")
    @ExcelProperty("利率/收益率（%）")
    private BigDecimal interestRate;

    @Schema(description = "期限（天）")
    @ExcelProperty("期限（天）")
    private Integer termDays;

    @Schema(description = "起购金额")
    @ExcelProperty("起购金额")
    private BigDecimal minAmount;

    @Schema(description = "最大金额")
    @ExcelProperty("最大金额")
    private BigDecimal maxAmount;

    @Schema(description = "风险等级（字典: aicrm_risk_level）")
    @ExcelProperty("风险等级（字典: aicrm_risk_level）")
    private String riskLevel;

    @Schema(description = "产品特点")
    @ExcelProperty("产品特点")
    private String productFeatures;

    @Schema(description = "产品优势")
    @ExcelProperty("产品优势")
    private String productAdvantage;

    @Schema(description = "营销主题")
    @ExcelProperty("营销主题")
    private String marketingTheme;

    @Schema(description = "营销内容")
    @ExcelProperty("营销内容")
    private String marketingContent;

    @Schema(description = "促销类型（字典: aicrm_promotion_type）", example = "2")
    @ExcelProperty("促销类型（字典: aicrm_promotion_type）")
    private String promotionType;

    @Schema(description = "促销内容")
    @ExcelProperty("促销内容")
    private String promotionContent;

    @Schema(description = "转化率（%）")
    @ExcelProperty("转化率（%）")
    private BigDecimal conversionRate;

    @Schema(description = "是否有效", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("是否有效")
    private Boolean isEffective;

    @Schema(description = "有效性评分（0-100分）")
    @ExcelProperty("有效性评分（0-100分）")
    private BigDecimal effectivenessScore;

    @Schema(description = "反馈内容")
    @ExcelProperty("反馈内容")
    private String feedbackContent;

    @Schema(description = "反馈时间")
    @ExcelProperty("反馈时间")
    private LocalDateTime feedbackTime;

    @Schema(description = "优先级（数字越大优先级越高）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("优先级（数字越大优先级越高）")
    private Integer priority;

    @Schema(description = "显示顺序", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("显示顺序")
    private Integer displayOrder;

    @Schema(description = "备注", example = "你说的对")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}