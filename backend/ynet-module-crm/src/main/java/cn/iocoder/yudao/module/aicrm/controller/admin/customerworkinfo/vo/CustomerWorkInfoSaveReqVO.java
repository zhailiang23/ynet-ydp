package cn.iocoder.yudao.module.aicrm.controller.admin.customerworkinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 客户工作信息表（精简版，只包含工作相关核心字段）新增/修改 Request VO")
@Data
public class CustomerWorkInfoSaveReqVO {

    @Schema(description = "工作信息主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "22993")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer 主表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "29539")
    @NotNull(message = "客户ID（关联 crm_customer 主表）不能为空")
    private Long customerId;

    @Schema(description = "工作单位名称", example = "赵六")
    private String employerName;

    @Schema(description = "单位类型（字典: aicrm_employer_type）", example = "2")
    private String employerType;

    @Schema(description = "所属行业")
    private String industry;

    @Schema(description = "职位/职务")
    private String position;

    @Schema(description = "职级（字典: aicrm_position_level）")
    private String positionLevel;

    @Schema(description = "所在部门")
    private String department;

    @Schema(description = "工作年限")
    private Integer workYears;

    @Schema(description = "年收入（元）")
    private BigDecimal annualIncome;

    @Schema(description = "月收入（元）")
    private BigDecimal monthlyIncome;

    @Schema(description = "是否有社保")
    private Boolean hasSocialInsurance;

    @Schema(description = "是否有公积金")
    private Boolean hasHousingFund;

    @Schema(description = "工作电话")
    private String workPhone;

    @Schema(description = "工作邮箱")
    private String workEmail;

    @Schema(description = "备注", example = "你猜")
    private String remark;

}