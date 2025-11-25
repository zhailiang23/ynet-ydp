package com.ynet.iplatform.module.aicrm.controller.admin.practiceconversation.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - CRM智能陪练-陪练对话分页 Request VO")
@Data
public class PracticeConversationPageReqVO extends PageParam {

    @Schema(description = "关联用户陪练记录ID", example = "8337")
    private Long recordId;

    @Schema(description = "对话序号（从1开始）")
    private Integer sequenceNo;

    @Schema(description = "发言角色：字典 aicrm_conversation_role（student/virtual_customer/system）")
    private String role;

    @Schema(description = "消息内容")
    private String messageContent;

    @Schema(description = "消息时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] messageTime;

    @Schema(description = "即时评分（针对学员发言）")
    private BigDecimal instantScore;

    @Schema(description = "话术分析（JSON格式）")
    private String speechAnalysis;

    @Schema(description = "技巧运用评价（JSON格式）")
    private String skillEvaluation;

    @Schema(description = "对话情绪标签：字典 aicrm_emotion_tag")
    private String emotionTag;

    @Schema(description = "销售意图识别：字典 aicrm_sales_intent")
    private String salesIntent;

    @Schema(description = "客户反应：字典 aicrm_customer_reaction")
    private String customerReaction;

    @Schema(description = "AI改进建议（JSON格式）")
    private String improvementSuggestions;

    @Schema(description = "推荐话术（JSON格式）")
    private String recommendedScripts;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    // 手动添加getter方法（为了确保编译通过）
    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Integer getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(Integer sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public LocalDateTime[] getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(LocalDateTime[] messageTime) {
        this.messageTime = messageTime;
    }

    public BigDecimal getInstantScore() {
        return instantScore;
    }

    public void setInstantScore(BigDecimal instantScore) {
        this.instantScore = instantScore;
    }

    public String getSpeechAnalysis() {
        return speechAnalysis;
    }

    public void setSpeechAnalysis(String speechAnalysis) {
        this.speechAnalysis = speechAnalysis;
    }

    public String getSkillEvaluation() {
        return skillEvaluation;
    }

    public void setSkillEvaluation(String skillEvaluation) {
        this.skillEvaluation = skillEvaluation;
    }

    public String getEmotionTag() {
        return emotionTag;
    }

    public void setEmotionTag(String emotionTag) {
        this.emotionTag = emotionTag;
    }

    public String getSalesIntent() {
        return salesIntent;
    }

    public void setSalesIntent(String salesIntent) {
        this.salesIntent = salesIntent;
    }

    public String getCustomerReaction() {
        return customerReaction;
    }

    public void setCustomerReaction(String customerReaction) {
        this.customerReaction = customerReaction;
    }

    public String getImprovementSuggestions() {
        return improvementSuggestions;
    }

    public void setImprovementSuggestions(String improvementSuggestions) {
        this.improvementSuggestions = improvementSuggestions;
    }

    public String getRecommendedScripts() {
        return recommendedScripts;
    }

    public void setRecommendedScripts(String recommendedScripts) {
        this.recommendedScripts = recommendedScripts;
    }

    public LocalDateTime[] getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime[] createTime) {
        this.createTime = createTime;
    }
}