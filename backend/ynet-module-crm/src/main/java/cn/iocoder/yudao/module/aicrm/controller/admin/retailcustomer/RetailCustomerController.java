package cn.iocoder.yudao.module.aicrm.controller.admin.retailcustomer;

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

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.aicrm.controller.admin.retailcustomer.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.retailcustomer.RetailCustomerDO;
import cn.iocoder.yudao.module.aicrm.service.retailcustomer.RetailCustomerService;

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

}