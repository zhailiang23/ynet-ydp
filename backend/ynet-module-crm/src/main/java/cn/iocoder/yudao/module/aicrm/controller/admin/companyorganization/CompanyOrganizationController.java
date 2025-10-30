package cn.iocoder.yudao.module.aicrm.controller.admin.companyorganization;

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

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.aicrm.controller.admin.companyorganization.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.companyorganization.CompanyOrganizationDO;
import cn.iocoder.yudao.module.aicrm.service.companyorganization.CompanyOrganizationService;

@Tag(name = "管理后台 - CRM对公客户组织架构信息")
@RestController
@RequestMapping("/aicrm/company-organization")
@Validated
public class CompanyOrganizationController {

    @Resource
    private CompanyOrganizationService companyOrganizationService;

    @PostMapping("/create")
    @Operation(summary = "创建CRM对公客户组织架构信息")
    @PreAuthorize("@ss.hasPermission('aicrm:company-organization:create')")
    public CommonResult<Long> createCompanyOrganization(@Valid @RequestBody CompanyOrganizationSaveReqVO createReqVO) {
        return success(companyOrganizationService.createCompanyOrganization(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新CRM对公客户组织架构信息")
    @PreAuthorize("@ss.hasPermission('aicrm:company-organization:update')")
    public CommonResult<Boolean> updateCompanyOrganization(@Valid @RequestBody CompanyOrganizationSaveReqVO updateReqVO) {
        companyOrganizationService.updateCompanyOrganization(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除CRM对公客户组织架构信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:company-organization:delete')")
    public CommonResult<Boolean> deleteCompanyOrganization(@RequestParam("id") Long id) {
        companyOrganizationService.deleteCompanyOrganization(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除CRM对公客户组织架构信息")
                @PreAuthorize("@ss.hasPermission('aicrm:company-organization:delete')")
    public CommonResult<Boolean> deleteCompanyOrganizationList(@RequestParam("ids") List<Long> ids) {
        companyOrganizationService.deleteCompanyOrganizationListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得CRM对公客户组织架构信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:company-organization:query')")
    public CommonResult<CompanyOrganizationRespVO> getCompanyOrganization(@RequestParam("id") Long id) {
        CompanyOrganizationDO companyOrganization = companyOrganizationService.getCompanyOrganization(id);
        return success(BeanUtils.toBean(companyOrganization, CompanyOrganizationRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得CRM对公客户组织架构信息分页")
    @PreAuthorize("@ss.hasPermission('aicrm:company-organization:query')")
    public CommonResult<PageResult<CompanyOrganizationRespVO>> getCompanyOrganizationPage(@Valid CompanyOrganizationPageReqVO pageReqVO) {
        PageResult<CompanyOrganizationDO> pageResult = companyOrganizationService.getCompanyOrganizationPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CompanyOrganizationRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出CRM对公客户组织架构信息 Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:company-organization:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCompanyOrganizationExcel(@Valid CompanyOrganizationPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CompanyOrganizationDO> list = companyOrganizationService.getCompanyOrganizationPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "CRM对公客户组织架构信息.xls", "数据", CompanyOrganizationRespVO.class,
                        BeanUtils.toBean(list, CompanyOrganizationRespVO.class));
    }

}