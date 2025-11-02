package cn.iocoder.yudao.module.aicrm.service.customerdelegation;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerdelegation.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerassignment.CustomerAssignmentDO;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerassignmenthistory.CustomerAssignmentHistoryDO;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerdelegation.CustomerDelegationDO;
import cn.iocoder.yudao.module.aicrm.dal.mysql.customerassignment.CustomerAssignmentMapper;
import cn.iocoder.yudao.module.aicrm.dal.mysql.customerassignmenthistory.CustomerAssignmentHistoryMapper;
import cn.iocoder.yudao.module.aicrm.dal.mysql.customerdelegation.CustomerDelegationMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户托管记录表 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CustomerDelegationServiceImpl implements CustomerDelegationService {

    @Resource
    private CustomerDelegationMapper customerDelegationMapper;

    @Resource
    private CustomerAssignmentMapper customerAssignmentMapper;

    @Resource
    private CustomerAssignmentHistoryMapper customerAssignmentHistoryMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createDelegation(Long userId, CustomerDelegationCreateReqVO createReqVO) {
        // 1. 校验客户归属关系是否存在
        CustomerAssignmentDO assignment = getActiveAssignmentByCustomerId(createReqVO.getCustomerId());
        if (assignment == null) {
            throw exception(CUSTOMER_ASSIGNMENT_NOT_EXISTS);
        }

        // 2. 校验是否已经存在进行中的托管
        CustomerDelegationDO existingDelegation = getActiveDelegationByCustomerId(createReqVO.getCustomerId());
        if (existingDelegation != null) {
            throw exception(CUSTOMER_DELEGATION_ALREADY_EXISTS);
        }

        // 3. 创建托管记录
        CustomerDelegationDO delegation = BeanUtils.toBean(createReqVO, CustomerDelegationDO.class);
        delegation.setFromUserId(assignment.getUserId());
        delegation.setDelegationStatus(1); // 托管中
        customerDelegationMapper.insert(delegation);

        // 4. 更新客户归属关系表，将客户经理临时改为托管接收人
        CustomerAssignmentDO updateAssignment = new CustomerAssignmentDO();
        updateAssignment.setId(assignment.getId());
        updateAssignment.setUserId(createReqVO.getToUserId());
        customerAssignmentMapper.updateById(updateAssignment);

        // 5. 记录历史
        CustomerAssignmentHistoryDO history = new CustomerAssignmentHistoryDO();
        history.setCustomerId(createReqVO.getCustomerId());
        history.setTransferLevel("branch_internal"); // 支行内调整
        history.setBeforeDeptId(assignment.getDeptId());
        history.setBeforeUserId(assignment.getUserId());
        history.setAfterDeptId(assignment.getDeptId());
        history.setAfterUserId(createReqVO.getToUserId());
        history.setTransferReason(createReqVO.getDelegationReason());
        history.setTransferDate(LocalDate.now());
        history.setAssignOperatorId(userId);
        customerAssignmentHistoryMapper.insert(history);

        return delegation.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void endDelegation(Long userId, CustomerDelegationEndReqVO endReqVO) {
        // 1. 校验托管记录存在
        CustomerDelegationDO delegation = validateDelegationExists(endReqVO.getId());

        // 2. 校验托管状态
        if (delegation.getDelegationStatus() != 1) {
            throw exception(CUSTOMER_DELEGATION_NOT_ACTIVE);
        }

        // 3. 更新托管记录
        CustomerDelegationDO updateDelegation = new CustomerDelegationDO();
        updateDelegation.setId(endReqVO.getId());
        updateDelegation.setDelegationStatus(0); // 已结束
        updateDelegation.setActualEndDate(LocalDate.now());
        customerDelegationMapper.updateById(updateDelegation);

        // 4. 恢复客户归属关系
        restoreAssignmentAfterDelegation(delegation, userId, endReqVO.getEndReason());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelDelegation(Long userId, Long id) {
        // 1. 校验托管记录存在
        CustomerDelegationDO delegation = validateDelegationExists(id);

        // 2. 校验托管状态
        if (delegation.getDelegationStatus() != 1) {
            throw exception(CUSTOMER_DELEGATION_NOT_ACTIVE);
        }

        // 3. 更新托管记录
        CustomerDelegationDO updateDelegation = new CustomerDelegationDO();
        updateDelegation.setId(id);
        updateDelegation.setDelegationStatus(2); // 已取消
        updateDelegation.setActualEndDate(LocalDate.now());
        customerDelegationMapper.updateById(updateDelegation);

        // 4. 恢复客户归属关系
        restoreAssignmentAfterDelegation(delegation, userId, "托管已取消");
    }

    @Override
    public CustomerDelegationDO getDelegation(Long id) {
        return customerDelegationMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerDelegationDO> getDelegationPage(CustomerDelegationPageReqVO pageReqVO) {
        return customerDelegationMapper.selectPage(pageReqVO);
    }

    @Override
    public CustomerDelegationDO getActiveDelegationByCustomerId(Long customerId) {
        return customerDelegationMapper.selectOne(
            new LambdaQueryWrapper<CustomerDelegationDO>()
                .eq(CustomerDelegationDO::getCustomerId, customerId)
                .eq(CustomerDelegationDO::getDelegationStatus, 1) // 托管中
        );
    }

    // ==================== 私有方法 ====================

    private CustomerDelegationDO validateDelegationExists(Long id) {
        CustomerDelegationDO delegation = customerDelegationMapper.selectById(id);
        if (delegation == null) {
            throw exception(CUSTOMER_DELEGATION_NOT_EXISTS);
        }
        return delegation;
    }

    private CustomerAssignmentDO getActiveAssignmentByCustomerId(Long customerId) {
        return customerAssignmentMapper.selectOne(
            new LambdaQueryWrapper<CustomerAssignmentDO>()
                .eq(CustomerAssignmentDO::getCustomerId, customerId)
                .eq(CustomerAssignmentDO::getAssignmentType, 1) // 主办
                .eq(CustomerAssignmentDO::getStatus, 1) // 生效中
        );
    }

    private void restoreAssignmentAfterDelegation(CustomerDelegationDO delegation, Long operatorId, String reason) {
        // 1. 查找客户归属关系
        CustomerAssignmentDO assignment = getActiveAssignmentByCustomerId(delegation.getCustomerId());
        if (assignment == null) {
            return;
        }

        // 2. 恢复原客户经理
        CustomerAssignmentDO updateAssignment = new CustomerAssignmentDO();
        updateAssignment.setId(assignment.getId());
        updateAssignment.setUserId(delegation.getFromUserId());
        customerAssignmentMapper.updateById(updateAssignment);

        // 3. 记录历史
        CustomerAssignmentHistoryDO history = new CustomerAssignmentHistoryDO();
        history.setCustomerId(delegation.getCustomerId());
        history.setTransferLevel("branch_internal"); // 支行内调整
        history.setBeforeDeptId(assignment.getDeptId());
        history.setBeforeUserId(delegation.getToUserId());
        history.setAfterDeptId(assignment.getDeptId());
        history.setAfterUserId(delegation.getFromUserId());
        history.setTransferReason(reason != null ? reason : "托管结束");
        history.setTransferDate(LocalDate.now());
        history.setAssignOperatorId(operatorId);
        customerAssignmentHistoryMapper.insert(history);
    }

}
