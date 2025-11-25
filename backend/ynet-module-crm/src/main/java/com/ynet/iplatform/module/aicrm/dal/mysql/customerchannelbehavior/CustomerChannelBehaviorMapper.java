package com.ynet.iplatform.module.aicrm.dal.mysql.customerchannelbehavior;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerchannelbehavior.CustomerChannelBehaviorDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.customerchannelbehavior.vo.*;

/**
 * 客户渠道行为信息 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerChannelBehaviorMapper extends BaseMapperX<CustomerChannelBehaviorDO> {

    default PageResult<CustomerChannelBehaviorDO> selectPage(CustomerChannelBehaviorPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerChannelBehaviorDO>()
                .eqIfPresent(CustomerChannelBehaviorDO::getCustomerId, reqVO.getCustomerId())
                .betweenIfPresent(CustomerChannelBehaviorDO::getOperationTime, reqVO.getOperationTime())
                .eqIfPresent(CustomerChannelBehaviorDO::getOperationAction, reqVO.getOperationAction())
                .eqIfPresent(CustomerChannelBehaviorDO::getOperationObject, reqVO.getOperationObject())
                .eqIfPresent(CustomerChannelBehaviorDO::getCurrentPageIdentifier, reqVO.getCurrentPageIdentifier())
                .eqIfPresent(CustomerChannelBehaviorDO::getCurrentPageCode, reqVO.getCurrentPageCode())
                .likeIfPresent(CustomerChannelBehaviorDO::getCurrentPageName, reqVO.getCurrentPageName())
                .eqIfPresent(CustomerChannelBehaviorDO::getPageStaySeconds, reqVO.getPageStaySeconds())
                .eqIfPresent(CustomerChannelBehaviorDO::getDescription, reqVO.getDescription())
                .eqIfPresent(CustomerChannelBehaviorDO::getBehaviorNo, reqVO.getBehaviorNo())
                .eqIfPresent(CustomerChannelBehaviorDO::getChannelType, reqVO.getChannelType())
                .eqIfPresent(CustomerChannelBehaviorDO::getDeviceType, reqVO.getDeviceType())
                .eqIfPresent(CustomerChannelBehaviorDO::getDeviceId, reqVO.getDeviceId())
                .eqIfPresent(CustomerChannelBehaviorDO::getDeviceModel, reqVO.getDeviceModel())
                .eqIfPresent(CustomerChannelBehaviorDO::getAppVersion, reqVO.getAppVersion())
                .eqIfPresent(CustomerChannelBehaviorDO::getOsVersion, reqVO.getOsVersion())
                .eqIfPresent(CustomerChannelBehaviorDO::getBrowserType, reqVO.getBrowserType())
                .eqIfPresent(CustomerChannelBehaviorDO::getBrowserVersion, reqVO.getBrowserVersion())
                .eqIfPresent(CustomerChannelBehaviorDO::getIpAddress, reqVO.getIpAddress())
                .eqIfPresent(CustomerChannelBehaviorDO::getIpLocation, reqVO.getIpLocation())
                .eqIfPresent(CustomerChannelBehaviorDO::getNetworkType, reqVO.getNetworkType())
                .eqIfPresent(CustomerChannelBehaviorDO::getSessionId, reqVO.getSessionId())
                .betweenIfPresent(CustomerChannelBehaviorDO::getSessionStartTime, reqVO.getSessionStartTime())
                .betweenIfPresent(CustomerChannelBehaviorDO::getSessionEndTime, reqVO.getSessionEndTime())
                .eqIfPresent(CustomerChannelBehaviorDO::getPreviousPageCode, reqVO.getPreviousPageCode())
                .likeIfPresent(CustomerChannelBehaviorDO::getPreviousPageName, reqVO.getPreviousPageName())
                .eqIfPresent(CustomerChannelBehaviorDO::getNextPageCode, reqVO.getNextPageCode())
                .likeIfPresent(CustomerChannelBehaviorDO::getNextPageName, reqVO.getNextPageName())
                .eqIfPresent(CustomerChannelBehaviorDO::getPageUrl, reqVO.getPageUrl())
                .eqIfPresent(CustomerChannelBehaviorDO::getPageTitle, reqVO.getPageTitle())
                .eqIfPresent(CustomerChannelBehaviorDO::getPageParams, reqVO.getPageParams())
                .eqIfPresent(CustomerChannelBehaviorDO::getOperationResult, reqVO.getOperationResult())
                .eqIfPresent(CustomerChannelBehaviorDO::getOperationDetail, reqVO.getOperationDetail())
                .eqIfPresent(CustomerChannelBehaviorDO::getBusinessType, reqVO.getBusinessType())
                .eqIfPresent(CustomerChannelBehaviorDO::getBusinessModule, reqVO.getBusinessModule())
                .eqIfPresent(CustomerChannelBehaviorDO::getBusinessFunction, reqVO.getBusinessFunction())
                .eqIfPresent(CustomerChannelBehaviorDO::getIsConversion, reqVO.getIsConversion())
                .eqIfPresent(CustomerChannelBehaviorDO::getConversionType, reqVO.getConversionType())
                .eqIfPresent(CustomerChannelBehaviorDO::getConversionValue, reqVO.getConversionValue())
                .eqIfPresent(CustomerChannelBehaviorDO::getReferSource, reqVO.getReferSource())
                .eqIfPresent(CustomerChannelBehaviorDO::getReferMedium, reqVO.getReferMedium())
                .eqIfPresent(CustomerChannelBehaviorDO::getReferCampaign, reqVO.getReferCampaign())
                .eqIfPresent(CustomerChannelBehaviorDO::getUserAgent, reqVO.getUserAgent())
                .eqIfPresent(CustomerChannelBehaviorDO::getScreenWidth, reqVO.getScreenWidth())
                .eqIfPresent(CustomerChannelBehaviorDO::getScreenHeight, reqVO.getScreenHeight())
                .eqIfPresent(CustomerChannelBehaviorDO::getViewportWidth, reqVO.getViewportWidth())
                .eqIfPresent(CustomerChannelBehaviorDO::getViewportHeight, reqVO.getViewportHeight())
                .eqIfPresent(CustomerChannelBehaviorDO::getIsNewVisitor, reqVO.getIsNewVisitor())
                .eqIfPresent(CustomerChannelBehaviorDO::getVisitCount, reqVO.getVisitCount())
                .eqIfPresent(CustomerChannelBehaviorDO::getBounceRate, reqVO.getBounceRate())
                .eqIfPresent(CustomerChannelBehaviorDO::getEventCategory, reqVO.getEventCategory())
                .eqIfPresent(CustomerChannelBehaviorDO::getEventLabel, reqVO.getEventLabel())
                .eqIfPresent(CustomerChannelBehaviorDO::getEventValue, reqVO.getEventValue())
                .eqIfPresent(CustomerChannelBehaviorDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerChannelBehaviorDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerChannelBehaviorDO::getId));
    }

}