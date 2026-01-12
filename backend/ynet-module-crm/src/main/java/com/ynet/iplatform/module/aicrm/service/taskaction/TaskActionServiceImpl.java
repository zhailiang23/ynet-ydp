package com.ynet.iplatform.module.aicrm.service.taskaction;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.taskaction.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.taskaction.TaskActionDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.taskaction.TaskActionMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 任务行动 Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class TaskActionServiceImpl implements TaskActionService {

    @Resource
    private TaskActionMapper taskActionMapper;

    @Override
    public Long createTaskAction(TaskActionSaveReqVO createReqVO) {
        // 插入
        TaskActionDO taskAction = BeanUtils.toBean(createReqVO, TaskActionDO.class);

        // 手动转换 actionTime 字符串为 LocalDateTime
        if (createReqVO.getActionTime() != null && !createReqVO.getActionTime().trim().isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime actionTime = LocalDateTime.parse(createReqVO.getActionTime(), formatter);
            taskAction.setActionTime(actionTime);
        }

        taskActionMapper.insert(taskAction);

        // 返回
        return taskAction.getId();
    }

    @Override
    public void updateTaskAction(TaskActionSaveReqVO updateReqVO) {
        // 校验存在
        validateTaskActionExists(updateReqVO.getId());

        // 更新
        TaskActionDO updateObj = BeanUtils.toBean(updateReqVO, TaskActionDO.class);

        // 手动转换 actionTime 字符串为 LocalDateTime
        if (updateReqVO.getActionTime() != null && !updateReqVO.getActionTime().trim().isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime actionTime = LocalDateTime.parse(updateReqVO.getActionTime(), formatter);
            updateObj.setActionTime(actionTime);
        }

        taskActionMapper.updateById(updateObj);
    }

    @Override
    public void deleteTaskAction(Long id) {
        // 校验存在
        validateTaskActionExists(id);
        // 删除
        taskActionMapper.deleteById(id);
    }

    @Override
    public void deleteTaskActionListByIds(List<Long> ids) {
        // 删除
        taskActionMapper.deleteByIds(ids);
    }

    private void validateTaskActionExists(Long id) {
        if (taskActionMapper.selectById(id) == null) {
            throw exception(TASK_ACTION_NOT_EXISTS);
        }
    }

    @Override
    public TaskActionDO getTaskAction(Long id) {
        return taskActionMapper.selectById(id);
    }

    @Override
    public PageResult<TaskActionDO> getTaskActionPage(TaskActionPageReqVO pageReqVO) {
        return taskActionMapper.selectPage(pageReqVO);
    }

    @Override
    public List<TaskActionDO> getTaskActionListByTaskId(Long taskId) {
        return taskActionMapper.selectListByTaskId(taskId);
    }

}
