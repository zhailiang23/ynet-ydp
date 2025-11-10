package cn.iocoder.yudao.module.aicrm.controller.admin.practiceconversation.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

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

}