package com.ynet.iplatform.module.aicrm.dal.mysql.customerguaranteepledge;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerguaranteepledge.CustomerGuaranteePledgeDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.customerguaranteepledge.vo.*;

/**
 * 客户质押物信息表（零售+对公共用） Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface CustomerGuaranteePledgeMapper extends BaseMapperX<CustomerGuaranteePledgeDO> {

    default PageResult<CustomerGuaranteePledgeDO> selectPage(CustomerGuaranteePledgePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerGuaranteePledgeDO>()
                .eqIfPresent(CustomerGuaranteePledgeDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerGuaranteePledgeDO::getCreditId, reqVO.getCreditId())
                .eqIfPresent(CustomerGuaranteePledgeDO::getCollateralNo, reqVO.getCollateralNo())
                .eqIfPresent(CustomerGuaranteePledgeDO::getCollateralType, reqVO.getCollateralType())
                .eqIfPresent(CustomerGuaranteePledgeDO::getGuaranteeType, reqVO.getGuaranteeType())
                .eqIfPresent(CustomerGuaranteePledgeDO::getPledgorIdType, reqVO.getPledgorIdType())
                .eqIfPresent(CustomerGuaranteePledgeDO::getPledgorIdNo, reqVO.getPledgorIdNo())
                .likeIfPresent(CustomerGuaranteePledgeDO::getPledgorName, reqVO.getPledgorName())
                .eqIfPresent(CustomerGuaranteePledgeDO::getPledgorType, reqVO.getPledgorType())
                .eqIfPresent(CustomerGuaranteePledgeDO::getRelationWithBorrower, reqVO.getRelationWithBorrower())
                .eqIfPresent(CustomerGuaranteePledgeDO::getGuaranteeAmount, reqVO.getGuaranteeAmount())
                .eqIfPresent(CustomerGuaranteePledgeDO::getPledgeRate, reqVO.getPledgeRate())
                .likeIfPresent(CustomerGuaranteePledgeDO::getCollateralName, reqVO.getCollateralName())
                .eqIfPresent(CustomerGuaranteePledgeDO::getCollateralValue, reqVO.getCollateralValue())
                .eqIfPresent(CustomerGuaranteePledgeDO::getPledgeStatus, reqVO.getPledgeStatus())
                .betweenIfPresent(CustomerGuaranteePledgeDO::getPledgeDate, reqVO.getPledgeDate())
                .betweenIfPresent(CustomerGuaranteePledgeDO::getReleaseDate, reqVO.getReleaseDate())
                .eqIfPresent(CustomerGuaranteePledgeDO::getManagementBranchId, reqVO.getManagementBranchId())
                .likeIfPresent(CustomerGuaranteePledgeDO::getManagementBranchName, reqVO.getManagementBranchName())
                .eqIfPresent(CustomerGuaranteePledgeDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerGuaranteePledgeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerGuaranteePledgeDO::getId));
    }

}