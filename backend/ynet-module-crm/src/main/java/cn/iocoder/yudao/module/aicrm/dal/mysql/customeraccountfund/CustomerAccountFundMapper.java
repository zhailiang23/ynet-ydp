package cn.iocoder.yudao.module.aicrm.dal.mysql.customeraccountfund;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customeraccountfund.CustomerAccountFundDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.aicrm.controller.admin.customeraccountfund.vo.*;

/**
 * 客户基金账户信息表（零售+对公共用） Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerAccountFundMapper extends BaseMapperX<CustomerAccountFundDO> {

    default PageResult<CustomerAccountFundDO> selectPage(CustomerAccountFundPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerAccountFundDO>()
                .eqIfPresent(CustomerAccountFundDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerAccountFundDO::getAccountNo, reqVO.getAccountNo())
                .eqIfPresent(CustomerAccountFundDO::getFundCode, reqVO.getFundCode())
                .likeIfPresent(CustomerAccountFundDO::getProductName, reqVO.getProductName())
                .likeIfPresent(CustomerAccountFundDO::getAccountName, reqVO.getAccountName())
                .betweenIfPresent(CustomerAccountFundDO::getOpenDate, reqVO.getOpenDate())
                .betweenIfPresent(CustomerAccountFundDO::getCloseDate, reqVO.getCloseDate())
                .eqIfPresent(CustomerAccountFundDO::getAccountStatus, reqVO.getAccountStatus())
                .eqIfPresent(CustomerAccountFundDO::getFundType, reqVO.getFundType())
                .eqIfPresent(CustomerAccountFundDO::getFundCompany, reqVO.getFundCompany())
                .eqIfPresent(CustomerAccountFundDO::getRiskLevel, reqVO.getRiskLevel())
                .eqIfPresent(CustomerAccountFundDO::getCurrencyType, reqVO.getCurrencyType())
                .eqIfPresent(CustomerAccountFundDO::getHoldingShares, reqVO.getHoldingShares())
                .eqIfPresent(CustomerAccountFundDO::getPurchaseAmount, reqVO.getPurchaseAmount())
                .eqIfPresent(CustomerAccountFundDO::getCurrentNav, reqVO.getCurrentNav())
                .eqIfPresent(CustomerAccountFundDO::getCurrentValue, reqVO.getCurrentValue())
                .eqIfPresent(CustomerAccountFundDO::getAccumulatedIncome, reqVO.getAccumulatedIncome())
                .eqIfPresent(CustomerAccountFundDO::getBalance, reqVO.getBalance())
                .eqIfPresent(CustomerAccountFundDO::getCostPrice, reqVO.getCostPrice())
                .eqIfPresent(CustomerAccountFundDO::getProfitRate, reqVO.getProfitRate())
                .eqIfPresent(CustomerAccountFundDO::getTodayIncome, reqVO.getTodayIncome())
                .eqIfPresent(CustomerAccountFundDO::getYesterdayIncome, reqVO.getYesterdayIncome())
                .eqIfPresent(CustomerAccountFundDO::getDeptId, reqVO.getDeptId())
                .likeIfPresent(CustomerAccountFundDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(CustomerAccountFundDO::getManagerUserId, reqVO.getManagerUserId())
                .eqIfPresent(CustomerAccountFundDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerAccountFundDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerAccountFundDO::getId));
    }

}