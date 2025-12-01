package com.ynet.iplatform.module.knowledge.controller.admin.base;

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

import com.ynet.iplatform.module.knowledge.controller.admin.base.vo.*;
import com.ynet.iplatform.module.knowledge.dal.dataobject.base.KnowledgeBaseDO;
import com.ynet.iplatform.module.knowledge.service.base.KnowledgeBaseService;

@Tag(name = "管理后台 - 知识库")
@RestController
@RequestMapping("/knowledge/base")
@Validated
public class KnowledgeBaseController {

    @Resource
    private KnowledgeBaseService baseService;

    @PostMapping("/create")
    @Operation(summary = "创建知识库")
    @PreAuthorize("@ss.hasPermission('knowledge:base:create')")
    public CommonResult<Long> createBase(@Valid @RequestBody KnowledgeBaseSaveReqVO createReqVO) {
        return success(baseService.createBase(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新知识库")
    @PreAuthorize("@ss.hasPermission('knowledge:base:update')")
    public CommonResult<Boolean> updateBase(@Valid @RequestBody KnowledgeBaseSaveReqVO updateReqVO) {
        baseService.updateBase(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除知识库")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('knowledge:base:delete')")
    public CommonResult<Boolean> deleteBase(@RequestParam("id") Long id) {
        baseService.deleteBase(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除知识库")
                @PreAuthorize("@ss.hasPermission('knowledge:base:delete')")
    public CommonResult<Boolean> deleteBaseList(@RequestParam("ids") List<Long> ids) {
        baseService.deleteBaseListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得知识库")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('knowledge:base:query')")
    public CommonResult<KnowledgeBaseRespVO> getBase(@RequestParam("id") Long id) {
        KnowledgeBaseDO base = baseService.getBase(id);
        return success(BeanUtils.toBean(base, KnowledgeBaseRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得知识库分页")
    @PreAuthorize("@ss.hasPermission('knowledge:base:query')")
    public CommonResult<PageResult<KnowledgeBaseRespVO>> getBasePage(@Valid KnowledgeBasePageReqVO pageReqVO) {
        PageResult<KnowledgeBaseDO> pageResult = baseService.getBasePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, KnowledgeBaseRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出知识库 Excel")
    @PreAuthorize("@ss.hasPermission('knowledge:base:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportBaseExcel(@Valid KnowledgeBasePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<KnowledgeBaseDO> list = baseService.getBasePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "知识库.xls", "数据", KnowledgeBaseRespVO.class,
                        BeanUtils.toBean(list, KnowledgeBaseRespVO.class));
    }

    @GetMapping("/get-or-create-personal")
    @Operation(summary = "获取或创建个人知识库")
    @PreAuthorize("@ss.hasPermission('knowledge:personal:query')")
    public CommonResult<KnowledgeBaseRespVO> getOrCreatePersonalBase() {
        KnowledgeBaseDO base = baseService.getOrCreatePersonalBase();
        return success(BeanUtils.toBean(base, KnowledgeBaseRespVO.class));
    }

}