package com.ynet.iplatform.module.aicrm.dal.mysql.customeraccountwealth;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customeraccountwealth.CustomerAccountWealthDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.customeraccountwealth.vo.*;

/**
 * 客户理财账户信息表（零售+对公共用） Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface CustomerAccountWealthMapper extends BaseMapperX<CustomerAccountWealthDO> {

    default PageResult<CustomerAccountWealthDO> selectPage(CustomerAccountWealthPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerAccountWealthDO>()
                .eqIfPresent(CustomerAccountWealthDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerAccountWealthDO::getAccountNo, reqVO.getAccountNo())
                .eqIfPresent(CustomerAccountWealthDO::getProductCode, reqVO.getProductCode())
                .likeIfPresent(CustomerAccountWealthDO::getProductName, reqVO.getProductName())
                .likeIfPresent(CustomerAccountWealthDO::getAccountName, reqVO.getAccountName())
                .betweenIfPresent(CustomerAccountWealthDO::getOpenDate, reqVO.getOpenDate())
                .betweenIfPresent(CustomerAccountWealthDO::getCloseDate, reqVO.getCloseDate())
                .eqIfPresent(CustomerAccountWealthDO::getAccountStatus, reqVO.getAccountStatus())
                .eqIfPresent(CustomerAccountWealthDO::getProductType, reqVO.getProductType())
                .eqIfPresent(CustomerAccountWealthDO::getRiskLevel, reqVO.getRiskLevel())
                .eqIfPresent(CustomerAccountWealthDO::getExpectedReturnRate, reqVO.getExpectedReturnRate())
                .eqIfPresent(CustomerAccountWealthDO::getActualReturnRate, reqVO.getActualReturnRate())
                .eqIfPresent(CustomerAccountWealthDO::getWealthTerm, reqVO.getWealthTerm())
                .eqIfPresent(CustomerAccountWealthDO::getCurrencyType, reqVO.getCurrencyType())
                .eqIfPresent(CustomerAccountWealthDO::getPurchaseAmount, reqVO.getPurchaseAmount())
                .eqIfPresent(CustomerAccountWealthDO::getCurrentValue, reqVO.getCurrentValue())
                .eqIfPresent(CustomerAccountWealthDO::getAccumulatedIncome, reqVO.getAccumulatedIncome())
                .eqIfPresent(CustomerAccountWealthDO::getBalance, reqVO.getBalance())
                .betweenIfPresent(CustomerAccountWealthDO::getValueDate, reqVO.getValueDate())
                .betweenIfPresent(CustomerAccountWealthDO::getMatureDate, reqVO.getMatureDate())
                .betweenIfPresent(CustomerAccountWealthDO::getNextOpenDate, reqVO.getNextOpenDate())
                .eqIfPresent(CustomerAccountWealthDO::getDeptId, reqVO.getDeptId())
                .likeIfPresent(CustomerAccountWealthDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(CustomerAccountWealthDO::getManagerUserId, reqVO.getManagerUserId())
                .eqIfPresent(CustomerAccountWealthDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerAccountWealthDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerAccountWealthDO::getId));
    }

}