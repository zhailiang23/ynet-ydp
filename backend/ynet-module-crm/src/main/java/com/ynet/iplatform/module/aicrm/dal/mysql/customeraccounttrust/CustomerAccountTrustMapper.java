package com.ynet.iplatform.module.aicrm.dal.mysql.customeraccounttrust;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customeraccounttrust.CustomerAccountTrustDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.customeraccounttrust.vo.*;

/**
 * 客户信托账户信息表（零售+对公共用） Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface CustomerAccountTrustMapper extends BaseMapperX<CustomerAccountTrustDO> {

    default PageResult<CustomerAccountTrustDO> selectPage(CustomerAccountTrustPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerAccountTrustDO>()
                .eqIfPresent(CustomerAccountTrustDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerAccountTrustDO::getAccountNo, reqVO.getAccountNo())
                .eqIfPresent(CustomerAccountTrustDO::getTrustContractNo, reqVO.getTrustContractNo())
                .likeIfPresent(CustomerAccountTrustDO::getProductName, reqVO.getProductName())
                .likeIfPresent(CustomerAccountTrustDO::getAccountName, reqVO.getAccountName())
                .betweenIfPresent(CustomerAccountTrustDO::getOpenDate, reqVO.getOpenDate())
                .betweenIfPresent(CustomerAccountTrustDO::getCloseDate, reqVO.getCloseDate())
                .eqIfPresent(CustomerAccountTrustDO::getAccountStatus, reqVO.getAccountStatus())
                .eqIfPresent(CustomerAccountTrustDO::getTrustType, reqVO.getTrustType())
                .eqIfPresent(CustomerAccountTrustDO::getTrustCompany, reqVO.getTrustCompany())
                .eqIfPresent(CustomerAccountTrustDO::getTrustPurpose, reqVO.getTrustPurpose())
                .eqIfPresent(CustomerAccountTrustDO::getExpectedReturnRate, reqVO.getExpectedReturnRate())
                .eqIfPresent(CustomerAccountTrustDO::getTrustTerm, reqVO.getTrustTerm())
                .eqIfPresent(CustomerAccountTrustDO::getCurrencyType, reqVO.getCurrencyType())
                .eqIfPresent(CustomerAccountTrustDO::getTrustAmount, reqVO.getTrustAmount())
                .eqIfPresent(CustomerAccountTrustDO::getPaidAmount, reqVO.getPaidAmount())
                .eqIfPresent(CustomerAccountTrustDO::getCurrentValue, reqVO.getCurrentValue())
                .eqIfPresent(CustomerAccountTrustDO::getAccumulatedIncome, reqVO.getAccumulatedIncome())
                .eqIfPresent(CustomerAccountTrustDO::getBalance, reqVO.getBalance())
                .likeIfPresent(CustomerAccountTrustDO::getBeneficiaryName, reqVO.getBeneficiaryName())
                .eqIfPresent(CustomerAccountTrustDO::getBeneficiaryRelation, reqVO.getBeneficiaryRelation())
                .betweenIfPresent(CustomerAccountTrustDO::getEffectiveDate, reqVO.getEffectiveDate())
                .betweenIfPresent(CustomerAccountTrustDO::getMatureDate, reqVO.getMatureDate())
                .betweenIfPresent(CustomerAccountTrustDO::getNextDistributionDate, reqVO.getNextDistributionDate())
                .eqIfPresent(CustomerAccountTrustDO::getDeptId, reqVO.getDeptId())
                .likeIfPresent(CustomerAccountTrustDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(CustomerAccountTrustDO::getManagerUserId, reqVO.getManagerUserId())
                .eqIfPresent(CustomerAccountTrustDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerAccountTrustDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerAccountTrustDO::getId));
    }

}