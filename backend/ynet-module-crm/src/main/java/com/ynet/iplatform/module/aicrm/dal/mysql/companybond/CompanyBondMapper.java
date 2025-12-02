package com.ynet.iplatform.module.aicrm.dal.mysql.companybond;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.companybond.CompanyBondDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.companybond.vo.*;

/**
 * 对公客户债券信息 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface CompanyBondMapper extends BaseMapperX<CompanyBondDO> {

    default PageResult<CompanyBondDO> selectPage(CompanyBondPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CompanyBondDO>()
                .eqIfPresent(CompanyBondDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CompanyBondDO::getBondCode, reqVO.getBondCode())
                .likeIfPresent(CompanyBondDO::getBondName, reqVO.getBondName())
                .eqIfPresent(CompanyBondDO::getBondType, reqVO.getBondType())
                .eqIfPresent(CompanyBondDO::getBondSubtype, reqVO.getBondSubtype())
                .betweenIfPresent(CompanyBondDO::getIssueDate, reqVO.getIssueDate())
                .eqIfPresent(CompanyBondDO::getIssueAmount, reqVO.getIssueAmount())
                .eqIfPresent(CompanyBondDO::getIssuePrice, reqVO.getIssuePrice())
                .eqIfPresent(CompanyBondDO::getParValue, reqVO.getParValue())
                .eqIfPresent(CompanyBondDO::getIssueScale, reqVO.getIssueScale())
                .betweenIfPresent(CompanyBondDO::getMaturityDate, reqVO.getMaturityDate())
                .eqIfPresent(CompanyBondDO::getTermYears, reqVO.getTermYears())
                .eqIfPresent(CompanyBondDO::getCouponRate, reqVO.getCouponRate())
                .eqIfPresent(CompanyBondDO::getInterestType, reqVO.getInterestType())
                .eqIfPresent(CompanyBondDO::getPaymentFrequency, reqVO.getPaymentFrequency())
                .eqIfPresent(CompanyBondDO::getCreditRating, reqVO.getCreditRating())
                .eqIfPresent(CompanyBondDO::getRatingAgency, reqVO.getRatingAgency())
                .betweenIfPresent(CompanyBondDO::getRatingDate, reqVO.getRatingDate())
                .eqIfPresent(CompanyBondDO::getUnderwriter, reqVO.getUnderwriter())
                .eqIfPresent(CompanyBondDO::getListingExchange, reqVO.getListingExchange())
                .betweenIfPresent(CompanyBondDO::getListingDate, reqVO.getListingDate())
                .eqIfPresent(CompanyBondDO::getBondStatus, reqVO.getBondStatus())
                .eqIfPresent(CompanyBondDO::getGuaranteeType, reqVO.getGuaranteeType())
                .eqIfPresent(CompanyBondDO::getGuarantor, reqVO.getGuarantor())
                .eqIfPresent(CompanyBondDO::getEnhancementMeasures, reqVO.getEnhancementMeasures())
                .eqIfPresent(CompanyBondDO::getIsConvertible, reqVO.getIsConvertible())
                .eqIfPresent(CompanyBondDO::getConversionPrice, reqVO.getConversionPrice())
                .betweenIfPresent(CompanyBondDO::getConversionStartDate, reqVO.getConversionStartDate())
                .betweenIfPresent(CompanyBondDO::getConversionEndDate, reqVO.getConversionEndDate())
                .eqIfPresent(CompanyBondDO::getConversionStockCode, reqVO.getConversionStockCode())
                .eqIfPresent(CompanyBondDO::getCurrentPrice, reqVO.getCurrentPrice())
                .eqIfPresent(CompanyBondDO::getYieldToMaturity, reqVO.getYieldToMaturity())
                .eqIfPresent(CompanyBondDO::getOutstandingAmount, reqVO.getOutstandingAmount())
                .betweenIfPresent(CompanyBondDO::getPriceUpdateTime, reqVO.getPriceUpdateTime())
                .eqIfPresent(CompanyBondDO::getUseOfProceeds, reqVO.getUseOfProceeds())
                .eqIfPresent(CompanyBondDO::getIsGreenBond, reqVO.getIsGreenBond())
                .eqIfPresent(CompanyBondDO::getSpecialClause, reqVO.getSpecialClause())
                .eqIfPresent(CompanyBondDO::getDataSource, reqVO.getDataSource())
                .eqIfPresent(CompanyBondDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CompanyBondDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CompanyBondDO::getId));
    }

}