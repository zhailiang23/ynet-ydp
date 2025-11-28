package com.ynet.iplatform.module.twins.controller.admin.chatautomessages;

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

import com.ynet.iplatform.module.twins.controller.admin.chatautomessages.vo.*;
import com.ynet.iplatform.module.twins.dal.dataobject.chatautomessages.ChatAutoMessagesDO;
import com.ynet.iplatform.module.twins.service.chatautomessages.ChatAutoMessagesService;

@Tag(name = "管理后台 - 客户留资消息")
@RestController
@RequestMapping("/twins/chat-auto-messages")
@Validated
public class ChatAutoMessagesController {

    @Resource
    private ChatAutoMessagesService chatAutoMessagesService;

    @PostMapping("/create")
    @Operation(summary = "创建客户留资消息")
    @PreAuthorize("@ss.hasPermission('twins:chat-auto-messages:create')")
    public CommonResult<Integer> createChatAutoMessages(@Valid @RequestBody ChatAutoMessagesSaveReqVO createReqVO) {
        return success(chatAutoMessagesService.createChatAutoMessages(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户留资消息")
    @PreAuthorize("@ss.hasPermission('twins:chat-auto-messages:update')")
    public CommonResult<Boolean> updateChatAutoMessages(@Valid @RequestBody ChatAutoMessagesSaveReqVO updateReqVO) {
        chatAutoMessagesService.updateChatAutoMessages(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户留资消息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('twins:chat-auto-messages:delete')")
    public CommonResult<Boolean> deleteChatAutoMessages(@RequestParam("id") Integer id) {
        chatAutoMessagesService.deleteChatAutoMessages(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户留资消息")
                @PreAuthorize("@ss.hasPermission('twins:chat-auto-messages:delete')")
    public CommonResult<Boolean> deleteChatAutoMessagesList(@RequestParam("ids") List<Integer> ids) {
        chatAutoMessagesService.deleteChatAutoMessagesListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户留资消息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('twins:chat-auto-messages:query')")
    public CommonResult<ChatAutoMessagesRespVO> getChatAutoMessages(@RequestParam("id") Integer id) {
        ChatAutoMessagesDO chatAutoMessages = chatAutoMessagesService.getChatAutoMessages(id);
        return success(BeanUtils.toBean(chatAutoMessages, ChatAutoMessagesRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户留资消息分页")
    @PreAuthorize("@ss.hasPermission('twins:chat-auto-messages:query')")
    public CommonResult<PageResult<ChatAutoMessagesRespVO>> getChatAutoMessagesPage(@Valid ChatAutoMessagesPageReqVO pageReqVO) {
        PageResult<ChatAutoMessagesDO> pageResult = chatAutoMessagesService.getChatAutoMessagesPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ChatAutoMessagesRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户留资消息 Excel")
    @PreAuthorize("@ss.hasPermission('twins:chat-auto-messages:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportChatAutoMessagesExcel(@Valid ChatAutoMessagesPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ChatAutoMessagesDO> list = chatAutoMessagesService.getChatAutoMessagesPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户留资消息.xls", "数据", ChatAutoMessagesRespVO.class,
                        BeanUtils.toBean(list, ChatAutoMessagesRespVO.class));
    }

}