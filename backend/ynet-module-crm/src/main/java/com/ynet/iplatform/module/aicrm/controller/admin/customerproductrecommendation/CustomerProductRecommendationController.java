package com.ynet.iplatform.module.aicrm.controller.admin.customerproductrecommendation;

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

import com.ynet.iplatform.module.aicrm.controller.admin.customerproductrecommendation.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerproductrecommendation.CustomerProductRecommendationDO;
import com.ynet.iplatform.module.aicrm.service.customerproductrecommendation.CustomerProductRecommendationService;

@Tag(name = "管理后台 - 客户产品推荐")
@RestController
@RequestMapping("/aicrm/customer-product-recommendation")
@Validated
public class CustomerProductRecommendationController {

    @Resource
    private CustomerProductRecommendationService customerProductRecommendationService;

    @PostMapping("/create")
    @Operation(summary = "创建客户产品推荐")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-product-recommendation:create')")
    public CommonResult<Long> createCustomerProductRecommendation(@Valid @RequestBody CustomerProductRecommendationSaveReqVO createReqVO) {
        return success(customerProductRecommendationService.createCustomerProductRecommendation(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户产品推荐")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-product-recommendation:update')")
    public CommonResult<Boolean> updateCustomerProductRecommendation(@Valid @RequestBody CustomerProductRecommendationSaveReqVO updateReqVO) {
        customerProductRecommendationService.updateCustomerProductRecommendation(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户产品推荐")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-product-recommendation:delete')")
    public CommonResult<Boolean> deleteCustomerProductRecommendation(@RequestParam("id") Long id) {
        customerProductRecommendationService.deleteCustomerProductRecommendation(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户产品推荐")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-product-recommendation:delete')")
    public CommonResult<Boolean> deleteCustomerProductRecommendationList(@RequestParam("ids") List<Long> ids) {
        customerProductRecommendationService.deleteCustomerProductRecommendationListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户产品推荐")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-product-recommendation:query')")
    public CommonResult<CustomerProductRecommendationRespVO> getCustomerProductRecommendation(@RequestParam("id") Long id) {
        CustomerProductRecommendationDO customerProductRecommendation = customerProductRecommendationService.getCustomerProductRecommendation(id);
        return success(BeanUtils.toBean(customerProductRecommendation, CustomerProductRecommendationRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户产品推荐分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-product-recommendation:query')")
    public CommonResult<PageResult<CustomerProductRecommendationRespVO>> getCustomerProductRecommendationPage(@Valid CustomerProductRecommendationPageReqVO pageReqVO) {
        PageResult<CustomerProductRecommendationDO> pageResult = customerProductRecommendationService.getCustomerProductRecommendationPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerProductRecommendationRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户产品推荐 Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-product-recommendation:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerProductRecommendationExcel(@Valid CustomerProductRecommendationPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerProductRecommendationDO> list = customerProductRecommendationService.getCustomerProductRecommendationPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户产品推荐.xls", "数据", CustomerProductRecommendationRespVO.class,
                        BeanUtils.toBean(list, CustomerProductRecommendationRespVO.class));
    }

}