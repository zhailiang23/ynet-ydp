package cn.iocoder.yudao.module.aicrm.controller.admin.companybond;

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

import cn.iocoder.yudao.module.aicrm.controller.admin.companybond.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.companybond.CompanyBondDO;
import cn.iocoder.yudao.module.aicrm.service.companybond.CompanyBondService;

@Tag(name = "管理后台 - 对公客户债券信息")
@RestController
@RequestMapping("/aicrm/company-bond")
@Validated
public class CompanyBondController {

    @Resource
    private CompanyBondService companyBondService;

    @PostMapping("/create")
    @Operation(summary = "创建对公客户债券信息")
    @PreAuthorize("@ss.hasPermission('aicrm:company-bond:create')")
    public CommonResult<Long> createCompanyBond(@Valid @RequestBody CompanyBondSaveReqVO createReqVO) {
        return success(companyBondService.createCompanyBond(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新对公客户债券信息")
    @PreAuthorize("@ss.hasPermission('aicrm:company-bond:update')")
    public CommonResult<Boolean> updateCompanyBond(@Valid @RequestBody CompanyBondSaveReqVO updateReqVO) {
        companyBondService.updateCompanyBond(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除对公客户债券信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:company-bond:delete')")
    public CommonResult<Boolean> deleteCompanyBond(@RequestParam("id") Long id) {
        companyBondService.deleteCompanyBond(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除对公客户债券信息")
                @PreAuthorize("@ss.hasPermission('aicrm:company-bond:delete')")
    public CommonResult<Boolean> deleteCompanyBondList(@RequestParam("ids") List<Long> ids) {
        companyBondService.deleteCompanyBondListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得对公客户债券信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:company-bond:query')")
    public CommonResult<CompanyBondRespVO> getCompanyBond(@RequestParam("id") Long id) {
        CompanyBondDO companyBond = companyBondService.getCompanyBond(id);
        return success(BeanUtils.toBean(companyBond, CompanyBondRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得对公客户债券信息分页")
    @PreAuthorize("@ss.hasPermission('aicrm:company-bond:query')")
    public CommonResult<PageResult<CompanyBondRespVO>> getCompanyBondPage(@Valid CompanyBondPageReqVO pageReqVO) {
        PageResult<CompanyBondDO> pageResult = companyBondService.getCompanyBondPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CompanyBondRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出对公客户债券信息 Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:company-bond:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCompanyBondExcel(@Valid CompanyBondPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CompanyBondDO> list = companyBondService.getCompanyBondPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "对公客户债券信息.xls", "数据", CompanyBondRespVO.class,
                        BeanUtils.toBean(list, CompanyBondRespVO.class));
    }

}