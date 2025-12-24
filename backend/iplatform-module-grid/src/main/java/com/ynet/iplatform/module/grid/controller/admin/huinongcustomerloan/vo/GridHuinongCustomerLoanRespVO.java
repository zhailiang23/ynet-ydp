package com.ynet.iplatform.module.grid.controller.admin.huinongcustomerloan.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 惠农贷款客户扩展 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GridHuinongCustomerLoanRespVO {

    @Schema(description = "扩展ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "773")
    @ExcelProperty("扩展ID")
    private Long id;

    @Schema(description = "客户ID (关联 grid_customer)", requiredMode = Schema.RequiredMode.REQUIRED, example = "22175")
    @ExcelProperty("客户ID (关联 grid_customer)")
    private Long customerId;

    @Schema(description = "站点ID (通过 grid_customer.grid_id 反查)", example = "123")
    private Long stationId;

    @Schema(description = "客户姓名")
    @ExcelProperty("客户姓名")
    private String customerName;

    @Schema(description = "手机号码")
    @ExcelProperty("手机号码")
    private String phone;

    @Schema(description = "客户大类 (关联字典 aicrm_customer_category)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("客户大类 (关联字典 aicrm_customer_category)")
    private String customerCategory;

    @Schema(description = "细分类型", example = "1")
    @ExcelProperty("细分类型")
    private String subdivisionType;

    @Schema(description = "经营地址")
    @ExcelProperty("经营地址")
    private String businessAddress;

    @Schema(description = "性别: 男/女")
    @ExcelProperty("性别: 男/女")
    private String gender;

    @Schema(description = "客户情况")
    @ExcelProperty("客户情况")
    private String customerSituation;

    @Schema(description = "经营年限")
    @ExcelProperty("经营年限")
    private Integer businessYears;

    @Schema(description = "当前融资情况")
    @ExcelProperty("当前融资情况")
    private String currentFinancing;

    @Schema(description = "信贷产品需求")
    @ExcelProperty("信贷产品需求")
    private String creditDemand;

    @Schema(description = "需求月份 (1-12)")
    @ExcelProperty("需求月份 (1-12)")
    private String demandMonth;

    @Schema(description = "需求旬 (关联字典 aicrm_demand_period: 上旬/中旬/下旬)")
    @ExcelProperty("需求旬 (关联字典 aicrm_demand_period: 上旬/中旬/下旬)")
    private String demandPeriod;

    @Schema(description = "业务进展")
    @ExcelProperty("业务进展")
    private String businessProgress;

    @Schema(description = "客户来源 (关联字典 aicrm_customer_source: 行内客户/外拓客户)")
    @ExcelProperty("客户来源 (关联字典 aicrm_customer_source: 行内客户/外拓客户)")
    private String customerSource;

    @Schema(description = "是否进件")
    @ExcelProperty("是否进件")
    private Boolean isApplied;

    @Schema(description = "进件时间")
    @ExcelProperty("进件时间")
    private LocalDateTime applyTime;

    @Schema(description = "是否审批通过")
    @ExcelProperty("是否审批通过")
    private Boolean isApproved;

    @Schema(description = "审批通过时间")
    @ExcelProperty("审批通过时间")
    private LocalDateTime approveTime;

    @Schema(description = "贷款产品名称", example = "赵六")
    @ExcelProperty("贷款产品名称")
    private String loanProductName;

    @Schema(description = "贷款金额 (元)")
    @ExcelProperty("贷款金额 (元)")
    private BigDecimal loanAmount;

    @Schema(description = "授信额度 (元)")
    @ExcelProperty("授信额度 (元)")
    private BigDecimal creditLimit;

    @Schema(description = "贷款余额 (元)")
    @ExcelProperty("贷款余额 (元)")
    private BigDecimal loanBalance;

    @Schema(description = "逾期状态", example = "2")
    @ExcelProperty("逾期状态")
    private String overdueStatus;

    @Schema(description = "是否正式客户")
    @ExcelProperty("是否正式客户")
    private Boolean isFormalCustomer;

    @Schema(description = "创建人ID", example = "15328")
    @ExcelProperty("创建人ID")
    private Long creatorId;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新人ID", example = "29525")
    @ExcelProperty("更新人ID")
    private Long updaterId;

    @Schema(description = "经度 (从 grid_customer.location 提取)", example = "113.625368")
    private Double longitude;

    @Schema(description = "纬度 (从 grid_customer.location 提取)", example = "34.746611")
    private Double latitude;

}