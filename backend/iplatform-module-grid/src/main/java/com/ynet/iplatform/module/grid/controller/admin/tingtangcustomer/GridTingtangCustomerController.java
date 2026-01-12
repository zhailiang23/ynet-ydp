package com.ynet.iplatform.module.grid.controller.admin.tingtangcustomer;

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

import com.ynet.iplatform.module.grid.controller.admin.tingtangcustomer.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.tingtangcustomer.GridTingtangCustomerDO;
import com.ynet.iplatform.module.grid.service.tingtangcustomer.GridTingtangCustomerService;

@Tag(name = "管理后台 - 厅堂客户扩展")
@RestController
@RequestMapping("/grid/tingtang-customer")
@Validated
public class GridTingtangCustomerController {

    @Resource
    private GridTingtangCustomerService tingtangCustomerService;

    @PostMapping("/create")
    @Operation(summary = "创建厅堂客户扩展")
    @PreAuthorize("@ss.hasPermission('grid:tingtang-customer:create')")
    public CommonResult<Long> createTingtangCustomer(@Valid @RequestBody GridTingtangCustomerSaveReqVO createReqVO) {
        return success(tingtangCustomerService.createTingtangCustomer(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新厅堂客户扩展")
    @PreAuthorize("@ss.hasPermission('grid:tingtang-customer:update')")
    public CommonResult<Boolean> updateTingtangCustomer(@Valid @RequestBody GridTingtangCustomerSaveReqVO updateReqVO) {
        tingtangCustomerService.updateTingtangCustomer(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除厅堂客户扩展")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('grid:tingtang-customer:delete')")
    public CommonResult<Boolean> deleteTingtangCustomer(@RequestParam("id") Long id) {
        tingtangCustomerService.deleteTingtangCustomer(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除厅堂客户扩展")
                @PreAuthorize("@ss.hasPermission('grid:tingtang-customer:delete')")
    public CommonResult<Boolean> deleteTingtangCustomerList(@RequestParam("ids") List<Long> ids) {
        tingtangCustomerService.deleteTingtangCustomerListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得厅堂客户")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('grid:tingtang-customer:query')")
    public CommonResult<GridTingtangCustomerRespVO> getTingtangCustomer(@RequestParam("id") Long id) {
        return success(tingtangCustomerService.getTingtangCustomer(id));
    }

    @GetMapping("/page")
    @Operation(summary = "获得厅堂客户分页")
    @PreAuthorize("@ss.hasPermission('grid:tingtang-customer:query')")
    public CommonResult<PageResult<GridTingtangCustomerRespVO>> getTingtangCustomerPage(@Valid GridTingtangCustomerPageReqVO pageReqVO) {
        return success(tingtangCustomerService.getTingtangCustomerPage(pageReqVO));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出厅堂客户 Excel")
    @PreAuthorize("@ss.hasPermission('grid:tingtang-customer:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportTingtangCustomerExcel(@Valid GridTingtangCustomerPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GridTingtangCustomerRespVO> list = tingtangCustomerService.getTingtangCustomerPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "厅堂客户.xls", "数据", GridTingtangCustomerRespVO.class, list);
    }

}