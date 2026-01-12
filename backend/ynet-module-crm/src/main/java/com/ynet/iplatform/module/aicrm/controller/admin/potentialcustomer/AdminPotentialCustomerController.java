package com.ynet.iplatform.module.aicrm.controller.admin.potentialcustomer;

import com.ynet.iplatform.framework.common.pojo.CommonResult;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.excel.core.util.ExcelUtils;
import com.ynet.iplatform.module.aicrm.controller.admin.potentialcustomer.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.potentialcustomer.PotentialCustomerDO;
import com.ynet.iplatform.module.aicrm.service.potentialcustomer.PotentialCustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static com.ynet.iplatform.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - AI CRM 潜客管理")
@RestController
@RequestMapping("/aicrm/potential-customer")
@Validated
public class AdminPotentialCustomerController {

    @Resource
    private PotentialCustomerService potentialCustomerService;

    @PostMapping("/create")
    @Operation(summary = "创建潜客")
    @PreAuthorize("@ss.hasPermission('aicrm:potential-customer:create')")
    public CommonResult<Long> createPotentialCustomer(@Valid @RequestBody PotentialCustomerSaveReqVO createReqVO) {
        return success(potentialCustomerService.createPotentialCustomer(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新潜客")
    @PreAuthorize("@ss.hasPermission('aicrm:potential-customer:update')")
    public CommonResult<Boolean> updatePotentialCustomer(@Valid @RequestBody PotentialCustomerSaveReqVO updateReqVO) {
        potentialCustomerService.updatePotentialCustomer(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除潜客")
    @Parameter(name = "id", description = "潜客编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:potential-customer:delete')")
    public CommonResult<Boolean> deletePotentialCustomer(@RequestParam("id") Long id) {
        potentialCustomerService.deletePotentialCustomer(id);
        return success(true);
    }

    @DeleteMapping("/delete-batch")
    @Operation(summary = "批量删除潜客")
    @Parameter(name = "ids", description = "潜客编号列表", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:potential-customer:delete')")
    public CommonResult<Boolean> deletePotentialCustomerList(@RequestParam("ids") List<Long> ids) {
        potentialCustomerService.deletePotentialCustomerList(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得潜客详情")
    @Parameter(name = "id", description = "潜客编号", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('aicrm:potential-customer:query')")
    public CommonResult<PotentialCustomerDO> getPotentialCustomer(@RequestParam("id") Long id) {
        PotentialCustomerDO customer = potentialCustomerService.getPotentialCustomer(id);
        return success(customer);
    }

    @GetMapping("/page")
    @Operation(summary = "获得潜客分页")
    @PreAuthorize("@ss.hasPermission('aicrm:potential-customer:query')")
    public CommonResult<PageResult<PotentialCustomerDO>> getPotentialCustomerPage(@Valid PotentialCustomerPageReqVO pageReqVO) {
        PageResult<PotentialCustomerDO> pageResult = potentialCustomerService.getPotentialCustomerPage(pageReqVO);
        return success(pageResult);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出潜客 Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:potential-customer:export')")
    public void exportPotentialCustomerExcel(@Valid PotentialCustomerPageReqVO pageReqVO,
                                              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(10000); // 导出最多1万条
        List<PotentialCustomerDO> list = potentialCustomerService.getPotentialCustomerPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "潜客列表.xls", "数据", PotentialCustomerDO.class, list);
    }

}
