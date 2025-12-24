package com.ynet.iplatform.module.grid.controller.admin.huinongcustomerloan;

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

import com.ynet.iplatform.module.grid.controller.admin.huinongcustomerloan.vo.*;
import com.ynet.iplatform.module.grid.service.huinongcustomerloan.GridHuinongCustomerLoanService;

@Tag(name = "管理后台 - 惠农贷款客户扩展")
@RestController
@RequestMapping("/grid/huinong-customer-loan")
@Validated
public class GridHuinongCustomerLoanController {

    @Resource
    private GridHuinongCustomerLoanService huinongCustomerLoanService;

    @PostMapping("/create")
    @Operation(summary = "创建惠农贷款客户扩展")
    @PreAuthorize("@ss.hasPermission('grid:huinong-customer-loan:create')")
    public CommonResult<Long> createHuinongCustomerLoan(@Valid @RequestBody GridHuinongCustomerLoanSaveReqVO createReqVO) {
        return success(huinongCustomerLoanService.createHuinongCustomerLoan(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新惠农贷款客户扩展")
    @PreAuthorize("@ss.hasPermission('grid:huinong-customer-loan:update')")
    public CommonResult<Boolean> updateHuinongCustomerLoan(@Valid @RequestBody GridHuinongCustomerLoanSaveReqVO updateReqVO) {
        huinongCustomerLoanService.updateHuinongCustomerLoan(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除惠农贷款客户扩展")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('grid:huinong-customer-loan:delete')")
    public CommonResult<Boolean> deleteHuinongCustomerLoan(@RequestParam("id") Long id) {
        huinongCustomerLoanService.deleteHuinongCustomerLoan(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除惠农贷款客户扩展")
                @PreAuthorize("@ss.hasPermission('grid:huinong-customer-loan:delete')")
    public CommonResult<Boolean> deleteHuinongCustomerLoanList(@RequestParam("ids") List<Long> ids) {
        huinongCustomerLoanService.deleteHuinongCustomerLoanListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得惠农贷款客户扩展")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('grid:huinong-customer-loan:query')")
    public CommonResult<GridHuinongCustomerLoanRespVO> getHuinongCustomerLoan(@RequestParam("id") Long id) {
        GridHuinongCustomerLoanRespVO respVO = huinongCustomerLoanService.getHuinongCustomerLoan(id);
        return success(respVO);
    }

    @GetMapping("/page")
    @Operation(summary = "获得惠农贷款客户扩展分页")
    @PreAuthorize("@ss.hasPermission('grid:huinong-customer-loan:query')")
    public CommonResult<PageResult<GridHuinongCustomerLoanRespVO>> getHuinongCustomerLoanPage(@Valid GridHuinongCustomerLoanPageReqVO pageReqVO) {
        PageResult<GridHuinongCustomerLoanRespVO> pageResult = huinongCustomerLoanService.getHuinongCustomerLoanPage(pageReqVO);
        return success(pageResult);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出惠农贷款客户扩展 Excel")
    @PreAuthorize("@ss.hasPermission('grid:huinong-customer-loan:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportHuinongCustomerLoanExcel(@Valid GridHuinongCustomerLoanPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GridHuinongCustomerLoanRespVO> list = huinongCustomerLoanService.getHuinongCustomerLoanPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "惠农贷款客户扩展.xls", "数据", GridHuinongCustomerLoanRespVO.class, list);
    }

    @GetMapping("/heatmap/data")
    @Operation(summary = "获取惠农贷款目标客户热力图数据")
    @PreAuthorize("@ss.hasPermission('grid:huinong-customer-loan:query')")
    public CommonResult<List<GridHuinongCustomerLoanHeatmapDataVO>> getHeatmapData(@Valid GridHuinongCustomerLoanHeatmapReqVO reqVO) {
        List<GridHuinongCustomerLoanHeatmapDataVO> data = huinongCustomerLoanService.getHeatmapData(reqVO);
        return success(data);
    }

    @GetMapping("/heatmap/customer-markers")
    @Operation(summary = "获取惠农贷款目标客户标记列表")
    @PreAuthorize("@ss.hasPermission('grid:huinong-customer-loan:query')")
    public CommonResult<List<GridHuinongCustomerLoanCustomerMarkerVO>> getCustomerMarkers() {
        List<GridHuinongCustomerLoanCustomerMarkerVO> markers = huinongCustomerLoanService.getCustomerMarkers();
        return success(markers);
    }

}