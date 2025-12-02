package com.ynet.iplatform.module.aicrm.service.customertransfer;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;
import com.ynet.iplatform.module.aicrm.controller.admin.customertransfer.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerassignment.CustomerAssignmentDO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerassignmenthistory.CustomerAssignmentHistoryDO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customertransfer.CustomerTransferApplicationDO;
import com.ynet.iplatform.module.aicrm.dal.mysql.customerassignment.CustomerAssignmentMapper;
import com.ynet.iplatform.module.aicrm.dal.mysql.customerassignmenthistory.CustomerAssignmentHistoryMapper;
import com.ynet.iplatform.module.aicrm.dal.mysql.customertransfer.CustomerTransferApplicationMapper;
import com.ynet.iplatform.module.bpm.api.task.BpmProcessInstanceApi;
import com.ynet.iplatform.module.bpm.api.task.dto.BpmProcessInstanceCreateReqDTO;
import com.ynet.iplatform.module.system.api.user.AdminUserApi;
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
 * 客户移交申请表 Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class CustomerTransferServiceImpl implements CustomerTransferService {

    /**
     * 客户移交对应的流程定义 KEY
     */
    public static final String PROCESS_KEY = "crm_customer_transfer";

    @Resource
    private CustomerTransferApplicationMapper transferApplicationMapper;

    @Resource
    private CustomerAssignmentMapper customerAssignmentMapper;

    @Resource
    private CustomerAssignmentHistoryMapper customerAssignmentHistoryMapper;

    @Resource
    private BpmProcessInstanceApi processInstanceApi;

    @Resource
    private AdminUserApi adminUserApi;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long applyForTransfer(Long userId, CustomerTransferApplicationApplyReqVO createReqVO) {
        // 1. 校验客户归属关系是否存在
        CustomerAssignmentDO assignment = getActiveAssignmentByCustomerId(createReqVO.getCustomerId());
        if (assignment == null) {
            throw exception(CUSTOMER_ASSIGNMENT_NOT_EXISTS);
        }

        // 2. 校验是否是当前客户经理
        if (!assignment.getUserId().equals(userId)) {
            throw exception(CUSTOMER_ASSIGNMENT_NOT_EXISTS); // 只有当前客户经理可以移交
        }

        // 3. 获取申请人和接收方的部门ID
        Long applicantDeptId = adminUserApi.getUser(userId).getDeptId();
        Long toDeptId = adminUserApi.getUser(createReqVO.getToUserId()).getDeptId();

        // 4. 创建移交申请记录
        CustomerTransferApplicationDO application = BeanUtils.toBean(createReqVO, CustomerTransferApplicationDO.class);
        application.setApplicantUserId(userId);
        application.setApplicantDeptId(applicantDeptId);
        application.setToDeptId(toDeptId);
        application.setApplyDate(LocalDate.now());
        application.setProcessStatus(1); // 审批中
        transferApplicationMapper.insert(application);

        // 5. 发起 BPM 流程
        Map<String, Object> variables = new HashMap<>();
        variables.put("customerId", createReqVO.getCustomerId());
        variables.put("transferReason", createReqVO.getTransferReason());
        variables.put("applicantUserId", userId);
        variables.put("toUserId", createReqVO.getToUserId());

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

        // 6. 更新流程实例ID
        transferApplicationMapper.updateById(
                new CustomerTransferApplicationDO()
                        .setId(application.getId())
                        .setProcessInstanceId(processInstanceId));

        return application.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelTransferApplication(Long userId, Long id) {
        // 1. 校验申请存在
        CustomerTransferApplicationDO application = validateTransferApplicationExists(id);

        // 2. 校验是否是申请人
        if (!application.getApplicantUserId().equals(userId)) {
            throw exception(CUSTOMER_TRANSFER_APPLICATION_NOT_EXISTS); // 只有申请人可以取消
        }

        // 3. 校验状态
        if (application.getProcessStatus() != 1) {
            throw exception(CUSTOMER_TRANSFER_APPLICATION_NOT_EXISTS); // 只有审批中的申请可以取消
        }

        // 4. 更新状态
        transferApplicationMapper.updateById(
                new CustomerTransferApplicationDO()
                        .setId(id)
                        .setProcessStatus(4)); // 已取消
    }

    @Override
    public CustomerTransferApplicationDO getTransferApplication(Long id) {
        return transferApplicationMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerTransferApplicationDO> getTransferApplicationPage(CustomerTransferApplicationPageReqVO pageReqVO) {
        return transferApplicationMapper.selectPage(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTransferStatus(Long id, Integer status) {
        // 1. 更新申请状态
        transferApplicationMapper.updateById(
                new CustomerTransferApplicationDO()
                        .setId(id)
                        .setProcessStatus(status));

        // 2. 如果审批通过,自动执行移交
        if (status == 2) {
            autoTransferCustomerAfterApproved(id);
        }
    }

    // ==================== 私有方法 ====================

    private CustomerTransferApplicationDO validateTransferApplicationExists(Long id) {
        CustomerTransferApplicationDO application = transferApplicationMapper.selectById(id);
        if (application == null) {
            throw exception(CUSTOMER_TRANSFER_APPLICATION_NOT_EXISTS);
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

    private void autoTransferCustomerAfterApproved(Long applicationId) {
        // 1. 查询申请信息
        CustomerTransferApplicationDO application = transferApplicationMapper.selectById(applicationId);
        if (application == null) {
            return;
        }

        // 2. 查询当前归属关系
        CustomerAssignmentDO assignment = getActiveAssignmentByCustomerId(application.getCustomerId());
        if (assignment == null) {
            return;
        }

        // 3. 更新归属关系
        customerAssignmentMapper.updateById(
                new CustomerAssignmentDO()
                        .setId(assignment.getId())
                        .setDeptId(application.getToDeptId())
                        .setUserId(application.getToUserId()));

        // 4. 记录历史
        CustomerAssignmentHistoryDO history = new CustomerAssignmentHistoryDO();
        history.setCustomerId(application.getCustomerId());
        history.setOperationType("transfer");
        history.setIsDelegateOperation(false);
        history.setBeforeDeptId(application.getApplicantDeptId());
        history.setBeforeUserId(application.getApplicantUserId());
        history.setAfterDeptId(application.getToDeptId());
        history.setAfterUserId(application.getToUserId());
        history.setTransferReason(application.getTransferReason());
        history.setTransferDate(LocalDate.now());
        history.setAssignOperatorId(application.getApplicantUserId());
        history.setProcessInstanceId(application.getProcessInstanceId());
        customerAssignmentHistoryMapper.insert(history);
    }

}
