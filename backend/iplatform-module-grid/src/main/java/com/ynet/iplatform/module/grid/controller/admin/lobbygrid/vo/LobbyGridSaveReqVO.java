package com.ynet.iplatform.module.grid.controller.admin.lobbygrid.vo;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * 厅堂网格 - 创建/更新请求 VO
 *
 * @author 易诚源码
 */
@Schema(description = "管理后台 - 厅堂网格创建/更新请求 VO")
@Data
public class LobbyGridSaveReqVO {

    @Schema(description = "网格ID", example = "1")
    private Long id;

    @Schema(description = "所属组织ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "所属组织不能为空")
    private Long orgId;

    @Schema(description = "管理层级", example = "一级")
    private String managementLevel;

    @Schema(description = "所属区域", example = "郑州市")
    private String region;

    @Schema(description = "行政片区", example = "金水区")
    private String district;

    @Schema(description = "位置名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "郑州分行营业部")
    @NotBlank(message = "位置名称不能为空")
    private String locationName;

    @Schema(description = "经度", requiredMode = Schema.RequiredMode.REQUIRED, example = "113.6234")
    @NotNull(message = "经度不能为空")
    private BigDecimal longitude;

    @Schema(description = "纬度", requiredMode = Schema.RequiredMode.REQUIRED, example = "34.7490")
    @NotNull(message = "纬度不能为空")
    private BigDecimal latitude;

    @Schema(description = "网点类型", example = "一级支行")
    private String outletType;

    @Schema(description = "网格常住居民数", example = "5000")
    private Integer residentCount;

    @Schema(description = "周围商户数", example = "200")
    private Integer merchantCount;

    @Schema(description = "半径（米）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1000")
    @NotNull(message = "半径不能为空")
    private Integer radiusMeters;

    @Schema(description = "网格边界（GeoJSON Polygon）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "网格边界不能为空")
    private String boundaryGeometry;

}
