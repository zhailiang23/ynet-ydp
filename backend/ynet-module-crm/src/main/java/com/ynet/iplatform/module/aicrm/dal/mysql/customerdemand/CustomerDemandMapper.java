package com.ynet.iplatform.module.aicrm.dal.mysql.customerdemand;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerdemand.CustomerDemandDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.customerdemand.vo.*;

/**
 * 客户需求信息 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface CustomerDemandMapper extends BaseMapperX<CustomerDemandDO> {

    default PageResult<CustomerDemandDO> selectPage(CustomerDemandPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerDemandDO>()
                .eqIfPresent(CustomerDemandDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerDemandDO::getSequenceNo, reqVO.getSequenceNo())
                .eqIfPresent(CustomerDemandDO::getDemandChannel, reqVO.getDemandChannel())
                .eqIfPresent(CustomerDemandDO::getDemandContent, reqVO.getDemandContent())
                .betweenIfPresent(CustomerDemandDO::getSubmitTime, reqVO.getSubmitTime())
                .eqIfPresent(CustomerDemandDO::getIsProcessed, reqVO.getIsProcessed())
                .eqIfPresent(CustomerDemandDO::getDemandNo, reqVO.getDemandNo())
                .eqIfPresent(CustomerDemandDO::getDemandType, reqVO.getDemandType())
                .eqIfPresent(CustomerDemandDO::getDemandStatus, reqVO.getDemandStatus())
                .eqIfPresent(CustomerDemandDO::getDemandPriority, reqVO.getDemandPriority())
                .eqIfPresent(CustomerDemandDO::getDemandSource, reqVO.getDemandSource())
                .eqIfPresent(CustomerDemandDO::getHandlerUserId, reqVO.getHandlerUserId())
                .likeIfPresent(CustomerDemandDO::getHandlerUserName, reqVO.getHandlerUserName())
                .eqIfPresent(CustomerDemandDO::getHandlerDeptId, reqVO.getHandlerDeptId())
                .likeIfPresent(CustomerDemandDO::getHandlerDeptName, reqVO.getHandlerDeptName())
                .betweenIfPresent(CustomerDemandDO::getProcessStartTime, reqVO.getProcessStartTime())
                .betweenIfPresent(CustomerDemandDO::getProcessEndTime, reqVO.getProcessEndTime())
                .eqIfPresent(CustomerDemandDO::getProcessDuration, reqVO.getProcessDuration())
                .eqIfPresent(CustomerDemandDO::getProcessResult, reqVO.getProcessResult())
                .eqIfPresent(CustomerDemandDO::getCustomerSatisfaction, reqVO.getCustomerSatisfaction())
                .eqIfPresent(CustomerDemandDO::getSatisfactionFeedback, reqVO.getSatisfactionFeedback())
                .eqIfPresent(CustomerDemandDO::getFollowUpRequired, reqVO.getFollowUpRequired())
                .betweenIfPresent(CustomerDemandDO::getFollowUpTime, reqVO.getFollowUpTime())
                .eqIfPresent(CustomerDemandDO::getFollowUpContent, reqVO.getFollowUpContent())
                .eqIfPresent(CustomerDemandDO::getRelatedProduct, reqVO.getRelatedProduct())
                .eqIfPresent(CustomerDemandDO::getExpectedAmount, reqVO.getExpectedAmount())
                .eqIfPresent(CustomerDemandDO::getActualAmount, reqVO.getActualAmount())
                .eqIfPresent(CustomerDemandDO::getIsConverted, reqVO.getIsConverted())
                .betweenIfPresent(CustomerDemandDO::getConvertTime, reqVO.getConvertTime())
                .eqIfPresent(CustomerDemandDO::getIsClosed, reqVO.getIsClosed())
                .betweenIfPresent(CustomerDemandDO::getCloseTime, reqVO.getCloseTime())
                .eqIfPresent(CustomerDemandDO::getCloseReason, reqVO.getCloseReason())
                .eqIfPresent(CustomerDemandDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerDemandDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerDemandDO::getId));
    }

}