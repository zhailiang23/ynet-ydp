package cn.iocoder.yudao.module.aicrm.controller.admin.customerassignment;

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

import cn.iocoder.yudao.module.aicrm.controller.admin.customerassignment.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerassignment.CustomerAssignmentDO;
import cn.iocoder.yudao.module.aicrm.service.customerassignment.CustomerAssignmentService;
import cn.iocoder.yudao.module.system.service.dept.DeptService;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import cn.iocoder.yudao.module.system.dal.dataobject.dept.DeptDO;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;

@Tag(name = "管理后台 - 客户归属关系表（零售+对公共用，支持主协办模式）")
@RestController
@RequestMapping("/aicrm/customer-assignment")
@Validated
public class CustomerAssignmentController {

    @Resource
    private CustomerAssignmentService customerAssignmentService;

    @Resource
    private DeptService deptService;

    @Resource
    private AdminUserService adminUserService;

    @PostMapping("/create")
    @Operation(summary = "创建客户归属关系表（零售+对公共用，支持主协办模式）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-assignment:create')")
    public CommonResult<Long> createCustomerAssignment(@Valid @RequestBody CustomerAssignmentSaveReqVO createReqVO) {
        return success(customerAssignmentService.createCustomerAssignment(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户归属关系表（零售+对公共用，支持主协办模式）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-assignment:update')")
    public CommonResult<Boolean> updateCustomerAssignment(@Valid @RequestBody CustomerAssignmentSaveReqVO updateReqVO) {
        customerAssignmentService.updateCustomerAssignment(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户归属关系表（零售+对公共用，支持主协办模式）")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-assignment:delete')")
    public CommonResult<Boolean> deleteCustomerAssignment(@RequestParam("id") Long id) {
        customerAssignmentService.deleteCustomerAssignment(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户归属关系表（零售+对公共用，支持主协办模式）")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-assignment:delete')")
    public CommonResult<Boolean> deleteCustomerAssignmentList(@RequestParam("ids") List<Long> ids) {
        customerAssignmentService.deleteCustomerAssignmentListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户归属关系表（零售+对公共用，支持主协办模式）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-assignment:query')")
    public CommonResult<CustomerAssignmentRespVO> getCustomerAssignment(@RequestParam("id") Long id) {
        CustomerAssignmentDO customerAssignment = customerAssignmentService.getCustomerAssignment(id);
        return success(BeanUtils.toBean(customerAssignment, CustomerAssignmentRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户归属关系表（零售+对公共用，支持主协办模式）分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-assignment:query')")
    public CommonResult<PageResult<CustomerAssignmentRespVO>> getCustomerAssignmentPage(@Valid CustomerAssignmentPageReqVO pageReqVO) {
        PageResult<CustomerAssignmentDO> pageResult = customerAssignmentService.getCustomerAssignmentPage(pageReqVO);
        PageResult<CustomerAssignmentRespVO> result = BeanUtils.toBean(pageResult, CustomerAssignmentRespVO.class);

        // 填充部门名称和用户名称
        result.getList().forEach(vo -> {
            if (vo.getDeptId() != null) {
                DeptDO dept = deptService.getDept(vo.getDeptId());
                vo.setDeptName(dept != null ? dept.getName() : null);
            }
            if (vo.getUserId() != null) {
                AdminUserDO user = adminUserService.getUser(vo.getUserId());
                vo.setUserName(user != null ? user.getNickname() : null);
            }
        });

        return success(result);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户归属关系表（零售+对公共用，支持主协办模式） Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-assignment:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerAssignmentExcel(@Valid CustomerAssignmentPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerAssignmentDO> list = customerAssignmentService.getCustomerAssignmentPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户归属关系表（零售+对公共用，支持主协办模式）.xls", "数据", CustomerAssignmentRespVO.class,
                        BeanUtils.toBean(list, CustomerAssignmentRespVO.class));
    }

    @PostMapping("/assign-customers")
    @Operation(summary = "分配客户")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-assignment:create')")
    @ApiAccessLog(operateType = CREATE)
    public CommonResult<Boolean> assignCustomers(@Valid @RequestBody AssignCustomerReqVO reqVO) {
        customerAssignmentService.assignCustomers(cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId(), reqVO);
        return success(true);
    }

    @PostMapping("/reclaim-customers")
    @Operation(summary = "回收客户")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-assignment:delete')")
    @ApiAccessLog(operateType = DELETE)
    public CommonResult<Boolean> reclaimCustomers(@Valid @RequestBody ReclaimCustomerReqVO reqVO) {
        customerAssignmentService.reclaimCustomers(cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId(), reqVO);
        return success(true);
    }

    @PostMapping("/claim-customers")
    @Operation(summary = "快速认领客户（未分配客户）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-assignment:create')")
    @ApiAccessLog(operateType = CREATE)
    public CommonResult<Boolean> claimCustomers(@Valid @RequestBody ClaimCustomerReqVO reqVO) {
        Long userId = cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId();
        // 获取当前用户的部门ID
        AdminUserDO user = adminUserService.getUser(userId);
        if (user == null || user.getDeptId() == null) {
            return CommonResult.error(400, "当前用户未设置部门，无法认领客户");
        }
        customerAssignmentService.claimCustomers(userId, user.getDeptId(), reqVO);
        return success(true);
    }

    @GetMapping("/my-customer-page")
    @Operation(summary = "获取我的客户分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-assignment:query')")
    public CommonResult<PageResult<MyCustomerRespVO>> getMyCustomerPage(@Valid MyCustomerPageReqVO pageReqVO) {
        Long userId = cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId();
        PageResult<MyCustomerRespVO> pageResult = customerAssignmentService.getMyCustomerPage(userId, pageReqVO);
        return success(pageResult);
    }

    @PostMapping("/transfer-customers")
    @Operation(summary = "移交客户")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-assignment:update')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> transferCustomers(@Valid @RequestBody TransferCustomerReqVO reqVO) {
        Long userId = cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId();
        customerAssignmentService.transferCustomers(userId, reqVO);
        return success(true);
    }

    @PostMapping("/delegate-customers")
    @Operation(summary = "托管客户")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-assignment:update')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> delegateCustomers(@Valid @RequestBody DelegateCustomerReqVO reqVO) {
        Long userId = cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId();
        customerAssignmentService.delegateCustomers(userId, reqVO);
        return success(true);
    }

}