package com.ynet.iplatform.module.infra.controller.admin.chatrobot;

import com.ynet.iplatform.framework.common.pojo.CommonResult;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.module.infra.controller.admin.chatrobot.vo.conversation.ChatRobotConversationPageReqVO;
import com.ynet.iplatform.module.infra.controller.admin.chatrobot.vo.conversation.ChatRobotConversationRespVO;
import com.ynet.iplatform.module.infra.service.chatrobot.ChatRobotConversationService;
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
 * 钉钉机器人对话 Controller
 *
 * @author 易诚源码
 */
@Tag(name = "管理后台 - 钉钉机器人对话")
@RestController
@RequestMapping("/infra/chat-robot/conversation")
@Validated
public class ChatRobotConversationController {

    @Resource
    private ChatRobotConversationService conversationService;

    @GetMapping("/page")
    @Operation(summary = "分页查询对话列表")
    @PreAuthorize("@ss.hasPermission('infra:chat-robot:query')")
    public CommonResult<PageResult<ChatRobotConversationRespVO>> getConversationPage(
            @Valid ChatRobotConversationPageReqVO pageReqVO) {
        return success(conversationService.getConversationPage(pageReqVO));
    }

    @GetMapping("/get")
    @Operation(summary = "获取对话详情")
    @Parameter(name = "id", description = "对话ID", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('infra:chat-robot:query')")
    public CommonResult<ChatRobotConversationRespVO> getConversation(@RequestParam("id") Long id) {
        return success(conversationService.getConversation(id));
    }

    @PutMapping("/close")
    @Operation(summary = "关闭对话")
    @Parameter(name = "id", description = "对话ID", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('infra:chat-robot:update')")
    public CommonResult<Boolean> closeConversation(@RequestParam("id") Long id) {
        conversationService.closeConversation(id);
        return success(true);
    }

    @GetMapping("/unread-count")
    @Operation(summary = "获取未读消息总数")
    @Parameter(name = "robotId", description = "机器人ID", required = true, example = "123")
    @PreAuthorize("@ss.hasPermission('infra:chat-robot:query')")
    public CommonResult<Integer> getUnreadCount(@RequestParam("robotId") Long robotId) {
        return success(conversationService.getUnreadCount(robotId));
    }
}
