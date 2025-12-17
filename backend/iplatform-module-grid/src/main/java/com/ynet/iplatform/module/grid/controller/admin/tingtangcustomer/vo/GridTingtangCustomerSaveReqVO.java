package com.ynet.iplatform.module.grid.controller.admin.tingtangcustomer.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 厅堂客户扩展新增/修改 Request VO")
@Data
public class GridTingtangCustomerSaveReqVO {

    @Schema(description = "扩展ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "11627")
    private Long id;

    @Schema(description = "客户ID (关联 grid_customer)", requiredMode = Schema.RequiredMode.REQUIRED, example = "16898")
    @NotNull(message = "客户ID (关联 grid_customer)不能为空")
    private Long customerId;

    @Schema(description = "性别: 男/女")
    private String gender;

    @Schema(description = "客群类型 (关联字典 aicrm_customer_group: 信贷客户/财富客户/结算客户/潜力客户)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "客群类型 (关联字典 aicrm_customer_group: 信贷客户/财富客户/结算客户/潜力客户)不能为空")
    private String customerGroup;

    @Schema(description = "是否有CRM客户号 (用于转化统计)")
    private Boolean hasCrmNumber;

    @Schema(description = "CRM客户号", example = "11000")
    private String crmCustomerId;

    @Schema(description = "创建人ID", example = "14018")
    private Long creatorId;

    @Schema(description = "更新人ID", example = "1999")
    private Long updaterId;

}