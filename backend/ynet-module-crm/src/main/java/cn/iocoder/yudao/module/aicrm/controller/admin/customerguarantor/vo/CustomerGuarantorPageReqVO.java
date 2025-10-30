package cn.iocoder.yudao.module.aicrm.controller.admin.customerguarantor.vo;

import java.time.LocalDate;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 客户担保人信息表（零售+对公共用）分页 Request VO")
@Data
public class CustomerGuarantorPageReqVO extends PageParam {

    @Schema(description = "客户ID（被担保人，关联 crm_customer 主表）", example = "7130")
    private Long customerId;

    @Schema(description = "授信ID（关联 crm_customer_credit.id）", example = "11623")
    private Long creditId;

    @Schema(description = "合同号")
    private String contractNo;

    @Schema(description = "合同类型（字典: aicrm_guarantor_contract_type）", example = "2")
    private String contractType;

    @Schema(description = "合同状态（字典: aicrm_guarantor_contract_status，effective=有效，expired=已到期，cancelled=已解除）", example = "2")
    private String contractStatus;

    @Schema(description = "签约日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] signDate;

    @Schema(description = "担保类型（字典: aicrm_guarantee_method，guarantee=保证）", example = "1")
    private String guaranteeType;

    @Schema(description = "担保人编号")
    private String guarantorNo;

    @Schema(description = "担保人姓名/名称", example = "王五")
    private String guarantorName;

    @Schema(description = "币种（字典: aicrm_currency_type）")
    private String currencyCode;

    @Schema(description = "担保总金额（万元）")
    private BigDecimal guaranteeTotalAmount;

    @Schema(description = "业务起始日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] businessStartDate;

    @Schema(description = "业务截止日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] businessEndDate;

    @Schema(description = "担保人类型（字典: aicrm_guarantor_type，person=个人，company=企业）", example = "2")
    private String guarantorType;

    @Schema(description = "担保人证件类型（字典: aicrm_identity_type）", example = "2")
    private String guarantorIdType;

    @Schema(description = "担保人证件号码（加密）")
    private String guarantorIdNo;

    @Schema(description = "与被担保人关系（字典: aicrm_relation_type）")
    private String relationWithBorrower;

    @Schema(description = "担保方式（字典: aicrm_guarantee_style，joint=连带责任，general=一般保证）")
    private String guaranteeMethod;

    @Schema(description = "已用担保金额（万元）")
    private BigDecimal usedAmount;

    @Schema(description = "可用担保金额（万元）")
    private BigDecimal availableAmount;

    @Schema(description = "客户经理ID（关联 system_users.id）", example = "23298")
    private Long managerUserId;

    @Schema(description = "管理机构ID（关联 system_dept.id）", example = "25584")
    private Long branchId;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}