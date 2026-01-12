package com.ynet.iplatform.module.task.service.scoring;

import com.ynet.iplatform.module.task.dal.dataobject.task.TaskDO;

import java.util.List;
import java.util.Map;

/**
 * 任务动态评分服务
 *
 * @author ynet
 */
public interface TaskScoringService {

    /**
     * 对任务列表进行动态评分并排序
     * <p>
     * 根据评分因子配置，动态计算每个任务的分数，并按新分数降序排序
     *
     * @param tasks 任务列表
     * @param customerDataMap 客户评分数据映射（key: 客户ID, value: 客户评分数据）
     * @return 重新评分并排序后的任务列表
     */
    List<TaskDO> scoreAndSortTasks(List<TaskDO> tasks, Map<Long, CustomerScoreData> customerDataMap);
}
