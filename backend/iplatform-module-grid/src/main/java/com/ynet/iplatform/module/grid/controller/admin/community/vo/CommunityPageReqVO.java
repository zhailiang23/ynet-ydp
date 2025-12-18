package com.ynet.iplatform.module.grid.controller.admin.community.vo;

import com.ynet.iplatform.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 社区信息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CommunityPageReqVO extends PageParam {

    @Schema(description = "社区编号", example = "COMM_1234567890")
    private String communityCode;

    @Schema(description = "社区名称", example = "阳光社区")
    private String communityName;

    @Schema(description = "状态", example = "ACTIVE")
    private String status;
}
