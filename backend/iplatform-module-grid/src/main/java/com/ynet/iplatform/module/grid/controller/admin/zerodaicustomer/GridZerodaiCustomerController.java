package com.ynet.iplatform.module.grid.controller.admin.zerodaicustomer;

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

import com.ynet.iplatform.module.grid.controller.admin.zerodaicustomer.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.zerodaicustomer.GridZerodaiCustomerDO;
import com.ynet.iplatform.module.grid.service.zerodaicustomer.GridZerodaiCustomerService;

@Tag(name = "管理后台 - 零贷客户扩展")
@RestController
@RequestMapping("/grid/zerodai-customer")
@Validated
public class GridZerodaiCustomerController {

    @Resource
    private GridZerodaiCustomerService zerodaiCustomerService;

    @PostMapping("/create")
    @Operation(summary = "创建零贷客户扩展")
    @PreAuthorize("@ss.hasPermission('grid:zerodai-customer:create')")
    public CommonResult<Long> createZerodaiCustomer(@Valid @RequestBody GridZerodaiCustomerSaveReqVO createReqVO) {
        return success(zerodaiCustomerService.createZerodaiCustomer(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新零贷客户扩展")
    @PreAuthorize("@ss.hasPermission('grid:zerodai-customer:update')")
    public CommonResult<Boolean> updateZerodaiCustomer(@Valid @RequestBody GridZerodaiCustomerSaveReqVO updateReqVO) {
        zerodaiCustomerService.updateZerodaiCustomer(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除零贷客户扩展")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('grid:zerodai-customer:delete')")
    public CommonResult<Boolean> deleteZerodaiCustomer(@RequestParam("id") Long id) {
        zerodaiCustomerService.deleteZerodaiCustomer(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除零贷客户扩展")
                @PreAuthorize("@ss.hasPermission('grid:zerodai-customer:delete')")
    public CommonResult<Boolean> deleteZerodaiCustomerList(@RequestParam("ids") List<Long> ids) {
        zerodaiCustomerService.deleteZerodaiCustomerListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得零贷客户扩展")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('grid:zerodai-customer:query')")
    public CommonResult<GridZerodaiCustomerRespVO> getZerodaiCustomer(@RequestParam("id") Long id) {
        GridZerodaiCustomerDO zerodaiCustomer = zerodaiCustomerService.getZerodaiCustomer(id);
        return success(BeanUtils.toBean(zerodaiCustomer, GridZerodaiCustomerRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得零贷客户扩展分页")
    @PreAuthorize("@ss.hasPermission('grid:zerodai-customer:query')")
    public CommonResult<PageResult<GridZerodaiCustomerRespVO>> getZerodaiCustomerPage(@Valid GridZerodaiCustomerPageReqVO pageReqVO) {
        PageResult<GridZerodaiCustomerDO> pageResult = zerodaiCustomerService.getZerodaiCustomerPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GridZerodaiCustomerRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出零贷客户扩展 Excel")
    @PreAuthorize("@ss.hasPermission('grid:zerodai-customer:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportZerodaiCustomerExcel(@Valid GridZerodaiCustomerPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GridZerodaiCustomerDO> list = zerodaiCustomerService.getZerodaiCustomerPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "零贷客户扩展.xls", "数据", GridZerodaiCustomerRespVO.class,
                        BeanUtils.toBean(list, GridZerodaiCustomerRespVO.class));
    }

}