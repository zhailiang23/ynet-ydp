package cn.iocoder.yudao.module.aicrm.controller.admin.customerdemand.vo;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 客户需求信息新增/修改 Request VO")
@Data
public class CustomerDemandSaveReqVO {

    @Schema(description = "需求主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "9322")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer 主表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "21802")
    @NotNull(message = "客户ID（关联 crm_customer 主表）不能为空")
    private Long customerId;

    @Schema(description = "序号")
    private Integer sequenceNo;

    @Schema(description = "需求渠道（字典: aicrm_demand_channel，enterprise_banking=企业网银，crm=CRM系统，mobile_banking=手机银行，counter=柜面，call_center=呼叫中心，account_manager=客户经理等）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "需求渠道（字典: aicrm_demand_channel，enterprise_banking=企业网银，crm=CRM系统，mobile_banking=手机银行，counter=柜面，call_center=呼叫中心，account_manager=客户经理等）不能为空")
    private String demandChannel;

    @Schema(description = "需求内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "需求内容不能为空")
    private String demandContent;

    @Schema(description = "提交时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "提交时间不能为空")
    private LocalDateTime submitTime;

    @Schema(description = "是否处理（0=未处理，1=已处理）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否处理（0=未处理，1=已处理）不能为空")
    private Boolean isProcessed;

    @Schema(description = "需求编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "需求编号不能为空")
    private String demandNo;

    @Schema(description = "需求类型（字典: aicrm_demand_type，product_consult=产品咨询，business_handle=业务办理，complaint_suggest=投诉建议，account_query=账户查询，loan_apply=贷款申请，wealth_manage=理财咨询等）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "需求类型（字典: aicrm_demand_type，product_consult=产品咨询，business_handle=业务办理，complaint_suggest=投诉建议，account_query=账户查询，loan_apply=贷款申请，wealth_manage=理财咨询等）不能为空")
    private String demandType;

    @Schema(description = "需求状态（字典: aicrm_demand_status，pending=未处理，processing=处理中，processed=已处理，closed=已关闭）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "需求状态（字典: aicrm_demand_status，pending=未处理，processing=处理中，processed=已处理，closed=已关闭）不能为空")
    private String demandStatus;

    @Schema(description = "需求优先级（字典: aicrm_demand_priority，urgent=紧急，high=高，normal=普通，low=低）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "需求优先级（字典: aicrm_demand_priority，urgent=紧急，high=高，normal=普通，low=低）不能为空")
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
    private LocalDateTime processStartTime;

    @Schema(description = "处理完成时间")
    private LocalDateTime processEndTime;

    @Schema(description = "处理时长（分钟）")
    private Integer processDuration;

    @Schema(description = "处理结果")
    private String processResult;

    @Schema(description = "客户满意度（1-5星）")
    private Integer customerSatisfaction;

    @Schema(description = "满意度反馈")
    private String satisfactionFeedback;

    @Schema(description = "是否需要跟进", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否需要跟进不能为空")
    private Boolean followUpRequired;

    @Schema(description = "跟进时间")
    private LocalDateTime followUpTime;

    @Schema(description = "跟进内容")
    private String followUpContent;

    @Schema(description = "相关产品")
    private String relatedProduct;

    @Schema(description = "预期金额（元）")
    private BigDecimal expectedAmount;

    @Schema(description = "实际成交金额（元）")
    private BigDecimal actualAmount;

    @Schema(description = "是否已转化", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否已转化不能为空")
    private Boolean isConverted;

    @Schema(description = "转化时间")
    private LocalDateTime convertTime;

    @Schema(description = "是否已关闭", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否已关闭不能为空")
    private Boolean isClosed;

    @Schema(description = "关闭时间")
    private LocalDateTime closeTime;

    @Schema(description = "关闭原因", example = "不喜欢")
    private String closeReason;

    @Schema(description = "备注", example = "随便")
    private String remark;

}