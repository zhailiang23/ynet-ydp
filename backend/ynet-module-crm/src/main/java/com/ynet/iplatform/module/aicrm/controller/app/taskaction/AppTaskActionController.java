package com.ynet.iplatform.module.aicrm.controller.app.taskaction;

import com.ynet.iplatform.framework.common.pojo.CommonResult;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;
import com.ynet.iplatform.module.aicrm.controller.admin.taskaction.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.taskaction.TaskActionDO;
import com.ynet.iplatform.module.aicrm.service.taskaction.TaskActionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ynet.iplatform.framework.common.pojo.CommonResult.success;

@Tag(name = "移动端 - 任务行动")
@RestController
@RequestMapping("/aicrm/task-action")
@Validated
public class AppTaskActionController {

    @Resource
    private TaskActionService taskActionService;

    @PostMapping("/create")
    @Operation(summary = "创建任务行动")
    public CommonResult<Long> createTaskAction(@Valid @RequestBody TaskActionSaveReqVO createReqVO) {
        return success(taskActionService.createTaskAction(createReqVO));
    }

    @GetMapping("/get")
    @Operation(summary = "获得任务行动详情")
    @Parameter(name = "id", description = "行动编号", required = true, example = "1")
    public CommonResult<TaskActionRespVO> getTaskAction(@RequestParam("id") Long id) {
        TaskActionDO taskAction = taskActionService.getTaskAction(id);
        return success(BeanUtils.toBean(taskAction, TaskActionRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得任务行动分页")
    public CommonResult<PageResult<TaskActionRespVO>> getTaskActionPage(@Valid TaskActionPageReqVO pageReqVO) {
        PageResult<TaskActionDO> pageResult = taskActionService.getTaskActionPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TaskActionRespVO.class));
    }

    @GetMapping("/list-by-task-id")
    @Operation(summary = "根据任务ID获取行动列表")
    @Parameter(name = "taskId", description = "任务ID", required = true, example = "1")
    public CommonResult<List<TaskActionRespVO>> getTaskActionListByTaskId(@RequestParam("taskId") Long taskId) {
        List<TaskActionDO> list = taskActionService.getTaskActionListByTaskId(taskId);
        return success(BeanUtils.toBean(list, TaskActionRespVO.class));
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除任务行动")
    @Parameter(name = "id", description = "编号", required = true)
    public CommonResult<Boolean> deleteTaskAction(@RequestParam("id") Long id) {
        taskActionService.deleteTaskAction(id);
        return success(true);
    }

}
