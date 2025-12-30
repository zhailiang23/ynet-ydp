package com.ynet.iplatform.module.aicrm.controller.app.financial;

import com.ynet.iplatform.framework.common.pojo.CommonResult;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;
import com.ynet.iplatform.module.aicrm.controller.app.financial.vo.AppCarouselRespVO;
import com.ynet.iplatform.module.aicrm.controller.app.financial.vo.AppFinancialProductPageReqVO;
import com.ynet.iplatform.module.aicrm.controller.app.financial.vo.AppFinancialProductRespVO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.financial.FinancialProductDO;
import com.ynet.iplatform.module.aicrm.service.financial.FinancialProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ynet.iplatform.framework.common.pojo.CommonResult.success;

/**
 * 移动端 - CRM 金融产品 Controller
 *
 * @author 易诚源码
 */
@Tag(name = "移动端 - CRM 金融产品")
@RestController
@RequestMapping("/aicrm/financial-product")
@Validated
public class AppFinancialProductController {

    @Resource
    private FinancialProductService financialProductService;

    @GetMapping("/page")
    @Operation(summary = "获得金融产品分页（移动端）")
    @PermitAll
    public CommonResult<PageResult<AppFinancialProductRespVO>> getAppFinancialProductPage(
            @Valid AppFinancialProductPageReqVO pageReqVO) {
        PageResult<AppFinancialProductRespVO> pageResult =
                financialProductService.getAppFinancialProductPage(pageReqVO);
        return success(pageResult);
    }

    @GetMapping("/get")
    @Operation(summary = "获得金融产品详情（移动端）")
    @Parameter(name = "id", description = "产品编号", required = true, example = "1")
    @PermitAll
    public CommonResult<AppFinancialProductRespVO> getAppFinancialProduct(@RequestParam("id") Long id) {
        FinancialProductDO product = financialProductService.getFinancialProduct(id);
        return success(BeanUtils.toBean(product, AppFinancialProductRespVO.class));
    }

    @GetMapping("/search")
    @Operation(summary = "搜索金融产品（移动端）")
    @Parameter(name = "keyword", description = "搜索关键词", required = true, example = "富盈")
    @PermitAll
    public CommonResult<PageResult<AppFinancialProductRespVO>> searchFinancialProduct(
            @RequestParam("keyword") String keyword,
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        AppFinancialProductPageReqVO pageReqVO = new AppFinancialProductPageReqVO();
        pageReqVO.setKeyword(keyword);
        pageReqVO.setPageNo(pageNo);
        pageReqVO.setPageSize(pageSize);
        pageReqVO.setStatus(1); // 只搜索在售产品

        PageResult<AppFinancialProductRespVO> pageResult =
                financialProductService.getAppFinancialProductPage(pageReqVO);
        return success(pageResult);
    }

    @GetMapping("/carousel/list")
    @Operation(summary = "获得轮播推荐列表（移动端）")
    @PermitAll
    public CommonResult<List<AppCarouselRespVO>> getCarouselList() {
        List<AppCarouselRespVO> carouselList = financialProductService.getCarouselList();
        return success(carouselList);
    }

}
