package com.ynet.iplatform.module.grid.controller.admin.zerodaigrid.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;

@Schema(description = "管理后台 - 零贷网格分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ZerodaiGridPageReqVO extends PageParam {

    @Schema(description = "网格名称", example = "XX网格")
    private String gridName;

    @Schema(description = "所属组织ID", example = "1")
    private Long orgId;

    @Schema(description = "资源标签", example = "HIGH_QUALITY")
    private String resourceTags;

    @Schema(description = "创建人", example = "张三")
    private String creator;

}
