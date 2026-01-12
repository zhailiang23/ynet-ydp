package com.ynet.iplatform.module.aicrm.controller.admin.financial;

import com.ynet.iplatform.framework.common.pojo.CommonResult;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.module.aicrm.controller.admin.financial.vo.carousel.FinancialProductCarouselPageReqVO;
import com.ynet.iplatform.module.aicrm.controller.admin.financial.vo.carousel.FinancialProductCarouselRespVO;
import com.ynet.iplatform.module.aicrm.controller.admin.financial.vo.carousel.FinancialProductCarouselSaveReqVO;
import com.ynet.iplatform.module.aicrm.service.financial.FinancialProductCarouselService;
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
 * CRM 金融产品轮播 Controller
 *
 * @author 易诚源码
 */
@Tag(name = "管理后台 - CRM 金融产品轮播")
@RestController
@RequestMapping("/aicrm/financial-carousel")
@Validated
public class FinancialProductCarouselController {

    @Resource
    private FinancialProductCarouselService carouselService;

    @PostMapping("/create")
    @Operation(summary = "创建轮播")
    @PreAuthorize("@ss.hasPermission('crm:financial-carousel:create')")
    public CommonResult<Long> createCarousel(@Valid @RequestBody FinancialProductCarouselSaveReqVO createReqVO) {
        Long carouselId = carouselService.createCarousel(createReqVO);
        return success(carouselId);
    }

    @PutMapping("/update")
    @Operation(summary = "更新轮播")
    @PreAuthorize("@ss.hasPermission('crm:financial-carousel:update')")
    public CommonResult<Boolean> updateCarousel(@Valid @RequestBody FinancialProductCarouselSaveReqVO updateReqVO) {
        carouselService.updateCarousel(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除轮播")
    @Parameter(name = "id", description = "编号", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('crm:financial-carousel:delete')")
    public CommonResult<Boolean> deleteCarousel(@RequestParam("id") Long id) {
        carouselService.deleteCarousel(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除轮播")
    @Parameter(name = "ids", description = "编号列表", required = true)
    @PreAuthorize("@ss.hasPermission('crm:financial-carousel:delete')")
    public CommonResult<Boolean> deleteCarouselList(@RequestParam("ids") List<Long> ids) {
        carouselService.deleteCarouselList(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得轮播信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('crm:financial-carousel:query')")
    public CommonResult<FinancialProductCarouselRespVO> getCarousel(@RequestParam("id") Long id) {
        return success(carouselService.getCarouselRespVO(id));
    }

    @GetMapping("/page")
    @Operation(summary = "获得轮播分页")
    @PreAuthorize("@ss.hasPermission('crm:financial-carousel:query')")
    public CommonResult<PageResult<FinancialProductCarouselRespVO>> getCarouselPage(@Valid FinancialProductCarouselPageReqVO pageReqVO) {
        PageResult<FinancialProductCarouselRespVO> pageResult = carouselService.getCarouselPage(pageReqVO);
        return success(pageResult);
    }

}
