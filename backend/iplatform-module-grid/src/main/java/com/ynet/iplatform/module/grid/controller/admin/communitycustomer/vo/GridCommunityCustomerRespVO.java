package com.ynet.iplatform.module.grid.controller.admin.communitycustomer.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 社区客户扩展 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GridCommunityCustomerRespVO {

    @Schema(description = "扩展ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "859")
    @ExcelProperty("扩展ID")
    private Long id;

    @Schema(description = "客户ID (关联 grid_customer)", requiredMode = Schema.RequiredMode.REQUIRED, example = "7456")
    @ExcelProperty("客户ID (关联 grid_customer)")
    private Long customerId;

    @Schema(description = "家庭成员数")
    @ExcelProperty("家庭成员数")
    private Integer familyMembers;

    @Schema(description = "住房类型", example = "1")
    @ExcelProperty("住房类型")
    private String housingType;

    @Schema(description = "月收入 (元)")
    @ExcelProperty("月收入 (元)")
    private BigDecimal monthlyIncome;

    @Schema(description = "创建人ID", example = "25814")
    @ExcelProperty("创建人ID")
    private Long creatorId;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新人ID", example = "18958")
    @ExcelProperty("更新人ID")
    private Long updaterId;

}