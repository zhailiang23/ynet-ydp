package cn.iocoder.yudao.module.aicrm.controller.admin.customerclaim;
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

import cn.iocoder.yudao.module.aicrm.controller.admin.customerclaim.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerclaim.CustomerClaimApplicationDO;
import cn.iocoder.yudao.module.aicrm.service.customerclaim.CustomerClaimService;

@Tag(name = "管理后台 - 客户认领申请")
@RestController
@RequestMapping("/aicrm/customer-claim-application")
@Validated
public class CustomerClaimApplicationController {

    @Resource
    private CustomerClaimService customerClaimService;

    @PostMapping("/apply")
    @Operation(summary = "提交客户认领申请")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-claim:create')")
    public CommonResult<Long> applyForClaim(@Valid @RequestBody CustomerClaimApplicationApplyReqVO applyReqVO) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        return success(customerClaimService.applyForClaim(userId, applyReqVO));
    }

    @DeleteMapping("/cancel")
    @Operation(summary = "取消客户认领申请")
    @Parameter(name = "id", description = "申请ID", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-claim:delete')")
    public CommonResult<Boolean> cancelClaimApplication(@RequestParam("id") Long id) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        customerClaimService.cancelClaimApplication(userId, id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户认领申请")
    @Parameter(name = "id", description = "编号", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-claim:query')")
    public CommonResult<CustomerClaimApplicationRespVO> getClaimApplication(@RequestParam("id") Long id) {
        return success(customerClaimService.getClaimApplicationDetail(id));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户认领申请分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-claim:query')")
    public CommonResult<PageResult<CustomerClaimApplicationDO>> getClaimApplicationPage(@Valid CustomerClaimApplicationPageReqVO pageReqVO) {
        return success(customerClaimService.getClaimApplicationPage(pageReqVO));
    }

}
