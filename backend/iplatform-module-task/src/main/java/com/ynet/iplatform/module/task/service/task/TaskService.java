package com.ynet.iplatform.module.task.service.task;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.module.task.controller.admin.task.vo.TaskPageReqVO;
import com.ynet.iplatform.module.task.controller.admin.task.vo.TaskSaveReqVO;
import com.ynet.iplatform.module.task.controller.app.task.vo.AppTaskPageReqVO;
import com.ynet.iplatform.module.task.controller.app.task.vo.AppTaskStatsRespVO;
import com.ynet.iplatform.module.task.dal.dataobject.task.TaskDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * AI智能任务 Service 接口
 *
 * @author iplatform
 */
public interface TaskService {

    /**
     * 创建任务
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTask(@Valid TaskSaveReqVO createReqVO);

    /**
     * 更新任务
     *
     * @param updateReqVO 更新信息
     */
    void updateTask(@Valid TaskSaveReqVO updateReqVO);

    /**
     * 删除任务
     *
     * @param id 编号
     */
    void deleteTask(Long id);

    /**
     * 批量删除任务
     *
     * @param ids 编号列表
     */
    void deleteTaskList(List<Long> ids);

    /**
     * 获得任务
     *
     * @param id 编号
     * @return 任务
     */
    TaskDO getTask(Long id);

    /**
     * 获得任务分页
     *
     * @param pageReqVO 分页查询
     * @return 任务分页
     */
    PageResult<TaskDO> getTaskPage(TaskPageReqVO pageReqVO);

    /**
     * 获得移动端任务分页
     *
     * @param pageReqVO 分页查询
     * @return 任务分页
     */
    PageResult<TaskDO> getAppTaskPage(AppTaskPageReqVO pageReqVO);

    /**
     * 完成任务
     *
     * @param id 任务编号
     */
    void completeTask(Long id);

    /**
     * 获取任务统计信息
     *
     * @return 统计信息
     */
    AppTaskStatsRespVO getTaskStats();

    /**
     * 根据客户ID获取任务列表
     *
     * @param customerId 客户ID
     * @return 任务列表
     */
    List<TaskDO> getTaskListByCustomerId(Long customerId);

}
