package cn.iocoder.yudao.module.aicrm.controller.admin.customercredit.vo;

import java.time.LocalDate;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 客户授信信息表（零售+对公共用）分页 Request VO")
@Data
public class CustomerCreditPageReqVO extends PageParam {

    @Schema(description = "客户ID（关联 crm_customer 主表）", example = "23175")
    private Long customerId;

    @Schema(description = "统计日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] statisticsDate;

    @Schema(description = "授信协议号")
    private String creditAgreementNo;

    @Schema(description = "授信品种（字典: aicrm_credit_product_type）", example = "2")
    private String creditProductType;

    @Schema(description = "授信币种（字典: aicrm_currency_type）")
    private String currencyCode;

    @Schema(description = "授信额度（元）")
    private BigDecimal creditLimit;

    @Schema(description = "已用额度（元）")
    private BigDecimal usedLimit;

    @Schema(description = "可用额度（元）")
    private BigDecimal availableLimit;

    @Schema(description = "使用比例（%）")
    private BigDecimal usageRatio;

    @Schema(description = "授信起始日")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] creditStartDate;

    @Schema(description = "授信到期日")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] creditEndDate;

    @Schema(description = "授信类型（字典: aicrm_credit_type，comprehensive=综合授信，single=单笔授信）", example = "1")
    private String creditType;

    @Schema(description = "授信状态（字典: aicrm_credit_status，effective=生效中，expired=已到期，cancelled=已撤销，frozen=已冻结）", example = "1")
    private String creditStatus;

    @Schema(description = "授信审批日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] approveDate;

    @Schema(description = "授信审批金额（元）")
    private BigDecimal approveAmount;

    @Schema(description = "授信利率（%）")
    private BigDecimal interestRate;

    @Schema(description = "担保方式（字典: aicrm_guarantee_type）", example = "1")
    private String guaranteeType;

    @Schema(description = "授信用途")
    private String creditPurpose;

    @Schema(description = "审批人ID（关联 system_users.id）", example = "29872")
    private Long approverUserId;

    @Schema(description = "审批部门ID（关联 system_dept.id）", example = "1836")
    private Long approverDeptId;

    @Schema(description = "客户经理ID（关联 system_users.id）", example = "8477")
    private Long managerUserId;

    @Schema(description = "授信网点ID（关联 system_dept.id）", example = "13110")
    private Long branchId;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}