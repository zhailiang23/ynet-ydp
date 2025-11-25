package com.ynet.iplatform.module.aicrm.controller.admin.customeraccountcreditcard;

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

import com.ynet.iplatform.module.aicrm.controller.admin.customeraccountcreditcard.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customeraccountcreditcard.CustomerAccountCreditcardDO;
import com.ynet.iplatform.module.aicrm.service.customeraccountcreditcard.CustomerAccountCreditcardService;

@Tag(name = "管理后台 - 客户信用卡账户信息表（仅限零售客户）")
@RestController
@RequestMapping("/aicrm/customer-account-creditcard")
@Validated
public class CustomerAccountCreditcardController {

    @Resource
    private CustomerAccountCreditcardService customerAccountCreditcardService;

    @PostMapping("/create")
    @Operation(summary = "创建客户信用卡账户信息表（仅限零售客户）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-account-creditcard:create')")
    public CommonResult<Long> createCustomerAccountCreditcard(@Valid @RequestBody CustomerAccountCreditcardSaveReqVO createReqVO) {
        return success(customerAccountCreditcardService.createCustomerAccountCreditcard(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户信用卡账户信息表（仅限零售客户）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-account-creditcard:update')")
    public CommonResult<Boolean> updateCustomerAccountCreditcard(@Valid @RequestBody CustomerAccountCreditcardSaveReqVO updateReqVO) {
        customerAccountCreditcardService.updateCustomerAccountCreditcard(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户信用卡账户信息表（仅限零售客户）")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-account-creditcard:delete')")
    public CommonResult<Boolean> deleteCustomerAccountCreditcard(@RequestParam("id") Long id) {
        customerAccountCreditcardService.deleteCustomerAccountCreditcard(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户信用卡账户信息表（仅限零售客户）")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-account-creditcard:delete')")
    public CommonResult<Boolean> deleteCustomerAccountCreditcardList(@RequestParam("ids") List<Long> ids) {
        customerAccountCreditcardService.deleteCustomerAccountCreditcardListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户信用卡账户信息表（仅限零售客户）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-account-creditcard:query')")
    public CommonResult<CustomerAccountCreditcardRespVO> getCustomerAccountCreditcard(@RequestParam("id") Long id) {
        CustomerAccountCreditcardDO customerAccountCreditcard = customerAccountCreditcardService.getCustomerAccountCreditcard(id);
        return success(BeanUtils.toBean(customerAccountCreditcard, CustomerAccountCreditcardRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户信用卡账户信息表（仅限零售客户）分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-account-creditcard:query')")
    public CommonResult<PageResult<CustomerAccountCreditcardRespVO>> getCustomerAccountCreditcardPage(@Valid CustomerAccountCreditcardPageReqVO pageReqVO) {
        PageResult<CustomerAccountCreditcardDO> pageResult = customerAccountCreditcardService.getCustomerAccountCreditcardPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerAccountCreditcardRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户信用卡账户信息表（仅限零售客户） Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-account-creditcard:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerAccountCreditcardExcel(@Valid CustomerAccountCreditcardPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerAccountCreditcardDO> list = customerAccountCreditcardService.getCustomerAccountCreditcardPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户信用卡账户信息表（仅限零售客户）.xls", "数据", CustomerAccountCreditcardRespVO.class,
                        BeanUtils.toBean(list, CustomerAccountCreditcardRespVO.class));
    }

}