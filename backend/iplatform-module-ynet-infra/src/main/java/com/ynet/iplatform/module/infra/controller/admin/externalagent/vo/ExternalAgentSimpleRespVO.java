package com.ynet.iplatform.module.infra.controller.admin.externalagent.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 外部智能体管理精简信息 Response VO")
@Data
public class ExternalAgentSimpleRespVO {

    @Schema(description = "智能体ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long id;

    @Schema(description = "智能体名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    private String name;

}
