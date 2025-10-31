package cn.iocoder.yudao.module.aicrm.controller.admin.customerchanneloverview;

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

import cn.iocoder.yudao.module.aicrm.controller.admin.customerchanneloverview.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerchanneloverview.CustomerChannelOverviewDO;
import cn.iocoder.yudao.module.aicrm.service.customerchanneloverview.CustomerChannelOverviewService;

@Tag(name = "管理后台 - 客户渠道业务概览")
@RestController
@RequestMapping("/aicrm/customer-channel-overview")
@Validated
public class CustomerChannelOverviewController {

    @Resource
    private CustomerChannelOverviewService customerChannelOverviewService;

    @PostMapping("/create")
    @Operation(summary = "创建客户渠道业务概览")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-channel-overview:create')")
    public CommonResult<Long> createCustomerChannelOverview(@Valid @RequestBody CustomerChannelOverviewSaveReqVO createReqVO) {
        return success(customerChannelOverviewService.createCustomerChannelOverview(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户渠道业务概览")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-channel-overview:update')")
    public CommonResult<Boolean> updateCustomerChannelOverview(@Valid @RequestBody CustomerChannelOverviewSaveReqVO updateReqVO) {
        customerChannelOverviewService.updateCustomerChannelOverview(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户渠道业务概览")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-channel-overview:delete')")
    public CommonResult<Boolean> deleteCustomerChannelOverview(@RequestParam("id") Long id) {
        customerChannelOverviewService.deleteCustomerChannelOverview(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户渠道业务概览")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-channel-overview:delete')")
    public CommonResult<Boolean> deleteCustomerChannelOverviewList(@RequestParam("ids") List<Long> ids) {
        customerChannelOverviewService.deleteCustomerChannelOverviewListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户渠道业务概览")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-channel-overview:query')")
    public CommonResult<CustomerChannelOverviewRespVO> getCustomerChannelOverview(@RequestParam("id") Long id) {
        CustomerChannelOverviewDO customerChannelOverview = customerChannelOverviewService.getCustomerChannelOverview(id);
        return success(BeanUtils.toBean(customerChannelOverview, CustomerChannelOverviewRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户渠道业务概览分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-channel-overview:query')")
    public CommonResult<PageResult<CustomerChannelOverviewRespVO>> getCustomerChannelOverviewPage(@Valid CustomerChannelOverviewPageReqVO pageReqVO) {
        PageResult<CustomerChannelOverviewDO> pageResult = customerChannelOverviewService.getCustomerChannelOverviewPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerChannelOverviewRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户渠道业务概览 Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-channel-overview:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerChannelOverviewExcel(@Valid CustomerChannelOverviewPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerChannelOverviewDO> list = customerChannelOverviewService.getCustomerChannelOverviewPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户渠道业务概览.xls", "数据", CustomerChannelOverviewRespVO.class,
                        BeanUtils.toBean(list, CustomerChannelOverviewRespVO.class));
    }

}