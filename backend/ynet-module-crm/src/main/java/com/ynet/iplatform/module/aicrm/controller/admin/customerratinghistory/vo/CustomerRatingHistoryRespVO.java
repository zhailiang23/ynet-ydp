package com.ynet.iplatform.module.aicrm.controller.admin.customerratinghistory.vo;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 客户评级调整历史 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CustomerRatingHistoryRespVO {

    @Schema(description = "历史主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "24564")
    @ExcelProperty("历史主键")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer 主表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "28853")
    @ExcelProperty("客户ID（关联 crm_customer 主表）")
    private Long customerId;

    @Schema(description = "评级ID（关联当前评级表）", example = "31023")
    @ExcelProperty("评级ID（关联当前评级表）")
    private Long ratingId;

    @Schema(description = "序号")
    @ExcelProperty("序号")
    private Integer sequenceNo;

    @Schema(description = "审批状态（字典: aicrm_rating_approval_status）", example = "2")
    @ExcelProperty("审批状态（字典: aicrm_rating_approval_status）")
    private String approvalStatus;

    @Schema(description = "评级日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("评级日期")
    private LocalDate ratingDate;

    @Schema(description = "价值等级（字典: aicrm_value_level）")
    @ExcelProperty("价值等级（字典: aicrm_value_level）")
    private String valueLevel;

    @Schema(description = "服务等级（字典: aicrm_service_level）")
    @ExcelProperty("服务等级（字典: aicrm_service_level）")
    private String serviceLevel;

    @Schema(description = "剔除风险前服务等级（字典: aicrm_service_level）")
    @ExcelProperty("剔除风险前服务等级（字典: aicrm_service_level）")
    private String serviceLevelBeforeRisk;

    @Schema(description = "风险影响因子内容")
    @ExcelProperty("风险影响因子内容")
    private String riskFactors;

    @Schema(description = "评级方式（字典: aicrm_rating_method）")
    @ExcelProperty("评级方式（字典: aicrm_rating_method）")
    private String ratingMethod;

    @Schema(description = "评级ID（老系统）", example = "18443")
    @ExcelProperty("评级ID（老系统）")
    private String custGradeId;

    @Schema(description = "生效日期")
    @ExcelProperty("生效日期")
    private LocalDate effectiveDate;

    @Schema(description = "失效日期")
    @ExcelProperty("失效日期")
    private LocalDate expiredDate;

    @Schema(description = "评估日期")
    @ExcelProperty("评估日期")
    private LocalDate evaluateDate;

    @Schema(description = "客户等级（老系统字段）")
    @ExcelProperty("客户等级（老系统字段）")
    private String custGrade;

    @Schema(description = "客户等级类型", example = "1")
    @ExcelProperty("客户等级类型")
    private String custGradeType;

    @Schema(description = "机构编码")
    @ExcelProperty("机构编码")
    private String orgCode;

    @Schema(description = "机构名称", example = "芋艿")
    @ExcelProperty("机构名称")
    private String orgName;

    @Schema(description = "客户数量")
    @ExcelProperty("客户数量")
    private Integer custCnt;

    @Schema(description = "评级分数")
    @ExcelProperty("评级分数")
    private BigDecimal ratingScore;

    @Schema(description = "评级模型版本")
    @ExcelProperty("评级模型版本")
    private String ratingModelVersion;

    @Schema(description = "风险等级（字典: aicrm_risk_level）")
    @ExcelProperty("风险等级（字典: aicrm_risk_level）")
    private String riskLevel;

    @Schema(description = "调整原因", example = "不香")
    @ExcelProperty("调整原因")
    private String changeReason;

    @Schema(description = "调整类型（字典: aicrm_rating_change_type，upgrade=升级，downgrade=降级，maintain=维持）", example = "2")
    @ExcelProperty("调整类型（字典: aicrm_rating_change_type，upgrade=升级，downgrade=降级，maintain=维持）")
    private String changeType;

    @Schema(description = "调整前价值等级")
    @ExcelProperty("调整前价值等级")
    private String previousValueLevel;

    @Schema(description = "调整前服务等级")
    @ExcelProperty("调整前服务等级")
    private String previousServiceLevel;

    @Schema(description = "备注", example = "你说的对")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}