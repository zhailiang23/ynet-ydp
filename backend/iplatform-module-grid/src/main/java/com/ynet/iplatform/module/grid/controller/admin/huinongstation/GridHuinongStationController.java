package com.ynet.iplatform.module.grid.controller.admin.huinongstation;

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

import com.ynet.iplatform.module.grid.controller.admin.huinongstation.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.info.GridInfoDO;
import com.ynet.iplatform.module.grid.service.huinongstation.GridHuinongStationService;

@Tag(name = "管理后台 - 惠农站点信息")
@RestController
@RequestMapping("/grid/huinong-station")
@Validated
public class GridHuinongStationController {

    @Resource
    private GridHuinongStationService huinongStationService;

    @Resource
    private com.ynet.iplatform.module.grid.dal.mysql.info.GridInfoMapper gridInfoMapper;

    @PostMapping("/create")
    @Operation(summary = "创建惠农站点信息")
    @PreAuthorize("@ss.hasPermission('grid:huinong-station:create')")
    public CommonResult<Long> createHuinongStation(@Valid @RequestBody GridHuinongStationSaveReqVO createReqVO) {
        return success(huinongStationService.createHuinongStation(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新惠农站点信息")
    @PreAuthorize("@ss.hasPermission('grid:huinong-station:update')")
    public CommonResult<Boolean> updateHuinongStation(@Valid @RequestBody GridHuinongStationSaveReqVO updateReqVO) {
        huinongStationService.updateHuinongStation(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除惠农站点信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('grid:huinong-station:delete')")
    public CommonResult<Boolean> deleteHuinongStation(@RequestParam("id") Long id) {
        huinongStationService.deleteHuinongStation(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除惠农站点信息")
                @PreAuthorize("@ss.hasPermission('grid:huinong-station:delete')")
    public CommonResult<Boolean> deleteHuinongStationList(@RequestParam("ids") List<Long> ids) {
        huinongStationService.deleteHuinongStationListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得惠农站点信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('grid:huinong-station:query')")
    public CommonResult<GridHuinongStationRespVO> getHuinongStation(@RequestParam("id") Long id) {
        GridHuinongStationRespVO huinongStation = huinongStationService.getHuinongStation(id);
        return success(huinongStation);
    }

    @GetMapping("/map-data")
    @Operation(summary = "获得惠农站点地图数据")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('grid:huinong-station:query')")
    public CommonResult<GridHuinongStationMapVO> getHuinongStationMapData(@RequestParam("id") Long id) {
        GridHuinongStationMapVO mapData = huinongStationService.getHuinongStationMapData(id);
        return success(mapData);
    }

    @GetMapping("/map-marketing-markers")
    @Operation(summary = "获取站点下的营销信息地图标记列表")
    @Parameter(name = "stationId", description = "站点ID", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('grid:huinong-station:query')")
    public CommonResult<List<GridHuinongMarketingMarkerVO>> getMarketingMarkers(@RequestParam("stationId") Long stationId) {
        List<GridHuinongMarketingMarkerVO> markers = huinongStationService.getMarketingMarkers(stationId);
        return success(markers);
    }

    @GetMapping("/map-customer-markers")
    @Operation(summary = "获取站点下的客户地图标记列表")
    @Parameter(name = "stationId", description = "站点ID", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('grid:huinong-station:query')")
    public CommonResult<List<GridHuinongCustomerMarkerVO>> getCustomerMarkers(@RequestParam("stationId") Long stationId) {
        List<GridHuinongCustomerMarkerVO> markers = huinongStationService.getCustomerMarkers(stationId);
        return success(markers);
    }

    @GetMapping({"/list-all-simple", "/simple-list"})
    @Operation(summary = "获取惠农站点全列表", description = "只包含启用状态的站点，主要用于前端的下拉选项")
    public CommonResult<List<GridHuinongStationSimpleRespVO>> getSimpleHuinongStationList() {
        // 获得惠农站点列表，只要启用状态的
        List<GridInfoDO> list = huinongStationService.getHuinongStationList();
        // 转换为简化 VO（手动映射字段：gridName -> stationName, gridCode -> stationCode）
        List<GridHuinongStationSimpleRespVO> result = list.stream().map(grid -> {
            GridHuinongStationSimpleRespVO vo = new GridHuinongStationSimpleRespVO();
            vo.setId(grid.getId());
            vo.setStationName(grid.getGridName());
            vo.setStationCode(grid.getGridCode());
            return vo;
        }).collect(java.util.stream.Collectors.toList());
        return success(result);
    }

    @GetMapping("/page")
    @Operation(summary = "获得惠农站点信息分页")
    @PreAuthorize("@ss.hasPermission('grid:huinong-station:query')")
    public CommonResult<PageResult<GridHuinongStationRespVO>> getHuinongStationPage(@Valid GridHuinongStationPageReqVO pageReqVO) {
        // 使用带关联查询的方法
        // 1. 构建 MyBatis Plus 分页对象
        com.baomidou.mybatisplus.core.metadata.IPage<GridHuinongStationRespVO> mpPage =
                com.ynet.iplatform.framework.mybatis.core.util.MyBatisUtils.buildPage(pageReqVO);

        // 2. 执行分页查询 (使用 GridInfoMapper 的查询方法)
        mpPage = gridInfoMapper.selectHuinongStationPageWithRelations(mpPage, pageReqVO);

        // 3. 转换为框架的 PageResult
        PageResult<GridHuinongStationRespVO> pageResult = new PageResult<>(mpPage.getRecords(), mpPage.getTotal());

        return success(pageResult);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出惠农站点信息 Excel")
    @PreAuthorize("@ss.hasPermission('grid:huinong-station:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportHuinongStationExcel(@Valid GridHuinongStationPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GridInfoDO> list = huinongStationService.getHuinongStationPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "惠农站点信息.xls", "数据", GridHuinongStationRespVO.class,
                        BeanUtils.toBean(list, GridHuinongStationRespVO.class));
    }

    @GetMapping("/get-import-template")
    @Operation(summary = "获得惠农站点信息导入模板")
    @PreAuthorize("@ss.hasPermission('grid:huinong-station:import')")
    public void importTemplate(HttpServletResponse response) throws IOException {
        // 输出模板
        List<GridHuinongStationImportVO> list = java.util.Arrays.asList();
        ExcelUtils.write(response, "惠农站点信息导入模板.xls", "惠农站点列表", GridHuinongStationImportVO.class, list);
    }

    @PostMapping("/import")
    @Operation(summary = "导入惠农站点信息")
    @PreAuthorize("@ss.hasPermission('grid:huinong-station:import')")
    public CommonResult<GridHuinongStationImportRespVO> importExcel(
            @Parameter(description = "Excel 文件", required = true)
            @RequestParam("file") org.springframework.web.multipart.MultipartFile file,
            @Parameter(description = "是否支持更新，默认为 false", example = "true")
            @RequestParam(value = "updateSupport", required = false, defaultValue = "false") Boolean updateSupport) throws Exception {
        List<GridHuinongStationImportVO> list = ExcelUtils.read(file, GridHuinongStationImportVO.class);
        return success(huinongStationService.importHuinongStationList(list, updateSupport));
    }

}