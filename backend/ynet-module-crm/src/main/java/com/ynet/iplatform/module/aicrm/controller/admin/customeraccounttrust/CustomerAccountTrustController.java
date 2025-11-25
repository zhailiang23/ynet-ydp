package com.ynet.iplatform.module.aicrm.controller.admin.customeraccounttrust;

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

import com.ynet.iplatform.module.aicrm.controller.admin.customeraccounttrust.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customeraccounttrust.CustomerAccountTrustDO;
import com.ynet.iplatform.module.aicrm.service.customeraccounttrust.CustomerAccountTrustService;

@Tag(name = "管理后台 - 客户信托账户信息表（零售+对公共用）")
@RestController
@RequestMapping("/aicrm/customer-account-trust")
@Validated
public class CustomerAccountTrustController {

    @Resource
    private CustomerAccountTrustService customerAccountTrustService;

    @PostMapping("/create")
    @Operation(summary = "创建客户信托账户信息表（零售+对公共用）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-account-trust:create')")
    public CommonResult<Long> createCustomerAccountTrust(@Valid @RequestBody CustomerAccountTrustSaveReqVO createReqVO) {
        return success(customerAccountTrustService.createCustomerAccountTrust(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户信托账户信息表（零售+对公共用）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-account-trust:update')")
    public CommonResult<Boolean> updateCustomerAccountTrust(@Valid @RequestBody CustomerAccountTrustSaveReqVO updateReqVO) {
        customerAccountTrustService.updateCustomerAccountTrust(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户信托账户信息表（零售+对公共用）")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-account-trust:delete')")
    public CommonResult<Boolean> deleteCustomerAccountTrust(@RequestParam("id") Long id) {
        customerAccountTrustService.deleteCustomerAccountTrust(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户信托账户信息表（零售+对公共用）")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-account-trust:delete')")
    public CommonResult<Boolean> deleteCustomerAccountTrustList(@RequestParam("ids") List<Long> ids) {
        customerAccountTrustService.deleteCustomerAccountTrustListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户信托账户信息表（零售+对公共用）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-account-trust:query')")
    public CommonResult<CustomerAccountTrustRespVO> getCustomerAccountTrust(@RequestParam("id") Long id) {
        CustomerAccountTrustDO customerAccountTrust = customerAccountTrustService.getCustomerAccountTrust(id);
        return success(BeanUtils.toBean(customerAccountTrust, CustomerAccountTrustRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户信托账户信息表（零售+对公共用）分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-account-trust:query')")
    public CommonResult<PageResult<CustomerAccountTrustRespVO>> getCustomerAccountTrustPage(@Valid CustomerAccountTrustPageReqVO pageReqVO) {
        PageResult<CustomerAccountTrustDO> pageResult = customerAccountTrustService.getCustomerAccountTrustPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerAccountTrustRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户信托账户信息表（零售+对公共用） Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-account-trust:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerAccountTrustExcel(@Valid CustomerAccountTrustPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerAccountTrustDO> list = customerAccountTrustService.getCustomerAccountTrustPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户信托账户信息表（零售+对公共用）.xls", "数据", CustomerAccountTrustRespVO.class,
                        BeanUtils.toBean(list, CustomerAccountTrustRespVO.class));
    }

}