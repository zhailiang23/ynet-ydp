package com.ynet.iplatform.module.knowledge.controller.admin.base.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 知识库新增/修改 Request VO")
@Data
public class KnowledgeBaseSaveReqVO {

    @Schema(description = "知识库ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "28433")
    private Long id;

    @Schema(description = "知识库名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "知识库名称不能为空")
    private String name;

    @Schema(description = "知识库描述", example = "你说的对")
    private String description;

    @Schema(description = "状态（0正常 1停用）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "状态（0正常 1停用）不能为空")
    private Integer status;

    @Schema(description = "是否公共知识库（默认true）", example = "true")
    private Boolean isPublic;

}