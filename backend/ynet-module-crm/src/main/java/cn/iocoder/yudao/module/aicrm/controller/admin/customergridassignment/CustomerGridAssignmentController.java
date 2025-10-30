package cn.iocoder.yudao.module.aicrm.controller.admin.customergridassignment;

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

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.aicrm.controller.admin.customergridassignment.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customergridassignment.CustomerGridAssignmentDO;
import cn.iocoder.yudao.module.aicrm.service.customergridassignment.CustomerGridAssignmentService;

@Tag(name = "管理后台 - 客户归属网格关系表（只记录关系，网格信息通过关联查询）")
@RestController
@RequestMapping("/aicrm/customer-grid-assignment")
@Validated
public class CustomerGridAssignmentController {

    @Resource
    private CustomerGridAssignmentService customerGridAssignmentService;

    @PostMapping("/create")
    @Operation(summary = "创建客户归属网格关系表（只记录关系，网格信息通过关联查询）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-grid-assignment:create')")
    public CommonResult<Long> createCustomerGridAssignment(@Valid @RequestBody CustomerGridAssignmentSaveReqVO createReqVO) {
        return success(customerGridAssignmentService.createCustomerGridAssignment(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户归属网格关系表（只记录关系，网格信息通过关联查询）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-grid-assignment:update')")
    public CommonResult<Boolean> updateCustomerGridAssignment(@Valid @RequestBody CustomerGridAssignmentSaveReqVO updateReqVO) {
        customerGridAssignmentService.updateCustomerGridAssignment(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户归属网格关系表（只记录关系，网格信息通过关联查询）")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-grid-assignment:delete')")
    public CommonResult<Boolean> deleteCustomerGridAssignment(@RequestParam("id") Long id) {
        customerGridAssignmentService.deleteCustomerGridAssignment(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户归属网格关系表（只记录关系，网格信息通过关联查询）")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-grid-assignment:delete')")
    public CommonResult<Boolean> deleteCustomerGridAssignmentList(@RequestParam("ids") List<Long> ids) {
        customerGridAssignmentService.deleteCustomerGridAssignmentListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户归属网格关系表（只记录关系，网格信息通过关联查询）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-grid-assignment:query')")
    public CommonResult<CustomerGridAssignmentRespVO> getCustomerGridAssignment(@RequestParam("id") Long id) {
        CustomerGridAssignmentDO customerGridAssignment = customerGridAssignmentService.getCustomerGridAssignment(id);
        return success(BeanUtils.toBean(customerGridAssignment, CustomerGridAssignmentRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户归属网格关系表（只记录关系，网格信息通过关联查询）分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-grid-assignment:query')")
    public CommonResult<PageResult<CustomerGridAssignmentRespVO>> getCustomerGridAssignmentPage(@Valid CustomerGridAssignmentPageReqVO pageReqVO) {
        // 使用联表查询，避免 N+1 查询
        PageResult<CustomerGridAssignmentRespVO> pageResult = customerGridAssignmentService.getCustomerGridAssignmentPageWithJoin(pageReqVO);
        return success(pageResult);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户归属网格关系表（只记录关系，网格信息通过关联查询） Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-grid-assignment:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerGridAssignmentExcel(@Valid CustomerGridAssignmentPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerGridAssignmentDO> list = customerGridAssignmentService.getCustomerGridAssignmentPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户归属网格关系表（只记录关系，网格信息通过关联查询）.xls", "数据", CustomerGridAssignmentRespVO.class,
                        BeanUtils.toBean(list, CustomerGridAssignmentRespVO.class));
    }

}