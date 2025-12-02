package com.ynet.iplatform.module.aicrm.dal.mysql.customerratinghistory;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerratinghistory.CustomerRatingHistoryDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.customerratinghistory.vo.*;

/**
 * 客户评级调整历史 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface CustomerRatingHistoryMapper extends BaseMapperX<CustomerRatingHistoryDO> {

    default PageResult<CustomerRatingHistoryDO> selectPage(CustomerRatingHistoryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerRatingHistoryDO>()
                .eqIfPresent(CustomerRatingHistoryDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerRatingHistoryDO::getRatingId, reqVO.getRatingId())
                .eqIfPresent(CustomerRatingHistoryDO::getSequenceNo, reqVO.getSequenceNo())
                .eqIfPresent(CustomerRatingHistoryDO::getApprovalStatus, reqVO.getApprovalStatus())
                .betweenIfPresent(CustomerRatingHistoryDO::getRatingDate, reqVO.getRatingDate())
                .eqIfPresent(CustomerRatingHistoryDO::getValueLevel, reqVO.getValueLevel())
                .eqIfPresent(CustomerRatingHistoryDO::getServiceLevel, reqVO.getServiceLevel())
                .eqIfPresent(CustomerRatingHistoryDO::getServiceLevelBeforeRisk, reqVO.getServiceLevelBeforeRisk())
                .eqIfPresent(CustomerRatingHistoryDO::getRiskFactors, reqVO.getRiskFactors())
                .eqIfPresent(CustomerRatingHistoryDO::getRatingMethod, reqVO.getRatingMethod())
                .eqIfPresent(CustomerRatingHistoryDO::getCustGradeId, reqVO.getCustGradeId())
                .betweenIfPresent(CustomerRatingHistoryDO::getEffectiveDate, reqVO.getEffectiveDate())
                .betweenIfPresent(CustomerRatingHistoryDO::getExpiredDate, reqVO.getExpiredDate())
                .betweenIfPresent(CustomerRatingHistoryDO::getEvaluateDate, reqVO.getEvaluateDate())
                .eqIfPresent(CustomerRatingHistoryDO::getCustGrade, reqVO.getCustGrade())
                .eqIfPresent(CustomerRatingHistoryDO::getCustGradeType, reqVO.getCustGradeType())
                .eqIfPresent(CustomerRatingHistoryDO::getOrgCode, reqVO.getOrgCode())
                .likeIfPresent(CustomerRatingHistoryDO::getOrgName, reqVO.getOrgName())
                .eqIfPresent(CustomerRatingHistoryDO::getCustCnt, reqVO.getCustCnt())
                .eqIfPresent(CustomerRatingHistoryDO::getRatingScore, reqVO.getRatingScore())
                .eqIfPresent(CustomerRatingHistoryDO::getRatingModelVersion, reqVO.getRatingModelVersion())
                .eqIfPresent(CustomerRatingHistoryDO::getRiskLevel, reqVO.getRiskLevel())
                .eqIfPresent(CustomerRatingHistoryDO::getChangeReason, reqVO.getChangeReason())
                .eqIfPresent(CustomerRatingHistoryDO::getChangeType, reqVO.getChangeType())
                .eqIfPresent(CustomerRatingHistoryDO::getPreviousValueLevel, reqVO.getPreviousValueLevel())
                .eqIfPresent(CustomerRatingHistoryDO::getPreviousServiceLevel, reqVO.getPreviousServiceLevel())
                .eqIfPresent(CustomerRatingHistoryDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerRatingHistoryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerRatingHistoryDO::getId));
    }

}