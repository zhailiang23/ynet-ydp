package cn.iocoder.yudao.module.aicrm.controller.admin.practiceconversation;

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

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.aicrm.controller.admin.practiceconversation.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.practiceconversation.PracticeConversationDO;
import cn.iocoder.yudao.module.aicrm.service.practiceconversation.PracticeConversationService;

@Tag(name = "管理后台 - CRM智能陪练-陪练对话")
@RestController
@RequestMapping("/aicrm/practice-conversation")
@Validated
public class PracticeConversationController {

    @Resource
    private PracticeConversationService practiceConversationService;

    @PostMapping("/create")
    @Operation(summary = "创建CRM智能陪练-陪练对话")
    @PreAuthorize("@ss.hasPermission('aicrm:practice-conversation:create')")
    public CommonResult<Long> createPracticeConversation(@Valid @RequestBody PracticeConversationSaveReqVO createReqVO) {
        return success(practiceConversationService.createPracticeConversation(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新CRM智能陪练-陪练对话")
    @PreAuthorize("@ss.hasPermission('aicrm:practice-conversation:update')")
    public CommonResult<Boolean> updatePracticeConversation(@Valid @RequestBody PracticeConversationSaveReqVO updateReqVO) {
        practiceConversationService.updatePracticeConversation(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除CRM智能陪练-陪练对话")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:practice-conversation:delete')")
    public CommonResult<Boolean> deletePracticeConversation(@RequestParam("id") Long id) {
        practiceConversationService.deletePracticeConversation(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除CRM智能陪练-陪练对话")
                @PreAuthorize("@ss.hasPermission('aicrm:practice-conversation:delete')")
    public CommonResult<Boolean> deletePracticeConversationList(@RequestParam("ids") List<Long> ids) {
        practiceConversationService.deletePracticeConversationListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得CRM智能陪练-陪练对话")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:practice-conversation:query')")
    public CommonResult<PracticeConversationRespVO> getPracticeConversation(@RequestParam("id") Long id) {
        PracticeConversationDO practiceConversation = practiceConversationService.getPracticeConversation(id);
        return success(BeanUtils.toBean(practiceConversation, PracticeConversationRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得CRM智能陪练-陪练对话分页")
    @PreAuthorize("@ss.hasPermission('aicrm:practice-conversation:query')")
    public CommonResult<PageResult<PracticeConversationRespVO>> getPracticeConversationPage(@Valid PracticeConversationPageReqVO pageReqVO) {
        PageResult<PracticeConversationDO> pageResult = practiceConversationService.getPracticeConversationPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PracticeConversationRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出CRM智能陪练-陪练对话 Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:practice-conversation:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPracticeConversationExcel(@Valid PracticeConversationPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PracticeConversationDO> list = practiceConversationService.getPracticeConversationPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "CRM智能陪练-陪练对话.xls", "数据", PracticeConversationRespVO.class,
                        BeanUtils.toBean(list, PracticeConversationRespVO.class));
    }

}