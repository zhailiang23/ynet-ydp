package com.ynet.iplatform.module.aicrm.controller.admin.customercontact;

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

import com.ynet.iplatform.module.aicrm.controller.admin.customercontact.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customercontact.CustomerContactDO;
import com.ynet.iplatform.module.aicrm.service.customercontact.CustomerContactService;

@Tag(name = "管理后台 - 客户接触信息")
@RestController
@RequestMapping("/aicrm/customer-contact")
@Validated
public class CustomerContactController {

    @Resource
    private CustomerContactService customerContactService;

    @PostMapping("/create")
    @Operation(summary = "创建客户接触信息")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-contact:create')")
    public CommonResult<Long> createCustomerContact(@Valid @RequestBody CustomerContactSaveReqVO createReqVO) {
        return success(customerContactService.createCustomerContact(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户接触信息")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-contact:update')")
    public CommonResult<Boolean> updateCustomerContact(@Valid @RequestBody CustomerContactSaveReqVO updateReqVO) {
        customerContactService.updateCustomerContact(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户接触信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-contact:delete')")
    public CommonResult<Boolean> deleteCustomerContact(@RequestParam("id") Long id) {
        customerContactService.deleteCustomerContact(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户接触信息")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-contact:delete')")
    public CommonResult<Boolean> deleteCustomerContactList(@RequestParam("ids") List<Long> ids) {
        customerContactService.deleteCustomerContactListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户接触信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-contact:query')")
    public CommonResult<CustomerContactRespVO> getCustomerContact(@RequestParam("id") Long id) {
        CustomerContactDO customerContact = customerContactService.getCustomerContact(id);
        return success(BeanUtils.toBean(customerContact, CustomerContactRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户接触信息分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-contact:query')")
    public CommonResult<PageResult<CustomerContactRespVO>> getCustomerContactPage(@Valid CustomerContactPageReqVO pageReqVO) {
        PageResult<CustomerContactDO> pageResult = customerContactService.getCustomerContactPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerContactRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户接触信息 Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-contact:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerContactExcel(@Valid CustomerContactPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerContactDO> list = customerContactService.getCustomerContactPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户接触信息.xls", "数据", CustomerContactRespVO.class,
                        BeanUtils.toBean(list, CustomerContactRespVO.class));
    }

}