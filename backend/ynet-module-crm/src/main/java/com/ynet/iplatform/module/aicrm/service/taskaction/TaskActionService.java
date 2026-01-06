package com.ynet.iplatform.module.aicrm.service.taskaction;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.aicrm.controller.admin.taskaction.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.taskaction.TaskActionDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;

/**
 * 任务行动 Service 接口
 *
 * @author 易诚源码
 */
public interface TaskActionService {

    /**
     * 创建任务行动
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTaskAction(@Valid TaskActionSaveReqVO createReqVO);

    /**
     * 更新任务行动
     *
     * @param updateReqVO 更新信息
     */
    void updateTaskAction(@Valid TaskActionSaveReqVO updateReqVO);

    /**
     * 删除任务行动
     *
     * @param id 编号
     */
    void deleteTaskAction(Long id);

    /**
     * 批量删除任务行动
     *
     * @param ids 编号
     */
    void deleteTaskActionListByIds(List<Long> ids);

    /**
     * 获得任务行动
     *
     * @param id 编号
     * @return 任务行动
     */
    TaskActionDO getTaskAction(Long id);

    /**
     * 获得任务行动分页
     *
     * @param pageReqVO 分页查询
     * @return 任务行动分页
     */
    PageResult<TaskActionDO> getTaskActionPage(TaskActionPageReqVO pageReqVO);

    /**
     * 根据任务ID获取行动列表
     *
     * @param taskId 任务ID
     * @return 行动列表
     */
    List<TaskActionDO> getTaskActionListByTaskId(Long taskId);

}
