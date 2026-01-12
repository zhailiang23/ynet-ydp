package com.ynet.iplatform.module.grid.controller.admin.huinongmarketing;

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

import com.ynet.iplatform.module.grid.controller.admin.huinongmarketing.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.huinongmarketing.GridHuinongMarketingDO;
import com.ynet.iplatform.module.grid.service.huinongmarketing.GridHuinongMarketingService;

@Tag(name = "管理后台 - 惠农网格营销信息")
@RestController
@RequestMapping("/grid/huinong-marketing")
@Validated
public class GridHuinongMarketingController {

    @Resource
    private GridHuinongMarketingService huinongMarketingService;

    @PostMapping("/create")
    @Operation(summary = "创建惠农网格营销信息")
    @PreAuthorize("@ss.hasPermission('grid:huinong-marketing:create')")
    public CommonResult<Long> createHuinongMarketing(@Valid @RequestBody GridHuinongMarketingSaveReqVO createReqVO) {
        return success(huinongMarketingService.createHuinongMarketing(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新惠农网格营销信息")
    @PreAuthorize("@ss.hasPermission('grid:huinong-marketing:update')")
    public CommonResult<Boolean> updateHuinongMarketing(@Valid @RequestBody GridHuinongMarketingSaveReqVO updateReqVO) {
        huinongMarketingService.updateHuinongMarketing(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除惠农网格营销信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('grid:huinong-marketing:delete')")
    public CommonResult<Boolean> deleteHuinongMarketing(@RequestParam("id") Long id) {
        huinongMarketingService.deleteHuinongMarketing(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除惠农网格营销信息")
                @PreAuthorize("@ss.hasPermission('grid:huinong-marketing:delete')")
    public CommonResult<Boolean> deleteHuinongMarketingList(@RequestParam("ids") List<Long> ids) {
        huinongMarketingService.deleteHuinongMarketingListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得惠农网格营销信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('grid:huinong-marketing:query')")
    public CommonResult<GridHuinongMarketingRespVO> getHuinongMarketing(@RequestParam("id") Long id) {
        GridHuinongMarketingDO huinongMarketing = huinongMarketingService.getHuinongMarketing(id);
        return success(BeanUtils.toBean(huinongMarketing, GridHuinongMarketingRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得惠农网格营销信息分页")
    @PreAuthorize("@ss.hasPermission('grid:huinong-marketing:query')")
    public CommonResult<PageResult<GridHuinongMarketingRespVO>> getHuinongMarketingPage(@Valid GridHuinongMarketingPageReqVO pageReqVO) {
        PageResult<GridHuinongMarketingRespVO> pageResult = huinongMarketingService.getHuinongMarketingPage(pageReqVO);
        return success(pageResult);
    }

    @GetMapping("/heatmap/statistics")
    @Operation(summary = "获取惠农网格营销热力图统计数据")
    @PreAuthorize("@ss.hasPermission('grid:huinong-marketing:query')")
    public CommonResult<GridHuinongMarketingHeatmapStatisticsVO> getHeatmapStatistics(@Valid GridHuinongMarketingHeatmapReqVO reqVO) {
        GridHuinongMarketingHeatmapStatisticsVO statistics = huinongMarketingService.getHeatmapStatistics(reqVO);
        return success(statistics);
    }

    @GetMapping("/heatmap/data")
    @Operation(summary = "获取惠农网格营销热力图数据")
    @PreAuthorize("@ss.hasPermission('grid:huinong-marketing:query')")
    public CommonResult<List<GridHuinongMarketingHeatmapDataVO>> getHeatmapData(@Valid GridHuinongMarketingHeatmapReqVO reqVO) {
        List<GridHuinongMarketingHeatmapDataVO> data = huinongMarketingService.getHeatmapData(reqVO);
        return success(data);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出惠农网格营销信息 Excel")
    @PreAuthorize("@ss.hasPermission('grid:huinong-marketing:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportHuinongMarketingExcel(@Valid GridHuinongMarketingPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GridHuinongMarketingRespVO> list = huinongMarketingService.getHuinongMarketingPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "惠农网格营销信息.xls", "数据", GridHuinongMarketingRespVO.class, list);
    }

}