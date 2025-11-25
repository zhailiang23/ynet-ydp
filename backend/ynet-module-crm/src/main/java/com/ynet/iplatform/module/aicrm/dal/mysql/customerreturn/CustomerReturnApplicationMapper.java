package com.ynet.iplatform.module.aicrm.dal.mysql.customerreturn;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerreturn.CustomerReturnApplicationDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.customerreturn.vo.*;

/**
 * 客户退回申请表 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerReturnApplicationMapper extends BaseMapperX<CustomerReturnApplicationDO> {

    default PageResult<CustomerReturnApplicationDO> selectPage(CustomerReturnApplicationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerReturnApplicationDO>()
                .eqIfPresent(CustomerReturnApplicationDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerReturnApplicationDO::getApplicantUserId, reqVO.getApplicantUserId())
                .eqIfPresent(CustomerReturnApplicationDO::getReturnToUserId, reqVO.getReturnToUserId())
                .betweenIfPresent(CustomerReturnApplicationDO::getApplyDate, reqVO.getApplyDate())
                .eqIfPresent(CustomerReturnApplicationDO::getProcessStatus, reqVO.getProcessStatus())
                .betweenIfPresent(CustomerReturnApplicationDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerReturnApplicationDO::getId));
    }

}
