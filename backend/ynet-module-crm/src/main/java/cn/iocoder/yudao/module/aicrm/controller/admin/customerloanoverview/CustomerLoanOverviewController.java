package cn.iocoder.yudao.module.aicrm.controller.admin.customerloanoverview;

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

import cn.iocoder.yudao.module.aicrm.controller.admin.customerloanoverview.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerloanoverview.CustomerLoanOverviewDO;
import cn.iocoder.yudao.module.aicrm.service.customerloanoverview.CustomerLoanOverviewService;

@Tag(name = "管理后台 - 客户贷款业务概览")
@RestController
@RequestMapping("/aicrm/customer-loan-overview")
@Validated
public class CustomerLoanOverviewController {

    @Resource
    private CustomerLoanOverviewService customerLoanOverviewService;

    @PostMapping("/create")
    @Operation(summary = "创建客户贷款业务概览")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-loan-overview:create')")
    public CommonResult<Long> createCustomerLoanOverview(@Valid @RequestBody CustomerLoanOverviewSaveReqVO createReqVO) {
        return success(customerLoanOverviewService.createCustomerLoanOverview(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户贷款业务概览")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-loan-overview:update')")
    public CommonResult<Boolean> updateCustomerLoanOverview(@Valid @RequestBody CustomerLoanOverviewSaveReqVO updateReqVO) {
        customerLoanOverviewService.updateCustomerLoanOverview(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户贷款业务概览")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-loan-overview:delete')")
    public CommonResult<Boolean> deleteCustomerLoanOverview(@RequestParam("id") Long id) {
        customerLoanOverviewService.deleteCustomerLoanOverview(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户贷款业务概览")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-loan-overview:delete')")
    public CommonResult<Boolean> deleteCustomerLoanOverviewList(@RequestParam("ids") List<Long> ids) {
        customerLoanOverviewService.deleteCustomerLoanOverviewListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户贷款业务概览")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-loan-overview:query')")
    public CommonResult<CustomerLoanOverviewRespVO> getCustomerLoanOverview(@RequestParam("id") Long id) {
        CustomerLoanOverviewDO customerLoanOverview = customerLoanOverviewService.getCustomerLoanOverview(id);
        return success(BeanUtils.toBean(customerLoanOverview, CustomerLoanOverviewRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户贷款业务概览分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-loan-overview:query')")
    public CommonResult<PageResult<CustomerLoanOverviewRespVO>> getCustomerLoanOverviewPage(@Valid CustomerLoanOverviewPageReqVO pageReqVO) {
        PageResult<CustomerLoanOverviewDO> pageResult = customerLoanOverviewService.getCustomerLoanOverviewPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerLoanOverviewRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户贷款业务概览 Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-loan-overview:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerLoanOverviewExcel(@Valid CustomerLoanOverviewPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerLoanOverviewDO> list = customerLoanOverviewService.getCustomerLoanOverviewPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户贷款业务概览.xls", "数据", CustomerLoanOverviewRespVO.class,
                        BeanUtils.toBean(list, CustomerLoanOverviewRespVO.class));
    }

}