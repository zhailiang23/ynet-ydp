package com.ynet.iplatform.module.twins.controller.admin.customerAdmin;

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

import com.ynet.iplatform.module.twins.controller.admin.customerAdmin.vo.*;
import com.ynet.iplatform.module.twins.dal.dataobject.customerAdmin.CustomerAdminDO;
import com.ynet.iplatform.module.twins.service.customerAdmin.CustomerAdminService;

@Tag(name = "管理后台 - 客服信息")
@RestController
@RequestMapping("/twins/customer-admin")
@Validated
public class CustomerAdminController {

    @Resource
    private CustomerAdminService customerAdminService;

    @PostMapping("/create")
    @Operation(summary = "创建客服信息")
    @PreAuthorize("@ss.hasPermission('twins:customer-admin:create')")
    public CommonResult<Integer> createCustomerAdmin(@Valid @RequestBody CustomerAdminSaveReqVO createReqVO) {
        return success(customerAdminService.createCustomerAdmin(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客服信息")
    @PreAuthorize("@ss.hasPermission('twins:customer-admin:update')")
    public CommonResult<Boolean> updateCustomerAdmin(@Valid @RequestBody CustomerAdminSaveReqVO updateReqVO) {
        customerAdminService.updateCustomerAdmin(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客服信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('twins:customer-admin:delete')")
    public CommonResult<Boolean> deleteCustomerAdmin(@RequestParam("id") Integer id) {
        customerAdminService.deleteCustomerAdmin(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客服信息")
                @PreAuthorize("@ss.hasPermission('twins:customer-admin:delete')")
    public CommonResult<Boolean> deleteCustomerAdminList(@RequestParam("ids") List<Integer> ids) {
        customerAdminService.deleteCustomerAdminListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客服信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('twins:customer-admin:query')")
    public CommonResult<CustomerAdminRespVO> getCustomerAdmin(@RequestParam("id") Integer id) {
        CustomerAdminDO customerAdmin = customerAdminService.getCustomerAdmin(id);
        return success(BeanUtils.toBean(customerAdmin, CustomerAdminRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客服信息分页")
    @PreAuthorize("@ss.hasPermission('twins:customer-admin:query')")
    public CommonResult<PageResult<CustomerAdminRespVO>> getCustomerAdminPage(@Valid CustomerAdminPageReqVO pageReqVO) {
        PageResult<CustomerAdminDO> pageResult = customerAdminService.getCustomerAdminPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerAdminRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客服信息 Excel")
    @PreAuthorize("@ss.hasPermission('twins:customer-admin:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerAdminExcel(@Valid CustomerAdminPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerAdminDO> list = customerAdminService.getCustomerAdminPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客服信息.xls", "数据", CustomerAdminRespVO.class,
                        BeanUtils.toBean(list, CustomerAdminRespVO.class));
    }

    @GetMapping("/check-exists")
    @Operation(summary = "检查客服是否已存在")
    @Parameter(name = "customerId", description = "客户 ID (用户 ID)", required = true)
    @PreAuthorize("@ss.hasPermission('twins:customer-admin:query')")
    public CommonResult<Boolean> checkCustomerAdminExists(@RequestParam("customerId") Integer customerId) {
        return success(customerAdminService.checkCustomerAdminExists(customerId));
    }

}