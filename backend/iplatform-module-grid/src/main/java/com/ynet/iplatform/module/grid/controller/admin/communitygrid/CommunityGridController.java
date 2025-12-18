package com.ynet.iplatform.module.grid.controller.admin.communitygrid;

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

import com.ynet.iplatform.module.grid.controller.admin.communitygrid.vo.*;
import com.ynet.iplatform.module.grid.service.communitygrid.CommunityGridService;

@Tag(name = "管理后台 - 社区网格")
@RestController
@RequestMapping("/grid/community-grid")
@Validated
public class CommunityGridController {

    @Resource
    private CommunityGridService communityGridService;

    @GetMapping("/page")
    @Operation(summary = "获得社区网格分页")
    @PreAuthorize("@ss.hasPermission('grid:community-grid:query')")
    public CommonResult<PageResult<CommunityGridRespVO>> getCommunityGridPage(@Valid CommunityGridPageReqVO pageReqVO) {
        PageResult<CommunityGridRespVO> pageResult = communityGridService.getCommunityGridPage(pageReqVO);
        return success(pageResult);
    }

    @GetMapping("/get")
    @Operation(summary = "获得社区网格详情")
    @PreAuthorize("@ss.hasPermission('grid:community-grid:query')")
    public CommonResult<CommunityGridRespVO> getCommunityGrid(@RequestParam("id") Long id) {
        CommunityGridRespVO grid = communityGridService.getCommunityGrid(id);
        return success(grid);
    }

    @PostMapping("/create")
    @Operation(summary = "创建社区网格")
    @PreAuthorize("@ss.hasPermission('grid:community-grid:query')")
    public CommonResult<Long> createCommunityGrid(@Valid @RequestBody CommunityGridSaveReqVO createReqVO) {
        return success(communityGridService.createCommunityGrid(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新社区网格")
    @PreAuthorize("@ss.hasPermission('grid:community-grid:query')")
    public CommonResult<Boolean> updateCommunityGrid(@Valid @RequestBody CommunityGridSaveReqVO updateReqVO) {
        communityGridService.updateCommunityGrid(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除社区网格")
    @PreAuthorize("@ss.hasPermission('grid:community-grid:query')")
    public CommonResult<Boolean> deleteCommunityGrid(@RequestParam("id") Long id) {
        communityGridService.deleteCommunityGrid(id);
        return success(true);
    }

}
