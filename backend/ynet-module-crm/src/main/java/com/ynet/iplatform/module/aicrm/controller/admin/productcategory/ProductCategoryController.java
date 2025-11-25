package com.ynet.iplatform.module.aicrm.controller.admin.productcategory;

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

import com.ynet.iplatform.module.aicrm.controller.admin.productcategory.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.productcategory.ProductCategoryDO;
import com.ynet.iplatform.module.aicrm.service.productcategory.ProductCategoryService;

@Tag(name = "管理后台 - 产品类目表（树形结构）")
@RestController
@RequestMapping("/aicrm/product-category")
@Validated
public class ProductCategoryController {

    @Resource
    private ProductCategoryService productCategoryService;

    @PostMapping("/create")
    @Operation(summary = "创建产品类目表（树形结构）")
    @PreAuthorize("@ss.hasPermission('aicrm:product-category:create')")
    public CommonResult<Long> createProductCategory(@Valid @RequestBody ProductCategorySaveReqVO createReqVO) {
        return success(productCategoryService.createProductCategory(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新产品类目表（树形结构）")
    @PreAuthorize("@ss.hasPermission('aicrm:product-category:update')")
    public CommonResult<Boolean> updateProductCategory(@Valid @RequestBody ProductCategorySaveReqVO updateReqVO) {
        productCategoryService.updateProductCategory(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除产品类目表（树形结构）")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:product-category:delete')")
    public CommonResult<Boolean> deleteProductCategory(@RequestParam("id") Long id) {
        productCategoryService.deleteProductCategory(id);
        return success(true);
    }


    @GetMapping("/get")
    @Operation(summary = "获得产品类目表（树形结构）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:product-category:query')")
    public CommonResult<ProductCategoryRespVO> getProductCategory(@RequestParam("id") Long id) {
        ProductCategoryDO productCategory = productCategoryService.getProductCategory(id);
        return success(BeanUtils.toBean(productCategory, ProductCategoryRespVO.class));
    }

    @GetMapping("/list")
    @Operation(summary = "获得产品类目表（树形结构）列表")
    @PreAuthorize("@ss.hasPermission('aicrm:product-category:query')")
    public CommonResult<List<ProductCategoryRespVO>> getProductCategoryList(@Valid ProductCategoryListReqVO listReqVO) {
        List<ProductCategoryDO> list = productCategoryService.getProductCategoryList(listReqVO);
        return success(BeanUtils.toBean(list, ProductCategoryRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出产品类目表（树形结构） Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:product-category:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportProductCategoryExcel(@Valid ProductCategoryListReqVO listReqVO,
              HttpServletResponse response) throws IOException {
        List<ProductCategoryDO> list = productCategoryService.getProductCategoryList(listReqVO);
        // 导出 Excel
        ExcelUtils.write(response, "产品类目表（树形结构）.xls", "数据", ProductCategoryRespVO.class,
                        BeanUtils.toBean(list, ProductCategoryRespVO.class));
    }

}