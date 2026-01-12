package com.ynet.iplatform.module.grid.controller.admin.huinongextension.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 惠农网格扩展新增/修改 Request VO")
@Data
public class GridHuinongExtensionSaveReqVO {

    @Schema(description = "扩展ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "29322")
    private Long id;

    @Schema(description = "网格ID (关联 grid_info)", requiredMode = Schema.RequiredMode.REQUIRED, example = "4292")
    @NotNull(message = "网格ID (关联 grid_info)不能为空")
    private Long gridId;

    @Schema(description = "是否为网格营销站点")
    private Boolean isMarketingSite;

    @Schema(description = "是否为必选站点")
    private Boolean isRequired;

    @Schema(description = "创建人ID", example = "15127")
    private Long creatorId;

    @Schema(description = "更新人ID", example = "17699")
    private Long updaterId;

}