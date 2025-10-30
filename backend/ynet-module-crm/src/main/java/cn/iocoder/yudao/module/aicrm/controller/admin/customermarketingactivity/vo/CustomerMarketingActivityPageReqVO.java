package cn.iocoder.yudao.module.aicrm.controller.admin.customermarketingactivity.vo;

import java.time.LocalDate;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 客户营销活动信息分页 Request VO")
@Data
public class CustomerMarketingActivityPageReqVO extends PageParam {

    @Schema(description = "客户ID（关联 crm_customer 主表）", example = "20246")
    private Long customerId;

    @Schema(description = "序号")
    private Integer sequenceNo;

    @Schema(description = "活动名称", example = "王五")
    private String activityName;

    @Schema(description = "活动编号")
    private String activityNo;

    @Schema(description = "活动形式（字典: aicrm_activity_form，parent_child=亲子沙龙，salon=线下沙龙，visit=客户拜访，phone=电话营销等）")
    private String activityForm;

    @Schema(description = "活动状态（字典: aicrm_activity_status，draft=草稿，pending=待执行，executing=执行中，completed=已完成，closed=已关闭）", example = "2")
    private String activityStatus;

    @Schema(description = "审批状态（字典: aicrm_approval_status，pending_submit=待提交，approving=审批中，approved=已通过，rejected=已驳回）", example = "1")
    private String approvalStatus;

    @Schema(description = "活动订购时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] activityOrderTime;

    @Schema(description = "活动结束时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] activityEndTime;

    @Schema(description = "是否发送客户短信")
    private Boolean isSendSms;

    @Schema(description = "机会ID（老系统）", example = "3294")
    private String opporId;

    @Schema(description = "机会名称（老系统）")
    private String opporNm;

    @Schema(description = "开始日期")
    private LocalDate startDt;

    @Schema(description = "结束日期")
    private LocalDate endDt;

    @Schema(description = "机会状态代码")
    private String opporStcd;

    @Schema(description = "自动分配规则代码")
    private String autoAcltRlcd;

    @Schema(description = "机会业务类型代码")
    private String opporBsnTpcd;

    @Schema(description = "机会分配对象类型代码")
    private String opporAlctObjTpcd;

    @Schema(description = "机会客户类型代码")
    private String opporCustTpcd;

    @Schema(description = "机会来源代码")
    private String opporSrccd;

    @Schema(description = "机会创建方式代码")
    private String opporCrtMthcd;

    @Schema(description = "创建机构ID", example = "24261")
    private String crtOrgId;

    @Schema(description = "机会描述")
    private String opporDsc;

    @Schema(description = "活动类型（字典: aicrm_activity_type，product_promotion=产品推广，customer_maintain=客户维护，market_research=市场调研，brand_publicity=品牌宣传等）", example = "1")
    private String activityType;

    @Schema(description = "活动地点")
    private String activityLocation;

    @Schema(description = "活动预算（元）")
    private BigDecimal activityBudget;

    @Schema(description = "活动实际费用（元）")
    private BigDecimal activityCost;

    @Schema(description = "目标客户数量", example = "20017")
    private Integer targetCustomerCount;

    @Schema(description = "实际参与客户数量", example = "2236")
    private Integer actualCustomerCount;

    @Schema(description = "预期效果")
    private String expectedEffect;

    @Schema(description = "实际效果")
    private String actualEffect;

    @Schema(description = "负责人ID", example = "12366")
    private Long handlerUserId;

    @Schema(description = "负责人姓名", example = "王五")
    private String handlerUserName;

    @Schema(description = "负责部门ID", example = "12701")
    private Long handlerDeptId;

    @Schema(description = "负责部门名称", example = "李四")
    private String handlerDeptName;

    @Schema(description = "审批人ID", example = "14146")
    private Long approverUserId;

    @Schema(description = "审批人姓名", example = "芋艿")
    private String approverUserName;

    @Schema(description = "审批时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] approvalTime;

    @Schema(description = "审批意见")
    private String approvalComment;

    @Schema(description = "短信发送时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] smsSendTime;

    @Schema(description = "短信内容")
    private String smsContent;

    @Schema(description = "活动评分（1-5星）")
    private Integer activityScore;

    @Schema(description = "客户反馈")
    private String customerFeedback;

    @Schema(description = "是否已取消")
    private Boolean isCancelled;

    @Schema(description = "取消时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] cancelTime;

    @Schema(description = "取消原因", example = "不喜欢")
    private String cancelReason;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}