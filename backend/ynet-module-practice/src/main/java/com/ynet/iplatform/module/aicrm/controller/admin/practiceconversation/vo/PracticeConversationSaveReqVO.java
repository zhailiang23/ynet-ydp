package com.ynet.iplatform.module.aicrm.controller.admin.practiceconversation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - CRM智能陪练-陪练对话新增/修改 Request VO")
@Data
public class PracticeConversationSaveReqVO {

    @Schema(description = "对话ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16764")
    private Long id;

    @Schema(description = "关联用户陪练记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8337")
    @NotNull(message = "关联用户陪练记录ID不能为空")
    private Long recordId;

    @Schema(description = "对话序号（从1开始）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "对话序号（从1开始）不能为空")
    private Integer sequenceNo;

    @Schema(description = "发言角色：字典 aicrm_conversation_role（student/virtual_customer/system）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "发言角色：字典 aicrm_conversation_role（student/virtual_customer/system）不能为空")
    private String role;

    @Schema(description = "消息内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "消息内容不能为空")
    private String messageContent;

    @Schema(description = "消息时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "消息时间不能为空")
    private LocalDateTime messageTime;

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

}