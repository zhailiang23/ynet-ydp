package cn.iocoder.yudao.module.aicrm.controller.admin.customeraccountmetal;

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

import cn.iocoder.yudao.module.aicrm.controller.admin.customeraccountmetal.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customeraccountmetal.CustomerAccountMetalDO;
import cn.iocoder.yudao.module.aicrm.service.customeraccountmetal.CustomerAccountMetalService;

@Tag(name = "管理后台 - 客户贵金属账户信息表（零售+对公共用）")
@RestController
@RequestMapping("/aicrm/customer-account-metal")
@Validated
public class CustomerAccountMetalController {

    @Resource
    private CustomerAccountMetalService customerAccountMetalService;

    @PostMapping("/create")
    @Operation(summary = "创建客户贵金属账户信息表（零售+对公共用）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-account-metal:create')")
    public CommonResult<Long> createCustomerAccountMetal(@Valid @RequestBody CustomerAccountMetalSaveReqVO createReqVO) {
        return success(customerAccountMetalService.createCustomerAccountMetal(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户贵金属账户信息表（零售+对公共用）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-account-metal:update')")
    public CommonResult<Boolean> updateCustomerAccountMetal(@Valid @RequestBody CustomerAccountMetalSaveReqVO updateReqVO) {
        customerAccountMetalService.updateCustomerAccountMetal(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户贵金属账户信息表（零售+对公共用）")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-account-metal:delete')")
    public CommonResult<Boolean> deleteCustomerAccountMetal(@RequestParam("id") Long id) {
        customerAccountMetalService.deleteCustomerAccountMetal(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户贵金属账户信息表（零售+对公共用）")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-account-metal:delete')")
    public CommonResult<Boolean> deleteCustomerAccountMetalList(@RequestParam("ids") List<Long> ids) {
        customerAccountMetalService.deleteCustomerAccountMetalListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户贵金属账户信息表（零售+对公共用）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-account-metal:query')")
    public CommonResult<CustomerAccountMetalRespVO> getCustomerAccountMetal(@RequestParam("id") Long id) {
        CustomerAccountMetalDO customerAccountMetal = customerAccountMetalService.getCustomerAccountMetal(id);
        return success(BeanUtils.toBean(customerAccountMetal, CustomerAccountMetalRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户贵金属账户信息表（零售+对公共用）分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-account-metal:query')")
    public CommonResult<PageResult<CustomerAccountMetalRespVO>> getCustomerAccountMetalPage(@Valid CustomerAccountMetalPageReqVO pageReqVO) {
        PageResult<CustomerAccountMetalDO> pageResult = customerAccountMetalService.getCustomerAccountMetalPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerAccountMetalRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户贵金属账户信息表（零售+对公共用） Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-account-metal:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerAccountMetalExcel(@Valid CustomerAccountMetalPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerAccountMetalDO> list = customerAccountMetalService.getCustomerAccountMetalPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户贵金属账户信息表（零售+对公共用）.xls", "数据", CustomerAccountMetalRespVO.class,
                        BeanUtils.toBean(list, CustomerAccountMetalRespVO.class));
    }

}