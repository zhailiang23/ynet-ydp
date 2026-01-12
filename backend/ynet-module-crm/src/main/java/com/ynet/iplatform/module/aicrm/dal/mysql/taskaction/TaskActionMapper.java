package com.ynet.iplatform.module.aicrm.dal.mysql.taskaction;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.taskaction.TaskActionDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.taskaction.vo.*;

/**
 * 任务行动 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface TaskActionMapper extends BaseMapperX<TaskActionDO> {

    default PageResult<TaskActionDO> selectPage(TaskActionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TaskActionDO>()
                .eqIfPresent(TaskActionDO::getTaskId, reqVO.getTaskId())
                .eqIfPresent(TaskActionDO::getActionType, reqVO.getActionType())
                .eqIfPresent(TaskActionDO::getActionUserId, reqVO.getActionUserId())
                .betweenIfPresent(TaskActionDO::getActionTime, reqVO.getActionTime())
                .betweenIfPresent(TaskActionDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TaskActionDO::getId));
    }

    /**
     * 根据任务ID查询行动列表
     */
    default List<TaskActionDO> selectListByTaskId(Long taskId) {
        return selectList(new LambdaQueryWrapperX<TaskActionDO>()
                .eq(TaskActionDO::getTaskId, taskId)
                .orderByDesc(TaskActionDO::getActionTime));
    }

}
