package com.ynet.iplatform.module.aicrm.controller.admin.customercomplaint;

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

import com.ynet.iplatform.module.aicrm.controller.admin.customercomplaint.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customercomplaint.CustomerComplaintDO;
import com.ynet.iplatform.module.aicrm.service.customercomplaint.CustomerComplaintService;

@Tag(name = "管理后台 - 客户投诉信息")
@RestController
@RequestMapping("/aicrm/customer-complaint")
@Validated
public class CustomerComplaintController {

    @Resource
    private CustomerComplaintService customerComplaintService;

    @PostMapping("/create")
    @Operation(summary = "创建客户投诉信息")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-complaint:create')")
    public CommonResult<Long> createCustomerComplaint(@Valid @RequestBody CustomerComplaintSaveReqVO createReqVO) {
        return success(customerComplaintService.createCustomerComplaint(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户投诉信息")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-complaint:update')")
    public CommonResult<Boolean> updateCustomerComplaint(@Valid @RequestBody CustomerComplaintSaveReqVO updateReqVO) {
        customerComplaintService.updateCustomerComplaint(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户投诉信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-complaint:delete')")
    public CommonResult<Boolean> deleteCustomerComplaint(@RequestParam("id") Long id) {
        customerComplaintService.deleteCustomerComplaint(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户投诉信息")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-complaint:delete')")
    public CommonResult<Boolean> deleteCustomerComplaintList(@RequestParam("ids") List<Long> ids) {
        customerComplaintService.deleteCustomerComplaintListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户投诉信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-complaint:query')")
    public CommonResult<CustomerComplaintRespVO> getCustomerComplaint(@RequestParam("id") Long id) {
        CustomerComplaintDO customerComplaint = customerComplaintService.getCustomerComplaint(id);
        return success(BeanUtils.toBean(customerComplaint, CustomerComplaintRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户投诉信息分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-complaint:query')")
    public CommonResult<PageResult<CustomerComplaintRespVO>> getCustomerComplaintPage(@Valid CustomerComplaintPageReqVO pageReqVO) {
        PageResult<CustomerComplaintDO> pageResult = customerComplaintService.getCustomerComplaintPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerComplaintRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户投诉信息 Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-complaint:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerComplaintExcel(@Valid CustomerComplaintPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerComplaintDO> list = customerComplaintService.getCustomerComplaintPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户投诉信息.xls", "数据", CustomerComplaintRespVO.class,
                        BeanUtils.toBean(list, CustomerComplaintRespVO.class));
    }

}