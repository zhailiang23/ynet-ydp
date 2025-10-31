package cn.iocoder.yudao.module.aicrm.controller.admin.customerdepositoverview;

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

import cn.iocoder.yudao.module.aicrm.controller.admin.customerdepositoverview.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerdepositoverview.CustomerDepositOverviewDO;
import cn.iocoder.yudao.module.aicrm.service.customerdepositoverview.CustomerDepositOverviewService;

@Tag(name = "管理后台 - 客户存款业务概览")
@RestController
@RequestMapping("/aicrm/customer-deposit-overview")
@Validated
public class CustomerDepositOverviewController {

    @Resource
    private CustomerDepositOverviewService customerDepositOverviewService;

    @PostMapping("/create")
    @Operation(summary = "创建客户存款业务概览")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-deposit-overview:create')")
    public CommonResult<Long> createCustomerDepositOverview(@Valid @RequestBody CustomerDepositOverviewSaveReqVO createReqVO) {
        return success(customerDepositOverviewService.createCustomerDepositOverview(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户存款业务概览")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-deposit-overview:update')")
    public CommonResult<Boolean> updateCustomerDepositOverview(@Valid @RequestBody CustomerDepositOverviewSaveReqVO updateReqVO) {
        customerDepositOverviewService.updateCustomerDepositOverview(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户存款业务概览")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-deposit-overview:delete')")
    public CommonResult<Boolean> deleteCustomerDepositOverview(@RequestParam("id") Long id) {
        customerDepositOverviewService.deleteCustomerDepositOverview(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户存款业务概览")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-deposit-overview:delete')")
    public CommonResult<Boolean> deleteCustomerDepositOverviewList(@RequestParam("ids") List<Long> ids) {
        customerDepositOverviewService.deleteCustomerDepositOverviewListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户存款业务概览")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-deposit-overview:query')")
    public CommonResult<CustomerDepositOverviewRespVO> getCustomerDepositOverview(@RequestParam("id") Long id) {
        CustomerDepositOverviewDO customerDepositOverview = customerDepositOverviewService.getCustomerDepositOverview(id);
        return success(BeanUtils.toBean(customerDepositOverview, CustomerDepositOverviewRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户存款业务概览分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-deposit-overview:query')")
    public CommonResult<PageResult<CustomerDepositOverviewRespVO>> getCustomerDepositOverviewPage(@Valid CustomerDepositOverviewPageReqVO pageReqVO) {
        PageResult<CustomerDepositOverviewDO> pageResult = customerDepositOverviewService.getCustomerDepositOverviewPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerDepositOverviewRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户存款业务概览 Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-deposit-overview:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerDepositOverviewExcel(@Valid CustomerDepositOverviewPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerDepositOverviewDO> list = customerDepositOverviewService.getCustomerDepositOverviewPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户存款业务概览.xls", "数据", CustomerDepositOverviewRespVO.class,
                        BeanUtils.toBean(list, CustomerDepositOverviewRespVO.class));
    }

}