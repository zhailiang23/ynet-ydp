package com.ynet.iplatform.module.aicrm.controller.app.customer;

import com.ynet.iplatform.framework.common.pojo.CommonResult;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;
import com.ynet.iplatform.module.aicrm.controller.app.customer.vo.AppCustomerPageReqVO;
import com.ynet.iplatform.module.aicrm.controller.app.customer.vo.AppCustomerRespVO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customer.CustomerDO;
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

    @GetMapping("/search")
    @Operation(summary = "客户识别查询", description = "通过手机号或证件号查询客户信息，用于到店客户识别场景")
    @Parameter(name = "mobile", description = "手机号", example = "13800138000")
    @Parameter(name = "idCardNo", description = "证件号码", example = "110101199001011234")
    public CommonResult<AppCustomerRespVO> searchCustomer(
            @RequestParam(value = "mobile", required = false) String mobile,
            @RequestParam(value = "idCardNo", required = false) String idCardNo) {
        CustomerDO customer = customerService.searchCustomerByMobileOrIdCard(mobile, idCardNo);
        return success(BeanUtils.toBean(customer, AppCustomerRespVO.class));
    }

}
