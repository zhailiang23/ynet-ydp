package com.ynet.iplatform.module.aicrm.controller.admin.customerguaranteemortgage;

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

import com.ynet.iplatform.module.aicrm.controller.admin.customerguaranteemortgage.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerguaranteemortgage.CustomerGuaranteeMortgageDO;
import com.ynet.iplatform.module.aicrm.service.customerguaranteemortgage.CustomerGuaranteeMortgageService;

@Tag(name = "管理后台 - 客户抵押物信息表（零售+对公共用）")
@RestController
@RequestMapping("/aicrm/customer-guarantee-mortgage")
@Validated
public class CustomerGuaranteeMortgageController {

    @Resource
    private CustomerGuaranteeMortgageService customerGuaranteeMortgageService;

    @PostMapping("/create")
    @Operation(summary = "创建客户抵押物信息表（零售+对公共用）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-guarantee-mortgage:create')")
    public CommonResult<Long> createCustomerGuaranteeMortgage(@Valid @RequestBody CustomerGuaranteeMortgageSaveReqVO createReqVO) {
        return success(customerGuaranteeMortgageService.createCustomerGuaranteeMortgage(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户抵押物信息表（零售+对公共用）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-guarantee-mortgage:update')")
    public CommonResult<Boolean> updateCustomerGuaranteeMortgage(@Valid @RequestBody CustomerGuaranteeMortgageSaveReqVO updateReqVO) {
        customerGuaranteeMortgageService.updateCustomerGuaranteeMortgage(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户抵押物信息表（零售+对公共用）")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-guarantee-mortgage:delete')")
    public CommonResult<Boolean> deleteCustomerGuaranteeMortgage(@RequestParam("id") Long id) {
        customerGuaranteeMortgageService.deleteCustomerGuaranteeMortgage(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户抵押物信息表（零售+对公共用）")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-guarantee-mortgage:delete')")
    public CommonResult<Boolean> deleteCustomerGuaranteeMortgageList(@RequestParam("ids") List<Long> ids) {
        customerGuaranteeMortgageService.deleteCustomerGuaranteeMortgageListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户抵押物信息表（零售+对公共用）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-guarantee-mortgage:query')")
    public CommonResult<CustomerGuaranteeMortgageRespVO> getCustomerGuaranteeMortgage(@RequestParam("id") Long id) {
        CustomerGuaranteeMortgageDO customerGuaranteeMortgage = customerGuaranteeMortgageService.getCustomerGuaranteeMortgage(id);
        return success(BeanUtils.toBean(customerGuaranteeMortgage, CustomerGuaranteeMortgageRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户抵押物信息表（零售+对公共用）分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-guarantee-mortgage:query')")
    public CommonResult<PageResult<CustomerGuaranteeMortgageRespVO>> getCustomerGuaranteeMortgagePage(@Valid CustomerGuaranteeMortgagePageReqVO pageReqVO) {
        PageResult<CustomerGuaranteeMortgageDO> pageResult = customerGuaranteeMortgageService.getCustomerGuaranteeMortgagePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerGuaranteeMortgageRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户抵押物信息表（零售+对公共用） Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-guarantee-mortgage:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerGuaranteeMortgageExcel(@Valid CustomerGuaranteeMortgagePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerGuaranteeMortgageDO> list = customerGuaranteeMortgageService.getCustomerGuaranteeMortgagePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户抵押物信息表（零售+对公共用）.xls", "数据", CustomerGuaranteeMortgageRespVO.class,
                        BeanUtils.toBean(list, CustomerGuaranteeMortgageRespVO.class));
    }

}