package com.ynet.iplatform.module.aicrm.dal.mysql.customerdiscountoverview;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerdiscountoverview.CustomerDiscountOverviewDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.customerdiscountoverview.vo.*;

/**
 * 客户贴现业务概览 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerDiscountOverviewMapper extends BaseMapperX<CustomerDiscountOverviewDO> {

    default PageResult<CustomerDiscountOverviewDO> selectPage(CustomerDiscountOverviewPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerDiscountOverviewDO>()
                .eqIfPresent(CustomerDiscountOverviewDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerDiscountOverviewDO::getCustomerNo, reqVO.getCustomerNo())
                .betweenIfPresent(CustomerDiscountOverviewDO::getStatisticsDate, reqVO.getStatisticsDate())
                .eqIfPresent(CustomerDiscountOverviewDO::getDiscountType, reqVO.getDiscountType())
                .eqIfPresent(CustomerDiscountOverviewDO::getBillNo, reqVO.getBillNo())
                .eqIfPresent(CustomerDiscountOverviewDO::getBillType, reqVO.getBillType())
                .eqIfPresent(CustomerDiscountOverviewDO::getCurrency, reqVO.getCurrency())
                .eqIfPresent(CustomerDiscountOverviewDO::getBillAmount, reqVO.getBillAmount())
                .eqIfPresent(CustomerDiscountOverviewDO::getDiscountAmount, reqVO.getDiscountAmount())
                .eqIfPresent(CustomerDiscountOverviewDO::getDiscountBalance, reqVO.getDiscountBalance())
                .eqIfPresent(CustomerDiscountOverviewDO::getInterestAmount, reqVO.getInterestAmount())
                .eqIfPresent(CustomerDiscountOverviewDO::getActualAmount, reqVO.getActualAmount())
                .eqIfPresent(CustomerDiscountOverviewDO::getDiscountCount, reqVO.getDiscountCount())
                .eqIfPresent(CustomerDiscountOverviewDO::getBalanceYearAvg, reqVO.getBalanceYearAvg())
                .eqIfPresent(CustomerDiscountOverviewDO::getLastYearBalanceYearAvg, reqVO.getLastYearBalanceYearAvg())
                .eqIfPresent(CustomerDiscountOverviewDO::getBalanceQuarterAvg, reqVO.getBalanceQuarterAvg())
                .eqIfPresent(CustomerDiscountOverviewDO::getLastYearBalanceQuarterAvg, reqVO.getLastYearBalanceQuarterAvg())
                .eqIfPresent(CustomerDiscountOverviewDO::getBalanceMonthAvg, reqVO.getBalanceMonthAvg())
                .eqIfPresent(CustomerDiscountOverviewDO::getLastYearBalanceMonthAvg, reqVO.getLastYearBalanceMonthAvg())
                .eqIfPresent(CustomerDiscountOverviewDO::getCumulativeYearAmount, reqVO.getCumulativeYearAmount())
                .eqIfPresent(CustomerDiscountOverviewDO::getLastYearCumulativeAmount, reqVO.getLastYearCumulativeAmount())
                .eqIfPresent(CustomerDiscountOverviewDO::getCumulativeQuarterAmount, reqVO.getCumulativeQuarterAmount())
                .eqIfPresent(CustomerDiscountOverviewDO::getLastYearCumulativeQuarterAmount, reqVO.getLastYearCumulativeQuarterAmount())
                .eqIfPresent(CustomerDiscountOverviewDO::getCumulativeMonthAmount, reqVO.getCumulativeMonthAmount())
                .eqIfPresent(CustomerDiscountOverviewDO::getLastYearCumulativeMonthAmount, reqVO.getLastYearCumulativeMonthAmount())
                .eqIfPresent(CustomerDiscountOverviewDO::getDiscountRate, reqVO.getDiscountRate())
                .eqIfPresent(CustomerDiscountOverviewDO::getDiscountDays, reqVO.getDiscountDays())
                .betweenIfPresent(CustomerDiscountOverviewDO::getBillIssueDate, reqVO.getBillIssueDate())
                .betweenIfPresent(CustomerDiscountOverviewDO::getBillDueDate, reqVO.getBillDueDate())
                .betweenIfPresent(CustomerDiscountOverviewDO::getDiscountDate, reqVO.getDiscountDate())
                .betweenIfPresent(CustomerDiscountOverviewDO::getSettleDate, reqVO.getSettleDate())
                .eqIfPresent(CustomerDiscountOverviewDO::getDrawer, reqVO.getDrawer())
                .eqIfPresent(CustomerDiscountOverviewDO::getPayee, reqVO.getPayee())
                .eqIfPresent(CustomerDiscountOverviewDO::getAcceptor, reqVO.getAcceptor())
                .eqIfPresent(CustomerDiscountOverviewDO::getAcceptanceBank, reqVO.getAcceptanceBank())
                .eqIfPresent(CustomerDiscountOverviewDO::getDiscountStatus, reqVO.getDiscountStatus())
                .eqIfPresent(CustomerDiscountOverviewDO::getSettlementStatus, reqVO.getSettlementStatus())
                .eqIfPresent(CustomerDiscountOverviewDO::getProfitContribution, reqVO.getProfitContribution())
                .eqIfPresent(CustomerDiscountOverviewDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerDiscountOverviewDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerDiscountOverviewDO::getId));
    }

}