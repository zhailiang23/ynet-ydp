package com.ynet.iplatform.module.grid.controller.admin.communitygrid.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "管理后台 - 社区网格新增/修改 Request VO")
@Data
public class CommunityGridSaveReqVO {

    @Schema(description = "网格ID", example = "1")
    private Long id;

    @Schema(description = "网格名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "XX社区网格")
    @NotBlank(message = "网格名称不能为空")
    private String gridName;

    @Schema(description = "归属机构ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "归属机构不能为空")
    private Long orgId;

    @Schema(description = "责任人用户ID", example = "1")
    private Long managerUserId;

    @Schema(description = "团队人数", requiredMode = Schema.RequiredMode.REQUIRED, example = "5")
    @NotNull(message = "团队人数不能为空")
    private Integer teamCount;

    @Schema(description = "网格状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "ACTIVE")
    @NotBlank(message = "网格状态不能为空")
    private String status;

    @Schema(description = "网格边界(GeoJSON格式)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "网格边界不能为空")
    private String boundaryGeometry;

}
