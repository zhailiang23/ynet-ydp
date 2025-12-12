package com.ynet.iplatform.module.infra.controller.admin.chatrobot.vo.message;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;

/**
 * 钉钉机器人消息标记已读 Request VO
 *
 * @author 易诚源码
 */
@Schema(description = "管理后台 - 钉钉机器人消息标记已读 Request VO")
@Data
public class ChatRobotMessageMarkReadReqVO {

    @Schema(description = "对话ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "cidxxx")
    @NotBlank(message = "对话ID不能为空")
    private String conversationId;
}
