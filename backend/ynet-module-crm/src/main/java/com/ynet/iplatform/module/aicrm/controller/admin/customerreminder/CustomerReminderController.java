package com.ynet.iplatform.module.aicrm.controller.admin.customerreminder;

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

import com.ynet.iplatform.module.aicrm.controller.admin.customerreminder.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerreminder.CustomerReminderDO;
import com.ynet.iplatform.module.aicrm.service.customerreminder.CustomerReminderService;

@Tag(name = "管理后台 - 客户提醒信息")
@RestController
@RequestMapping("/aicrm/customer-reminder")
@Validated
public class CustomerReminderController {

    @Resource
    private CustomerReminderService customerReminderService;

    @PostMapping("/create")
    @Operation(summary = "创建客户提醒信息")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-reminder:create')")
    public CommonResult<Long> createCustomerReminder(@Valid @RequestBody CustomerReminderSaveReqVO createReqVO) {
        return success(customerReminderService.createCustomerReminder(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户提醒信息")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-reminder:update')")
    public CommonResult<Boolean> updateCustomerReminder(@Valid @RequestBody CustomerReminderSaveReqVO updateReqVO) {
        customerReminderService.updateCustomerReminder(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户提醒信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-reminder:delete')")
    public CommonResult<Boolean> deleteCustomerReminder(@RequestParam("id") Long id) {
        customerReminderService.deleteCustomerReminder(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户提醒信息")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-reminder:delete')")
    public CommonResult<Boolean> deleteCustomerReminderList(@RequestParam("ids") List<Long> ids) {
        customerReminderService.deleteCustomerReminderListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户提醒信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-reminder:query')")
    public CommonResult<CustomerReminderRespVO> getCustomerReminder(@RequestParam("id") Long id) {
        CustomerReminderDO customerReminder = customerReminderService.getCustomerReminder(id);
        return success(BeanUtils.toBean(customerReminder, CustomerReminderRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户提醒信息分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-reminder:query')")
    public CommonResult<PageResult<CustomerReminderRespVO>> getCustomerReminderPage(@Valid CustomerReminderPageReqVO pageReqVO) {
        PageResult<CustomerReminderDO> pageResult = customerReminderService.getCustomerReminderPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerReminderRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户提醒信息 Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-reminder:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerReminderExcel(@Valid CustomerReminderPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerReminderDO> list = customerReminderService.getCustomerReminderPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户提醒信息.xls", "数据", CustomerReminderRespVO.class,
                        BeanUtils.toBean(list, CustomerReminderRespVO.class));
    }

}