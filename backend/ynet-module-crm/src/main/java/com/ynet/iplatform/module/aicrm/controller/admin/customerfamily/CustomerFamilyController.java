package com.ynet.iplatform.module.aicrm.controller.admin.customerfamily;

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

import com.ynet.iplatform.module.aicrm.controller.admin.customerfamily.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerfamily.CustomerFamilyDO;
import com.ynet.iplatform.module.aicrm.service.customerfamily.CustomerFamilyService;

@Tag(name = "管理后台 - 客户家庭信息表（零售客户）")
@RestController
@RequestMapping("/aicrm/customer-family")
@Validated
public class CustomerFamilyController {

    @Resource
    private CustomerFamilyService customerFamilyService;

    @PostMapping("/create")
    @Operation(summary = "创建客户家庭信息表（零售客户）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-family:create')")
    public CommonResult<Long> createCustomerFamily(@Valid @RequestBody CustomerFamilySaveReqVO createReqVO) {
        return success(customerFamilyService.createCustomerFamily(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户家庭信息表（零售客户）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-family:update')")
    public CommonResult<Boolean> updateCustomerFamily(@Valid @RequestBody CustomerFamilySaveReqVO updateReqVO) {
        customerFamilyService.updateCustomerFamily(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户家庭信息表（零售客户）")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-family:delete')")
    public CommonResult<Boolean> deleteCustomerFamily(@RequestParam("id") Long id) {
        customerFamilyService.deleteCustomerFamily(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户家庭信息表（零售客户）")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-family:delete')")
    public CommonResult<Boolean> deleteCustomerFamilyList(@RequestParam("ids") List<Long> ids) {
        customerFamilyService.deleteCustomerFamilyListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户家庭信息表（零售客户）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-family:query')")
    public CommonResult<CustomerFamilyRespVO> getCustomerFamily(@RequestParam("id") Long id) {
        CustomerFamilyDO customerFamily = customerFamilyService.getCustomerFamily(id);
        return success(BeanUtils.toBean(customerFamily, CustomerFamilyRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户家庭信息表（零售客户）分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-family:query')")
    public CommonResult<PageResult<CustomerFamilyRespVO>> getCustomerFamilyPage(@Valid CustomerFamilyPageReqVO pageReqVO) {
        PageResult<CustomerFamilyDO> pageResult = customerFamilyService.getCustomerFamilyPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerFamilyRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户家庭信息表（零售客户） Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-family:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerFamilyExcel(@Valid CustomerFamilyPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerFamilyDO> list = customerFamilyService.getCustomerFamilyPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户家庭信息表（零售客户）.xls", "数据", CustomerFamilyRespVO.class,
                        BeanUtils.toBean(list, CustomerFamilyRespVO.class));
    }

}