package com.ynet.iplatform.module.aicrm.controller.admin.retailcustomer;

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

import com.ynet.iplatform.module.aicrm.controller.admin.retailcustomer.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.retailcustomer.RetailCustomerDO;
import com.ynet.iplatform.module.aicrm.service.retailcustomer.RetailCustomerService;

@Tag(name = "管理后台 - CRM零售客户扩展表(零售客户特有基本信息)")
@RestController
@RequestMapping("/crm/retail-customer")
@Validated
public class RetailCustomerController {

    @Resource
    private RetailCustomerService retailCustomerService;

    @PostMapping("/create")
    @Operation(summary = "创建CRM零售客户扩展表(零售客户特有基本信息)")
    @PreAuthorize("@ss.hasPermission('crm:retail-customer:create')")
    public CommonResult<Long> createRetailCustomer(@Valid @RequestBody RetailCustomerSaveReqVO createReqVO) {
        return success(retailCustomerService.createRetailCustomer(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新CRM零售客户扩展表(零售客户特有基本信息)")
    @PreAuthorize("@ss.hasPermission('crm:retail-customer:update')")
    public CommonResult<Boolean> updateRetailCustomer(@Valid @RequestBody RetailCustomerSaveReqVO updateReqVO) {
        retailCustomerService.updateRetailCustomer(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除CRM零售客户扩展表(零售客户特有基本信息)")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('crm:retail-customer:delete')")
    public CommonResult<Boolean> deleteRetailCustomer(@RequestParam("id") Long id) {
        retailCustomerService.deleteRetailCustomer(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除CRM零售客户扩展表(零售客户特有基本信息)")
                @PreAuthorize("@ss.hasPermission('crm:retail-customer:delete')")
    public CommonResult<Boolean> deleteRetailCustomerList(@RequestParam("ids") List<Long> ids) {
        retailCustomerService.deleteRetailCustomerListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得CRM零售客户扩展表(零售客户特有基本信息)")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('crm:retail-customer:query')")
    public CommonResult<RetailCustomerRespVO> getRetailCustomer(@RequestParam("id") Long id) {
        RetailCustomerRespVO retailCustomer = retailCustomerService.getRetailCustomer(id);
        return success(retailCustomer);
    }

    @GetMapping("/page")
    @Operation(summary = "获得CRM零售客户扩展表(零售客户特有基本信息)分页")
    @PreAuthorize("@ss.hasPermission('crm:retail-customer:query')")
    public CommonResult<PageResult<RetailCustomerRespVO>> getRetailCustomerPage(@Valid RetailCustomerPageReqVO pageReqVO) {
        PageResult<RetailCustomerDO> pageResult = retailCustomerService.getRetailCustomerPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RetailCustomerRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出CRM零售客户扩展表(零售客户特有基本信息) Excel")
    @PreAuthorize("@ss.hasPermission('crm:retail-customer:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportRetailCustomerExcel(@Valid RetailCustomerPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RetailCustomerDO> list = retailCustomerService.getRetailCustomerPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "CRM零售客户扩展表(零售客户特有基本信息).xls", "数据", RetailCustomerRespVO.class,
                        BeanUtils.toBean(list, RetailCustomerRespVO.class));
    }

    @GetMapping("/get-overview")
    @Operation(summary = "获取零售客户概况信息")
    @Parameter(name = "customerId", description = "客户ID", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('crm:retail-customer:query')")
    public CommonResult<RetailCustomerOverviewRespVO> getRetailCustomerOverview(
            @RequestParam("customerId") Long customerId,
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate) {
        return success(retailCustomerService.getCustomerOverview(customerId, startDate, endDate));
    }

}