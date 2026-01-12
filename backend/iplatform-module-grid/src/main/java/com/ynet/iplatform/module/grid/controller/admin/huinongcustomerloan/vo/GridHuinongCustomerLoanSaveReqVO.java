package com.ynet.iplatform.module.grid.controller.admin.huinongcustomerloan.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 惠农贷款客户扩展新增/修改 Request VO")
@Data
public class GridHuinongCustomerLoanSaveReqVO {

    @Schema(description = "扩展ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "773")
    private Long id;

    @Schema(description = "客户ID (关联 grid_customer)", requiredMode = Schema.RequiredMode.REQUIRED, example = "22175")
    @NotNull(message = "客户ID (关联 grid_customer)不能为空")
    private Long customerId;

    @Schema(description = "网格ID（主站点）", example = "1")
    private Long gridId;

    @Schema(description = "经度", example = "113.12345")
    private Double longitude;

    @Schema(description = "纬度", example = "23.12345")
    private Double latitude;

    @Schema(description = "客户大类 (关联字典 aicrm_customer_category)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "客户大类不能为空")
    private String customerCategory;

    @Schema(description = "细分类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotBlank(message = "细分类型不能为空")
    private String subdivisionType;

    @Schema(description = "经营地址", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "经营地址不能为空")
    private String businessAddress;

    @Schema(description = "性别: 男/女", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "性别不能为空")
    private String gender;

    @Schema(description = "客户情况", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "客户情况不能为空")
    private String customerSituation;

    @Schema(description = "经营年限", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "经营年限不能为空")
    private Integer businessYears;

    @Schema(description = "当前融资情况", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "当前融资情况不能为空")
    private String currentFinancing;

    @Schema(description = "信贷产品需求", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "信贷产品需求不能为空")
    private String creditDemand;

    @Schema(description = "需求月份 (1-12)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "需求月份不能为空")
    private String demandMonth;

    @Schema(description = "需求旬 (关联字典 aicrm_demand_period: 上旬/中旬/下旬)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "需求旬不能为空")
    private String demandPeriod;

    @Schema(description = "业务进展", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "业务进展不能为空")
    private String businessProgress;

    @Schema(description = "客户来源 (关联字典 aicrm_customer_source: 行内客户/外拓客户)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "客户来源不能为空")
    private String customerSource;

    @Schema(description = "是否进件")
    private Boolean isApplied;

    @Schema(description = "进件时间")
    private LocalDateTime applyTime;

    @Schema(description = "是否审批通过")
    private Boolean isApproved;

    @Schema(description = "审批通过时间")
    private LocalDateTime approveTime;

    @Schema(description = "贷款产品名称", example = "赵六")
    private String loanProductName;

    @Schema(description = "贷款金额 (元)")
    private BigDecimal loanAmount;

    @Schema(description = "授信额度 (元)")
    private BigDecimal creditLimit;

    @Schema(description = "贷款余额 (元)")
    private BigDecimal loanBalance;

    @Schema(description = "逾期状态", example = "2")
    private String overdueStatus;

    @Schema(description = "是否正式客户")
    private Boolean isFormalCustomer;

    @Schema(description = "创建人ID", example = "15328")
    private Long creatorId;

    @Schema(description = "更新人ID", example = "29525")
    private Long updaterId;

}