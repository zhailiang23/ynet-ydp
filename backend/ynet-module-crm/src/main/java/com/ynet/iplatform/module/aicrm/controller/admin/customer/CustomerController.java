package com.ynet.iplatform.module.aicrm.controller.admin.customer;

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

import com.ynet.iplatform.module.aicrm.controller.admin.customer.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customer.CustomerDO;
import com.ynet.iplatform.module.aicrm.service.customer.CustomerService;

@Tag(name = "管理后台 - CRM客户主表(零售+对公共用)")
@RestController
@RequestMapping("/aicrm/customer")
@Validated
public class CustomerController {

    @Resource
    private CustomerService customerService;

    @PostMapping("/create")
    @Operation(summary = "创建CRM客户主表(零售+对公共用)")
    @PreAuthorize("@ss.hasPermission('aicrm:customer:create')")
    public CommonResult<Long> createCustomer(@Valid @RequestBody CustomerSaveReqVO createReqVO) {
        return success(customerService.createCustomer(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新CRM客户主表(零售+对公共用)")
    @PreAuthorize("@ss.hasPermission('aicrm:customer:update')")
    public CommonResult<Boolean> updateCustomer(@Valid @RequestBody CustomerSaveReqVO updateReqVO) {
        customerService.updateCustomer(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除CRM客户主表(零售+对公共用)")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer:delete')")
    public CommonResult<Boolean> deleteCustomer(@RequestParam("id") Long id) {
        customerService.deleteCustomer(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除CRM客户主表(零售+对公共用)")
                @PreAuthorize("@ss.hasPermission('aicrm:customer:delete')")
    public CommonResult<Boolean> deleteCustomerList(@RequestParam("ids") List<Long> ids) {
        customerService.deleteCustomerListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得CRM客户主表(零售+对公共用)")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer:query')")
    public CommonResult<CustomerRespVO> getCustomer(@RequestParam("id") Long id) {
        CustomerDO customer = customerService.getCustomer(id);
        return success(BeanUtils.toBean(customer, CustomerRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得CRM客户主表(零售+对公共用)分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer:query')")
    public CommonResult<PageResult<CustomerRespVO>> getCustomerPage(@Valid CustomerPageReqVO pageReqVO) {
        PageResult<CustomerDO> pageResult = customerService.getCustomerPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerRespVO.class));
    }

    @GetMapping("/search")
    @Operation(summary = "客户识别查询", description = "通过手机号或证件号查询客户信息，用于到店客户识别场景")
    @Parameter(name = "mobile", description = "手机号", example = "13800138000")
    @Parameter(name = "idCardNo", description = "证件号码", example = "110101199001011234")
    public CommonResult<CustomerRespVO> searchCustomer(
            @RequestParam(value = "mobile", required = false) String mobile,
            @RequestParam(value = "idCardNo", required = false) String idCardNo) {
        CustomerDO customer = customerService.searchCustomerByMobileOrIdCard(mobile, idCardNo);
        return success(BeanUtils.toBean(customer, CustomerRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出CRM客户主表(零售+对公共用) Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerExcel(@Valid CustomerPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerDO> list = customerService.getCustomerPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "CRM客户主表(零售+对公共用).xls", "数据", CustomerRespVO.class,
                        BeanUtils.toBean(list, CustomerRespVO.class));
    }

    @GetMapping("/simple-list")
    @Operation(summary = "获得客户简单列表", description = "用于下拉选择，返回 id、customerName、customerNo 等基本信息")
    public CommonResult<List<CustomerRespVO>> getSimpleCustomerList() {
        List<CustomerDO> list = customerService.getSimpleCustomerList();
        return success(BeanUtils.toBean(list, CustomerRespVO.class));
    }

}