package com.ynet.iplatform.module.aicrm.controller.admin.customercreditdetail;

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

import com.ynet.iplatform.module.aicrm.controller.admin.customercreditdetail.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customercreditdetail.CustomerCreditDetailDO;
import com.ynet.iplatform.module.aicrm.service.customercreditdetail.CustomerCreditDetailService;

@Tag(name = "管理后台 - 客户授信使用明细表（零售+对公共用）")
@RestController
@RequestMapping("/aicrm/customer-credit-detail")
@Validated
public class CustomerCreditDetailController {

    @Resource
    private CustomerCreditDetailService customerCreditDetailService;

    @PostMapping("/create")
    @Operation(summary = "创建客户授信使用明细表（零售+对公共用）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-credit-detail:create')")
    public CommonResult<Long> createCustomerCreditDetail(@Valid @RequestBody CustomerCreditDetailSaveReqVO createReqVO) {
        return success(customerCreditDetailService.createCustomerCreditDetail(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户授信使用明细表（零售+对公共用）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-credit-detail:update')")
    public CommonResult<Boolean> updateCustomerCreditDetail(@Valid @RequestBody CustomerCreditDetailSaveReqVO updateReqVO) {
        customerCreditDetailService.updateCustomerCreditDetail(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户授信使用明细表（零售+对公共用）")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-credit-detail:delete')")
    public CommonResult<Boolean> deleteCustomerCreditDetail(@RequestParam("id") Long id) {
        customerCreditDetailService.deleteCustomerCreditDetail(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户授信使用明细表（零售+对公共用）")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-credit-detail:delete')")
    public CommonResult<Boolean> deleteCustomerCreditDetailList(@RequestParam("ids") List<Long> ids) {
        customerCreditDetailService.deleteCustomerCreditDetailListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户授信使用明细表（零售+对公共用）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-credit-detail:query')")
    public CommonResult<CustomerCreditDetailRespVO> getCustomerCreditDetail(@RequestParam("id") Long id) {
        CustomerCreditDetailDO customerCreditDetail = customerCreditDetailService.getCustomerCreditDetail(id);
        return success(BeanUtils.toBean(customerCreditDetail, CustomerCreditDetailRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户授信使用明细表（零售+对公共用）分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-credit-detail:query')")
    public CommonResult<PageResult<CustomerCreditDetailRespVO>> getCustomerCreditDetailPage(@Valid CustomerCreditDetailPageReqVO pageReqVO) {
        PageResult<CustomerCreditDetailDO> pageResult = customerCreditDetailService.getCustomerCreditDetailPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerCreditDetailRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户授信使用明细表（零售+对公共用） Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-credit-detail:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerCreditDetailExcel(@Valid CustomerCreditDetailPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerCreditDetailDO> list = customerCreditDetailService.getCustomerCreditDetailPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户授信使用明细表（零售+对公共用）.xls", "数据", CustomerCreditDetailRespVO.class,
                        BeanUtils.toBean(list, CustomerCreditDetailRespVO.class));
    }

}