package com.ynet.iplatform.module.aicrm.controller.app.customer;

import com.ynet.iplatform.framework.common.pojo.CommonResult;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.module.aicrm.controller.app.customer.vo.AppCustomerPageReqVO;
import com.ynet.iplatform.module.aicrm.controller.app.customer.vo.AppCustomerRespVO;
import com.ynet.iplatform.module.aicrm.service.customer.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.ynet.iplatform.framework.common.pojo.CommonResult.success;

@Tag(name = "移动端 - CRM客户管理")
@RestController
@RequestMapping("/aicrm/customer")
@Validated
public class AppCustomerController {

    @Resource
    private CustomerService customerService;

    @GetMapping("/page")
    @Operation(summary = "获得客户分页")
    public CommonResult<PageResult<AppCustomerRespVO>> getCustomerPage(@Valid AppCustomerPageReqVO pageReqVO) {
        PageResult<AppCustomerRespVO> pageResult = customerService.getAppCustomerPage(pageReqVO);
        return success(pageResult);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户详情")
    @Parameter(name = "id", description = "客户编号", required = true, example = "1")
    public CommonResult<AppCustomerRespVO> getCustomer(@RequestParam("id") Long id) {
        AppCustomerRespVO customer = customerService.getAppCustomer(id);
        return success(customer);
    }

}
