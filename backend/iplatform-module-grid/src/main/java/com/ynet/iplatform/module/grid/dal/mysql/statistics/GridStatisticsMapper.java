package com.ynet.iplatform.module.grid.dal.mysql.statistics;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.grid.dal.dataobject.statistics.GridStatisticsDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.grid.controller.admin.statistics.vo.*;

/**
 * 网格统计报表 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface GridStatisticsMapper extends BaseMapperX<GridStatisticsDO> {

    default PageResult<GridStatisticsDO> selectPage(GridStatisticsPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GridStatisticsDO>()
                .eqIfPresent(GridStatisticsDO::getGridId, reqVO.getGridId())
                .betweenIfPresent(GridStatisticsDO::getStatDate, reqVO.getStatDate())
                .eqIfPresent(GridStatisticsDO::getStatType, reqVO.getStatType())
                .eqIfPresent(GridStatisticsDO::getCustomerCount, reqVO.getCustomerCount())
                .eqIfPresent(GridStatisticsDO::getNewCustomerCount, reqVO.getNewCustomerCount())
                .eqIfPresent(GridStatisticsDO::getActiveCustomerCount, reqVO.getActiveCustomerCount())
                .eqIfPresent(GridStatisticsDO::getActivityCount, reqVO.getActivityCount())
                .eqIfPresent(GridStatisticsDO::getLoanApplicationCount, reqVO.getLoanApplicationCount())
                .eqIfPresent(GridStatisticsDO::getLoanApprovalCount, reqVO.getLoanApprovalCount())
                .eqIfPresent(GridStatisticsDO::getLoanAmount, reqVO.getLoanAmount())
                .eqIfPresent(GridStatisticsDO::getDepositAmount, reqVO.getDepositAmount())
                .eqIfPresent(GridStatisticsDO::getPerformanceScore, reqVO.getPerformanceScore())
                .betweenIfPresent(GridStatisticsDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GridStatisticsDO::getId));
    }

}