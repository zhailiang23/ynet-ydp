package com.ynet.iplatform.module.grid.controller.admin.zerodaicustomer.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 零贷客户扩展 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GridZerodaiCustomerRespVO {

    @Schema(description = "扩展ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25218")
    @ExcelProperty("扩展ID")
    private Long id;

    @Schema(description = "客户ID (关联 grid_customer)", requiredMode = Schema.RequiredMode.REQUIRED, example = "29250")
    @ExcelProperty("客户ID (关联 grid_customer)")
    private Long customerId;

    @Schema(description = "业务类型 (关联字典 aicrm_business_type)", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("业务类型 (关联字典 aicrm_business_type)")
    private String businessType;

    @Schema(description = "年营业额 (元)")
    @ExcelProperty("年营业额 (元)")
    private BigDecimal annualRevenue;

    @Schema(description = "信用评级")
    @ExcelProperty("信用评级")
    private String creditRating;

    @Schema(description = "是否进件")
    @ExcelProperty("是否进件")
    private Boolean isApplied;

    @Schema(description = "是否完件")
    @ExcelProperty("是否完件")
    private Boolean isCompleted;

    @Schema(description = "贷款投放金额 (元)")
    @ExcelProperty("贷款投放金额 (元)")
    private BigDecimal loanAmount;

    @Schema(description = "贷款余额 (元)")
    @ExcelProperty("贷款余额 (元)")
    private BigDecimal loanBalance;

    @Schema(description = "是否受保护")
    @ExcelProperty("是否受保护")
    private Boolean archiveProtected;

    @Schema(description = "创建人ID", example = "27862")
    @ExcelProperty("创建人ID")
    private Long creatorId;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新人ID", example = "27823")
    @ExcelProperty("更新人ID")
    private Long updaterId;

}