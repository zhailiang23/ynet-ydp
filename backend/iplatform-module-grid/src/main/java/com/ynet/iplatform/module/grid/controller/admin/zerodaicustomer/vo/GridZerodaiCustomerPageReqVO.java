package com.ynet.iplatform.module.grid.controller.admin.zerodaicustomer.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 零贷客户扩展分页 Request VO")
@Data
public class GridZerodaiCustomerPageReqVO extends PageParam {

    @Schema(description = "客户ID (关联 grid_customer)", example = "29250")
    private Long customerId;

    @Schema(description = "业务类型 (关联字典 aicrm_business_type)", example = "1")
    private String businessType;

    @Schema(description = "年营业额 (元)")
    private BigDecimal annualRevenue;

    @Schema(description = "信用评级")
    private String creditRating;

    @Schema(description = "是否进件")
    private Boolean isApplied;

    @Schema(description = "是否完件")
    private Boolean isCompleted;

    @Schema(description = "贷款投放金额 (元)")
    private BigDecimal loanAmount;

    @Schema(description = "贷款余额 (元)")
    private BigDecimal loanBalance;

    @Schema(description = "是否受保护")
    private Boolean archiveProtected;

    @Schema(description = "创建人ID", example = "27862")
    private Long creatorId;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新人ID", example = "27823")
    private Long updaterId;

}