package cn.iocoder.yudao.module.aicrm.dal.mysql.customerassignment;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerassignment.CustomerAssignmentDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerassignment.vo.*;

/**
 * 客户归属关系表（零售+对公共用，支持主协办模式） Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerAssignmentMapper extends BaseMapperX<CustomerAssignmentDO> {

    default PageResult<CustomerAssignmentDO> selectPage(CustomerAssignmentPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerAssignmentDO>()
                .eqIfPresent(CustomerAssignmentDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerAssignmentDO::getAssignmentType, reqVO.getAssignmentType())
                .eqIfPresent(CustomerAssignmentDO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(CustomerAssignmentDO::getUserId, reqVO.getUserId())
                .eqIfPresent(CustomerAssignmentDO::getHasViewRight, reqVO.getHasViewRight())
                .eqIfPresent(CustomerAssignmentDO::getHasMaintainRight, reqVO.getHasMaintainRight())
                .betweenIfPresent(CustomerAssignmentDO::getAssignDate, reqVO.getAssignDate())
                .betweenIfPresent(CustomerAssignmentDO::getEffectiveDate, reqVO.getEffectiveDate())
                .betweenIfPresent(CustomerAssignmentDO::getExpiryDate, reqVO.getExpiryDate())
                .eqIfPresent(CustomerAssignmentDO::getAssignOperatorId, reqVO.getAssignOperatorId())
                .eqIfPresent(CustomerAssignmentDO::getStatus, reqVO.getStatus())
                .eqIfPresent(CustomerAssignmentDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerAssignmentDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerAssignmentDO::getId));
    }

}