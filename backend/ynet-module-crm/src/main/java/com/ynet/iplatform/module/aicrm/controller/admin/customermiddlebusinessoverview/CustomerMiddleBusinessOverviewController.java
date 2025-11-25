package com.ynet.iplatform.module.aicrm.controller.admin.customermiddlebusinessoverview;

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

import com.ynet.iplatform.module.aicrm.controller.admin.customermiddlebusinessoverview.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customermiddlebusinessoverview.CustomerMiddleBusinessOverviewDO;
import com.ynet.iplatform.module.aicrm.service.customermiddlebusinessoverview.CustomerMiddleBusinessOverviewService;

@Tag(name = "管理后台 - 客户中间业务概览")
@RestController
@RequestMapping("/aicrm/customer-middle-business-overview")
@Validated
public class CustomerMiddleBusinessOverviewController {

    @Resource
    private CustomerMiddleBusinessOverviewService customerMiddleBusinessOverviewService;

    @PostMapping("/create")
    @Operation(summary = "创建客户中间业务概览")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-middle-business-overview:create')")
    public CommonResult<Long> createCustomerMiddleBusinessOverview(@Valid @RequestBody CustomerMiddleBusinessOverviewSaveReqVO createReqVO) {
        return success(customerMiddleBusinessOverviewService.createCustomerMiddleBusinessOverview(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户中间业务概览")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-middle-business-overview:update')")
    public CommonResult<Boolean> updateCustomerMiddleBusinessOverview(@Valid @RequestBody CustomerMiddleBusinessOverviewSaveReqVO updateReqVO) {
        customerMiddleBusinessOverviewService.updateCustomerMiddleBusinessOverview(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户中间业务概览")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-middle-business-overview:delete')")
    public CommonResult<Boolean> deleteCustomerMiddleBusinessOverview(@RequestParam("id") Long id) {
        customerMiddleBusinessOverviewService.deleteCustomerMiddleBusinessOverview(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户中间业务概览")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-middle-business-overview:delete')")
    public CommonResult<Boolean> deleteCustomerMiddleBusinessOverviewList(@RequestParam("ids") List<Long> ids) {
        customerMiddleBusinessOverviewService.deleteCustomerMiddleBusinessOverviewListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户中间业务概览")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-middle-business-overview:query')")
    public CommonResult<CustomerMiddleBusinessOverviewRespVO> getCustomerMiddleBusinessOverview(@RequestParam("id") Long id) {
        CustomerMiddleBusinessOverviewDO customerMiddleBusinessOverview = customerMiddleBusinessOverviewService.getCustomerMiddleBusinessOverview(id);
        return success(BeanUtils.toBean(customerMiddleBusinessOverview, CustomerMiddleBusinessOverviewRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户中间业务概览分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-middle-business-overview:query')")
    public CommonResult<PageResult<CustomerMiddleBusinessOverviewRespVO>> getCustomerMiddleBusinessOverviewPage(@Valid CustomerMiddleBusinessOverviewPageReqVO pageReqVO) {
        PageResult<CustomerMiddleBusinessOverviewDO> pageResult = customerMiddleBusinessOverviewService.getCustomerMiddleBusinessOverviewPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerMiddleBusinessOverviewRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户中间业务概览 Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-middle-business-overview:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerMiddleBusinessOverviewExcel(@Valid CustomerMiddleBusinessOverviewPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerMiddleBusinessOverviewDO> list = customerMiddleBusinessOverviewService.getCustomerMiddleBusinessOverviewPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户中间业务概览.xls", "数据", CustomerMiddleBusinessOverviewRespVO.class,
                        BeanUtils.toBean(list, CustomerMiddleBusinessOverviewRespVO.class));
    }

}