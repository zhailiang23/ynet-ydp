package com.ynet.iplatform.module.task.service.scoring;

import cn.hutool.core.collection.CollUtil;
import com.ynet.iplatform.module.task.dal.dataobject.factor.TaskScoringConditionDO;
import com.ynet.iplatform.module.task.dal.dataobject.factor.TaskScoringFactorDO;
import com.ynet.iplatform.module.task.dal.dataobject.task.TaskDO;
import com.ynet.iplatform.module.task.dal.mysql.factor.TaskScoringConditionMapper;
import com.ynet.iplatform.module.task.dal.mysql.factor.TaskScoringFactorMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 任务动态评分服务实现
 *
 * @author ynet
 */
@Service
@Slf4j
public class TaskScoringServiceImpl implements TaskScoringService {

    @Resource
    private TaskScoringFactorMapper taskScoringFactorMapper;

    @Resource
    private TaskScoringConditionMapper taskScoringConditionMapper;

    @Override
    public List<TaskDO> scoreAndSortTasks(List<TaskDO> tasks, Map<Long, CustomerScoreData> customerDataMap) {
        if (CollUtil.isEmpty(tasks)) {
            return tasks;
        }

        // 1. 查询所有启用的评分因子
        List<TaskScoringFactorDO> factors = taskScoringFactorMapper.selectList(
                new com.ynet.iplatform.module.task.controller.admin.factor.vo.TaskScoringFactorListReqVO()
        ).stream()
                .filter(f -> Boolean.TRUE.equals(f.getEnabled()))
                .collect(Collectors.toList());

        if (CollUtil.isEmpty(factors)) {
            log.warn("[scoreAndSortTasks] 没有启用的评分因子，跳过动态评分");
            return tasks;
        }

        log.info("[scoreAndSortTasks] 开始动态评分，任务数: {}, 启用的因子数: {}", tasks.size(), factors.size());

        // 2. 查询所有因子的条件
        Map<Long, List<TaskScoringConditionDO>> factorConditionsMap = new HashMap<>();
        for (TaskScoringFactorDO factor : factors) {
            List<TaskScoringConditionDO> conditions = taskScoringConditionMapper.selectListByFactorId(factor.getId());
            factorConditionsMap.put(factor.getId(), conditions);
            log.debug("[scoreAndSortTasks] 因子 {} 有 {} 个条件", factor.getFactorName(), conditions.size());
        }

        // 3. 使用传入的客户数据（避免循环依赖）
        log.info("[scoreAndSortTasks] 使用传入的客户数据: {} 条", customerDataMap != null ? customerDataMap.size() : 0);

        // 4. 对每个任务进行评分
        for (TaskDO task : tasks) {
            BigDecimal newScore = calculateTaskScore(task, factors, factorConditionsMap, customerDataMap);
            task.setComprehensiveScore(newScore);
        }

        // 5. 按新分数降序排序
        tasks.sort((a, b) -> {
            BigDecimal scoreA = a.getComprehensiveScore() != null ? a.getComprehensiveScore() : BigDecimal.ZERO;
            BigDecimal scoreB = b.getComprehensiveScore() != null ? b.getComprehensiveScore() : BigDecimal.ZERO;
            return scoreB.compareTo(scoreA);
        });

        log.info("[scoreAndSortTasks] 动态评分完成");
        return tasks;
    }

    /**
     * 计算单个任务的分数
     */
    private BigDecimal calculateTaskScore(TaskDO task,
                                           List<TaskScoringFactorDO> factors,
                                           Map<Long, List<TaskScoringConditionDO>> factorConditionsMap,
                                           Map<Long, CustomerScoreData> customerDataMap) {
        // 基础分值
        BigDecimal baseScore = task.getComprehensiveScore() != null ? task.getComprehensiveScore() : BigDecimal.ZERO;
        BigDecimal finalScore = baseScore;

        log.debug("[calculateTaskScore] 任务 {} 基础分值: {}", task.getId(), baseScore);

        // 遍历所有因子
        for (TaskScoringFactorDO factor : factors) {
            List<TaskScoringConditionDO> conditions = factorConditionsMap.get(factor.getId());

            // 判断是否满足因子条件
            boolean conditionsMet = evaluateConditions(task, conditions, factor.getLogicType(), customerDataMap);

            if (conditionsMet) {
                log.debug("[calculateTaskScore] 任务 {} 满足因子 {} 的条件", task.getId(), factor.getFactorName());
                // 根据影响方式调整分数
                finalScore = applyFactorImpact(finalScore, factor);
            } else {
                log.debug("[calculateTaskScore] 任务 {} 不满足因子 {} 的条件", task.getId(), factor.getFactorName());
            }
        }

        log.debug("[calculateTaskScore] 任务 {} 最终分值: {}", task.getId(), finalScore);
        return finalScore;
    }

    /**
     * 评估条件是否满足
     */
    private boolean evaluateConditions(TaskDO task,
                                        List<TaskScoringConditionDO> conditions,
                                        String logicType,
                                        Map<Long, CustomerScoreData> customerDataMap) {
        if (CollUtil.isEmpty(conditions)) {
            // 没有条件，默认满足
            return true;
        }

        boolean isAndLogic = "AND".equalsIgnoreCase(logicType);

        for (TaskScoringConditionDO condition : conditions) {
            boolean singleConditionMet = evaluateSingleCondition(task, condition, customerDataMap);

            if (isAndLogic) {
                // AND 逻辑：任意一个条件不满足，直接返回 false
                if (!singleConditionMet) {
                    return false;
                }
            } else {
                // OR 逻辑：任意一个条件满足，直接返回 true
                if (singleConditionMet) {
                    return true;
                }
            }
        }

        // AND 逻辑：所有条件都满足，返回 true
        // OR 逻辑：所有条件都不满足，返回 false
        return isAndLogic;
    }

    /**
     * 评估单个条件是否满足
     */
    private boolean evaluateSingleCondition(TaskDO task,
                                             TaskScoringConditionDO condition,
                                             Map<Long, CustomerScoreData> customerDataMap) {
        String dataSource = condition.getDataSource();
        String fieldName = condition.getFieldName();
        String operator = condition.getOperator();
        String expectedValue = condition.getFieldValue();

        // 获取实际值
        String actualValue = getFieldValue(task, dataSource, fieldName, customerDataMap);

        if (actualValue == null) {
            log.debug("[evaluateSingleCondition] 字段值为空: dataSource={}, fieldName={}", dataSource, fieldName);
            return false;
        }

        // 根据操作符进行比较
        boolean result = compareValues(actualValue, operator, expectedValue);
        log.debug("[evaluateSingleCondition] 条件判断: {} {} {} = {}", actualValue, operator, expectedValue, result);
        return result;
    }

    /**
     * 获取字段值
     */
    private String getFieldValue(TaskDO task, String dataSource, String fieldName, Map<Long, CustomerScoreData> customerDataMap) {
        if ("customer_profile".equals(dataSource)) {
            // 客户画像数据
            if (task.getCustomerId() == null) {
                return null;
            }

            CustomerScoreData customer = customerDataMap != null ? customerDataMap.get(task.getCustomerId()) : null;
            if (customer == null) {
                return null;
            }

            switch (fieldName) {
                case "account_balance":
                    // 账户余额（使用信用评分代替，实际项目中应使用资产总额）
                    return customer.getCreditScore() != null ? customer.getCreditScore().toString() : null;
                case "customer_level":
                    // 客户等级
                    return customer.getCustomerLevel();
                case "risk_rating":
                    // 风险评级（信用等级）
                    return customer.getCreditLevel();
                case "account_age_days":
                    // 开户时长（天数）- 使用 createTime 计算
                    if (customer.getCreateTime() != null) {
                        long days = ChronoUnit.DAYS.between(customer.getCreateTime(), LocalDateTime.now());
                        return String.valueOf(days);
                    }
                    return null;
                default:
                    return null;
            }
        } else if ("task_attribute".equals(dataSource)) {
            // 任务属性数据
            switch (fieldName) {
                case "task_type":
                    return task.getTaskType();
                case "task_priority":
                    return task.getPriority();
                case "deadline_days":
                    // 截止天数 = deadline - 当前日期
                    if (task.getDeadline() != null) {
                        LocalDate deadlineDate = task.getDeadline().toLocalDate();
                        LocalDate today = LocalDate.now();
                        long days = ChronoUnit.DAYS.between(today, deadlineDate);
                        return String.valueOf(days);
                    }
                    return null;
                default:
                    return null;
            }
        }

        return null;
    }

    /**
     * 比较值
     */
    private boolean compareValues(String actualValue, String operator, String expectedValue) {
        try {
            switch (operator) {
                case "EQUAL":
                    return actualValue.equals(expectedValue);

                case "NOT_EQUAL":
                    return !actualValue.equals(expectedValue);

                case "GREATER_THAN":
                    return compareNumeric(actualValue, expectedValue) > 0;

                case "LESS_THAN":
                    return compareNumeric(actualValue, expectedValue) < 0;

                case "GREATER_EQUAL":
                    return compareNumeric(actualValue, expectedValue) >= 0;

                case "LESS_EQUAL":
                    return compareNumeric(actualValue, expectedValue) <= 0;

                case "IN":
                    // 期望值是逗号分隔的列表
                    String[] expectedValues = expectedValue.split(",");
                    for (String value : expectedValues) {
                        if (actualValue.trim().equalsIgnoreCase(value.trim())) {
                            return true;
                        }
                    }
                    return false;

                case "LIKE":
                    return actualValue.contains(expectedValue);

                default:
                    log.warn("[compareValues] 未知的操作符: {}", operator);
                    return false;
            }
        } catch (Exception e) {
            log.error("[compareValues] 比较值时出错: actualValue={}, operator={}, expectedValue={}",
                    actualValue, operator, expectedValue, e);
            return false;
        }
    }

    /**
     * 数值比较
     */
    private int compareNumeric(String actualValue, String expectedValue) {
        BigDecimal actual = new BigDecimal(actualValue);
        BigDecimal expected = new BigDecimal(expectedValue);
        return actual.compareTo(expected);
    }

    /**
     * 应用因子影响
     *
     * @param currentScore 当前分数
     * @param factor       因子
     * @return 调整后的分数
     */
    private BigDecimal applyFactorImpact(BigDecimal currentScore, TaskScoringFactorDO factor) {
        String impactType = factor.getImpactType();
        int adjustment = factor.getScoreAdjustment();
        int weight = factor.getWeight();

        switch (impactType) {
            case "direct":
                // 直接设定评分
                log.debug("[applyFactorImpact] 因子 {} 直接设定评分为 {}", factor.getFactorName(), adjustment);
                return new BigDecimal(adjustment);

            case "weight":
                // 调整评分权重（百分比）
                BigDecimal multiplier = BigDecimal.ONE.add(new BigDecimal(adjustment).divide(new BigDecimal(100), 4, RoundingMode.HALF_UP));
                BigDecimal weightedScore = currentScore.multiply(multiplier).setScale(2, RoundingMode.HALF_UP);
                log.debug("[applyFactorImpact] 因子 {} 权重调整: {} * {} = {}",
                        factor.getFactorName(), currentScore, multiplier, weightedScore);
                return weightedScore;

            case "score":
            default:
                // 评分值加减（考虑权重）
                BigDecimal weightedAdjustment = new BigDecimal(adjustment)
                        .multiply(new BigDecimal(weight))
                        .divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
                BigDecimal newScore = currentScore.add(weightedAdjustment);
                log.debug("[applyFactorImpact] 因子 {} 分值调整: {} + ({} * {}%) = {}",
                        factor.getFactorName(), currentScore, adjustment, weight, newScore);
                return newScore;
        }
    }
}
