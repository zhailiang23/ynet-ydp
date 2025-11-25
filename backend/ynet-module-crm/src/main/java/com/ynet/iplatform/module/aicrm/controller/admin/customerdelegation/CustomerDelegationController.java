package com.ynet.iplatform.module.aicrm.controller.admin.customerdelegation;
import com.ynet.iplatform.framework.security.core.util.SecurityFrameworkUtils;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.*;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.CommonResult;
import static com.ynet.iplatform.framework.common.pojo.CommonResult.success;

import com.ynet.iplatform.module.aicrm.controller.admin.customerdelegation.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerdelegation.CustomerDelegationDO;
import com.ynet.iplatform.module.aicrm.service.customerdelegation.CustomerDelegationService;

@Tag(name = "管理后台 - 客户托管记录")
@RestController
@RequestMapping("/aicrm/customer-delegation")
@Validated
public class CustomerDelegationController {

    @Resource
    private CustomerDelegationService customerDelegationService;

    @PostMapping("/create")
    @Operation(summary = "创建客户托管")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-delegation:create')")
    public CommonResult<Long> createDelegation(@Valid @RequestBody CustomerDelegationCreateReqVO createReqVO) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        return success(customerDelegationService.createDelegation(userId, createReqVO));
    }

    @PutMapping("/end")
    @Operation(summary = "结束客户托管")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-delegation:update')")
    public CommonResult<Boolean> endDelegation(@Valid @RequestBody CustomerDelegationEndReqVO endReqVO) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        customerDelegationService.endDelegation(userId, endReqVO);
        return success(true);
    }

    @DeleteMapping("/cancel")
    @Operation(summary = "取消客户托管")
    @Parameter(name = "id", description = "托管ID", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-delegation:delete')")
    public CommonResult<Boolean> cancelDelegation(@RequestParam("id") Long id) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        customerDelegationService.cancelDelegation(userId, id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户托管记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-delegation:query')")
    public CommonResult<CustomerDelegationDO> getDelegation(@RequestParam("id") Long id) {
        return success(customerDelegationService.getDelegation(id));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户托管记录分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-delegation:query')")
    public CommonResult<PageResult<CustomerDelegationDO>> getDelegationPage(@Valid CustomerDelegationPageReqVO pageReqVO) {
        return success(customerDelegationService.getDelegationPage(pageReqVO));
    }

}
