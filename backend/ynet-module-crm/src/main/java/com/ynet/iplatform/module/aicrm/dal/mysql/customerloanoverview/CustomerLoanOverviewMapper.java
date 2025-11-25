package com.ynet.iplatform.module.aicrm.dal.mysql.customerloanoverview;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerloanoverview.CustomerLoanOverviewDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.customerloanoverview.vo.*;

/**
 * 客户贷款业务概览 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerLoanOverviewMapper extends BaseMapperX<CustomerLoanOverviewDO> {

    default PageResult<CustomerLoanOverviewDO> selectPage(CustomerLoanOverviewPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerLoanOverviewDO>()
                .eqIfPresent(CustomerLoanOverviewDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerLoanOverviewDO::getCustomerNo, reqVO.getCustomerNo())
                .betweenIfPresent(CustomerLoanOverviewDO::getStatisticsDate, reqVO.getStatisticsDate())
                .eqIfPresent(CustomerLoanOverviewDO::getLoanType, reqVO.getLoanType())
                .eqIfPresent(CustomerLoanOverviewDO::getCurrency, reqVO.getCurrency())
                .eqIfPresent(CustomerLoanOverviewDO::getLoanAmount, reqVO.getLoanAmount())
                .eqIfPresent(CustomerLoanOverviewDO::getLoanAccountCount, reqVO.getLoanAccountCount())
                .eqIfPresent(CustomerLoanOverviewDO::getLoanCustomerCount, reqVO.getLoanCustomerCount())
                .eqIfPresent(CustomerLoanOverviewDO::getNormalBusinessBalance, reqVO.getNormalBusinessBalance())
                .eqIfPresent(CustomerLoanOverviewDO::getOverdueBalance, reqVO.getOverdueBalance())
                .eqIfPresent(CustomerLoanOverviewDO::getBadLoanBalance, reqVO.getBadLoanBalance())
                .eqIfPresent(CustomerLoanOverviewDO::getBusinessBalance, reqVO.getBusinessBalance())
                .eqIfPresent(CustomerLoanOverviewDO::getBalanceYearAvg, reqVO.getBalanceYearAvg())
                .eqIfPresent(CustomerLoanOverviewDO::getLastYearBalanceYearAvg, reqVO.getLastYearBalanceYearAvg())
                .eqIfPresent(CustomerLoanOverviewDO::getBalanceQuarterAvg, reqVO.getBalanceQuarterAvg())
                .eqIfPresent(CustomerLoanOverviewDO::getLastYearBalanceQuarterAvg, reqVO.getLastYearBalanceQuarterAvg())
                .eqIfPresent(CustomerLoanOverviewDO::getBalanceMonthAvg, reqVO.getBalanceMonthAvg())
                .eqIfPresent(CustomerLoanOverviewDO::getLastYearBalanceMonthAvg, reqVO.getLastYearBalanceMonthAvg())
                .eqIfPresent(CustomerLoanOverviewDO::getLoanAmountTotal, reqVO.getLoanAmountTotal())
                .eqIfPresent(CustomerLoanOverviewDO::getLastYearLoanAmount, reqVO.getLastYearLoanAmount())
                .eqIfPresent(CustomerLoanOverviewDO::getBusinessAmount, reqVO.getBusinessAmount())
                .eqIfPresent(CustomerLoanOverviewDO::getCreditTotalAmount, reqVO.getCreditTotalAmount())
                .eqIfPresent(CustomerLoanOverviewDO::getAmountYearAvg, reqVO.getAmountYearAvg())
                .eqIfPresent(CustomerLoanOverviewDO::getLastYearAmountYearAvg, reqVO.getLastYearAmountYearAvg())
                .eqIfPresent(CustomerLoanOverviewDO::getAmountQuarterAvg, reqVO.getAmountQuarterAvg())
                .eqIfPresent(CustomerLoanOverviewDO::getLastYearAmountQuarterAvg, reqVO.getLastYearAmountQuarterAvg())
                .eqIfPresent(CustomerLoanOverviewDO::getAmountMonthAvg, reqVO.getAmountMonthAvg())
                .eqIfPresent(CustomerLoanOverviewDO::getLastYearAmountMonthAvg, reqVO.getLastYearAmountMonthAvg())
                .eqIfPresent(CustomerLoanOverviewDO::getOutsideLoanUsage, reqVO.getOutsideLoanUsage())
                .eqIfPresent(CustomerLoanOverviewDO::getInsideLoanUsage, reqVO.getInsideLoanUsage())
                .eqIfPresent(CustomerLoanOverviewDO::getBillLoanUsage, reqVO.getBillLoanUsage())
                .eqIfPresent(CustomerLoanOverviewDO::getLoanProfit, reqVO.getLoanProfit())
                .eqIfPresent(CustomerLoanOverviewDO::getInterestRate, reqVO.getInterestRate())
                .eqIfPresent(CustomerLoanOverviewDO::getOverdueDays, reqVO.getOverdueDays())
                .eqIfPresent(CustomerLoanOverviewDO::getRiskLevel, reqVO.getRiskLevel())
                .eqIfPresent(CustomerLoanOverviewDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerLoanOverviewDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerLoanOverviewDO::getId));
    }

}