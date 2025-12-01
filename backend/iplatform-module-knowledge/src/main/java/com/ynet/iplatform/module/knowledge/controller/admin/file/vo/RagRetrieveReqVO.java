package com.ynet.iplatform.module.knowledge.controller.admin.file.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

/**
 * RAG 召回请求 VO
 *
 * @author 芋道源码
 */
@Schema(description = "管理后台 - RAG 召回请求 VO")
@Data
public class RagRetrieveReqVO {

    @Schema(description = "用户问题", requiredMode = Schema.RequiredMode.REQUIRED, example = "如何使用知识库？")
    @NotBlank(message = "问题不能为空")
    private String question;

    @Schema(description = "知识库 ID 列表", requiredMode = Schema.RequiredMode.REQUIRED, example = "[1,2,3]")
    @NotEmpty(message = "知识库 ID 列表不能为空")
    private Long[] kbIds;

    @Schema(description = "返回结果数量", example = "5")
    private Integer topK = 5;
}
