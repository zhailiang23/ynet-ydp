package com.ynet.iplatform.module.aicrm.controller.admin.customerworkinfo;

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

import com.ynet.iplatform.module.aicrm.controller.admin.customerworkinfo.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerworkinfo.CustomerWorkInfoDO;
import com.ynet.iplatform.module.aicrm.service.customerworkinfo.CustomerWorkInfoService;

@Tag(name = "管理后台 - 客户工作信息表（精简版，只包含工作相关核心字段）")
@RestController
@RequestMapping("/aicrm/customer-work-info")
@Validated
public class CustomerWorkInfoController {

    @Resource
    private CustomerWorkInfoService customerWorkInfoService;

    @PostMapping("/create")
    @Operation(summary = "创建客户工作信息表（精简版，只包含工作相关核心字段）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-work-info:create')")
    public CommonResult<Long> createCustomerWorkInfo(@Valid @RequestBody CustomerWorkInfoSaveReqVO createReqVO) {
        return success(customerWorkInfoService.createCustomerWorkInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户工作信息表（精简版，只包含工作相关核心字段）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-work-info:update')")
    public CommonResult<Boolean> updateCustomerWorkInfo(@Valid @RequestBody CustomerWorkInfoSaveReqVO updateReqVO) {
        customerWorkInfoService.updateCustomerWorkInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户工作信息表（精简版，只包含工作相关核心字段）")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-work-info:delete')")
    public CommonResult<Boolean> deleteCustomerWorkInfo(@RequestParam("id") Long id) {
        customerWorkInfoService.deleteCustomerWorkInfo(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户工作信息表（精简版，只包含工作相关核心字段）")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-work-info:delete')")
    public CommonResult<Boolean> deleteCustomerWorkInfoList(@RequestParam("ids") List<Long> ids) {
        customerWorkInfoService.deleteCustomerWorkInfoListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户工作信息表（精简版，只包含工作相关核心字段）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-work-info:query')")
    public CommonResult<CustomerWorkInfoRespVO> getCustomerWorkInfo(@RequestParam("id") Long id) {
        CustomerWorkInfoDO customerWorkInfo = customerWorkInfoService.getCustomerWorkInfo(id);
        return success(BeanUtils.toBean(customerWorkInfo, CustomerWorkInfoRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户工作信息表（精简版，只包含工作相关核心字段）分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-work-info:query')")
    public CommonResult<PageResult<CustomerWorkInfoRespVO>> getCustomerWorkInfoPage(@Valid CustomerWorkInfoPageReqVO pageReqVO) {
        PageResult<CustomerWorkInfoDO> pageResult = customerWorkInfoService.getCustomerWorkInfoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerWorkInfoRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户工作信息表（精简版，只包含工作相关核心字段） Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-work-info:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerWorkInfoExcel(@Valid CustomerWorkInfoPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerWorkInfoDO> list = customerWorkInfoService.getCustomerWorkInfoPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户工作信息表（精简版，只包含工作相关核心字段）.xls", "数据", CustomerWorkInfoRespVO.class,
                        BeanUtils.toBean(list, CustomerWorkInfoRespVO.class));
    }

}