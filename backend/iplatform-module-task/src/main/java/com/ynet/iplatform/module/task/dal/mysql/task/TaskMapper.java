package com.ynet.iplatform.module.task.dal.mysql.task;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.module.task.controller.admin.task.vo.TaskPageReqVO;
import com.ynet.iplatform.module.task.controller.app.task.vo.AppTaskPageReqVO;
import com.ynet.iplatform.module.task.dal.dataobject.task.TaskDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * AI智能任务 Mapper
 *
 * @author iplatform
 */
@Mapper
public interface TaskMapper extends BaseMapperX<TaskDO> {

    default PageResult<TaskDO> selectPage(TaskPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TaskDO>()
                .likeIfPresent(TaskDO::getTitle, reqVO.getTitle())
                .eqIfPresent(TaskDO::getTaskType, reqVO.getTaskType())
                .eqIfPresent(TaskDO::getPriority, reqVO.getPriority())
                .eqIfPresent(TaskDO::getStatus, reqVO.getStatus())
                .eqIfPresent(TaskDO::getCategory, reqVO.getCategory())
                .eqIfPresent(TaskDO::getBusinessValue, reqVO.getBusinessValue())
                .eqIfPresent(TaskDO::getCustomerId, reqVO.getCustomerId())
                .betweenIfPresent(TaskDO::getDeadline, reqVO.getDeadlineStart(), reqVO.getDeadlineEnd())
                .betweenIfPresent(TaskDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TaskDO::getComprehensiveScore));
    }

    default PageResult<TaskDO> selectPage(AppTaskPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TaskDO>()
                .eqIfPresent(TaskDO::getStatus, reqVO.getStatus())
                .eqIfPresent(TaskDO::getPriority, reqVO.getPriority())
                .eqIfPresent(TaskDO::getAiGenerated, reqVO.getAiGenerated())
                .likeIfPresent(TaskDO::getCustomerName, reqVO.getCustomerName())
                .geIfPresent(TaskDO::getComprehensiveScore, reqVO.getMinBusinessValue())
                .orderByDesc(TaskDO::getComprehensiveScore)
                .orderByDesc(TaskDO::getCreateTime));
    }

}
