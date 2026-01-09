package com.ynet.iplatform.module.task.dal.mysql.factor;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.module.task.controller.admin.factor.vo.TaskScoringFactorListReqVO;
import com.ynet.iplatform.module.task.controller.admin.factor.vo.TaskScoringFactorPageReqVO;
import com.ynet.iplatform.module.task.dal.dataobject.factor.TaskScoringFactorDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 任务评分因子 Mapper
 *
 * @author ynet
 */
@Mapper
public interface TaskScoringFactorMapper extends BaseMapperX<TaskScoringFactorDO> {

    default PageResult<TaskScoringFactorDO> selectPage(TaskScoringFactorPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TaskScoringFactorDO>()
                .likeIfPresent(TaskScoringFactorDO::getFactorName, reqVO.getFactorName())
                .eqIfPresent(TaskScoringFactorDO::getEnabled, reqVO.getEnabled())
                .orderByAsc(TaskScoringFactorDO::getSort)
                .orderByDesc(TaskScoringFactorDO::getId));
    }

    default List<TaskScoringFactorDO> selectList(TaskScoringFactorListReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<TaskScoringFactorDO>()
                .likeIfPresent(TaskScoringFactorDO::getFactorName, reqVO.getFactorName())
                .eqIfPresent(TaskScoringFactorDO::getEnabled, reqVO.getEnabled())
                .orderByAsc(TaskScoringFactorDO::getSort)
                .orderByDesc(TaskScoringFactorDO::getId));
    }
}
