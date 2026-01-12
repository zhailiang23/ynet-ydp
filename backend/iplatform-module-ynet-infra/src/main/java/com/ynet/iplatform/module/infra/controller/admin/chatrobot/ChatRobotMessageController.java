package com.ynet.iplatform.module.infra.controller.admin.chatrobot;

import com.ynet.iplatform.framework.common.pojo.CommonResult;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.module.infra.controller.admin.chatrobot.vo.message.ChatRobotMessageMarkReadReqVO;
import com.ynet.iplatform.module.infra.controller.admin.chatrobot.vo.message.ChatRobotMessagePageReqVO;
import com.ynet.iplatform.module.infra.controller.admin.chatrobot.vo.message.ChatRobotMessageRespVO;
import com.ynet.iplatform.module.infra.controller.admin.chatrobot.vo.message.ChatRobotMessageSendReqVO;
import com.ynet.iplatform.module.infra.service.chatrobot.ChatRobotMessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

import static com.ynet.iplatform.framework.common.pojo.CommonResult.success;

/**
 * 钉钉机器人消息 Controller
 *
 * @author 易诚源码
 */
@Tag(name = "管理后台 - 钉钉机器人消息")
@RestController
@RequestMapping("/infra/chat-robot/message")
@Validated
public class ChatRobotMessageController {

    @Resource
    private ChatRobotMessageService messageService;

    @GetMapping("/page")
    @Operation(summary = "分页查询消息列表")
    @PreAuthorize("@ss.hasPermission('infra:chat-robot:query')")
    public CommonResult<PageResult<ChatRobotMessageRespVO>> getMessagePage(
            @Valid ChatRobotMessagePageReqVO pageReqVO) {
        return success(messageService.getMessagePage(pageReqVO));
    }

    @PostMapping("/send")
    @Operation(summary = "发送消息")
    @PreAuthorize("@ss.hasPermission('infra:chat-robot:update')")
    public CommonResult<Long> sendMessage(@Valid @RequestBody ChatRobotMessageSendReqVO sendReqVO) {
        return success(messageService.sendMessage(sendReqVO));
    }

    @PutMapping("/mark-read")
    @Operation(summary = "标记消息已读")
    @PreAuthorize("@ss.hasPermission('infra:chat-robot:update')")
    public CommonResult<Boolean> markMessagesAsRead(@Valid @RequestBody ChatRobotMessageMarkReadReqVO reqVO) {
        messageService.markMessagesAsRead(reqVO.getConversationId());
        return success(true);
    }

    @GetMapping("/polling")
    @Operation(summary = "轮询获取新消息")
    @Parameter(name = "conversationId", description = "对话ID", required = true, example = "cidxxx")
    @Parameter(name = "lastMessageId", description = "最后一条消息ID", example = "123")
    @PreAuthorize("@ss.hasPermission('infra:chat-robot:query')")
    public CommonResult<PageResult<ChatRobotMessageRespVO>> pollNewMessages(
            @RequestParam("conversationId") String conversationId,
            @RequestParam(value = "lastMessageId", required = false) Long lastMessageId) {
        return success(messageService.pollNewMessages(conversationId, lastMessageId));
    }
}
