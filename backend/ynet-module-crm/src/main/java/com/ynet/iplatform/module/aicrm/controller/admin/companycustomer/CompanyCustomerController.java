package com.ynet.iplatform.module.aicrm.controller.admin.companycustomer;

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

import com.ynet.iplatform.module.aicrm.controller.admin.companycustomer.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.companycustomer.CompanyCustomerDO;
import com.ynet.iplatform.module.aicrm.service.companycustomer.CompanyCustomerService;

@Tag(name = "管理后台 - CRM对公客户扩展表(对公客户特有基本信息)")
@RestController
@RequestMapping("/crm/company-customer")
@Validated
public class CompanyCustomerController {

    @Resource
    private CompanyCustomerService companyCustomerService;

    @PostMapping("/create")
    @Operation(summary = "创建CRM对公客户扩展表(对公客户特有基本信息)")
    @PreAuthorize("@ss.hasPermission('crm:company-customer:create')")
    public CommonResult<Long> createCompanyCustomer(@Valid @RequestBody CompanyCustomerSaveReqVO createReqVO) {
        return success(companyCustomerService.createCompanyCustomer(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新CRM对公客户扩展表(对公客户特有基本信息)")
    @PreAuthorize("@ss.hasPermission('crm:company-customer:update')")
    public CommonResult<Boolean> updateCompanyCustomer(@Valid @RequestBody CompanyCustomerSaveReqVO updateReqVO) {
        companyCustomerService.updateCompanyCustomer(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除CRM对公客户扩展表(对公客户特有基本信息)")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('crm:company-customer:delete')")
    public CommonResult<Boolean> deleteCompanyCustomer(@RequestParam("id") Long id) {
        companyCustomerService.deleteCompanyCustomer(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除CRM对公客户扩展表(对公客户特有基本信息)")
                @PreAuthorize("@ss.hasPermission('crm:company-customer:delete')")
    public CommonResult<Boolean> deleteCompanyCustomerList(@RequestParam("ids") List<Long> ids) {
        companyCustomerService.deleteCompanyCustomerListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得CRM对公客户扩展表(对公客户特有基本信息)")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('crm:company-customer:query')")
    public CommonResult<CompanyCustomerRespVO> getCompanyCustomer(@RequestParam("id") Long id) {
        CompanyCustomerRespVO companyCustomer = companyCustomerService.getCompanyCustomer(id);
        return success(companyCustomer);
    }

    @GetMapping("/page")
    @Operation(summary = "获得CRM对公客户扩展表(对公客户特有基本信息)分页")
    @PreAuthorize("@ss.hasPermission('crm:company-customer:query')")
    public CommonResult<PageResult<CompanyCustomerRespVO>> getCompanyCustomerPage(@Valid CompanyCustomerPageReqVO pageReqVO) {
        PageResult<CompanyCustomerDO> pageResult = companyCustomerService.getCompanyCustomerPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CompanyCustomerRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出CRM对公客户扩展表(对公客户特有基本信息) Excel")
    @PreAuthorize("@ss.hasPermission('crm:company-customer:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCompanyCustomerExcel(@Valid CompanyCustomerPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CompanyCustomerDO> list = companyCustomerService.getCompanyCustomerPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "CRM对公客户扩展表(对公客户特有基本信息).xls", "数据", CompanyCustomerRespVO.class,
                        BeanUtils.toBean(list, CompanyCustomerRespVO.class));
    }

}