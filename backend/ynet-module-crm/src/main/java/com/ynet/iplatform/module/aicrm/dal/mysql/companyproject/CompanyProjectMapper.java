package com.ynet.iplatform.module.aicrm.dal.mysql.companyproject;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.companyproject.CompanyProjectDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.companyproject.vo.*;

/**
 * 对公客户项目信息 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface CompanyProjectMapper extends BaseMapperX<CompanyProjectDO> {

    default PageResult<CompanyProjectDO> selectPage(CompanyProjectPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CompanyProjectDO>()
                .eqIfPresent(CompanyProjectDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CompanyProjectDO::getProjectCode, reqVO.getProjectCode())
                .likeIfPresent(CompanyProjectDO::getProjectName, reqVO.getProjectName())
                .eqIfPresent(CompanyProjectDO::getProjectType, reqVO.getProjectType())
                .eqIfPresent(CompanyProjectDO::getProjectCategory, reqVO.getProjectCategory())
                .eqIfPresent(CompanyProjectDO::getTotalInvestment, reqVO.getTotalInvestment())
                .eqIfPresent(CompanyProjectDO::getRegisteredCapital, reqVO.getRegisteredCapital())
                .eqIfPresent(CompanyProjectDO::getProjectArea, reqVO.getProjectArea())
                .eqIfPresent(CompanyProjectDO::getBuildingArea, reqVO.getBuildingArea())
                .betweenIfPresent(CompanyProjectDO::getPlanStartDate, reqVO.getPlanStartDate())
                .betweenIfPresent(CompanyProjectDO::getActualStartDate, reqVO.getActualStartDate())
                .betweenIfPresent(CompanyProjectDO::getPlanCompleteDate, reqVO.getPlanCompleteDate())
                .betweenIfPresent(CompanyProjectDO::getActualCompleteDate, reqVO.getActualCompleteDate())
                .eqIfPresent(CompanyProjectDO::getConstructionPeriod, reqVO.getConstructionPeriod())
                .eqIfPresent(CompanyProjectDO::getProjectStatus, reqVO.getProjectStatus())
                .eqIfPresent(CompanyProjectDO::getProgressRate, reqVO.getProgressRate())
                .eqIfPresent(CompanyProjectDO::getProjectProvince, reqVO.getProjectProvince())
                .eqIfPresent(CompanyProjectDO::getProjectCity, reqVO.getProjectCity())
                .eqIfPresent(CompanyProjectDO::getProjectDistrict, reqVO.getProjectDistrict())
                .eqIfPresent(CompanyProjectDO::getProjectAddress, reqVO.getProjectAddress())
                .eqIfPresent(CompanyProjectDO::getSelfFunding, reqVO.getSelfFunding())
                .eqIfPresent(CompanyProjectDO::getBankLoan, reqVO.getBankLoan())
                .eqIfPresent(CompanyProjectDO::getBondFinancing, reqVO.getBondFinancing())
                .eqIfPresent(CompanyProjectDO::getEquityFinancing, reqVO.getEquityFinancing())
                .eqIfPresent(CompanyProjectDO::getGovernmentSubsidy, reqVO.getGovernmentSubsidy())
                .eqIfPresent(CompanyProjectDO::getOtherFunding, reqVO.getOtherFunding())
                .eqIfPresent(CompanyProjectDO::getAccumulatedInvestment, reqVO.getAccumulatedInvestment())
                .eqIfPresent(CompanyProjectDO::getFinancingRequirement, reqVO.getFinancingRequirement())
                .eqIfPresent(CompanyProjectDO::getFinancingPurpose, reqVO.getFinancingPurpose())
                .eqIfPresent(CompanyProjectDO::getFinancingStatus, reqVO.getFinancingStatus())
                .eqIfPresent(CompanyProjectDO::getOurBankFinancing, reqVO.getOurBankFinancing())
                .eqIfPresent(CompanyProjectDO::getOtherBankFinancing, reqVO.getOtherBankFinancing())
                .eqIfPresent(CompanyProjectDO::getPartners, reqVO.getPartners())
                .eqIfPresent(CompanyProjectDO::getContractor, reqVO.getContractor())
                .eqIfPresent(CompanyProjectDO::getDesigner, reqVO.getDesigner())
                .eqIfPresent(CompanyProjectDO::getSupervisor, reqVO.getSupervisor())
                .eqIfPresent(CompanyProjectDO::getExpectedRevenue, reqVO.getExpectedRevenue())
                .eqIfPresent(CompanyProjectDO::getExpectedProfit, reqVO.getExpectedProfit())
                .eqIfPresent(CompanyProjectDO::getPaybackPeriod, reqVO.getPaybackPeriod())
                .eqIfPresent(CompanyProjectDO::getIrrRate, reqVO.getIrrRate())
                .eqIfPresent(CompanyProjectDO::getRiskLevel, reqVO.getRiskLevel())
                .eqIfPresent(CompanyProjectDO::getRiskFactors, reqVO.getRiskFactors())
                .eqIfPresent(CompanyProjectDO::getRiskMitigation, reqVO.getRiskMitigation())
                .eqIfPresent(CompanyProjectDO::getIsKeyProject, reqVO.getIsKeyProject())
                .eqIfPresent(CompanyProjectDO::getGovernmentApproval, reqVO.getGovernmentApproval())
                .eqIfPresent(CompanyProjectDO::getPolicySupport, reqVO.getPolicySupport())
                .eqIfPresent(CompanyProjectDO::getEnvironmentalApproval, reqVO.getEnvironmentalApproval())
                .eqIfPresent(CompanyProjectDO::getLandApproval, reqVO.getLandApproval())
                .eqIfPresent(CompanyProjectDO::getConstructionPermit, reqVO.getConstructionPermit())
                .eqIfPresent(CompanyProjectDO::getProjectManager, reqVO.getProjectManager())
                .eqIfPresent(CompanyProjectDO::getManagerPhone, reqVO.getManagerPhone())
                .eqIfPresent(CompanyProjectDO::getManagerEmail, reqVO.getManagerEmail())
                .eqIfPresent(CompanyProjectDO::getMarketingPriority, reqVO.getMarketingPriority())
                .eqIfPresent(CompanyProjectDO::getMarketingOpportunity, reqVO.getMarketingOpportunity())
                .eqIfPresent(CompanyProjectDO::getFollowUpPlan, reqVO.getFollowUpPlan())
                .betweenIfPresent(CompanyProjectDO::getLastFollowUpDate, reqVO.getLastFollowUpDate())
                .betweenIfPresent(CompanyProjectDO::getNextFollowUpDate, reqVO.getNextFollowUpDate())
                .eqIfPresent(CompanyProjectDO::getDataSource, reqVO.getDataSource())
                .eqIfPresent(CompanyProjectDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CompanyProjectDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CompanyProjectDO::getId));
    }

}