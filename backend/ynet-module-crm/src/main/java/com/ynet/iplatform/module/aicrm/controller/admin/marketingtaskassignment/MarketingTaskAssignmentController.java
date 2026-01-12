package com.ynet.iplatform.module.aicrm.controller.admin.marketingtaskassignment;

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

import com.ynet.iplatform.module.aicrm.controller.admin.marketingtaskassignment.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.marketingtaskassignment.MarketingTaskAssignmentDO;
import com.ynet.iplatform.module.aicrm.service.marketingtaskassignment.MarketingTaskAssignmentService;

@Tag(name = "管理后台 - 营销活动任务下发")
@RestController
@RequestMapping("/aicrm/marketing-task-assignment")
@Validated
public class MarketingTaskAssignmentController {

    @Resource
    private MarketingTaskAssignmentService marketingTaskAssignmentService;

    @PostMapping("/create")
    @Operation(summary = "创建营销活动任务下发")
    @PreAuthorize("@ss.hasPermission('aicrm:marketing-task-assignment:create')")
    public CommonResult<Long> createMarketingTaskAssignment(@Valid @RequestBody MarketingTaskAssignmentSaveReqVO createReqVO) {
        return success(marketingTaskAssignmentService.createMarketingTaskAssignment(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新营销活动任务下发")
    @PreAuthorize("@ss.hasPermission('aicrm:marketing-task-assignment:update')")
    public CommonResult<Boolean> updateMarketingTaskAssignment(@Valid @RequestBody MarketingTaskAssignmentSaveReqVO updateReqVO) {
        marketingTaskAssignmentService.updateMarketingTaskAssignment(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除营销活动任务下发")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:marketing-task-assignment:delete')")
    public CommonResult<Boolean> deleteMarketingTaskAssignment(@RequestParam("id") Long id) {
        marketingTaskAssignmentService.deleteMarketingTaskAssignment(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号列表", required = true)
    @Operation(summary = "批量删除营销活动任务下发")
    @PreAuthorize("@ss.hasPermission('aicrm:marketing-task-assignment:delete')")
    public CommonResult<Boolean> deleteMarketingTaskAssignmentList(@RequestParam("ids") List<Long> ids) {
        marketingTaskAssignmentService.deleteMarketingTaskAssignmentListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得营销活动任务下发")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:marketing-task-assignment:query')")
    public CommonResult<MarketingTaskAssignmentRespVO> getMarketingTaskAssignment(@RequestParam("id") Long id) {
        MarketingTaskAssignmentDO assignment = marketingTaskAssignmentService.getMarketingTaskAssignment(id);
        return success(BeanUtils.toBean(assignment, MarketingTaskAssignmentRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得营销活动任务下发分页")
    @PreAuthorize("@ss.hasPermission('aicrm:marketing-task-assignment:query')")
    public CommonResult<PageResult<MarketingTaskAssignmentRespVO>> getMarketingTaskAssignmentPage(@Valid MarketingTaskAssignmentPageReqVO pageReqVO) {
        PageResult<MarketingTaskAssignmentRespVO> pageResult = marketingTaskAssignmentService.getMarketingTaskAssignmentPageWithActivity(pageReqVO);
        return success(pageResult);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出营销活动任务下发 Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:marketing-task-assignment:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMarketingTaskAssignmentExcel(@Valid MarketingTaskAssignmentPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MarketingTaskAssignmentDO> list = marketingTaskAssignmentService.getMarketingTaskAssignmentPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "营销活动任务下发.xls", "数据", MarketingTaskAssignmentRespVO.class,
                        BeanUtils.toBean(list, MarketingTaskAssignmentRespVO.class));
    }

}
