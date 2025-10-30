package cn.iocoder.yudao.module.aicrm.controller.admin.customercontribution;

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

import cn.iocoder.yudao.module.aicrm.controller.admin.customercontribution.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customercontribution.CustomerContributionDO;
import cn.iocoder.yudao.module.aicrm.service.customercontribution.CustomerContributionService;

@Tag(name = "管理后台 - 客户贡献度信息表（与客户主表1对1关系）")
@RestController
@RequestMapping("/aicrm/customer-contribution")
@Validated
public class CustomerContributionController {

    @Resource
    private CustomerContributionService customerContributionService;

    @PostMapping("/create")
    @Operation(summary = "创建客户贡献度信息表（与客户主表1对1关系）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-contribution:create')")
    public CommonResult<Long> createCustomerContribution(@Valid @RequestBody CustomerContributionSaveReqVO createReqVO) {
        return success(customerContributionService.createCustomerContribution(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户贡献度信息表（与客户主表1对1关系）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-contribution:update')")
    public CommonResult<Boolean> updateCustomerContribution(@Valid @RequestBody CustomerContributionSaveReqVO updateReqVO) {
        customerContributionService.updateCustomerContribution(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户贡献度信息表（与客户主表1对1关系）")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-contribution:delete')")
    public CommonResult<Boolean> deleteCustomerContribution(@RequestParam("id") Long id) {
        customerContributionService.deleteCustomerContribution(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户贡献度信息表（与客户主表1对1关系）")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-contribution:delete')")
    public CommonResult<Boolean> deleteCustomerContributionList(@RequestParam("ids") List<Long> ids) {
        customerContributionService.deleteCustomerContributionListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户贡献度信息表（与客户主表1对1关系）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-contribution:query')")
    public CommonResult<CustomerContributionRespVO> getCustomerContribution(@RequestParam("id") Long id) {
        CustomerContributionDO customerContribution = customerContributionService.getCustomerContribution(id);
        return success(BeanUtils.toBean(customerContribution, CustomerContributionRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户贡献度信息表（与客户主表1对1关系）分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-contribution:query')")
    public CommonResult<PageResult<CustomerContributionRespVO>> getCustomerContributionPage(@Valid CustomerContributionPageReqVO pageReqVO) {
        PageResult<CustomerContributionDO> pageResult = customerContributionService.getCustomerContributionPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerContributionRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户贡献度信息表（与客户主表1对1关系） Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-contribution:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerContributionExcel(@Valid CustomerContributionPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerContributionDO> list = customerContributionService.getCustomerContributionPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户贡献度信息表（与客户主表1对1关系）.xls", "数据", CustomerContributionRespVO.class,
                        BeanUtils.toBean(list, CustomerContributionRespVO.class));
    }

}