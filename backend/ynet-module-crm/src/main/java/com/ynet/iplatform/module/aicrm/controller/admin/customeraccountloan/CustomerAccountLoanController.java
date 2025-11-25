package com.ynet.iplatform.module.aicrm.controller.admin.customeraccountloan;

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

import com.ynet.iplatform.module.aicrm.controller.admin.customeraccountloan.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customeraccountloan.CustomerAccountLoanDO;
import com.ynet.iplatform.module.aicrm.service.customeraccountloan.CustomerAccountLoanService;

@Tag(name = "管理后台 - 客户贷款账户信息表（零售+对公共用）")
@RestController
@RequestMapping("/aicrm/customer-account-loan")
@Validated
public class CustomerAccountLoanController {

    @Resource
    private CustomerAccountLoanService customerAccountLoanService;

    @PostMapping("/create")
    @Operation(summary = "创建客户贷款账户信息表（零售+对公共用）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-account-loan:create')")
    public CommonResult<Long> createCustomerAccountLoan(@Valid @RequestBody CustomerAccountLoanSaveReqVO createReqVO) {
        return success(customerAccountLoanService.createCustomerAccountLoan(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户贷款账户信息表（零售+对公共用）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-account-loan:update')")
    public CommonResult<Boolean> updateCustomerAccountLoan(@Valid @RequestBody CustomerAccountLoanSaveReqVO updateReqVO) {
        customerAccountLoanService.updateCustomerAccountLoan(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户贷款账户信息表（零售+对公共用）")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-account-loan:delete')")
    public CommonResult<Boolean> deleteCustomerAccountLoan(@RequestParam("id") Long id) {
        customerAccountLoanService.deleteCustomerAccountLoan(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户贷款账户信息表（零售+对公共用）")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-account-loan:delete')")
    public CommonResult<Boolean> deleteCustomerAccountLoanList(@RequestParam("ids") List<Long> ids) {
        customerAccountLoanService.deleteCustomerAccountLoanListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户贷款账户信息表（零售+对公共用）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-account-loan:query')")
    public CommonResult<CustomerAccountLoanRespVO> getCustomerAccountLoan(@RequestParam("id") Long id) {
        CustomerAccountLoanDO customerAccountLoan = customerAccountLoanService.getCustomerAccountLoan(id);
        return success(BeanUtils.toBean(customerAccountLoan, CustomerAccountLoanRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户贷款账户信息表（零售+对公共用）分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-account-loan:query')")
    public CommonResult<PageResult<CustomerAccountLoanRespVO>> getCustomerAccountLoanPage(@Valid CustomerAccountLoanPageReqVO pageReqVO) {
        PageResult<CustomerAccountLoanDO> pageResult = customerAccountLoanService.getCustomerAccountLoanPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerAccountLoanRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户贷款账户信息表（零售+对公共用） Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-account-loan:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerAccountLoanExcel(@Valid CustomerAccountLoanPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerAccountLoanDO> list = customerAccountLoanService.getCustomerAccountLoanPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户贷款账户信息表（零售+对公共用）.xls", "数据", CustomerAccountLoanRespVO.class,
                        BeanUtils.toBean(list, CustomerAccountLoanRespVO.class));
    }

}