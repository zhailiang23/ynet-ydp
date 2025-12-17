package com.ynet.iplatform.module.grid.controller.admin.statistics.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 网格统计报表分页 Request VO")
@Data
public class GridStatisticsPageReqVO extends PageParam {

    @Schema(description = "网格ID", example = "20719")
    private Long gridId;

    @Schema(description = "统计日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] statDate;

    @Schema(description = "统计类型: DAILY/WEEKLY/MONTHLY", example = "1")
    private String statType;

    @Schema(description = "客户总数", example = "18721")
    private Integer customerCount;

    @Schema(description = "新增客户数", example = "6553")
    private Integer newCustomerCount;

    @Schema(description = "活跃客户数", example = "2202")
    private Integer activeCustomerCount;

    @Schema(description = "活动次数", example = "28189")
    private Integer activityCount;

    @Schema(description = "贷款申请数", example = "8428")
    private Integer loanApplicationCount;

    @Schema(description = "贷款批准数", example = "4642")
    private Integer loanApprovalCount;

    @Schema(description = "贷款金额")
    private BigDecimal loanAmount;

    @Schema(description = "存款金额")
    private BigDecimal depositAmount;

    @Schema(description = "绩效评分")
    private BigDecimal performanceScore;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}