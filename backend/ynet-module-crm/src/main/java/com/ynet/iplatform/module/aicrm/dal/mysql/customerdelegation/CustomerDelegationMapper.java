package com.ynet.iplatform.module.aicrm.dal.mysql.customerdelegation;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerdelegation.CustomerDelegationDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.customerdelegation.vo.*;

/**
 * 客户托管记录表 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerDelegationMapper extends BaseMapperX<CustomerDelegationDO> {

    default PageResult<CustomerDelegationDO> selectPage(CustomerDelegationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerDelegationDO>()
                .eqIfPresent(CustomerDelegationDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerDelegationDO::getFromUserId, reqVO.getFromUserId())
                .eqIfPresent(CustomerDelegationDO::getToUserId, reqVO.getToUserId())
                .betweenIfPresent(CustomerDelegationDO::getStartDate, reqVO.getStartDate())
                .betweenIfPresent(CustomerDelegationDO::getEndDate, reqVO.getEndDate())
                .eqIfPresent(CustomerDelegationDO::getDelegationStatus, reqVO.getDelegationStatus())
                .betweenIfPresent(CustomerDelegationDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerDelegationDO::getId));
    }

}
