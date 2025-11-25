package com.ynet.iplatform.module.aicrm.dal.mysql.customerrating;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerrating.CustomerRatingDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.customerrating.vo.*;

/**
 * 客户评级信息表（与客户主表1对1关系） Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerRatingMapper extends BaseMapperX<CustomerRatingDO> {

    default PageResult<CustomerRatingDO> selectPage(CustomerRatingPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerRatingDO>()
                .eqIfPresent(CustomerRatingDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerRatingDO::getSequenceNo, reqVO.getSequenceNo())
                .eqIfPresent(CustomerRatingDO::getApprovalStatus, reqVO.getApprovalStatus())
                .betweenIfPresent(CustomerRatingDO::getRatingDate, reqVO.getRatingDate())
                .eqIfPresent(CustomerRatingDO::getValueLevel, reqVO.getValueLevel())
                .eqIfPresent(CustomerRatingDO::getServiceLevel, reqVO.getServiceLevel())
                .eqIfPresent(CustomerRatingDO::getServiceLevelBeforeRisk, reqVO.getServiceLevelBeforeRisk())
                .eqIfPresent(CustomerRatingDO::getRiskFactors, reqVO.getRiskFactors())
                .eqIfPresent(CustomerRatingDO::getRatingMethod, reqVO.getRatingMethod())
                .eqIfPresent(CustomerRatingDO::getCustGradeId, reqVO.getCustGradeId())
                .betweenIfPresent(CustomerRatingDO::getEffectiveDate, reqVO.getEffectiveDate())
                .betweenIfPresent(CustomerRatingDO::getExpiredDate, reqVO.getExpiredDate())
                .betweenIfPresent(CustomerRatingDO::getEvaluateDate, reqVO.getEvaluateDate())
                .eqIfPresent(CustomerRatingDO::getCustGrade, reqVO.getCustGrade())
                .eqIfPresent(CustomerRatingDO::getCustGradeType, reqVO.getCustGradeType())
                .eqIfPresent(CustomerRatingDO::getOrgCode, reqVO.getOrgCode())
                .likeIfPresent(CustomerRatingDO::getOrgName, reqVO.getOrgName())
                .eqIfPresent(CustomerRatingDO::getCustCnt, reqVO.getCustCnt())
                .eqIfPresent(CustomerRatingDO::getRatingScore, reqVO.getRatingScore())
                .eqIfPresent(CustomerRatingDO::getRatingModelVersion, reqVO.getRatingModelVersion())
                .eqIfPresent(CustomerRatingDO::getRiskLevel, reqVO.getRiskLevel())
                .betweenIfPresent(CustomerRatingDO::getNextRatingDate, reqVO.getNextRatingDate())
                .eqIfPresent(CustomerRatingDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerRatingDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerRatingDO::getId));
    }

}