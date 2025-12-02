package com.ynet.iplatform.module.aicrm.dal.mysql.customeroffbalanceoverview;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customeroffbalanceoverview.CustomerOffbalanceOverviewDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.customeroffbalanceoverview.vo.*;

/**
 * 客户表外业务概览 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface CustomerOffbalanceOverviewMapper extends BaseMapperX<CustomerOffbalanceOverviewDO> {

    default PageResult<CustomerOffbalanceOverviewDO> selectPage(CustomerOffbalanceOverviewPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerOffbalanceOverviewDO>()
                .eqIfPresent(CustomerOffbalanceOverviewDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerOffbalanceOverviewDO::getCustomerNo, reqVO.getCustomerNo())
                .betweenIfPresent(CustomerOffbalanceOverviewDO::getStatisticsDate, reqVO.getStatisticsDate())
                .eqIfPresent(CustomerOffbalanceOverviewDO::getBusinessType, reqVO.getBusinessType())
                .eqIfPresent(CustomerOffbalanceOverviewDO::getBusinessCode, reqVO.getBusinessCode())
                .likeIfPresent(CustomerOffbalanceOverviewDO::getBusinessName, reqVO.getBusinessName())
                .eqIfPresent(CustomerOffbalanceOverviewDO::getCurrency, reqVO.getCurrency())
                .eqIfPresent(CustomerOffbalanceOverviewDO::getBusinessAmount, reqVO.getBusinessAmount())
                .eqIfPresent(CustomerOffbalanceOverviewDO::getBusinessBalance, reqVO.getBusinessBalance())
                .eqIfPresent(CustomerOffbalanceOverviewDO::getGuaranteeAmount, reqVO.getGuaranteeAmount())
                .eqIfPresent(CustomerOffbalanceOverviewDO::getCreditAmount, reqVO.getCreditAmount())
                .eqIfPresent(CustomerOffbalanceOverviewDO::getUsedAmount, reqVO.getUsedAmount())
                .eqIfPresent(CustomerOffbalanceOverviewDO::getAvailableAmount, reqVO.getAvailableAmount())
                .eqIfPresent(CustomerOffbalanceOverviewDO::getBusinessCount, reqVO.getBusinessCount())
                .eqIfPresent(CustomerOffbalanceOverviewDO::getBalanceYearAvg, reqVO.getBalanceYearAvg())
                .eqIfPresent(CustomerOffbalanceOverviewDO::getLastYearBalanceYearAvg, reqVO.getLastYearBalanceYearAvg())
                .eqIfPresent(CustomerOffbalanceOverviewDO::getBalanceQuarterAvg, reqVO.getBalanceQuarterAvg())
                .eqIfPresent(CustomerOffbalanceOverviewDO::getLastYearBalanceQuarterAvg, reqVO.getLastYearBalanceQuarterAvg())
                .eqIfPresent(CustomerOffbalanceOverviewDO::getBalanceMonthAvg, reqVO.getBalanceMonthAvg())
                .eqIfPresent(CustomerOffbalanceOverviewDO::getLastYearBalanceMonthAvg, reqVO.getLastYearBalanceMonthAvg())
                .eqIfPresent(CustomerOffbalanceOverviewDO::getCumulativeYearAmount, reqVO.getCumulativeYearAmount())
                .eqIfPresent(CustomerOffbalanceOverviewDO::getLastYearCumulativeAmount, reqVO.getLastYearCumulativeAmount())
                .eqIfPresent(CustomerOffbalanceOverviewDO::getCumulativeQuarterAmount, reqVO.getCumulativeQuarterAmount())
                .eqIfPresent(CustomerOffbalanceOverviewDO::getLastYearCumulativeQuarterAmount, reqVO.getLastYearCumulativeQuarterAmount())
                .eqIfPresent(CustomerOffbalanceOverviewDO::getCumulativeMonthAmount, reqVO.getCumulativeMonthAmount())
                .eqIfPresent(CustomerOffbalanceOverviewDO::getLastYearCumulativeMonthAmount, reqVO.getLastYearCumulativeMonthAmount())
                .eqIfPresent(CustomerOffbalanceOverviewDO::getUsageRate, reqVO.getUsageRate())
                .betweenIfPresent(CustomerOffbalanceOverviewDO::getStartDate, reqVO.getStartDate())
                .betweenIfPresent(CustomerOffbalanceOverviewDO::getEndDate, reqVO.getEndDate())
                .betweenIfPresent(CustomerOffbalanceOverviewDO::getEffectiveDate, reqVO.getEffectiveDate())
                .eqIfPresent(CustomerOffbalanceOverviewDO::getFeeRate, reqVO.getFeeRate())
                .eqIfPresent(CustomerOffbalanceOverviewDO::getFeeAmount, reqVO.getFeeAmount())
                .eqIfPresent(CustomerOffbalanceOverviewDO::getBusinessStatus, reqVO.getBusinessStatus())
                .eqIfPresent(CustomerOffbalanceOverviewDO::getRiskLevel, reqVO.getRiskLevel())
                .eqIfPresent(CustomerOffbalanceOverviewDO::getProfitContribution, reqVO.getProfitContribution())
                .eqIfPresent(CustomerOffbalanceOverviewDO::getFeeIncome, reqVO.getFeeIncome())
                .eqIfPresent(CustomerOffbalanceOverviewDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerOffbalanceOverviewDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerOffbalanceOverviewDO::getId));
    }

}