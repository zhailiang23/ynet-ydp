package com.ynet.iplatform.module.aicrm.controller.admin.customergridhistory;

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

import com.ynet.iplatform.module.aicrm.controller.admin.customergridhistory.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customergridhistory.CustomerGridHistoryDO;
import com.ynet.iplatform.module.aicrm.service.customergridhistory.CustomerGridHistoryService;

@Tag(name = "管理后台 - 客户归属网格调整历史表（记录调整当时的网格信息快照）")
@RestController
@RequestMapping("/aicrm/customer-grid-history")
@Validated
public class CustomerGridHistoryController {

    @Resource
    private CustomerGridHistoryService customerGridHistoryService;

    @PostMapping("/create")
    @Operation(summary = "创建客户归属网格调整历史表（记录调整当时的网格信息快照）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-grid-history:create')")
    public CommonResult<Long> createCustomerGridHistory(@Valid @RequestBody CustomerGridHistorySaveReqVO createReqVO) {
        return success(customerGridHistoryService.createCustomerGridHistory(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户归属网格调整历史表（记录调整当时的网格信息快照）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-grid-history:update')")
    public CommonResult<Boolean> updateCustomerGridHistory(@Valid @RequestBody CustomerGridHistorySaveReqVO updateReqVO) {
        customerGridHistoryService.updateCustomerGridHistory(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户归属网格调整历史表（记录调整当时的网格信息快照）")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-grid-history:delete')")
    public CommonResult<Boolean> deleteCustomerGridHistory(@RequestParam("id") Long id) {
        customerGridHistoryService.deleteCustomerGridHistory(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户归属网格调整历史表（记录调整当时的网格信息快照）")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-grid-history:delete')")
    public CommonResult<Boolean> deleteCustomerGridHistoryList(@RequestParam("ids") List<Long> ids) {
        customerGridHistoryService.deleteCustomerGridHistoryListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户归属网格调整历史表（记录调整当时的网格信息快照）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-grid-history:query')")
    public CommonResult<CustomerGridHistoryRespVO> getCustomerGridHistory(@RequestParam("id") Long id) {
        CustomerGridHistoryDO customerGridHistory = customerGridHistoryService.getCustomerGridHistory(id);
        return success(BeanUtils.toBean(customerGridHistory, CustomerGridHistoryRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户归属网格调整历史表（记录调整当时的网格信息快照）分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-grid-history:query')")
    public CommonResult<PageResult<CustomerGridHistoryRespVO>> getCustomerGridHistoryPage(@Valid CustomerGridHistoryPageReqVO pageReqVO) {
        // 使用联表查询，避免 N+1 查询
        PageResult<CustomerGridHistoryRespVO> pageResult = customerGridHistoryService.getCustomerGridHistoryPageWithJoin(pageReqVO);
        return success(pageResult);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户归属网格调整历史表（记录调整当时的网格信息快照） Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-grid-history:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerGridHistoryExcel(@Valid CustomerGridHistoryPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerGridHistoryDO> list = customerGridHistoryService.getCustomerGridHistoryPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户归属网格调整历史表（记录调整当时的网格信息快照）.xls", "数据", CustomerGridHistoryRespVO.class,
                        BeanUtils.toBean(list, CustomerGridHistoryRespVO.class));
    }

}