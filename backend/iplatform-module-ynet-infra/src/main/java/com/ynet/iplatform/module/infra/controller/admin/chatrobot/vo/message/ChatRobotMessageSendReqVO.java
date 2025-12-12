package com.ynet.iplatform.module.infra.controller.admin.chatrobot.vo.message;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 钉钉机器人发送消息 Request VO
 *
 * @author 易诚源码
 */
@Schema(description = "管理后台 - 钉钉机器人发送消息 Request VO")
@Data
public class ChatRobotMessageSendReqVO {

    @Schema(description = "机器人ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "123")
    @NotNull(message = "机器人ID不能为空")
    private Long robotId;

    @Schema(description = "对话ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "cidxxx")
    @NotBlank(message = "对话ID不能为空")
    private String conversationId;

    @Schema(description = "消息内容", requiredMode = Schema.RequiredMode.REQUIRED, example = "你好")
    @NotBlank(message = "消息内容不能为空")
    private String content;

    @Schema(description = "消息类型：text, markdown", example = "text")
    private String messageType = "text";
}
