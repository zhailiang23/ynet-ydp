package com.ynet.iplatform.module.aicrm.controller.admin.customerproductholding;

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

import com.ynet.iplatform.module.aicrm.controller.admin.customerproductholding.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerproductholding.CustomerProductHoldingDO;
import com.ynet.iplatform.module.aicrm.service.customerproductholding.CustomerProductHoldingService;

@Tag(name = "管理后台 - 客户产品持有情况表（客户与产品的多对多关系）")
@RestController
@RequestMapping("/aicrm/customer-product-holding")
@Validated
public class CustomerProductHoldingController {

    @Resource
    private CustomerProductHoldingService customerProductHoldingService;

    @PostMapping("/create")
    @Operation(summary = "创建客户产品持有情况表（客户与产品的多对多关系）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-product-holding:create')")
    public CommonResult<Long> createCustomerProductHolding(@Valid @RequestBody CustomerProductHoldingSaveReqVO createReqVO) {
        return success(customerProductHoldingService.createCustomerProductHolding(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户产品持有情况表（客户与产品的多对多关系）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-product-holding:update')")
    public CommonResult<Boolean> updateCustomerProductHolding(@Valid @RequestBody CustomerProductHoldingSaveReqVO updateReqVO) {
        customerProductHoldingService.updateCustomerProductHolding(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户产品持有情况表（客户与产品的多对多关系）")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-product-holding:delete')")
    public CommonResult<Boolean> deleteCustomerProductHolding(@RequestParam("id") Long id) {
        customerProductHoldingService.deleteCustomerProductHolding(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户产品持有情况表（客户与产品的多对多关系）")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-product-holding:delete')")
    public CommonResult<Boolean> deleteCustomerProductHoldingList(@RequestParam("ids") List<Long> ids) {
        customerProductHoldingService.deleteCustomerProductHoldingListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户产品持有情况表（客户与产品的多对多关系）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-product-holding:query')")
    public CommonResult<CustomerProductHoldingRespVO> getCustomerProductHolding(@RequestParam("id") Long id) {
        CustomerProductHoldingDO customerProductHolding = customerProductHoldingService.getCustomerProductHolding(id);
        return success(BeanUtils.toBean(customerProductHolding, CustomerProductHoldingRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户产品持有情况表（客户与产品的多对多关系）分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-product-holding:query')")
    public CommonResult<PageResult<CustomerProductHoldingRespVO>> getCustomerProductHoldingPage(@Valid CustomerProductHoldingPageReqVO pageReqVO) {
        PageResult<CustomerProductHoldingDO> pageResult = customerProductHoldingService.getCustomerProductHoldingPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerProductHoldingRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户产品持有情况表（客户与产品的多对多关系） Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-product-holding:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerProductHoldingExcel(@Valid CustomerProductHoldingPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerProductHoldingDO> list = customerProductHoldingService.getCustomerProductHoldingPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户产品持有情况表（客户与产品的多对多关系）.xls", "数据", CustomerProductHoldingRespVO.class,
                        BeanUtils.toBean(list, CustomerProductHoldingRespVO.class));
    }

}