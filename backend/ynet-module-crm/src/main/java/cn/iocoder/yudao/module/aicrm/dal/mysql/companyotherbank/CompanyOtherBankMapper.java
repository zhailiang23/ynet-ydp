package cn.iocoder.yudao.module.aicrm.dal.mysql.companyotherbank;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.companyotherbank.CompanyOtherBankDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.aicrm.controller.admin.companyotherbank.vo.*;

/**
 * 对公客户他行信息 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CompanyOtherBankMapper extends BaseMapperX<CompanyOtherBankDO> {

    default PageResult<CompanyOtherBankDO> selectPage(CompanyOtherBankPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CompanyOtherBankDO>()
                .eqIfPresent(CompanyOtherBankDO::getCustomerId, reqVO.getCustomerId())
                .likeIfPresent(CompanyOtherBankDO::getBankName, reqVO.getBankName())
                .eqIfPresent(CompanyOtherBankDO::getBankType, reqVO.getBankType())
                .likeIfPresent(CompanyOtherBankDO::getBranchName, reqVO.getBranchName())
                .eqIfPresent(CompanyOtherBankDO::getRelationshipManager, reqVO.getRelationshipManager())
                .eqIfPresent(CompanyOtherBankDO::getManagerPhone, reqVO.getManagerPhone())
                .eqIfPresent(CompanyOtherBankDO::getManagerEmail, reqVO.getManagerEmail())
                .eqIfPresent(CompanyOtherBankDO::getCooperationType, reqVO.getCooperationType())
                .betweenIfPresent(CompanyOtherBankDO::getCooperationStartDate, reqVO.getCooperationStartDate())
                .eqIfPresent(CompanyOtherBankDO::getRelationshipDuration, reqVO.getRelationshipDuration())
                .eqIfPresent(CompanyOtherBankDO::getCooperationStatus, reqVO.getCooperationStatus())
                .eqIfPresent(CompanyOtherBankDO::getHasSettlementAccount, reqVO.getHasSettlementAccount())
                .eqIfPresent(CompanyOtherBankDO::getSettlementAccountNo, reqVO.getSettlementAccountNo())
                .eqIfPresent(CompanyOtherBankDO::getAccountBalance, reqVO.getAccountBalance())
                .eqIfPresent(CompanyOtherBankDO::getIsMainSettlementBank, reqVO.getIsMainSettlementBank())
                .eqIfPresent(CompanyOtherBankDO::getDailyAverageBalance, reqVO.getDailyAverageBalance())
                .eqIfPresent(CompanyOtherBankDO::getTotalCreditLimit, reqVO.getTotalCreditLimit())
                .eqIfPresent(CompanyOtherBankDO::getUsedCreditLimit, reqVO.getUsedCreditLimit())
                .eqIfPresent(CompanyOtherBankDO::getLoanBalance, reqVO.getLoanBalance())
                .eqIfPresent(CompanyOtherBankDO::getDepositBalance, reqVO.getDepositBalance())
                .eqIfPresent(CompanyOtherBankDO::getWealthManagementBalance, reqVO.getWealthManagementBalance())
                .eqIfPresent(CompanyOtherBankDO::getBusinessTypes, reqVO.getBusinessTypes())
                .eqIfPresent(CompanyOtherBankDO::getMainBusiness, reqVO.getMainBusiness())
                .likeIfPresent(CompanyOtherBankDO::getLoanProductName, reqVO.getLoanProductName())
                .eqIfPresent(CompanyOtherBankDO::getLoanAmount, reqVO.getLoanAmount())
                .eqIfPresent(CompanyOtherBankDO::getLoanRate, reqVO.getLoanRate())
                .betweenIfPresent(CompanyOtherBankDO::getLoanStartDate, reqVO.getLoanStartDate())
                .betweenIfPresent(CompanyOtherBankDO::getLoanMaturityDate, reqVO.getLoanMaturityDate())
                .eqIfPresent(CompanyOtherBankDO::getGuaranteeType, reqVO.getGuaranteeType())
                .eqIfPresent(CompanyOtherBankDO::getCollateralInfo, reqVO.getCollateralInfo())
                .eqIfPresent(CompanyOtherBankDO::getServiceSatisfaction, reqVO.getServiceSatisfaction())
                .eqIfPresent(CompanyOtherBankDO::getPricingLevel, reqVO.getPricingLevel())
                .eqIfPresent(CompanyOtherBankDO::getResponseSpeed, reqVO.getResponseSpeed())
                .eqIfPresent(CompanyOtherBankDO::getCustomerComment, reqVO.getCustomerComment())
                .eqIfPresent(CompanyOtherBankDO::getCompetitorAdvantage, reqVO.getCompetitorAdvantage())
                .eqIfPresent(CompanyOtherBankDO::getCompetitorDisadvantage, reqVO.getCompetitorDisadvantage())
                .eqIfPresent(CompanyOtherBankDO::getOurOpportunity, reqVO.getOurOpportunity())
                .eqIfPresent(CompanyOtherBankDO::getCompetitiveStrategy, reqVO.getCompetitiveStrategy())
                .eqIfPresent(CompanyOtherBankDO::getTargetBusiness, reqVO.getTargetBusiness())
                .eqIfPresent(CompanyOtherBankDO::getMarketingPriority, reqVO.getMarketingPriority())
                .eqIfPresent(CompanyOtherBankDO::getContractNo, reqVO.getContractNo())
                .betweenIfPresent(CompanyOtherBankDO::getContractExpiryDate, reqVO.getContractExpiryDate())
                .eqIfPresent(CompanyOtherBankDO::getIsDueSoon, reqVO.getIsDueSoon())
                .eqIfPresent(CompanyOtherBankDO::getFollowUpPlan, reqVO.getFollowUpPlan())
                .eqIfPresent(CompanyOtherBankDO::getRiskWarning, reqVO.getRiskWarning())
                .eqIfPresent(CompanyOtherBankDO::getInfoSource, reqVO.getInfoSource())
                .eqIfPresent(CompanyOtherBankDO::getInfoReliability, reqVO.getInfoReliability())
                .betweenIfPresent(CompanyOtherBankDO::getLastUpdateDate, reqVO.getLastUpdateDate())
                .eqIfPresent(CompanyOtherBankDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CompanyOtherBankDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CompanyOtherBankDO::getId));
    }

}