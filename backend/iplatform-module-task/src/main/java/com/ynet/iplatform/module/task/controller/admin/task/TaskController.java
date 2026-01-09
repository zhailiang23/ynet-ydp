package com.ynet.iplatform.module.task.controller.admin.task;

import com.ynet.iplatform.framework.common.pojo.CommonResult;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;
import com.ynet.iplatform.framework.security.core.util.SecurityFrameworkUtils;
import com.ynet.iplatform.module.system.api.user.AdminUserApi;
import com.ynet.iplatform.module.system.api.user.dto.AdminUserRespDTO;
import com.ynet.iplatform.module.task.controller.admin.task.vo.TaskBatchCreateReqVO;
import com.ynet.iplatform.module.task.controller.admin.task.vo.TaskPageReqVO;
import com.ynet.iplatform.module.task.controller.admin.task.vo.TaskRespVO;
import com.ynet.iplatform.module.task.controller.admin.task.vo.TaskSaveReqVO;
import com.ynet.iplatform.module.task.dal.dataobject.task.TaskDO;
import com.ynet.iplatform.module.task.service.task.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.ynet.iplatform.framework.common.pojo.CommonResult.success;

/**
 * AI智能任务 Controller
 *
 * @author iplatform
 */
@Tag(name = "管理后台 - AI智能任务")
@RestController
@RequestMapping("/task/task")
@Validated
public class TaskController {

    @Resource
    private TaskService taskService;

    @Resource
    private AdminUserApi adminUserApi;

    @PostMapping("/create")
    @Operation(summary = "创建任务")
    @PermitAll // 允许匿名访问
    public CommonResult<Long> createTask(@Valid @RequestBody TaskSaveReqVO createReqVO) {
        return success(taskService.createTask(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新任务")
    @PreAuthorize("@ss.hasPermission('task:task:update')")
    public CommonResult<Boolean> updateTask(@Valid @RequestBody TaskSaveReqVO updateReqVO) {
        taskService.updateTask(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除任务")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('task:task:delete')")
    public CommonResult<Boolean> deleteTask(@RequestParam("id") Long id) {
        taskService.deleteTask(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除任务")
    @Parameter(name = "ids", description = "编号列表", required = true)
    @PreAuthorize("@ss.hasPermission('task:task:delete')")
    public CommonResult<Boolean> deleteTaskList(@RequestParam("ids") List<Long> ids) {
        taskService.deleteTaskList(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得任务")
    @Parameter(name = "id", description = "编号", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('task:task:query')")
    public CommonResult<TaskRespVO> getTask(@RequestParam("id") Long id) {
        TaskDO task = taskService.getTask(id);
        TaskRespVO respVO = BeanUtils.toBean(task, TaskRespVO.class);

        // 填充负责人姓名
        if (respVO.getResponsibleUserId() != null) {
            AdminUserRespDTO user = adminUserApi.getUser(respVO.getResponsibleUserId());
            if (user != null) {
                respVO.setResponsibleUserName(user.getNickname());
            }
        }

        return success(respVO);
    }

    @GetMapping("/page")
    @Operation(summary = "获得任务分页")
    @PreAuthorize("@ss.hasPermission('task:task:query')")
    public CommonResult<PageResult<TaskRespVO>> getTaskPage(@Valid TaskPageReqVO pageReqVO) {
        // 如果前端传递了 responsibleUserId，则使用前端传递的值进行过滤
        // 这样支持移动端场景：只查看当前用户的任务
        PageResult<TaskDO> pageResult = taskService.getTaskPage(pageReqVO);
        PageResult<TaskRespVO> result = BeanUtils.toBean(pageResult, TaskRespVO.class);

        // 填充负责人姓名
        Set<Long> userIds = result.getList().stream()
                .map(TaskRespVO::getResponsibleUserId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        if (!userIds.isEmpty()) {
            Map<Long, AdminUserRespDTO> userMap = adminUserApi.getUserMap(userIds);
            result.getList().forEach(task -> {
                if (task.getResponsibleUserId() != null) {
                    AdminUserRespDTO user = userMap.get(task.getResponsibleUserId());
                    if (user != null) {
                        task.setResponsibleUserName(user.getNickname());
                    }
                }
            });
        }

        return success(result);
    }

    @GetMapping("/list-by-customer-id")
    @Operation(summary = "根据客户ID获取任务列表")
    @Parameter(name = "customerId", description = "客户ID", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('task:task:query')")
    public CommonResult<List<TaskRespVO>> getTaskListByCustomerId(@RequestParam("customerId") Long customerId) {
        List<TaskDO> taskList = taskService.getTaskListByCustomerId(customerId);
        List<TaskRespVO> result = BeanUtils.toBean(taskList, TaskRespVO.class);

        // 填充负责人姓名
        Set<Long> userIds = result.stream()
                .map(TaskRespVO::getResponsibleUserId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        if (!userIds.isEmpty()) {
            Map<Long, AdminUserRespDTO> userMap = adminUserApi.getUserMap(userIds);
            result.forEach(task -> {
                if (task.getResponsibleUserId() != null) {
                    AdminUserRespDTO user = userMap.get(task.getResponsibleUserId());
                    if (user != null) {
                        task.setResponsibleUserName(user.getNickname());
                    }
                }
            });
        }

        return success(result);
    }

    @PostMapping("/batch-create-from-csv")
    @Operation(summary = "基于 CSV 文件批量创建任务")
    @PermitAll // 允许匿名访问
    public CommonResult<Integer> batchCreateTasksFromCsv(@Valid @RequestBody TaskBatchCreateReqVO reqVO) {
        int createdCount = taskService.batchCreateTasksFromCsv(reqVO);
        return success(createdCount);
    }

}
