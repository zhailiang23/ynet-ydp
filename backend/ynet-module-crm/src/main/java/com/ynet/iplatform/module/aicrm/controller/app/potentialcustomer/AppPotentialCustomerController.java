package com.ynet.iplatform.module.aicrm.controller.app.potentialcustomer;

import com.ynet.iplatform.framework.common.pojo.CommonResult;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.module.aicrm.controller.app.potentialcustomer.vo.AppPotentialCustomerPageReqVO;
import com.ynet.iplatform.module.aicrm.controller.app.potentialcustomer.vo.AppPotentialCustomerRespVO;
import com.ynet.iplatform.module.aicrm.service.potentialcustomer.PotentialCustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.ynet.iplatform.framework.common.pojo.CommonResult.success;

@Tag(name = "移动端 - AI CRM 潜客管理")
@RestController
@RequestMapping("/aicrm/potential-customer")
@Validated
public class AppPotentialCustomerController {

    @Resource
    private PotentialCustomerService potentialCustomerService;

    @GetMapping("/page")
    @Operation(summary = "获得潜客分页")
    public CommonResult<PageResult<AppPotentialCustomerRespVO>> getPotentialCustomerPage(@Valid AppPotentialCustomerPageReqVO pageReqVO) {
        PageResult<AppPotentialCustomerRespVO> pageResult = potentialCustomerService.getAppPotentialCustomerPage(pageReqVO);
        return success(pageResult);
    }

    @GetMapping("/get")
    @Operation(summary = "获得潜客详情")
    @Parameter(name = "id", description = "潜客编号", required = true, example = "1")
    public CommonResult<AppPotentialCustomerRespVO> getPotentialCustomer(@RequestParam("id") Long id) {
        AppPotentialCustomerRespVO customer = potentialCustomerService.getAppPotentialCustomer(id);
        return success(customer);
    }

}
