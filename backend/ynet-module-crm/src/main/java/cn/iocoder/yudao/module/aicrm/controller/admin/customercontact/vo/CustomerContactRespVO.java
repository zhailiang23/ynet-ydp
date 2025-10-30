package cn.iocoder.yudao.module.aicrm.controller.admin.customercontact.vo;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 客户接触信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CustomerContactRespVO {

    @Schema(description = "接触主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "13210")
    @ExcelProperty("接触主键")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer 主表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "30695")
    @ExcelProperty("客户ID（关联 crm_customer 主表）")
    private Long customerId;

    @Schema(description = "评估时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("评估时间")
    private LocalDateTime evaluationTime;

    @Schema(description = "接触类型（字典: aicrm_contact_type，customer_callback=客户反馈，phone=电话，visit=拜访，email=邮件，wechat=微信，meeting=会议等）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("接触类型（字典: aicrm_contact_type，customer_callback=客户反馈，phone=电话，visit=拜访，email=邮件，wechat=微信，meeting=会议等）")
    private String contactType;

    @Schema(description = "客户经理", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("客户经理")
    private String accountManager;

    @Schema(description = "接触目的", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("接触目的")
    private String contactPurpose;

    @Schema(description = "接触反馈", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("接触反馈")
    private String contactFeedback;

    @Schema(description = "接触ID（老系统）", example = "4658")
    @ExcelProperty("接触ID（老系统）")
    private Integer cstVstId;

    @Schema(description = "客户ID（老系统）", example = "24785")
    @ExcelProperty("客户ID（老系统）")
    private String cstId;

    @Schema(description = "接触方式代码")
    @ExcelProperty("接触方式代码")
    private String vstMthCd;

    @Schema(description = "接触时间")
    @ExcelProperty("接触时间")
    private LocalDateTime vstTm;

    @Schema(description = "客户意向代码")
    @ExcelProperty("客户意向代码")
    private String cstInintCd;

    @Schema(description = "接触人员（我方参与人员）")
    @ExcelProperty("接触人员（我方参与人员）")
    private String vstPsnLst;

    @Schema(description = "客户人员（客户方参与人员）")
    @ExcelProperty("客户人员（客户方参与人员）")
    private String cstPsnLst;

    @Schema(description = "接触地址")
    @ExcelProperty("接触地址")
    private String vstAdr;

    @Schema(description = "接触结果")
    @ExcelProperty("接触结果")
    private String vstRsltDsc;

    @Schema(description = "跟进时间")
    @ExcelProperty("跟进时间")
    private LocalDateTime fuaTm;

    @Schema(description = "待办事项")
    @ExcelProperty("待办事项")
    private String toDoDsc;

    @Schema(description = "其他描述")
    @ExcelProperty("其他描述")
    private String othDsc;

    @Schema(description = "创建用户ID（老系统）", example = "28888")
    @ExcelProperty("创建用户ID（老系统）")
    private String crtUsrId;

    @Schema(description = "创建时间（老系统）")
    @ExcelProperty("创建时间（老系统）")
    private LocalDateTime crtTm;

    @Schema(description = "更新用户ID（老系统）", example = "12418")
    @ExcelProperty("更新用户ID（老系统）")
    private String udtUsrId;

    @Schema(description = "更新时间（老系统）")
    @ExcelProperty("更新时间（老系统）")
    private LocalDateTime udtTm;

    @Schema(description = "接触编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("接触编号")
    private String contactNo;

    @Schema(description = "接触状态（字典: aicrm_contact_status，planned=已计划，completed=已完成，cancelled=已取消）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("接触状态（字典: aicrm_contact_status，planned=已计划，completed=已完成，cancelled=已取消）")
    private String contactStatus;

    @Schema(description = "接触渠道（字典: aicrm_contact_channel，active=主动接触，passive=被动接触）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("接触渠道（字典: aicrm_contact_channel，active=主动接触，passive=被动接触）")
    private String contactChannel;

    @Schema(description = "接触结果（字典: aicrm_contact_result，success=成功，partial=部分成功，failed=失败）")
    @ExcelProperty("接触结果（字典: aicrm_contact_result，success=成功，partial=部分成功，failed=失败）")
    private String contactResult;

    @Schema(description = "客户意向（字典: aicrm_customer_intention，high=意向强烈，medium=意向一般，low=意向较弱，none=无意向）")
    @ExcelProperty("客户意向（字典: aicrm_customer_intention，high=意向强烈，medium=意向一般，low=意向较弱，none=无意向）")
    private String customerIntention;

    @Schema(description = "接触时长（分钟）")
    @ExcelProperty("接触时长（分钟）")
    private Integer contactDuration;

    @Schema(description = "接触地点")
    @ExcelProperty("接触地点")
    private String contactLocation;

    @Schema(description = "接触主题")
    @ExcelProperty("接触主题")
    private String contactSubject;

    @Schema(description = "接触摘要")
    @ExcelProperty("接触摘要")
    private String contactSummary;

    @Schema(description = "下次接触时间")
    @ExcelProperty("下次接触时间")
    private LocalDateTime nextContactTime;

    @Schema(description = "下次接触计划")
    @ExcelProperty("下次接触计划")
    private String nextContactPlan;

    @Schema(description = "是否需要跟进", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("是否需要跟进")
    private Boolean isNeedFollowup;

    @Schema(description = "跟进人ID", example = "23458")
    @ExcelProperty("跟进人ID")
    private Long followupUserId;

    @Schema(description = "跟进人姓名", example = "王五")
    @ExcelProperty("跟进人姓名")
    private String followupUserName;

    @Schema(description = "跟进状态（字典: aicrm_followup_status，pending=待跟进，following=跟进中，completed=已完成）", example = "1")
    @ExcelProperty("跟进状态（字典: aicrm_followup_status，pending=待跟进，following=跟进中，completed=已完成）")
    private String followupStatus;

    @Schema(description = "关联营销活动ID", example = "28981")
    @ExcelProperty("关联营销活动ID")
    private Long relatedActivityId;

    @Schema(description = "关联营销活动名称", example = "赵六")
    @ExcelProperty("关联营销活动名称")
    private String relatedActivityName;

    @Schema(description = "关联产品")
    @ExcelProperty("关联产品")
    private String relatedProduct;

    @Schema(description = "预期金额（元）")
    @ExcelProperty("预期金额（元）")
    private BigDecimal expectedAmount;

    @Schema(description = "实际成交金额（元）")
    @ExcelProperty("实际成交金额（元）")
    private BigDecimal actualAmount;

    @Schema(description = "是否已转化", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("是否已转化")
    private Boolean isConverted;

    @Schema(description = "转化时间")
    @ExcelProperty("转化时间")
    private LocalDateTime convertTime;

    @Schema(description = "客户满意度（1-5星）")
    @ExcelProperty("客户满意度（1-5星）")
    private Integer customerSatisfaction;

    @Schema(description = "处理人ID", example = "12064")
    @ExcelProperty("处理人ID")
    private Long handlerUserId;

    @Schema(description = "处理人姓名", example = "李四")
    @ExcelProperty("处理人姓名")
    private String handlerUserName;

    @Schema(description = "处理部门ID", example = "14337")
    @ExcelProperty("处理部门ID")
    private Long handlerDeptId;

    @Schema(description = "处理部门名称", example = "李四")
    @ExcelProperty("处理部门名称")
    private String handlerDeptName;

    @Schema(description = "附件列表（JSON格式）")
    @ExcelProperty("附件列表（JSON格式）")
    private String attachments;

    @Schema(description = "备注", example = "你说的对")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}