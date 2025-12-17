package com.ynet.iplatform.module.grid.controller.admin.datasynclog;

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

import com.ynet.iplatform.module.grid.controller.admin.datasynclog.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.datasynclog.GridDataSyncLogDO;
import com.ynet.iplatform.module.grid.service.datasynclog.GridDataSyncLogService;

@Tag(name = "管理后台 - 数据同步记录")
@RestController
@RequestMapping("/grid/data-sync-log")
@Validated
public class GridDataSyncLogController {

    @Resource
    private GridDataSyncLogService dataSyncLogService;

    @PostMapping("/create")
    @Operation(summary = "创建数据同步记录")
    @PreAuthorize("@ss.hasPermission('grid:data-sync-log:create')")
    public CommonResult<Long> createDataSyncLog(@Valid @RequestBody GridDataSyncLogSaveReqVO createReqVO) {
        return success(dataSyncLogService.createDataSyncLog(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新数据同步记录")
    @PreAuthorize("@ss.hasPermission('grid:data-sync-log:update')")
    public CommonResult<Boolean> updateDataSyncLog(@Valid @RequestBody GridDataSyncLogSaveReqVO updateReqVO) {
        dataSyncLogService.updateDataSyncLog(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除数据同步记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('grid:data-sync-log:delete')")
    public CommonResult<Boolean> deleteDataSyncLog(@RequestParam("id") Long id) {
        dataSyncLogService.deleteDataSyncLog(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除数据同步记录")
                @PreAuthorize("@ss.hasPermission('grid:data-sync-log:delete')")
    public CommonResult<Boolean> deleteDataSyncLogList(@RequestParam("ids") List<Long> ids) {
        dataSyncLogService.deleteDataSyncLogListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得数据同步记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('grid:data-sync-log:query')")
    public CommonResult<GridDataSyncLogRespVO> getDataSyncLog(@RequestParam("id") Long id) {
        GridDataSyncLogDO dataSyncLog = dataSyncLogService.getDataSyncLog(id);
        return success(BeanUtils.toBean(dataSyncLog, GridDataSyncLogRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得数据同步记录分页")
    @PreAuthorize("@ss.hasPermission('grid:data-sync-log:query')")
    public CommonResult<PageResult<GridDataSyncLogRespVO>> getDataSyncLogPage(@Valid GridDataSyncLogPageReqVO pageReqVO) {
        PageResult<GridDataSyncLogDO> pageResult = dataSyncLogService.getDataSyncLogPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GridDataSyncLogRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出数据同步记录 Excel")
    @PreAuthorize("@ss.hasPermission('grid:data-sync-log:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDataSyncLogExcel(@Valid GridDataSyncLogPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GridDataSyncLogDO> list = dataSyncLogService.getDataSyncLogPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "数据同步记录.xls", "数据", GridDataSyncLogRespVO.class,
                        BeanUtils.toBean(list, GridDataSyncLogRespVO.class));
    }

}