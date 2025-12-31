package com.ynet.iplatform.module.task.controller.app.task;

import com.ynet.iplatform.framework.common.pojo.CommonResult;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;
import com.ynet.iplatform.module.task.controller.admin.task.vo.TaskSaveReqVO;
import com.ynet.iplatform.module.task.controller.app.task.vo.AppTaskPageReqVO;
import com.ynet.iplatform.module.task.controller.app.task.vo.AppTaskRespVO;
import com.ynet.iplatform.module.task.controller.app.task.vo.AppTaskStatsRespVO;
import com.ynet.iplatform.module.task.dal.dataobject.task.TaskDO;
import com.ynet.iplatform.module.task.service.task.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.ynet.iplatform.framework.common.pojo.CommonResult.success;

/**
 * 移动端 - AI智能任务 Controller
 *
 * @author iplatform
 */
@Tag(name = "移动端 - AI智能任务")
@RestController
@RequestMapping("/aicrm/task")
@Validated
public class AppTaskController {

    @Resource
    private TaskService taskService;

    @GetMapping("/page")
    @Operation(summary = "获得任务分页（移动端）")
    @PermitAll
    public CommonResult<PageResult<AppTaskRespVO>> getAppTaskPage(@Valid AppTaskPageReqVO pageReqVO) {
        // 转换为管理端的 PageReqVO（复用 Service 层逻辑）
        PageResult<TaskDO> pageResult = taskService.getAppTaskPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AppTaskRespVO.class));
    }

    @GetMapping("/get")
    @Operation(summary = "获得任务详情（移动端）")
    @Parameter(name = "id", description = "编号", required = true, example = "1")
    @PermitAll
    public CommonResult<AppTaskRespVO> getAppTask(@RequestParam("id") Long id) {
        TaskDO task = taskService.getTask(id);
        return success(BeanUtils.toBean(task, AppTaskRespVO.class));
    }

    @PostMapping("/create")
    @Operation(summary = "创建任务（移动端）")
    @PermitAll
    public CommonResult<Long> createAppTask(@Valid @RequestBody TaskSaveReqVO createReqVO) {
        return success(taskService.createTask(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新任务（移动端）")
    @PermitAll
    public CommonResult<Boolean> updateAppTask(@Valid @RequestBody TaskSaveReqVO updateReqVO) {
        taskService.updateTask(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除任务（移动端）")
    @Parameter(name = "id", description = "编号", required = true)
    @PermitAll
    public CommonResult<Boolean> deleteAppTask(@RequestParam("id") Long id) {
        taskService.deleteTask(id);
        return success(true);
    }

    @PostMapping("/complete")
    @Operation(summary = "完成任务（移动端）")
    @Parameter(name = "id", description = "编号", required = true)
    @PermitAll
    public CommonResult<Boolean> completeAppTask(@RequestParam("id") Long id) {
        taskService.completeTask(id);
        return success(true);
    }

    @GetMapping("/stats")
    @Operation(summary = "获得任务统计信息（移动端）")
    @PermitAll
    public CommonResult<AppTaskStatsRespVO> getAppTaskStats() {
        // 获取统计数据
        AppTaskStatsRespVO stats = taskService.getTaskStats();
        return success(stats);
    }

}
