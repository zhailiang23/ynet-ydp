package com.ynet.iplatform.module.knowledge.service.file;

import cn.hutool.core.util.StrUtil;
import com.ynet.iplatform.module.knowledge.event.FileEmbedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.*;
import com.ynet.iplatform.module.knowledge.controller.admin.file.vo.*;
import com.ynet.iplatform.module.knowledge.dal.dataobject.file.KonwledgeFileDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.knowledge.dal.mysql.file.KonwledgeFileMapper;
import com.ynet.iplatform.module.infra.service.file.FileService;
import com.ynet.iplatform.module.infra.service.file.FileConfigService;
import com.ynet.iplatform.module.infra.framework.file.core.client.FileClient;
import com.ynet.iplatform.module.infra.framework.file.core.client.local.LocalFileClient;
import com.ynet.iplatform.module.infra.framework.file.core.client.local.LocalFileClientConfig;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.module.knowledge.enums.ErrorCodeConstants.*;

/**
 * 知识库文件 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
@Slf4j
public class KonwledgeFileServiceImpl implements KonwledgeFileService {

    @Resource
    private KonwledgeFileMapper konwledgeFileMapper;

    @Resource
    private FileService fileService;

    @Resource
    private FileConfigService fileConfigService;

    @Resource
    private ApplicationEventPublisher eventPublisher;

    @Resource
    private com.ynet.iplatform.module.knowledge.client.rag.RagServiceClient ragServiceClient;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long uploadFile(byte[] content, String fileName, String contentType, Long baseId) throws Exception {
        // 1. 上传文件到文件服务
        String fileUrl = fileService.createFile(content, fileName, "knowledge", contentType);

        // 2. 创建文件记录
        KonwledgeFileDO konwledgeFile = new KonwledgeFileDO();
        konwledgeFile.setKnowledgeBaseId(baseId);
        konwledgeFile.setFileName(fileName);
        konwledgeFile.setFileType(StrUtil.subAfter(fileName, ".", true));
        konwledgeFile.setFileSize((long) content.length);
        konwledgeFile.setStatus(0); // 0=处理中（等待嵌入）
        konwledgeFile.setFileUrl(fileUrl);

        konwledgeFileMapper.insert(konwledgeFile);
        Long fileId = konwledgeFile.getId();

        // 3. 获取文件的本地路径
        String localFilePath = getLocalFilePath(fileUrl);
        if (StrUtil.isNotBlank(localFilePath)) {
            // 4. 发布文件嵌入事件（事务提交后异步处理）
            FileEmbedEvent event = new FileEmbedEvent(fileId, baseId, localFilePath, fileName);
            eventPublisher.publishEvent(event);
            log.info("[uploadFile] 发布文件嵌入事件: fileId={}, kbId={}, path={}", fileId, baseId, localFilePath);
        } else {
            log.warn("[uploadFile] 无法获取文件本地路径，跳过嵌入: fileId={}, fileUrl={}", fileId, fileUrl);
            // 如果无法获取本地路径，标记为处理失败
            konwledgeFile.setStatus(2);
            konwledgeFileMapper.updateById(konwledgeFile);
        }

        return fileId;
    }

    /**
     * 从文件 URL 获取本地文件路径
     *
     * @param fileUrl 文件 URL
     * @return 本地文件路径，如果不是本地文件则返回 null
     */
    private String getLocalFilePath(String fileUrl) {
        try {
            // 获取主文件客户端
            FileClient client = fileConfigService.getMasterFileClient();
            if (client == null) {
                log.warn("[getLocalFilePath] 无法获取文件客户端");
                return null;
            }

            // 只处理本地文件存储
            if (!(client instanceof LocalFileClient)) {
                log.warn("[getLocalFilePath] 文件不是本地存储，跳过嵌入: clientType={}", client.getClass().getSimpleName());
                return null;
            }

            // 使用反射获取 basePath
            java.lang.reflect.Field configField = client.getClass().getSuperclass().getDeclaredField("config");
            configField.setAccessible(true);
            LocalFileClientConfig config = (LocalFileClientConfig) configField.get(client);
            String basePath = config.getBasePath();

            // 从 fileUrl 中提取文件 ID，然后从数据库查询真实的文件路径
            // URL 格式：http://127.0.0.1:48080/admin-api/infra/file/{id}/get/{path}
            if (fileUrl.contains("/infra/file/")) {
                String[] parts = fileUrl.split("/infra/file/");
                if (parts.length > 1) {
                    String[] idAndPath = parts[1].split("/get/");
                    if (idAndPath.length > 1) {
                        String filePath = idAndPath[1];
                        // 拼接完整的本地文件路径
                        String fullPath = basePath + File.separator + filePath;
                        log.info("[getLocalFilePath] 解析文件路径: fileUrl={}, path={}, fullPath={}", fileUrl, filePath, fullPath);
                        return fullPath;
                    }
                }
            }

            log.warn("[getLocalFilePath] 无法解析文件路径: fileUrl={}", fileUrl);
            return null;

        } catch (Exception e) {
            log.error("[getLocalFilePath] 获取本地文件路径失败: fileUrl={}", fileUrl, e);
            return null;
        }
    }

    @Override
    public Long createKonwledgeFile(KonwledgeFileSaveReqVO createReqVO) {
        // 插入
        KonwledgeFileDO konwledgeFile = BeanUtils.toBean(createReqVO, KonwledgeFileDO.class);
        konwledgeFileMapper.insert(konwledgeFile);

        // 返回
        return konwledgeFile.getId();
    }

    @Override
    public void updateKonwledgeFile(KonwledgeFileSaveReqVO updateReqVO) {
        // 校验存在
        validateKonwledgeFileExists(updateReqVO.getId());
        // 更新
        KonwledgeFileDO updateObj = BeanUtils.toBean(updateReqVO, KonwledgeFileDO.class);
        konwledgeFileMapper.updateById(updateObj);
    }

    @Override
    public void deleteKonwledgeFile(Long id) {
        // 1. 校验存在并获取文件信息
        KonwledgeFileDO file = validateKonwledgeFileExists(id);

        // 2. 调用 Python 服务删除向量数据
        try {
            boolean deleted = ragServiceClient.deleteFile(id, file.getKnowledgeBaseId());
            if (!deleted) {
                log.warn("[deleteKonwledgeFile] 向量删除失败，继续删除数据库记录: fileId={}, kbId={}",
                         id, file.getKnowledgeBaseId());
            } else {
                log.info("[deleteKonwledgeFile] 向量删除成功: fileId={}, kbId={}",
                         id, file.getKnowledgeBaseId());
            }
        } catch (Exception e) {
            log.error("[deleteKonwledgeFile] 调用向量删除服务异常，继续删除数据库记录: fileId={}, kbId={}",
                      id, file.getKnowledgeBaseId(), e);
        }

        // 3. 删除数据库记录
        konwledgeFileMapper.deleteById(id);
    }

    @Override
    public void deleteKonwledgeFileListByIds(List<Long> ids) {
        // 1. 遍历每个文件，调用向量删除服务
        for (Long id : ids) {
            try {
                KonwledgeFileDO file = konwledgeFileMapper.selectById(id);
                if (file != null) {
                    boolean deleted = ragServiceClient.deleteFile(id, file.getKnowledgeBaseId());
                    if (!deleted) {
                        log.warn("[deleteKonwledgeFileListByIds] 向量删除失败: fileId={}, kbId={}",
                                 id, file.getKnowledgeBaseId());
                    } else {
                        log.info("[deleteKonwledgeFileListByIds] 向量删除成功: fileId={}, kbId={}",
                                 id, file.getKnowledgeBaseId());
                    }
                }
            } catch (Exception e) {
                log.error("[deleteKonwledgeFileListByIds] 调用向量删除服务异常: fileId={}", id, e);
            }
        }

        // 2. 批量删除数据库记录
        konwledgeFileMapper.deleteByIds(ids);
    }


    private KonwledgeFileDO validateKonwledgeFileExists(Long id) {
        KonwledgeFileDO file = konwledgeFileMapper.selectById(id);
        if (file == null) {
            throw exception(KONWLEDGE_FILE_NOT_EXISTS);
        }
        return file;
    }

    @Override
    public KonwledgeFileDO getKonwledgeFile(Long id) {
        return konwledgeFileMapper.selectById(id);
    }

    @Override
    public PageResult<KonwledgeFileDO> getKonwledgeFilePage(KonwledgeFilePageReqVO pageReqVO) {
        return konwledgeFileMapper.selectPage(pageReqVO);
    }

}