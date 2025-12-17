package com.ynet.iplatform.module.grid.controller.admin.tingtangcustomer.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 厅堂客户扩展 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GridTingtangCustomerRespVO {

    @Schema(description = "扩展ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "11627")
    @ExcelProperty("扩展ID")
    private Long id;

    @Schema(description = "客户ID (关联 grid_customer)", requiredMode = Schema.RequiredMode.REQUIRED, example = "16898")
    @ExcelProperty("客户ID (关联 grid_customer)")
    private Long customerId;

    @Schema(description = "性别: 男/女")
    @ExcelProperty("性别: 男/女")
    private String gender;

    @Schema(description = "客群类型 (关联字典 aicrm_customer_group: 信贷客户/财富客户/结算客户/潜力客户)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("客群类型 (关联字典 aicrm_customer_group: 信贷客户/财富客户/结算客户/潜力客户)")
    private String customerGroup;

    @Schema(description = "是否有CRM客户号 (用于转化统计)")
    @ExcelProperty("是否有CRM客户号 (用于转化统计)")
    private Boolean hasCrmNumber;

    @Schema(description = "CRM客户号", example = "11000")
    @ExcelProperty("CRM客户号")
    private String crmCustomerId;

    @Schema(description = "创建人ID", example = "14018")
    @ExcelProperty("创建人ID")
    private Long creatorId;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新人ID", example = "1999")
    @ExcelProperty("更新人ID")
    private Long updaterId;

}