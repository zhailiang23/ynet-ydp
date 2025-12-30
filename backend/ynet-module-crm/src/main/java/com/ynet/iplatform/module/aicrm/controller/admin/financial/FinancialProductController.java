package com.ynet.iplatform.module.aicrm.controller.admin.financial;

import com.ynet.iplatform.framework.common.pojo.CommonResult;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.excel.core.util.ExcelUtils;
import com.ynet.iplatform.framework.apilog.core.annotation.ApiAccessLog;
import com.ynet.iplatform.module.aicrm.controller.admin.financial.vo.product.FinancialProductPageReqVO;
import com.ynet.iplatform.module.aicrm.controller.admin.financial.vo.product.FinancialProductRespVO;
import com.ynet.iplatform.module.aicrm.controller.admin.financial.vo.product.FinancialProductSaveReqVO;
import com.ynet.iplatform.module.aicrm.service.financial.FinancialProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.ynet.iplatform.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static com.ynet.iplatform.framework.common.pojo.CommonResult.success;

/**
 * CRM 金融产品 Controller
 *
 * @author 易诚源码
 */
@Tag(name = "管理后台 - CRM 金融产品")
@RestController
@RequestMapping("/aicrm/financial-product")
@Validated
public class FinancialProductController {

    @Resource
    private FinancialProductService financialProductService;

    @PostMapping("/create")
    @Operation(summary = "创建金融产品")
    @PreAuthorize("@ss.hasPermission('crm:financial-product:create')")
    public CommonResult<Long> createFinancialProduct(@Valid @RequestBody FinancialProductSaveReqVO createReqVO) {
        Long productId = financialProductService.createFinancialProduct(createReqVO);
        return success(productId);
    }

    @PutMapping("/update")
    @Operation(summary = "更新金融产品")
    @PreAuthorize("@ss.hasPermission('crm:financial-product:update')")
    public CommonResult<Boolean> updateFinancialProduct(@Valid @RequestBody FinancialProductSaveReqVO updateReqVO) {
        financialProductService.updateFinancialProduct(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除金融产品")
    @Parameter(name = "id", description = "编号", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('crm:financial-product:delete')")
    public CommonResult<Boolean> deleteFinancialProduct(@RequestParam("id") Long id) {
        financialProductService.deleteFinancialProduct(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除金融产品")
    @Parameter(name = "ids", description = "编号列表", required = true)
    @PreAuthorize("@ss.hasPermission('crm:financial-product:delete')")
    public CommonResult<Boolean> deleteFinancialProductList(@RequestParam("ids") List<Long> ids) {
        financialProductService.deleteFinancialProductList(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得金融产品信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('crm:financial-product:query')")
    public CommonResult<FinancialProductRespVO> getFinancialProduct(@RequestParam("id") Long id) {
        return success(financialProductService.getFinancialProductRespVO(id));
    }

    @GetMapping("/page")
    @Operation(summary = "获得金融产品分页")
    @PreAuthorize("@ss.hasPermission('crm:financial-product:query')")
    public CommonResult<PageResult<FinancialProductRespVO>> getFinancialProductPage(@Valid FinancialProductPageReqVO pageReqVO) {
        PageResult<FinancialProductRespVO> pageResult = financialProductService.getFinancialProductPage(pageReqVO);
        return success(pageResult);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出金融产品 Excel")
    @PreAuthorize("@ss.hasPermission('crm:financial-product:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportFinancialProductExcel(FinancialProductPageReqVO pageReqVO,
                                             HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(Integer.MAX_VALUE);
        PageResult<FinancialProductRespVO> pageResult = financialProductService.getFinancialProductPage(pageReqVO);
        ExcelUtils.write(response, "金融产品.xls", "数据", FinancialProductRespVO.class, pageResult.getList());
    }

}
