package cn.iocoder.yudao.module.aicrm.dal.mysql.customercoupon;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customercoupon.CustomerCouponDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.aicrm.controller.admin.customercoupon.vo.*;

/**
 * 客户卡券信息表（零售客户特有） Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerCouponMapper extends BaseMapperX<CustomerCouponDO> {

    default PageResult<CustomerCouponDO> selectPage(CustomerCouponPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerCouponDO>()
                .eqIfPresent(CustomerCouponDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerCouponDO::getCouponNo, reqVO.getCouponNo())
                .eqIfPresent(CustomerCouponDO::getCouponCode, reqVO.getCouponCode())
                .likeIfPresent(CustomerCouponDO::getCouponName, reqVO.getCouponName())
                .eqIfPresent(CustomerCouponDO::getCouponType, reqVO.getCouponType())
                .eqIfPresent(CustomerCouponDO::getCouponCategory, reqVO.getCouponCategory())
                .eqIfPresent(CustomerCouponDO::getDiscountRate, reqVO.getDiscountRate())
                .eqIfPresent(CustomerCouponDO::getDiscountAmount, reqVO.getDiscountAmount())
                .likeIfPresent(CustomerCouponDO::getGiftName, reqVO.getGiftName())
                .eqIfPresent(CustomerCouponDO::getRateDiscount, reqVO.getRateDiscount())
                .eqIfPresent(CustomerCouponDO::getThresholdAmount, reqVO.getThresholdAmount())
                .eqIfPresent(CustomerCouponDO::getMaxDiscountAmount, reqVO.getMaxDiscountAmount())
                .eqIfPresent(CustomerCouponDO::getCouponStatus, reqVO.getCouponStatus())
                .betweenIfPresent(CustomerCouponDO::getIssueDate, reqVO.getIssueDate())
                .betweenIfPresent(CustomerCouponDO::getIssueTime, reqVO.getIssueTime())
                .betweenIfPresent(CustomerCouponDO::getEffectiveDate, reqVO.getEffectiveDate())
                .betweenIfPresent(CustomerCouponDO::getExpiryDate, reqVO.getExpiryDate())
                .betweenIfPresent(CustomerCouponDO::getUseDate, reqVO.getUseDate())
                .betweenIfPresent(CustomerCouponDO::getUseTime, reqVO.getUseTime())
                .betweenIfPresent(CustomerCouponDO::getCancelDate, reqVO.getCancelDate())
                .eqIfPresent(CustomerCouponDO::getCancelReason, reqVO.getCancelReason())
                .eqIfPresent(CustomerCouponDO::getIssueSource, reqVO.getIssueSource())
                .eqIfPresent(CustomerCouponDO::getIssueChannel, reqVO.getIssueChannel())
                .eqIfPresent(CustomerCouponDO::getIssueActivityId, reqVO.getIssueActivityId())
                .likeIfPresent(CustomerCouponDO::getIssueActivityName, reqVO.getIssueActivityName())
                .eqIfPresent(CustomerCouponDO::getUseScenario, reqVO.getUseScenario())
                .eqIfPresent(CustomerCouponDO::getUseChannel, reqVO.getUseChannel())
                .eqIfPresent(CustomerCouponDO::getUseProductCode, reqVO.getUseProductCode())
                .likeIfPresent(CustomerCouponDO::getUseProductName, reqVO.getUseProductName())
                .eqIfPresent(CustomerCouponDO::getUseOrderNo, reqVO.getUseOrderNo())
                .eqIfPresent(CustomerCouponDO::getUseTransactionAmount, reqVO.getUseTransactionAmount())
                .eqIfPresent(CustomerCouponDO::getActualDiscountAmount, reqVO.getActualDiscountAmount())
                .eqIfPresent(CustomerCouponDO::getApplicableProducts, reqVO.getApplicableProducts())
                .eqIfPresent(CustomerCouponDO::getApplicableChannels, reqVO.getApplicableChannels())
                .eqIfPresent(CustomerCouponDO::getUseLimitTimes, reqVO.getUseLimitTimes())
                .eqIfPresent(CustomerCouponDO::getUsedTimes, reqVO.getUsedTimes())
                .eqIfPresent(CustomerCouponDO::getIsTransferable, reqVO.getIsTransferable())
                .eqIfPresent(CustomerCouponDO::getIsStackable, reqVO.getIsStackable())
                .eqIfPresent(CustomerCouponDO::getExpireRemindDays, reqVO.getExpireRemindDays())
                .eqIfPresent(CustomerCouponDO::getIsReminded, reqVO.getIsReminded())
                .betweenIfPresent(CustomerCouponDO::getRemindTime, reqVO.getRemindTime())
                .eqIfPresent(CustomerCouponDO::getDescription, reqVO.getDescription())
                .eqIfPresent(CustomerCouponDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerCouponDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerCouponDO::getId));
    }

}