package com.ynet.iplatform.module.task.service.task;

import cn.hutool.core.bean.BeanUtil;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.module.task.controller.admin.task.vo.TaskPageReqVO;
import com.ynet.iplatform.module.task.controller.admin.task.vo.TaskSaveReqVO;
import com.ynet.iplatform.module.task.controller.app.task.vo.AppTaskPageReqVO;
import com.ynet.iplatform.module.task.controller.app.task.vo.AppTaskStatsRespVO;
import com.ynet.iplatform.module.task.dal.dataobject.task.TaskDO;
import com.ynet.iplatform.module.task.dal.mysql.task.TaskMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.module.task.enums.ErrorCodeConstants.TASK_NOT_EXISTS;

/**
 * AI智能任务 Service 实现类
 *
 * @author iplatform
 */
@Service
@Validated
public class TaskServiceImpl implements TaskService {

    @Resource
    private TaskMapper taskMapper;

    @Override
    public Long createTask(TaskSaveReqVO createReqVO) {
        // 插入
        TaskDO task = BeanUtil.toBean(createReqVO, TaskDO.class);
        taskMapper.insert(task);
        // 返回
        return task.getId();
    }

    @Override
    public void updateTask(TaskSaveReqVO updateReqVO) {
        // 校验存在
        if (taskMapper.selectById(updateReqVO.getId()) == null) {
            throw exception(TASK_NOT_EXISTS);
        }
        // 更新
        TaskDO updateObj = BeanUtil.toBean(updateReqVO, TaskDO.class);
        taskMapper.updateById(updateObj);
    }

    @Override
    public void deleteTask(Long id) {
        // 校验存在
        if (taskMapper.selectById(id) == null) {
            throw exception(TASK_NOT_EXISTS);
        }
        // 删除
        taskMapper.deleteById(id);
    }

    @Override
    public void deleteTaskList(List<Long> ids) {
        // 校验存在
        ids.forEach(id -> {
            if (taskMapper.selectById(id) == null) {
                throw exception(TASK_NOT_EXISTS);
            }
        });
        // 删除
        taskMapper.deleteBatchIds(ids);
    }

    @Override
    public TaskDO getTask(Long id) {
        return taskMapper.selectById(id);
    }

    @Override
    public PageResult<TaskDO> getTaskPage(TaskPageReqVO pageReqVO) {
        return taskMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<TaskDO> getAppTaskPage(AppTaskPageReqVO pageReqVO) {
        return taskMapper.selectPage(pageReqVO);
    }

    @Override
    public void completeTask(Long id) {
        // 校验存在
        TaskDO task = validateTaskExists(id);

        // 校验任务未完成
        if (task.getStatus() != null && task.getStatus() == 2) {
            // 任务已完成，无需重复操作
            return;
        }

        // 更新状态为已完成
        TaskDO updateObj = new TaskDO();
        updateObj.setId(id);
        updateObj.setStatus(2);  // 2=已完成
        updateObj.setCompletedTime(LocalDateTime.now());
        taskMapper.updateById(updateObj);
    }

    @Override
    public AppTaskStatsRespVO getTaskStats() {
        // 获取今天的开始和结束时间
        LocalDateTime todayStart = LocalDate.now().atStartOfDay();
        LocalDateTime todayEnd = LocalDate.now().atTime(LocalTime.MAX);

        // 获取昨天的开始和结束时间
        LocalDateTime yesterdayStart = LocalDate.now().minusDays(1).atStartOfDay();
        LocalDateTime yesterdayEnd = LocalDate.now().minusDays(1).atTime(LocalTime.MAX);

        // 查询今天的 AI 待办任务
        List<TaskDO> todayAiTasks = taskMapper.selectList(
            new com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX<TaskDO>()
                .eq(TaskDO::getAiGenerated, 1)
                .eq(TaskDO::getStatus, 0)  // 待办
                .between(TaskDO::getCreateTime, todayStart, todayEnd)
        );

        // 查询昨天的 AI 待办任务
        List<TaskDO> yesterdayAiTasks = taskMapper.selectList(
            new com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX<TaskDO>()
                .eq(TaskDO::getAiGenerated, 1)
                .eq(TaskDO::getStatus, 0)  // 待办
                .between(TaskDO::getCreateTime, yesterdayStart, yesterdayEnd)
        );

        // 计算今天的潜在价值
        BigDecimal todayValue = todayAiTasks.stream()
            .map(TaskDO::getExpectedRevenue)
            .filter(revenue -> revenue != null)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 计算昨天的潜在价值
        BigDecimal yesterdayValue = yesterdayAiTasks.stream()
            .map(TaskDO::getExpectedRevenue)
            .filter(revenue -> revenue != null)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 计算价值变化百分比
        String valueChange = "0%";
        if (yesterdayValue.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal change = todayValue.subtract(yesterdayValue)
                .divide(yesterdayValue, 4, RoundingMode.HALF_UP)
                .multiply(new BigDecimal("100"));
            valueChange = (change.compareTo(BigDecimal.ZERO) > 0 ? "+" : "") +
                change.intValue() + "%";
        } else if (todayValue.compareTo(BigDecimal.ZERO) > 0) {
            valueChange = "+100%";
        }

        // 格式化今日潜在价值
        String totalValue = "¥0";
        if (todayValue.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal valueInWan = todayValue.divide(new BigDecimal("10000"), 0, RoundingMode.HALF_UP);
            totalValue = "¥" + valueInWan + "w";
        }

        // 查询高优先级任务数量 (P0 + P1)
        Long highPriorityCount = taskMapper.selectCount(
            new com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX<TaskDO>()
                .eq(TaskDO::getStatus, 0)  // 待办
                .in(TaskDO::getPriority, "P0", "P1")
        );

        // 查询已完成任务数量
        Long completedCount = taskMapper.selectCount(
            new com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX<TaskDO>()
                .eq(TaskDO::getStatus, 2)  // 已完成
        );

        return AppTaskStatsRespVO.builder()
            .totalValue(totalValue)
            .valueChange(valueChange)
            .highPriorityCount(highPriorityCount.intValue())
            .completedCount(completedCount.intValue())
            .build();
    }

    private TaskDO validateTaskExists(Long id) {
        TaskDO task = taskMapper.selectById(id);
        if (task == null) {
            throw exception(TASK_NOT_EXISTS);
        }
        return task;
    }

}
