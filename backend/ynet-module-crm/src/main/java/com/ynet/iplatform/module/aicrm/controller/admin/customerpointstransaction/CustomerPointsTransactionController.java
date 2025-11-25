package com.ynet.iplatform.module.aicrm.controller.admin.customerpointstransaction;

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

import com.ynet.iplatform.module.aicrm.controller.admin.customerpointstransaction.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerpointstransaction.CustomerPointsTransactionDO;
import com.ynet.iplatform.module.aicrm.service.customerpointstransaction.CustomerPointsTransactionService;

@Tag(name = "管理后台 - 客户积分消费明细表（积分交易流水表）")
@RestController
@RequestMapping("/aicrm/customer-points-transaction")
@Validated
public class CustomerPointsTransactionController {

    @Resource
    private CustomerPointsTransactionService customerPointsTransactionService;

    @PostMapping("/create")
    @Operation(summary = "创建客户积分消费明细表（积分交易流水表）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-points-transaction:create')")
    public CommonResult<Long> createCustomerPointsTransaction(@Valid @RequestBody CustomerPointsTransactionSaveReqVO createReqVO) {
        return success(customerPointsTransactionService.createCustomerPointsTransaction(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户积分消费明细表（积分交易流水表）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-points-transaction:update')")
    public CommonResult<Boolean> updateCustomerPointsTransaction(@Valid @RequestBody CustomerPointsTransactionSaveReqVO updateReqVO) {
        customerPointsTransactionService.updateCustomerPointsTransaction(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户积分消费明细表（积分交易流水表）")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-points-transaction:delete')")
    public CommonResult<Boolean> deleteCustomerPointsTransaction(@RequestParam("id") Long id) {
        customerPointsTransactionService.deleteCustomerPointsTransaction(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户积分消费明细表（积分交易流水表）")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-points-transaction:delete')")
    public CommonResult<Boolean> deleteCustomerPointsTransactionList(@RequestParam("ids") List<Long> ids) {
        customerPointsTransactionService.deleteCustomerPointsTransactionListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户积分消费明细表（积分交易流水表）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-points-transaction:query')")
    public CommonResult<CustomerPointsTransactionRespVO> getCustomerPointsTransaction(@RequestParam("id") Long id) {
        CustomerPointsTransactionDO customerPointsTransaction = customerPointsTransactionService.getCustomerPointsTransaction(id);
        return success(BeanUtils.toBean(customerPointsTransaction, CustomerPointsTransactionRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户积分消费明细表（积分交易流水表）分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-points-transaction:query')")
    public CommonResult<PageResult<CustomerPointsTransactionRespVO>> getCustomerPointsTransactionPage(@Valid CustomerPointsTransactionPageReqVO pageReqVO) {
        PageResult<CustomerPointsTransactionDO> pageResult = customerPointsTransactionService.getCustomerPointsTransactionPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerPointsTransactionRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户积分消费明细表（积分交易流水表） Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-points-transaction:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerPointsTransactionExcel(@Valid CustomerPointsTransactionPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerPointsTransactionDO> list = customerPointsTransactionService.getCustomerPointsTransactionPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户积分消费明细表（积分交易流水表）.xls", "数据", CustomerPointsTransactionRespVO.class,
                        BeanUtils.toBean(list, CustomerPointsTransactionRespVO.class));
    }

}