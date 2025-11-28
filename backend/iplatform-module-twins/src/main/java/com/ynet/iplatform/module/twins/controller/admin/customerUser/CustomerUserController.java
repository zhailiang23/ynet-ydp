package com.ynet.iplatform.module.twins.controller.admin.customerUser;

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

import com.ynet.iplatform.module.twins.controller.admin.customerUser.vo.*;
import com.ynet.iplatform.module.twins.dal.dataobject.customerUser.CustomerUserDO;
import com.ynet.iplatform.module.twins.service.customerUser.CustomerUserService;

@Tag(name = "管理后台 - 接入用户")
@RestController
@RequestMapping("/twins/customer-user")
@Validated
public class CustomerUserController {

    @Resource
    private CustomerUserService customerUserService;

    @PostMapping("/create")
    @Operation(summary = "创建接入用户")
    @PreAuthorize("@ss.hasPermission('twins:customer-user:create')")
    public CommonResult<Integer> createCustomerUser(@Valid @RequestBody CustomerUserSaveReqVO createReqVO) {
        return success(customerUserService.createCustomerUser(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新接入用户")
    @PreAuthorize("@ss.hasPermission('twins:customer-user:update')")
    public CommonResult<Boolean> updateCustomerUser(@Valid @RequestBody CustomerUserSaveReqVO updateReqVO) {
        customerUserService.updateCustomerUser(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除接入用户")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('twins:customer-user:delete')")
    public CommonResult<Boolean> deleteCustomerUser(@RequestParam("id") Integer id) {
        customerUserService.deleteCustomerUser(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除接入用户")
                @PreAuthorize("@ss.hasPermission('twins:customer-user:delete')")
    public CommonResult<Boolean> deleteCustomerUserList(@RequestParam("ids") List<Integer> ids) {
        customerUserService.deleteCustomerUserListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得接入用户")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('twins:customer-user:query')")
    public CommonResult<CustomerUserRespVO> getCustomerUser(@RequestParam("id") Integer id) {
        CustomerUserDO customerUser = customerUserService.getCustomerUser(id);
        return success(BeanUtils.toBean(customerUser, CustomerUserRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得接入用户分页")
    @PreAuthorize("@ss.hasPermission('twins:customer-user:query')")
    public CommonResult<PageResult<CustomerUserRespVO>> getCustomerUserPage(@Valid CustomerUserPageReqVO pageReqVO) {
        PageResult<CustomerUserDO> pageResult = customerUserService.getCustomerUserPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerUserRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出接入用户 Excel")
    @PreAuthorize("@ss.hasPermission('twins:customer-user:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerUserExcel(@Valid CustomerUserPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerUserDO> list = customerUserService.getCustomerUserPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "接入用户.xls", "数据", CustomerUserRespVO.class,
                        BeanUtils.toBean(list, CustomerUserRespVO.class));
    }

}