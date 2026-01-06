package com.ynet.iplatform.module.aicrm.controller.admin.customermarketingactivity;

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

import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.CommonResult;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;
import static com.ynet.iplatform.framework.common.pojo.CommonResult.success;

import com.ynet.iplatform.framework.excel.core.util.ExcelUtils;

import com.ynet.iplatform.framework.apilog.core.annotation.ApiAccessLog;
import static com.ynet.iplatform.framework.apilog.core.enums.OperateTypeEnum.*;

import com.ynet.iplatform.module.aicrm.controller.admin.customermarketingactivity.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customermarketingactivity.CustomerMarketingActivityDO;
import com.ynet.iplatform.module.aicrm.service.customermarketingactivity.CustomerMarketingActivityService;

@Tag(name = "管理后台 - 客户营销活动信息")
@RestController
@RequestMapping("/aicrm/customer-marketing-activity")
@Validated
public class CustomerMarketingActivityController {

    @Resource
    private CustomerMarketingActivityService customerMarketingActivityService;

    @PostMapping("/create")
    @Operation(summary = "创建客户营销活动信息")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-marketing-activity:create')")
    public CommonResult<Long> createCustomerMarketingActivity(@Valid @RequestBody CustomerMarketingActivitySaveReqVO createReqVO) {
        return success(customerMarketingActivityService.createCustomerMarketingActivity(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户营销活动信息")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-marketing-activity:update')")
    public CommonResult<Boolean> updateCustomerMarketingActivity(@Valid @RequestBody CustomerMarketingActivitySaveReqVO updateReqVO) {
        customerMarketingActivityService.updateCustomerMarketingActivity(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户营销活动信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-marketing-activity:delete')")
    public CommonResult<Boolean> deleteCustomerMarketingActivity(@RequestParam("id") Long id) {
        customerMarketingActivityService.deleteCustomerMarketingActivity(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户营销活动信息")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-marketing-activity:delete')")
    public CommonResult<Boolean> deleteCustomerMarketingActivityList(@RequestParam("ids") List<Long> ids) {
        customerMarketingActivityService.deleteCustomerMarketingActivityListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户营销活动信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-marketing-activity:query')")
    public CommonResult<CustomerMarketingActivityRespVO> getCustomerMarketingActivity(@RequestParam("id") Long id) {
        CustomerMarketingActivityDO customerMarketingActivity = customerMarketingActivityService.getCustomerMarketingActivity(id);
        return success(BeanUtils.toBean(customerMarketingActivity, CustomerMarketingActivityRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户营销活动信息分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-marketing-activity:query')")
    public CommonResult<PageResult<CustomerMarketingActivityRespVO>> getCustomerMarketingActivityPage(@Valid CustomerMarketingActivityPageReqVO pageReqVO) {
        PageResult<CustomerMarketingActivityDO> pageResult = customerMarketingActivityService.getCustomerMarketingActivityPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerMarketingActivityRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户营销活动信息 Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-marketing-activity:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerMarketingActivityExcel(@Valid CustomerMarketingActivityPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerMarketingActivityDO> list = customerMarketingActivityService.getCustomerMarketingActivityPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户营销活动信息.xls", "数据", CustomerMarketingActivityRespVO.class,
                        BeanUtils.toBean(list, CustomerMarketingActivityRespVO.class));
    }

    @GetMapping("/simple-list")
    @Operation(summary = "获取营销活动简化列表（用于下拉选择）")
    public CommonResult<List<CustomerMarketingActivitySimpleVO>> getCustomerMarketingActivitySimpleList() {
        List<CustomerMarketingActivityDO> list = customerMarketingActivityService.getCustomerMarketingActivitySimpleList();
        return success(BeanUtils.toBean(list, CustomerMarketingActivitySimpleVO.class));
    }

}