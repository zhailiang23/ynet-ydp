package com.ynet.iplatform.module.grid.controller.admin.tingtangcustomer.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 厅堂客户扩展分页 Request VO")
@Data
public class GridTingtangCustomerPageReqVO extends PageParam {

    @Schema(description = "客户ID (关联 grid_customer)", example = "16898")
    private Long customerId;

    @Schema(description = "性别: 男/女")
    private String gender;

    @Schema(description = "客群类型 (关联字典 aicrm_customer_group: 信贷客户/财富客户/结算客户/潜力客户)")
    private String customerGroup;

    @Schema(description = "是否有CRM客户号 (用于转化统计)")
    private Boolean hasCrmNumber;

    @Schema(description = "CRM客户号", example = "11000")
    private String crmCustomerId;

    @Schema(description = "创建人ID", example = "14018")
    private Long creatorId;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新人ID", example = "1999")
    private Long updaterId;

}