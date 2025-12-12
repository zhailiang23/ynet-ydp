package com.ynet.iplatform.module.infra.controller.admin.externalagent;

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

import com.ynet.iplatform.module.infra.controller.admin.externalagent.vo.*;
import com.ynet.iplatform.module.infra.dal.dataobject.externalagent.ExternalAgentDO;
import com.ynet.iplatform.module.infra.service.externalagent.ExternalAgentService;

@Tag(name = "管理后台 - 外部智能体管理")
@RestController
@RequestMapping("/infra/external-agent")
@Validated
public class ExternalAgentController {

    @Resource
    private ExternalAgentService externalAgentService;

    @PostMapping("/create")
    @Operation(summary = "创建外部智能体管理")
    @PreAuthorize("@ss.hasPermission('infra:external-agent:create')")
    public CommonResult<Long> createExternalAgent(@Valid @RequestBody ExternalAgentSaveReqVO createReqVO) {
        return success(externalAgentService.createExternalAgent(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新外部智能体管理")
    @PreAuthorize("@ss.hasPermission('infra:external-agent:update')")
    public CommonResult<Boolean> updateExternalAgent(@Valid @RequestBody ExternalAgentSaveReqVO updateReqVO) {
        externalAgentService.updateExternalAgent(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除外部智能体管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('infra:external-agent:delete')")
    public CommonResult<Boolean> deleteExternalAgent(@RequestParam("id") Long id) {
        externalAgentService.deleteExternalAgent(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除外部智能体管理")
                @PreAuthorize("@ss.hasPermission('infra:external-agent:delete')")
    public CommonResult<Boolean> deleteExternalAgentList(@RequestParam("ids") List<Long> ids) {
        externalAgentService.deleteExternalAgentListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得外部智能体管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('infra:external-agent:query')")
    public CommonResult<ExternalAgentRespVO> getExternalAgent(@RequestParam("id") Long id) {
        ExternalAgentDO externalAgent = externalAgentService.getExternalAgent(id);
        return success(BeanUtils.toBean(externalAgent, ExternalAgentRespVO.class));
    }

    @GetMapping(value = {"/list-all-simple", "simple-list"})
    @Operation(summary = "获取外部智能体精简列表", description = "只包含 id 和 name，主要用于前端的下拉选项")
    public CommonResult<List<ExternalAgentSimpleRespVO>> getSimpleExternalAgentList() {
        List<ExternalAgentDO> list = externalAgentService.getExternalAgentList();
        return success(BeanUtils.toBean(list, ExternalAgentSimpleRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得外部智能体管理分页")
    @PreAuthorize("@ss.hasPermission('infra:external-agent:query')")
    public CommonResult<PageResult<ExternalAgentRespVO>> getExternalAgentPage(@Valid ExternalAgentPageReqVO pageReqVO) {
        PageResult<ExternalAgentDO> pageResult = externalAgentService.getExternalAgentPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ExternalAgentRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出外部智能体管理 Excel")
    @PreAuthorize("@ss.hasPermission('infra:external-agent:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportExternalAgentExcel(@Valid ExternalAgentPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ExternalAgentDO> list = externalAgentService.getExternalAgentPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "外部智能体管理.xls", "数据", ExternalAgentRespVO.class,
                        BeanUtils.toBean(list, ExternalAgentRespVO.class));
    }

}