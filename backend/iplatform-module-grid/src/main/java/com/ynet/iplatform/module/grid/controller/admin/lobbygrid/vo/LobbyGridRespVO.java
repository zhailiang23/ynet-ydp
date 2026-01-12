package com.ynet.iplatform.module.grid.controller.admin.lobbygrid.vo;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 厅堂网格 - 响应 VO
 *
 * @author 易诚源码
 */
@Schema(description = "管理后台 - 厅堂网格响应 VO")
@Data
public class LobbyGridRespVO {

    @Schema(description = "网格ID", example = "1")
    private Long id;

    @Schema(description = "网格编号", example = "GRID_LOBBY_1735012345678")
    private String gridCode;

    @Schema(description = "所属组织ID", example = "1")
    private Long orgId;

    @Schema(description = "所属组织名称（三级）", example = "郑州分行营业部")
    private String orgName;

    @Schema(description = "二级行ID", example = "2")
    private Long secondLevelId;

    @Schema(description = "二级行名称", example = "郑州分行")
    private String secondLevelName;

    @Schema(description = "一级行ID", example = "3")
    private Long firstLevelId;

    @Schema(description = "一级行名称", example = "河南省分行")
    private String firstLevelName;

    @Schema(description = "管理层级", example = "一级")
    private String managementLevel;

    @Schema(description = "所属区域", example = "郑州市")
    private String region;

    @Schema(description = "行政片区", example = "金水区")
    private String district;

    @Schema(description = "位置名称", example = "郑州分行营业部")
    private String locationName;

    @Schema(description = "经度", example = "113.6234")
    private BigDecimal longitude;

    @Schema(description = "纬度", example = "34.7490")
    private BigDecimal latitude;

    @Schema(description = "网点类型", example = "一级支行")
    private String outletType;

    @Schema(description = "网格常住居民数", example = "5000")
    private Integer residentCount;

    @Schema(description = "周围商户数", example = "200")
    private Integer merchantCount;

    @Schema(description = "半径（米）", example = "1000")
    private Integer radiusMeters;

    @Schema(description = "创建人", example = "1")
    private String creator;

    @Schema(description = "创建人姓名", example = "管理员")
    private String creatorName;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "网格边界（GeoJSON，仅详情接口返回）")
    private String boundaryGeometry;

}
