package com.ynet.iplatform.module.aicrm.controller.admin.customerpoints;

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

import com.ynet.iplatform.module.aicrm.controller.admin.customerpoints.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerpoints.CustomerPointsDO;
import com.ynet.iplatform.module.aicrm.service.customerpoints.CustomerPointsService;

@Tag(name = "管理后台 - 客户积分信息")
@RestController
@RequestMapping("/aicrm/customer-points")
@Validated
public class CustomerPointsController {

    @Resource
    private CustomerPointsService customerPointsService;

    @PostMapping("/create")
    @Operation(summary = "创建客户积分信息")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-points:create')")
    public CommonResult<Long> createCustomerPoints(@Valid @RequestBody CustomerPointsSaveReqVO createReqVO) {
        return success(customerPointsService.createCustomerPoints(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户积分信息")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-points:update')")
    public CommonResult<Boolean> updateCustomerPoints(@Valid @RequestBody CustomerPointsSaveReqVO updateReqVO) {
        customerPointsService.updateCustomerPoints(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户积分信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-points:delete')")
    public CommonResult<Boolean> deleteCustomerPoints(@RequestParam("id") Long id) {
        customerPointsService.deleteCustomerPoints(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户积分信息")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-points:delete')")
    public CommonResult<Boolean> deleteCustomerPointsList(@RequestParam("ids") List<Long> ids) {
        customerPointsService.deleteCustomerPointsListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户积分信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-points:query')")
    public CommonResult<CustomerPointsRespVO> getCustomerPoints(@RequestParam("id") Long id) {
        CustomerPointsDO customerPoints = customerPointsService.getCustomerPoints(id);
        return success(BeanUtils.toBean(customerPoints, CustomerPointsRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户积分信息分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-points:query')")
    public CommonResult<PageResult<CustomerPointsRespVO>> getCustomerPointsPage(@Valid CustomerPointsPageReqVO pageReqVO) {
        PageResult<CustomerPointsDO> pageResult = customerPointsService.getCustomerPointsPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerPointsRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户积分信息 Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-points:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerPointsExcel(@Valid CustomerPointsPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerPointsDO> list = customerPointsService.getCustomerPointsPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户积分信息.xls", "数据", CustomerPointsRespVO.class,
                        BeanUtils.toBean(list, CustomerPointsRespVO.class));
    }

}