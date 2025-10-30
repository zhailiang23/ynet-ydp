package cn.iocoder.yudao.module.aicrm.controller.admin.companyotherbank;

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

import cn.iocoder.yudao.module.aicrm.controller.admin.companyotherbank.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.companyotherbank.CompanyOtherBankDO;
import cn.iocoder.yudao.module.aicrm.service.companyotherbank.CompanyOtherBankService;

@Tag(name = "管理后台 - 对公客户他行信息")
@RestController
@RequestMapping("/aicrm/company-other-bank")
@Validated
public class CompanyOtherBankController {

    @Resource
    private CompanyOtherBankService companyOtherBankService;

    @PostMapping("/create")
    @Operation(summary = "创建对公客户他行信息")
    @PreAuthorize("@ss.hasPermission('aicrm:company-other-bank:create')")
    public CommonResult<Long> createCompanyOtherBank(@Valid @RequestBody CompanyOtherBankSaveReqVO createReqVO) {
        return success(companyOtherBankService.createCompanyOtherBank(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新对公客户他行信息")
    @PreAuthorize("@ss.hasPermission('aicrm:company-other-bank:update')")
    public CommonResult<Boolean> updateCompanyOtherBank(@Valid @RequestBody CompanyOtherBankSaveReqVO updateReqVO) {
        companyOtherBankService.updateCompanyOtherBank(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除对公客户他行信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:company-other-bank:delete')")
    public CommonResult<Boolean> deleteCompanyOtherBank(@RequestParam("id") Long id) {
        companyOtherBankService.deleteCompanyOtherBank(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除对公客户他行信息")
                @PreAuthorize("@ss.hasPermission('aicrm:company-other-bank:delete')")
    public CommonResult<Boolean> deleteCompanyOtherBankList(@RequestParam("ids") List<Long> ids) {
        companyOtherBankService.deleteCompanyOtherBankListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得对公客户他行信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:company-other-bank:query')")
    public CommonResult<CompanyOtherBankRespVO> getCompanyOtherBank(@RequestParam("id") Long id) {
        CompanyOtherBankDO companyOtherBank = companyOtherBankService.getCompanyOtherBank(id);
        return success(BeanUtils.toBean(companyOtherBank, CompanyOtherBankRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得对公客户他行信息分页")
    @PreAuthorize("@ss.hasPermission('aicrm:company-other-bank:query')")
    public CommonResult<PageResult<CompanyOtherBankRespVO>> getCompanyOtherBankPage(@Valid CompanyOtherBankPageReqVO pageReqVO) {
        PageResult<CompanyOtherBankDO> pageResult = companyOtherBankService.getCompanyOtherBankPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CompanyOtherBankRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出对公客户他行信息 Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:company-other-bank:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCompanyOtherBankExcel(@Valid CompanyOtherBankPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CompanyOtherBankDO> list = companyOtherBankService.getCompanyOtherBankPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "对公客户他行信息.xls", "数据", CompanyOtherBankRespVO.class,
                        BeanUtils.toBean(list, CompanyOtherBankRespVO.class));
    }

}