package cn.iocoder.yudao.module.aicrm.dal.mysql.customerassignment;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerassignment.CustomerAssignmentDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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

    /**
     * 查询我的客户分页（包括主办和协办）
     * 同时查询归属于我的客户和托管给我的客户
     */
    @Select("<script>" +
            "SELECT " +
            "  ca.id as assignment_id, " +
            "  ca.customer_id, " +
            "  c.customer_no, " +
            "  c.customer_name, " +
            "  c.customer_type, " +
            "  c.customer_status, " +
            "  ca.assignment_type, " +
            "  ca.dept_id, " +
            "  d.name as dept_name, " +
            "  ca.assign_date, " +
            "  ca.remark, " +
            "  du.nickname as delegate_user_name, " +
            "  ca.delegate_start_date, " +
            "  ca.delegate_end_date " +
            "FROM crm_customer_assignment ca " +
            "INNER JOIN crm_customer c ON ca.customer_id = c.id " +
            "LEFT JOIN system_dept d ON ca.dept_id = d.id " +
            "LEFT JOIN system_users du ON ca.delegate_from_user_id = du.id " +
            "WHERE (ca.user_id = #{userId} OR (ca.delegate_from_user_id = #{userId} AND ca.is_delegated = true)) " +
            "  AND ca.deleted = 0 " +
            "  AND c.deleted = 0 " +
            "<if test='reqVO.customerNo != null and reqVO.customerNo != \"\"'>" +
            "  AND c.customer_no LIKE CONCAT('%', #{reqVO.customerNo}, '%') " +
            "</if>" +
            "<if test='reqVO.customerName != null and reqVO.customerName != \"\"'>" +
            "  AND c.customer_name LIKE CONCAT('%', #{reqVO.customerName}, '%') " +
            "</if>" +
            "<if test='reqVO.customerType != null'>" +
            "  AND c.customer_type = #{reqVO.customerType} " +
            "</if>" +
            "<if test='reqVO.assignmentType != null'>" +
            "  AND ca.assignment_type = #{reqVO.assignmentType} " +
            "</if>" +
            "<if test='reqVO.customerStatus != null'>" +
            "  AND c.customer_status = #{reqVO.customerStatus} " +
            "</if>" +
            "ORDER BY ca.create_time DESC" +
            "</script>")
    IPage<MyCustomerRespVO> selectMyCustomerPage(IPage<MyCustomerRespVO> page,
                                                   @Param("userId") Long userId,
                                                   @Param("reqVO") MyCustomerPageReqVO reqVO);

}