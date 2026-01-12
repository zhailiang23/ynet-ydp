package com.ynet.iplatform.module.aicrm.controller.admin.cohort.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 客群列表查询 Request VO
 *
 * @author 易诚源码
 */
@Schema(description = "管理后台 - 客群列表查询 Request VO")
@Data
public class CohortListReqVO {

    @Schema(description = "客群类型", example = "0")
    private Integer type = 0;

    @Schema(description = "页码", example = "1")
    private Integer pageNo = 1;

    @Schema(description = "每页条数", example = "100")
    private Integer pageSize = 100;

}
