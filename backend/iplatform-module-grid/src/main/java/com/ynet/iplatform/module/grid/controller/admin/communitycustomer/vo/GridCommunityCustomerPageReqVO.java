package com.ynet.iplatform.module.grid.controller.admin.communitycustomer.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 社区客户扩展分页 Request VO")
@Data
public class GridCommunityCustomerPageReqVO extends PageParam {

    @Schema(description = "客户ID (关联 grid_customer)", example = "7456")
    private Long customerId;

    @Schema(description = "家庭成员数")
    private Integer familyMembers;

    @Schema(description = "住房类型", example = "1")
    private String housingType;

    @Schema(description = "月收入 (元)")
    private BigDecimal monthlyIncome;

    @Schema(description = "创建人ID", example = "25814")
    private Long creatorId;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新人ID", example = "18958")
    private Long updaterId;

}