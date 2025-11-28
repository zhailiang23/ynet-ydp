package com.ynet.iplatform.module.twins.controller.admin.chatcollecttemplate;

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

import com.ynet.iplatform.module.twins.controller.admin.chatcollecttemplate.vo.*;
import com.ynet.iplatform.module.twins.dal.dataobject.chatcollecttemplate.ChatCollectTemplateDO;
import com.ynet.iplatform.module.twins.service.chatcollecttemplate.ChatCollectTemplateService;

@Tag(name = "管理后台 - 客户留资模板")
@RestController
@RequestMapping("/twins/chat-collect-template")
@Validated
public class ChatCollectTemplateController {

    @Resource
    private ChatCollectTemplateService chatCollectTemplateService;

    @PostMapping("/create")
    @Operation(summary = "创建客户留资模板")
    @PreAuthorize("@ss.hasPermission('twins:chat-collect-template:create')")
    public CommonResult<Integer> createChatCollectTemplate(@Valid @RequestBody ChatCollectTemplateSaveReqVO createReqVO) {
        return success(chatCollectTemplateService.createChatCollectTemplate(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户留资模板")
    @PreAuthorize("@ss.hasPermission('twins:chat-collect-template:update')")
    public CommonResult<Boolean> updateChatCollectTemplate(@Valid @RequestBody ChatCollectTemplateSaveReqVO updateReqVO) {
        chatCollectTemplateService.updateChatCollectTemplate(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户留资模板")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('twins:chat-collect-template:delete')")
    public CommonResult<Boolean> deleteChatCollectTemplate(@RequestParam("id") Integer id) {
        chatCollectTemplateService.deleteChatCollectTemplate(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户留资模板")
                @PreAuthorize("@ss.hasPermission('twins:chat-collect-template:delete')")
    public CommonResult<Boolean> deleteChatCollectTemplateList(@RequestParam("ids") List<Integer> ids) {
        chatCollectTemplateService.deleteChatCollectTemplateListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户留资模板")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('twins:chat-collect-template:query')")
    public CommonResult<ChatCollectTemplateRespVO> getChatCollectTemplate(@RequestParam("id") Integer id) {
        ChatCollectTemplateDO chatCollectTemplate = chatCollectTemplateService.getChatCollectTemplate(id);
        return success(BeanUtils.toBean(chatCollectTemplate, ChatCollectTemplateRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户留资模板分页")
    @PreAuthorize("@ss.hasPermission('twins:chat-collect-template:query')")
    public CommonResult<PageResult<ChatCollectTemplateRespVO>> getChatCollectTemplatePage(@Valid ChatCollectTemplatePageReqVO pageReqVO) {
        PageResult<ChatCollectTemplateDO> pageResult = chatCollectTemplateService.getChatCollectTemplatePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ChatCollectTemplateRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户留资模板 Excel")
    @PreAuthorize("@ss.hasPermission('twins:chat-collect-template:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportChatCollectTemplateExcel(@Valid ChatCollectTemplatePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ChatCollectTemplateDO> list = chatCollectTemplateService.getChatCollectTemplatePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户留资模板.xls", "数据", ChatCollectTemplateRespVO.class,
                        BeanUtils.toBean(list, ChatCollectTemplateRespVO.class));
    }

}