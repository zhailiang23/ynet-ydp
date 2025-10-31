package cn.iocoder.yudao.module.aicrm.controller.admin.customerworkinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 客户工作信息表（精简版，只包含工作相关核心字段） Response VO")
@Data
@ExcelIgnoreUnannotated
public class CustomerWorkInfoRespVO {

    @Schema(description = "工作信息主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "22993")
    @ExcelProperty("工作信息主键")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer 主表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "29539")
    @ExcelProperty("客户ID（关联 crm_customer 主表）")
    private Long customerId;

    @Schema(description = "工作单位名称", example = "赵六")
    @ExcelProperty("工作单位名称")
    private String employerName;

    @Schema(description = "单位类型（字典: aicrm_employer_type）", example = "2")
    @ExcelProperty("单位类型（字典: aicrm_employer_type）")
    private String employerType;

    @Schema(description = "所属行业")
    @ExcelProperty("所属行业")
    private String industry;

    @Schema(description = "职位/职务")
    @ExcelProperty("职位/职务")
    private String position;

    @Schema(description = "职级（字典: aicrm_position_level）")
    @ExcelProperty("职级（字典: aicrm_position_level）")
    private String positionLevel;

    @Schema(description = "所在部门")
    @ExcelProperty("所在部门")
    private String department;

    @Schema(description = "工作年限")
    @ExcelProperty("工作年限")
    private Integer workYears;

    @Schema(description = "年收入（元）")
    @ExcelProperty("年收入（元）")
    private BigDecimal annualIncome;

    @Schema(description = "月收入（元）")
    @ExcelProperty("月收入（元）")
    private BigDecimal monthlyIncome;

    @Schema(description = "是否有社保")
    @ExcelProperty("是否有社保")
    private Boolean hasSocialInsurance;

    @Schema(description = "是否有公积金")
    @ExcelProperty("是否有公积金")
    private Boolean hasHousingFund;

    @Schema(description = "工作电话")
    @ExcelProperty("工作电话")
    private String workPhone;

    @Schema(description = "工作邮箱")
    @ExcelProperty("工作邮箱")
    private String workEmail;

    @Schema(description = "备注", example = "你猜")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}