package cn.iocoder.yudao.module.aicrm.controller.admin.companyproject;

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

import cn.iocoder.yudao.module.aicrm.controller.admin.companyproject.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.companyproject.CompanyProjectDO;
import cn.iocoder.yudao.module.aicrm.service.companyproject.CompanyProjectService;

@Tag(name = "管理后台 - 对公客户项目信息")
@RestController
@RequestMapping("/aicrm/company-project")
@Validated
public class CompanyProjectController {

    @Resource
    private CompanyProjectService companyProjectService;

    @PostMapping("/create")
    @Operation(summary = "创建对公客户项目信息")
    @PreAuthorize("@ss.hasPermission('aicrm:company-project:create')")
    public CommonResult<Long> createCompanyProject(@Valid @RequestBody CompanyProjectSaveReqVO createReqVO) {
        return success(companyProjectService.createCompanyProject(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新对公客户项目信息")
    @PreAuthorize("@ss.hasPermission('aicrm:company-project:update')")
    public CommonResult<Boolean> updateCompanyProject(@Valid @RequestBody CompanyProjectSaveReqVO updateReqVO) {
        companyProjectService.updateCompanyProject(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除对公客户项目信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:company-project:delete')")
    public CommonResult<Boolean> deleteCompanyProject(@RequestParam("id") Long id) {
        companyProjectService.deleteCompanyProject(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除对公客户项目信息")
                @PreAuthorize("@ss.hasPermission('aicrm:company-project:delete')")
    public CommonResult<Boolean> deleteCompanyProjectList(@RequestParam("ids") List<Long> ids) {
        companyProjectService.deleteCompanyProjectListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得对公客户项目信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:company-project:query')")
    public CommonResult<CompanyProjectRespVO> getCompanyProject(@RequestParam("id") Long id) {
        CompanyProjectDO companyProject = companyProjectService.getCompanyProject(id);
        return success(BeanUtils.toBean(companyProject, CompanyProjectRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得对公客户项目信息分页")
    @PreAuthorize("@ss.hasPermission('aicrm:company-project:query')")
    public CommonResult<PageResult<CompanyProjectRespVO>> getCompanyProjectPage(@Valid CompanyProjectPageReqVO pageReqVO) {
        PageResult<CompanyProjectDO> pageResult = companyProjectService.getCompanyProjectPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CompanyProjectRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出对公客户项目信息 Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:company-project:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCompanyProjectExcel(@Valid CompanyProjectPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CompanyProjectDO> list = companyProjectService.getCompanyProjectPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "对公客户项目信息.xls", "数据", CompanyProjectRespVO.class,
                        BeanUtils.toBean(list, CompanyProjectRespVO.class));
    }

}