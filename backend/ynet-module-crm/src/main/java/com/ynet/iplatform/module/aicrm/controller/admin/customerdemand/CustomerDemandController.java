package com.ynet.iplatform.module.aicrm.controller.admin.customerdemand;

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

import com.ynet.iplatform.module.aicrm.controller.admin.customerdemand.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerdemand.CustomerDemandDO;
import com.ynet.iplatform.module.aicrm.service.customerdemand.CustomerDemandService;

@Tag(name = "管理后台 - 客户需求信息")
@RestController
@RequestMapping("/aicrm/customer-demand")
@Validated
public class CustomerDemandController {

    @Resource
    private CustomerDemandService customerDemandService;

    @PostMapping("/create")
    @Operation(summary = "创建客户需求信息")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-demand:create')")
    public CommonResult<Long> createCustomerDemand(@Valid @RequestBody CustomerDemandSaveReqVO createReqVO) {
        return success(customerDemandService.createCustomerDemand(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户需求信息")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-demand:update')")
    public CommonResult<Boolean> updateCustomerDemand(@Valid @RequestBody CustomerDemandSaveReqVO updateReqVO) {
        customerDemandService.updateCustomerDemand(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户需求信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-demand:delete')")
    public CommonResult<Boolean> deleteCustomerDemand(@RequestParam("id") Long id) {
        customerDemandService.deleteCustomerDemand(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户需求信息")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-demand:delete')")
    public CommonResult<Boolean> deleteCustomerDemandList(@RequestParam("ids") List<Long> ids) {
        customerDemandService.deleteCustomerDemandListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户需求信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-demand:query')")
    public CommonResult<CustomerDemandRespVO> getCustomerDemand(@RequestParam("id") Long id) {
        CustomerDemandDO customerDemand = customerDemandService.getCustomerDemand(id);
        return success(BeanUtils.toBean(customerDemand, CustomerDemandRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户需求信息分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-demand:query')")
    public CommonResult<PageResult<CustomerDemandRespVO>> getCustomerDemandPage(@Valid CustomerDemandPageReqVO pageReqVO) {
        PageResult<CustomerDemandDO> pageResult = customerDemandService.getCustomerDemandPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerDemandRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户需求信息 Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-demand:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerDemandExcel(@Valid CustomerDemandPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerDemandDO> list = customerDemandService.getCustomerDemandPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户需求信息.xls", "数据", CustomerDemandRespVO.class,
                        BeanUtils.toBean(list, CustomerDemandRespVO.class));
    }

}