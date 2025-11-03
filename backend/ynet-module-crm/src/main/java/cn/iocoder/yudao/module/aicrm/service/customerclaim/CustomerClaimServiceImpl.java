package cn.iocoder.yudao.module.aicrm.service.customerclaim;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerclaim.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerassignment.CustomerAssignmentDO;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerassignmenthistory.CustomerAssignmentHistoryDO;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerclaim.CustomerClaimApplicationDO;
import cn.iocoder.yudao.module.aicrm.dal.mysql.customerassignment.CustomerAssignmentMapper;
import cn.iocoder.yudao.module.aicrm.dal.mysql.customerassignmenthistory.CustomerAssignmentHistoryMapper;
import cn.iocoder.yudao.module.aicrm.dal.mysql.customerclaim.CustomerClaimApplicationMapper;
import cn.iocoder.yudao.module.bpm.api.task.BpmProcessInstanceApi;
import cn.iocoder.yudao.module.bpm.api.task.dto.BpmProcessInstanceCreateReqDTO;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
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

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户认领申请表 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CustomerClaimServiceImpl implements CustomerClaimService {

    /**
     * 客户认领对应的流程定义 KEY
     */
    public static final String PROCESS_KEY = "crm_customer_claim";

    @Resource
    private CustomerClaimApplicationMapper claimApplicationMapper;

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
    public Long applyForClaim(Long userId, CustomerClaimApplicationApplyReqVO createReqVO) {
        // 1. 校验客户是否可以认领 (客户必须是未分配状态)
        CustomerAssignmentDO existingAssignment = getActiveAssignmentByCustomerId(createReqVO.getCustomerId());
        if (existingAssignment != null) {
            throw exception(CUSTOMER_ASSIGNMENT_NOT_EXISTS); // 客户已经被分配，不能认领
        }

        // 2. 获取申请人部门ID
        Long deptId = adminUserApi.getUser(userId).getDeptId();

        // 3. 创建认领申请记录
        CustomerClaimApplicationDO application = BeanUtils.toBean(createReqVO, CustomerClaimApplicationDO.class);
        application.setApplicantUserId(userId);
        application.setApplicantDeptId(deptId);
        application.setApplyDate(LocalDate.now());
        application.setProcessStatus(1); // 审批中
        claimApplicationMapper.insert(application);

        // 4. 发起 BPM 流程
        Map<String, Object> variables = new HashMap<>();
        variables.put("customerId", createReqVO.getCustomerId());
        variables.put("applyReason", createReqVO.getApplyReason());
        variables.put("applicantUserId", userId);
        variables.put("applicantDeptId", deptId);

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
        claimApplicationMapper.updateById(
                new CustomerClaimApplicationDO()
                        .setId(application.getId())
                        .setProcessInstanceId(processInstanceId));

        return application.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelClaimApplication(Long userId, Long id) {
        // 1. 校验申请存在
        CustomerClaimApplicationDO application = validateClaimApplicationExists(id);

        // 2. 校验是否是申请人
        if (!application.getApplicantUserId().equals(userId)) {
            throw exception(CUSTOMER_CLAIM_APPLICATION_NOT_EXISTS); // 只有申请人可以取消
        }

        // 3. 校验状态
        if (application.getProcessStatus() != 1) {
            throw exception(CUSTOMER_CLAIM_APPLICATION_NOT_EXISTS); // 只有审批中的申请可以取消
        }

        // 4. 更新状态
        claimApplicationMapper.updateById(
                new CustomerClaimApplicationDO()
                        .setId(id)
                        .setProcessStatus(4)); // 已取消
    }

    @Override
    public CustomerClaimApplicationDO getClaimApplication(Long id) {
        return claimApplicationMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerClaimApplicationDO> getClaimApplicationPage(CustomerClaimApplicationPageReqVO pageReqVO) {
        return claimApplicationMapper.selectPage(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateClaimStatus(Long id, Integer status) {
        // 1. 更新申请状态
        claimApplicationMapper.updateById(
                new CustomerClaimApplicationDO()
                        .setId(id)
                        .setProcessStatus(status));

        // 2. 如果审批通过，自动分配客户
        if (status == 2) {
            autoAssignCustomerAfterClaimApproved(id);
        }
    }

    // ==================== 私有方法 ====================

    private CustomerClaimApplicationDO validateClaimApplicationExists(Long id) {
        CustomerClaimApplicationDO application = claimApplicationMapper.selectById(id);
        if (application == null) {
            throw exception(CUSTOMER_CLAIM_APPLICATION_NOT_EXISTS);
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

    private void autoAssignCustomerAfterClaimApproved(Long applicationId) {
        // 1. 查询申请信息
        CustomerClaimApplicationDO application = claimApplicationMapper.selectById(applicationId);
        if (application == null) {
            return;
        }

        // 2. 创建客户归属关系
        CustomerAssignmentDO assignment = new CustomerAssignmentDO();
        assignment.setCustomerId(application.getCustomerId());
        assignment.setAssignmentType(1); // 主办
        assignment.setDeptId(application.getApplicantDeptId());
        assignment.setUserId(application.getApplicantUserId());
        assignment.setHasViewRight(true);
        assignment.setHasMaintainRight(true);
        assignment.setAssignDate(LocalDate.now());
        assignment.setAssignOperatorId(application.getApplicantUserId());
        assignment.setRemark("客户认领自动分配");
        customerAssignmentMapper.insert(assignment);

        // 3. 记录历史
        CustomerAssignmentHistoryDO history = new CustomerAssignmentHistoryDO();
        history.setCustomerId(application.getCustomerId());
        history.setOperationType("claim");
        history.setIsDelegateOperation(false);
        history.setBeforeDeptId(null);
        history.setBeforeUserId(null);
        history.setAfterDeptId(application.getApplicantDeptId());
        history.setAfterUserId(application.getApplicantUserId());
        history.setTransferReason(application.getApplyReason());
        history.setTransferDate(LocalDate.now());
        history.setAssignOperatorId(application.getApplicantUserId());
        history.setProcessInstanceId(application.getProcessInstanceId());
        customerAssignmentHistoryMapper.insert(history);
    }

}
