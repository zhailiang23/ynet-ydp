package com.ynet.iplatform.module.aicrm.controller.admin.customercoupon;

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

import com.ynet.iplatform.module.aicrm.controller.admin.customercoupon.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customercoupon.CustomerCouponDO;
import com.ynet.iplatform.module.aicrm.service.customercoupon.CustomerCouponService;

@Tag(name = "管理后台 - 客户卡券信息表（零售客户特有）")
@RestController
@RequestMapping("/aicrm/customer-coupon")
@Validated
public class CustomerCouponController {

    @Resource
    private CustomerCouponService customerCouponService;

    @PostMapping("/create")
    @Operation(summary = "创建客户卡券信息表（零售客户特有）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-coupon:create')")
    public CommonResult<Long> createCustomerCoupon(@Valid @RequestBody CustomerCouponSaveReqVO createReqVO) {
        return success(customerCouponService.createCustomerCoupon(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户卡券信息表（零售客户特有）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-coupon:update')")
    public CommonResult<Boolean> updateCustomerCoupon(@Valid @RequestBody CustomerCouponSaveReqVO updateReqVO) {
        customerCouponService.updateCustomerCoupon(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户卡券信息表（零售客户特有）")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-coupon:delete')")
    public CommonResult<Boolean> deleteCustomerCoupon(@RequestParam("id") Long id) {
        customerCouponService.deleteCustomerCoupon(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户卡券信息表（零售客户特有）")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-coupon:delete')")
    public CommonResult<Boolean> deleteCustomerCouponList(@RequestParam("ids") List<Long> ids) {
        customerCouponService.deleteCustomerCouponListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户卡券信息表（零售客户特有）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-coupon:query')")
    public CommonResult<CustomerCouponRespVO> getCustomerCoupon(@RequestParam("id") Long id) {
        CustomerCouponDO customerCoupon = customerCouponService.getCustomerCoupon(id);
        return success(BeanUtils.toBean(customerCoupon, CustomerCouponRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户卡券信息表（零售客户特有）分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-coupon:query')")
    public CommonResult<PageResult<CustomerCouponRespVO>> getCustomerCouponPage(@Valid CustomerCouponPageReqVO pageReqVO) {
        PageResult<CustomerCouponDO> pageResult = customerCouponService.getCustomerCouponPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerCouponRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户卡券信息表（零售客户特有） Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-coupon:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerCouponExcel(@Valid CustomerCouponPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerCouponDO> list = customerCouponService.getCustomerCouponPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户卡券信息表（零售客户特有）.xls", "数据", CustomerCouponRespVO.class,
                        BeanUtils.toBean(list, CustomerCouponRespVO.class));
    }

}