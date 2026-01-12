package com.ynet.iplatform.module.aicrm.dal.mysql.potentialcustomer;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.module.aicrm.controller.admin.potentialcustomer.vo.PotentialCustomerPageReqVO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.potentialcustomer.PotentialCustomerDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * AI CRM 潜客管理 Mapper
 *
 * @author AI CRM Team
 */
@Mapper
public interface PotentialCustomerMapper extends BaseMapperX<PotentialCustomerDO> {

    default PageResult<PotentialCustomerDO> selectPage(PotentialCustomerPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PotentialCustomerDO>()
                .likeIfPresent(PotentialCustomerDO::getCustomerName, reqVO.getCustomerName())
                .eqIfPresent(PotentialCustomerDO::getRiskLevel, reqVO.getRiskLevel())
                .eqIfPresent(PotentialCustomerDO::getCustomerLevel, reqVO.getCustomerLevel())
                .geIfPresent(PotentialCustomerDO::getAiMatchScore, reqVO.getMinAiMatchScore())
                .eqIfPresent(PotentialCustomerDO::getStatus, reqVO.getStatus())
                .eqIfPresent(PotentialCustomerDO::getSource, reqVO.getSource())
                .eqIfPresent(PotentialCustomerDO::getInsightId, reqVO.getInsightId())
                .eqIfPresent(PotentialCustomerDO::getAssignedUserId, reqVO.getAssignedUserId())
                .betweenIfPresent(PotentialCustomerDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PotentialCustomerDO::getAiMatchScore)  // 默认按AI匹配度降序
                .orderByDesc(PotentialCustomerDO::getId));
    }

}
