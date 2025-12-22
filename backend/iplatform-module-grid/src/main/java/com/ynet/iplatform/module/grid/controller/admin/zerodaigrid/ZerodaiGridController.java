package com.ynet.iplatform.module.grid.controller.admin.zerodaigrid;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.CommonResult;
import static com.ynet.iplatform.framework.common.pojo.CommonResult.success;

import com.ynet.iplatform.module.grid.controller.admin.zerodaigrid.vo.*;
import com.ynet.iplatform.module.grid.service.zerodaigrid.ZerodaiGridService;

@Tag(name = "管理后台 - 零贷网格")
@RestController
@RequestMapping("/grid/zerodai-grid")
@Validated
public class ZerodaiGridController {

    @Resource
    private ZerodaiGridService zerodaiGridService;

    @GetMapping("/page")
    @Operation(summary = "获得零贷网格分页")
    @PreAuthorize("@ss.hasPermission('grid:zerodai-grid:query')")
    public CommonResult<PageResult<ZerodaiGridRespVO>> getZerodaiGridPage(@Valid ZerodaiGridPageReqVO pageReqVO) {
        PageResult<ZerodaiGridRespVO> pageResult = zerodaiGridService.getZerodaiGridPage(pageReqVO);
        return success(pageResult);
    }

    @GetMapping("/get")
    @Operation(summary = "获得零贷网格详情")
    @PreAuthorize("@ss.hasPermission('grid:zerodai-grid:query')")
    public CommonResult<ZerodaiGridRespVO> getZerodaiGrid(@RequestParam("id") Long id) {
        ZerodaiGridRespVO grid = zerodaiGridService.getZerodaiGrid(id);
        return success(grid);
    }

    @PostMapping("/create")
    @Operation(summary = "创建零贷网格")
    @PreAuthorize("@ss.hasPermission('grid:zerodai-grid:create')")
    public CommonResult<Long> createZerodaiGrid(@Valid @RequestBody ZerodaiGridSaveReqVO createReqVO) {
        return success(zerodaiGridService.createZerodaiGrid(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新零贷网格")
    @PreAuthorize("@ss.hasPermission('grid:zerodai-grid:update')")
    public CommonResult<Boolean> updateZerodaiGrid(@Valid @RequestBody ZerodaiGridSaveReqVO updateReqVO) {
        zerodaiGridService.updateZerodaiGrid(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除零贷网格")
    @PreAuthorize("@ss.hasPermission('grid:zerodai-grid:delete')")
    public CommonResult<Boolean> deleteZerodaiGrid(@RequestParam("id") Long id) {
        zerodaiGridService.deleteZerodaiGrid(id);
        return success(true);
    }

}
