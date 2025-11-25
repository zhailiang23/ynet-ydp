package com.ynet.iplatform.module.aicrm.controller.admin.customerdemand.vo;

import java.time.LocalDate;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 客户需求信息分页 Request VO")
@Data
public class CustomerDemandPageReqVO extends PageParam {

    @Schema(description = "客户ID（关联 crm_customer 主表）", example = "21802")
    private Long customerId;

    @Schema(description = "序号")
    private Integer sequenceNo;

    @Schema(description = "需求渠道（字典: aicrm_demand_channel，enterprise_banking=企业网银，crm=CRM系统，mobile_banking=手机银行，counter=柜面，call_center=呼叫中心，account_manager=客户经理等）")
    private String demandChannel;

    @Schema(description = "需求内容")
    private String demandContent;

    @Schema(description = "提交时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] submitTime;

    @Schema(description = "是否处理（0=未处理，1=已处理）")
    private Boolean isProcessed;

    @Schema(description = "需求编号")
    private String demandNo;

    @Schema(description = "需求类型（字典: aicrm_demand_type，product_consult=产品咨询，business_handle=业务办理，complaint_suggest=投诉建议，account_query=账户查询，loan_apply=贷款申请，wealth_manage=理财咨询等）", example = "2")
    private String demandType;

    @Schema(description = "需求状态（字典: aicrm_demand_status，pending=未处理，processing=处理中，processed=已处理，closed=已关闭）", example = "1")
    private String demandStatus;

    @Schema(description = "需求优先级（字典: aicrm_demand_priority，urgent=紧急，high=高，normal=普通，low=低）")
    private String demandPriority;

    @Schema(description = "需求来源（字典: aicrm_demand_source，customer_active=客户主动，marketing_activity=营销活动，data_analysis=数据分析，other=其他）")
    private String demandSource;

    @Schema(description = "处理人ID", example = "17889")
    private Long handlerUserId;

    @Schema(description = "处理人姓名", example = "王五")
    private String handlerUserName;

    @Schema(description = "处理部门ID", example = "12410")
    private Long handlerDeptId;

    @Schema(description = "处理部门名称", example = "芋艿")
    private String handlerDeptName;

    @Schema(description = "开始处理时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] processStartTime;

    @Schema(description = "处理完成时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] processEndTime;

    @Schema(description = "处理时长（分钟）")
    private Integer processDuration;

    @Schema(description = "处理结果")
    private String processResult;

    @Schema(description = "客户满意度（1-5星）")
    private Integer customerSatisfaction;

    @Schema(description = "满意度反馈")
    private String satisfactionFeedback;

    @Schema(description = "是否需要跟进")
    private Boolean followUpRequired;

    @Schema(description = "跟进时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] followUpTime;

    @Schema(description = "跟进内容")
    private String followUpContent;

    @Schema(description = "相关产品")
    private String relatedProduct;

    @Schema(description = "预期金额（元）")
    private BigDecimal expectedAmount;

    @Schema(description = "实际成交金额（元）")
    private BigDecimal actualAmount;

    @Schema(description = "是否已转化")
    private Boolean isConverted;

    @Schema(description = "转化时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] convertTime;

    @Schema(description = "是否已关闭")
    private Boolean isClosed;

    @Schema(description = "关闭时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] closeTime;

    @Schema(description = "关闭原因", example = "不喜欢")
    private String closeReason;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}