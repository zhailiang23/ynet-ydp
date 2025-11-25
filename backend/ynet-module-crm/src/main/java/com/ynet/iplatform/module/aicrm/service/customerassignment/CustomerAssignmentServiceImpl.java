package com.ynet.iplatform.module.aicrm.service.customerassignment;

import cn.hutool.core.collection.CollUtil;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customer.CustomerDO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerassignmenthistory.CustomerAssignmentHistoryDO;
import com.ynet.iplatform.module.aicrm.dal.mysql.customer.CustomerMapper;
import com.ynet.iplatform.module.aicrm.dal.mysql.customerassignmenthistory.CustomerAssignmentHistoryMapper;
import com.ynet.iplatform.module.system.service.user.AdminUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customerassignment.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerassignment.CustomerAssignmentDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.customerassignment.CustomerAssignmentMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

import lombok.extern.slf4j.Slf4j;

/**
 * 客户归属关系表（零售+对公共用，支持主协办模式） Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
@Slf4j
public class CustomerAssignmentServiceImpl implements CustomerAssignmentService {

    @Resource
    private CustomerAssignmentMapper customerAssignmentMapper;

    @Resource
    private CustomerAssignmentHistoryMapper customerAssignmentHistoryMapper;

    @Resource
    private CustomerMapper customerMapper;

    @Resource
    private AdminUserService adminUserService;

    @Override
    public Long createCustomerAssignment(CustomerAssignmentSaveReqVO createReqVO) {
        // 插入
        CustomerAssignmentDO customerAssignment = BeanUtils.toBean(createReqVO, CustomerAssignmentDO.class);
        customerAssignmentMapper.insert(customerAssignment);

        // 返回
        return customerAssignment.getId();
    }

    @Override
    public void updateCustomerAssignment(CustomerAssignmentSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerAssignmentExists(updateReqVO.getId());
        // 更新
        CustomerAssignmentDO updateObj = BeanUtils.toBean(updateReqVO, CustomerAssignmentDO.class);
        customerAssignmentMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerAssignment(Long id) {
        // 校验存在
        validateCustomerAssignmentExists(id);
        // 删除
        customerAssignmentMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerAssignmentListByIds(List<Long> ids) {
        // 删除
        customerAssignmentMapper.deleteByIds(ids);
        }


    private void validateCustomerAssignmentExists(Long id) {
        if (customerAssignmentMapper.selectById(id) == null) {
            throw exception(CUSTOMER_ASSIGNMENT_NOT_EXISTS);
        }
    }

    @Override
    public CustomerAssignmentDO getCustomerAssignment(Long id) {
        return customerAssignmentMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerAssignmentDO> getCustomerAssignmentPage(CustomerAssignmentPageReqVO pageReqVO) {
        return customerAssignmentMapper.selectPage(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assignCustomers(Long userId, AssignCustomerReqVO reqVO) {
        for (Long customerId : reqVO.getCustomerIds()) {
            // 1. 检查是否存在该客户+该经理的归属关系
            CustomerAssignmentDO existingAssignment = customerAssignmentMapper.selectOne(
                    new LambdaQueryWrapper<CustomerAssignmentDO>()
                            .eq(CustomerAssignmentDO::getCustomerId, customerId)
                            .eq(CustomerAssignmentDO::getUserId, reqVO.getUserId())
            );

            // 2. 如果分配的是主办,需要将其他人的主办改为协办
            if (reqVO.getAssignmentType() == 1) {
                // 查询其他人的主办记录(排除当前经理)
                List<CustomerAssignmentDO> otherPrimaries = customerAssignmentMapper.selectList(
                        new LambdaQueryWrapper<CustomerAssignmentDO>()
                                .eq(CustomerAssignmentDO::getCustomerId, customerId)
                                .eq(CustomerAssignmentDO::getAssignmentType, 1) // 主办
                                .ne(CustomerAssignmentDO::getUserId, reqVO.getUserId()) // 排除当前经理
                );

                // 将其他人的主办改为协办
                if (CollUtil.isNotEmpty(otherPrimaries)) {
                    for (CustomerAssignmentDO otherPrimary : otherPrimaries) {
                        // 检查该用户是否已有协办记录（避免唯一索引冲突）
                        Long existingAssistantCount = customerAssignmentMapper.selectCount(
                                new LambdaQueryWrapper<CustomerAssignmentDO>()
                                        .eq(CustomerAssignmentDO::getCustomerId, customerId)
                                        .eq(CustomerAssignmentDO::getAssignmentType, 2) // 协办
                                        .eq(CustomerAssignmentDO::getUserId, otherPrimary.getUserId())
                        );

                        if (existingAssistantCount > 0) {
                            // 如果已存在协办记录，物理删除主办记录（避免唯一索引冲突）
                            customerAssignmentMapper.physicalDeleteById(otherPrimary.getId());

                            // 记录主办删除的历史
                            recordAssignmentHistory(customerId, "primary_removed",
                                    otherPrimary.getDeptId(), otherPrimary.getUserId(),
                                    null, null,
                                    "分配新主办,删除原主办(该用户已有协办记录)", userId, null);
                        } else {
                            // 如果不存在协办记录，将主办改为协办
                            customerAssignmentMapper.updateById(
                                    new CustomerAssignmentDO()
                                            .setId(otherPrimary.getId())
                                            .setAssignmentType(2) // 改为协办
                            );

                            // 记录主办变更为协办的历史
                            recordAssignmentHistory(customerId, "primary_to_assistant",
                                    otherPrimary.getDeptId(), otherPrimary.getUserId(),
                                    otherPrimary.getDeptId(), otherPrimary.getUserId(),
                                    "分配新主办,原主办自动转为协办", userId, null);
                        }
                    }
                }
            }

            // 3. 更新或新建归属关系
            if (existingAssignment != null) {
                // 存在则更新
                customerAssignmentMapper.updateById(
                        new CustomerAssignmentDO()
                                .setId(existingAssignment.getId())
                                .setAssignmentType(reqVO.getAssignmentType())
                                .setDeptId(reqVO.getDeptId())
                                .setHasViewRight(reqVO.getHasViewRight())
                                .setHasMaintainRight(reqVO.getHasMaintainRight())
                                .setAssignDate(LocalDate.now())
                                .setAssignOperatorId(userId)
                                .setRemark(reqVO.getRemark())
                );

                // 记录更新历史
                recordAssignmentHistory(customerId, "update_assign",
                        existingAssignment.getDeptId(), existingAssignment.getUserId(),
                        reqVO.getDeptId(), reqVO.getUserId(),
                        "更新客户归属关系", userId, null);
            } else {
                // 不存在则新建
                CustomerAssignmentDO assignment = new CustomerAssignmentDO();
                assignment.setCustomerId(customerId);
                assignment.setAssignmentType(reqVO.getAssignmentType());
                assignment.setDeptId(reqVO.getDeptId());
                assignment.setUserId(reqVO.getUserId());
                assignment.setHasViewRight(reqVO.getHasViewRight());
                assignment.setHasMaintainRight(reqVO.getHasMaintainRight());
                assignment.setAssignDate(LocalDate.now());
                assignment.setAssignOperatorId(userId);
                assignment.setRemark(reqVO.getRemark());
                customerAssignmentMapper.insert(assignment);

                // 记录新建历史
                recordAssignmentHistory(customerId, "manual_assign", null, null,
                        reqVO.getDeptId(), reqVO.getUserId(), "手动分配客户", userId, null);
            }

            // 4. 更新客户表的分配状态和部门(只有主办才更新客户表的部门)
            if (reqVO.getAssignmentType() == 1) {
                customerMapper.updateById(
                        new CustomerDO()
                                .setId(customerId)
                                .setAssignmentStatus(1) // 已分配
                                .setDeptId(reqVO.getDeptId())
                );
            } else {
                // 协办只更新分配状态,不更新部门
                customerMapper.updateById(
                        new CustomerDO()
                                .setId(customerId)
                                .setAssignmentStatus(1) // 已分配
                );
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void transferCustomers(Long userId, TransferCustomerReqVO reqVO) {
        for (Long customerId : reqVO.getCustomerIds()) {
            // 1. 查询当前归属关系
            CustomerAssignmentDO oldAssignment = getActiveAssignmentByCustomerId(customerId);
            if (oldAssignment == null) {
                continue;
            }

            // 2. 删除旧的归属关系
            customerAssignmentMapper.deleteById(oldAssignment.getId());

            // 3. 创建新的归属关系
            CustomerAssignmentDO newAssignment = new CustomerAssignmentDO();
            newAssignment.setCustomerId(customerId);
            newAssignment.setAssignmentType(oldAssignment.getAssignmentType());
            newAssignment.setDeptId(oldAssignment.getDeptId());
            newAssignment.setUserId(reqVO.getToUserId());
            newAssignment.setHasViewRight(oldAssignment.getHasViewRight());
            newAssignment.setHasMaintainRight(oldAssignment.getHasMaintainRight());
            newAssignment.setAssignDate(LocalDate.now());
            newAssignment.setAssignOperatorId(userId);
            newAssignment.setRemark("客户移交");
            customerAssignmentMapper.insert(newAssignment);

            // 4. 记录历史
            recordAssignmentHistory(customerId, "transfer", oldAssignment.getDeptId(), oldAssignment.getUserId(),
                    oldAssignment.getDeptId(), reqVO.getToUserId(), reqVO.getTransferReason(), userId, null);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reclaimCustomers(Long userId, ReclaimCustomerReqVO reqVO) {
        for (Long customerId : reqVO.getCustomerIds()) {
            // 1. 查询当前所有归属关系（包括主办和协办）
            List<CustomerAssignmentDO> assignments = customerAssignmentMapper.selectList(
                    new LambdaQueryWrapper<CustomerAssignmentDO>()
                            .eq(CustomerAssignmentDO::getCustomerId, customerId)
            );

            if (CollUtil.isEmpty(assignments)) {
                continue;
            }

            // 2. 先记录历史，再物理删除所有归属关系
            for (CustomerAssignmentDO assignment : assignments) {
                // 记录历史
                recordAssignmentHistory(customerId, "reclaim", assignment.getDeptId(), assignment.getUserId(),
                        null, null, reqVO.getReclaimReason(), userId, null);
            }

            // 3. 物理删除所有归属关系（使用原生 SQL 避免唯一索引冲突）
            customerAssignmentMapper.physicalDeleteByCustomerId(customerId);

            // 4. 更新客户表的分配状态
            customerMapper.updateById(
                    new CustomerDO()
                            .setId(customerId)
                            .setAssignmentStatus(0) // 未分配
                            .setDeptId(null)
            );
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changeDept(Long userId, ChangeDeptReqVO reqVO) {
        for (Long customerId : reqVO.getCustomerIds()) {
            // 1. 查询当前主办归属关系
            CustomerAssignmentDO oldAssignment = getActiveAssignmentByCustomerId(customerId);
            if (oldAssignment == null) {
                continue;
            }

            // 2. 删除旧的归属关系（物理删除）
            customerAssignmentMapper.deleteById(oldAssignment.getId());

            // 3. 创建新的归属关系
            CustomerAssignmentDO newAssignment = new CustomerAssignmentDO();
            newAssignment.setCustomerId(customerId);
            newAssignment.setAssignmentType(1); // 主办
            newAssignment.setDeptId(reqVO.getNewDeptId());
            newAssignment.setUserId(reqVO.getNewUserId());
            newAssignment.setHasViewRight(true);
            newAssignment.setHasMaintainRight(true);
            newAssignment.setAssignDate(LocalDate.now());
            newAssignment.setAssignOperatorId(userId);
            newAssignment.setRemark("主办变更");
            customerAssignmentMapper.insert(newAssignment);

            // 4. 记录历史
            recordAssignmentHistory(customerId, "dept_transfer", oldAssignment.getDeptId(), oldAssignment.getUserId(),
                    reqVO.getNewDeptId(), reqVO.getNewUserId(), reqVO.getChangeReason(), userId, null);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void claimCustomers(Long userId, Long deptId, ClaimCustomerReqVO reqVO) {
        for (Long customerId : reqVO.getCustomerIds()) {
            // 1. 检查客户是否已经被分配
            CustomerAssignmentDO existingAssignment = getActiveAssignmentByCustomerId(customerId);
            if (existingAssignment != null) {
                // 客户已被分配，跳过
                continue;
            }

            // 2. 创建归属关系（主办）
            CustomerAssignmentDO assignment = new CustomerAssignmentDO();
            assignment.setCustomerId(customerId);
            assignment.setAssignmentType(1); // 主办
            assignment.setDeptId(deptId);
            assignment.setUserId(userId);
            assignment.setHasViewRight(true);
            assignment.setHasMaintainRight(true);
            assignment.setAssignDate(LocalDate.now());
            assignment.setAssignOperatorId(userId);
            assignment.setRemark(reqVO.getRemark() != null ? reqVO.getRemark() : "快速认领");
            customerAssignmentMapper.insert(assignment);

            // 3. 更新客户表的分配状态和部门
            customerMapper.updateById(
                    new CustomerDO()
                            .setId(customerId)
                            .setAssignmentStatus(1) // 已分配
                            .setDeptId(deptId)
            );

            // 4. 记录历史
            recordAssignmentHistory(customerId, "claim", null, null,
                    deptId, userId, "快速认领客户", userId, null);
        }
    }

    @Override
    public PageResult<MyCustomerRespVO> getMyCustomerPage(Long userId, MyCustomerPageReqVO pageReqVO) {
        // 创建分页对象
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<MyCustomerRespVO> page =
                new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        // 查询我的客户分页
        com.baomidou.mybatisplus.core.metadata.IPage<MyCustomerRespVO> resultPage =
                customerAssignmentMapper.selectMyCustomerPage(page, userId, pageReqVO);

        // 转换为 PageResult
        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delegateCustomers(Long userId, DelegateCustomerReqVO reqVO) {
        // 1. 验证托管日期
        if (reqVO.getDelegateEndDate().isBefore(reqVO.getDelegateStartDate())) {
            throw exception(CUSTOMER_DELEGATION_DATE_INVALID);
        }

        // 2. 验证托管目标用户存在
        com.ynet.iplatform.module.system.dal.dataobject.user.AdminUserDO targetUser = adminUserService.getUser(reqVO.getDelegateToUserId());
        if (targetUser == null) {
            throw exception(CUSTOMER_DELEGATION_USER_NOT_EXISTS);
        }

        // 3. 批量创建托管归属记录
        for (Long customerId : reqVO.getCustomerIds()) {
            // 查询当前用户对该客户的主办归属记录
            CustomerAssignmentDO primaryAssignment = customerAssignmentMapper.selectOne(
                    new LambdaQueryWrapper<CustomerAssignmentDO>()
                            .eq(CustomerAssignmentDO::getCustomerId, customerId)
                            .eq(CustomerAssignmentDO::getUserId, userId)
                            .eq(CustomerAssignmentDO::getAssignmentType, 1) // 只能托管主办客户
            );

            if (primaryAssignment == null) {
                throw exception(CUSTOMER_DELEGATION_NOT_PRIMARY);
            }

            // 检查是否已存在托管记录
            Long existingCount = customerAssignmentMapper.selectCount(
                    new LambdaQueryWrapper<CustomerAssignmentDO>()
                            .eq(CustomerAssignmentDO::getCustomerId, customerId)
                            .eq(CustomerAssignmentDO::getUserId, reqVO.getDelegateToUserId())
                            .eq(CustomerAssignmentDO::getAssignmentType, 3) // 托管类型
            );
            if (existingCount > 0) {
                throw exception(CUSTOMER_DELEGATION_ALREADY_EXISTS);
            }

            // 创建新的托管归属记录
            CustomerAssignmentDO delegationAssignment = new CustomerAssignmentDO();
            delegationAssignment.setCustomerId(customerId);
            delegationAssignment.setAssignmentType(3); // 3=托管
            delegationAssignment.setDeptId(targetUser.getDeptId()); // 使用目标用户的部门
            delegationAssignment.setUserId(reqVO.getDelegateToUserId()); // 托管目标用户
            delegationAssignment.setHasViewRight(true);
            delegationAssignment.setHasMaintainRight(true);
            delegationAssignment.setAssignDate(LocalDate.now());
            delegationAssignment.setAssignOperatorId(userId);
            // 设置托管相关字段用于追踪
            delegationAssignment.setDelegateFromUserId(userId); // 记录托管来源用户
            delegationAssignment.setDelegateStartDate(reqVO.getDelegateStartDate());
            delegationAssignment.setDelegateEndDate(reqVO.getDelegateEndDate());
            delegationAssignment.setDelegateReason(reqVO.getDelegateReason());
            delegationAssignment.setRemark("客户托管");

            customerAssignmentMapper.insert(delegationAssignment);

            // 4. 记录历史
            recordAssignmentHistory(
                    customerId,
                    "DELEGATE", // 操作类型：托管
                    primaryAssignment.getDeptId(), // 原部门
                    primaryAssignment.getUserId(), // 原用户
                    targetUser.getDeptId(), // 新部门
                    reqVO.getDelegateToUserId(), // 新用户
                    reqVO.getDelegateReason(), // 托管原因
                    userId, // 操作人
                    null // 无流程实例
            );
        }
    }

    // ==================== 私有方法 ====================

    private CustomerAssignmentDO getActiveAssignmentByCustomerId(Long customerId) {
        return customerAssignmentMapper.selectOne(
                new LambdaQueryWrapper<CustomerAssignmentDO>()
                        .eq(CustomerAssignmentDO::getCustomerId, customerId)
                        .eq(CustomerAssignmentDO::getAssignmentType, 1) // 主办
        );
    }

    private void recordAssignmentHistory(Long customerId, String operationType, Long oldDeptId, Long oldUserId,
                                          Long newDeptId, Long newUserId, String changeReason, Long operatorId, String processInstanceId) {
        CustomerAssignmentHistoryDO history = new CustomerAssignmentHistoryDO();
        history.setCustomerId(customerId);
        history.setOperationType(operationType);
        history.setIsDelegateOperation(false);
        history.setBeforeDeptId(oldDeptId);
        history.setBeforeUserId(oldUserId);
        history.setAfterDeptId(newDeptId);
        history.setAfterUserId(newUserId);
        history.setTransferReason(changeReason);
        history.setTransferDate(LocalDate.now());
        history.setAssignOperatorId(operatorId);
        history.setProcessInstanceId(processInstanceId);
        customerAssignmentHistoryMapper.insert(history);
    }

}