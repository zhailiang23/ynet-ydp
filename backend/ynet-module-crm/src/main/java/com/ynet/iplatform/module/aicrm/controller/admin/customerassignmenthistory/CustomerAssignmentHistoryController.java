package com.ynet.iplatform.module.aicrm.controller.admin.customerassignmenthistory;

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

import com.ynet.iplatform.module.aicrm.controller.admin.customerassignmenthistory.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerassignmenthistory.CustomerAssignmentHistoryDO;
import com.ynet.iplatform.module.aicrm.service.customerassignmenthistory.CustomerAssignmentHistoryService;
import com.ynet.iplatform.module.system.service.dept.DeptService;
import com.ynet.iplatform.module.system.service.user.AdminUserService;
import com.ynet.iplatform.module.system.dal.dataobject.dept.DeptDO;
import com.ynet.iplatform.module.system.dal.dataobject.user.AdminUserDO;

@Tag(name = "管理后台 - 客户归属调整历史表（零售+对公共用，记录所有归属变更历史）")
@RestController
@RequestMapping("/aicrm/customer-assignment-history")
@Validated
public class CustomerAssignmentHistoryController {

    @Resource
    private CustomerAssignmentHistoryService customerAssignmentHistoryService;

    @Resource
    private DeptService deptService;

    @Resource
    private AdminUserService adminUserService;

    @PostMapping("/create")
    @Operation(summary = "创建客户归属调整历史表（零售+对公共用，记录所有归属变更历史）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-assignment-history:create')")
    public CommonResult<Long> createCustomerAssignmentHistory(@Valid @RequestBody CustomerAssignmentHistorySaveReqVO createReqVO) {
        return success(customerAssignmentHistoryService.createCustomerAssignmentHistory(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户归属调整历史表（零售+对公共用，记录所有归属变更历史）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-assignment-history:update')")
    public CommonResult<Boolean> updateCustomerAssignmentHistory(@Valid @RequestBody CustomerAssignmentHistorySaveReqVO updateReqVO) {
        customerAssignmentHistoryService.updateCustomerAssignmentHistory(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户归属调整历史表（零售+对公共用，记录所有归属变更历史）")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-assignment-history:delete')")
    public CommonResult<Boolean> deleteCustomerAssignmentHistory(@RequestParam("id") Long id) {
        customerAssignmentHistoryService.deleteCustomerAssignmentHistory(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户归属调整历史表（零售+对公共用，记录所有归属变更历史）")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-assignment-history:delete')")
    public CommonResult<Boolean> deleteCustomerAssignmentHistoryList(@RequestParam("ids") List<Long> ids) {
        customerAssignmentHistoryService.deleteCustomerAssignmentHistoryListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户归属调整历史表（零售+对公共用，记录所有归属变更历史）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-assignment-history:query')")
    public CommonResult<CustomerAssignmentHistoryRespVO> getCustomerAssignmentHistory(@RequestParam("id") Long id) {
        CustomerAssignmentHistoryDO customerAssignmentHistory = customerAssignmentHistoryService.getCustomerAssignmentHistory(id);
        return success(BeanUtils.toBean(customerAssignmentHistory, CustomerAssignmentHistoryRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户归属调整历史表（零售+对公共用，记录所有归属变更历史）分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-assignment-history:query')")
    public CommonResult<PageResult<CustomerAssignmentHistoryRespVO>> getCustomerAssignmentHistoryPage(@Valid CustomerAssignmentHistoryPageReqVO pageReqVO) {
        PageResult<CustomerAssignmentHistoryDO> pageResult = customerAssignmentHistoryService.getCustomerAssignmentHistoryPage(pageReqVO);
        PageResult<CustomerAssignmentHistoryRespVO> result = BeanUtils.toBean(pageResult, CustomerAssignmentHistoryRespVO.class);

        // 填充调整前后的部门名称和用户名称
        result.getList().forEach(vo -> {
            // 调整前部门名称
            if (vo.getBeforeDeptId() != null) {
                DeptDO beforeDept = deptService.getDept(vo.getBeforeDeptId());
                vo.setBeforeDeptName(beforeDept != null ? beforeDept.getName() : null);
            }
            // 调整前用户名称
            if (vo.getBeforeUserId() != null) {
                AdminUserDO beforeUser = adminUserService.getUser(vo.getBeforeUserId());
                vo.setBeforeUserName(beforeUser != null ? beforeUser.getNickname() : null);
            }
            // 调整后部门名称
            if (vo.getAfterDeptId() != null) {
                DeptDO afterDept = deptService.getDept(vo.getAfterDeptId());
                vo.setAfterDeptName(afterDept != null ? afterDept.getName() : null);
            }
            // 调整后用户名称
            if (vo.getAfterUserId() != null) {
                AdminUserDO afterUser = adminUserService.getUser(vo.getAfterUserId());
                vo.setAfterUserName(afterUser != null ? afterUser.getNickname() : null);
            }
        });

        return success(result);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户归属调整历史表（零售+对公共用，记录所有归属变更历史） Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-assignment-history:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerAssignmentHistoryExcel(@Valid CustomerAssignmentHistoryPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerAssignmentHistoryDO> list = customerAssignmentHistoryService.getCustomerAssignmentHistoryPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户归属调整历史表（零售+对公共用，记录所有归属变更历史）.xls", "数据", CustomerAssignmentHistoryRespVO.class,
                        BeanUtils.toBean(list, CustomerAssignmentHistoryRespVO.class));
    }

}