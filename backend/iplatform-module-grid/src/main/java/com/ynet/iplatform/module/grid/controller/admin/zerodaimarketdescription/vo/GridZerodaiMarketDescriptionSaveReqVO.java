package com.ynet.iplatform.module.grid.controller.admin.zerodaimarketdescription.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 零贷市场描述新增/修改 Request VO")
@Data
public class GridZerodaiMarketDescriptionSaveReqVO {

    @Schema(description = "市场描述ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "29655")
    private Long id;

    @Schema(description = "网格ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20527")
    @NotNull(message = "网格ID不能为空")
    private Long gridId;

    @Schema(description = "市场名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "市场名称不能为空")
    private String marketName;

    @Schema(description = "市场类型 (关联字典 aicrm_market_type)", example = "2")
    private String marketType;

    @Schema(description = "市场位置坐标")
    private byte[] location;

    @Schema(description = "市场地址")
    private String address;

    @Schema(description = "经营范围")
    private String businessScope;

    @Schema(description = "商户数量", example = "9341")
    private Integer merchantCount;

    @Schema(description = "日均客流量")
    private Integer dailyTraffic;

    @Schema(description = "市场特点")
    private String marketFeatures;

    @Schema(description = "目标客户群体")
    private String targetCustomers;

    @Schema(description = "潜力分析")
    private String potentialAnalysis;

    @Schema(description = "客户经理ID", example = "18547")
    private Long managerId;

    @Schema(description = "创建人ID", example = "11787")
    private Long creatorId;

    @Schema(description = "更新人ID", example = "16604")
    private Long updaterId;

}