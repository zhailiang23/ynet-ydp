package com.ynet.iplatform.module.grid.controller.admin.huinongstation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 惠农站点信息导入 Response VO")
@Data
@Builder
public class GridHuinongStationImportRespVO {

    @Schema(description = "创建成功的站点编号数组", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<String> createStationCodes;

    @Schema(description = "更新成功的站点编号数组", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<String> updateStationCodes;

    @Schema(description = "导入失败的站点集合，key 为站点编号，value 为失败原因", requiredMode = Schema.RequiredMode.REQUIRED)
    private Map<String, String> failureStationCodes;

    @Schema(description = "自动创建的网格数量", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer createdGridCount;

}
