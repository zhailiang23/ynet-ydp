package com.ynet.iplatform.module.infra.controller.admin.chatrobot.vo.message;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 钉钉机器人消息 Response VO
 *
 * @author 易诚源码
 */
@Schema(description = "管理后台 - 钉钉机器人消息 Response VO")
@Data
public class ChatRobotMessageRespVO {

    @Schema(description = "消息ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "机器人ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "123")
    private Long robotId;

    @Schema(description = "钉钉对话ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "cidxxx")
    private String conversationId;

    @Schema(description = "钉钉消息ID", example = "msgxxx")
    private String messageId;

    @Schema(description = "发送者ID", example = "user123")
    private String senderId;

    @Schema(description = "发送者昵称", example = "张三")
    private String senderName;

    @Schema(description = "消息内容", requiredMode = Schema.RequiredMode.REQUIRED, example = "你好")
    private String content;

    @Schema(description = "消息类型：text, image, file, audio, video", requiredMode = Schema.RequiredMode.REQUIRED, example = "text")
    private String messageType;

    @Schema(description = "消息来源：0=钉钉用户，1=管理员，2=系统", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    private Integer source;

    @Schema(description = "是否已读", requiredMode = Schema.RequiredMode.REQUIRED, example = "false")
    private Boolean isRead;

    @Schema(description = "发送时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime sendTime;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
