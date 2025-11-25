package com.ynet.iplatform.module.aicrm.controller.admin.practicevirtualcustomer;

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

import com.ynet.iplatform.module.aicrm.controller.admin.practicevirtualcustomer.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.practicevirtualcustomer.PracticeVirtualCustomerDO;
import com.ynet.iplatform.module.aicrm.service.practicevirtualcustomer.PracticeVirtualCustomerService;

@Tag(name = "管理后台 - CRM智能陪练-虚拟客户")
@RestController
@RequestMapping("/aicrm/practice-virtual-customer")
@Validated
public class PracticeVirtualCustomerController {

    @Resource
    private PracticeVirtualCustomerService practiceVirtualCustomerService;

    @PostMapping("/create")
    @Operation(summary = "创建CRM智能陪练-虚拟客户")
    @PreAuthorize("@ss.hasPermission('aicrm:practice-virtual-customer:create')")
    public CommonResult<Long> createPracticeVirtualCustomer(@Valid @RequestBody PracticeVirtualCustomerSaveReqVO createReqVO) {
        return success(practiceVirtualCustomerService.createPracticeVirtualCustomer(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新CRM智能陪练-虚拟客户")
    @PreAuthorize("@ss.hasPermission('aicrm:practice-virtual-customer:update')")
    public CommonResult<Boolean> updatePracticeVirtualCustomer(@Valid @RequestBody PracticeVirtualCustomerSaveReqVO updateReqVO) {
        practiceVirtualCustomerService.updatePracticeVirtualCustomer(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除CRM智能陪练-虚拟客户")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:practice-virtual-customer:delete')")
    public CommonResult<Boolean> deletePracticeVirtualCustomer(@RequestParam("id") Long id) {
        practiceVirtualCustomerService.deletePracticeVirtualCustomer(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除CRM智能陪练-虚拟客户")
                @PreAuthorize("@ss.hasPermission('aicrm:practice-virtual-customer:delete')")
    public CommonResult<Boolean> deletePracticeVirtualCustomerList(@RequestParam("ids") List<Long> ids) {
        practiceVirtualCustomerService.deletePracticeVirtualCustomerListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得CRM智能陪练-虚拟客户")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:practice-virtual-customer:query')")
    public CommonResult<PracticeVirtualCustomerRespVO> getPracticeVirtualCustomer(@RequestParam("id") Long id) {
        PracticeVirtualCustomerDO practiceVirtualCustomer = practiceVirtualCustomerService.getPracticeVirtualCustomer(id);
        return success(BeanUtils.toBean(practiceVirtualCustomer, PracticeVirtualCustomerRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得CRM智能陪练-虚拟客户分页")
    @PreAuthorize("@ss.hasPermission('aicrm:practice-virtual-customer:query')")
    public CommonResult<PageResult<PracticeVirtualCustomerRespVO>> getPracticeVirtualCustomerPage(@Valid PracticeVirtualCustomerPageReqVO pageReqVO) {
        PageResult<PracticeVirtualCustomerDO> pageResult = practiceVirtualCustomerService.getPracticeVirtualCustomerPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PracticeVirtualCustomerRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出CRM智能陪练-虚拟客户 Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:practice-virtual-customer:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPracticeVirtualCustomerExcel(@Valid PracticeVirtualCustomerPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PracticeVirtualCustomerDO> list = practiceVirtualCustomerService.getPracticeVirtualCustomerPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "CRM智能陪练-虚拟客户.xls", "数据", PracticeVirtualCustomerRespVO.class,
                        BeanUtils.toBean(list, PracticeVirtualCustomerRespVO.class));
    }

}