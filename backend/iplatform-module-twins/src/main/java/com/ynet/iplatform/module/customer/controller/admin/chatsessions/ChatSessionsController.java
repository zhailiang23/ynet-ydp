package com.ynet.iplatform.module.customer.controller.admin.chatsessions;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;

import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.CommonResult;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;
import static com.ynet.iplatform.framework.common.pojo.CommonResult.success;

import com.ynet.iplatform.framework.excel.core.util.ExcelUtils;

import com.ynet.iplatform.framework.apilog.core.annotation.ApiAccessLog;
import static com.ynet.iplatform.framework.apilog.core.enums.OperateTypeEnum.*;

import com.ynet.iplatform.module.customer.controller.admin.chatsessions.vo.*;
import com.ynet.iplatform.module.customer.dal.dataobject.chatsessions.ChatSessionsDO;
import com.ynet.iplatform.module.customer.dal.dataobject.chatmessages.ChatMessagesDO;
import com.ynet.iplatform.module.customer.service.chatsessions.ChatSessionsService;
import com.ynet.iplatform.module.customer.service.chatmessages.ChatMessagesService;

@Tag(name = "管理后台 - 会话信息")
@RestController
@RequestMapping("/customer/chat-sessions")
@Validated
public class ChatSessionsController {

    @Resource
    private ChatSessionsService chatSessionsService;

    @Resource
    private ChatMessagesService chatMessagesService;

    @PostMapping("/create")
    @Operation(summary = "创建会话信息")
    @PreAuthorize("@ss.hasPermission('customer:chat-sessions:create')")
    public CommonResult<Integer> createChatSessions(@Valid @RequestBody ChatSessionsSaveReqVO createReqVO) {
        return success(chatSessionsService.createChatSessions(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新会话信息")
    @PreAuthorize("@ss.hasPermission('customer:chat-sessions:update')")
    public CommonResult<Boolean> updateChatSessions(@Valid @RequestBody ChatSessionsSaveReqVO updateReqVO) {
        chatSessionsService.updateChatSessions(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除会话信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('customer:chat-sessions:delete')")
    public CommonResult<Boolean> deleteChatSessions(@RequestParam("id") Integer id) {
        chatSessionsService.deleteChatSessions(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除会话信息")
                @PreAuthorize("@ss.hasPermission('customer:chat-sessions:delete')")
    public CommonResult<Boolean> deleteChatSessionsList(@RequestParam("ids") List<Integer> ids) {
        chatSessionsService.deleteChatSessionsListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得会话信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('customer:chat-sessions:query')")
    public CommonResult<ChatSessionsRespVO> getChatSessions(@RequestParam("id") Integer id) {
        ChatSessionsDO chatSessions = chatSessionsService.getChatSessions(id);
        return success(BeanUtils.toBean(chatSessions, ChatSessionsRespVO.class));
    }

    @GetMapping("/detail")
    @Operation(summary = "获得会话详情（包含消息列表）")
    @Parameter(name = "id", description = "会话编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('customer:chat-sessions:query')")
    public CommonResult<ChatSessionDetailRespVO> getChatSessionDetail(@RequestParam("id") Integer id) {
        // 获取会话信息
        ChatSessionsDO chatSessions = chatSessionsService.getChatSessions(id);
        ChatSessionsRespVO sessionVO = BeanUtils.toBean(chatSessions, ChatSessionsRespVO.class);

        // 获取消息列表
        List<ChatMessagesDO> messages = chatMessagesService.getMessagesBySessionId(id);
        List<ChatMessageRespVO> messageVOs = BeanUtils.toBean(messages, ChatMessageRespVO.class);

        // 构建响应
        ChatSessionDetailRespVO detailVO = ChatSessionDetailRespVO.builder()
                .session(sessionVO)
                .messages(messageVOs)
                .total(messageVOs.size())
                .build();
        return success(detailVO);
    }

    @GetMapping("/page")
    @Operation(summary = "获得会话信息分页")
    @PreAuthorize("@ss.hasPermission('customer:chat-sessions:query')")
    public CommonResult<PageResult<ChatSessionsRespVO>> getChatSessionsPage(@Valid ChatSessionsPageReqVO pageReqVO) {
        PageResult<ChatSessionsRespVO> pageResult = chatSessionsService.getChatSessionsPageWithNames(pageReqVO);
        return success(pageResult);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出会话信息 Excel")
    @PreAuthorize("@ss.hasPermission('customer:chat-sessions:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportChatSessionsExcel(@Valid ChatSessionsPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ChatSessionsDO> list = chatSessionsService.getChatSessionsPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "会话信息.xls", "数据", ChatSessionsRespVO.class,
                        BeanUtils.toBean(list, ChatSessionsRespVO.class));
    }

}