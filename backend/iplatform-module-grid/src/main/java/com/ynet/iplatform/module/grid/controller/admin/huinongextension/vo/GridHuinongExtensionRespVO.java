package com.ynet.iplatform.module.grid.controller.admin.huinongextension.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 惠农网格扩展 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GridHuinongExtensionRespVO {

    @Schema(description = "扩展ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "29322")
    @ExcelProperty("扩展ID")
    private Long id;

    @Schema(description = "网格ID (关联 grid_info)", requiredMode = Schema.RequiredMode.REQUIRED, example = "4292")
    @ExcelProperty("网格ID (关联 grid_info)")
    private Long gridId;

    @Schema(description = "是否为网格营销站点")
    @ExcelProperty("是否为网格营销站点")
    private Boolean isMarketingSite;

    @Schema(description = "是否为必选站点")
    @ExcelProperty("是否为必选站点")
    private Boolean isRequired;

    @Schema(description = "创建人ID", example = "15127")
    @ExcelProperty("创建人ID")
    private Long creatorId;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新人ID", example = "17699")
    @ExcelProperty("更新人ID")
    private Long updaterId;

}