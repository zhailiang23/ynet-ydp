package com.ynet.iplatform.module.grid.dal.mysql.communitycustomer;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.grid.dal.dataobject.communitycustomer.GridCommunityCustomerDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.grid.controller.admin.communitycustomer.vo.*;

/**
 * 社区客户扩展 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface GridCommunityCustomerMapper extends BaseMapperX<GridCommunityCustomerDO> {

    default PageResult<GridCommunityCustomerDO> selectPage(GridCommunityCustomerPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GridCommunityCustomerDO>()
                .eqIfPresent(GridCommunityCustomerDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(GridCommunityCustomerDO::getFamilyMembers, reqVO.getFamilyMembers())
                .eqIfPresent(GridCommunityCustomerDO::getHousingType, reqVO.getHousingType())
                .eqIfPresent(GridCommunityCustomerDO::getMonthlyIncome, reqVO.getMonthlyIncome())
                .eqIfPresent(GridCommunityCustomerDO::getCreator, reqVO.getCreatorId())
                .betweenIfPresent(GridCommunityCustomerDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(GridCommunityCustomerDO::getUpdater, reqVO.getUpdaterId())
                .orderByDesc(GridCommunityCustomerDO::getId));
    }

}