package cn.iocoder.yudao.module.aicrm.dal.mysql.customerchanneloverview;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerchanneloverview.CustomerChannelOverviewDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerchanneloverview.vo.*;

/**
 * 客户渠道业务概览 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerChannelOverviewMapper extends BaseMapperX<CustomerChannelOverviewDO> {

    default PageResult<CustomerChannelOverviewDO> selectPage(CustomerChannelOverviewPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerChannelOverviewDO>()
                .eqIfPresent(CustomerChannelOverviewDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerChannelOverviewDO::getCustomerNo, reqVO.getCustomerNo())
                .betweenIfPresent(CustomerChannelOverviewDO::getStatisticsDate, reqVO.getStatisticsDate())
                .eqIfPresent(CustomerChannelOverviewDO::getChannelType, reqVO.getChannelType())
                .eqIfPresent(CustomerChannelOverviewDO::getChannelCode, reqVO.getChannelCode())
                .likeIfPresent(CustomerChannelOverviewDO::getChannelName, reqVO.getChannelName())
                .eqIfPresent(CustomerChannelOverviewDO::getAccessCount, reqVO.getAccessCount())
                .eqIfPresent(CustomerChannelOverviewDO::getLoginCount, reqVO.getLoginCount())
                .eqIfPresent(CustomerChannelOverviewDO::getTransactionCount, reqVO.getTransactionCount())
                .eqIfPresent(CustomerChannelOverviewDO::getActiveDays, reqVO.getActiveDays())
                .eqIfPresent(CustomerChannelOverviewDO::getTransactionAmount, reqVO.getTransactionAmount())
                .eqIfPresent(CustomerChannelOverviewDO::getAmountYearAvg, reqVO.getAmountYearAvg())
                .eqIfPresent(CustomerChannelOverviewDO::getLastYearAmountYearAvg, reqVO.getLastYearAmountYearAvg())
                .eqIfPresent(CustomerChannelOverviewDO::getAmountQuarterAvg, reqVO.getAmountQuarterAvg())
                .eqIfPresent(CustomerChannelOverviewDO::getLastYearAmountQuarterAvg, reqVO.getLastYearAmountQuarterAvg())
                .eqIfPresent(CustomerChannelOverviewDO::getAmountMonthAvg, reqVO.getAmountMonthAvg())
                .eqIfPresent(CustomerChannelOverviewDO::getLastYearAmountMonthAvg, reqVO.getLastYearAmountMonthAvg())
                .eqIfPresent(CustomerChannelOverviewDO::getCumulativeYearAmount, reqVO.getCumulativeYearAmount())
                .eqIfPresent(CustomerChannelOverviewDO::getLastYearCumulativeAmount, reqVO.getLastYearCumulativeAmount())
                .eqIfPresent(CustomerChannelOverviewDO::getCumulativeQuarterAmount, reqVO.getCumulativeQuarterAmount())
                .eqIfPresent(CustomerChannelOverviewDO::getLastYearCumulativeQuarterAmount, reqVO.getLastYearCumulativeQuarterAmount())
                .eqIfPresent(CustomerChannelOverviewDO::getCumulativeMonthAmount, reqVO.getCumulativeMonthAmount())
                .eqIfPresent(CustomerChannelOverviewDO::getLastYearCumulativeMonthAmount, reqVO.getLastYearCumulativeMonthAmount())
                .betweenIfPresent(CustomerChannelOverviewDO::getFirstAccessTime, reqVO.getFirstAccessTime())
                .betweenIfPresent(CustomerChannelOverviewDO::getLastAccessTime, reqVO.getLastAccessTime())
                .betweenIfPresent(CustomerChannelOverviewDO::getLastTransactionTime, reqVO.getLastTransactionTime())
                .eqIfPresent(CustomerChannelOverviewDO::getDeviceType, reqVO.getDeviceType())
                .eqIfPresent(CustomerChannelOverviewDO::getDeviceModel, reqVO.getDeviceModel())
                .eqIfPresent(CustomerChannelOverviewDO::getOsVersion, reqVO.getOsVersion())
                .eqIfPresent(CustomerChannelOverviewDO::getAppVersion, reqVO.getAppVersion())
                .eqIfPresent(CustomerChannelOverviewDO::getPreferredBusiness, reqVO.getPreferredBusiness())
                .eqIfPresent(CustomerChannelOverviewDO::getUsageFrequency, reqVO.getUsageFrequency())
                .eqIfPresent(CustomerChannelOverviewDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerChannelOverviewDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerChannelOverviewDO::getId));
    }

}