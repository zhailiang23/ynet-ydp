package com.ynet.iplatform.module.aicrm.controller.admin.customergrouphistory;

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

import com.ynet.iplatform.module.aicrm.controller.admin.customergrouphistory.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customergrouphistory.CustomerGroupHistoryDO;
import com.ynet.iplatform.module.aicrm.service.customergrouphistory.CustomerGroupHistoryService;

@Tag(name = "管理后台 - 客户归属客群调整历史表（记录调整当时的客群信息快照）")
@RestController
@RequestMapping("/aicrm/customer-group-history")
@Validated
public class CustomerGroupHistoryController {

    @Resource
    private CustomerGroupHistoryService customerGroupHistoryService;

    @PostMapping("/create")
    @Operation(summary = "创建客户归属客群调整历史表（记录调整当时的客群信息快照）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-group-history:create')")
    public CommonResult<Long> createCustomerGroupHistory(@Valid @RequestBody CustomerGroupHistorySaveReqVO createReqVO) {
        return success(customerGroupHistoryService.createCustomerGroupHistory(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户归属客群调整历史表（记录调整当时的客群信息快照）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-group-history:update')")
    public CommonResult<Boolean> updateCustomerGroupHistory(@Valid @RequestBody CustomerGroupHistorySaveReqVO updateReqVO) {
        customerGroupHistoryService.updateCustomerGroupHistory(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户归属客群调整历史表（记录调整当时的客群信息快照）")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-group-history:delete')")
    public CommonResult<Boolean> deleteCustomerGroupHistory(@RequestParam("id") Long id) {
        customerGroupHistoryService.deleteCustomerGroupHistory(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户归属客群调整历史表（记录调整当时的客群信息快照）")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-group-history:delete')")
    public CommonResult<Boolean> deleteCustomerGroupHistoryList(@RequestParam("ids") List<Long> ids) {
        customerGroupHistoryService.deleteCustomerGroupHistoryListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户归属客群调整历史表（记录调整当时的客群信息快照）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-group-history:query')")
    public CommonResult<CustomerGroupHistoryRespVO> getCustomerGroupHistory(@RequestParam("id") Long id) {
        CustomerGroupHistoryDO customerGroupHistory = customerGroupHistoryService.getCustomerGroupHistory(id);
        return success(BeanUtils.toBean(customerGroupHistory, CustomerGroupHistoryRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户归属客群调整历史表（记录调整当时的客群信息快照）分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-group-history:query')")
    public CommonResult<PageResult<CustomerGroupHistoryRespVO>> getCustomerGroupHistoryPage(@Valid CustomerGroupHistoryPageReqVO pageReqVO) {
        PageResult<CustomerGroupHistoryDO> pageResult = customerGroupHistoryService.getCustomerGroupHistoryPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerGroupHistoryRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户归属客群调整历史表（记录调整当时的客群信息快照） Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-group-history:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerGroupHistoryExcel(@Valid CustomerGroupHistoryPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerGroupHistoryDO> list = customerGroupHistoryService.getCustomerGroupHistoryPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户归属客群调整历史表（记录调整当时的客群信息快照）.xls", "数据", CustomerGroupHistoryRespVO.class,
                        BeanUtils.toBean(list, CustomerGroupHistoryRespVO.class));
    }

}