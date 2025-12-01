package com.ynet.iplatform.module.knowledge.event;

import com.ynet.iplatform.module.knowledge.client.rag.RagServiceClient;
import com.ynet.iplatform.module.knowledge.dal.dataobject.file.KonwledgeFileDO;
import com.ynet.iplatform.module.knowledge.dal.mysql.file.KonwledgeFileMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * 文件嵌入事件监听器
 * 在事务提交后异步处理文件嵌入
 *
 * @author 芋道源码
 */
@Component
@Slf4j
public class FileEmbedEventListener {

    @Resource
    private RagServiceClient ragServiceClient;

    @Resource
    private KonwledgeFileMapper konwledgeFileMapper;

    /**
     * 监听文件嵌入事件，在事务提交后异步处理
     *
     * @param event 文件嵌入事件
     */
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleFileEmbedEvent(FileEmbedEvent event) {
        log.info("[handleFileEmbedEvent] 开始处理文件嵌入事件: fileId={}, kbId={}, filePath={}",
                event.getFileId(), event.getKbId(), event.getFilePath());

        try {
            // 1. 更新文件状态为处理中
            updateFileStatus(event.getFileId(), 0); // 0=处理中

            // 2. 调用 Python RAG 服务进行文件嵌入
            boolean success = ragServiceClient.embedFile(
                    event.getFilePath(),
                    event.getFileId(),
                    event.getKbId()
            );

            // 3. 更新文件状态
            if (success) {
                updateFileStatus(event.getFileId(), 1); // 1=处理完成
                log.info("[handleFileEmbedEvent] 文件嵌入成功: fileId={}, kbId={}", event.getFileId(), event.getKbId());
            } else {
                updateFileStatus(event.getFileId(), 2); // 2=处理失败
                log.error("[handleFileEmbedEvent] 文件嵌入失败: fileId={}, kbId={}", event.getFileId(), event.getKbId());
            }

        } catch (Exception e) {
            log.error("[handleFileEmbedEvent] 文件嵌入异常: fileId={}, kbId={}", event.getFileId(), event.getKbId(), e);
            updateFileStatus(event.getFileId(), 2); // 2=处理失败
        }
    }

    /**
     * 更新文件处理状态
     *
     * @param fileId 文件 ID
     * @param status 状态 (0=处理中, 1=处理完成, 2=处理失败)
     */
    private void updateFileStatus(Long fileId, Integer status) {
        try {
            KonwledgeFileDO updateObj = new KonwledgeFileDO();
            updateObj.setId(fileId);
            updateObj.setStatus(status);
            konwledgeFileMapper.updateById(updateObj);
        } catch (Exception e) {
            log.error("[updateFileStatus] 更新文件状态失败: fileId={}, status={}", fileId, status, e);
        }
    }
}
