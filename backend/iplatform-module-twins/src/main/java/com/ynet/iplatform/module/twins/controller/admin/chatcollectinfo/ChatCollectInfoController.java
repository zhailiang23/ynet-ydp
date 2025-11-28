package com.ynet.iplatform.module.twins.controller.admin.chatcollectinfo;

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
import java.util.stream.Collectors;
import java.io.IOException;

import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.CommonResult;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;
import static com.ynet.iplatform.framework.common.pojo.CommonResult.success;

import com.ynet.iplatform.framework.excel.core.util.ExcelUtils;

import com.ynet.iplatform.framework.apilog.core.annotation.ApiAccessLog;
import static com.ynet.iplatform.framework.apilog.core.enums.OperateTypeEnum.*;

import com.ynet.iplatform.module.twins.controller.admin.chatcollectinfo.vo.*;
import com.ynet.iplatform.module.twins.dal.dataobject.chatcollectinfo.ChatCollectInfoDO;
import com.ynet.iplatform.module.twins.service.chatcollectinfo.ChatCollectInfoService;
import com.ynet.iplatform.module.twins.dal.mysql.chatautomessages.ChatAutoMessagesMapper;
import com.ynet.iplatform.module.twins.dal.mysql.customerAdmin.CustomerAdminMapper;
import com.ynet.iplatform.module.twins.dal.mysql.customerUser.CustomerUserMapper;

@Tag(name = "管理后台 - 客户留资信息")
@RestController
@RequestMapping("/twins/chat-collect-info")
@Validated
public class ChatCollectInfoController {

    @Resource
    private ChatCollectInfoService chatCollectInfoService;

    @Resource
    private ChatAutoMessagesMapper chatAutoMessagesMapper;

    @Resource
    private CustomerAdminMapper customerAdminMapper;

    @Resource
    private CustomerUserMapper customerUserMapper;

    @PostMapping("/create")
    @Operation(summary = "创建客户留资信息")
    @PreAuthorize("@ss.hasPermission('twins:chat-collect-info:create')")
    public CommonResult<Integer> createChatCollectInfo(@Valid @RequestBody ChatCollectInfoSaveReqVO createReqVO) {
        return success(chatCollectInfoService.createChatCollectInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户留资信息")
    @PreAuthorize("@ss.hasPermission('twins:chat-collect-info:update')")
    public CommonResult<Boolean> updateChatCollectInfo(@Valid @RequestBody ChatCollectInfoSaveReqVO updateReqVO) {
        chatCollectInfoService.updateChatCollectInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户留资信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('twins:chat-collect-info:delete')")
    public CommonResult<Boolean> deleteChatCollectInfo(@RequestParam("id") Integer id) {
        chatCollectInfoService.deleteChatCollectInfo(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户留资信息")
                @PreAuthorize("@ss.hasPermission('twins:chat-collect-info:delete')")
    public CommonResult<Boolean> deleteChatCollectInfoList(@RequestParam("ids") List<Integer> ids) {
        chatCollectInfoService.deleteChatCollectInfoListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户留资信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('twins:chat-collect-info:query')")
    public CommonResult<ChatCollectInfoRespVO> getChatCollectInfo(@RequestParam("id") Integer id) {
        ChatCollectInfoDO chatCollectInfo = chatCollectInfoService.getChatCollectInfo(id);
        return success(BeanUtils.toBean(chatCollectInfo, ChatCollectInfoRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户留资信息分页")
    @PreAuthorize("@ss.hasPermission('twins:chat-collect-info:query')")
    public CommonResult<PageResult<ChatCollectInfoRespVO>> getChatCollectInfoPage(@Valid ChatCollectInfoPageReqVO pageReqVO) {
        PageResult<ChatCollectInfoDO> pageResult = chatCollectInfoService.getChatCollectInfoPage(pageReqVO);
        List<ChatCollectInfoRespVO> voList = BeanUtils.toBean(pageResult.getList(), ChatCollectInfoRespVO.class);

        // 填充关联数据
        fillRelatedData(voList);

        return success(new PageResult<>(voList, pageResult.getTotal()));
    }

    private void fillRelatedData(List<ChatCollectInfoRespVO> voList) {
        if (voList == null || voList.isEmpty()) {
            return;
        }

        // 收集所有需要查询的 ID
        Set<Integer> templateIds = voList.stream()
                .filter(vo -> vo.getTemplateId() != null)
                .map(ChatCollectInfoRespVO::getTemplateId)
                .collect(Collectors.toSet());
        Set<Integer> userIds = voList.stream()
                .filter(vo -> vo.getUserId() != null)
                .map(ChatCollectInfoRespVO::getUserId)
                .collect(Collectors.toSet());
        Set<Integer> adminIds = voList.stream()
                .filter(vo -> vo.getAdminId() != null)
                .map(ChatCollectInfoRespVO::getAdminId)
                .collect(Collectors.toSet());

        // 批量查询关联数据(使用 Map 存储)
        Map<Integer, String> templateNameMap = new HashMap<>();
        Map<Integer, String> userNameMap = new HashMap<>();
        Map<Integer, String> adminNameMap = new HashMap<>();

        // 查询模板名称 (customer_chat_auto_messages表)
        if (!templateIds.isEmpty()) {
            templateIds.forEach(templateId -> {
                try {
                    var template = chatAutoMessagesMapper.selectById(templateId);
                    if (template != null) {
                        templateNameMap.put(templateId, template.getName());
                    }
                } catch (Exception e) {
                    // 忽略查询错误
                }
            });
        }

        // 查询用户名 (users表)
        if (!userIds.isEmpty()) {
            userIds.forEach(userId -> {
                try {
                    var user = customerUserMapper.selectById(userId);
                    if (user != null) {
                        userNameMap.put(userId, user.getUsername());
                    }
                } catch (Exception e) {
                    // 忽略查询错误
                }
            });
        }

        // 查询客服名 (customer_admins表)
        if (!adminIds.isEmpty()) {
            adminIds.forEach(adminId -> {
                try {
                    var admin = customerAdminMapper.selectById(adminId);
                    if (admin != null) {
                        adminNameMap.put(adminId, admin.getUsername());
                    }
                } catch (Exception e) {
                    // 忽略查询错误
                }
            });
        }

        // 填充数据
        voList.forEach(vo -> {
            // 填充模板名称
            if (vo.getTemplateId() != null) {
                vo.setTemplateName(templateNameMap.getOrDefault(vo.getTemplateId(), ""));
            }

            // 填充状态文本
            if (vo.getStatus() != null) {
                switch (vo.getStatus()) {
                    case 0:
                        vo.setStatusText("未处理");
                        break;
                    case 1:
                        vo.setStatusText("处理中");
                        break;
                    case 2:
                        vo.setStatusText("处理完成");
                        break;
                    default:
                        vo.setStatusText("未知");
                }
            }

            // 填充用户名
            if (vo.getUserId() != null) {
                vo.setUsername(userNameMap.getOrDefault(vo.getUserId(), ""));
            }

            // 填充客服名
            if (vo.getAdminId() != null) {
                vo.setAdminName(adminNameMap.getOrDefault(vo.getAdminId(), ""));
            }
        });
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户留资信息 Excel")
    @PreAuthorize("@ss.hasPermission('twins:chat-collect-info:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportChatCollectInfoExcel(@Valid ChatCollectInfoPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ChatCollectInfoDO> list = chatCollectInfoService.getChatCollectInfoPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户留资信息.xls", "数据", ChatCollectInfoRespVO.class,
                        BeanUtils.toBean(list, ChatCollectInfoRespVO.class));
    }

}