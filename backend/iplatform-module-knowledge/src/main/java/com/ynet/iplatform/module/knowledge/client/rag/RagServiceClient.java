package com.ynet.iplatform.module.knowledge.client.rag;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Python RAG 服务客户端
 * 用于调用 Python 知识库嵌入和召回服务
 *
 * @author 芋道源码
 */
@Component
@Slf4j
public class RagServiceClient {

    @Value("${knowledge.rag.service-url:http://localhost:8001}")
    private String ragServiceUrl;

    @Value("${knowledge.rag.timeout:60000}")
    private int timeout;

    @Value("${knowledge.rag.chunk-size:512}")
    private int defaultChunkSize;

    @Value("${knowledge.rag.overlap:64}")
    private int defaultOverlap;

    /**
     * 调用文件嵌入服务
     *
     * @param filePath 文件路径
     * @param fileId 文件 ID
     * @param kbId 知识库 ID
     * @return 是否成功
     */
    public boolean embedFile(String filePath, Long fileId, Long kbId) {
        return embedFile(filePath, fileId, kbId, defaultChunkSize, defaultOverlap);
    }

    /**
     * 调用文件嵌入服务
     *
     * @param filePath 文件路径
     * @param fileId 文件 ID
     * @param kbId 知识库 ID
     * @param chunkSize 分片大小
     * @param overlap 重叠大小
     * @return 是否成功
     */
    public boolean embedFile(String filePath, Long fileId, Long kbId, int chunkSize, int overlap) {
        try {
            // 构建请求体
            JSONObject requestBody = JSONUtil.createObj()
                    .set("file_path", filePath)
                    .set("file_id", fileId)
                    .set("kb_id", kbId)
                    .set("chunk_size", chunkSize)
                    .set("overlap", overlap);

            // 发送 POST 请求
            String url = ragServiceUrl + "/api/embed";
            log.info("[embedFile] 调用 RAG 服务嵌入文件: url={}, request={}", url, requestBody);

            HttpResponse response = HttpRequest.post(url)
                    .header("Content-Type", "application/json")
                    .body(requestBody.toString())
                    .timeout(timeout)
                    .execute();

            // 检查响应
            if (response.isOk()) {
                String responseBody = response.body();
                log.info("[embedFile] RAG 服务响应成功: response={}", responseBody);

                JSONObject result = JSONUtil.parseObj(responseBody);
                boolean success = result.getBool("success", false);

                if (success) {
                    Integer chunksCreated = result.getInt("chunks_created", 0);
                    log.info("[embedFile] 文件嵌入成功: fileId={}, kbId={}, chunks={}", fileId, kbId, chunksCreated);
                    return true;
                } else {
                    String message = result.getStr("message", "未知错误");
                    log.error("[embedFile] 文件嵌入失败: fileId={}, kbId={}, message={}", fileId, kbId, message);
                    return false;
                }
            } else {
                log.error("[embedFile] RAG 服务请求失败: status={}, body={}", response.getStatus(), response.body());
                return false;
            }

        } catch (Exception e) {
            log.error("[embedFile] 调用 RAG 服务异常: fileId={}, kbId={}", fileId, kbId, e);
            return false;
        }
    }

    /**
     * 调用召回服务
     *
     * @param question 用户问题
     * @param kbIds 知识库 ID 列表
     * @param topK 返回结果数量
     * @return 召回结果 JSON
     */
    public JSONObject retrieve(String question, Long[] kbIds, int topK) {
        try {
            // 构建请求体
            JSONObject requestBody = JSONUtil.createObj()
                    .set("question", question)
                    .set("kb_ids", kbIds)
                    .set("top_k", topK);

            // 发送 POST 请求
            String url = ragServiceUrl + "/api/retrieve";
            log.info("[retrieve] 调用 RAG 服务召回: url={}, request={}", url, requestBody);

            HttpResponse response = HttpRequest.post(url)
                    .header("Content-Type", "application/json")
                    .body(requestBody.toString())
                    .timeout(timeout)
                    .execute();

            // 检查响应
            if (response.isOk()) {
                String responseBody = response.body();
                log.info("[retrieve] RAG 服务响应成功: response={}", responseBody);
                return JSONUtil.parseObj(responseBody);
            } else {
                log.error("[retrieve] RAG 服务请求失败: status={}, body={}", response.getStatus(), response.body());
                return null;
            }

        } catch (Exception e) {
            log.error("[retrieve] 调用 RAG 服务异常: question={}", question, e);
            return null;
        }
    }

    /**
     * 删除文件的向量数据
     *
     * @param fileId 文件 ID
     * @param kbId 知识库 ID
     * @return 是否成功
     */
    public boolean deleteFile(Long fileId, Long kbId) {
        try {
            // 构建请求体
            JSONObject requestBody = JSONUtil.createObj()
                    .set("file_id", fileId)
                    .set("kb_id", kbId);

            // 发送 DELETE 请求
            String url = ragServiceUrl + "/api/embed";
            log.info("[deleteFile] 调用 RAG 服务删除文件向量: url={}, request={}", url, requestBody);

            // 注意：Hutool 的 HttpRequest.delete() 默认不支持 body，需要使用 request() 方法
            HttpResponse response = HttpRequest.delete(url)
                    .header("Content-Type", "application/json")
                    .body(requestBody.toString())
                    .timeout(timeout)
                    .execute();

            // 检查响应
            if (response.isOk()) {
                String responseBody = response.body();
                log.info("[deleteFile] RAG 服务响应成功: response={}", responseBody);

                JSONObject result = JSONUtil.parseObj(responseBody);
                boolean success = result.getBool("success", false);

                if (success) {
                    Integer vectorsDeleted = result.getInt("vectors_deleted", 0);
                    log.info("[deleteFile] 文件向量删除成功: fileId={}, kbId={}, vectors={}", fileId, kbId, vectorsDeleted);
                    return true;
                } else {
                    String message = result.getStr("message", "未知错误");
                    log.error("[deleteFile] 文件向量删除失败: fileId={}, kbId={}, message={}", fileId, kbId, message);
                    return false;
                }
            } else {
                log.error("[deleteFile] RAG 服务请求失败: status={}, body={}", response.getStatus(), response.body());
                return false;
            }

        } catch (Exception e) {
            log.error("[deleteFile] 调用 RAG 服务异常: fileId={}, kbId={}", fileId, kbId, e);
            return false;
        }
    }

    /**
     * 健康检查
     *
     * @return 是否健康
     */
    public boolean healthCheck() {
        try {
            String url = ragServiceUrl + "/health";
            HttpResponse response = HttpRequest.get(url)
                    .timeout(5000)
                    .execute();

            if (response.isOk()) {
                log.info("[healthCheck] RAG 服务健康: {}", response.body());
                return true;
            } else {
                log.warn("[healthCheck] RAG 服务不健康: status={}", response.getStatus());
                return false;
            }
        } catch (Exception e) {
            log.warn("[healthCheck] RAG 服务连接失败", e);
            return false;
        }
    }
}
