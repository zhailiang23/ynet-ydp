package com.ynet.iplatform.module.aicrm.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ynet.iplatform.module.infra.dal.dataobject.externalagent.ExternalAgentDO;
import com.ynet.iplatform.module.infra.service.externalagent.ExternalAgentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

/**
 * Dify 客户端工具类
 * 提供统一的 Dify API 调用方法
 *
 * @author 易诚源码
 */
@Component
@Slf4j
public class DifyClientUtil {

    @Resource
    private ExternalAgentService externalAgentService;

    /**
     * 默认超时时间(毫秒) - 10分钟
     */
    private static final int DEFAULT_TIMEOUT = 600000;

    /**
     * 调用 Dify Chat Messages API
     * 用于对话应用,支持多轮对话
     *
     * @param agentCode 外部智能体编码
     * @param requestParams JSON 格式的请求参数对象
     *                      必须包含: query(用户消息), response_mode(blocking/streaming), user(用户标识)
     *                      可选参数: inputs(输入变量), conversation_id(会话ID,用于继续对话)
     * @return Dify 响应 JSON 对象
     * @throws Exception 调用失败时抛出异常
     */
    public JSONObject chatMessages(String agentCode, JSONObject requestParams) throws Exception {
        // 1. 根据 code 获取外部智能体配置
        ExternalAgentDO agent = getExternalAgent(agentCode);

        // 2. 构建完整的 API URL
        String url = buildUrl(agent.getAccessUrl(), "/chat-messages");

        // 3. 发送请求
        return sendRequest(url, agent.getApiKey(), requestParams);
    }

    /**
     * 调用 Dify Workflows Run API
     * 用于工作流应用,执行完整的工作流程
     *
     * @param agentCode 外部智能体编码
     * @param requestParams JSON 格式的请求参数对象
     *                      必须包含: inputs(工作流输入变量), response_mode(blocking/streaming), user(用户标识)
     * @return Dify 响应 JSON 对象
     * @throws Exception 调用失败时抛出异常
     */
    public JSONObject workflowsRun(String agentCode, JSONObject requestParams) throws Exception {
        // 1. 根据 code 获取外部智能体配置
        ExternalAgentDO agent = getExternalAgent(agentCode);

        // 2. 构建完整的 API URL
        String url = buildUrl(agent.getAccessUrl(), "/workflows/run");

        // 3. 发送请求
        return sendRequest(url, agent.getApiKey(), requestParams);
    }

    /**
     * 根据智能体编码获取外部智能体配置
     *
     * @param agentCode 智能体编码
     * @return 外部智能体配置
     * @throws Exception 智能体不存在或未启用时抛出异常
     */
    private ExternalAgentDO getExternalAgent(String agentCode) throws Exception {
        if (StrUtil.isBlank(agentCode)) {
            throw new Exception("智能体编码不能为空");
        }

        ExternalAgentDO agent = externalAgentService.getExternalAgentByCode(agentCode);
        if (agent == null) {
            throw new Exception("智能体不存在: " + agentCode);
        }

        // 检查状态 (0=开启, 1=关闭)
        if (agent.getStatus() == null || agent.getStatus() != 0) {
            throw new Exception("智能体未启用: " + agentCode);
        }

        // 检查必要配置
        if (StrUtil.isBlank(agent.getAccessUrl())) {
            throw new Exception("智能体访问URL未配置: " + agentCode);
        }
        if (StrUtil.isBlank(agent.getApiKey())) {
            throw new Exception("智能体API密钥未配置: " + agentCode);
        }

        return agent;
    }

    /**
     * 构建完整的 API URL
     *
     * @param baseUrl API 基础地址
     * @param endpoint API 端点
     * @return 完整的 URL
     */
    private String buildUrl(String baseUrl, String endpoint) {
        // 移除 baseUrl 末尾的斜杠
        baseUrl = baseUrl.trim();
        if (baseUrl.endsWith("/")) {
            baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
        }

        // 确保 endpoint 以斜杠开头
        if (!endpoint.startsWith("/")) {
            endpoint = "/" + endpoint;
        }

        return baseUrl + endpoint;
    }

    /**
     * 发送 HTTP 请求到 Dify API
     *
     * @param url API URL
     * @param apiKey API 密钥
     * @param requestParams 请求参数
     * @return 响应 JSON 对象
     * @throws Exception 请求失败时抛出异常
     */
    private JSONObject sendRequest(String url, String apiKey, JSONObject requestParams) throws Exception {
        if (requestParams == null) {
            throw new Exception("请求参数不能为空");
        }

        log.info("调用 Dify API, URL: {}", url);
        log.debug("Dify 请求参数: {}", requestParams.toString());

        try (HttpResponse response = HttpRequest.post(url)
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .body(requestParams.toString())
                .timeout(DEFAULT_TIMEOUT)
                .execute()) {

            int status = response.getStatus();
            String body = response.body();

            log.info("Dify 响应状态: {}, 响应体长度: {}", status, body != null ? body.length() : 0);

            if (status != 200) {
                log.error("Dify API 调用失败, 响应: {}", body);
                throw new Exception("调用 Dify API 失败, HTTP状态码: " + status + ", 响应: " + body);
            }

            // 解析响应
            JSONObject responseJson = JSONUtil.parseObj(body);

            // 检查是否有错误
            if (responseJson.containsKey("code")) {
                String errorCode = responseJson.getStr("code");
                String errorMessage = responseJson.getStr("message");
                throw new Exception("Dify API 调用失败: " + errorCode + " - " + errorMessage);
            }

            log.info("Dify API 调用成功");
            return responseJson;

        } catch (Exception e) {
            log.error("调用 Dify API 异常", e);
            throw new Exception("调用 Dify API 异常: " + e.getMessage(), e);
        }
    }

    /**
     * 构建 Chat Messages 请求参数的辅助类
     * 用于简化参数构建过程
     */
    public static class ChatMessagesBuilder {
        private final JSONObject params = new JSONObject();
        private final JSONObject inputs = new JSONObject();

        /**
         * 设置用户消息
         */
        public ChatMessagesBuilder query(String query) {
            params.set("query", query);
            return this;
        }

        /**
         * 设置响应模式
         * @param mode blocking(阻塞式) 或 streaming(流式)
         */
        public ChatMessagesBuilder responseMode(String mode) {
            params.set("response_mode", mode);
            return this;
        }

        /**
         * 设置用户标识
         */
        public ChatMessagesBuilder user(String user) {
            params.set("user", user);
            return this;
        }

        /**
         * 设置会话ID (用于继续对话)
         */
        public ChatMessagesBuilder conversationId(String conversationId) {
            if (StrUtil.isNotBlank(conversationId)) {
                params.set("conversation_id", conversationId);
            }
            return this;
        }

        /**
         * 添加输入变量
         */
        public ChatMessagesBuilder input(String key, Object value) {
            inputs.set(key, value);
            return this;
        }

        /**
         * 构建请求参数
         */
        public JSONObject build() {
            if (!inputs.isEmpty()) {
                params.set("inputs", inputs);
            }
            return params;
        }
    }

    /**
     * 构建 Workflows Run 请求参数的辅助类
     * 用于简化参数构建过程
     */
    public static class WorkflowsRunBuilder {
        private final JSONObject params = new JSONObject();
        private final JSONObject inputs = new JSONObject();

        /**
         * 设置响应模式
         * @param mode blocking(阻塞式) 或 streaming(流式)
         */
        public WorkflowsRunBuilder responseMode(String mode) {
            params.set("response_mode", mode);
            return this;
        }

        /**
         * 设置用户标识
         */
        public WorkflowsRunBuilder user(String user) {
            params.set("user", user);
            return this;
        }

        /**
         * 添加输入变量
         */
        public WorkflowsRunBuilder input(String key, Object value) {
            inputs.set(key, value);
            return this;
        }

        /**
         * 构建请求参数
         */
        public JSONObject build() {
            params.set("inputs", inputs);
            return params;
        }
    }
}
