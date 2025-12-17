package com.ynet.iplatform.module.grid.controller.admin.zerodaimarketdescription;

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

import com.ynet.iplatform.module.grid.controller.admin.zerodaimarketdescription.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.zerodaimarketdescription.GridZerodaiMarketDescriptionDO;
import com.ynet.iplatform.module.grid.service.zerodaimarketdescription.GridZerodaiMarketDescriptionService;

@Tag(name = "管理后台 - 零贷市场描述")
@RestController
@RequestMapping("/grid/zerodai-market-description")
@Validated
public class GridZerodaiMarketDescriptionController {

    @Resource
    private GridZerodaiMarketDescriptionService zerodaiMarketDescriptionService;

    @PostMapping("/create")
    @Operation(summary = "创建零贷市场描述")
    @PreAuthorize("@ss.hasPermission('grid:zerodai-market-description:create')")
    public CommonResult<Long> createZerodaiMarketDescription(@Valid @RequestBody GridZerodaiMarketDescriptionSaveReqVO createReqVO) {
        return success(zerodaiMarketDescriptionService.createZerodaiMarketDescription(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新零贷市场描述")
    @PreAuthorize("@ss.hasPermission('grid:zerodai-market-description:update')")
    public CommonResult<Boolean> updateZerodaiMarketDescription(@Valid @RequestBody GridZerodaiMarketDescriptionSaveReqVO updateReqVO) {
        zerodaiMarketDescriptionService.updateZerodaiMarketDescription(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除零贷市场描述")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('grid:zerodai-market-description:delete')")
    public CommonResult<Boolean> deleteZerodaiMarketDescription(@RequestParam("id") Long id) {
        zerodaiMarketDescriptionService.deleteZerodaiMarketDescription(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除零贷市场描述")
                @PreAuthorize("@ss.hasPermission('grid:zerodai-market-description:delete')")
    public CommonResult<Boolean> deleteZerodaiMarketDescriptionList(@RequestParam("ids") List<Long> ids) {
        zerodaiMarketDescriptionService.deleteZerodaiMarketDescriptionListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得零贷市场描述")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('grid:zerodai-market-description:query')")
    public CommonResult<GridZerodaiMarketDescriptionRespVO> getZerodaiMarketDescription(@RequestParam("id") Long id) {
        GridZerodaiMarketDescriptionDO zerodaiMarketDescription = zerodaiMarketDescriptionService.getZerodaiMarketDescription(id);
        return success(BeanUtils.toBean(zerodaiMarketDescription, GridZerodaiMarketDescriptionRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得零贷市场描述分页")
    @PreAuthorize("@ss.hasPermission('grid:zerodai-market-description:query')")
    public CommonResult<PageResult<GridZerodaiMarketDescriptionRespVO>> getZerodaiMarketDescriptionPage(@Valid GridZerodaiMarketDescriptionPageReqVO pageReqVO) {
        PageResult<GridZerodaiMarketDescriptionDO> pageResult = zerodaiMarketDescriptionService.getZerodaiMarketDescriptionPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GridZerodaiMarketDescriptionRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出零贷市场描述 Excel")
    @PreAuthorize("@ss.hasPermission('grid:zerodai-market-description:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportZerodaiMarketDescriptionExcel(@Valid GridZerodaiMarketDescriptionPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GridZerodaiMarketDescriptionDO> list = zerodaiMarketDescriptionService.getZerodaiMarketDescriptionPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "零贷市场描述.xls", "数据", GridZerodaiMarketDescriptionRespVO.class,
                        BeanUtils.toBean(list, GridZerodaiMarketDescriptionRespVO.class));
    }

}