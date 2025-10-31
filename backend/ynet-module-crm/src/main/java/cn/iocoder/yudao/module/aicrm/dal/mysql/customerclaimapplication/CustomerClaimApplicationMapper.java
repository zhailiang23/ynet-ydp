package cn.iocoder.yudao.module.aicrm.dal.mysql.customerclaimapplication;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerclaimapplication.CustomerClaimApplicationDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerclaimapplication.vo.*;

/**
 * 客户认领申请 Mapper
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
                .eqIfPresent(CustomerClaimApplicationDO::getApplyReason, reqVO.getApplyReason())
                .eqIfPresent(CustomerClaimApplicationDO::getProcessInstanceId, reqVO.getProcessInstanceId())
                .eqIfPresent(CustomerClaimApplicationDO::getProcessStatus, reqVO.getProcessStatus())
                .betweenIfPresent(CustomerClaimApplicationDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerClaimApplicationDO::getId));
    }

}