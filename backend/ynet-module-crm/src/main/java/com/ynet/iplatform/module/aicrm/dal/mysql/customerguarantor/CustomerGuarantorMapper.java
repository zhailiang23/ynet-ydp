package com.ynet.iplatform.module.aicrm.dal.mysql.customerguarantor;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerguarantor.CustomerGuarantorDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.customerguarantor.vo.*;

/**
 * 客户担保人信息表（零售+对公共用） Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface CustomerGuarantorMapper extends BaseMapperX<CustomerGuarantorDO> {

    default PageResult<CustomerGuarantorDO> selectPage(CustomerGuarantorPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerGuarantorDO>()
                .eqIfPresent(CustomerGuarantorDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerGuarantorDO::getCreditId, reqVO.getCreditId())
                .eqIfPresent(CustomerGuarantorDO::getContractNo, reqVO.getContractNo())
                .eqIfPresent(CustomerGuarantorDO::getContractType, reqVO.getContractType())
                .eqIfPresent(CustomerGuarantorDO::getContractStatus, reqVO.getContractStatus())
                .betweenIfPresent(CustomerGuarantorDO::getSignDate, reqVO.getSignDate())
                .eqIfPresent(CustomerGuarantorDO::getGuaranteeType, reqVO.getGuaranteeType())
                .eqIfPresent(CustomerGuarantorDO::getGuarantorNo, reqVO.getGuarantorNo())
                .likeIfPresent(CustomerGuarantorDO::getGuarantorName, reqVO.getGuarantorName())
                .eqIfPresent(CustomerGuarantorDO::getCurrencyCode, reqVO.getCurrencyCode())
                .eqIfPresent(CustomerGuarantorDO::getGuaranteeTotalAmount, reqVO.getGuaranteeTotalAmount())
                .betweenIfPresent(CustomerGuarantorDO::getBusinessStartDate, reqVO.getBusinessStartDate())
                .betweenIfPresent(CustomerGuarantorDO::getBusinessEndDate, reqVO.getBusinessEndDate())
                .eqIfPresent(CustomerGuarantorDO::getGuarantorType, reqVO.getGuarantorType())
                .eqIfPresent(CustomerGuarantorDO::getGuarantorIdType, reqVO.getGuarantorIdType())
                .eqIfPresent(CustomerGuarantorDO::getGuarantorIdNo, reqVO.getGuarantorIdNo())
                .eqIfPresent(CustomerGuarantorDO::getRelationWithBorrower, reqVO.getRelationWithBorrower())
                .eqIfPresent(CustomerGuarantorDO::getGuaranteeMethod, reqVO.getGuaranteeMethod())
                .eqIfPresent(CustomerGuarantorDO::getUsedAmount, reqVO.getUsedAmount())
                .eqIfPresent(CustomerGuarantorDO::getAvailableAmount, reqVO.getAvailableAmount())
                .eqIfPresent(CustomerGuarantorDO::getManagerUserId, reqVO.getManagerUserId())
                .eqIfPresent(CustomerGuarantorDO::getBranchId, reqVO.getBranchId())
                .eqIfPresent(CustomerGuarantorDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerGuarantorDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerGuarantorDO::getId));
    }

}