package cn.iocoder.yudao.module.aicrm.controller.admin.customertransfer;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.module.aicrm.controller.admin.customertransfer.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customertransfer.CustomerTransferApplicationDO;
import cn.iocoder.yudao.module.aicrm.service.customertransfer.CustomerTransferService;

@Tag(name = "管理后台 - 客户移交申请")
@RestController
@RequestMapping("/aicrm/customer-transfer-application")
@Validated
public class CustomerTransferApplicationController {

    @Resource
    private CustomerTransferService customerTransferService;

    @PostMapping("/apply")
    @Operation(summary = "提交客户移交申请")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-transfer:create')")
    public CommonResult<Long> applyForTransfer(@Valid @RequestBody CustomerTransferApplicationApplyReqVO applyReqVO) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        return success(customerTransferService.applyForTransfer(userId, applyReqVO));
    }

    @DeleteMapping("/cancel")
    @Operation(summary = "取消客户移交申请")
    @Parameter(name = "id", description = "申请ID", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-transfer:delete')")
    public CommonResult<Boolean> cancelTransferApplication(@RequestParam("id") Long id) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        customerTransferService.cancelTransferApplication(userId, id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户移交申请")
    @Parameter(name = "id", description = "编号", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-transfer:query')")
    public CommonResult<CustomerTransferApplicationDO> getTransferApplication(@RequestParam("id") Long id) {
        return success(customerTransferService.getTransferApplication(id));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户移交申请分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-transfer:query')")
    public CommonResult<PageResult<CustomerTransferApplicationDO>> getTransferApplicationPage(@Valid CustomerTransferApplicationPageReqVO pageReqVO) {
        return success(customerTransferService.getTransferApplicationPage(pageReqVO));
    }

}
