package com.ynet.iplatform.module.aicrm.dal.mysql.customercontact;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customercontact.CustomerContactDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.customercontact.vo.*;

/**
 * 客户接触信息 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface CustomerContactMapper extends BaseMapperX<CustomerContactDO> {

    default PageResult<CustomerContactDO> selectPage(CustomerContactPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerContactDO>()
                .eqIfPresent(CustomerContactDO::getCustomerId, reqVO.getCustomerId())
                .betweenIfPresent(CustomerContactDO::getEvaluationTime, reqVO.getEvaluationTime())
                .eqIfPresent(CustomerContactDO::getContactType, reqVO.getContactType())
                .eqIfPresent(CustomerContactDO::getAccountManager, reqVO.getAccountManager())
                .eqIfPresent(CustomerContactDO::getContactPurpose, reqVO.getContactPurpose())
                .eqIfPresent(CustomerContactDO::getContactFeedback, reqVO.getContactFeedback())
                .eqIfPresent(CustomerContactDO::getCstVstId, reqVO.getCstVstId())
                .eqIfPresent(CustomerContactDO::getCstId, reqVO.getCstId())
                .eqIfPresent(CustomerContactDO::getVstMthCd, reqVO.getVstMthCd())
                .eqIfPresent(CustomerContactDO::getVstTm, reqVO.getVstTm())
                .eqIfPresent(CustomerContactDO::getCstInintCd, reqVO.getCstInintCd())
                .eqIfPresent(CustomerContactDO::getVstPsnLst, reqVO.getVstPsnLst())
                .eqIfPresent(CustomerContactDO::getCstPsnLst, reqVO.getCstPsnLst())
                .eqIfPresent(CustomerContactDO::getVstAdr, reqVO.getVstAdr())
                .eqIfPresent(CustomerContactDO::getVstRsltDsc, reqVO.getVstRsltDsc())
                .eqIfPresent(CustomerContactDO::getFuaTm, reqVO.getFuaTm())
                .eqIfPresent(CustomerContactDO::getToDoDsc, reqVO.getToDoDsc())
                .eqIfPresent(CustomerContactDO::getOthDsc, reqVO.getOthDsc())
                .eqIfPresent(CustomerContactDO::getCrtUsrId, reqVO.getCrtUsrId())
                .eqIfPresent(CustomerContactDO::getCrtTm, reqVO.getCrtTm())
                .eqIfPresent(CustomerContactDO::getUdtUsrId, reqVO.getUdtUsrId())
                .eqIfPresent(CustomerContactDO::getUdtTm, reqVO.getUdtTm())
                .eqIfPresent(CustomerContactDO::getContactNo, reqVO.getContactNo())
                .eqIfPresent(CustomerContactDO::getContactStatus, reqVO.getContactStatus())
                .eqIfPresent(CustomerContactDO::getContactChannel, reqVO.getContactChannel())
                .eqIfPresent(CustomerContactDO::getContactResult, reqVO.getContactResult())
                .eqIfPresent(CustomerContactDO::getCustomerIntention, reqVO.getCustomerIntention())
                .eqIfPresent(CustomerContactDO::getContactDuration, reqVO.getContactDuration())
                .eqIfPresent(CustomerContactDO::getContactLocation, reqVO.getContactLocation())
                .eqIfPresent(CustomerContactDO::getContactSubject, reqVO.getContactSubject())
                .eqIfPresent(CustomerContactDO::getContactSummary, reqVO.getContactSummary())
                .betweenIfPresent(CustomerContactDO::getNextContactTime, reqVO.getNextContactTime())
                .eqIfPresent(CustomerContactDO::getNextContactPlan, reqVO.getNextContactPlan())
                .eqIfPresent(CustomerContactDO::getIsNeedFollowup, reqVO.getIsNeedFollowup())
                .eqIfPresent(CustomerContactDO::getFollowupUserId, reqVO.getFollowupUserId())
                .likeIfPresent(CustomerContactDO::getFollowupUserName, reqVO.getFollowupUserName())
                .eqIfPresent(CustomerContactDO::getFollowupStatus, reqVO.getFollowupStatus())
                .eqIfPresent(CustomerContactDO::getRelatedActivityId, reqVO.getRelatedActivityId())
                .likeIfPresent(CustomerContactDO::getRelatedActivityName, reqVO.getRelatedActivityName())
                .eqIfPresent(CustomerContactDO::getRelatedProduct, reqVO.getRelatedProduct())
                .eqIfPresent(CustomerContactDO::getExpectedAmount, reqVO.getExpectedAmount())
                .eqIfPresent(CustomerContactDO::getActualAmount, reqVO.getActualAmount())
                .eqIfPresent(CustomerContactDO::getIsConverted, reqVO.getIsConverted())
                .betweenIfPresent(CustomerContactDO::getConvertTime, reqVO.getConvertTime())
                .eqIfPresent(CustomerContactDO::getCustomerSatisfaction, reqVO.getCustomerSatisfaction())
                .eqIfPresent(CustomerContactDO::getHandlerUserId, reqVO.getHandlerUserId())
                .likeIfPresent(CustomerContactDO::getHandlerUserName, reqVO.getHandlerUserName())
                .eqIfPresent(CustomerContactDO::getHandlerDeptId, reqVO.getHandlerDeptId())
                .likeIfPresent(CustomerContactDO::getHandlerDeptName, reqVO.getHandlerDeptName())
                .eqIfPresent(CustomerContactDO::getAttachments, reqVO.getAttachments())
                .eqIfPresent(CustomerContactDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerContactDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerContactDO::getId));
    }

}