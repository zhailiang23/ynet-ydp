package cn.iocoder.yudao.module.aicrm.controller.admin.practiceconversation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - CRM智能陪练-陪练对话 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PracticeConversationRespVO {

    @Schema(description = "对话ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16764")
    @ExcelProperty("对话ID")
    private Long id;

    @Schema(description = "关联用户陪练记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8337")
    @ExcelProperty("关联用户陪练记录ID")
    private Long recordId;

    @Schema(description = "对话序号（从1开始）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("对话序号（从1开始）")
    private Integer sequenceNo;

    @Schema(description = "发言角色：字典 aicrm_conversation_role（student/virtual_customer/system）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("发言角色：字典 aicrm_conversation_role（student/virtual_customer/system）")
    private String role;

    @Schema(description = "消息内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("消息内容")
    private String messageContent;

    @Schema(description = "消息时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("消息时间")
    private LocalDateTime messageTime;

    @Schema(description = "即时评分（针对学员发言）")
    @ExcelProperty("即时评分（针对学员发言）")
    private BigDecimal instantScore;

    @Schema(description = "话术分析（JSON格式）")
    @ExcelProperty("话术分析（JSON格式）")
    private String speechAnalysis;

    @Schema(description = "技巧运用评价（JSON格式）")
    @ExcelProperty("技巧运用评价（JSON格式）")
    private String skillEvaluation;

    @Schema(description = "对话情绪标签：字典 aicrm_emotion_tag")
    @ExcelProperty("对话情绪标签：字典 aicrm_emotion_tag")
    private String emotionTag;

    @Schema(description = "销售意图识别：字典 aicrm_sales_intent")
    @ExcelProperty("销售意图识别：字典 aicrm_sales_intent")
    private String salesIntent;

    @Schema(description = "客户反应：字典 aicrm_customer_reaction")
    @ExcelProperty("客户反应：字典 aicrm_customer_reaction")
    private String customerReaction;

    @Schema(description = "AI改进建议（JSON格式）")
    @ExcelProperty("AI改进建议（JSON格式）")
    private String improvementSuggestions;

    @Schema(description = "推荐话术（JSON格式）")
    @ExcelProperty("推荐话术（JSON格式）")
    private String recommendedScripts;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}