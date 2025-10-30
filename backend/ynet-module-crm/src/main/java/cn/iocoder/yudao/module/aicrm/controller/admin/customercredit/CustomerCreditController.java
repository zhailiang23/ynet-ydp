package cn.iocoder.yudao.module.aicrm.controller.admin.customercredit;

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

import cn.iocoder.yudao.module.aicrm.controller.admin.customercredit.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customercredit.CustomerCreditDO;
import cn.iocoder.yudao.module.aicrm.service.customercredit.CustomerCreditService;

@Tag(name = "管理后台 - 客户授信信息表（零售+对公共用）")
@RestController
@RequestMapping("/aicrm/customer-credit")
@Validated
public class CustomerCreditController {

    @Resource
    private CustomerCreditService customerCreditService;

    @PostMapping("/create")
    @Operation(summary = "创建客户授信信息表（零售+对公共用）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-credit:create')")
    public CommonResult<Long> createCustomerCredit(@Valid @RequestBody CustomerCreditSaveReqVO createReqVO) {
        return success(customerCreditService.createCustomerCredit(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户授信信息表（零售+对公共用）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-credit:update')")
    public CommonResult<Boolean> updateCustomerCredit(@Valid @RequestBody CustomerCreditSaveReqVO updateReqVO) {
        customerCreditService.updateCustomerCredit(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户授信信息表（零售+对公共用）")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-credit:delete')")
    public CommonResult<Boolean> deleteCustomerCredit(@RequestParam("id") Long id) {
        customerCreditService.deleteCustomerCredit(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户授信信息表（零售+对公共用）")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-credit:delete')")
    public CommonResult<Boolean> deleteCustomerCreditList(@RequestParam("ids") List<Long> ids) {
        customerCreditService.deleteCustomerCreditListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户授信信息表（零售+对公共用）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-credit:query')")
    public CommonResult<CustomerCreditRespVO> getCustomerCredit(@RequestParam("id") Long id) {
        CustomerCreditDO customerCredit = customerCreditService.getCustomerCredit(id);
        return success(BeanUtils.toBean(customerCredit, CustomerCreditRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户授信信息表（零售+对公共用）分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-credit:query')")
    public CommonResult<PageResult<CustomerCreditRespVO>> getCustomerCreditPage(@Valid CustomerCreditPageReqVO pageReqVO) {
        PageResult<CustomerCreditDO> pageResult = customerCreditService.getCustomerCreditPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerCreditRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户授信信息表（零售+对公共用） Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-credit:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerCreditExcel(@Valid CustomerCreditPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerCreditDO> list = customerCreditService.getCustomerCreditPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户授信信息表（零售+对公共用）.xls", "数据", CustomerCreditRespVO.class,
                        BeanUtils.toBean(list, CustomerCreditRespVO.class));
    }

}