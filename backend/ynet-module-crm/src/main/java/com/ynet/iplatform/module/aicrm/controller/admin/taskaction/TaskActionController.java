package com.ynet.iplatform.module.aicrm.controller.admin.taskaction;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

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

import com.ynet.iplatform.module.aicrm.controller.admin.taskaction.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.taskaction.TaskActionDO;
import com.ynet.iplatform.module.aicrm.service.taskaction.TaskActionService;

@Tag(name = "管理后台 - 任务行动")
@RestController
@RequestMapping("/aicrm/task-action")
@Validated
public class TaskActionController {

    @Resource
    private TaskActionService taskActionService;

    @PostMapping("/create")
    @Operation(summary = "创建任务行动")
    @PreAuthorize("@ss.hasPermission('aicrm:task-action:create')")
    public CommonResult<Long> createTaskAction(@Valid @RequestBody TaskActionSaveReqVO createReqVO) {
        return success(taskActionService.createTaskAction(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新任务行动")
    @PreAuthorize("@ss.hasPermission('aicrm:task-action:update')")
    public CommonResult<Boolean> updateTaskAction(@Valid @RequestBody TaskActionSaveReqVO updateReqVO) {
        taskActionService.updateTaskAction(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除任务行动")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:task-action:delete')")
    public CommonResult<Boolean> deleteTaskAction(@RequestParam("id") Long id) {
        taskActionService.deleteTaskAction(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除任务行动")
    @PreAuthorize("@ss.hasPermission('aicrm:task-action:delete')")
    public CommonResult<Boolean> deleteTaskActionList(@RequestParam("ids") List<Long> ids) {
        taskActionService.deleteTaskActionListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得任务行动")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:task-action:query')")
    public CommonResult<TaskActionRespVO> getTaskAction(@RequestParam("id") Long id) {
        TaskActionDO taskAction = taskActionService.getTaskAction(id);
        return success(BeanUtils.toBean(taskAction, TaskActionRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得任务行动分页")
    @PreAuthorize("@ss.hasPermission('aicrm:task-action:query')")
    public CommonResult<PageResult<TaskActionRespVO>> getTaskActionPage(@Valid TaskActionPageReqVO pageReqVO) {
        PageResult<TaskActionDO> pageResult = taskActionService.getTaskActionPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TaskActionRespVO.class));
    }

    @GetMapping("/list-by-task-id")
    @Operation(summary = "根据任务ID获取行动列表")
    @Parameter(name = "taskId", description = "任务ID", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('aicrm:task-action:query')")
    public CommonResult<List<TaskActionRespVO>> getTaskActionListByTaskId(@RequestParam("taskId") Long taskId) {
        List<TaskActionDO> list = taskActionService.getTaskActionListByTaskId(taskId);
        return success(BeanUtils.toBean(list, TaskActionRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出任务行动 Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:task-action:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportTaskActionExcel(@Valid TaskActionPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TaskActionDO> list = taskActionService.getTaskActionPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "任务行动.xls", "数据", TaskActionRespVO.class,
                        BeanUtils.toBean(list, TaskActionRespVO.class));
    }

}
