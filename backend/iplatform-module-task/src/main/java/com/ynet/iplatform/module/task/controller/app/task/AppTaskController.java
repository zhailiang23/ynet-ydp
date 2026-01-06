package com.ynet.iplatform.module.task.controller.app.task;

import com.ynet.iplatform.framework.common.pojo.CommonResult;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;
import com.ynet.iplatform.framework.security.core.util.SecurityFrameworkUtils;
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
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public CommonResult<PageResult<AppTaskRespVO>> getAppTaskPage(@Valid AppTaskPageReqVO pageReqVO) {
        // 自动设置当前登录用户为责任人筛选条件，确保用户只能查看自己的任务
        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
        System.out.println("========== DEBUG: Current login user ID = " + loginUserId);
        pageReqVO.setResponsibleUserId(loginUserId);
        System.out.println("========== DEBUG: After set responsibleUserId = " + pageReqVO.getResponsibleUserId());

        // 转换为管理端的 PageReqVO（复用 Service 层逻辑）
        PageResult<TaskDO> pageResult = taskService.getAppTaskPage(pageReqVO);
        System.out.println("========== DEBUG: Query result total = " + pageResult.getTotal());
        return success(BeanUtils.toBean(pageResult, AppTaskRespVO.class));
    }

    @GetMapping("/get")
    @Operation(summary = "获得任务详情（移动端）")
    @Parameter(name = "id", description = "编号", required = true, example = "1")
    public CommonResult<AppTaskRespVO> getAppTask(@RequestParam("id") Long id) {
        TaskDO task = taskService.getTask(id);
        return success(BeanUtils.toBean(task, AppTaskRespVO.class));
    }

    @PostMapping("/create")
    @Operation(summary = "创建任务（移动端）")
    public CommonResult<Long> createAppTask(@Valid @RequestBody TaskSaveReqVO createReqVO) {
        return success(taskService.createTask(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新任务（移动端）")
    public CommonResult<Boolean> updateAppTask(@Valid @RequestBody TaskSaveReqVO updateReqVO) {
        taskService.updateTask(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除任务（移动端）")
    @Parameter(name = "id", description = "编号", required = true)
    public CommonResult<Boolean> deleteAppTask(@RequestParam("id") Long id) {
        taskService.deleteTask(id);
        return success(true);
    }

    @PostMapping("/complete")
    @Operation(summary = "完成任务（移动端）")
    @Parameter(name = "id", description = "编号", required = true)
    public CommonResult<Boolean> completeAppTask(@RequestParam("id") Long id) {
        taskService.completeTask(id);
        return success(true);
    }

    @GetMapping("/stats")
    @Operation(summary = "获得任务统计信息（移动端）")
    public CommonResult<AppTaskStatsRespVO> getAppTaskStats() {
        // 获取统计数据
        AppTaskStatsRespVO stats = taskService.getTaskStats();
        return success(stats);
    }

    @GetMapping("/list-by-customer-id")
    @Operation(summary = "根据客户ID获取任务列表（移动端）")
    @Parameter(name = "customerId", description = "客户ID", required = true, example = "1")
    public CommonResult<List<AppTaskRespVO>> getTaskListByCustomerId(@RequestParam("customerId") Long customerId) {
        List<TaskDO> list = taskService.getTaskListByCustomerId(customerId);
        return success(BeanUtils.toBean(list, AppTaskRespVO.class));
    }

}
