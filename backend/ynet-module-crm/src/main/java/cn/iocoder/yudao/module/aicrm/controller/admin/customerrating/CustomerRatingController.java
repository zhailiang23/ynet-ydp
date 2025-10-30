package cn.iocoder.yudao.module.aicrm.controller.admin.customerrating;

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

import cn.iocoder.yudao.module.aicrm.controller.admin.customerrating.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerrating.CustomerRatingDO;
import cn.iocoder.yudao.module.aicrm.service.customerrating.CustomerRatingService;

@Tag(name = "管理后台 - 客户评级信息表（与客户主表1对1关系）")
@RestController
@RequestMapping("/aicrm/customer-rating")
@Validated
public class CustomerRatingController {

    @Resource
    private CustomerRatingService customerRatingService;

    @PostMapping("/create")
    @Operation(summary = "创建客户评级信息表（与客户主表1对1关系）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-rating:create')")
    public CommonResult<Long> createCustomerRating(@Valid @RequestBody CustomerRatingSaveReqVO createReqVO) {
        return success(customerRatingService.createCustomerRating(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户评级信息表（与客户主表1对1关系）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-rating:update')")
    public CommonResult<Boolean> updateCustomerRating(@Valid @RequestBody CustomerRatingSaveReqVO updateReqVO) {
        customerRatingService.updateCustomerRating(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户评级信息表（与客户主表1对1关系）")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-rating:delete')")
    public CommonResult<Boolean> deleteCustomerRating(@RequestParam("id") Long id) {
        customerRatingService.deleteCustomerRating(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户评级信息表（与客户主表1对1关系）")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-rating:delete')")
    public CommonResult<Boolean> deleteCustomerRatingList(@RequestParam("ids") List<Long> ids) {
        customerRatingService.deleteCustomerRatingListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户评级信息表（与客户主表1对1关系）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-rating:query')")
    public CommonResult<CustomerRatingRespVO> getCustomerRating(@RequestParam("id") Long id) {
        CustomerRatingDO customerRating = customerRatingService.getCustomerRating(id);
        return success(BeanUtils.toBean(customerRating, CustomerRatingRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户评级信息表（与客户主表1对1关系）分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-rating:query')")
    public CommonResult<PageResult<CustomerRatingRespVO>> getCustomerRatingPage(@Valid CustomerRatingPageReqVO pageReqVO) {
        PageResult<CustomerRatingDO> pageResult = customerRatingService.getCustomerRatingPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerRatingRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户评级信息表（与客户主表1对1关系） Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-rating:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerRatingExcel(@Valid CustomerRatingPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerRatingDO> list = customerRatingService.getCustomerRatingPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户评级信息表（与客户主表1对1关系）.xls", "数据", CustomerRatingRespVO.class,
                        BeanUtils.toBean(list, CustomerRatingRespVO.class));
    }

}