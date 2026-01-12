package com.ynet.iplatform.module.grid.controller.admin.customer;

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

import com.ynet.iplatform.module.grid.controller.admin.customer.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.customer.GridCustomerDO;
import com.ynet.iplatform.module.grid.service.customer.GridCustomerService;

@Tag(name = "管理后台 - 网格客户档案")
@RestController
@RequestMapping("/grid/customer")
@Validated
public class GridCustomerController {

    @Resource
    private GridCustomerService customerService;

    @PostMapping("/create")
    @Operation(summary = "创建网格客户档案")
    @PreAuthorize("@ss.hasPermission('grid:customer:create')")
    public CommonResult<Long> createCustomer(@Valid @RequestBody GridCustomerSaveReqVO createReqVO) {
        return success(customerService.createCustomer(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新网格客户档案")
    @PreAuthorize("@ss.hasPermission('grid:customer:update')")
    public CommonResult<Boolean> updateCustomer(@Valid @RequestBody GridCustomerSaveReqVO updateReqVO) {
        customerService.updateCustomer(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除网格客户档案")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('grid:customer:delete')")
    public CommonResult<Boolean> deleteCustomer(@RequestParam("id") Long id) {
        customerService.deleteCustomer(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除网格客户档案")
                @PreAuthorize("@ss.hasPermission('grid:customer:delete')")
    public CommonResult<Boolean> deleteCustomerList(@RequestParam("ids") List<Long> ids) {
        customerService.deleteCustomerListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得网格客户档案")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('grid:customer:query')")
    public CommonResult<GridCustomerRespVO> getCustomer(@RequestParam("id") Long id) {
        GridCustomerDO customer = customerService.getCustomer(id);
        return success(BeanUtils.toBean(customer, GridCustomerRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得网格客户档案分页")
    @PreAuthorize("@ss.hasPermission('grid:customer:query')")
    public CommonResult<PageResult<GridCustomerRespVO>> getCustomerPage(@Valid GridCustomerPageReqVO pageReqVO) {
        PageResult<GridCustomerDO> pageResult = customerService.getCustomerPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GridCustomerRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出网格客户档案 Excel")
    @PreAuthorize("@ss.hasPermission('grid:customer:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerExcel(@Valid GridCustomerPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GridCustomerDO> list = customerService.getCustomerPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "网格客户档案.xls", "数据", GridCustomerRespVO.class,
                        BeanUtils.toBean(list, GridCustomerRespVO.class));
    }

}