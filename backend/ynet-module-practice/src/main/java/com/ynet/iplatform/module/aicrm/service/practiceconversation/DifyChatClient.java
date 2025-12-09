package com.ynet.iplatform.module.aicrm.service.practiceconversation;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.ynet.iplatform.module.aicrm.util.DifyClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

/**
 * Dify 对话客户端
 * 调用 Dify Chat API 进行对话交互
 *
 * 注意: 本类基于 DifyClientUtil 实现,需要在 infra_external_agent 表中配置智能体编码
 */
@Component
@Slf4j
public class DifyChatClient {

    @Resource
    private DifyClientUtil difyClientUtil;

    /**
     * 智能体编码 - 对应 infra_external_agent 表的 code 字段
     * 默认使用 "practice-chat" 编码,可通过配置文件修改
     */
    private static final String AGENT_CODE = "practice-chat";

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
        log.info("调用 Dify Chat API, roleId: {}, scriptId: {}, message: {}", roleId, scriptId, userMessage);

        try {
            // 使用 Builder 构建请求参数
            JSONObject requestParams = new DifyClientUtil.ChatMessagesBuilder()
                    .query(userMessage)                      // 用户消息
                    .responseMode("blocking")                // 响应模式: blocking(阻塞式)
                    .user("backend-system")                  // 用户标识
                    .conversationId(conversationId)          // 会话ID (可选,用于继续对话)
                    .input("role_id", roleId)                // 输入变量: 虚拟角色ID
                    .input("script_id", scriptId)            // 输入变量: 剧本ID
                    .build();

            // 调用 Dify Chat API (根据 AGENT_CODE 自动获取 URL 和 API Key)
            JSONObject responseJson = difyClientUtil.chatMessages(AGENT_CODE, requestParams);

            // 解析响应
            String answer = responseJson.getStr("answer");
            String newConversationId = responseJson.getStr("conversation_id");
            String messageId = responseJson.getStr("message_id");

            if (StrUtil.isBlank(answer)) {
                log.warn("Dify Chat 返回的回复为空");
            }

            log.info("Dify Chat 成功, 回复长度: {}, conversationId: {}",
                    answer != null ? answer.length() : 0, newConversationId);

            // 封装响应
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
