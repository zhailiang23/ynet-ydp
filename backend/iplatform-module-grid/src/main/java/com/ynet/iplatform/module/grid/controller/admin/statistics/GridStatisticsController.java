package com.ynet.iplatform.module.grid.controller.admin.statistics;

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

import com.ynet.iplatform.module.grid.controller.admin.statistics.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.statistics.GridStatisticsDO;
import com.ynet.iplatform.module.grid.service.statistics.GridStatisticsService;

@Tag(name = "管理后台 - 网格统计报表")
@RestController
@RequestMapping("/grid/statistics")
@Validated
public class GridStatisticsController {

    @Resource
    private GridStatisticsService statisticsService;

    @PostMapping("/create")
    @Operation(summary = "创建网格统计报表")
    @PreAuthorize("@ss.hasPermission('grid:statistics:create')")
    public CommonResult<Long> createStatistics(@Valid @RequestBody GridStatisticsSaveReqVO createReqVO) {
        return success(statisticsService.createStatistics(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新网格统计报表")
    @PreAuthorize("@ss.hasPermission('grid:statistics:update')")
    public CommonResult<Boolean> updateStatistics(@Valid @RequestBody GridStatisticsSaveReqVO updateReqVO) {
        statisticsService.updateStatistics(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除网格统计报表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('grid:statistics:delete')")
    public CommonResult<Boolean> deleteStatistics(@RequestParam("id") Long id) {
        statisticsService.deleteStatistics(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除网格统计报表")
                @PreAuthorize("@ss.hasPermission('grid:statistics:delete')")
    public CommonResult<Boolean> deleteStatisticsList(@RequestParam("ids") List<Long> ids) {
        statisticsService.deleteStatisticsListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得网格统计报表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('grid:statistics:query')")
    public CommonResult<GridStatisticsRespVO> getStatistics(@RequestParam("id") Long id) {
        GridStatisticsDO statistics = statisticsService.getStatistics(id);
        return success(BeanUtils.toBean(statistics, GridStatisticsRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得网格统计报表分页")
    @PreAuthorize("@ss.hasPermission('grid:statistics:query')")
    public CommonResult<PageResult<GridStatisticsRespVO>> getStatisticsPage(@Valid GridStatisticsPageReqVO pageReqVO) {
        PageResult<GridStatisticsDO> pageResult = statisticsService.getStatisticsPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GridStatisticsRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出网格统计报表 Excel")
    @PreAuthorize("@ss.hasPermission('grid:statistics:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportStatisticsExcel(@Valid GridStatisticsPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GridStatisticsDO> list = statisticsService.getStatisticsPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "网格统计报表.xls", "数据", GridStatisticsRespVO.class,
                        BeanUtils.toBean(list, GridStatisticsRespVO.class));
    }

}