package cn.iocoder.yudao.module.aicrm.controller.admin.customerbusinessinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 客户经营信息表（精简版，只包含经营相关核心字段）新增/修改 Request VO")
@Data
public class CustomerBusinessInfoSaveReqVO {

    @Schema(description = "经营信息主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "266")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer 主表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "20561")
    @NotNull(message = "客户ID（关联 crm_customer 主表）不能为空")
    private Long customerId;

    @Schema(description = "经营主体名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "经营主体名称不能为空")
    private String businessName;

    @Schema(description = "经营类型（字典: aicrm_business_type）", example = "1")
    private String businessType;

    @Schema(description = "营业执照号/统一社会信用代码")
    private String businessLicenseNo;

    @Schema(description = "经营范围")
    private String businessScope;

    @Schema(description = "所属行业")
    private String industry;

    @Schema(description = "经营规模（字典: aicrm_business_scale）")
    private String businessScale;

    @Schema(description = "经营状态（字典: aicrm_business_status）", example = "2")
    private String businessStatus;

    @Schema(description = "注册资本（元）")
    private BigDecimal registeredCapital;

    @Schema(description = "员工人数", example = "12402")
    private Integer employeeCount;

    @Schema(description = "年营业额（元）")
    private BigDecimal annualRevenue;

    @Schema(description = "月营业额（元）")
    private BigDecimal monthlyRevenue;

    @Schema(description = "年利润（元）")
    private BigDecimal annualProfit;

    @Schema(description = "税务登记号")
    private String taxRegistrationNo;

    @Schema(description = "是否一般纳税人")
    private Boolean isGeneralTaxpayer;

    @Schema(description = "备注", example = "随便")
    private String remark;

}