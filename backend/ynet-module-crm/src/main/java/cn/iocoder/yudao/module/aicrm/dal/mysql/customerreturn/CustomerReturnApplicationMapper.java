package cn.iocoder.yudao.module.aicrm.dal.mysql.customerreturn;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerreturn.CustomerReturnApplicationDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerreturn.vo.*;

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
