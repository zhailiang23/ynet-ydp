package com.ynet.iplatform.module.aicrm.controller.admin.customerreturn;
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

import com.ynet.iplatform.module.aicrm.controller.admin.customerreturn.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerreturn.CustomerReturnApplicationDO;
import com.ynet.iplatform.module.aicrm.service.customerreturn.CustomerReturnService;

@Tag(name = "管理后台 - 客户退回申请")
@RestController
@RequestMapping("/aicrm/customer-return-application")
@Validated
public class CustomerReturnApplicationController {

    @Resource
    private CustomerReturnService customerReturnService;

    @PostMapping("/apply")
    @Operation(summary = "提交客户退回申请")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-return:create')")
    public CommonResult<Long> applyForReturn(@Valid @RequestBody CustomerReturnApplicationApplyReqVO applyReqVO) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        return success(customerReturnService.applyForReturn(userId, applyReqVO));
    }

    @DeleteMapping("/cancel")
    @Operation(summary = "取消客户退回申请")
    @Parameter(name = "id", description = "申请ID", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-return:delete')")
    public CommonResult<Boolean> cancelReturnApplication(@RequestParam("id") Long id) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        customerReturnService.cancelReturnApplication(userId, id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户退回申请")
    @Parameter(name = "id", description = "编号", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-return:query')")
    public CommonResult<CustomerReturnApplicationDO> getReturnApplication(@RequestParam("id") Long id) {
        return success(customerReturnService.getReturnApplication(id));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户退回申请分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-return:query')")
    public CommonResult<PageResult<CustomerReturnApplicationDO>> getReturnApplicationPage(@Valid CustomerReturnApplicationPageReqVO pageReqVO) {
        return success(customerReturnService.getReturnApplicationPage(pageReqVO));
    }

}
