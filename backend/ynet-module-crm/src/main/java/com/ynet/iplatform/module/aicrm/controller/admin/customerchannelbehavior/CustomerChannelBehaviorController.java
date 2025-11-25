package com.ynet.iplatform.module.aicrm.controller.admin.customerchannelbehavior;

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

import com.ynet.iplatform.module.aicrm.controller.admin.customerchannelbehavior.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerchannelbehavior.CustomerChannelBehaviorDO;
import com.ynet.iplatform.module.aicrm.service.customerchannelbehavior.CustomerChannelBehaviorService;

@Tag(name = "管理后台 - 客户渠道行为信息")
@RestController
@RequestMapping("/aicrm/customer-channel-behavior")
@Validated
public class CustomerChannelBehaviorController {

    @Resource
    private CustomerChannelBehaviorService customerChannelBehaviorService;

    @PostMapping("/create")
    @Operation(summary = "创建客户渠道行为信息")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-channel-behavior:create')")
    public CommonResult<Long> createCustomerChannelBehavior(@Valid @RequestBody CustomerChannelBehaviorSaveReqVO createReqVO) {
        return success(customerChannelBehaviorService.createCustomerChannelBehavior(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户渠道行为信息")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-channel-behavior:update')")
    public CommonResult<Boolean> updateCustomerChannelBehavior(@Valid @RequestBody CustomerChannelBehaviorSaveReqVO updateReqVO) {
        customerChannelBehaviorService.updateCustomerChannelBehavior(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户渠道行为信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-channel-behavior:delete')")
    public CommonResult<Boolean> deleteCustomerChannelBehavior(@RequestParam("id") Long id) {
        customerChannelBehaviorService.deleteCustomerChannelBehavior(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户渠道行为信息")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-channel-behavior:delete')")
    public CommonResult<Boolean> deleteCustomerChannelBehaviorList(@RequestParam("ids") List<Long> ids) {
        customerChannelBehaviorService.deleteCustomerChannelBehaviorListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户渠道行为信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-channel-behavior:query')")
    public CommonResult<CustomerChannelBehaviorRespVO> getCustomerChannelBehavior(@RequestParam("id") Long id) {
        CustomerChannelBehaviorDO customerChannelBehavior = customerChannelBehaviorService.getCustomerChannelBehavior(id);
        return success(BeanUtils.toBean(customerChannelBehavior, CustomerChannelBehaviorRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户渠道行为信息分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-channel-behavior:query')")
    public CommonResult<PageResult<CustomerChannelBehaviorRespVO>> getCustomerChannelBehaviorPage(@Valid CustomerChannelBehaviorPageReqVO pageReqVO) {
        PageResult<CustomerChannelBehaviorDO> pageResult = customerChannelBehaviorService.getCustomerChannelBehaviorPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerChannelBehaviorRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户渠道行为信息 Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-channel-behavior:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerChannelBehaviorExcel(@Valid CustomerChannelBehaviorPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerChannelBehaviorDO> list = customerChannelBehaviorService.getCustomerChannelBehaviorPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户渠道行为信息.xls", "数据", CustomerChannelBehaviorRespVO.class,
                        BeanUtils.toBean(list, CustomerChannelBehaviorRespVO.class));
    }

}