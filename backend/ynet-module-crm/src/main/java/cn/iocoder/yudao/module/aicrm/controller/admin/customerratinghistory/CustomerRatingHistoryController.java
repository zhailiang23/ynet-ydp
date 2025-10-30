package cn.iocoder.yudao.module.aicrm.controller.admin.customerratinghistory;

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

import cn.iocoder.yudao.module.aicrm.controller.admin.customerratinghistory.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerratinghistory.CustomerRatingHistoryDO;
import cn.iocoder.yudao.module.aicrm.service.customerratinghistory.CustomerRatingHistoryService;

@Tag(name = "管理后台 - 客户评级调整历史")
@RestController
@RequestMapping("/aicrm/customer-rating-history")
@Validated
public class CustomerRatingHistoryController {

    @Resource
    private CustomerRatingHistoryService customerRatingHistoryService;

    @PostMapping("/create")
    @Operation(summary = "创建客户评级调整历史")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-rating-history:create')")
    public CommonResult<Long> createCustomerRatingHistory(@Valid @RequestBody CustomerRatingHistorySaveReqVO createReqVO) {
        return success(customerRatingHistoryService.createCustomerRatingHistory(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户评级调整历史")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-rating-history:update')")
    public CommonResult<Boolean> updateCustomerRatingHistory(@Valid @RequestBody CustomerRatingHistorySaveReqVO updateReqVO) {
        customerRatingHistoryService.updateCustomerRatingHistory(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户评级调整历史")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-rating-history:delete')")
    public CommonResult<Boolean> deleteCustomerRatingHistory(@RequestParam("id") Long id) {
        customerRatingHistoryService.deleteCustomerRatingHistory(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户评级调整历史")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-rating-history:delete')")
    public CommonResult<Boolean> deleteCustomerRatingHistoryList(@RequestParam("ids") List<Long> ids) {
        customerRatingHistoryService.deleteCustomerRatingHistoryListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户评级调整历史")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-rating-history:query')")
    public CommonResult<CustomerRatingHistoryRespVO> getCustomerRatingHistory(@RequestParam("id") Long id) {
        CustomerRatingHistoryDO customerRatingHistory = customerRatingHistoryService.getCustomerRatingHistory(id);
        return success(BeanUtils.toBean(customerRatingHistory, CustomerRatingHistoryRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户评级调整历史分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-rating-history:query')")
    public CommonResult<PageResult<CustomerRatingHistoryRespVO>> getCustomerRatingHistoryPage(@Valid CustomerRatingHistoryPageReqVO pageReqVO) {
        PageResult<CustomerRatingHistoryDO> pageResult = customerRatingHistoryService.getCustomerRatingHistoryPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerRatingHistoryRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户评级调整历史 Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-rating-history:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerRatingHistoryExcel(@Valid CustomerRatingHistoryPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerRatingHistoryDO> list = customerRatingHistoryService.getCustomerRatingHistoryPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户评级调整历史.xls", "数据", CustomerRatingHistoryRespVO.class,
                        BeanUtils.toBean(list, CustomerRatingHistoryRespVO.class));
    }

}