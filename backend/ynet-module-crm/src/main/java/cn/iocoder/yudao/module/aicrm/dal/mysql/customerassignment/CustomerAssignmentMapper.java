package cn.iocoder.yudao.module.aicrm.dal.mysql.customerassignment;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerassignment.CustomerAssignmentDO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
                .eqIfPresent(CustomerAssignmentDO::getAssignOperatorId, reqVO.getAssignOperatorId())
                .eqIfPresent(CustomerAssignmentDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerAssignmentDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerAssignmentDO::getId));
    }

    /**
     * 物理删除指定客户的所有归属关系（用于回收客户）
     * 注意：这是物理删除，会永久删除数据，不受 @TableLogic 影响
     */
    @Delete("DELETE FROM crm_customer_assignment WHERE customer_id = #{customerId}")
    void physicalDeleteByCustomerId(@Param("customerId") Long customerId);

    /**
     * 物理删除单条归属关系记录（用于避免唯一索引冲突）
     * 注意：这是物理删除，会永久删除数据，不受 @TableLogic 影响
     */
    @Delete("DELETE FROM crm_customer_assignment WHERE id = #{id}")
    void physicalDeleteById(@Param("id") Long id);

}