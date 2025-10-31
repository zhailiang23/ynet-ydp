package cn.iocoder.yudao.module.aicrm.controller.admin.customerclaimapplication;

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

import cn.iocoder.yudao.module.aicrm.controller.admin.customerclaimapplication.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerclaimapplication.CustomerClaimApplicationDO;
import cn.iocoder.yudao.module.aicrm.service.customerclaimapplication.CustomerClaimApplicationService;

@Tag(name = "管理后台 - 客户认领申请")
@RestController
@RequestMapping("/aicrm/customer-claim-application")
@Validated
public class CustomerClaimApplicationController {

    @Resource
    private CustomerClaimApplicationService customerClaimApplicationService;

    @PostMapping("/create")
    @Operation(summary = "创建客户认领申请")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-claim-application:create')")
    public CommonResult<Long> createCustomerClaimApplication(@Valid @RequestBody CustomerClaimApplicationSaveReqVO createReqVO) {
        return success(customerClaimApplicationService.createCustomerClaimApplication(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户认领申请")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-claim-application:update')")
    public CommonResult<Boolean> updateCustomerClaimApplication(@Valid @RequestBody CustomerClaimApplicationSaveReqVO updateReqVO) {
        customerClaimApplicationService.updateCustomerClaimApplication(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户认领申请")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-claim-application:delete')")
    public CommonResult<Boolean> deleteCustomerClaimApplication(@RequestParam("id") Long id) {
        customerClaimApplicationService.deleteCustomerClaimApplication(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户认领申请")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-claim-application:delete')")
    public CommonResult<Boolean> deleteCustomerClaimApplicationList(@RequestParam("ids") List<Long> ids) {
        customerClaimApplicationService.deleteCustomerClaimApplicationListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户认领申请")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-claim-application:query')")
    public CommonResult<CustomerClaimApplicationRespVO> getCustomerClaimApplication(@RequestParam("id") Long id) {
        CustomerClaimApplicationDO customerClaimApplication = customerClaimApplicationService.getCustomerClaimApplication(id);
        return success(BeanUtils.toBean(customerClaimApplication, CustomerClaimApplicationRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户认领申请分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-claim-application:query')")
    public CommonResult<PageResult<CustomerClaimApplicationRespVO>> getCustomerClaimApplicationPage(@Valid CustomerClaimApplicationPageReqVO pageReqVO) {
        PageResult<CustomerClaimApplicationDO> pageResult = customerClaimApplicationService.getCustomerClaimApplicationPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerClaimApplicationRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户认领申请 Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-claim-application:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerClaimApplicationExcel(@Valid CustomerClaimApplicationPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerClaimApplicationDO> list = customerClaimApplicationService.getCustomerClaimApplicationPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户认领申请.xls", "数据", CustomerClaimApplicationRespVO.class,
                        BeanUtils.toBean(list, CustomerClaimApplicationRespVO.class));
    }

}