package com.ynet.iplatform.module.aicrm.dal.mysql.customercontribution;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customercontribution.CustomerContributionDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.customercontribution.vo.*;

/**
 * 客户贡献度信息表（与客户主表1对1关系） Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerContributionMapper extends BaseMapperX<CustomerContributionDO> {

    default PageResult<CustomerContributionDO> selectPage(CustomerContributionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerContributionDO>()
                .eqIfPresent(CustomerContributionDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerContributionDO::getSequenceNo, reqVO.getSequenceNo())
                .eqIfPresent(CustomerContributionDO::getTotalContribution, reqVO.getTotalContribution())
                .eqIfPresent(CustomerContributionDO::getDepositContribution, reqVO.getDepositContribution())
                .eqIfPresent(CustomerContributionDO::getLoanContribution, reqVO.getLoanContribution())
                .eqIfPresent(CustomerContributionDO::getIntermediateContribution, reqVO.getIntermediateContribution())
                .betweenIfPresent(CustomerContributionDO::getStatisticsTime, reqVO.getStatisticsTime())
                .likeIfPresent(CustomerContributionDO::getCustName, reqVO.getCustName())
                .eqIfPresent(CustomerContributionDO::getOrgId, reqVO.getOrgId())
                .eqIfPresent(CustomerContributionDO::getContriDeposit, reqVO.getContriDeposit())
                .eqIfPresent(CustomerContributionDO::getContributionLoan, reqVO.getContributionLoan())
                .eqIfPresent(CustomerContributionDO::getContributionMid, reqVO.getContributionMid())
                .eqIfPresent(CustomerContributionDO::getContributionCust, reqVO.getContributionCust())
                .eqIfPresent(CustomerContributionDO::getContriBillDiscount, reqVO.getContriBillDiscount())
                .eqIfPresent(CustomerContributionDO::getContriInternation, reqVO.getContriInternation())
                .betweenIfPresent(CustomerContributionDO::getEtlDate, reqVO.getEtlDate())
                .eqIfPresent(CustomerContributionDO::getWealthManagementContribution, reqVO.getWealthManagementContribution())
                .eqIfPresent(CustomerContributionDO::getCardContribution, reqVO.getCardContribution())
                .eqIfPresent(CustomerContributionDO::getSettlementContribution, reqVO.getSettlementContribution())
                .eqIfPresent(CustomerContributionDO::getElectronicBankingContribution, reqVO.getElectronicBankingContribution())
                .eqIfPresent(CustomerContributionDO::getContributionRank, reqVO.getContributionRank())
                .eqIfPresent(CustomerContributionDO::getContributionLevel, reqVO.getContributionLevel())
                .eqIfPresent(CustomerContributionDO::getYearOverYearGrowth, reqVO.getYearOverYearGrowth())
                .eqIfPresent(CustomerContributionDO::getMonthOverMonthGrowth, reqVO.getMonthOverMonthGrowth())
                .eqIfPresent(CustomerContributionDO::getStatisticsPeriod, reqVO.getStatisticsPeriod())
                .eqIfPresent(CustomerContributionDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerContributionDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerContributionDO::getId));
    }

}