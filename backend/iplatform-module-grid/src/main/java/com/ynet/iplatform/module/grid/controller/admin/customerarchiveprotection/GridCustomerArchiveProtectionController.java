package com.ynet.iplatform.module.grid.controller.admin.customerarchiveprotection;

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

import com.ynet.iplatform.module.grid.controller.admin.customerarchiveprotection.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.customerarchiveprotection.GridCustomerArchiveProtectionDO;
import com.ynet.iplatform.module.grid.service.customerarchiveprotection.GridCustomerArchiveProtectionService;

@Tag(name = "管理后台 - 客户档案保护记录")
@RestController
@RequestMapping("/grid/customer-archive-protection")
@Validated
public class GridCustomerArchiveProtectionController {

    @Resource
    private GridCustomerArchiveProtectionService customerArchiveProtectionService;

    @PostMapping("/create")
    @Operation(summary = "创建客户档案保护记录")
    @PreAuthorize("@ss.hasPermission('grid:customer-archive-protection:create')")
    public CommonResult<Long> createCustomerArchiveProtection(@Valid @RequestBody GridCustomerArchiveProtectionSaveReqVO createReqVO) {
        return success(customerArchiveProtectionService.createCustomerArchiveProtection(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户档案保护记录")
    @PreAuthorize("@ss.hasPermission('grid:customer-archive-protection:update')")
    public CommonResult<Boolean> updateCustomerArchiveProtection(@Valid @RequestBody GridCustomerArchiveProtectionSaveReqVO updateReqVO) {
        customerArchiveProtectionService.updateCustomerArchiveProtection(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户档案保护记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('grid:customer-archive-protection:delete')")
    public CommonResult<Boolean> deleteCustomerArchiveProtection(@RequestParam("id") Long id) {
        customerArchiveProtectionService.deleteCustomerArchiveProtection(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户档案保护记录")
                @PreAuthorize("@ss.hasPermission('grid:customer-archive-protection:delete')")
    public CommonResult<Boolean> deleteCustomerArchiveProtectionList(@RequestParam("ids") List<Long> ids) {
        customerArchiveProtectionService.deleteCustomerArchiveProtectionListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户档案保护记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('grid:customer-archive-protection:query')")
    public CommonResult<GridCustomerArchiveProtectionRespVO> getCustomerArchiveProtection(@RequestParam("id") Long id) {
        GridCustomerArchiveProtectionDO customerArchiveProtection = customerArchiveProtectionService.getCustomerArchiveProtection(id);
        return success(BeanUtils.toBean(customerArchiveProtection, GridCustomerArchiveProtectionRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户档案保护记录分页")
    @PreAuthorize("@ss.hasPermission('grid:customer-archive-protection:query')")
    public CommonResult<PageResult<GridCustomerArchiveProtectionRespVO>> getCustomerArchiveProtectionPage(@Valid GridCustomerArchiveProtectionPageReqVO pageReqVO) {
        PageResult<GridCustomerArchiveProtectionDO> pageResult = customerArchiveProtectionService.getCustomerArchiveProtectionPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GridCustomerArchiveProtectionRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户档案保护记录 Excel")
    @PreAuthorize("@ss.hasPermission('grid:customer-archive-protection:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerArchiveProtectionExcel(@Valid GridCustomerArchiveProtectionPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GridCustomerArchiveProtectionDO> list = customerArchiveProtectionService.getCustomerArchiveProtectionPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户档案保护记录.xls", "数据", GridCustomerArchiveProtectionRespVO.class,
                        BeanUtils.toBean(list, GridCustomerArchiveProtectionRespVO.class));
    }

}