package com.ynet.iplatform.module.grid.controller.admin.zerodaigrid.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "管理后台 - 零贷网格新增/修改 Request VO")
@Data
public class ZerodaiGridSaveReqVO {

    @Schema(description = "网格ID", example = "1")
    private Long id;

    @Schema(description = "网格名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "XX零贷网格")
    @NotBlank(message = "网格名称不能为空")
    private String gridName;

    @Schema(description = "所属组织ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "所属组织不能为空")
    private Long orgId;

    @Schema(description = "资源标签(JSON数组)", requiredMode = Schema.RequiredMode.REQUIRED, example = "[\"HIGH_QUALITY\",\"HIGH_POTENTIAL\"]")
    @NotBlank(message = "资源标签不能为空")
    private String resourceTags;

    @Schema(description = "组织类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "BRANCH")
    @NotBlank(message = "组织类型不能为空")
    private String orgType;

    @Schema(description = "网格边界(GeoJSON格式)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "网格边界不能为空")
    private String boundaryGeometry;

}
