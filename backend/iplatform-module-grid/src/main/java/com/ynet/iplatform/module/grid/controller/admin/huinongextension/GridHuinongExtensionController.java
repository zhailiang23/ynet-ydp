package com.ynet.iplatform.module.grid.controller.admin.huinongextension;

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

import com.ynet.iplatform.module.grid.controller.admin.huinongextension.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.huinongextension.GridHuinongExtensionDO;
import com.ynet.iplatform.module.grid.service.huinongextension.GridHuinongExtensionService;

@Tag(name = "管理后台 - 惠农网格扩展")
@RestController
@RequestMapping("/grid/huinong-extension")
@Validated
public class GridHuinongExtensionController {

    @Resource
    private GridHuinongExtensionService huinongExtensionService;

    @PostMapping("/create")
    @Operation(summary = "创建惠农网格扩展")
    @PreAuthorize("@ss.hasPermission('grid:huinong-extension:create')")
    public CommonResult<Long> createHuinongExtension(@Valid @RequestBody GridHuinongExtensionSaveReqVO createReqVO) {
        return success(huinongExtensionService.createHuinongExtension(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新惠农网格扩展")
    @PreAuthorize("@ss.hasPermission('grid:huinong-extension:update')")
    public CommonResult<Boolean> updateHuinongExtension(@Valid @RequestBody GridHuinongExtensionSaveReqVO updateReqVO) {
        huinongExtensionService.updateHuinongExtension(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除惠农网格扩展")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('grid:huinong-extension:delete')")
    public CommonResult<Boolean> deleteHuinongExtension(@RequestParam("id") Long id) {
        huinongExtensionService.deleteHuinongExtension(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除惠农网格扩展")
                @PreAuthorize("@ss.hasPermission('grid:huinong-extension:delete')")
    public CommonResult<Boolean> deleteHuinongExtensionList(@RequestParam("ids") List<Long> ids) {
        huinongExtensionService.deleteHuinongExtensionListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得惠农网格扩展")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('grid:huinong-extension:query')")
    public CommonResult<GridHuinongExtensionRespVO> getHuinongExtension(@RequestParam("id") Long id) {
        GridHuinongExtensionDO huinongExtension = huinongExtensionService.getHuinongExtension(id);
        return success(BeanUtils.toBean(huinongExtension, GridHuinongExtensionRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得惠农网格扩展分页")
    @PreAuthorize("@ss.hasPermission('grid:huinong-extension:query')")
    public CommonResult<PageResult<GridHuinongExtensionRespVO>> getHuinongExtensionPage(@Valid GridHuinongExtensionPageReqVO pageReqVO) {
        PageResult<GridHuinongExtensionDO> pageResult = huinongExtensionService.getHuinongExtensionPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GridHuinongExtensionRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出惠农网格扩展 Excel")
    @PreAuthorize("@ss.hasPermission('grid:huinong-extension:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportHuinongExtensionExcel(@Valid GridHuinongExtensionPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GridHuinongExtensionDO> list = huinongExtensionService.getHuinongExtensionPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "惠农网格扩展.xls", "数据", GridHuinongExtensionRespVO.class,
                        BeanUtils.toBean(list, GridHuinongExtensionRespVO.class));
    }

}