package com.ynet.iplatform.module.grid.controller.admin.lobbygrid;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.CommonResult;
import com.ynet.iplatform.module.grid.controller.admin.lobbygrid.vo.LobbyGridPageReqVO;
import com.ynet.iplatform.module.grid.controller.admin.lobbygrid.vo.LobbyGridRespVO;
import com.ynet.iplatform.module.grid.controller.admin.lobbygrid.vo.LobbyGridSaveReqVO;
import com.ynet.iplatform.module.grid.service.lobbygrid.LobbyGridService;

import static com.ynet.iplatform.framework.common.pojo.CommonResult.success;

/**
 * 厅堂网格 Controller
 *
 * @author 易诚源码
 */
@Tag(name = "管理后台 - 厅堂网格")
@RestController
@RequestMapping("/grid/lobby-grid")
@Validated
public class LobbyGridController {

    @Resource
    private LobbyGridService lobbyGridService;

    @GetMapping("/page")
    @Operation(summary = "获得厅堂网格分页")
    @PreAuthorize("@ss.hasPermission('grid:lobby-grid:query')")
    public CommonResult<PageResult<LobbyGridRespVO>> getLobbyGridPage(
            @Valid LobbyGridPageReqVO pageReqVO) {
        PageResult<LobbyGridRespVO> pageResult = lobbyGridService.getLobbyGridPage(pageReqVO);
        return success(pageResult);
    }

    @GetMapping("/get")
    @Operation(summary = "获得厅堂网格详情")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('grid:lobby-grid:query')")
    public CommonResult<LobbyGridRespVO> getLobbyGrid(@RequestParam("id") Long id) {
        LobbyGridRespVO lobbyGrid = lobbyGridService.getLobbyGrid(id);
        return success(lobbyGrid);
    }

    @PostMapping("/create")
    @Operation(summary = "创建厅堂网格")
    @PreAuthorize("@ss.hasPermission('grid:lobby-grid:create')")
    public CommonResult<Long> createLobbyGrid(@Valid @RequestBody LobbyGridSaveReqVO createReqVO) {
        return success(lobbyGridService.createLobbyGrid(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新厅堂网格")
    @PreAuthorize("@ss.hasPermission('grid:lobby-grid:update')")
    public CommonResult<Boolean> updateLobbyGrid(@Valid @RequestBody LobbyGridSaveReqVO updateReqVO) {
        lobbyGridService.updateLobbyGrid(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除厅堂网格")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('grid:lobby-grid:delete')")
    public CommonResult<Boolean> deleteLobbyGrid(@RequestParam("id") Long id) {
        lobbyGridService.deleteLobbyGrid(id);
        return success(true);
    }

}
