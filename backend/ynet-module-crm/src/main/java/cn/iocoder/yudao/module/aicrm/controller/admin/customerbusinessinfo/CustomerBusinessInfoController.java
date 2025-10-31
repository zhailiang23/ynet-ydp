package cn.iocoder.yudao.module.aicrm.controller.admin.customerbusinessinfo;

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

import cn.iocoder.yudao.module.aicrm.controller.admin.customerbusinessinfo.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerbusinessinfo.CustomerBusinessInfoDO;
import cn.iocoder.yudao.module.aicrm.service.customerbusinessinfo.CustomerBusinessInfoService;

@Tag(name = "管理后台 - 客户经营信息表（精简版，只包含经营相关核心字段）")
@RestController
@RequestMapping("/aicrm/customer-business-info")
@Validated
public class CustomerBusinessInfoController {

    @Resource
    private CustomerBusinessInfoService customerBusinessInfoService;

    @PostMapping("/create")
    @Operation(summary = "创建客户经营信息表（精简版，只包含经营相关核心字段）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-business-info:create')")
    public CommonResult<Long> createCustomerBusinessInfo(@Valid @RequestBody CustomerBusinessInfoSaveReqVO createReqVO) {
        return success(customerBusinessInfoService.createCustomerBusinessInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户经营信息表（精简版，只包含经营相关核心字段）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-business-info:update')")
    public CommonResult<Boolean> updateCustomerBusinessInfo(@Valid @RequestBody CustomerBusinessInfoSaveReqVO updateReqVO) {
        customerBusinessInfoService.updateCustomerBusinessInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户经营信息表（精简版，只包含经营相关核心字段）")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-business-info:delete')")
    public CommonResult<Boolean> deleteCustomerBusinessInfo(@RequestParam("id") Long id) {
        customerBusinessInfoService.deleteCustomerBusinessInfo(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户经营信息表（精简版，只包含经营相关核心字段）")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-business-info:delete')")
    public CommonResult<Boolean> deleteCustomerBusinessInfoList(@RequestParam("ids") List<Long> ids) {
        customerBusinessInfoService.deleteCustomerBusinessInfoListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户经营信息表（精简版，只包含经营相关核心字段）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-business-info:query')")
    public CommonResult<CustomerBusinessInfoRespVO> getCustomerBusinessInfo(@RequestParam("id") Long id) {
        CustomerBusinessInfoDO customerBusinessInfo = customerBusinessInfoService.getCustomerBusinessInfo(id);
        return success(BeanUtils.toBean(customerBusinessInfo, CustomerBusinessInfoRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户经营信息表（精简版，只包含经营相关核心字段）分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-business-info:query')")
    public CommonResult<PageResult<CustomerBusinessInfoRespVO>> getCustomerBusinessInfoPage(@Valid CustomerBusinessInfoPageReqVO pageReqVO) {
        PageResult<CustomerBusinessInfoDO> pageResult = customerBusinessInfoService.getCustomerBusinessInfoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerBusinessInfoRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户经营信息表（精简版，只包含经营相关核心字段） Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-business-info:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerBusinessInfoExcel(@Valid CustomerBusinessInfoPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerBusinessInfoDO> list = customerBusinessInfoService.getCustomerBusinessInfoPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户经营信息表（精简版，只包含经营相关核心字段）.xls", "数据", CustomerBusinessInfoRespVO.class,
                        BeanUtils.toBean(list, CustomerBusinessInfoRespVO.class));
    }

}