package com.ynet.iplatform.module.customer.controller.admin.chattransfers;

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

import com.ynet.iplatform.module.customer.controller.admin.chattransfers.vo.*;
import com.ynet.iplatform.module.customer.dal.dataobject.chattransfers.ChatTransfersDO;
import com.ynet.iplatform.module.customer.service.chattransfers.ChatTransfersService;

@Tag(name = "管理后台 - 转接记录")
@RestController
@RequestMapping("/customer/chat-transfers")
@Validated
public class ChatTransfersController {

    @Resource
    private ChatTransfersService chatTransfersService;

    @PostMapping("/create")
    @Operation(summary = "创建转接记录")
    @PreAuthorize("@ss.hasPermission('customer:chat-transfers:create')")
    public CommonResult<Integer> createChatTransfers(@Valid @RequestBody ChatTransfersSaveReqVO createReqVO) {
        return success(chatTransfersService.createChatTransfers(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新转接记录")
    @PreAuthorize("@ss.hasPermission('customer:chat-transfers:update')")
    public CommonResult<Boolean> updateChatTransfers(@Valid @RequestBody ChatTransfersSaveReqVO updateReqVO) {
        chatTransfersService.updateChatTransfers(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除转接记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('customer:chat-transfers:delete')")
    public CommonResult<Boolean> deleteChatTransfers(@RequestParam("id") Integer id) {
        chatTransfersService.deleteChatTransfers(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除转接记录")
                @PreAuthorize("@ss.hasPermission('customer:chat-transfers:delete')")
    public CommonResult<Boolean> deleteChatTransfersList(@RequestParam("ids") List<Integer> ids) {
        chatTransfersService.deleteChatTransfersListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得转接记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('customer:chat-transfers:query')")
    public CommonResult<ChatTransfersRespVO> getChatTransfers(@RequestParam("id") Integer id) {
        ChatTransfersDO chatTransfers = chatTransfersService.getChatTransfers(id);
        return success(BeanUtils.toBean(chatTransfers, ChatTransfersRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得转接记录分页")
    @PreAuthorize("@ss.hasPermission('customer:chat-transfers:query')")
    public CommonResult<PageResult<ChatTransfersRespVO>> getChatTransfersPage(@Valid ChatTransfersPageReqVO pageReqVO) {
        // 使用连表查询方法，返回包含用户名和客服名的结果
        PageResult<ChatTransfersRespVO> pageResult = chatTransfersService.getChatTransfersPageWithNames(pageReqVO);
        return success(pageResult);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出转接记录 Excel")
    @PreAuthorize("@ss.hasPermission('customer:chat-transfers:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportChatTransfersExcel(@Valid ChatTransfersPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ChatTransfersDO> list = chatTransfersService.getChatTransfersPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "转接记录.xls", "数据", ChatTransfersRespVO.class,
                        BeanUtils.toBean(list, ChatTransfersRespVO.class));
    }

}