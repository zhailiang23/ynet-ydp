package cn.iocoder.yudao.module.aicrm.dal.mysql.customerpoints;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerpoints.CustomerPointsDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerpoints.vo.*;

/**
 * 客户积分信息 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerPointsMapper extends BaseMapperX<CustomerPointsDO> {

    default PageResult<CustomerPointsDO> selectPage(CustomerPointsPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerPointsDO>()
                .eqIfPresent(CustomerPointsDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerPointsDO::getAvailablePoints, reqVO.getAvailablePoints())
                .eqIfPresent(CustomerPointsDO::getHistoryTotalGiftPoints, reqVO.getHistoryTotalGiftPoints())
                .eqIfPresent(CustomerPointsDO::getHistoryTotalDeductPoints, reqVO.getHistoryTotalDeductPoints())
                .eqIfPresent(CustomerPointsDO::getHistoryTotalExpirePoints, reqVO.getHistoryTotalExpirePoints())
                .eqIfPresent(CustomerPointsDO::getUpcomingExpirePoints, reqVO.getUpcomingExpirePoints())
                .betweenIfPresent(CustomerPointsDO::getUpcomingExpireDate, reqVO.getUpcomingExpireDate())
                .eqIfPresent(CustomerPointsDO::getPointsAccountNo, reqVO.getPointsAccountNo())
                .eqIfPresent(CustomerPointsDO::getPointsLevel, reqVO.getPointsLevel())
                .eqIfPresent(CustomerPointsDO::getTotalEarnedPoints, reqVO.getTotalEarnedPoints())
                .eqIfPresent(CustomerPointsDO::getTotalUsedPoints, reqVO.getTotalUsedPoints())
                .eqIfPresent(CustomerPointsDO::getFrozenPoints, reqVO.getFrozenPoints())
                .eqIfPresent(CustomerPointsDO::getHistoryTotalTransactionPoints, reqVO.getHistoryTotalTransactionPoints())
                .eqIfPresent(CustomerPointsDO::getHistoryTotalRedeemPoints, reqVO.getHistoryTotalRedeemPoints())
                .eqIfPresent(CustomerPointsDO::getPointsBalance, reqVO.getPointsBalance())
                .eqIfPresent(CustomerPointsDO::getAccountStatus, reqVO.getAccountStatus())
                .betweenIfPresent(CustomerPointsDO::getOpenDate, reqVO.getOpenDate())
                .betweenIfPresent(CustomerPointsDO::getLastTransactionDate, reqVO.getLastTransactionDate())
                .betweenIfPresent(CustomerPointsDO::getLastTransactionTime, reqVO.getLastTransactionTime())
                .betweenIfPresent(CustomerPointsDO::getLastGiftDate, reqVO.getLastGiftDate())
                .betweenIfPresent(CustomerPointsDO::getLastRedeemDate, reqVO.getLastRedeemDate())
                .eqIfPresent(CustomerPointsDO::getPointsValidMonths, reqVO.getPointsValidMonths())
                .eqIfPresent(CustomerPointsDO::getIsAutoExpire, reqVO.getIsAutoExpire())
                .eqIfPresent(CustomerPointsDO::getAutoExpireRemindDays, reqVO.getAutoExpireRemindDays())
                .eqIfPresent(CustomerPointsDO::getYearEarnedPoints, reqVO.getYearEarnedPoints())
                .eqIfPresent(CustomerPointsDO::getYearUsedPoints, reqVO.getYearUsedPoints())
                .eqIfPresent(CustomerPointsDO::getMonthEarnedPoints, reqVO.getMonthEarnedPoints())
                .eqIfPresent(CustomerPointsDO::getMonthUsedPoints, reqVO.getMonthUsedPoints())
                .eqIfPresent(CustomerPointsDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerPointsDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerPointsDO::getId));
    }

}