package com.ynet.iplatform.module.knowledge.controller.admin.file.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * RAG 召回响应 VO
 *
 * @author 易诚源码
 */
@Schema(description = "管理后台 - RAG 召回响应 VO")
@Data
public class RagRetrieveRespVO {

    @Schema(description = "是否成功", example = "true")
    private Boolean success;

    @Schema(description = "消息", example = "成功检索到 5 个相关文本片段")
    private String message;

    @Schema(description = "召回结果列表")
    private List<RagChunkVO> results;

    /**
     * RAG 文本分片 VO
     */
    @Schema(description = "RAG 文本分片 VO")
    @Data
    public static class RagChunkVO {

        @Schema(description = "知识库 ID", example = "1")
        private Long kbId;

        @Schema(description = "文件 ID", example = "123")
        private Long fileId;

        @Schema(description = "分片索引", example = "5")
        private Integer chunkIdx;

        @Schema(description = "文本内容", example = "知识库使用说明...")
        private String text;

        @Schema(description = "相似度分数", example = "0.8923")
        private Double score;
    }
}
