package cn.iocoder.yudao.module.aicrm.controller.admin.customerwork;

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

import cn.iocoder.yudao.module.aicrm.controller.admin.customerwork.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerwork.CustomerWorkDO;
import cn.iocoder.yudao.module.aicrm.service.customerwork.CustomerWorkService;

@Tag(name = "管理后台 - 客户工作或经营信息表")
@RestController
@RequestMapping("/aicrm/customer-work")
@Validated
public class CustomerWorkController {

    @Resource
    private CustomerWorkService customerWorkService;

    @PostMapping("/create")
    @Operation(summary = "创建客户工作或经营信息表")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-work:create')")
    public CommonResult<Long> createCustomerWork(@Valid @RequestBody CustomerWorkSaveReqVO createReqVO) {
        return success(customerWorkService.createCustomerWork(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户工作或经营信息表")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-work:update')")
    public CommonResult<Boolean> updateCustomerWork(@Valid @RequestBody CustomerWorkSaveReqVO updateReqVO) {
        customerWorkService.updateCustomerWork(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户工作或经营信息表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-work:delete')")
    public CommonResult<Boolean> deleteCustomerWork(@RequestParam("id") Long id) {
        customerWorkService.deleteCustomerWork(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户工作或经营信息表")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-work:delete')")
    public CommonResult<Boolean> deleteCustomerWorkList(@RequestParam("ids") List<Long> ids) {
        customerWorkService.deleteCustomerWorkListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户工作或经营信息表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-work:query')")
    public CommonResult<CustomerWorkRespVO> getCustomerWork(@RequestParam("id") Long id) {
        CustomerWorkDO customerWork = customerWorkService.getCustomerWork(id);
        return success(BeanUtils.toBean(customerWork, CustomerWorkRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户工作或经营信息表分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-work:query')")
    public CommonResult<PageResult<CustomerWorkRespVO>> getCustomerWorkPage(@Valid CustomerWorkPageReqVO pageReqVO) {
        PageResult<CustomerWorkDO> pageResult = customerWorkService.getCustomerWorkPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerWorkRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户工作或经营信息表 Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-work:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerWorkExcel(@Valid CustomerWorkPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerWorkDO> list = customerWorkService.getCustomerWorkPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户工作或经营信息表.xls", "数据", CustomerWorkRespVO.class,
                        BeanUtils.toBean(list, CustomerWorkRespVO.class));
    }

}