package com.ynet.iplatform.module.grid.dal.mysql.customerarchiveprotection;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.grid.dal.dataobject.customerarchiveprotection.GridCustomerArchiveProtectionDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.grid.controller.admin.customerarchiveprotection.vo.*;

/**
 * 客户档案保护记录 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface GridCustomerArchiveProtectionMapper extends BaseMapperX<GridCustomerArchiveProtectionDO> {

    default PageResult<GridCustomerArchiveProtectionDO> selectPage(GridCustomerArchiveProtectionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GridCustomerArchiveProtectionDO>()
                .eqIfPresent(GridCustomerArchiveProtectionDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(GridCustomerArchiveProtectionDO::getProtectionType, reqVO.getProtectionType())
                .eqIfPresent(GridCustomerArchiveProtectionDO::getOriginalManagerId, reqVO.getOriginalManagerId())
                .betweenIfPresent(GridCustomerArchiveProtectionDO::getClaimTime, reqVO.getClaimTime())
                .betweenIfPresent(GridCustomerArchiveProtectionDO::getProtectionStartTime, reqVO.getProtectionStartTime())
                .betweenIfPresent(GridCustomerArchiveProtectionDO::getProtectionEndTime, reqVO.getProtectionEndTime())
                .eqIfPresent(GridCustomerArchiveProtectionDO::getIsProtected, reqVO.getIsProtected())
                .eqIfPresent(GridCustomerArchiveProtectionDO::getReleaseReason, reqVO.getReleaseReason())
                .eqIfPresent(GridCustomerArchiveProtectionDO::getCreator, reqVO.getCreatorId())
                .betweenIfPresent(GridCustomerArchiveProtectionDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(GridCustomerArchiveProtectionDO::getUpdater, reqVO.getUpdaterId())
                .orderByDesc(GridCustomerArchiveProtectionDO::getId));
    }

}