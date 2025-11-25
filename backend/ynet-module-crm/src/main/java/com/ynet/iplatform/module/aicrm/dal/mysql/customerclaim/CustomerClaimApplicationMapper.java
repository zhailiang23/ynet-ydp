package com.ynet.iplatform.module.aicrm.dal.mysql.customerclaim;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.query.MPJLambdaWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerclaim.CustomerClaimApplicationDO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customer.CustomerDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.customerclaim.vo.*;

/**
 * 客户认领申请表 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerClaimApplicationMapper extends BaseMapperX<CustomerClaimApplicationDO> {

    default PageResult<CustomerClaimApplicationDO> selectPage(CustomerClaimApplicationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerClaimApplicationDO>()
                .eqIfPresent(CustomerClaimApplicationDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerClaimApplicationDO::getApplicantUserId, reqVO.getApplicantUserId())
                .eqIfPresent(CustomerClaimApplicationDO::getApplicantDeptId, reqVO.getApplicantDeptId())
                .betweenIfPresent(CustomerClaimApplicationDO::getApplyDate, reqVO.getApplyDate())
                .eqIfPresent(CustomerClaimApplicationDO::getProcessStatus, reqVO.getProcessStatus())
                .betweenIfPresent(CustomerClaimApplicationDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerClaimApplicationDO::getId));
    }

    default PageResult<CustomerClaimApplicationRespVO> selectPageWithNames(CustomerClaimApplicationPageReqVO reqVO) {
        return selectJoinPage(reqVO, CustomerClaimApplicationRespVO.class,
                new MPJLambdaWrapperX<CustomerClaimApplicationDO>()
                        .selectAll(CustomerClaimApplicationDO.class)
                        .selectAs(CustomerDO::getCustomerName, CustomerClaimApplicationRespVO::getCustomerName)
                        .leftJoin(CustomerDO.class, CustomerDO::getId, CustomerClaimApplicationDO::getCustomerId)
                        .eqIfPresent(CustomerClaimApplicationDO::getCustomerId, reqVO.getCustomerId())
                        .eqIfPresent(CustomerClaimApplicationDO::getApplicantUserId, reqVO.getApplicantUserId())
                        .eqIfPresent(CustomerClaimApplicationDO::getApplicantDeptId, reqVO.getApplicantDeptId())
                        .betweenIfPresent(CustomerClaimApplicationDO::getApplyDate, reqVO.getApplyDate())
                        .eqIfPresent(CustomerClaimApplicationDO::getProcessStatus, reqVO.getProcessStatus())
                        .betweenIfPresent(CustomerClaimApplicationDO::getCreateTime, reqVO.getCreateTime())
                        .orderByDesc(CustomerClaimApplicationDO::getId));
    }

}
