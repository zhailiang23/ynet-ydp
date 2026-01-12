package com.ynet.iplatform.module.grid.dal.mysql.activitylog;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.grid.dal.dataobject.activitylog.GridActivityLogDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.grid.controller.admin.activitylog.vo.*;

/**
 * 网格活动记录 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface GridActivityLogMapper extends BaseMapperX<GridActivityLogDO> {

    default PageResult<GridActivityLogDO> selectPage(GridActivityLogPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GridActivityLogDO>()
                .eqIfPresent(GridActivityLogDO::getGridId, reqVO.getGridId())
                .eqIfPresent(GridActivityLogDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(GridActivityLogDO::getActivityType, reqVO.getActivityType())
                .eqIfPresent(GridActivityLogDO::getActivityTitle, reqVO.getActivityTitle())
                .eqIfPresent(GridActivityLogDO::getActivityContent, reqVO.getActivityContent())
                .betweenIfPresent(GridActivityLogDO::getActivityDate, reqVO.getActivityDate())
                .eqIfPresent(GridActivityLogDO::getLocation, reqVO.getLocation())
                .eqIfPresent(GridActivityLogDO::getAddress, reqVO.getAddress())
                .eqIfPresent(GridActivityLogDO::getStaffId, reqVO.getStaffId())
                .eqIfPresent(GridActivityLogDO::getResult, reqVO.getResult())
                .eqIfPresent(GridActivityLogDO::getAttachments, reqVO.getAttachments())
                .eqIfPresent(GridActivityLogDO::getCreator, reqVO.getCreatorId())
                .betweenIfPresent(GridActivityLogDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(GridActivityLogDO::getUpdater, reqVO.getUpdaterId())
                .orderByDesc(GridActivityLogDO::getId));
    }

}