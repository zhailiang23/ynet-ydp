package com.ynet.iplatform.module.grid.controller.admin.zerodaigrid.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 零贷网格 Response VO")
@Data
public class ZerodaiGridRespVO {

    @Schema(description = "网格ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "网格编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "GRID_ZERODAI_1735012345678")
    private String gridCode;

    @Schema(description = "网格名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "XX零贷网格")
    private String gridName;

    @Schema(description = "网格类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "ZERODAI")
    private String gridType;

    @Schema(description = "所属组织ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long orgId;

    @Schema(description = "所属组织名称", example = "市场部")
    private String orgName;

    @Schema(description = "资源标签(JSON数组)", example = "[\"HIGH_QUALITY\",\"HIGH_POTENTIAL\"]")
    private String resourceTags;

    @Schema(description = "组织类型", example = "BRANCH")
    private String orgType;

    @Schema(description = "创建者", example = "1")
    private String creator;

    @Schema(description = "创建人姓名", example = "张三")
    private String creatorName;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

    @Schema(description = "网格边界(GeoJSON格式，仅详情接口返回)")
    private String boundaryGeometry;

}
