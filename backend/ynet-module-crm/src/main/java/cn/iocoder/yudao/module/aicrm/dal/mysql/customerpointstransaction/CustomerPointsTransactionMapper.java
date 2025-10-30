package cn.iocoder.yudao.module.aicrm.dal.mysql.customerpointstransaction;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerpointstransaction.CustomerPointsTransactionDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerpointstransaction.vo.*;

/**
 * 客户积分消费明细表（积分交易流水表） Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerPointsTransactionMapper extends BaseMapperX<CustomerPointsTransactionDO> {

    default PageResult<CustomerPointsTransactionDO> selectPage(CustomerPointsTransactionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerPointsTransactionDO>()
                .eqIfPresent(CustomerPointsTransactionDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerPointsTransactionDO::getPointsAccountId, reqVO.getPointsAccountId())
                .eqIfPresent(CustomerPointsTransactionDO::getOrderNo, reqVO.getOrderNo())
                .betweenIfPresent(CustomerPointsTransactionDO::getTransactionDate, reqVO.getTransactionDate())
                .eqIfPresent(CustomerPointsTransactionDO::getExchangeChannel, reqVO.getExchangeChannel())
                .eqIfPresent(CustomerPointsTransactionDO::getGiftExchangeMethod, reqVO.getGiftExchangeMethod())
                .eqIfPresent(CustomerPointsTransactionDO::getOrderConsumedPoints, reqVO.getOrderConsumedPoints())
                .eqIfPresent(CustomerPointsTransactionDO::getOrderStatus, reqVO.getOrderStatus())
                .eqIfPresent(CustomerPointsTransactionDO::getOrderApprovalStatus, reqVO.getOrderApprovalStatus())
                .eqIfPresent(CustomerPointsTransactionDO::getExchangeUser, reqVO.getExchangeUser())
                .eqIfPresent(CustomerPointsTransactionDO::getTransactionNo, reqVO.getTransactionNo())
                .eqIfPresent(CustomerPointsTransactionDO::getTransactionType, reqVO.getTransactionType())
                .betweenIfPresent(CustomerPointsTransactionDO::getTransactionTime, reqVO.getTransactionTime())
                .eqIfPresent(CustomerPointsTransactionDO::getPointsChange, reqVO.getPointsChange())
                .eqIfPresent(CustomerPointsTransactionDO::getPointsBefore, reqVO.getPointsBefore())
                .eqIfPresent(CustomerPointsTransactionDO::getPointsAfter, reqVO.getPointsAfter())
                .eqIfPresent(CustomerPointsTransactionDO::getTransactionStatus, reqVO.getTransactionStatus())
                .eqIfPresent(CustomerPointsTransactionDO::getTransactionDesc, reqVO.getTransactionDesc())
                .eqIfPresent(CustomerPointsTransactionDO::getBusinessType, reqVO.getBusinessType())
                .eqIfPresent(CustomerPointsTransactionDO::getBusinessNo, reqVO.getBusinessNo())
                .eqIfPresent(CustomerPointsTransactionDO::getGiftCode, reqVO.getGiftCode())
                .likeIfPresent(CustomerPointsTransactionDO::getGiftName, reqVO.getGiftName())
                .eqIfPresent(CustomerPointsTransactionDO::getGiftQuantity, reqVO.getGiftQuantity())
                .eqIfPresent(CustomerPointsTransactionDO::getGiftValue, reqVO.getGiftValue())
                .eqIfPresent(CustomerPointsTransactionDO::getDeliveryAddress, reqVO.getDeliveryAddress())
                .eqIfPresent(CustomerPointsTransactionDO::getDeliveryStatus, reqVO.getDeliveryStatus())
                .betweenIfPresent(CustomerPointsTransactionDO::getDeliveryTime, reqVO.getDeliveryTime())
                .likeIfPresent(CustomerPointsTransactionDO::getReceiverName, reqVO.getReceiverName())
                .eqIfPresent(CustomerPointsTransactionDO::getReceiverPhone, reqVO.getReceiverPhone())
                .betweenIfPresent(CustomerPointsTransactionDO::getExpireDate, reqVO.getExpireDate())
                .eqIfPresent(CustomerPointsTransactionDO::getRefundReason, reqVO.getRefundReason())
                .betweenIfPresent(CustomerPointsTransactionDO::getRefundTime, reqVO.getRefundTime())
                .eqIfPresent(CustomerPointsTransactionDO::getRefundPoints, reqVO.getRefundPoints())
                .eqIfPresent(CustomerPointsTransactionDO::getApprovalUserId, reqVO.getApprovalUserId())
                .likeIfPresent(CustomerPointsTransactionDO::getApprovalUserName, reqVO.getApprovalUserName())
                .betweenIfPresent(CustomerPointsTransactionDO::getApprovalTime, reqVO.getApprovalTime())
                .eqIfPresent(CustomerPointsTransactionDO::getApprovalRemark, reqVO.getApprovalRemark())
                .eqIfPresent(CustomerPointsTransactionDO::getOperatorUserId, reqVO.getOperatorUserId())
                .likeIfPresent(CustomerPointsTransactionDO::getOperatorUserName, reqVO.getOperatorUserName())
                .eqIfPresent(CustomerPointsTransactionDO::getOperatorDeptId, reqVO.getOperatorDeptId())
                .likeIfPresent(CustomerPointsTransactionDO::getOperatorDeptName, reqVO.getOperatorDeptName())
                .eqIfPresent(CustomerPointsTransactionDO::getIsReversed, reqVO.getIsReversed())
                .eqIfPresent(CustomerPointsTransactionDO::getReverseTransactionId, reqVO.getReverseTransactionId())
                .betweenIfPresent(CustomerPointsTransactionDO::getReverseTime, reqVO.getReverseTime())
                .eqIfPresent(CustomerPointsTransactionDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerPointsTransactionDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerPointsTransactionDO::getId));
    }

}