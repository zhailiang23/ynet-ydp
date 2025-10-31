package cn.iocoder.yudao.module.aicrm.controller.admin.customerreturnapplication;

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

import cn.iocoder.yudao.module.aicrm.controller.admin.customerreturnapplication.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerreturnapplication.CustomerReturnApplicationDO;
import cn.iocoder.yudao.module.aicrm.service.customerreturnapplication.CustomerReturnApplicationService;

@Tag(name = "管理后台 - 客户退回申请")
@RestController
@RequestMapping("/aicrm/customer-return-application")
@Validated
public class CustomerReturnApplicationController {

    @Resource
    private CustomerReturnApplicationService customerReturnApplicationService;

    @PostMapping("/create")
    @Operation(summary = "创建客户退回申请")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-return-application:create')")
    public CommonResult<Long> createCustomerReturnApplication(@Valid @RequestBody CustomerReturnApplicationSaveReqVO createReqVO) {
        return success(customerReturnApplicationService.createCustomerReturnApplication(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户退回申请")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-return-application:update')")
    public CommonResult<Boolean> updateCustomerReturnApplication(@Valid @RequestBody CustomerReturnApplicationSaveReqVO updateReqVO) {
        customerReturnApplicationService.updateCustomerReturnApplication(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户退回申请")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-return-application:delete')")
    public CommonResult<Boolean> deleteCustomerReturnApplication(@RequestParam("id") Long id) {
        customerReturnApplicationService.deleteCustomerReturnApplication(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户退回申请")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-return-application:delete')")
    public CommonResult<Boolean> deleteCustomerReturnApplicationList(@RequestParam("ids") List<Long> ids) {
        customerReturnApplicationService.deleteCustomerReturnApplicationListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户退回申请")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-return-application:query')")
    public CommonResult<CustomerReturnApplicationRespVO> getCustomerReturnApplication(@RequestParam("id") Long id) {
        CustomerReturnApplicationDO customerReturnApplication = customerReturnApplicationService.getCustomerReturnApplication(id);
        return success(BeanUtils.toBean(customerReturnApplication, CustomerReturnApplicationRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户退回申请分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-return-application:query')")
    public CommonResult<PageResult<CustomerReturnApplicationRespVO>> getCustomerReturnApplicationPage(@Valid CustomerReturnApplicationPageReqVO pageReqVO) {
        PageResult<CustomerReturnApplicationDO> pageResult = customerReturnApplicationService.getCustomerReturnApplicationPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerReturnApplicationRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户退回申请 Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-return-application:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerReturnApplicationExcel(@Valid CustomerReturnApplicationPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerReturnApplicationDO> list = customerReturnApplicationService.getCustomerReturnApplicationPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户退回申请.xls", "数据", CustomerReturnApplicationRespVO.class,
                        BeanUtils.toBean(list, CustomerReturnApplicationRespVO.class));
    }

}