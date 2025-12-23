package com.ynet.iplatform.module.grid.controller.admin.lobbygrid.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 厅堂网格 - 分页查询请求 VO
 *
 * @author 易诚源码
 */
@Schema(description = "管理后台 - 厅堂网格分页查询请求 VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class LobbyGridPageReqVO extends PageParam {

    @Schema(description = "所属组织ID", example = "1")
    private Long orgId;

}
