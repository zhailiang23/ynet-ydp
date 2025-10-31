package cn.iocoder.yudao.module.aicrm.dal.mysql.customerdepositoverview;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerdepositoverview.CustomerDepositOverviewDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerdepositoverview.vo.*;

/**
 * 客户存款业务概览 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerDepositOverviewMapper extends BaseMapperX<CustomerDepositOverviewDO> {

    default PageResult<CustomerDepositOverviewDO> selectPage(CustomerDepositOverviewPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerDepositOverviewDO>()
                .eqIfPresent(CustomerDepositOverviewDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerDepositOverviewDO::getCustomerNo, reqVO.getCustomerNo())
                .betweenIfPresent(CustomerDepositOverviewDO::getStatisticsDate, reqVO.getStatisticsDate())
                .eqIfPresent(CustomerDepositOverviewDO::getDepositType, reqVO.getDepositType())
                .eqIfPresent(CustomerDepositOverviewDO::getCurrency, reqVO.getCurrency())
                .eqIfPresent(CustomerDepositOverviewDO::getAccountNo, reqVO.getAccountNo())
                .eqIfPresent(CustomerDepositOverviewDO::getCardNo, reqVO.getCardNo())
                .eqIfPresent(CustomerDepositOverviewDO::getDepositBalance, reqVO.getDepositBalance())
                .eqIfPresent(CustomerDepositOverviewDO::getBusinessAmount, reqVO.getBusinessAmount())
                .eqIfPresent(CustomerDepositOverviewDO::getOriginalAmount, reqVO.getOriginalAmount())
                .eqIfPresent(CustomerDepositOverviewDO::getDepositAccountCount, reqVO.getDepositAccountCount())
                .eqIfPresent(CustomerDepositOverviewDO::getAccountStatus, reqVO.getAccountStatus())
                .eqIfPresent(CustomerDepositOverviewDO::getSubjectCode, reqVO.getSubjectCode())
                .eqIfPresent(CustomerDepositOverviewDO::getProductId, reqVO.getProductId())
                .eqIfPresent(CustomerDepositOverviewDO::getBalanceYearAvg, reqVO.getBalanceYearAvg())
                .eqIfPresent(CustomerDepositOverviewDO::getRealBalanceYearAvg, reqVO.getRealBalanceYearAvg())
                .eqIfPresent(CustomerDepositOverviewDO::getDepositCumulativeYear, reqVO.getDepositCumulativeYear())
                .eqIfPresent(CustomerDepositOverviewDO::getBalanceQuarterAvg, reqVO.getBalanceQuarterAvg())
                .eqIfPresent(CustomerDepositOverviewDO::getRealBalanceQuarterAvg, reqVO.getRealBalanceQuarterAvg())
                .eqIfPresent(CustomerDepositOverviewDO::getDepositCumulativeQuarter, reqVO.getDepositCumulativeQuarter())
                .eqIfPresent(CustomerDepositOverviewDO::getBalanceMonthAvg, reqVO.getBalanceMonthAvg())
                .eqIfPresent(CustomerDepositOverviewDO::getRealBalanceMonthAvg, reqVO.getRealBalanceMonthAvg())
                .eqIfPresent(CustomerDepositOverviewDO::getDepositCumulativeMonth, reqVO.getDepositCumulativeMonth())
                .eqIfPresent(CustomerDepositOverviewDO::getMonthTotalIn, reqVO.getMonthTotalIn())
                .eqIfPresent(CustomerDepositOverviewDO::getMonthTotalOut, reqVO.getMonthTotalOut())
                .eqIfPresent(CustomerDepositOverviewDO::getBuyAmount, reqVO.getBuyAmount())
                .eqIfPresent(CustomerDepositOverviewDO::getInterestRate, reqVO.getInterestRate())
                .eqIfPresent(CustomerDepositOverviewDO::getFtpPrice, reqVO.getFtpPrice())
                .eqIfPresent(CustomerDepositOverviewDO::getDepositProfit, reqVO.getDepositProfit())
                .betweenIfPresent(CustomerDepositOverviewDO::getOpenDate, reqVO.getOpenDate())
                .betweenIfPresent(CustomerDepositOverviewDO::getStartInterestDate, reqVO.getStartInterestDate())
                .betweenIfPresent(CustomerDepositOverviewDO::getMatureDate, reqVO.getMatureDate())
                .betweenIfPresent(CustomerDepositOverviewDO::getLogoutDate, reqVO.getLogoutDate())
                .eqIfPresent(CustomerDepositOverviewDO::getOrgNo, reqVO.getOrgNo())
                .likeIfPresent(CustomerDepositOverviewDO::getOrgName, reqVO.getOrgName())
                .eqIfPresent(CustomerDepositOverviewDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerDepositOverviewDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerDepositOverviewDO::getId));
    }

}