package com.ynet.iplatform.module.aicrm.controller.admin.customerpreference;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.*;

import com.ynet.iplatform.framework.common.pojo.CommonResult;
import static com.ynet.iplatform.framework.common.pojo.CommonResult.success;

import com.ynet.iplatform.module.aicrm.controller.admin.customerpreference.vo.*;
import com.ynet.iplatform.module.aicrm.service.customerpreference.CustomerPreferenceService;

@Tag(name = "管理后台 - 客户偏好")
@RestController
@RequestMapping("/aicrm/customer-preference")
@Validated
public class CustomerPreferenceController {

    @Resource
    private CustomerPreferenceService customerPreferenceService;

    @PostMapping("/save")
    @Operation(summary = "保存客户偏好（创建或更新）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-preference:save')")
    public CommonResult<Long> saveCustomerPreference(@Valid @RequestBody CustomerPreferenceSaveReqVO saveReqVO) {
        return success(customerPreferenceService.saveCustomerPreference(saveReqVO));
    }

    @GetMapping("/get-by-customer-id")
    @Operation(summary = "根据客户ID获取客户偏好")
    @Parameter(name = "customerId", description = "客户ID", required = true, example = "1001")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-preference:query')")
    public CommonResult<CustomerPreferenceRespVO> getCustomerPreferenceByCustomerId(@RequestParam("customerId") Long customerId) {
        CustomerPreferenceRespVO preference = customerPreferenceService.getCustomerPreferenceByCustomerId(customerId);
        return success(preference);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户偏好")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-preference:delete')")
    public CommonResult<Boolean> deleteCustomerPreference(@RequestParam("id") Long id) {
        customerPreferenceService.deleteCustomerPreference(id);
        return success(true);
    }

}
