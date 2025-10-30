package cn.iocoder.yudao.module.aicrm.controller.admin.customeraccountdeposit;

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

import cn.iocoder.yudao.module.aicrm.controller.admin.customeraccountdeposit.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customeraccountdeposit.CustomerAccountDepositDO;
import cn.iocoder.yudao.module.aicrm.service.customeraccountdeposit.CustomerAccountDepositService;

@Tag(name = "管理后台 - 客户存款账户信息表（零售+对公共用）")
@RestController
@RequestMapping("/aicrm/customer-account-deposit")
@Validated
public class CustomerAccountDepositController {

    @Resource
    private CustomerAccountDepositService customerAccountDepositService;

    @PostMapping("/create")
    @Operation(summary = "创建客户存款账户信息表（零售+对公共用）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-account-deposit:create')")
    public CommonResult<Long> createCustomerAccountDeposit(@Valid @RequestBody CustomerAccountDepositSaveReqVO createReqVO) {
        return success(customerAccountDepositService.createCustomerAccountDeposit(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户存款账户信息表（零售+对公共用）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-account-deposit:update')")
    public CommonResult<Boolean> updateCustomerAccountDeposit(@Valid @RequestBody CustomerAccountDepositSaveReqVO updateReqVO) {
        customerAccountDepositService.updateCustomerAccountDeposit(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户存款账户信息表（零售+对公共用）")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-account-deposit:delete')")
    public CommonResult<Boolean> deleteCustomerAccountDeposit(@RequestParam("id") Long id) {
        customerAccountDepositService.deleteCustomerAccountDeposit(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户存款账户信息表（零售+对公共用）")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-account-deposit:delete')")
    public CommonResult<Boolean> deleteCustomerAccountDepositList(@RequestParam("ids") List<Long> ids) {
        customerAccountDepositService.deleteCustomerAccountDepositListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户存款账户信息表（零售+对公共用）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-account-deposit:query')")
    public CommonResult<CustomerAccountDepositRespVO> getCustomerAccountDeposit(@RequestParam("id") Long id) {
        CustomerAccountDepositDO customerAccountDeposit = customerAccountDepositService.getCustomerAccountDeposit(id);
        return success(BeanUtils.toBean(customerAccountDeposit, CustomerAccountDepositRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户存款账户信息表（零售+对公共用）分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-account-deposit:query')")
    public CommonResult<PageResult<CustomerAccountDepositRespVO>> getCustomerAccountDepositPage(@Valid CustomerAccountDepositPageReqVO pageReqVO) {
        PageResult<CustomerAccountDepositDO> pageResult = customerAccountDepositService.getCustomerAccountDepositPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerAccountDepositRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户存款账户信息表（零售+对公共用） Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-account-deposit:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerAccountDepositExcel(@Valid CustomerAccountDepositPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerAccountDepositDO> list = customerAccountDepositService.getCustomerAccountDepositPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户存款账户信息表（零售+对公共用）.xls", "数据", CustomerAccountDepositRespVO.class,
                        BeanUtils.toBean(list, CustomerAccountDepositRespVO.class));
    }

}