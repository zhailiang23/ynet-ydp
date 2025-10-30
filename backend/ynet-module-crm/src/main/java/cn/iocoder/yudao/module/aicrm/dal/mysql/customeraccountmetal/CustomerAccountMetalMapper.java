package cn.iocoder.yudao.module.aicrm.dal.mysql.customeraccountmetal;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customeraccountmetal.CustomerAccountMetalDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.aicrm.controller.admin.customeraccountmetal.vo.*;

/**
 * 客户贵金属账户信息表（零售+对公共用） Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerAccountMetalMapper extends BaseMapperX<CustomerAccountMetalDO> {

    default PageResult<CustomerAccountMetalDO> selectPage(CustomerAccountMetalPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerAccountMetalDO>()
                .eqIfPresent(CustomerAccountMetalDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerAccountMetalDO::getAccountNo, reqVO.getAccountNo())
                .likeIfPresent(CustomerAccountMetalDO::getProductName, reqVO.getProductName())
                .likeIfPresent(CustomerAccountMetalDO::getAccountName, reqVO.getAccountName())
                .betweenIfPresent(CustomerAccountMetalDO::getOpenDate, reqVO.getOpenDate())
                .betweenIfPresent(CustomerAccountMetalDO::getCloseDate, reqVO.getCloseDate())
                .eqIfPresent(CustomerAccountMetalDO::getAccountStatus, reqVO.getAccountStatus())
                .eqIfPresent(CustomerAccountMetalDO::getMetalType, reqVO.getMetalType())
                .eqIfPresent(CustomerAccountMetalDO::getMetalCategory, reqVO.getMetalCategory())
                .eqIfPresent(CustomerAccountMetalDO::getProductCode, reqVO.getProductCode())
                .eqIfPresent(CustomerAccountMetalDO::getHoldingWeight, reqVO.getHoldingWeight())
                .eqIfPresent(CustomerAccountMetalDO::getAverageCost, reqVO.getAverageCost())
                .eqIfPresent(CustomerAccountMetalDO::getCurrentPrice, reqVO.getCurrentPrice())
                .eqIfPresent(CustomerAccountMetalDO::getCurrentValue, reqVO.getCurrentValue())
                .eqIfPresent(CustomerAccountMetalDO::getAccumulatedIncome, reqVO.getAccumulatedIncome())
                .eqIfPresent(CustomerAccountMetalDO::getProfitRate, reqVO.getProfitRate())
                .eqIfPresent(CustomerAccountMetalDO::getBalance, reqVO.getBalance())
                .eqIfPresent(CustomerAccountMetalDO::getTotalBuyWeight, reqVO.getTotalBuyWeight())
                .eqIfPresent(CustomerAccountMetalDO::getTotalBuyAmount, reqVO.getTotalBuyAmount())
                .eqIfPresent(CustomerAccountMetalDO::getTotalSellWeight, reqVO.getTotalSellWeight())
                .eqIfPresent(CustomerAccountMetalDO::getTotalSellAmount, reqVO.getTotalSellAmount())
                .eqIfPresent(CustomerAccountMetalDO::getCurrencyType, reqVO.getCurrencyType())
                .eqIfPresent(CustomerAccountMetalDO::getDeptId, reqVO.getDeptId())
                .likeIfPresent(CustomerAccountMetalDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(CustomerAccountMetalDO::getManagerUserId, reqVO.getManagerUserId())
                .eqIfPresent(CustomerAccountMetalDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerAccountMetalDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerAccountMetalDO::getId));
    }

}