package com.ynet.iplatform.module.infra.controller.admin.chatrobot;

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

import com.ynet.iplatform.module.infra.controller.admin.chatrobot.vo.*;
import com.ynet.iplatform.module.infra.dal.dataobject.chatrobot.ChatRobotDO;
import com.ynet.iplatform.module.infra.dal.dataobject.externalagent.ExternalAgentDO;
import com.ynet.iplatform.module.infra.service.chatrobot.ChatRobotService;
import com.ynet.iplatform.module.infra.service.externalagent.ExternalAgentService;

import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertMap;

@Tag(name = "管理后台 - 对话机器人管理")
@RestController
@RequestMapping("/infra/chat-robot")
@Validated
public class ChatRobotController {

    @Resource
    private ChatRobotService chatRobotService;

    @Resource
    private ExternalAgentService externalAgentService;

    @PostMapping("/create")
    @Operation(summary = "创建对话机器人管理")
    @PreAuthorize("@ss.hasPermission('infra:chat-robot:create')")
    public CommonResult<Long> createChatRobot(@Valid @RequestBody ChatRobotSaveReqVO createReqVO) {
        return success(chatRobotService.createChatRobot(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新对话机器人管理")
    @PreAuthorize("@ss.hasPermission('infra:chat-robot:update')")
    public CommonResult<Boolean> updateChatRobot(@Valid @RequestBody ChatRobotSaveReqVO updateReqVO) {
        chatRobotService.updateChatRobot(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除对话机器人管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('infra:chat-robot:delete')")
    public CommonResult<Boolean> deleteChatRobot(@RequestParam("id") Long id) {
        chatRobotService.deleteChatRobot(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除对话机器人管理")
                @PreAuthorize("@ss.hasPermission('infra:chat-robot:delete')")
    public CommonResult<Boolean> deleteChatRobotList(@RequestParam("ids") List<Long> ids) {
        chatRobotService.deleteChatRobotListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得对话机器人管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('infra:chat-robot:query')")
    public CommonResult<ChatRobotRespVO> getChatRobot(@RequestParam("id") Long id) {
        ChatRobotDO chatRobot = chatRobotService.getChatRobot(id);
        return success(BeanUtils.toBean(chatRobot, ChatRobotRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得对话机器人管理分页")
    @PreAuthorize("@ss.hasPermission('infra:chat-robot:query')")
    public CommonResult<PageResult<ChatRobotRespVO>> getChatRobotPage(@Valid ChatRobotPageReqVO pageReqVO) {
        PageResult<ChatRobotDO> pageResult = chatRobotService.getChatRobotPage(pageReqVO);
        PageResult<ChatRobotRespVO> voPageResult = BeanUtils.toBean(pageResult, ChatRobotRespVO.class);

        // 填充智能体名称
        fillAgentName(voPageResult.getList());

        return success(voPageResult);
    }

    /**
     * 填充智能体名称
     */
    private void fillAgentName(List<ChatRobotRespVO> list) {
        if (list == null || list.isEmpty()) {
            return;
        }

        // 收集所有智能体 ID
        Set<Long> agentIds = new HashSet<>();
        for (ChatRobotRespVO vo : list) {
            if (vo.getAgentId() != null) {
                agentIds.add(vo.getAgentId());
            }
        }

        if (agentIds.isEmpty()) {
            return;
        }

        // 批量查询智能体信息
        List<ExternalAgentDO> agents = externalAgentService.getExternalAgentList();
        Map<Long, String> agentNameMap = convertMap(agents, ExternalAgentDO::getId, ExternalAgentDO::getName);

        // 填充名称
        for (ChatRobotRespVO vo : list) {
            if (vo.getAgentId() != null) {
                vo.setAgentName(agentNameMap.get(vo.getAgentId()));
            }
        }
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出对话机器人管理 Excel")
    @PreAuthorize("@ss.hasPermission('infra:chat-robot:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportChatRobotExcel(@Valid ChatRobotPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ChatRobotDO> list = chatRobotService.getChatRobotPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "对话机器人管理.xls", "数据", ChatRobotRespVO.class,
                        BeanUtils.toBean(list, ChatRobotRespVO.class));
    }

}