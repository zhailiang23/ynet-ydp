package com.ynet.iplatform.module.grid.controller.admin.info.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 网格信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GridInfoRespVO {

    @Schema(description = "网格ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10985")
    @ExcelProperty("网格ID")
    private Long id;

    @Schema(description = "网格编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("网格编号")
    private String gridCode;

    @Schema(description = "网格名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("网格名称")
    private String gridName;

    @Schema(description = "网格类型: HUINONG/TINGTANG/COMMUNITY/ZERODAI", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("网格类型: HUINONG/TINGTANG/COMMUNITY/ZERODAI")
    private String gridType;

    @Schema(description = "所属机构ID (关联 system_dept)", requiredMode = Schema.RequiredMode.REQUIRED, example = "18885")
    @ExcelProperty("所属机构ID (关联 system_dept)")
    private Long orgId;

    @Schema(description = "父网格ID (零贷层级结构使用)", example = "20986")
    @ExcelProperty("父网格ID (零贷层级结构使用)")
    private Long parentId;

    @Schema(description = "网格中心坐标 (经纬度)")
    @ExcelProperty("网格中心坐标 (经纬度)")
    private byte[] centerPoint;

    @Schema(description = "网格边界 (POLYGON 或 CIRCLE)")
    @ExcelProperty("网格边界 (POLYGON 或 CIRCLE)")
    private byte[] boundaryGeometry;

    @Schema(description = "网格半径(米) - 惠农固定3000, 厅堂默认1000")
    @ExcelProperty("网格半径(米) - 惠农固定3000, 厅堂默认1000")
    private Integer radiusMeters;

    @Schema(description = "网格常住居民数 (厅堂使用)", example = "9875")
    @ExcelProperty("网格常住居民数 (厅堂使用)")
    private Integer residentCount;

    @Schema(description = "网格周围商户数 (厅堂使用)", example = "31741")
    @ExcelProperty("网格周围商户数 (厅堂使用)")
    private Integer merchantCount;

    @Schema(description = "网格状态: ACTIVE/INACTIVE/PENDING_APPROVAL", example = "1")
    @ExcelProperty("网格状态: ACTIVE/INACTIVE/PENDING_APPROVAL")
    private String status;

    @Schema(description = "创建人ID", example = "6130")
    @ExcelProperty("创建人ID")
    private Long creatorId;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新人ID", example = "14403")
    @ExcelProperty("更新人ID")
    private Long updaterId;

}