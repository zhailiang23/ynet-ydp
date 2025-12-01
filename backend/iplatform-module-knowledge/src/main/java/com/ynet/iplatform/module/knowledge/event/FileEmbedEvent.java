package com.ynet.iplatform.module.knowledge.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文件嵌入事件
 * 在文件上传成功并保存到数据库后触发，用于调用 Python RAG 服务进行文件嵌入
 *
 * @author 芋道源码
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileEmbedEvent {

    /**
     * 文件 ID
     */
    private Long fileId;

    /**
     * 知识库 ID
     */
    private Long kbId;

    /**
     * 文件路径（本地路径）
     */
    private String filePath;

    /**
     * 文件名
     */
    private String fileName;
}
