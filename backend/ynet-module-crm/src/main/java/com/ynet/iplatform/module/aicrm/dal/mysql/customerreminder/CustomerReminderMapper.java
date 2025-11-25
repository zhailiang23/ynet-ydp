package com.ynet.iplatform.module.aicrm.dal.mysql.customerreminder;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerreminder.CustomerReminderDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.customerreminder.vo.*;

/**
 * 客户提醒信息 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerReminderMapper extends BaseMapperX<CustomerReminderDO> {

    default PageResult<CustomerReminderDO> selectPage(CustomerReminderPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerReminderDO>()
                .eqIfPresent(CustomerReminderDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerReminderDO::getSequenceNo, reqVO.getSequenceNo())
                .likeIfPresent(CustomerReminderDO::getReminderCategoryName, reqVO.getReminderCategoryName())
                .betweenIfPresent(CustomerReminderDO::getReminderGenerateDate, reqVO.getReminderGenerateDate())
                .betweenIfPresent(CustomerReminderDO::getReminderDueDate, reqVO.getReminderDueDate())
                .eqIfPresent(CustomerReminderDO::getReminderContent, reqVO.getReminderContent())
                .eqIfPresent(CustomerReminderDO::getReminderNo, reqVO.getReminderNo())
                .eqIfPresent(CustomerReminderDO::getReminderType, reqVO.getReminderType())
                .eqIfPresent(CustomerReminderDO::getReminderLevel, reqVO.getReminderLevel())
                .eqIfPresent(CustomerReminderDO::getReminderStatus, reqVO.getReminderStatus())
                .eqIfPresent(CustomerReminderDO::getReminderSource, reqVO.getReminderSource())
                .eqIfPresent(CustomerReminderDO::getIsSent, reqVO.getIsSent())
                .betweenIfPresent(CustomerReminderDO::getSendTime, reqVO.getSendTime())
                .eqIfPresent(CustomerReminderDO::getSendChannel, reqVO.getSendChannel())
                .eqIfPresent(CustomerReminderDO::getRecipientUserId, reqVO.getRecipientUserId())
                .likeIfPresent(CustomerReminderDO::getRecipientUserName, reqVO.getRecipientUserName())
                .eqIfPresent(CustomerReminderDO::getRecipientDeptId, reqVO.getRecipientDeptId())
                .likeIfPresent(CustomerReminderDO::getRecipientDeptName, reqVO.getRecipientDeptName())
                .eqIfPresent(CustomerReminderDO::getHandlerUserId, reqVO.getHandlerUserId())
                .likeIfPresent(CustomerReminderDO::getHandlerUserName, reqVO.getHandlerUserName())
                .betweenIfPresent(CustomerReminderDO::getHandleTime, reqVO.getHandleTime())
                .eqIfPresent(CustomerReminderDO::getHandleResult, reqVO.getHandleResult())
                .eqIfPresent(CustomerReminderDO::getIsRepeat, reqVO.getIsRepeat())
                .eqIfPresent(CustomerReminderDO::getRepeatRule, reqVO.getRepeatRule())
                .betweenIfPresent(CustomerReminderDO::getNextRemindTime, reqVO.getNextRemindTime())
                .eqIfPresent(CustomerReminderDO::getIsRead, reqVO.getIsRead())
                .betweenIfPresent(CustomerReminderDO::getReadTime, reqVO.getReadTime())
                .eqIfPresent(CustomerReminderDO::getPriority, reqVO.getPriority())
                .eqIfPresent(CustomerReminderDO::getRelatedBusinessType, reqVO.getRelatedBusinessType())
                .eqIfPresent(CustomerReminderDO::getRelatedBusinessId, reqVO.getRelatedBusinessId())
                .betweenIfPresent(CustomerReminderDO::getExpireTime, reqVO.getExpireTime())
                .eqIfPresent(CustomerReminderDO::getIsExpired, reqVO.getIsExpired())
                .eqIfPresent(CustomerReminderDO::getIsCancelled, reqVO.getIsCancelled())
                .betweenIfPresent(CustomerReminderDO::getCancelTime, reqVO.getCancelTime())
                .eqIfPresent(CustomerReminderDO::getCancelReason, reqVO.getCancelReason())
                .eqIfPresent(CustomerReminderDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerReminderDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerReminderDO::getId));
    }

}