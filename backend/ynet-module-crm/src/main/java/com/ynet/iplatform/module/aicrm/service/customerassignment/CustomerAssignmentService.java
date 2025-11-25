package com.ynet.iplatform.module.aicrm.service.customerassignment;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customerassignment.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerassignment.CustomerAssignmentDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 客户归属关系表（零售+对公共用，支持主协办模式） Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerAssignmentService {

    /**
     * 创建客户归属关系表（零售+对公共用，支持主协办模式）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerAssignment(@Valid CustomerAssignmentSaveReqVO createReqVO);

    /**
     * 更新客户归属关系表（零售+对公共用，支持主协办模式）
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerAssignment(@Valid CustomerAssignmentSaveReqVO updateReqVO);

    /**
     * 删除客户归属关系表（零售+对公共用，支持主协办模式）
     *
     * @param id 编号
     */
    void deleteCustomerAssignment(Long id);

    /**
    * 批量删除客户归属关系表（零售+对公共用，支持主协办模式）
    *
    * @param ids 编号
    */
    void deleteCustomerAssignmentListByIds(List<Long> ids);

    /**
     * 获得客户归属关系表（零售+对公共用，支持主协办模式）
     *
     * @param id 编号
     * @return 客户归属关系表（零售+对公共用，支持主协办模式）
     */
    CustomerAssignmentDO getCustomerAssignment(Long id);

    /**
     * 获得客户归属关系表（零售+对公共用，支持主协办模式）分页
     *
     * @param pageReqVO 分页查询
     * @return 客户归属关系表（零售+对公共用，支持主协办模式）分页
     */
    PageResult<CustomerAssignmentDO> getCustomerAssignmentPage(CustomerAssignmentPageReqVO pageReqVO);

    /**
     * 批量分配客户
     *
     * @param userId 操作人ID
     * @param reqVO 分配信息
     */
    void assignCustomers(Long userId, @Valid AssignCustomerReqVO reqVO);

    /**
     * 批量移交客户
     *
     * @param userId 操作人ID
     * @param reqVO 移交信息
     */
    void transferCustomers(Long userId, @Valid TransferCustomerReqVO reqVO);

    /**
     * 批量回收客户
     *
     * @param userId 操作人ID
     * @param reqVO 回收信息
     */
    void reclaimCustomers(Long userId, @Valid ReclaimCustomerReqVO reqVO);

    /**
     * 批量变更主办部门
     *
     * @param userId 操作人ID
     * @param reqVO 变更信息
     */
    void changeDept(Long userId, @Valid ChangeDeptReqVO reqVO);

    /**
     * 快速认领客户（仅未分配的客户可认领）
     *
     * @param userId 当前用户ID
     * @param deptId 当前用户部门ID
     * @param reqVO 认领信息
     */
    void claimCustomers(Long userId, Long deptId, @Valid ClaimCustomerReqVO reqVO);

    /**
     * 获取我的客户分页（包括主办和协办）
     *
     * @param userId 当前用户ID
     * @param pageReqVO 分页查询参数
     * @return 我的客户分页
     */
    PageResult<MyCustomerRespVO> getMyCustomerPage(Long userId, @Valid MyCustomerPageReqVO pageReqVO);

    /**
     * 托管客户（修改当前归属记录的托管字段）
     *
     * @param userId 当前用户ID
     * @param reqVO 托管信息
     */
    void delegateCustomers(Long userId, @Valid DelegateCustomerReqVO reqVO);

}