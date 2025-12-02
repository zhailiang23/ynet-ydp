package com.ynet.iplatform.module.aicrm.dal.mysql.customerassignmenthistory;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerassignmenthistory.CustomerAssignmentHistoryDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.customerassignmenthistory.vo.*;

/**
 * 客户归属调整历史表（零售+对公共用，记录所有归属变更历史） Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface CustomerAssignmentHistoryMapper extends BaseMapperX<CustomerAssignmentHistoryDO> {

    default PageResult<CustomerAssignmentHistoryDO> selectPage(CustomerAssignmentHistoryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerAssignmentHistoryDO>()
                .eqIfPresent(CustomerAssignmentHistoryDO::getCustomerId, reqVO.getCustomerId())
                .betweenIfPresent(CustomerAssignmentHistoryDO::getTransferDate, reqVO.getTransferDate())
                .eqIfPresent(CustomerAssignmentHistoryDO::getTransferLevel, reqVO.getTransferLevel())
                .eqIfPresent(CustomerAssignmentHistoryDO::getTransferReason, reqVO.getTransferReason())
                .eqIfPresent(CustomerAssignmentHistoryDO::getBeforeAssignmentType, reqVO.getBeforeAssignmentType())
                .eqIfPresent(CustomerAssignmentHistoryDO::getBeforeDeptId, reqVO.getBeforeDeptId())
                .eqIfPresent(CustomerAssignmentHistoryDO::getBeforeUserId, reqVO.getBeforeUserId())
                .eqIfPresent(CustomerAssignmentHistoryDO::getAfterAssignmentType, reqVO.getAfterAssignmentType())
                .eqIfPresent(CustomerAssignmentHistoryDO::getAfterDeptId, reqVO.getAfterDeptId())
                .eqIfPresent(CustomerAssignmentHistoryDO::getAfterUserId, reqVO.getAfterUserId())
                .eqIfPresent(CustomerAssignmentHistoryDO::getAssignOperatorId, reqVO.getAssignOperatorId())
                .betweenIfPresent(CustomerAssignmentHistoryDO::getAssignDate, reqVO.getAssignDate())
                .eqIfPresent(CustomerAssignmentHistoryDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerAssignmentHistoryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerAssignmentHistoryDO::getId));
    }

}