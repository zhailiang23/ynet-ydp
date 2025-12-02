package com.ynet.iplatform.module.aicrm.service.customerreturn;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;
import com.ynet.iplatform.module.aicrm.controller.admin.customerreturn.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerassignment.CustomerAssignmentDO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerassignmenthistory.CustomerAssignmentHistoryDO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerreturn.CustomerReturnApplicationDO;
import com.ynet.iplatform.module.aicrm.dal.mysql.customerassignment.CustomerAssignmentMapper;
import com.ynet.iplatform.module.aicrm.dal.mysql.customerassignmenthistory.CustomerAssignmentHistoryMapper;
import com.ynet.iplatform.module.aicrm.dal.mysql.customerreturn.CustomerReturnApplicationMapper;
import com.ynet.iplatform.module.bpm.api.task.BpmProcessInstanceApi;
import com.ynet.iplatform.module.bpm.api.task.dto.BpmProcessInstanceCreateReqDTO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户退回申请表 Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class CustomerReturnServiceImpl implements CustomerReturnService {

    /**
     * 客户退回对应的流程定义 KEY
     */
    public static final String PROCESS_KEY = "crm_customer_return";

    @Resource
    private CustomerReturnApplicationMapper returnApplicationMapper;

    @Resource
    private CustomerAssignmentMapper customerAssignmentMapper;

    @Resource
    private CustomerAssignmentHistoryMapper customerAssignmentHistoryMapper;

    @Resource
    private BpmProcessInstanceApi processInstanceApi;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long applyForReturn(Long userId, CustomerReturnApplicationApplyReqVO createReqVO) {
        // 1. 校验客户归属关系是否存在
        CustomerAssignmentDO assignment = getActiveAssignmentByCustomerId(createReqVO.getCustomerId());
        if (assignment == null) {
            throw exception(CUSTOMER_ASSIGNMENT_NOT_EXISTS);
        }

        // 2. 校验是否是当前客户经理
        if (!assignment.getUserId().equals(userId)) {
            throw exception(CUSTOMER_ASSIGNMENT_NOT_EXISTS); // 只有当前客户经理可以退回
        }

        // 3. 创建退回申请记录
        CustomerReturnApplicationDO application = BeanUtils.toBean(createReqVO, CustomerReturnApplicationDO.class);
        application.setApplicantUserId(userId);
        application.setApplyDate(LocalDate.now());
        application.setProcessStatus(1); // 审批中
        returnApplicationMapper.insert(application);

        // 4. 发起 BPM 流程
        Map<String, Object> variables = new HashMap<>();
        variables.put("customerId", createReqVO.getCustomerId());
        variables.put("returnReason", createReqVO.getReturnReason());
        variables.put("applicantUserId", userId);
        variables.put("returnToUserId", createReqVO.getReturnToUserId());

        // 转换审批人选择格式 Map<String, Long> -> Map<String, List<Long>>
        Map<String, List<Long>> startUserSelectAssignees = null;
        if (createReqVO.getStartUserSelectAssignees() != null) {
            startUserSelectAssignees = new HashMap<>();
            for (Map.Entry<String, Long> entry : createReqVO.getStartUserSelectAssignees().entrySet()) {
                startUserSelectAssignees.put(entry.getKey(), Collections.singletonList(entry.getValue()));
            }
        }

        String processInstanceId = processInstanceApi.createProcessInstance(userId,
                new BpmProcessInstanceCreateReqDTO()
                        .setProcessDefinitionKey(PROCESS_KEY)
                        .setBusinessKey(String.valueOf(application.getId()))
                        .setVariables(variables)
                        .setStartUserSelectAssignees(startUserSelectAssignees));

        // 5. 更新流程实例ID
        returnApplicationMapper.updateById(
                new CustomerReturnApplicationDO()
                        .setId(application.getId())
                        .setProcessInstanceId(processInstanceId));

        return application.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelReturnApplication(Long userId, Long id) {
        // 1. 校验申请存在
        CustomerReturnApplicationDO application = validateReturnApplicationExists(id);

        // 2. 校验是否是申请人
        if (!application.getApplicantUserId().equals(userId)) {
            throw exception(CUSTOMER_RETURN_APPLICATION_NOT_EXISTS); // 只有申请人可以取消
        }

        // 3. 校验状态
        if (application.getProcessStatus() != 1) {
            throw exception(CUSTOMER_RETURN_APPLICATION_NOT_EXISTS); // 只有审批中的申请可以取消
        }

        // 4. 更新状态
        returnApplicationMapper.updateById(
                new CustomerReturnApplicationDO()
                        .setId(id)
                        .setProcessStatus(4)); // 已取消
    }

    @Override
    public CustomerReturnApplicationDO getReturnApplication(Long id) {
        return returnApplicationMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerReturnApplicationDO> getReturnApplicationPage(CustomerReturnApplicationPageReqVO pageReqVO) {
        return returnApplicationMapper.selectPage(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateReturnStatus(Long id, Integer status) {
        // 1. 更新申请状态
        returnApplicationMapper.updateById(
                new CustomerReturnApplicationDO()
                        .setId(id)
                        .setProcessStatus(status));

        // 2. 如果审批通过，自动回收客户
        if (status == 2) {
            autoReclaimCustomerAfterReturnApproved(id);
        }
    }

    // ==================== 私有方法 ====================

    private CustomerReturnApplicationDO validateReturnApplicationExists(Long id) {
        CustomerReturnApplicationDO application = returnApplicationMapper.selectById(id);
        if (application == null) {
            throw exception(CUSTOMER_RETURN_APPLICATION_NOT_EXISTS);
        }
        return application;
    }

    private CustomerAssignmentDO getActiveAssignmentByCustomerId(Long customerId) {
        return customerAssignmentMapper.selectOne(
                new LambdaQueryWrapper<CustomerAssignmentDO>()
                        .eq(CustomerAssignmentDO::getCustomerId, customerId)
                        .eq(CustomerAssignmentDO::getAssignmentType, 1) // 主办
        );
    }

    private void autoReclaimCustomerAfterReturnApproved(Long applicationId) {
        // 1. 查询申请信息
        CustomerReturnApplicationDO application = returnApplicationMapper.selectById(applicationId);
        if (application == null) {
            return;
        }

        // 2. 查询当前归属关系
        CustomerAssignmentDO assignment = getActiveAssignmentByCustomerId(application.getCustomerId());
        if (assignment == null) {
            return;
        }

        // 3. 删除旧的归属关系（物理删除）
        customerAssignmentMapper.deleteById(assignment.getId());

        // 4. 创建新的归属关系(退回给主管)
        CustomerAssignmentDO newAssignment = new CustomerAssignmentDO();
        newAssignment.setCustomerId(application.getCustomerId());
        newAssignment.setAssignmentType(1); // 主办
        newAssignment.setDeptId(assignment.getDeptId());
        newAssignment.setUserId(application.getReturnToUserId());
        newAssignment.setHasViewRight(true);
        newAssignment.setHasMaintainRight(true);
        newAssignment.setAssignDate(LocalDate.now());
        newAssignment.setAssignOperatorId(application.getApplicantUserId());
        newAssignment.setRemark("客户退回自动分配");
        customerAssignmentMapper.insert(newAssignment);

        // 5. 记录历史
        CustomerAssignmentHistoryDO history = new CustomerAssignmentHistoryDO();
        history.setCustomerId(application.getCustomerId());
        history.setOperationType("return");
        history.setIsDelegateOperation(false);
        history.setBeforeDeptId(assignment.getDeptId());
        history.setBeforeUserId(application.getApplicantUserId());
        history.setAfterDeptId(assignment.getDeptId());
        history.setAfterUserId(application.getReturnToUserId());
        history.setTransferReason(application.getReturnReason());
        history.setTransferDate(LocalDate.now());
        history.setAssignOperatorId(application.getApplicantUserId());
        history.setProcessInstanceId(application.getProcessInstanceId());
        customerAssignmentHistoryMapper.insert(history);
    }

}
