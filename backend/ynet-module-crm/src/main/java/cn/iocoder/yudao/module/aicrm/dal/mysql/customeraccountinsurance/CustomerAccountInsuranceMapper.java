package cn.iocoder.yudao.module.aicrm.dal.mysql.customeraccountinsurance;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customeraccountinsurance.CustomerAccountInsuranceDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.aicrm.controller.admin.customeraccountinsurance.vo.*;

/**
 * 客户保险账户信息表（零售+对公共用） Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerAccountInsuranceMapper extends BaseMapperX<CustomerAccountInsuranceDO> {

    default PageResult<CustomerAccountInsuranceDO> selectPage(CustomerAccountInsurancePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerAccountInsuranceDO>()
                .eqIfPresent(CustomerAccountInsuranceDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerAccountInsuranceDO::getAccountNo, reqVO.getAccountNo())
                .eqIfPresent(CustomerAccountInsuranceDO::getPolicyNo, reqVO.getPolicyNo())
                .likeIfPresent(CustomerAccountInsuranceDO::getProductName, reqVO.getProductName())
                .likeIfPresent(CustomerAccountInsuranceDO::getAccountName, reqVO.getAccountName())
                .betweenIfPresent(CustomerAccountInsuranceDO::getOpenDate, reqVO.getOpenDate())
                .betweenIfPresent(CustomerAccountInsuranceDO::getCloseDate, reqVO.getCloseDate())
                .eqIfPresent(CustomerAccountInsuranceDO::getAccountStatus, reqVO.getAccountStatus())
                .eqIfPresent(CustomerAccountInsuranceDO::getInsuranceType, reqVO.getInsuranceType())
                .eqIfPresent(CustomerAccountInsuranceDO::getInsuranceCompany, reqVO.getInsuranceCompany())
                .eqIfPresent(CustomerAccountInsuranceDO::getInsuranceTerm, reqVO.getInsuranceTerm())
                .eqIfPresent(CustomerAccountInsuranceDO::getPaymentTerm, reqVO.getPaymentTerm())
                .eqIfPresent(CustomerAccountInsuranceDO::getPaymentFrequency, reqVO.getPaymentFrequency())
                .eqIfPresent(CustomerAccountInsuranceDO::getInsuredAmount, reqVO.getInsuredAmount())
                .eqIfPresent(CustomerAccountInsuranceDO::getPremium, reqVO.getPremium())
                .eqIfPresent(CustomerAccountInsuranceDO::getPaidPremium, reqVO.getPaidPremium())
                .eqIfPresent(CustomerAccountInsuranceDO::getCashValue, reqVO.getCashValue())
                .eqIfPresent(CustomerAccountInsuranceDO::getBalance, reqVO.getBalance())
                .eqIfPresent(CustomerAccountInsuranceDO::getCurrencyType, reqVO.getCurrencyType())
                .likeIfPresent(CustomerAccountInsuranceDO::getInsuredName, reqVO.getInsuredName())
                .eqIfPresent(CustomerAccountInsuranceDO::getInsuredRelation, reqVO.getInsuredRelation())
                .likeIfPresent(CustomerAccountInsuranceDO::getBeneficiaryName, reqVO.getBeneficiaryName())
                .betweenIfPresent(CustomerAccountInsuranceDO::getEffectiveDate, reqVO.getEffectiveDate())
                .betweenIfPresent(CustomerAccountInsuranceDO::getExpireDate, reqVO.getExpireDate())
                .betweenIfPresent(CustomerAccountInsuranceDO::getNextPaymentDate, reqVO.getNextPaymentDate())
                .eqIfPresent(CustomerAccountInsuranceDO::getDeptId, reqVO.getDeptId())
                .likeIfPresent(CustomerAccountInsuranceDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(CustomerAccountInsuranceDO::getManagerUserId, reqVO.getManagerUserId())
                .eqIfPresent(CustomerAccountInsuranceDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerAccountInsuranceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerAccountInsuranceDO::getId));
    }

}