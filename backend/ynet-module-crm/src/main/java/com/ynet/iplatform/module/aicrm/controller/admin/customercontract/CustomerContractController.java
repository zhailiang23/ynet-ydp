package com.ynet.iplatform.module.aicrm.controller.admin.customercontract;

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

import com.ynet.iplatform.module.aicrm.controller.admin.customercontract.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customercontract.CustomerContractDO;
import com.ynet.iplatform.module.aicrm.service.customercontract.CustomerContractService;

@Tag(name = "管理后台 - 客户签约信息表（零售+对公共用）")
@RestController
@RequestMapping("/aicrm/customer-contract")
@Validated
public class CustomerContractController {

    @Resource
    private CustomerContractService customerContractService;

    @PostMapping("/create")
    @Operation(summary = "创建客户签约信息表（零售+对公共用）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-contract:create')")
    public CommonResult<Long> createCustomerContract(@Valid @RequestBody CustomerContractSaveReqVO createReqVO) {
        return success(customerContractService.createCustomerContract(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户签约信息表（零售+对公共用）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-contract:update')")
    public CommonResult<Boolean> updateCustomerContract(@Valid @RequestBody CustomerContractSaveReqVO updateReqVO) {
        customerContractService.updateCustomerContract(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户签约信息表（零售+对公共用）")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-contract:delete')")
    public CommonResult<Boolean> deleteCustomerContract(@RequestParam("id") Long id) {
        customerContractService.deleteCustomerContract(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户签约信息表（零售+对公共用）")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-contract:delete')")
    public CommonResult<Boolean> deleteCustomerContractList(@RequestParam("ids") List<Long> ids) {
        customerContractService.deleteCustomerContractListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户签约信息表（零售+对公共用）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-contract:query')")
    public CommonResult<CustomerContractRespVO> getCustomerContract(@RequestParam("id") Long id) {
        CustomerContractDO customerContract = customerContractService.getCustomerContract(id);
        return success(BeanUtils.toBean(customerContract, CustomerContractRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户签约信息表（零售+对公共用）分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-contract:query')")
    public CommonResult<PageResult<CustomerContractRespVO>> getCustomerContractPage(@Valid CustomerContractPageReqVO pageReqVO) {
        PageResult<CustomerContractDO> pageResult = customerContractService.getCustomerContractPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerContractRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户签约信息表（零售+对公共用） Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-contract:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerContractExcel(@Valid CustomerContractPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerContractDO> list = customerContractService.getCustomerContractPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户签约信息表（零售+对公共用）.xls", "数据", CustomerContractRespVO.class,
                        BeanUtils.toBean(list, CustomerContractRespVO.class));
    }

}