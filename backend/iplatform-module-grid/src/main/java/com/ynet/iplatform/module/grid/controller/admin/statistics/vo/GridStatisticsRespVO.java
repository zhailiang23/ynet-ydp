package com.ynet.iplatform.module.grid.controller.admin.statistics.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 网格统计报表 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GridStatisticsRespVO {

    @Schema(description = "统计ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16071")
    @ExcelProperty("统计ID")
    private Long id;

    @Schema(description = "网格ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20719")
    @ExcelProperty("网格ID")
    private Long gridId;

    @Schema(description = "统计日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计日期")
    private LocalDate statDate;

    @Schema(description = "统计类型: DAILY/WEEKLY/MONTHLY", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("统计类型: DAILY/WEEKLY/MONTHLY")
    private String statType;

    @Schema(description = "客户总数", example = "18721")
    @ExcelProperty("客户总数")
    private Integer customerCount;

    @Schema(description = "新增客户数", example = "6553")
    @ExcelProperty("新增客户数")
    private Integer newCustomerCount;

    @Schema(description = "活跃客户数", example = "2202")
    @ExcelProperty("活跃客户数")
    private Integer activeCustomerCount;

    @Schema(description = "活动次数", example = "28189")
    @ExcelProperty("活动次数")
    private Integer activityCount;

    @Schema(description = "贷款申请数", example = "8428")
    @ExcelProperty("贷款申请数")
    private Integer loanApplicationCount;

    @Schema(description = "贷款批准数", example = "4642")
    @ExcelProperty("贷款批准数")
    private Integer loanApprovalCount;

    @Schema(description = "贷款金额")
    @ExcelProperty("贷款金额")
    private BigDecimal loanAmount;

    @Schema(description = "存款金额")
    @ExcelProperty("存款金额")
    private BigDecimal depositAmount;

    @Schema(description = "绩效评分")
    @ExcelProperty("绩效评分")
    private BigDecimal performanceScore;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}