package com.ynet.iplatform.module.aicrm.controller.admin.customertransactionmock;

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

import com.ynet.iplatform.module.aicrm.controller.admin.customertransactionmock.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customertransactionmock.CustomerTransactionMockDO;
import com.ynet.iplatform.module.aicrm.service.customertransactionmock.CustomerTransactionMockService;

@Tag(name = "管理后台 - 客户交易明细信息表（Mock数据）")
@RestController
@RequestMapping("/aicrm/customer-transaction-mock")
@Validated
public class CustomerTransactionMockController {

    @Resource
    private CustomerTransactionMockService customerTransactionMockService;

    @PostMapping("/create")
    @Operation(summary = "创建客户交易明细信息表（Mock数据）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-transaction-mock:create')")
    public CommonResult<Long> createCustomerTransactionMock(@Valid @RequestBody CustomerTransactionMockSaveReqVO createReqVO) {
        return success(customerTransactionMockService.createCustomerTransactionMock(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户交易明细信息表（Mock数据）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-transaction-mock:update')")
    public CommonResult<Boolean> updateCustomerTransactionMock(@Valid @RequestBody CustomerTransactionMockSaveReqVO updateReqVO) {
        customerTransactionMockService.updateCustomerTransactionMock(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户交易明细信息表（Mock数据）")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-transaction-mock:delete')")
    public CommonResult<Boolean> deleteCustomerTransactionMock(@RequestParam("id") Long id) {
        customerTransactionMockService.deleteCustomerTransactionMock(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户交易明细信息表（Mock数据）")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-transaction-mock:delete')")
    public CommonResult<Boolean> deleteCustomerTransactionMockList(@RequestParam("ids") List<Long> ids) {
        customerTransactionMockService.deleteCustomerTransactionMockListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户交易明细信息表（Mock数据）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-transaction-mock:query')")
    public CommonResult<CustomerTransactionMockRespVO> getCustomerTransactionMock(@RequestParam("id") Long id) {
        CustomerTransactionMockDO customerTransactionMock = customerTransactionMockService.getCustomerTransactionMock(id);
        return success(BeanUtils.toBean(customerTransactionMock, CustomerTransactionMockRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户交易明细信息表（Mock数据）分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-transaction-mock:query')")
    public CommonResult<PageResult<CustomerTransactionMockRespVO>> getCustomerTransactionMockPage(@Valid CustomerTransactionMockPageReqVO pageReqVO) {
        PageResult<CustomerTransactionMockDO> pageResult = customerTransactionMockService.getCustomerTransactionMockPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerTransactionMockRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户交易明细信息表（Mock数据） Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-transaction-mock:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerTransactionMockExcel(@Valid CustomerTransactionMockPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerTransactionMockDO> list = customerTransactionMockService.getCustomerTransactionMockPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户交易明细信息表（Mock数据）.xls", "数据", CustomerTransactionMockRespVO.class,
                        BeanUtils.toBean(list, CustomerTransactionMockRespVO.class));
    }

}