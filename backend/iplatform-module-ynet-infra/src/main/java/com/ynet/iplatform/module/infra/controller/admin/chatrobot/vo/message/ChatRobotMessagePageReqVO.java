package com.ynet.iplatform.module.infra.controller.admin.chatrobot.vo.message;

import com.ynet.iplatform.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 钉钉机器人消息分页查询 Request VO
 *
 * @author 易诚源码
 */
@Schema(description = "管理后台 - 钉钉机器人消息分页查询 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class ChatRobotMessagePageReqVO extends PageParam {

    @Schema(description = "对话ID（必填）", requiredMode = Schema.RequiredMode.REQUIRED, example = "cidxxx")
    private String conversationId;

    @Schema(description = "机器人ID", example = "123")
    private Long robotId;

    @Schema(description = "消息类型", example = "text")
    private String messageType;

    @Schema(description = "消息来源：0=钉钉用户，1=管理员，2=系统", example = "0")
    private Integer source;
}
