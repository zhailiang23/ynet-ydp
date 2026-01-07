package com.ynet.iplatform.module.task.dal.mysql.task;

import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * CRM 客户归属关系 Mapper
 * 用于跨模块查询客户归属信息
 *
 * @author iplatform
 */
@Mapper
public interface CrmCustomerAssignmentMapper extends BaseMapperX<Object> {

    /**
     * 根据客户ID查询主办客户经理ID（assignmentType = 1）
     *
     * @param customerId 客户ID
     * @return 主办客户经理的用户ID，如果没有则返回 null
     */
    @Select("SELECT user_id FROM crm_customer_assignment " +
            "WHERE customer_id = #{customerId} " +
            "  AND assignment_type = 1 " +
            "  AND deleted = 0 " +
            "LIMIT 1")
    Long selectPrimaryAccountManagerId(@Param("customerId") Long customerId);

}
