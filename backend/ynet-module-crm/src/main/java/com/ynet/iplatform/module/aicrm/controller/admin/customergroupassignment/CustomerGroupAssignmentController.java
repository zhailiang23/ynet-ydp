package com.ynet.iplatform.module.aicrm.controller.admin.customergroupassignment;

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

import com.ynet.iplatform.module.aicrm.controller.admin.customergroupassignment.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customergroupassignment.CustomerGroupAssignmentDO;
import com.ynet.iplatform.module.aicrm.service.customergroupassignment.CustomerGroupAssignmentService;

@Tag(name = "管理后台 - 客户归属客群关系表（只记录关系，客群信息通过关联查询）")
@RestController
@RequestMapping("/aicrm/customer-group-assignment")
@Validated
public class CustomerGroupAssignmentController {

    @Resource
    private CustomerGroupAssignmentService customerGroupAssignmentService;

    @PostMapping("/create")
    @Operation(summary = "创建客户归属客群关系表（只记录关系，客群信息通过关联查询）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-group-assignment:create')")
    public CommonResult<Long> createCustomerGroupAssignment(@Valid @RequestBody CustomerGroupAssignmentSaveReqVO createReqVO) {
        return success(customerGroupAssignmentService.createCustomerGroupAssignment(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户归属客群关系表（只记录关系，客群信息通过关联查询）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-group-assignment:update')")
    public CommonResult<Boolean> updateCustomerGroupAssignment(@Valid @RequestBody CustomerGroupAssignmentSaveReqVO updateReqVO) {
        customerGroupAssignmentService.updateCustomerGroupAssignment(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户归属客群关系表（只记录关系，客群信息通过关联查询）")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-group-assignment:delete')")
    public CommonResult<Boolean> deleteCustomerGroupAssignment(@RequestParam("id") Long id) {
        customerGroupAssignmentService.deleteCustomerGroupAssignment(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户归属客群关系表（只记录关系，客群信息通过关联查询）")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-group-assignment:delete')")
    public CommonResult<Boolean> deleteCustomerGroupAssignmentList(@RequestParam("ids") List<Long> ids) {
        customerGroupAssignmentService.deleteCustomerGroupAssignmentListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户归属客群关系表（只记录关系，客群信息通过关联查询）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-group-assignment:query')")
    public CommonResult<CustomerGroupAssignmentRespVO> getCustomerGroupAssignment(@RequestParam("id") Long id) {
        CustomerGroupAssignmentDO customerGroupAssignment = customerGroupAssignmentService.getCustomerGroupAssignment(id);
        return success(BeanUtils.toBean(customerGroupAssignment, CustomerGroupAssignmentRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户归属客群关系分页（联表查询）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-group-assignment:query')")
    public CommonResult<PageResult<CustomerGroupAssignmentRespVO>> getCustomerGroupAssignmentPage(@Valid CustomerGroupAssignmentPageReqVO pageReqVO) {
        // 使用联表查询方法,返回包含客户群信息、用户名称、部门名称的完整数据
        PageResult<CustomerGroupAssignmentRespVO> pageResult = customerGroupAssignmentService.getCustomerGroupAssignmentPageWithJoin(pageReqVO);
        return success(pageResult);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户归属客群关系表（只记录关系，客群信息通过关联查询） Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-group-assignment:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerGroupAssignmentExcel(@Valid CustomerGroupAssignmentPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerGroupAssignmentDO> list = customerGroupAssignmentService.getCustomerGroupAssignmentPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户归属客群关系表（只记录关系，客群信息通过关联查询）.xls", "数据", CustomerGroupAssignmentRespVO.class,
                        BeanUtils.toBean(list, CustomerGroupAssignmentRespVO.class));
    }

}