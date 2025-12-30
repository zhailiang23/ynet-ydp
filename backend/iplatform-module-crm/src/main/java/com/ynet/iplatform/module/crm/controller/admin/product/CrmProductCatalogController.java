package com.ynet.iplatform.module.crm.controller.admin.product;

import com.ynet.iplatform.framework.common.enums.CommonStatusEnum;
import com.ynet.iplatform.framework.common.pojo.CommonResult;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;
import com.ynet.iplatform.module.crm.controller.admin.product.vo.catalog.CrmProductCatalogListReqVO;
import com.ynet.iplatform.module.crm.controller.admin.product.vo.catalog.CrmProductCatalogRespVO;
import com.ynet.iplatform.module.crm.controller.admin.product.vo.catalog.CrmProductCatalogSaveReqVO;
import com.ynet.iplatform.module.crm.dal.dataobject.product.CrmProductCatalogDO;
import com.ynet.iplatform.module.crm.service.product.CrmProductCatalogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import java.util.List;

import static com.ynet.iplatform.framework.common.pojo.CommonResult.success;

/**
 * CRM 产品目录 Controller
 *
 * @author 易诚源码
 */
@Tag(name = "管理后台 - CRM 产品目录")
@RestController
@RequestMapping("/crm/product-catalog")
@Validated
public class CrmProductCatalogController {

    @Resource
    private CrmProductCatalogService productCatalogService;

    @PostMapping("/create")
    @Operation(summary = "创建产品目录")
    @PreAuthorize("@ss.hasPermission('crm:product-catalog:create')")
    public CommonResult<Long> createProductCatalog(@Valid @RequestBody CrmProductCatalogSaveReqVO createReqVO) {
        Long catalogId = productCatalogService.createProductCatalog(createReqVO);
        return success(catalogId);
    }

    @PutMapping("/update")
    @Operation(summary = "更新产品目录")
    @PreAuthorize("@ss.hasPermission('crm:product-catalog:update')")
    public CommonResult<Boolean> updateProductCatalog(@Valid @RequestBody CrmProductCatalogSaveReqVO updateReqVO) {
        productCatalogService.updateProductCatalog(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除产品目录")
    @Parameter(name = "id", description = "编号", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('crm:product-catalog:delete')")
    public CommonResult<Boolean> deleteProductCatalog(@RequestParam("id") Long id) {
        productCatalogService.deleteProductCatalog(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除产品目录")
    @Parameter(name = "ids", description = "编号列表", required = true)
    @PreAuthorize("@ss.hasPermission('crm:product-catalog:delete')")
    public CommonResult<Boolean> deleteProductCatalogList(@RequestParam("ids") List<Long> ids) {
        productCatalogService.deleteProductCatalogList(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得产品目录信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('crm:product-catalog:query')")
    public CommonResult<CrmProductCatalogRespVO> getProductCatalog(@RequestParam("id") Long id) {
        CrmProductCatalogDO catalog = productCatalogService.getProductCatalog(id);
        return success(BeanUtils.toBean(catalog, CrmProductCatalogRespVO.class));
    }

    @GetMapping("/list")
    @Operation(summary = "获取产品目录列表")
    @PreAuthorize("@ss.hasPermission('crm:product-catalog:query')")
    public CommonResult<List<CrmProductCatalogRespVO>> getProductCatalogList(CrmProductCatalogListReqVO reqVO) {
        List<CrmProductCatalogDO> list = productCatalogService.getProductCatalogList(reqVO);
        return success(BeanUtils.toBean(list, CrmProductCatalogRespVO.class));
    }

    @GetMapping(value = {"/list-all-simple", "/simple-list"})
    @Operation(summary = "获取产品目录精简信息列表", description = "只包含被开启的产品目录，主要用于前端的下拉选项")
    public CommonResult<List<CrmProductCatalogRespVO>> getSimpleProductCatalogList() {
        List<CrmProductCatalogDO> list = productCatalogService.getProductCatalogList(
                new CrmProductCatalogListReqVO().setStatus(CommonStatusEnum.ENABLE.getStatus()));
        return success(BeanUtils.toBean(list, CrmProductCatalogRespVO.class));
    }

}
