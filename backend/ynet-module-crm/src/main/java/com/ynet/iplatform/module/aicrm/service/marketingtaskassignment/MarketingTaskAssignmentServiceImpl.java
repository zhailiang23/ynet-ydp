package com.ynet.iplatform.module.aicrm.service.marketingtaskassignment;

import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.marketingtaskassignment.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.marketingtaskassignment.MarketingTaskAssignmentDO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customermarketingactivity.CustomerMarketingActivityDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.marketingtaskassignment.MarketingTaskAssignmentMapper;
import com.ynet.iplatform.module.aicrm.dal.mysql.customermarketingactivity.CustomerMarketingActivityMapper;
import com.ynet.iplatform.module.task.service.task.TaskService;
import com.ynet.iplatform.module.task.controller.admin.task.vo.TaskSaveReqVO;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 营销活动任务下发 Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
@Slf4j
public class MarketingTaskAssignmentServiceImpl implements MarketingTaskAssignmentService {

    @Resource
    private MarketingTaskAssignmentMapper marketingTaskAssignmentMapper;

    @Resource
    private CustomerMarketingActivityMapper customerMarketingActivityMapper;

    @Resource
    private TaskService taskService;

    @Resource
    private com.ynet.iplatform.module.aicrm.controller.admin.cohort.CohortController cohortController;

    @Resource
    private com.ynet.iplatform.module.aicrm.dal.mysql.customerassignment.CustomerAssignmentMapper customerAssignmentMapper;

    @Resource
    private com.ynet.iplatform.module.aicrm.service.customer.CustomerService customerService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createMarketingTaskAssignment(MarketingTaskAssignmentSaveReqVO createReqVO) {
        // 校验关联的营销活动是否存在
        CustomerMarketingActivityDO activity = customerMarketingActivityMapper.selectById(createReqVO.getMarketingActivityId());
        if (activity == null) {
            throw exception(CUSTOMER_MARKETING_ACTIVITY_NOT_EXISTS);
        }

        // 插入任务下发记录
        MarketingTaskAssignmentDO assignment = BeanUtils.toBean(createReqVO, MarketingTaskAssignmentDO.class);

        // 根据派发类型设置对应的数量和ID集合
        if ("customer".equals(createReqVO.getAssignmentType())) {
            assignment.setAssignedUserCount(CollUtil.size(createReqVO.getAssignedUserIds()));
            assignment.setAssignedCohortCount(0);
            assignment.setAssignedCohortIds(null); // 清空客群ID
        } else if ("cohort".equals(createReqVO.getAssignmentType())) {
            // 将客群ID集合转换为逗号分隔的字符串
            if (CollUtil.isNotEmpty(createReqVO.getAssignedCohortIds())) {
                String cohortIdsStr = String.join(",", createReqVO.getAssignedCohortIds());
                assignment.setAssignedCohortIds(cohortIdsStr);
                assignment.setAssignedCohortCount(createReqVO.getAssignedCohortIds().size());
            } else {
                assignment.setAssignedCohortIds(null);
                assignment.setAssignedCohortCount(0);
            }
            assignment.setAssignedUserIds(null); // 清空客户ID
            assignment.setAssignedUserCount(0);
        }

        // 设置默认状态为 active（进行中）
        if (assignment.getStatus() == null) {
            assignment.setStatus("active");
        }
        marketingTaskAssignmentMapper.insert(assignment);

        // 为所有被选中的客户经理创建任务记录
        createTasksForUsers(assignment, activity);

        // 返回
        return assignment.getId();
    }

    /**
     * 为所有客户创建任务记录（按客户维度创建）
     *
     * 根据派发类型：
     * - customer: 直接使用 assignedUserIds 作为客户ID列表
     * - cohort: 先根据客群ID获取客户列表
     *
     * 然后为每个客户查询主办客户经理，使用客户经理ID和客户ID创建任务
     */
    private void createTasksForUsers(MarketingTaskAssignmentDO assignment, CustomerMarketingActivityDO activity) {
        log.info("开始创建营销任务，派发类型: {}", assignment.getAssignmentType());

        List<Long> customerIds = new ArrayList<>();

        // 1. 根据派发类型获取客户ID列表
        if ("customer".equals(assignment.getAssignmentType())) {
            // 派发类型为"客户"：直接使用 assignedUserIds 作为客户ID
            if (CollUtil.isNotEmpty(assignment.getAssignedUserIds())) {
                customerIds.addAll(assignment.getAssignedUserIds());
                log.info("派发类型=客户，客户ID列表: {}", customerIds);
            }
        } else if ("cohort".equals(assignment.getAssignmentType())) {
            // 派发类型为"客群"：根据客群ID获取客户列表
            if (assignment.getAssignedCohortIds() != null && !assignment.getAssignedCohortIds().isEmpty()) {
                // 将逗号分隔的字符串转换为列表
                List<String> cohortIds = Arrays.asList(assignment.getAssignedCohortIds().split(","));
                log.info("派发类型=客群，客群ID列表: {}", cohortIds);
                // 调用客群服务获取客户列表
                List<Long> cohortCustomerIds = cohortController.getCustomerIdsByCohortIds(cohortIds);
                log.info("从客群获取到的客户ID列表: {}", cohortCustomerIds);
                customerIds.addAll(cohortCustomerIds);
            }
        }

        if (customerIds.isEmpty()) {
            log.warn("客户ID列表为空，无法创建任务");
            return;
        }

        log.info("共有 {} 个客户需要创建任务", customerIds.size());

        // 2. 批量查询所有客户的主办客户经理
        log.info("开始批量查询客户的主办客户经理");
        List<Map<String, Object>> managerMappings = customerAssignmentMapper.selectPrimaryAccountManagerIdsBatch(customerIds);

        // 构建客户ID -> 经理ID的映射
        Map<Long, Long> customerToManagerMap = new HashMap<>();
        for (Map<String, Object> mapping : managerMappings) {
            Long customerId = ((Number) mapping.get("customer_id")).longValue();
            Long managerId = ((Number) mapping.get("user_id")).longValue();
            customerToManagerMap.put(customerId, managerId);
        }
        log.info("批量查询完成，找到 {} 个客户有主办客户经理", customerToManagerMap.size());

        // 3. 批量查询客户信息，构建客户ID -> 客户名称的映射
        log.info("开始批量查询客户信息");
        Map<Long, String> customerNameMap = new HashMap<>();
        for (Long customerId : customerIds) {
            com.ynet.iplatform.module.aicrm.dal.dataobject.customer.CustomerDO customer = customerService.getCustomer(customerId);
            if (customer != null && customer.getCustomerName() != null) {
                customerNameMap.put(customerId, customer.getCustomerName());
            }
        }
        log.info("批量查询客户信息完成，找到 {} 个客户名称", customerNameMap.size());

        // 4. 为每个客户创建任务
        int createdCount = 0;
        int skippedCount = 0;
        for (Long customerId : customerIds) {
            // 从映射中获取主办客户经理ID
            Long accountManagerId = customerToManagerMap.get(customerId);

            // 如果没有主办客户经理，跳过该客户
            if (accountManagerId == null) {
                log.debug("客户 {} 没有主办客户经理，跳过", customerId);
                skippedCount++;
                continue;
            }

            // 从映射中获取客户名称
            String customerName = customerNameMap.get(customerId);
            if (customerName == null) {
                log.debug("客户 {} 名称为空，使用默认值", customerId);
                customerName = "客户ID:" + customerId;
            }

            log.debug("为客户 {} ({}) 的主办客户经理 {} 创建任务", customerId, customerName, accountManagerId);

            // 创建任务
            TaskSaveReqVO taskReqVO = new TaskSaveReqVO();
            taskReqVO.setTaskType("MARKETING"); // 营销任务类型
            taskReqVO.setTitle(assignment.getTaskName());
            taskReqVO.setDescription(String.format("营销活动：%s\n目标：%s %d\n任务话术：%s\n关联客户：%s (ID:%d)",
                    activity.getActivityName(),
                    getTargetTypeLabel(assignment.getTargetType()),
                    assignment.getTargetValue(),
                    assignment.getTaskScript() != null ? assignment.getTaskScript() : "",
                    customerName,
                    customerId));
            taskReqVO.setPriority("P0"); // 最高优先级
            taskReqVO.setDeadline(assignment.getEndTime());
            taskReqVO.setStatus(0); // 待办状态
            taskReqVO.setAiGenerated(1); // AI生成
            taskReqVO.setResponsibleUserId(accountManagerId); // 设置任务负责人为主办客户经理
            taskReqVO.setCustomerId(customerId); // 设置关联的客户ID
            taskReqVO.setCustomerName(customerName); // 设置客户名称

            // 设置默认值
            taskReqVO.setComprehensiveScore(new java.math.BigDecimal("70.00")); // 默认综合评分70分
            taskReqVO.setCategory("营销推广"); // 默认任务分类
            taskReqVO.setBusinessValue("MEDIUM"); // 默认业务价值：中等

            // 创建任务
            taskService.createTask(taskReqVO);
            createdCount++;
        }

        log.info("任务创建完成，成功创建 {} 个任务，跳过 {} 个客户（无主办客户经理）", createdCount, skippedCount);
    }

    /**
     * 获取目标类型标签
     */
    private String getTargetTypeLabel(String targetType) {
        if ("view".equals(targetType)) {
            return "浏览量";
        } else if ("promotion".equals(targetType)) {
            return "推广量";
        }
        return targetType;
    }

    @Override
    public void updateMarketingTaskAssignment(MarketingTaskAssignmentSaveReqVO updateReqVO) {
        // 校验存在
        validateMarketingTaskAssignmentExists(updateReqVO.getId());

        // 校验关联的营销活动是否存在
        if (updateReqVO.getMarketingActivityId() != null) {
            CustomerMarketingActivityDO activity = customerMarketingActivityMapper.selectById(updateReqVO.getMarketingActivityId());
            if (activity == null) {
                throw exception(CUSTOMER_MARKETING_ACTIVITY_NOT_EXISTS);
            }
        }

        // 更新
        MarketingTaskAssignmentDO updateObj = BeanUtils.toBean(updateReqVO, MarketingTaskAssignmentDO.class);

        // 根据派发类型设置对应的数量和ID集合
        if ("customer".equals(updateReqVO.getAssignmentType())) {
            updateObj.setAssignedUserCount(CollUtil.size(updateReqVO.getAssignedUserIds()));
            updateObj.setAssignedCohortCount(0);
            updateObj.setAssignedCohortIds(null); // 清空客群ID
        } else if ("cohort".equals(updateReqVO.getAssignmentType())) {
            // 将客群ID集合转换为逗号分隔的字符串
            if (CollUtil.isNotEmpty(updateReqVO.getAssignedCohortIds())) {
                String cohortIdsStr = String.join(",", updateReqVO.getAssignedCohortIds());
                updateObj.setAssignedCohortIds(cohortIdsStr);
                updateObj.setAssignedCohortCount(updateReqVO.getAssignedCohortIds().size());
            } else {
                updateObj.setAssignedCohortIds(null);
                updateObj.setAssignedCohortCount(0);
            }
            updateObj.setAssignedUserIds(null); // 清空客户ID
            updateObj.setAssignedUserCount(0);
        }

        marketingTaskAssignmentMapper.updateById(updateObj);
    }

    @Override
    public void deleteMarketingTaskAssignment(Long id) {
        // 校验存在
        validateMarketingTaskAssignmentExists(id);
        // 删除
        marketingTaskAssignmentMapper.deleteById(id);
    }

    @Override
    public void deleteMarketingTaskAssignmentListByIds(List<Long> ids) {
        // 删除
        marketingTaskAssignmentMapper.deleteByIds(ids);
    }

    private void validateMarketingTaskAssignmentExists(Long id) {
        if (marketingTaskAssignmentMapper.selectById(id) == null) {
            throw exception(MARKETING_TASK_ASSIGNMENT_NOT_EXISTS);
        }
    }

    @Override
    public MarketingTaskAssignmentDO getMarketingTaskAssignment(Long id) {
        return marketingTaskAssignmentMapper.selectById(id);
    }

    @Override
    public PageResult<MarketingTaskAssignmentDO> getMarketingTaskAssignmentPage(MarketingTaskAssignmentPageReqVO pageReqVO) {
        return marketingTaskAssignmentMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<MarketingTaskAssignmentRespVO> getMarketingTaskAssignmentPageWithActivity(MarketingTaskAssignmentPageReqVO pageReqVO) {
        // 计算偏移量
        int offset = (pageReqVO.getPageNo() - 1) * pageReqVO.getPageSize();
        pageReqVO.setOffset(offset);

        // 查询列表和总数
        List<MarketingTaskAssignmentRespVO> list = marketingTaskAssignmentMapper.selectPageWithActivity(pageReqVO);
        Long total = marketingTaskAssignmentMapper.selectPageCountWithActivity(pageReqVO);

        // 返回分页结果
        return new PageResult<>(list, total);
    }

}
