package cn.iocoder.yudao.module.aicrm.service.practiceevaluation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * AI 评估请求 DTO
 *
 * @author 系统
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AiEvaluationRequest {

    /**
     * 记录ID
     */
    @JsonProperty("record_id")
    private Long recordId;

    /**
     * 对话历史
     */
    @JsonProperty("conversation_history")
    private List<ConversationMessage> conversationHistory;

    /**
     * 课程名称
     */
    @JsonProperty("course_name")
    private String courseName;

    /**
     * 虚拟客户名称
     */
    @JsonProperty("virtual_customer_name")
    private String virtualCustomerName;

    /**
     * 对话消息
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ConversationMessage {
        private String role;
        private String content;
    }
}
