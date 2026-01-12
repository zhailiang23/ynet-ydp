package com.ynet.iplatform.module.infra.controller.admin.externalagent.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 外部智能体管理新增/修改 Request VO")
@Data
public class ExternalAgentSaveReqVO {

    @Schema(description = "智能体ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "14511")
    private Long id;

    @Schema(description = "智能体编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "dify_agent_001")
    @NotEmpty(message = "智能体编码不能为空")
    private String code;

    @Schema(description = "智能体名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "智能体名称不能为空")
    private String name;

    @Schema(description = "智能体描述", example = "随便")
    private String description;

    @Schema(description = "平台类型（dify, hiagent, coze等）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "平台类型（dify, hiagent, coze等）不能为空")
    private String platformType;

    @Schema(description = "访问URL", requiredMode = Schema.RequiredMode.REQUIRED, example = "https://www.iocoder.cn")
    @NotEmpty(message = "访问URL不能为空")
    private String accessUrl;

    @Schema(description = "API密钥", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "API密钥不能为空")
    private String apiKey;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "状态不能为空")
    private Integer status;

}