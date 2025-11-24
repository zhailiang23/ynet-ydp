package cn.iocoder.yudao.module.aicrm.service.practiceconversation;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.iocoder.yudao.module.aicrm.config.DifyProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

/**
 * Dify 对话客户端
 * 调用 Dify Chat API 进行对话交互
 */
@Component
@Slf4j
public class DifyChatClient {

    @Resource
    private DifyProperties difyProperties;

    /**
     * 发送对话消息
     *
     * @param roleId 虚拟角色ID
     * @param scriptId 剧本ID
     * @param userMessage 用户消息
     * @param conversationId 会话ID (可选,用于继续之前的对话)
     * @return Dify 响应,包含 answer 和 conversation_id
     * @throws Exception 调用失败时抛出异常
     */
    public DifyChatResponse sendMessage(Long roleId, Long scriptId, String userMessage, String conversationId) throws Exception {
        if (!difyProperties.getEnabled()) {
            throw new Exception("Dify 服务未启用");
        }

        String apiUrl = difyProperties.getApiUrl();
        String chatApiKey = difyProperties.getChatApiKey();

        if (StrUtil.isBlank(apiUrl)) {
            throw new Exception("Dify API URL 未配置");
        }
        if (StrUtil.isBlank(chatApiKey)) {
            throw new Exception("Dify 对话应用 API Key 未配置");
        }

        // 构建请求 URL - 对话应用使用 /chat-messages 接口
        String url = apiUrl + "/chat-messages";

        // 构建请求参数
        JSONObject requestBody = new JSONObject();

        // inputs: 传递给对话应用的输入变量
        JSONObject inputs = new JSONObject();
        inputs.set("role_id", roleId);
        inputs.set("script_id", scriptId);
        requestBody.set("inputs", inputs);

        // query: 用户消息
        requestBody.set("query", userMessage);

        // response_mode: blocking(阻塞式) 或 streaming(流式)
        requestBody.set("response_mode", "blocking");

        // user: 用户标识
        requestBody.set("user", difyProperties.getUser());

        // conversation_id: 会话ID (可选,用于继续对话)
        if (StrUtil.isNotBlank(conversationId)) {
            requestBody.set("conversation_id", conversationId);
        }

        log.info("调用 Dify Chat API, roleId: {}, scriptId: {}, message: {}", roleId, scriptId, userMessage);
        log.debug("Dify Chat 请求参数: {}", requestBody.toString());

        // 发送 HTTP 请求
        try (HttpResponse response = HttpRequest.post(url)
                .header("Authorization", "Bearer " + chatApiKey)
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .timeout(difyProperties.getTimeout())
                .execute()) {

            int status = response.getStatus();
            String body = response.body();

            log.info("Dify Chat 响应状态: {}, 响应体长度: {}", status, body != null ? body.length() : 0);

            if (status != 200) {
                log.error("Dify Chat 调用失败, 响应: {}", body);
                throw new Exception("调用 Dify Chat 失败, HTTP状态码: " + status + ", 响应: " + body);
            }

            // 解析响应
            JSONObject responseJson = JSONUtil.parseObj(body);

            // Dify Chat API 响应格式:
            // {
            //   "event": "message",
            //   "message_id": "xxx",
            //   "conversation_id": "xxx",
            //   "mode": "chat",
            //   "answer": "AI的回复内容",
            //   "metadata": {...},
            //   "created_at": 1234567890
            // }

            // 检查是否有错误
            if (responseJson.containsKey("code")) {
                String errorCode = responseJson.getStr("code");
                String errorMessage = responseJson.getStr("message");
                throw new Exception("Dify Chat 调用失败: " + errorCode + " - " + errorMessage);
            }

            String answer = responseJson.getStr("answer");
            String newConversationId = responseJson.getStr("conversation_id");
            String messageId = responseJson.getStr("message_id");

            if (StrUtil.isBlank(answer)) {
                log.warn("Dify Chat 返回的回复为空");
            }

            log.info("Dify Chat 成功, 回复长度: {}, conversationId: {}",
                    answer != null ? answer.length() : 0, newConversationId);

            DifyChatResponse chatResponse = new DifyChatResponse();
            chatResponse.setAnswer(answer);
            chatResponse.setConversationId(newConversationId);
            chatResponse.setMessageId(messageId);
            chatResponse.setCreatedAt(responseJson.getLong("created_at"));

            return chatResponse;

        } catch (Exception e) {
            log.error("调用 Dify Chat 异常", e);
            throw new Exception("调用 Dify Chat 异常: " + e.getMessage(), e);
        }
    }

    /**
     * Dify Chat 响应封装
     */
    public static class DifyChatResponse {
        private String answer;
        private String conversationId;
        private String messageId;
        private Long createdAt;

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public String getConversationId() {
            return conversationId;
        }

        public void setConversationId(String conversationId) {
            this.conversationId = conversationId;
        }

        public String getMessageId() {
            return messageId;
        }

        public void setMessageId(String messageId) {
            this.messageId = messageId;
        }

        public Long getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Long createdAt) {
            this.createdAt = createdAt;
        }
    }
}
