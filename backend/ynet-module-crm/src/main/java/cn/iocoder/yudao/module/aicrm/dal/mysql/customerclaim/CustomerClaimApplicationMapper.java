package cn.iocoder.yudao.module.aicrm.dal.mysql.customerclaim;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.MPJLambdaWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerclaim.CustomerClaimApplicationDO;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customer.CustomerDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerclaim.vo.*;

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
