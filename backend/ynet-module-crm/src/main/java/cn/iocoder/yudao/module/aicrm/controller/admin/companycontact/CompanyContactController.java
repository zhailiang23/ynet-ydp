package cn.iocoder.yudao.module.aicrm.controller.admin.companycontact;

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

import cn.iocoder.yudao.module.aicrm.controller.admin.companycontact.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.companycontact.CompanyContactDO;
import cn.iocoder.yudao.module.aicrm.service.companycontact.CompanyContactService;

@Tag(name = "管理后台 - CRM对公客户联系人信息")
@RestController
@RequestMapping("/aicrm/company-contact")
@Validated
public class CompanyContactController {

    @Resource
    private CompanyContactService companyContactService;

    @PostMapping("/create")
    @Operation(summary = "创建CRM对公客户联系人信息")
    @PreAuthorize("@ss.hasPermission('aicrm:company-contact:create')")
    public CommonResult<Long> createCompanyContact(@Valid @RequestBody CompanyContactSaveReqVO createReqVO) {
        return success(companyContactService.createCompanyContact(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新CRM对公客户联系人信息")
    @PreAuthorize("@ss.hasPermission('aicrm:company-contact:update')")
    public CommonResult<Boolean> updateCompanyContact(@Valid @RequestBody CompanyContactSaveReqVO updateReqVO) {
        companyContactService.updateCompanyContact(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除CRM对公客户联系人信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:company-contact:delete')")
    public CommonResult<Boolean> deleteCompanyContact(@RequestParam("id") Long id) {
        companyContactService.deleteCompanyContact(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除CRM对公客户联系人信息")
                @PreAuthorize("@ss.hasPermission('aicrm:company-contact:delete')")
    public CommonResult<Boolean> deleteCompanyContactList(@RequestParam("ids") List<Long> ids) {
        companyContactService.deleteCompanyContactListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得CRM对公客户联系人信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:company-contact:query')")
    public CommonResult<CompanyContactRespVO> getCompanyContact(@RequestParam("id") Long id) {
        CompanyContactDO companyContact = companyContactService.getCompanyContact(id);
        return success(BeanUtils.toBean(companyContact, CompanyContactRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得CRM对公客户联系人信息分页")
    @PreAuthorize("@ss.hasPermission('aicrm:company-contact:query')")
    public CommonResult<PageResult<CompanyContactRespVO>> getCompanyContactPage(@Valid CompanyContactPageReqVO pageReqVO) {
        PageResult<CompanyContactDO> pageResult = companyContactService.getCompanyContactPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CompanyContactRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出CRM对公客户联系人信息 Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:company-contact:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCompanyContactExcel(@Valid CompanyContactPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CompanyContactDO> list = companyContactService.getCompanyContactPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "CRM对公客户联系人信息.xls", "数据", CompanyContactRespVO.class,
                        BeanUtils.toBean(list, CompanyContactRespVO.class));
    }

}