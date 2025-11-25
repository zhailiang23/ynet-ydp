package com.ynet.iplatform.module.aicrm.controller.admin.customeridentity;

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

import com.ynet.iplatform.module.aicrm.controller.admin.customeridentity.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customeridentity.CustomerIdentityDO;
import com.ynet.iplatform.module.aicrm.service.customeridentity.CustomerIdentityService;

@Tag(name = "管理后台 - 客户证件信息表（零售客户特有，1对多关系，支持多证件）")
@RestController
@RequestMapping("/aicrm/customer-identity")
@Validated
public class CustomerIdentityController {

    @Resource
    private CustomerIdentityService customerIdentityService;

    @PostMapping("/create")
    @Operation(summary = "创建客户证件信息表（零售客户特有，1对多关系，支持多证件）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-identity:create')")
    public CommonResult<Long> createCustomerIdentity(@Valid @RequestBody CustomerIdentitySaveReqVO createReqVO) {
        return success(customerIdentityService.createCustomerIdentity(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户证件信息表（零售客户特有，1对多关系，支持多证件）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-identity:update')")
    public CommonResult<Boolean> updateCustomerIdentity(@Valid @RequestBody CustomerIdentitySaveReqVO updateReqVO) {
        customerIdentityService.updateCustomerIdentity(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户证件信息表（零售客户特有，1对多关系，支持多证件）")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-identity:delete')")
    public CommonResult<Boolean> deleteCustomerIdentity(@RequestParam("id") Long id) {
        customerIdentityService.deleteCustomerIdentity(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户证件信息表（零售客户特有，1对多关系，支持多证件）")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-identity:delete')")
    public CommonResult<Boolean> deleteCustomerIdentityList(@RequestParam("ids") List<Long> ids) {
        customerIdentityService.deleteCustomerIdentityListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户证件信息表（零售客户特有，1对多关系，支持多证件）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-identity:query')")
    public CommonResult<CustomerIdentityRespVO> getCustomerIdentity(@RequestParam("id") Long id) {
        CustomerIdentityDO customerIdentity = customerIdentityService.getCustomerIdentity(id);
        return success(BeanUtils.toBean(customerIdentity, CustomerIdentityRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户证件信息表（零售客户特有，1对多关系，支持多证件）分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-identity:query')")
    public CommonResult<PageResult<CustomerIdentityRespVO>> getCustomerIdentityPage(@Valid CustomerIdentityPageReqVO pageReqVO) {
        PageResult<CustomerIdentityDO> pageResult = customerIdentityService.getCustomerIdentityPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerIdentityRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户证件信息表（零售客户特有，1对多关系，支持多证件） Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-identity:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerIdentityExcel(@Valid CustomerIdentityPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerIdentityDO> list = customerIdentityService.getCustomerIdentityPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户证件信息表（零售客户特有，1对多关系，支持多证件）.xls", "数据", CustomerIdentityRespVO.class,
                        BeanUtils.toBean(list, CustomerIdentityRespVO.class));
    }

}