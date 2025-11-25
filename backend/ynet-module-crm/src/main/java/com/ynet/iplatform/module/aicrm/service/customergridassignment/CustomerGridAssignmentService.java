package com.ynet.iplatform.module.aicrm.service.customergridassignment;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customergridassignment.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customergridassignment.CustomerGridAssignmentDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 客户归属网格关系表（只记录关系，网格信息通过关联查询） Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerGridAssignmentService {

    /**
     * 创建客户归属网格关系表（只记录关系，网格信息通过关联查询）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerGridAssignment(@Valid CustomerGridAssignmentSaveReqVO createReqVO);

    /**
     * 更新客户归属网格关系表（只记录关系，网格信息通过关联查询）
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerGridAssignment(@Valid CustomerGridAssignmentSaveReqVO updateReqVO);

    /**
     * 删除客户归属网格关系表（只记录关系，网格信息通过关联查询）
     *
     * @param id 编号
     */
    void deleteCustomerGridAssignment(Long id);

    /**
    * 批量删除客户归属网格关系表（只记录关系，网格信息通过关联查询）
    *
    * @param ids 编号
    */
    void deleteCustomerGridAssignmentListByIds(List<Long> ids);

    /**
     * 获得客户归属网格关系表（只记录关系，网格信息通过关联查询）
     *
     * @param id 编号
     * @return 客户归属网格关系表（只记录关系，网格信息通过关联查询）
     */
    CustomerGridAssignmentDO getCustomerGridAssignment(Long id);

    /**
     * 获得客户归属网格关系表（只记录关系，网格信息通过关联查询）分页
     *
     * @param pageReqVO 分页查询
     * @return 客户归属网格关系表（只记录关系，网格信息通过关联查询）分页
     */
    PageResult<CustomerGridAssignmentDO> getCustomerGridAssignmentPage(CustomerGridAssignmentPageReqVO pageReqVO);

    /**
     * 获得客户归属网格关系表分页（包含联表查询用户信息）
     *
     * @param pageReqVO 分页查询
     * @return 客户归属网格关系表分页（VO）
     */
    PageResult<CustomerGridAssignmentRespVO> getCustomerGridAssignmentPageWithJoin(CustomerGridAssignmentPageReqVO pageReqVO);

}