package com.ynet.iplatform.module.grid.dal.mysql.zerodaimarketdescription;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.grid.dal.dataobject.zerodaimarketdescription.GridZerodaiMarketDescriptionDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.grid.controller.admin.zerodaimarketdescription.vo.*;

/**
 * 零贷市场描述 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface GridZerodaiMarketDescriptionMapper extends BaseMapperX<GridZerodaiMarketDescriptionDO> {

    default PageResult<GridZerodaiMarketDescriptionDO> selectPage(GridZerodaiMarketDescriptionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GridZerodaiMarketDescriptionDO>()
                .eqIfPresent(GridZerodaiMarketDescriptionDO::getGridId, reqVO.getGridId())
                .likeIfPresent(GridZerodaiMarketDescriptionDO::getMarketName, reqVO.getMarketName())
                .eqIfPresent(GridZerodaiMarketDescriptionDO::getMarketType, reqVO.getMarketType())
                .eqIfPresent(GridZerodaiMarketDescriptionDO::getLocation, reqVO.getLocation())
                .eqIfPresent(GridZerodaiMarketDescriptionDO::getAddress, reqVO.getAddress())
                .eqIfPresent(GridZerodaiMarketDescriptionDO::getBusinessScope, reqVO.getBusinessScope())
                .eqIfPresent(GridZerodaiMarketDescriptionDO::getMerchantCount, reqVO.getMerchantCount())
                .eqIfPresent(GridZerodaiMarketDescriptionDO::getDailyTraffic, reqVO.getDailyTraffic())
                .eqIfPresent(GridZerodaiMarketDescriptionDO::getMarketFeatures, reqVO.getMarketFeatures())
                .eqIfPresent(GridZerodaiMarketDescriptionDO::getTargetCustomers, reqVO.getTargetCustomers())
                .eqIfPresent(GridZerodaiMarketDescriptionDO::getPotentialAnalysis, reqVO.getPotentialAnalysis())
                .eqIfPresent(GridZerodaiMarketDescriptionDO::getManagerId, reqVO.getManagerId())
                .eqIfPresent(GridZerodaiMarketDescriptionDO::getCreator, reqVO.getCreatorId())
                .betweenIfPresent(GridZerodaiMarketDescriptionDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(GridZerodaiMarketDescriptionDO::getUpdater, reqVO.getUpdaterId())
                .orderByDesc(GridZerodaiMarketDescriptionDO::getId));
    }

}