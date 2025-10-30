package cn.iocoder.yudao.module.aicrm.dal.mysql.customeraccountloan;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customeraccountloan.CustomerAccountLoanDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.aicrm.controller.admin.customeraccountloan.vo.*;

/**
 * 客户贷款账户信息表（零售+对公共用） Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerAccountLoanMapper extends BaseMapperX<CustomerAccountLoanDO> {

    default PageResult<CustomerAccountLoanDO> selectPage(CustomerAccountLoanPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerAccountLoanDO>()
                .eqIfPresent(CustomerAccountLoanDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerAccountLoanDO::getAccountNo, reqVO.getAccountNo())
                .eqIfPresent(CustomerAccountLoanDO::getContractNo, reqVO.getContractNo())
                .eqIfPresent(CustomerAccountLoanDO::getAgrNo, reqVO.getAgrNo())
                .likeIfPresent(CustomerAccountLoanDO::getProductName, reqVO.getProductName())
                .eqIfPresent(CustomerAccountLoanDO::getProductId, reqVO.getProductId())
                .likeIfPresent(CustomerAccountLoanDO::getAccountName, reqVO.getAccountName())
                .betweenIfPresent(CustomerAccountLoanDO::getOpenDate, reqVO.getOpenDate())
                .betweenIfPresent(CustomerAccountLoanDO::getCloseDate, reqVO.getCloseDate())
                .eqIfPresent(CustomerAccountLoanDO::getAccountStatus, reqVO.getAccountStatus())
                .eqIfPresent(CustomerAccountLoanDO::getContractAmount, reqVO.getContractAmount())
                .eqIfPresent(CustomerAccountLoanDO::getLoanAmount, reqVO.getLoanAmount())
                .eqIfPresent(CustomerAccountLoanDO::getBalance, reqVO.getBalance())
                .eqIfPresent(CustomerAccountLoanDO::getCurrencyType, reqVO.getCurrencyType())
                .eqIfPresent(CustomerAccountLoanDO::getInterestRate, reqVO.getInterestRate())
                .eqIfPresent(CustomerAccountLoanDO::getLoanTerm, reqVO.getLoanTerm())
                .eqIfPresent(CustomerAccountLoanDO::getLoanTermUnit, reqVO.getLoanTermUnit())
                .betweenIfPresent(CustomerAccountLoanDO::getMatureDate, reqVO.getMatureDate())
                .eqIfPresent(CustomerAccountLoanDO::getRepaymentMode, reqVO.getRepaymentMode())
                .eqIfPresent(CustomerAccountLoanDO::getLoanPurpose, reqVO.getLoanPurpose())
                .eqIfPresent(CustomerAccountLoanDO::getLoanType, reqVO.getLoanType())
                .eqIfPresent(CustomerAccountLoanDO::getGuaranteeType, reqVO.getGuaranteeType())
                .eqIfPresent(CustomerAccountLoanDO::getBusinessType, reqVO.getBusinessType())
                .eqIfPresent(CustomerAccountLoanDO::getFiveLevelClass, reqVO.getFiveLevelClass())
                .eqIfPresent(CustomerAccountLoanDO::getOverdueDays, reqVO.getOverdueDays())
                .eqIfPresent(CustomerAccountLoanDO::getOverduePrincipal, reqVO.getOverduePrincipal())
                .eqIfPresent(CustomerAccountLoanDO::getOverdueInterest, reqVO.getOverdueInterest())
                .eqIfPresent(CustomerAccountLoanDO::getOverdueTimes, reqVO.getOverdueTimes())
                .eqIfPresent(CustomerAccountLoanDO::getDeptId, reqVO.getDeptId())
                .likeIfPresent(CustomerAccountLoanDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(CustomerAccountLoanDO::getManagerUserId, reqVO.getManagerUserId())
                .eqIfPresent(CustomerAccountLoanDO::getMonthAvgBalance, reqVO.getMonthAvgBalance())
                .eqIfPresent(CustomerAccountLoanDO::getQuarterAvgBalance, reqVO.getQuarterAvgBalance())
                .eqIfPresent(CustomerAccountLoanDO::getYearAvgBalance, reqVO.getYearAvgBalance())
                .eqIfPresent(CustomerAccountLoanDO::getTotalRepaidAmount, reqVO.getTotalRepaidAmount())
                .eqIfPresent(CustomerAccountLoanDO::getTotalRepaidInterest, reqVO.getTotalRepaidInterest())
                .eqIfPresent(CustomerAccountLoanDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerAccountLoanDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerAccountLoanDO::getId));
    }

}