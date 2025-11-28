package com.ynet.iplatform.module.customer.controller.admin.chatsessions.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 聊天消息 Response VO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageRespVO {

    @Schema(description = "主键", example = "1")
    private Integer id;

    @Schema(description = "用户 id", example = "1")
    private Integer userId;

    @Schema(description = "客服 id", example = "1")
    private Integer adminId;

    @Schema(description = "消息类型", example = "text")
    private String type;

    @Schema(description = "消息内容", example = "你好")
    private String content;

    @Schema(description = "接收时间")
    private LocalDateTime receivedAt;

    @Schema(description = "消息来源: 0=用户, 1=客服, 2=系统", example = "0")
    private Integer source;

    @Schema(description = "会话 id", example = "1")
    private Integer sessionId;

}
