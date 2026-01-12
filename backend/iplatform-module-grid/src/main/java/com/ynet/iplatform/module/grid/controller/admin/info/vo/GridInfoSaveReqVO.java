package com.ynet.iplatform.module.grid.controller.admin.info.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 网格信息新增/修改 Request VO")
@Data
public class GridInfoSaveReqVO {

    @Schema(description = "网格ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10985")
    private Long id;

    @Schema(description = "网格编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "网格编号不能为空")
    private String gridCode;

    @Schema(description = "网格名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "网格名称不能为空")
    private String gridName;

    @Schema(description = "网格类型: HUINONG/TINGTANG/COMMUNITY/ZERODAI", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "网格类型: HUINONG/TINGTANG/COMMUNITY/ZERODAI不能为空")
    private String gridType;

    @Schema(description = "所属机构ID (关联 system_dept)", requiredMode = Schema.RequiredMode.REQUIRED, example = "18885")
    @NotNull(message = "所属机构ID (关联 system_dept)不能为空")
    private Long orgId;

    @Schema(description = "父网格ID (零贷层级结构使用)", example = "20986")
    private Long parentId;

    @Schema(description = "网格中心坐标 (经纬度)")
    private byte[] centerPoint;

    @Schema(description = "网格边界 (POLYGON 或 CIRCLE)")
    private byte[] boundaryGeometry;

    @Schema(description = "网格半径(米) - 惠农固定3000, 厅堂默认1000")
    private Integer radiusMeters;

    @Schema(description = "网格常住居民数 (厅堂使用)", example = "9875")
    private Integer residentCount;

    @Schema(description = "网格周围商户数 (厅堂使用)", example = "31741")
    private Integer merchantCount;

    @Schema(description = "网格状态: ACTIVE/INACTIVE/PENDING_APPROVAL", example = "1")
    private String status;

    @Schema(description = "创建人ID", example = "6130")
    private Long creatorId;

    @Schema(description = "更新人ID", example = "14403")
    private Long updaterId;

}