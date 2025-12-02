package com.ynet.iplatform.module.aicrm.dal.dataobject.practiceconversation;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * CRM智能陪练-陪练对话 DO
 *
 * @author 易诚源码
 */
@TableName("crm_practice_conversation")
@KeySequence("crm_practice_conversation_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PracticeConversationDO extends BaseDO {

    /**
     * 对话ID
     */
    @TableId
    private Long id;
    /**
     * 关联用户陪练记录ID
     */
    private Long recordId;
    /**
     * 对话序号（从1开始）
     */
    private Integer sequenceNo;
    /**
     * 发言角色：字典 aicrm_conversation_role（student/virtual_customer/system）
     */
    private String role;
    /**
     * 消息内容
     */
    private String messageContent;
    /**
     * 消息时间
     */
    private LocalDateTime messageTime;
    /**
     * 即时评分（针对学员发言）
     */
    private BigDecimal instantScore;
    /**
     * 话术分析（JSON格式）
     */
    private String speechAnalysis;
    /**
     * 技巧运用评价（JSON格式）
     */
    private String skillEvaluation;
    /**
     * 对话情绪标签：字典 aicrm_emotion_tag
     */
    private String emotionTag;
    /**
     * 销售意图识别：字典 aicrm_sales_intent
     */
    private String salesIntent;
    /**
     * 客户反应：字典 aicrm_customer_reaction
     */
    private String customerReaction;
    /**
     * AI改进建议（JSON格式）
     */
    private String improvementSuggestions;
    /**
     * 推荐话术（JSON格式）
     */
    private String recommendedScripts;

    // 标准getter/setter方法（为了确保编译通过）
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDateTime getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(LocalDateTime messageTime) {
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
}