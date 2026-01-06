package com.ynet.iplatform.module.aicrm.service.marketingtaskassignment;

import cn.hutool.core.collection.CollUtil;
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
public class MarketingTaskAssignmentServiceImpl implements MarketingTaskAssignmentService {

    @Resource
    private MarketingTaskAssignmentMapper marketingTaskAssignmentMapper;

    @Resource
    private CustomerMarketingActivityMapper customerMarketingActivityMapper;

    @Resource
    private TaskService taskService;

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
        assignment.setAssignedUserCount(CollUtil.size(createReqVO.getAssignedUserIds()));
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
     * 为所有被选中的客户经理创建任务记录
     */
    private void createTasksForUsers(MarketingTaskAssignmentDO assignment, CustomerMarketingActivityDO activity) {
        if (CollUtil.isEmpty(assignment.getAssignedUserIds())) {
            return;
        }

        for (Long userId : assignment.getAssignedUserIds()) {
            TaskSaveReqVO taskReqVO = new TaskSaveReqVO();
            taskReqVO.setTaskType("MARKETING"); // 营销任务类型
            taskReqVO.setTitle(assignment.getTaskName());
            taskReqVO.setDescription(String.format("营销活动：%s\n目标：%s %d\n任务话术：%s",
                    activity.getActivityName(),
                    getTargetTypeLabel(assignment.getTargetType()),
                    assignment.getTargetValue(),
                    assignment.getTaskScript() != null ? assignment.getTaskScript() : ""));
            taskReqVO.setPriority("P2"); // 普通优先级
            taskReqVO.setDeadline(assignment.getEndTime());
            taskReqVO.setStatus(0); // 待办状态
            taskReqVO.setAiGenerated(0); // 非AI生成
            taskReqVO.setResponsibleUserId(userId); // 设置任务负责人为派发对象

            // 设置默认值
            taskReqVO.setComprehensiveScore(new java.math.BigDecimal("70.00")); // 默认综合评分70分
            taskReqVO.setCategory("营销推广"); // 默认任务分类
            taskReqVO.setBusinessValue("MEDIUM"); // 默认业务价值：中等
            // taskReqVO.setTriggerSource("营销活动任务下发"); // Task模块没有此字段

            // 创建任务
            taskService.createTask(taskReqVO);
        }
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
        updateObj.setAssignedUserCount(CollUtil.size(updateReqVO.getAssignedUserIds()));
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
