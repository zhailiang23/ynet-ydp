package com.ynet.iplatform.module.aicrm.dal.mysql.customermiddlebusinessoverview;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customermiddlebusinessoverview.CustomerMiddleBusinessOverviewDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.customermiddlebusinessoverview.vo.*;

/**
 * 客户中间业务概览 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface CustomerMiddleBusinessOverviewMapper extends BaseMapperX<CustomerMiddleBusinessOverviewDO> {

    default PageResult<CustomerMiddleBusinessOverviewDO> selectPage(CustomerMiddleBusinessOverviewPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerMiddleBusinessOverviewDO>()
                .eqIfPresent(CustomerMiddleBusinessOverviewDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerMiddleBusinessOverviewDO::getCustomerNo, reqVO.getCustomerNo())
                .betweenIfPresent(CustomerMiddleBusinessOverviewDO::getStatisticsDate, reqVO.getStatisticsDate())
                .eqIfPresent(CustomerMiddleBusinessOverviewDO::getBusinessType, reqVO.getBusinessType())
                .eqIfPresent(CustomerMiddleBusinessOverviewDO::getBusinessCode, reqVO.getBusinessCode())
                .likeIfPresent(CustomerMiddleBusinessOverviewDO::getBusinessName, reqVO.getBusinessName())
                .eqIfPresent(CustomerMiddleBusinessOverviewDO::getCurrency, reqVO.getCurrency())
                .eqIfPresent(CustomerMiddleBusinessOverviewDO::getBusinessAmount, reqVO.getBusinessAmount())
                .eqIfPresent(CustomerMiddleBusinessOverviewDO::getTransactionAmount, reqVO.getTransactionAmount())
                .eqIfPresent(CustomerMiddleBusinessOverviewDO::getFeeAmount, reqVO.getFeeAmount())
                .eqIfPresent(CustomerMiddleBusinessOverviewDO::getCommissionAmount, reqVO.getCommissionAmount())
                .eqIfPresent(CustomerMiddleBusinessOverviewDO::getTransactionCount, reqVO.getTransactionCount())
                .eqIfPresent(CustomerMiddleBusinessOverviewDO::getAmountYearAvg, reqVO.getAmountYearAvg())
                .eqIfPresent(CustomerMiddleBusinessOverviewDO::getLastYearAmountYearAvg, reqVO.getLastYearAmountYearAvg())
                .eqIfPresent(CustomerMiddleBusinessOverviewDO::getAmountQuarterAvg, reqVO.getAmountQuarterAvg())
                .eqIfPresent(CustomerMiddleBusinessOverviewDO::getLastYearAmountQuarterAvg, reqVO.getLastYearAmountQuarterAvg())
                .eqIfPresent(CustomerMiddleBusinessOverviewDO::getAmountMonthAvg, reqVO.getAmountMonthAvg())
                .eqIfPresent(CustomerMiddleBusinessOverviewDO::getLastYearAmountMonthAvg, reqVO.getLastYearAmountMonthAvg())
                .eqIfPresent(CustomerMiddleBusinessOverviewDO::getCumulativeYearAmount, reqVO.getCumulativeYearAmount())
                .eqIfPresent(CustomerMiddleBusinessOverviewDO::getLastYearCumulativeAmount, reqVO.getLastYearCumulativeAmount())
                .eqIfPresent(CustomerMiddleBusinessOverviewDO::getCumulativeQuarterAmount, reqVO.getCumulativeQuarterAmount())
                .eqIfPresent(CustomerMiddleBusinessOverviewDO::getLastYearCumulativeQuarterAmount, reqVO.getLastYearCumulativeQuarterAmount())
                .eqIfPresent(CustomerMiddleBusinessOverviewDO::getCumulativeMonthAmount, reqVO.getCumulativeMonthAmount())
                .eqIfPresent(CustomerMiddleBusinessOverviewDO::getLastYearCumulativeMonthAmount, reqVO.getLastYearCumulativeMonthAmount())
                .eqIfPresent(CustomerMiddleBusinessOverviewDO::getProfitContribution, reqVO.getProfitContribution())
                .eqIfPresent(CustomerMiddleBusinessOverviewDO::getFeeIncome, reqVO.getFeeIncome())
                .eqIfPresent(CustomerMiddleBusinessOverviewDO::getCommissionIncome, reqVO.getCommissionIncome())
                .eqIfPresent(CustomerMiddleBusinessOverviewDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerMiddleBusinessOverviewDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerMiddleBusinessOverviewDO::getId));
    }

}