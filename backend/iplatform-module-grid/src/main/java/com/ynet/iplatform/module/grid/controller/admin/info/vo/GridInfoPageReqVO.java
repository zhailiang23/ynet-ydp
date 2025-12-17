package com.ynet.iplatform.module.grid.controller.admin.info.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 网格信息分页 Request VO")
@Data
public class GridInfoPageReqVO extends PageParam {

    @Schema(description = "网格编号")
    private String gridCode;

    @Schema(description = "网格名称", example = "芋艿")
    private String gridName;

    @Schema(description = "网格类型: HUINONG/TINGTANG/COMMUNITY/ZERODAI", example = "2")
    private String gridType;

    @Schema(description = "所属机构ID (关联 system_dept)", example = "18885")
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

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新人ID", example = "14403")
    private Long updaterId;

}