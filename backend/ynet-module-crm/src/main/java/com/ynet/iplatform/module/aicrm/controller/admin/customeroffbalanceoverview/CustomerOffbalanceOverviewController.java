package com.ynet.iplatform.module.aicrm.controller.admin.customeroffbalanceoverview;

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

import com.ynet.iplatform.module.aicrm.controller.admin.customeroffbalanceoverview.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customeroffbalanceoverview.CustomerOffbalanceOverviewDO;
import com.ynet.iplatform.module.aicrm.service.customeroffbalanceoverview.CustomerOffbalanceOverviewService;

@Tag(name = "管理后台 - 客户表外业务概览")
@RestController
@RequestMapping("/aicrm/customer-offbalance-overview")
@Validated
public class CustomerOffbalanceOverviewController {

    @Resource
    private CustomerOffbalanceOverviewService customerOffbalanceOverviewService;

    @PostMapping("/create")
    @Operation(summary = "创建客户表外业务概览")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-offbalance-overview:create')")
    public CommonResult<Long> createCustomerOffbalanceOverview(@Valid @RequestBody CustomerOffbalanceOverviewSaveReqVO createReqVO) {
        return success(customerOffbalanceOverviewService.createCustomerOffbalanceOverview(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户表外业务概览")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-offbalance-overview:update')")
    public CommonResult<Boolean> updateCustomerOffbalanceOverview(@Valid @RequestBody CustomerOffbalanceOverviewSaveReqVO updateReqVO) {
        customerOffbalanceOverviewService.updateCustomerOffbalanceOverview(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户表外业务概览")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-offbalance-overview:delete')")
    public CommonResult<Boolean> deleteCustomerOffbalanceOverview(@RequestParam("id") Long id) {
        customerOffbalanceOverviewService.deleteCustomerOffbalanceOverview(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户表外业务概览")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-offbalance-overview:delete')")
    public CommonResult<Boolean> deleteCustomerOffbalanceOverviewList(@RequestParam("ids") List<Long> ids) {
        customerOffbalanceOverviewService.deleteCustomerOffbalanceOverviewListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户表外业务概览")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-offbalance-overview:query')")
    public CommonResult<CustomerOffbalanceOverviewRespVO> getCustomerOffbalanceOverview(@RequestParam("id") Long id) {
        CustomerOffbalanceOverviewDO customerOffbalanceOverview = customerOffbalanceOverviewService.getCustomerOffbalanceOverview(id);
        return success(BeanUtils.toBean(customerOffbalanceOverview, CustomerOffbalanceOverviewRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户表外业务概览分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-offbalance-overview:query')")
    public CommonResult<PageResult<CustomerOffbalanceOverviewRespVO>> getCustomerOffbalanceOverviewPage(@Valid CustomerOffbalanceOverviewPageReqVO pageReqVO) {
        PageResult<CustomerOffbalanceOverviewDO> pageResult = customerOffbalanceOverviewService.getCustomerOffbalanceOverviewPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerOffbalanceOverviewRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户表外业务概览 Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-offbalance-overview:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerOffbalanceOverviewExcel(@Valid CustomerOffbalanceOverviewPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerOffbalanceOverviewDO> list = customerOffbalanceOverviewService.getCustomerOffbalanceOverviewPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户表外业务概览.xls", "数据", CustomerOffbalanceOverviewRespVO.class,
                        BeanUtils.toBean(list, CustomerOffbalanceOverviewRespVO.class));
    }

}