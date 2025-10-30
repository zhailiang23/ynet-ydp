package cn.iocoder.yudao.module.aicrm.controller.admin.companyaddress;

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

import cn.iocoder.yudao.module.aicrm.controller.admin.companyaddress.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.companyaddress.CompanyAddressDO;
import cn.iocoder.yudao.module.aicrm.service.companyaddress.CompanyAddressService;

@Tag(name = "管理后台 - CRM对公客户地址信息")
@RestController
@RequestMapping("/aicrm/company-address")
@Validated
public class CompanyAddressController {

    @Resource
    private CompanyAddressService companyAddressService;

    @PostMapping("/create")
    @Operation(summary = "创建CRM对公客户地址信息")
    @PreAuthorize("@ss.hasPermission('aicrm:company-address:create')")
    public CommonResult<Long> createCompanyAddress(@Valid @RequestBody CompanyAddressSaveReqVO createReqVO) {
        return success(companyAddressService.createCompanyAddress(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新CRM对公客户地址信息")
    @PreAuthorize("@ss.hasPermission('aicrm:company-address:update')")
    public CommonResult<Boolean> updateCompanyAddress(@Valid @RequestBody CompanyAddressSaveReqVO updateReqVO) {
        companyAddressService.updateCompanyAddress(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除CRM对公客户地址信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:company-address:delete')")
    public CommonResult<Boolean> deleteCompanyAddress(@RequestParam("id") Long id) {
        companyAddressService.deleteCompanyAddress(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除CRM对公客户地址信息")
                @PreAuthorize("@ss.hasPermission('aicrm:company-address:delete')")
    public CommonResult<Boolean> deleteCompanyAddressList(@RequestParam("ids") List<Long> ids) {
        companyAddressService.deleteCompanyAddressListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得CRM对公客户地址信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:company-address:query')")
    public CommonResult<CompanyAddressRespVO> getCompanyAddress(@RequestParam("id") Long id) {
        CompanyAddressDO companyAddress = companyAddressService.getCompanyAddress(id);
        return success(BeanUtils.toBean(companyAddress, CompanyAddressRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得CRM对公客户地址信息分页")
    @PreAuthorize("@ss.hasPermission('aicrm:company-address:query')")
    public CommonResult<PageResult<CompanyAddressRespVO>> getCompanyAddressPage(@Valid CompanyAddressPageReqVO pageReqVO) {
        PageResult<CompanyAddressDO> pageResult = companyAddressService.getCompanyAddressPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CompanyAddressRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出CRM对公客户地址信息 Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:company-address:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCompanyAddressExcel(@Valid CompanyAddressPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CompanyAddressDO> list = companyAddressService.getCompanyAddressPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "CRM对公客户地址信息.xls", "数据", CompanyAddressRespVO.class,
                        BeanUtils.toBean(list, CompanyAddressRespVO.class));
    }

}