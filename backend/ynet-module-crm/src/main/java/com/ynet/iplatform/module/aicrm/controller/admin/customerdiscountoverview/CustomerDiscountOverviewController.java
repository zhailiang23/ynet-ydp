package com.ynet.iplatform.module.aicrm.controller.admin.customerdiscountoverview;

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

import com.ynet.iplatform.module.aicrm.controller.admin.customerdiscountoverview.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerdiscountoverview.CustomerDiscountOverviewDO;
import com.ynet.iplatform.module.aicrm.service.customerdiscountoverview.CustomerDiscountOverviewService;

@Tag(name = "管理后台 - 客户贴现业务概览")
@RestController
@RequestMapping("/aicrm/customer-discount-overview")
@Validated
public class CustomerDiscountOverviewController {

    @Resource
    private CustomerDiscountOverviewService customerDiscountOverviewService;

    @PostMapping("/create")
    @Operation(summary = "创建客户贴现业务概览")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-discount-overview:create')")
    public CommonResult<Long> createCustomerDiscountOverview(@Valid @RequestBody CustomerDiscountOverviewSaveReqVO createReqVO) {
        return success(customerDiscountOverviewService.createCustomerDiscountOverview(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户贴现业务概览")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-discount-overview:update')")
    public CommonResult<Boolean> updateCustomerDiscountOverview(@Valid @RequestBody CustomerDiscountOverviewSaveReqVO updateReqVO) {
        customerDiscountOverviewService.updateCustomerDiscountOverview(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户贴现业务概览")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-discount-overview:delete')")
    public CommonResult<Boolean> deleteCustomerDiscountOverview(@RequestParam("id") Long id) {
        customerDiscountOverviewService.deleteCustomerDiscountOverview(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户贴现业务概览")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-discount-overview:delete')")
    public CommonResult<Boolean> deleteCustomerDiscountOverviewList(@RequestParam("ids") List<Long> ids) {
        customerDiscountOverviewService.deleteCustomerDiscountOverviewListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户贴现业务概览")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-discount-overview:query')")
    public CommonResult<CustomerDiscountOverviewRespVO> getCustomerDiscountOverview(@RequestParam("id") Long id) {
        CustomerDiscountOverviewDO customerDiscountOverview = customerDiscountOverviewService.getCustomerDiscountOverview(id);
        return success(BeanUtils.toBean(customerDiscountOverview, CustomerDiscountOverviewRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户贴现业务概览分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-discount-overview:query')")
    public CommonResult<PageResult<CustomerDiscountOverviewRespVO>> getCustomerDiscountOverviewPage(@Valid CustomerDiscountOverviewPageReqVO pageReqVO) {
        PageResult<CustomerDiscountOverviewDO> pageResult = customerDiscountOverviewService.getCustomerDiscountOverviewPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerDiscountOverviewRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户贴现业务概览 Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-discount-overview:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerDiscountOverviewExcel(@Valid CustomerDiscountOverviewPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerDiscountOverviewDO> list = customerDiscountOverviewService.getCustomerDiscountOverviewPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户贴现业务概览.xls", "数据", CustomerDiscountOverviewRespVO.class,
                        BeanUtils.toBean(list, CustomerDiscountOverviewRespVO.class));
    }

}