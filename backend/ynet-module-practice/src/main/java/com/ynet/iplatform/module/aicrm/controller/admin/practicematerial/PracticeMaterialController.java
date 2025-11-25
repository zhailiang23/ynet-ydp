package com.ynet.iplatform.module.aicrm.controller.admin.practicematerial;

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

import com.ynet.iplatform.module.aicrm.controller.admin.practicematerial.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.practicematerial.PracticeMaterialDO;
import com.ynet.iplatform.module.aicrm.service.practicematerial.PracticeMaterialService;

@Tag(name = "管理后台 - CRM智能陪练-培训文件")
@RestController
@RequestMapping("/aicrm/practice-material")
@Validated
public class PracticeMaterialController {

    @Resource
    private PracticeMaterialService practiceMaterialService;

    @PostMapping("/create")
    @Operation(summary = "创建CRM智能陪练-培训文件")
    @PreAuthorize("@ss.hasPermission('aicrm:practice-material:create')")
    public CommonResult<Long> createPracticeMaterial(@Valid @RequestBody PracticeMaterialSaveReqVO createReqVO) {
        return success(practiceMaterialService.createPracticeMaterial(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新CRM智能陪练-培训文件")
    @PreAuthorize("@ss.hasPermission('aicrm:practice-material:update')")
    public CommonResult<Boolean> updatePracticeMaterial(@Valid @RequestBody PracticeMaterialSaveReqVO updateReqVO) {
        practiceMaterialService.updatePracticeMaterial(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除CRM智能陪练-培训文件")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:practice-material:delete')")
    public CommonResult<Boolean> deletePracticeMaterial(@RequestParam("id") Long id) {
        practiceMaterialService.deletePracticeMaterial(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除CRM智能陪练-培训文件")
                @PreAuthorize("@ss.hasPermission('aicrm:practice-material:delete')")
    public CommonResult<Boolean> deletePracticeMaterialList(@RequestParam("ids") List<Long> ids) {
        practiceMaterialService.deletePracticeMaterialListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得CRM智能陪练-培训文件")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:practice-material:query')")
    public CommonResult<PracticeMaterialRespVO> getPracticeMaterial(@RequestParam("id") Long id) {
        PracticeMaterialDO practiceMaterial = practiceMaterialService.getPracticeMaterial(id);
        return success(BeanUtils.toBean(practiceMaterial, PracticeMaterialRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得CRM智能陪练-培训文件分页")
    @PreAuthorize("@ss.hasPermission('aicrm:practice-material:query')")
    public CommonResult<PageResult<PracticeMaterialRespVO>> getPracticeMaterialPage(@Valid PracticeMaterialPageReqVO pageReqVO) {
        PageResult<PracticeMaterialDO> pageResult = practiceMaterialService.getPracticeMaterialPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PracticeMaterialRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出CRM智能陪练-培训文件 Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:practice-material:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPracticeMaterialExcel(@Valid PracticeMaterialPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PracticeMaterialDO> list = practiceMaterialService.getPracticeMaterialPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "CRM智能陪练-培训文件.xls", "数据", PracticeMaterialRespVO.class,
                        BeanUtils.toBean(list, PracticeMaterialRespVO.class));
    }

}