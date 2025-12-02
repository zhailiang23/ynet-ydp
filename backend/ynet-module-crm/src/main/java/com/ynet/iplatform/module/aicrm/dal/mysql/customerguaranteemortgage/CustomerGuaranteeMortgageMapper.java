package com.ynet.iplatform.module.aicrm.dal.mysql.customerguaranteemortgage;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerguaranteemortgage.CustomerGuaranteeMortgageDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.customerguaranteemortgage.vo.*;

/**
 * 客户抵押物信息表（零售+对公共用） Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface CustomerGuaranteeMortgageMapper extends BaseMapperX<CustomerGuaranteeMortgageDO> {

    default PageResult<CustomerGuaranteeMortgageDO> selectPage(CustomerGuaranteeMortgagePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerGuaranteeMortgageDO>()
                .eqIfPresent(CustomerGuaranteeMortgageDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerGuaranteeMortgageDO::getCreditId, reqVO.getCreditId())
                .eqIfPresent(CustomerGuaranteeMortgageDO::getCollateralNo, reqVO.getCollateralNo())
                .likeIfPresent(CustomerGuaranteeMortgageDO::getCollateralName, reqVO.getCollateralName())
                .eqIfPresent(CustomerGuaranteeMortgageDO::getCollateralType, reqVO.getCollateralType())
                .eqIfPresent(CustomerGuaranteeMortgageDO::getCertificateNo, reqVO.getCertificateNo())
                .eqIfPresent(CustomerGuaranteeMortgageDO::getGuaranteeType, reqVO.getGuaranteeType())
                .likeIfPresent(CustomerGuaranteeMortgageDO::getMortgagorName, reqVO.getMortgagorName())
                .eqIfPresent(CustomerGuaranteeMortgageDO::getMortgagorType, reqVO.getMortgagorType())
                .eqIfPresent(CustomerGuaranteeMortgageDO::getRelationWithBorrower, reqVO.getRelationWithBorrower())
                .eqIfPresent(CustomerGuaranteeMortgageDO::getGuaranteeAmount, reqVO.getGuaranteeAmount())
                .eqIfPresent(CustomerGuaranteeMortgageDO::getManagementBranchId, reqVO.getManagementBranchId())
                .likeIfPresent(CustomerGuaranteeMortgageDO::getManagementBranchName, reqVO.getManagementBranchName())
                .eqIfPresent(CustomerGuaranteeMortgageDO::getMortgagorIdType, reqVO.getMortgagorIdType())
                .eqIfPresent(CustomerGuaranteeMortgageDO::getMortgagorIdNo, reqVO.getMortgagorIdNo())
                .eqIfPresent(CustomerGuaranteeMortgageDO::getCollateralAddress, reqVO.getCollateralAddress())
                .eqIfPresent(CustomerGuaranteeMortgageDO::getCollateralArea, reqVO.getCollateralArea())
                .eqIfPresent(CustomerGuaranteeMortgageDO::getEvaluationValue, reqVO.getEvaluationValue())
                .betweenIfPresent(CustomerGuaranteeMortgageDO::getEvaluationDate, reqVO.getEvaluationDate())
                .eqIfPresent(CustomerGuaranteeMortgageDO::getEvaluationAgency, reqVO.getEvaluationAgency())
                .eqIfPresent(CustomerGuaranteeMortgageDO::getMortgageRate, reqVO.getMortgageRate())
                .eqIfPresent(CustomerGuaranteeMortgageDO::getMortgageStatus, reqVO.getMortgageStatus())
                .betweenIfPresent(CustomerGuaranteeMortgageDO::getMortgageDate, reqVO.getMortgageDate())
                .betweenIfPresent(CustomerGuaranteeMortgageDO::getReleaseDate, reqVO.getReleaseDate())
                .eqIfPresent(CustomerGuaranteeMortgageDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerGuaranteeMortgageDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerGuaranteeMortgageDO::getId));
    }

}