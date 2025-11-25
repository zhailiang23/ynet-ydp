package com.ynet.iplatform.module.aicrm.dal.mysql.customercredit;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customercredit.CustomerCreditDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.customercredit.vo.*;

/**
 * 客户授信信息表（零售+对公共用） Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerCreditMapper extends BaseMapperX<CustomerCreditDO> {

    default PageResult<CustomerCreditDO> selectPage(CustomerCreditPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerCreditDO>()
                .eqIfPresent(CustomerCreditDO::getCustomerId, reqVO.getCustomerId())
                .betweenIfPresent(CustomerCreditDO::getStatisticsDate, reqVO.getStatisticsDate())
                .eqIfPresent(CustomerCreditDO::getCreditAgreementNo, reqVO.getCreditAgreementNo())
                .eqIfPresent(CustomerCreditDO::getCreditProductType, reqVO.getCreditProductType())
                .eqIfPresent(CustomerCreditDO::getCurrencyCode, reqVO.getCurrencyCode())
                .eqIfPresent(CustomerCreditDO::getCreditLimit, reqVO.getCreditLimit())
                .eqIfPresent(CustomerCreditDO::getUsedLimit, reqVO.getUsedLimit())
                .eqIfPresent(CustomerCreditDO::getAvailableLimit, reqVO.getAvailableLimit())
                .eqIfPresent(CustomerCreditDO::getUsageRatio, reqVO.getUsageRatio())
                .betweenIfPresent(CustomerCreditDO::getCreditStartDate, reqVO.getCreditStartDate())
                .betweenIfPresent(CustomerCreditDO::getCreditEndDate, reqVO.getCreditEndDate())
                .eqIfPresent(CustomerCreditDO::getCreditType, reqVO.getCreditType())
                .eqIfPresent(CustomerCreditDO::getCreditStatus, reqVO.getCreditStatus())
                .betweenIfPresent(CustomerCreditDO::getApproveDate, reqVO.getApproveDate())
                .eqIfPresent(CustomerCreditDO::getApproveAmount, reqVO.getApproveAmount())
                .eqIfPresent(CustomerCreditDO::getInterestRate, reqVO.getInterestRate())
                .eqIfPresent(CustomerCreditDO::getGuaranteeType, reqVO.getGuaranteeType())
                .eqIfPresent(CustomerCreditDO::getCreditPurpose, reqVO.getCreditPurpose())
                .eqIfPresent(CustomerCreditDO::getApproverUserId, reqVO.getApproverUserId())
                .eqIfPresent(CustomerCreditDO::getApproverDeptId, reqVO.getApproverDeptId())
                .eqIfPresent(CustomerCreditDO::getManagerUserId, reqVO.getManagerUserId())
                .eqIfPresent(CustomerCreditDO::getBranchId, reqVO.getBranchId())
                .eqIfPresent(CustomerCreditDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerCreditDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerCreditDO::getId));
    }

}