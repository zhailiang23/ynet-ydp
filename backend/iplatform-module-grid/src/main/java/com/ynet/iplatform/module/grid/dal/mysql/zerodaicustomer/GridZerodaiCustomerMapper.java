package com.ynet.iplatform.module.grid.dal.mysql.zerodaicustomer;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.grid.dal.dataobject.zerodaicustomer.GridZerodaiCustomerDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.grid.controller.admin.zerodaicustomer.vo.*;

/**
 * 零贷客户扩展 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface GridZerodaiCustomerMapper extends BaseMapperX<GridZerodaiCustomerDO> {

    default PageResult<GridZerodaiCustomerDO> selectPage(GridZerodaiCustomerPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GridZerodaiCustomerDO>()
                .eqIfPresent(GridZerodaiCustomerDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(GridZerodaiCustomerDO::getBusinessType, reqVO.getBusinessType())
                .eqIfPresent(GridZerodaiCustomerDO::getAnnualRevenue, reqVO.getAnnualRevenue())
                .eqIfPresent(GridZerodaiCustomerDO::getCreditRating, reqVO.getCreditRating())
                .eqIfPresent(GridZerodaiCustomerDO::getIsApplied, reqVO.getIsApplied())
                .eqIfPresent(GridZerodaiCustomerDO::getIsCompleted, reqVO.getIsCompleted())
                .eqIfPresent(GridZerodaiCustomerDO::getLoanAmount, reqVO.getLoanAmount())
                .eqIfPresent(GridZerodaiCustomerDO::getLoanBalance, reqVO.getLoanBalance())
                .eqIfPresent(GridZerodaiCustomerDO::getArchiveProtected, reqVO.getArchiveProtected())
                .eqIfPresent(GridZerodaiCustomerDO::getCreator, reqVO.getCreatorId())
                .betweenIfPresent(GridZerodaiCustomerDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(GridZerodaiCustomerDO::getUpdater, reqVO.getUpdaterId())
                .orderByDesc(GridZerodaiCustomerDO::getId));
    }

}