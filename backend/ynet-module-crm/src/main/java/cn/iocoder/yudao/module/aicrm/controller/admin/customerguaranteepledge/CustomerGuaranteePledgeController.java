package cn.iocoder.yudao.module.aicrm.controller.admin.customerguaranteepledge;

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

import cn.iocoder.yudao.module.aicrm.controller.admin.customerguaranteepledge.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerguaranteepledge.CustomerGuaranteePledgeDO;
import cn.iocoder.yudao.module.aicrm.service.customerguaranteepledge.CustomerGuaranteePledgeService;

@Tag(name = "管理后台 - 客户质押物信息表（零售+对公共用）")
@RestController
@RequestMapping("/aicrm/customer-guarantee-pledge")
@Validated
public class CustomerGuaranteePledgeController {

    @Resource
    private CustomerGuaranteePledgeService customerGuaranteePledgeService;

    @PostMapping("/create")
    @Operation(summary = "创建客户质押物信息表（零售+对公共用）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-guarantee-pledge:create')")
    public CommonResult<Long> createCustomerGuaranteePledge(@Valid @RequestBody CustomerGuaranteePledgeSaveReqVO createReqVO) {
        return success(customerGuaranteePledgeService.createCustomerGuaranteePledge(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户质押物信息表（零售+对公共用）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-guarantee-pledge:update')")
    public CommonResult<Boolean> updateCustomerGuaranteePledge(@Valid @RequestBody CustomerGuaranteePledgeSaveReqVO updateReqVO) {
        customerGuaranteePledgeService.updateCustomerGuaranteePledge(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户质押物信息表（零售+对公共用）")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-guarantee-pledge:delete')")
    public CommonResult<Boolean> deleteCustomerGuaranteePledge(@RequestParam("id") Long id) {
        customerGuaranteePledgeService.deleteCustomerGuaranteePledge(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户质押物信息表（零售+对公共用）")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-guarantee-pledge:delete')")
    public CommonResult<Boolean> deleteCustomerGuaranteePledgeList(@RequestParam("ids") List<Long> ids) {
        customerGuaranteePledgeService.deleteCustomerGuaranteePledgeListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户质押物信息表（零售+对公共用）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-guarantee-pledge:query')")
    public CommonResult<CustomerGuaranteePledgeRespVO> getCustomerGuaranteePledge(@RequestParam("id") Long id) {
        CustomerGuaranteePledgeDO customerGuaranteePledge = customerGuaranteePledgeService.getCustomerGuaranteePledge(id);
        return success(BeanUtils.toBean(customerGuaranteePledge, CustomerGuaranteePledgeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户质押物信息表（零售+对公共用）分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-guarantee-pledge:query')")
    public CommonResult<PageResult<CustomerGuaranteePledgeRespVO>> getCustomerGuaranteePledgePage(@Valid CustomerGuaranteePledgePageReqVO pageReqVO) {
        PageResult<CustomerGuaranteePledgeDO> pageResult = customerGuaranteePledgeService.getCustomerGuaranteePledgePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerGuaranteePledgeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户质押物信息表（零售+对公共用） Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-guarantee-pledge:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerGuaranteePledgeExcel(@Valid CustomerGuaranteePledgePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerGuaranteePledgeDO> list = customerGuaranteePledgeService.getCustomerGuaranteePledgePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户质押物信息表（零售+对公共用）.xls", "数据", CustomerGuaranteePledgeRespVO.class,
                        BeanUtils.toBean(list, CustomerGuaranteePledgeRespVO.class));
    }

}