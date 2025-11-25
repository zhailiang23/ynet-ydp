package com.ynet.iplatform.module.aicrm.controller.admin.customerguarantor;

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

import com.ynet.iplatform.module.aicrm.controller.admin.customerguarantor.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerguarantor.CustomerGuarantorDO;
import com.ynet.iplatform.module.aicrm.service.customerguarantor.CustomerGuarantorService;

@Tag(name = "管理后台 - 客户担保人信息表（零售+对公共用）")
@RestController
@RequestMapping("/aicrm/customer-guarantor")
@Validated
public class CustomerGuarantorController {

    @Resource
    private CustomerGuarantorService customerGuarantorService;

    @PostMapping("/create")
    @Operation(summary = "创建客户担保人信息表（零售+对公共用）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-guarantor:create')")
    public CommonResult<Long> createCustomerGuarantor(@Valid @RequestBody CustomerGuarantorSaveReqVO createReqVO) {
        return success(customerGuarantorService.createCustomerGuarantor(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户担保人信息表（零售+对公共用）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-guarantor:update')")
    public CommonResult<Boolean> updateCustomerGuarantor(@Valid @RequestBody CustomerGuarantorSaveReqVO updateReqVO) {
        customerGuarantorService.updateCustomerGuarantor(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户担保人信息表（零售+对公共用）")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-guarantor:delete')")
    public CommonResult<Boolean> deleteCustomerGuarantor(@RequestParam("id") Long id) {
        customerGuarantorService.deleteCustomerGuarantor(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户担保人信息表（零售+对公共用）")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-guarantor:delete')")
    public CommonResult<Boolean> deleteCustomerGuarantorList(@RequestParam("ids") List<Long> ids) {
        customerGuarantorService.deleteCustomerGuarantorListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户担保人信息表（零售+对公共用）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-guarantor:query')")
    public CommonResult<CustomerGuarantorRespVO> getCustomerGuarantor(@RequestParam("id") Long id) {
        CustomerGuarantorDO customerGuarantor = customerGuarantorService.getCustomerGuarantor(id);
        return success(BeanUtils.toBean(customerGuarantor, CustomerGuarantorRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户担保人信息表（零售+对公共用）分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-guarantor:query')")
    public CommonResult<PageResult<CustomerGuarantorRespVO>> getCustomerGuarantorPage(@Valid CustomerGuarantorPageReqVO pageReqVO) {
        PageResult<CustomerGuarantorDO> pageResult = customerGuarantorService.getCustomerGuarantorPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerGuarantorRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户担保人信息表（零售+对公共用） Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-guarantor:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerGuarantorExcel(@Valid CustomerGuarantorPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerGuarantorDO> list = customerGuarantorService.getCustomerGuarantorPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户担保人信息表（零售+对公共用）.xls", "数据", CustomerGuarantorRespVO.class,
                        BeanUtils.toBean(list, CustomerGuarantorRespVO.class));
    }

}