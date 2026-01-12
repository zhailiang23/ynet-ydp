package com.ynet.iplatform.module.grid.controller.admin.activitylog;

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

import com.ynet.iplatform.module.grid.controller.admin.activitylog.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.activitylog.GridActivityLogDO;
import com.ynet.iplatform.module.grid.service.activitylog.GridActivityLogService;

@Tag(name = "管理后台 - 网格活动记录")
@RestController
@RequestMapping("/grid/activity-log")
@Validated
public class GridActivityLogController {

    @Resource
    private GridActivityLogService activityLogService;

    @PostMapping("/create")
    @Operation(summary = "创建网格活动记录")
    @PreAuthorize("@ss.hasPermission('grid:activity-log:create')")
    public CommonResult<Long> createActivityLog(@Valid @RequestBody GridActivityLogSaveReqVO createReqVO) {
        return success(activityLogService.createActivityLog(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新网格活动记录")
    @PreAuthorize("@ss.hasPermission('grid:activity-log:update')")
    public CommonResult<Boolean> updateActivityLog(@Valid @RequestBody GridActivityLogSaveReqVO updateReqVO) {
        activityLogService.updateActivityLog(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除网格活动记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('grid:activity-log:delete')")
    public CommonResult<Boolean> deleteActivityLog(@RequestParam("id") Long id) {
        activityLogService.deleteActivityLog(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除网格活动记录")
                @PreAuthorize("@ss.hasPermission('grid:activity-log:delete')")
    public CommonResult<Boolean> deleteActivityLogList(@RequestParam("ids") List<Long> ids) {
        activityLogService.deleteActivityLogListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得网格活动记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('grid:activity-log:query')")
    public CommonResult<GridActivityLogRespVO> getActivityLog(@RequestParam("id") Long id) {
        GridActivityLogDO activityLog = activityLogService.getActivityLog(id);
        return success(BeanUtils.toBean(activityLog, GridActivityLogRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得网格活动记录分页")
    @PreAuthorize("@ss.hasPermission('grid:activity-log:query')")
    public CommonResult<PageResult<GridActivityLogRespVO>> getActivityLogPage(@Valid GridActivityLogPageReqVO pageReqVO) {
        PageResult<GridActivityLogDO> pageResult = activityLogService.getActivityLogPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GridActivityLogRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出网格活动记录 Excel")
    @PreAuthorize("@ss.hasPermission('grid:activity-log:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportActivityLogExcel(@Valid GridActivityLogPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GridActivityLogDO> list = activityLogService.getActivityLogPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "网格活动记录.xls", "数据", GridActivityLogRespVO.class,
                        BeanUtils.toBean(list, GridActivityLogRespVO.class));
    }

}