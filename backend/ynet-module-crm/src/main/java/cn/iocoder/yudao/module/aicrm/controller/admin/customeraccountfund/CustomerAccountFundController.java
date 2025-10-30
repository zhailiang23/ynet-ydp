package cn.iocoder.yudao.module.aicrm.controller.admin.customeraccountfund;

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

import cn.iocoder.yudao.module.aicrm.controller.admin.customeraccountfund.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customeraccountfund.CustomerAccountFundDO;
import cn.iocoder.yudao.module.aicrm.service.customeraccountfund.CustomerAccountFundService;

@Tag(name = "管理后台 - 客户基金账户信息表（零售+对公共用）")
@RestController
@RequestMapping("/aicrm/customer-account-fund")
@Validated
public class CustomerAccountFundController {

    @Resource
    private CustomerAccountFundService customerAccountFundService;

    @PostMapping("/create")
    @Operation(summary = "创建客户基金账户信息表（零售+对公共用）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-account-fund:create')")
    public CommonResult<Long> createCustomerAccountFund(@Valid @RequestBody CustomerAccountFundSaveReqVO createReqVO) {
        return success(customerAccountFundService.createCustomerAccountFund(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户基金账户信息表（零售+对公共用）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-account-fund:update')")
    public CommonResult<Boolean> updateCustomerAccountFund(@Valid @RequestBody CustomerAccountFundSaveReqVO updateReqVO) {
        customerAccountFundService.updateCustomerAccountFund(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户基金账户信息表（零售+对公共用）")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-account-fund:delete')")
    public CommonResult<Boolean> deleteCustomerAccountFund(@RequestParam("id") Long id) {
        customerAccountFundService.deleteCustomerAccountFund(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户基金账户信息表（零售+对公共用）")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-account-fund:delete')")
    public CommonResult<Boolean> deleteCustomerAccountFundList(@RequestParam("ids") List<Long> ids) {
        customerAccountFundService.deleteCustomerAccountFundListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户基金账户信息表（零售+对公共用）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-account-fund:query')")
    public CommonResult<CustomerAccountFundRespVO> getCustomerAccountFund(@RequestParam("id") Long id) {
        CustomerAccountFundDO customerAccountFund = customerAccountFundService.getCustomerAccountFund(id);
        return success(BeanUtils.toBean(customerAccountFund, CustomerAccountFundRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户基金账户信息表（零售+对公共用）分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-account-fund:query')")
    public CommonResult<PageResult<CustomerAccountFundRespVO>> getCustomerAccountFundPage(@Valid CustomerAccountFundPageReqVO pageReqVO) {
        PageResult<CustomerAccountFundDO> pageResult = customerAccountFundService.getCustomerAccountFundPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerAccountFundRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户基金账户信息表（零售+对公共用） Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-account-fund:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerAccountFundExcel(@Valid CustomerAccountFundPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerAccountFundDO> list = customerAccountFundService.getCustomerAccountFundPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户基金账户信息表（零售+对公共用）.xls", "数据", CustomerAccountFundRespVO.class,
                        BeanUtils.toBean(list, CustomerAccountFundRespVO.class));
    }

}