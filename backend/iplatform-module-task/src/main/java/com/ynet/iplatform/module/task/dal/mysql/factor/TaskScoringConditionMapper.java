package com.ynet.iplatform.module.task.dal.mysql.factor;

import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.module.task.dal.dataobject.factor.TaskScoringConditionDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 任务评分条件 Mapper
 *
 * @author ynet
 */
@Mapper
public interface TaskScoringConditionMapper extends BaseMapperX<TaskScoringConditionDO> {

    /**
     * 根据评分因子 ID 查询条件列表
     *
     * @param factorId 评分因子 ID
     * @return 条件列表
     */
    default List<TaskScoringConditionDO> selectListByFactorId(Long factorId) {
        return selectList(new LambdaQueryWrapperX<TaskScoringConditionDO>()
                .eq(TaskScoringConditionDO::getFactorId, factorId)
                .orderByAsc(TaskScoringConditionDO::getSort)
                .orderByDesc(TaskScoringConditionDO::getId));
    }

    /**
     * 根据评分因子 ID 删除所有条件
     *
     * @param factorId 评分因子 ID
     */
    default void deleteByFactorId(Long factorId) {
        delete(new LambdaQueryWrapperX<TaskScoringConditionDO>()
                .eq(TaskScoringConditionDO::getFactorId, factorId));
    }
}
