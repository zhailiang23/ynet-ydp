package cn.iocoder.yudao.module.aicrm.controller.admin.customerimportantevent;

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

import cn.iocoder.yudao.module.aicrm.controller.admin.customerimportantevent.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerimportantevent.CustomerImportantEventDO;
import cn.iocoder.yudao.module.aicrm.service.customerimportantevent.CustomerImportantEventService;

@Tag(name = "管理后台 - 客户重要事件表（零售+对公共用）")
@RestController
@RequestMapping("/aicrm/customer-important-event")
@Validated
public class CustomerImportantEventController {

    @Resource
    private CustomerImportantEventService customerImportantEventService;

    @PostMapping("/create")
    @Operation(summary = "创建客户重要事件表（零售+对公共用）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-important-event:create')")
    public CommonResult<Long> createCustomerImportantEvent(@Valid @RequestBody CustomerImportantEventSaveReqVO createReqVO) {
        return success(customerImportantEventService.createCustomerImportantEvent(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户重要事件表（零售+对公共用）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-important-event:update')")
    public CommonResult<Boolean> updateCustomerImportantEvent(@Valid @RequestBody CustomerImportantEventSaveReqVO updateReqVO) {
        customerImportantEventService.updateCustomerImportantEvent(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户重要事件表（零售+对公共用）")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-important-event:delete')")
    public CommonResult<Boolean> deleteCustomerImportantEvent(@RequestParam("id") Long id) {
        customerImportantEventService.deleteCustomerImportantEvent(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户重要事件表（零售+对公共用）")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-important-event:delete')")
    public CommonResult<Boolean> deleteCustomerImportantEventList(@RequestParam("ids") List<Long> ids) {
        customerImportantEventService.deleteCustomerImportantEventListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户重要事件表（零售+对公共用）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-important-event:query')")
    public CommonResult<CustomerImportantEventRespVO> getCustomerImportantEvent(@RequestParam("id") Long id) {
        CustomerImportantEventDO customerImportantEvent = customerImportantEventService.getCustomerImportantEvent(id);
        return success(BeanUtils.toBean(customerImportantEvent, CustomerImportantEventRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户重要事件表（零售+对公共用）分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-important-event:query')")
    public CommonResult<PageResult<CustomerImportantEventRespVO>> getCustomerImportantEventPage(@Valid CustomerImportantEventPageReqVO pageReqVO) {
        PageResult<CustomerImportantEventDO> pageResult = customerImportantEventService.getCustomerImportantEventPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerImportantEventRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户重要事件表（零售+对公共用） Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-important-event:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerImportantEventExcel(@Valid CustomerImportantEventPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerImportantEventDO> list = customerImportantEventService.getCustomerImportantEventPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户重要事件表（零售+对公共用）.xls", "数据", CustomerImportantEventRespVO.class,
                        BeanUtils.toBean(list, CustomerImportantEventRespVO.class));
    }

}