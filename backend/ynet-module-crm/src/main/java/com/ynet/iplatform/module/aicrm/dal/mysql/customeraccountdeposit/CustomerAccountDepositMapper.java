package com.ynet.iplatform.module.aicrm.dal.mysql.customeraccountdeposit;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customeraccountdeposit.CustomerAccountDepositDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.customeraccountdeposit.vo.*;

/**
 * 客户存款账户信息表（零售+对公共用） Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface CustomerAccountDepositMapper extends BaseMapperX<CustomerAccountDepositDO> {

    default PageResult<CustomerAccountDepositDO> selectPage(CustomerAccountDepositPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerAccountDepositDO>()
                .eqIfPresent(CustomerAccountDepositDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerAccountDepositDO::getAccountNo, reqVO.getAccountNo())
                .likeIfPresent(CustomerAccountDepositDO::getProductName, reqVO.getProductName())
                .likeIfPresent(CustomerAccountDepositDO::getAccountName, reqVO.getAccountName())
                .betweenIfPresent(CustomerAccountDepositDO::getOpenDate, reqVO.getOpenDate())
                .betweenIfPresent(CustomerAccountDepositDO::getCloseDate, reqVO.getCloseDate())
                .eqIfPresent(CustomerAccountDepositDO::getAccountStatus, reqVO.getAccountStatus())
                .eqIfPresent(CustomerAccountDepositDO::getInterestRate, reqVO.getInterestRate())
                .eqIfPresent(CustomerAccountDepositDO::getDepositTerm, reqVO.getDepositTerm())
                .eqIfPresent(CustomerAccountDepositDO::getBalance, reqVO.getBalance())
                .eqIfPresent(CustomerAccountDepositDO::getAgrNo, reqVO.getAgrNo())
                .eqIfPresent(CustomerAccountDepositDO::getProductId, reqVO.getProductId())
                .eqIfPresent(CustomerAccountDepositDO::getCardNo, reqVO.getCardNo())
                .eqIfPresent(CustomerAccountDepositDO::getDepositType, reqVO.getDepositType())
                .eqIfPresent(CustomerAccountDepositDO::getCurrencyType, reqVO.getCurrencyType())
                .eqIfPresent(CustomerAccountDepositDO::getOriginalAmount, reqVO.getOriginalAmount())
                .betweenIfPresent(CustomerAccountDepositDO::getMatureDate, reqVO.getMatureDate())
                .betweenIfPresent(CustomerAccountDepositDO::getStartInterestDate, reqVO.getStartInterestDate())
                .eqIfPresent(CustomerAccountDepositDO::getDeptId, reqVO.getDeptId())
                .likeIfPresent(CustomerAccountDepositDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(CustomerAccountDepositDO::getManagerUserId, reqVO.getManagerUserId())
                .eqIfPresent(CustomerAccountDepositDO::getMonthAvgBalance, reqVO.getMonthAvgBalance())
                .eqIfPresent(CustomerAccountDepositDO::getQuarterAvgBalance, reqVO.getQuarterAvgBalance())
                .eqIfPresent(CustomerAccountDepositDO::getYearAvgBalance, reqVO.getYearAvgBalance())
                .eqIfPresent(CustomerAccountDepositDO::getMonthTotalIn, reqVO.getMonthTotalIn())
                .eqIfPresent(CustomerAccountDepositDO::getMonthTotalOut, reqVO.getMonthTotalOut())
                .eqIfPresent(CustomerAccountDepositDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerAccountDepositDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerAccountDepositDO::getId));
    }

}