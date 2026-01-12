package com.ynet.iplatform.module.grid.dal.mysql.datasynclog;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.grid.dal.dataobject.datasynclog.GridDataSyncLogDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.grid.controller.admin.datasynclog.vo.*;

/**
 * 数据同步记录 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface GridDataSyncLogMapper extends BaseMapperX<GridDataSyncLogDO> {

    default PageResult<GridDataSyncLogDO> selectPage(GridDataSyncLogPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GridDataSyncLogDO>()
                .eqIfPresent(GridDataSyncLogDO::getSyncType, reqVO.getSyncType())
                .eqIfPresent(GridDataSyncLogDO::getSyncDirection, reqVO.getSyncDirection())
                .eqIfPresent(GridDataSyncLogDO::getEntityType, reqVO.getEntityType())
                .eqIfPresent(GridDataSyncLogDO::getEntityId, reqVO.getEntityId())
                .eqIfPresent(GridDataSyncLogDO::getSyncStatus, reqVO.getSyncStatus())
                .betweenIfPresent(GridDataSyncLogDO::getSyncTime, reqVO.getSyncTime())
                .eqIfPresent(GridDataSyncLogDO::getRequestData, reqVO.getRequestData())
                .eqIfPresent(GridDataSyncLogDO::getResponseData, reqVO.getResponseData())
                .eqIfPresent(GridDataSyncLogDO::getErrorMessage, reqVO.getErrorMessage())
                .eqIfPresent(GridDataSyncLogDO::getRetryCount, reqVO.getRetryCount())
                .betweenIfPresent(GridDataSyncLogDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GridDataSyncLogDO::getId));
    }

}