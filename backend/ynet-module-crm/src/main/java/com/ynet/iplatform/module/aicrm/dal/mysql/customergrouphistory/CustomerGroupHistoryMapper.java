package com.ynet.iplatform.module.aicrm.dal.mysql.customergrouphistory;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customergrouphistory.CustomerGroupHistoryDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.customergrouphistory.vo.*;

/**
 * 客户归属客群调整历史表（记录调整当时的客群信息快照） Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerGroupHistoryMapper extends BaseMapperX<CustomerGroupHistoryDO> {

    default PageResult<CustomerGroupHistoryDO> selectPage(CustomerGroupHistoryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerGroupHistoryDO>()
                .eqIfPresent(CustomerGroupHistoryDO::getCustomerId, reqVO.getCustomerId())
                .betweenIfPresent(CustomerGroupHistoryDO::getAdjustDate, reqVO.getAdjustDate())
                .eqIfPresent(CustomerGroupHistoryDO::getAdjustReason, reqVO.getAdjustReason())
                .eqIfPresent(CustomerGroupHistoryDO::getBeforeGroupId, reqVO.getBeforeGroupId())
                .eqIfPresent(CustomerGroupHistoryDO::getBeforeGroupCode, reqVO.getBeforeGroupCode())
                .likeIfPresent(CustomerGroupHistoryDO::getBeforeGroupName, reqVO.getBeforeGroupName())
                .eqIfPresent(CustomerGroupHistoryDO::getBeforeGroupCategory, reqVO.getBeforeGroupCategory())
                .eqIfPresent(CustomerGroupHistoryDO::getBeforeManagerUserId, reqVO.getBeforeManagerUserId())
                .eqIfPresent(CustomerGroupHistoryDO::getAfterGroupId, reqVO.getAfterGroupId())
                .eqIfPresent(CustomerGroupHistoryDO::getAfterGroupCode, reqVO.getAfterGroupCode())
                .likeIfPresent(CustomerGroupHistoryDO::getAfterGroupName, reqVO.getAfterGroupName())
                .eqIfPresent(CustomerGroupHistoryDO::getAfterGroupCategory, reqVO.getAfterGroupCategory())
                .eqIfPresent(CustomerGroupHistoryDO::getAfterManagerUserId, reqVO.getAfterManagerUserId())
                .eqIfPresent(CustomerGroupHistoryDO::getAdjustOperatorId, reqVO.getAdjustOperatorId())
                .eqIfPresent(CustomerGroupHistoryDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerGroupHistoryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerGroupHistoryDO::getId));
    }

}