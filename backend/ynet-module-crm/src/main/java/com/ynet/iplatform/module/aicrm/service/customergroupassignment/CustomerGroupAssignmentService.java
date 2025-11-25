package com.ynet.iplatform.module.aicrm.service.customergroupassignment;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customergroupassignment.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customergroupassignment.CustomerGroupAssignmentDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 客户归属客群关系表（只记录关系，客群信息通过关联查询） Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerGroupAssignmentService {

    /**
     * 创建客户归属客群关系表（只记录关系，客群信息通过关联查询）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerGroupAssignment(@Valid CustomerGroupAssignmentSaveReqVO createReqVO);

    /**
     * 更新客户归属客群关系表（只记录关系，客群信息通过关联查询）
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerGroupAssignment(@Valid CustomerGroupAssignmentSaveReqVO updateReqVO);

    /**
     * 删除客户归属客群关系表（只记录关系，客群信息通过关联查询）
     *
     * @param id 编号
     */
    void deleteCustomerGroupAssignment(Long id);

    /**
    * 批量删除客户归属客群关系表（只记录关系，客群信息通过关联查询）
    *
    * @param ids 编号
    */
    void deleteCustomerGroupAssignmentListByIds(List<Long> ids);

    /**
     * 获得客户归属客群关系表（只记录关系，客群信息通过关联查询）
     *
     * @param id 编号
     * @return 客户归属客群关系表（只记录关系，客群信息通过关联查询）
     */
    CustomerGroupAssignmentDO getCustomerGroupAssignment(Long id);

    /**
     * 获得客户归属客群关系表（只记录关系，客群信息通过关联查询）分页
     *
     * @param pageReqVO 分页查询
     * @return 客户归属客群关系表（只记录关系，客群信息通过关联查询）分页
     */
    PageResult<CustomerGroupAssignmentDO> getCustomerGroupAssignmentPage(CustomerGroupAssignmentPageReqVO pageReqVO);

    /**
     * 获得客户归属客群关系分页（联表查询）
     *
     * @param pageReqVO 分页查询
     * @return 客户归属客群关系分页（包含客户群信息、用户名称、部门名称）
     */
    PageResult<CustomerGroupAssignmentRespVO> getCustomerGroupAssignmentPageWithJoin(CustomerGroupAssignmentPageReqVO pageReqVO);

}