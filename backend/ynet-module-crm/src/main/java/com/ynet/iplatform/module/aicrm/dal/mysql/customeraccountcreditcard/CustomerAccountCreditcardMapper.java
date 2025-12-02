package com.ynet.iplatform.module.aicrm.dal.mysql.customeraccountcreditcard;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customeraccountcreditcard.CustomerAccountCreditcardDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.customeraccountcreditcard.vo.*;

/**
 * 客户信用卡账户信息表（仅限零售客户） Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface CustomerAccountCreditcardMapper extends BaseMapperX<CustomerAccountCreditcardDO> {

    default PageResult<CustomerAccountCreditcardDO> selectPage(CustomerAccountCreditcardPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerAccountCreditcardDO>()
                .eqIfPresent(CustomerAccountCreditcardDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerAccountCreditcardDO::getAccountNo, reqVO.getAccountNo())
                .eqIfPresent(CustomerAccountCreditcardDO::getCardNo, reqVO.getCardNo())
                .likeIfPresent(CustomerAccountCreditcardDO::getProductName, reqVO.getProductName())
                .likeIfPresent(CustomerAccountCreditcardDO::getAccountName, reqVO.getAccountName())
                .betweenIfPresent(CustomerAccountCreditcardDO::getOpenDate, reqVO.getOpenDate())
                .betweenIfPresent(CustomerAccountCreditcardDO::getCloseDate, reqVO.getCloseDate())
                .eqIfPresent(CustomerAccountCreditcardDO::getAccountStatus, reqVO.getAccountStatus())
                .eqIfPresent(CustomerAccountCreditcardDO::getCardType, reqVO.getCardType())
                .eqIfPresent(CustomerAccountCreditcardDO::getCardLevel, reqVO.getCardLevel())
                .eqIfPresent(CustomerAccountCreditcardDO::getCardBrand, reqVO.getCardBrand())
                .eqIfPresent(CustomerAccountCreditcardDO::getIsMainCard, reqVO.getIsMainCard())
                .eqIfPresent(CustomerAccountCreditcardDO::getMainCardNo, reqVO.getMainCardNo())
                .eqIfPresent(CustomerAccountCreditcardDO::getCurrencyType, reqVO.getCurrencyType())
                .eqIfPresent(CustomerAccountCreditcardDO::getCreditLimit, reqVO.getCreditLimit())
                .eqIfPresent(CustomerAccountCreditcardDO::getAvailableLimit, reqVO.getAvailableLimit())
                .eqIfPresent(CustomerAccountCreditcardDO::getTemporaryLimit, reqVO.getTemporaryLimit())
                .eqIfPresent(CustomerAccountCreditcardDO::getCashLimit, reqVO.getCashLimit())
                .eqIfPresent(CustomerAccountCreditcardDO::getUsedAmount, reqVO.getUsedAmount())
                .eqIfPresent(CustomerAccountCreditcardDO::getBalance, reqVO.getBalance())
                .eqIfPresent(CustomerAccountCreditcardDO::getBillingDay, reqVO.getBillingDay())
                .eqIfPresent(CustomerAccountCreditcardDO::getPaymentDueDay, reqVO.getPaymentDueDay())
                .eqIfPresent(CustomerAccountCreditcardDO::getCurrentBillAmount, reqVO.getCurrentBillAmount())
                .eqIfPresent(CustomerAccountCreditcardDO::getMinPaymentAmount, reqVO.getMinPaymentAmount())
                .eqIfPresent(CustomerAccountCreditcardDO::getUnpaidAmount, reqVO.getUnpaidAmount())
                .betweenIfPresent(CustomerAccountCreditcardDO::getLastPaymentDate, reqVO.getLastPaymentDate())
                .eqIfPresent(CustomerAccountCreditcardDO::getLastPaymentAmount, reqVO.getLastPaymentAmount())
                .eqIfPresent(CustomerAccountCreditcardDO::getOverdueDays, reqVO.getOverdueDays())
                .eqIfPresent(CustomerAccountCreditcardDO::getOverdueAmount, reqVO.getOverdueAmount())
                .eqIfPresent(CustomerAccountCreditcardDO::getOverdueInterest, reqVO.getOverdueInterest())
                .eqIfPresent(CustomerAccountCreditcardDO::getOverdueTimes, reqVO.getOverdueTimes())
                .eqIfPresent(CustomerAccountCreditcardDO::getTotalPoints, reqVO.getTotalPoints())
                .eqIfPresent(CustomerAccountCreditcardDO::getAvailablePoints, reqVO.getAvailablePoints())
                .betweenIfPresent(CustomerAccountCreditcardDO::getPointsExpireDate, reqVO.getPointsExpireDate())
                .betweenIfPresent(CustomerAccountCreditcardDO::getExpireDate, reqVO.getExpireDate())
                .betweenIfPresent(CustomerAccountCreditcardDO::getActivateDate, reqVO.getActivateDate())
                .betweenIfPresent(CustomerAccountCreditcardDO::getLastTransactionDate, reqVO.getLastTransactionDate())
                .eqIfPresent(CustomerAccountCreditcardDO::getDeptId, reqVO.getDeptId())
                .likeIfPresent(CustomerAccountCreditcardDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(CustomerAccountCreditcardDO::getManagerUserId, reqVO.getManagerUserId())
                .eqIfPresent(CustomerAccountCreditcardDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerAccountCreditcardDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerAccountCreditcardDO::getId));
    }

}