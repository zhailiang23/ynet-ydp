package cn.iocoder.yudao.module.aicrm.controller.admin.customeraccountwealth;

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

import cn.iocoder.yudao.module.aicrm.controller.admin.customeraccountwealth.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customeraccountwealth.CustomerAccountWealthDO;
import cn.iocoder.yudao.module.aicrm.service.customeraccountwealth.CustomerAccountWealthService;

@Tag(name = "管理后台 - 客户理财账户信息表（零售+对公共用）")
@RestController
@RequestMapping("/aicrm/customer-account-wealth")
@Validated
public class CustomerAccountWealthController {

    @Resource
    private CustomerAccountWealthService customerAccountWealthService;

    @PostMapping("/create")
    @Operation(summary = "创建客户理财账户信息表（零售+对公共用）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-account-wealth:create')")
    public CommonResult<Long> createCustomerAccountWealth(@Valid @RequestBody CustomerAccountWealthSaveReqVO createReqVO) {
        return success(customerAccountWealthService.createCustomerAccountWealth(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户理财账户信息表（零售+对公共用）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-account-wealth:update')")
    public CommonResult<Boolean> updateCustomerAccountWealth(@Valid @RequestBody CustomerAccountWealthSaveReqVO updateReqVO) {
        customerAccountWealthService.updateCustomerAccountWealth(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户理财账户信息表（零售+对公共用）")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-account-wealth:delete')")
    public CommonResult<Boolean> deleteCustomerAccountWealth(@RequestParam("id") Long id) {
        customerAccountWealthService.deleteCustomerAccountWealth(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户理财账户信息表（零售+对公共用）")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-account-wealth:delete')")
    public CommonResult<Boolean> deleteCustomerAccountWealthList(@RequestParam("ids") List<Long> ids) {
        customerAccountWealthService.deleteCustomerAccountWealthListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户理财账户信息表（零售+对公共用）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-account-wealth:query')")
    public CommonResult<CustomerAccountWealthRespVO> getCustomerAccountWealth(@RequestParam("id") Long id) {
        CustomerAccountWealthDO customerAccountWealth = customerAccountWealthService.getCustomerAccountWealth(id);
        return success(BeanUtils.toBean(customerAccountWealth, CustomerAccountWealthRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户理财账户信息表（零售+对公共用）分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-account-wealth:query')")
    public CommonResult<PageResult<CustomerAccountWealthRespVO>> getCustomerAccountWealthPage(@Valid CustomerAccountWealthPageReqVO pageReqVO) {
        PageResult<CustomerAccountWealthDO> pageResult = customerAccountWealthService.getCustomerAccountWealthPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerAccountWealthRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户理财账户信息表（零售+对公共用） Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-account-wealth:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerAccountWealthExcel(@Valid CustomerAccountWealthPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerAccountWealthDO> list = customerAccountWealthService.getCustomerAccountWealthPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户理财账户信息表（零售+对公共用）.xls", "数据", CustomerAccountWealthRespVO.class,
                        BeanUtils.toBean(list, CustomerAccountWealthRespVO.class));
    }

}