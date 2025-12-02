package com.ynet.iplatform.module.aicrm.dal.mysql.customermarketingactivity;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customermarketingactivity.CustomerMarketingActivityDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.customermarketingactivity.vo.*;

/**
 * 客户营销活动信息 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface CustomerMarketingActivityMapper extends BaseMapperX<CustomerMarketingActivityDO> {

    default PageResult<CustomerMarketingActivityDO> selectPage(CustomerMarketingActivityPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerMarketingActivityDO>()
                .eqIfPresent(CustomerMarketingActivityDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerMarketingActivityDO::getSequenceNo, reqVO.getSequenceNo())
                .likeIfPresent(CustomerMarketingActivityDO::getActivityName, reqVO.getActivityName())
                .eqIfPresent(CustomerMarketingActivityDO::getActivityNo, reqVO.getActivityNo())
                .eqIfPresent(CustomerMarketingActivityDO::getActivityForm, reqVO.getActivityForm())
                .eqIfPresent(CustomerMarketingActivityDO::getActivityStatus, reqVO.getActivityStatus())
                .eqIfPresent(CustomerMarketingActivityDO::getApprovalStatus, reqVO.getApprovalStatus())
                .betweenIfPresent(CustomerMarketingActivityDO::getActivityOrderTime, reqVO.getActivityOrderTime())
                .betweenIfPresent(CustomerMarketingActivityDO::getActivityEndTime, reqVO.getActivityEndTime())
                .eqIfPresent(CustomerMarketingActivityDO::getIsSendSms, reqVO.getIsSendSms())
                .eqIfPresent(CustomerMarketingActivityDO::getOpporId, reqVO.getOpporId())
                .eqIfPresent(CustomerMarketingActivityDO::getOpporNm, reqVO.getOpporNm())
                .eqIfPresent(CustomerMarketingActivityDO::getStartDt, reqVO.getStartDt())
                .eqIfPresent(CustomerMarketingActivityDO::getEndDt, reqVO.getEndDt())
                .eqIfPresent(CustomerMarketingActivityDO::getOpporStcd, reqVO.getOpporStcd())
                .eqIfPresent(CustomerMarketingActivityDO::getAutoAcltRlcd, reqVO.getAutoAcltRlcd())
                .eqIfPresent(CustomerMarketingActivityDO::getOpporBsnTpcd, reqVO.getOpporBsnTpcd())
                .eqIfPresent(CustomerMarketingActivityDO::getOpporAlctObjTpcd, reqVO.getOpporAlctObjTpcd())
                .eqIfPresent(CustomerMarketingActivityDO::getOpporCustTpcd, reqVO.getOpporCustTpcd())
                .eqIfPresent(CustomerMarketingActivityDO::getOpporSrccd, reqVO.getOpporSrccd())
                .eqIfPresent(CustomerMarketingActivityDO::getOpporCrtMthcd, reqVO.getOpporCrtMthcd())
                .eqIfPresent(CustomerMarketingActivityDO::getCrtOrgId, reqVO.getCrtOrgId())
                .eqIfPresent(CustomerMarketingActivityDO::getOpporDsc, reqVO.getOpporDsc())
                .eqIfPresent(CustomerMarketingActivityDO::getActivityType, reqVO.getActivityType())
                .eqIfPresent(CustomerMarketingActivityDO::getActivityLocation, reqVO.getActivityLocation())
                .eqIfPresent(CustomerMarketingActivityDO::getActivityBudget, reqVO.getActivityBudget())
                .eqIfPresent(CustomerMarketingActivityDO::getActivityCost, reqVO.getActivityCost())
                .eqIfPresent(CustomerMarketingActivityDO::getTargetCustomerCount, reqVO.getTargetCustomerCount())
                .eqIfPresent(CustomerMarketingActivityDO::getActualCustomerCount, reqVO.getActualCustomerCount())
                .eqIfPresent(CustomerMarketingActivityDO::getExpectedEffect, reqVO.getExpectedEffect())
                .eqIfPresent(CustomerMarketingActivityDO::getActualEffect, reqVO.getActualEffect())
                .eqIfPresent(CustomerMarketingActivityDO::getHandlerUserId, reqVO.getHandlerUserId())
                .likeIfPresent(CustomerMarketingActivityDO::getHandlerUserName, reqVO.getHandlerUserName())
                .eqIfPresent(CustomerMarketingActivityDO::getHandlerDeptId, reqVO.getHandlerDeptId())
                .likeIfPresent(CustomerMarketingActivityDO::getHandlerDeptName, reqVO.getHandlerDeptName())
                .eqIfPresent(CustomerMarketingActivityDO::getApproverUserId, reqVO.getApproverUserId())
                .likeIfPresent(CustomerMarketingActivityDO::getApproverUserName, reqVO.getApproverUserName())
                .betweenIfPresent(CustomerMarketingActivityDO::getApprovalTime, reqVO.getApprovalTime())
                .eqIfPresent(CustomerMarketingActivityDO::getApprovalComment, reqVO.getApprovalComment())
                .betweenIfPresent(CustomerMarketingActivityDO::getSmsSendTime, reqVO.getSmsSendTime())
                .eqIfPresent(CustomerMarketingActivityDO::getSmsContent, reqVO.getSmsContent())
                .eqIfPresent(CustomerMarketingActivityDO::getActivityScore, reqVO.getActivityScore())
                .eqIfPresent(CustomerMarketingActivityDO::getCustomerFeedback, reqVO.getCustomerFeedback())
                .eqIfPresent(CustomerMarketingActivityDO::getIsCancelled, reqVO.getIsCancelled())
                .betweenIfPresent(CustomerMarketingActivityDO::getCancelTime, reqVO.getCancelTime())
                .eqIfPresent(CustomerMarketingActivityDO::getCancelReason, reqVO.getCancelReason())
                .eqIfPresent(CustomerMarketingActivityDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerMarketingActivityDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerMarketingActivityDO::getId));
    }

}