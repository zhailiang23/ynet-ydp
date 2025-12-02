package com.ynet.iplatform.module.aicrm.dal.mysql.customercomplaint;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customercomplaint.CustomerComplaintDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.customercomplaint.vo.*;

/**
 * 客户投诉信息 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface CustomerComplaintMapper extends BaseMapperX<CustomerComplaintDO> {

    default PageResult<CustomerComplaintDO> selectPage(CustomerComplaintPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerComplaintDO>()
                .eqIfPresent(CustomerComplaintDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerComplaintDO::getSequenceNo, reqVO.getSequenceNo())
                .eqIfPresent(CustomerComplaintDO::getWorkOrderNo, reqVO.getWorkOrderNo())
                .eqIfPresent(CustomerComplaintDO::getEcifCustomerNo, reqVO.getEcifCustomerNo())
                .likeIfPresent(CustomerComplaintDO::getCustomerName, reqVO.getCustomerName())
                .eqIfPresent(CustomerComplaintDO::getWorkOrderStatus, reqVO.getWorkOrderStatus())
                .betweenIfPresent(CustomerComplaintDO::getLastProcessTime, reqVO.getLastProcessTime())
                .eqIfPresent(CustomerComplaintDO::getComplaintChannel, reqVO.getComplaintChannel())
                .eqIfPresent(CustomerComplaintDO::getComplaintType, reqVO.getComplaintType())
                .eqIfPresent(CustomerComplaintDO::getComplaintContent, reqVO.getComplaintContent())
                .betweenIfPresent(CustomerComplaintDO::getComplaintTime, reqVO.getComplaintTime())
                .eqIfPresent(CustomerComplaintDO::getComplaintLevel, reqVO.getComplaintLevel())
                .eqIfPresent(CustomerComplaintDO::getHandlerUserId, reqVO.getHandlerUserId())
                .likeIfPresent(CustomerComplaintDO::getHandlerUserName, reqVO.getHandlerUserName())
                .eqIfPresent(CustomerComplaintDO::getHandlerDeptId, reqVO.getHandlerDeptId())
                .likeIfPresent(CustomerComplaintDO::getHandlerDeptName, reqVO.getHandlerDeptName())
                .eqIfPresent(CustomerComplaintDO::getProcessResult, reqVO.getProcessResult())
                .betweenIfPresent(CustomerComplaintDO::getProcessStartTime, reqVO.getProcessStartTime())
                .betweenIfPresent(CustomerComplaintDO::getProcessEndTime, reqVO.getProcessEndTime())
                .eqIfPresent(CustomerComplaintDO::getProcessDuration, reqVO.getProcessDuration())
                .eqIfPresent(CustomerComplaintDO::getCustomerSatisfaction, reqVO.getCustomerSatisfaction())
                .eqIfPresent(CustomerComplaintDO::getSatisfactionFeedback, reqVO.getSatisfactionFeedback())
                .eqIfPresent(CustomerComplaintDO::getIsClosed, reqVO.getIsClosed())
                .betweenIfPresent(CustomerComplaintDO::getCloseTime, reqVO.getCloseTime())
                .eqIfPresent(CustomerComplaintDO::getIsReopened, reqVO.getIsReopened())
                .betweenIfPresent(CustomerComplaintDO::getReopenTime, reqVO.getReopenTime())
                .eqIfPresent(CustomerComplaintDO::getReopenReason, reqVO.getReopenReason())
                .eqIfPresent(CustomerComplaintDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerComplaintDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerComplaintDO::getId));
    }

}