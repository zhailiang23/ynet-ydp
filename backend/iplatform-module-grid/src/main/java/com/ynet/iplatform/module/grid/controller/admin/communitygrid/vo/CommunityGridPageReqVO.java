package com.ynet.iplatform.module.grid.controller.admin.communitygrid.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;

@Schema(description = "管理后台 - 社区网格分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class CommunityGridPageReqVO extends PageParam {

    @Schema(description = "网格编号")
    private String gridCode;

    @Schema(description = "网格名称")
    private String gridName;

}
