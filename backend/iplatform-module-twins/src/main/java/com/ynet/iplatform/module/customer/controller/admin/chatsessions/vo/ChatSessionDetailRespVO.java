package com.ynet.iplatform.module.customer.controller.admin.chatsessions.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.List;

@Schema(description = "管理后台 - 会话详情 Response VO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatSessionDetailRespVO {

    @Schema(description = "会话信息")
    private ChatSessionsRespVO session;

    @Schema(description = "消息列表")
    private List<ChatMessageRespVO> messages;

    @Schema(description = "消息总数")
    private Integer total;

}
