package com.ynet.iplatform.module.infra.controller.admin.chatrobot.vo.conversation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 钉钉机器人对话 Response VO
 *
 * @author 易诚源码
 */
@Schema(description = "管理后台 - 钉钉机器人对话 Response VO")
@Data
public class ChatRobotConversationRespVO {

    @Schema(description = "对话ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "机器人ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "123")
    private Long robotId;

    @Schema(description = "钉钉对话ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "cidxxx")
    private String conversationId;

    @Schema(description = "对话类型：1=单聊，2=群聊", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Integer conversationType;

    @Schema(description = "对话标题", example = "张三")
    private String conversationTitle;

    @Schema(description = "发送者ID", example = "user123")
    private String senderId;

    @Schema(description = "发送者昵称", example = "张三")
    private String senderName;

    @Schema(description = "最后消息时间")
    private LocalDateTime lastMessageTime;

    @Schema(description = "最后消息内容", example = "你好")
    private String lastMessageContent;

    @Schema(description = "消息总数", example = "10")
    private Integer totalMessageCount;

    @Schema(description = "未读消息数", example = "3")
    private Integer unreadCount;

    @Schema(description = "对话状态：0=进行中，1=已关闭", example = "0")
    private Integer status;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
