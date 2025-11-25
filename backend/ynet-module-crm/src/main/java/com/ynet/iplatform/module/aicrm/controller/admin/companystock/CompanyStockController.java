package com.ynet.iplatform.module.aicrm.controller.admin.companystock;

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

import com.ynet.iplatform.module.aicrm.controller.admin.companystock.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.companystock.CompanyStockDO;
import com.ynet.iplatform.module.aicrm.service.companystock.CompanyStockService;

@Tag(name = "管理后台 - 对公客户股票发行人信息")
@RestController
@RequestMapping("/aicrm/company-stock")
@Validated
public class CompanyStockController {

    @Resource
    private CompanyStockService companyStockService;

    @PostMapping("/create")
    @Operation(summary = "创建对公客户股票发行人信息")
    @PreAuthorize("@ss.hasPermission('aicrm:company-stock:create')")
    public CommonResult<Long> createCompanyStock(@Valid @RequestBody CompanyStockSaveReqVO createReqVO) {
        return success(companyStockService.createCompanyStock(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新对公客户股票发行人信息")
    @PreAuthorize("@ss.hasPermission('aicrm:company-stock:update')")
    public CommonResult<Boolean> updateCompanyStock(@Valid @RequestBody CompanyStockSaveReqVO updateReqVO) {
        companyStockService.updateCompanyStock(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除对公客户股票发行人信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:company-stock:delete')")
    public CommonResult<Boolean> deleteCompanyStock(@RequestParam("id") Long id) {
        companyStockService.deleteCompanyStock(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除对公客户股票发行人信息")
                @PreAuthorize("@ss.hasPermission('aicrm:company-stock:delete')")
    public CommonResult<Boolean> deleteCompanyStockList(@RequestParam("ids") List<Long> ids) {
        companyStockService.deleteCompanyStockListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得对公客户股票发行人信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:company-stock:query')")
    public CommonResult<CompanyStockRespVO> getCompanyStock(@RequestParam("id") Long id) {
        CompanyStockDO companyStock = companyStockService.getCompanyStock(id);
        return success(BeanUtils.toBean(companyStock, CompanyStockRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得对公客户股票发行人信息分页")
    @PreAuthorize("@ss.hasPermission('aicrm:company-stock:query')")
    public CommonResult<PageResult<CompanyStockRespVO>> getCompanyStockPage(@Valid CompanyStockPageReqVO pageReqVO) {
        PageResult<CompanyStockDO> pageResult = companyStockService.getCompanyStockPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CompanyStockRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出对公客户股票发行人信息 Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:company-stock:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCompanyStockExcel(@Valid CompanyStockPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CompanyStockDO> list = companyStockService.getCompanyStockPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "对公客户股票发行人信息.xls", "数据", CompanyStockRespVO.class,
                        BeanUtils.toBean(list, CompanyStockRespVO.class));
    }

}