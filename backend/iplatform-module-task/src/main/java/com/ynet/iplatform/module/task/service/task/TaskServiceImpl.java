package com.ynet.iplatform.module.task.service.task;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.module.task.controller.admin.task.vo.TaskBatchCreateReqVO;
import com.ynet.iplatform.module.task.controller.admin.task.vo.TaskPageReqVO;
import com.ynet.iplatform.module.task.controller.admin.task.vo.TaskSaveReqVO;
import com.ynet.iplatform.module.task.controller.app.task.vo.AppTaskPageReqVO;
import com.ynet.iplatform.module.task.controller.app.task.vo.AppTaskStatsRespVO;
import com.ynet.iplatform.module.task.dal.dataobject.task.TaskDO;
import com.ynet.iplatform.module.task.dal.mysql.task.CrmCustomerAssignmentMapper;
import com.ynet.iplatform.module.task.dal.mysql.task.TaskMapper;
import com.ynet.iplatform.module.task.util.SftpUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.module.task.enums.ErrorCodeConstants.TASK_NOT_EXISTS;

/**
 * AI智能任务 Service 实现类
 *
 * @author iplatform
 */
@Service
@Validated
@Slf4j
public class TaskServiceImpl implements TaskService {

    @Resource
    private TaskMapper taskMapper;

    @Resource
    private CrmCustomerAssignmentMapper crmCustomerAssignmentMapper;

    @Override
    public Long createTask(TaskSaveReqVO createReqVO) {
        // 如果未指定任务负责人，且存在关联客户，则自动从客户归属关系中获取主办客户经理
        if (createReqVO.getResponsibleUserId() == null && createReqVO.getCustomerId() != null) {
            Long primaryManagerId = crmCustomerAssignmentMapper.selectPrimaryAccountManagerId(createReqVO.getCustomerId());
            if (primaryManagerId != null) {
                createReqVO.setResponsibleUserId(primaryManagerId);
                log.info("[createTask] 自动设置任务负责人: customerId={}, responsibleUserId={}",
                    createReqVO.getCustomerId(), primaryManagerId);
            } else {
                log.warn("[createTask] 客户无主办客户经理，需手动指定任务负责人: customerId={}",
                    createReqVO.getCustomerId());
            }
        }

        // 插入
        TaskDO task = BeanUtil.toBean(createReqVO, TaskDO.class);
        taskMapper.insert(task);
        // 返回
        return task.getId();
    }

    @Override
    public void updateTask(TaskSaveReqVO updateReqVO) {
        // 校验存在
        if (taskMapper.selectById(updateReqVO.getId()) == null) {
            throw exception(TASK_NOT_EXISTS);
        }
        // 更新
        TaskDO updateObj = BeanUtil.toBean(updateReqVO, TaskDO.class);
        taskMapper.updateById(updateObj);
    }

    @Override
    public void deleteTask(Long id) {
        // 校验存在
        if (taskMapper.selectById(id) == null) {
            throw exception(TASK_NOT_EXISTS);
        }
        // 删除
        taskMapper.deleteById(id);
    }

    @Override
    public void deleteTaskList(List<Long> ids) {
        // 校验存在
        ids.forEach(id -> {
            if (taskMapper.selectById(id) == null) {
                throw exception(TASK_NOT_EXISTS);
            }
        });
        // 删除
        taskMapper.deleteBatchIds(ids);
    }

    @Override
    public TaskDO getTask(Long id) {
        return taskMapper.selectById(id);
    }

    @Override
    public PageResult<TaskDO> getTaskPage(TaskPageReqVO pageReqVO) {
        // 查询任务列表
        PageResult<TaskDO> pageResult = taskMapper.selectPage(pageReqVO);

        // 填充客户名称（根据客户 ID 逐个查询）
        if (pageResult.getList() != null && !pageResult.getList().isEmpty()) {
            for (TaskDO task : pageResult.getList()) {
                if (task.getCustomerId() != null) {
                    String customerName = taskMapper.selectCustomerNameById(task.getCustomerId());
                    task.setCustomerName(customerName);
                }
            }
        }

        return pageResult;
    }

    @Override
    public PageResult<TaskDO> getAppTaskPage(AppTaskPageReqVO pageReqVO) {
        return taskMapper.selectPage(pageReqVO);
    }

    @Override
    public void completeTask(Long id) {
        // 校验存在
        TaskDO task = validateTaskExists(id);

        // 校验任务未完成
        if (task.getStatus() != null && task.getStatus() == 2) {
            // 任务已完成，无需重复操作
            return;
        }

        // 更新状态为已完成
        TaskDO updateObj = new TaskDO();
        updateObj.setId(id);
        updateObj.setStatus(2);  // 2=已完成
        updateObj.setCompletedTime(LocalDateTime.now());
        taskMapper.updateById(updateObj);
    }

    @Override
    public AppTaskStatsRespVO getTaskStats() {
        // 获取今天的开始和结束时间
        LocalDateTime todayStart = LocalDate.now().atStartOfDay();
        LocalDateTime todayEnd = LocalDate.now().atTime(LocalTime.MAX);

        // 获取昨天的开始和结束时间
        LocalDateTime yesterdayStart = LocalDate.now().minusDays(1).atStartOfDay();
        LocalDateTime yesterdayEnd = LocalDate.now().minusDays(1).atTime(LocalTime.MAX);

        // 查询今天的 AI 待办任务
        List<TaskDO> todayAiTasks = taskMapper.selectList(
            new com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX<TaskDO>()
                .eq(TaskDO::getAiGenerated, 1)
                .eq(TaskDO::getStatus, 0)  // 待办
                .between(TaskDO::getCreateTime, todayStart, todayEnd)
        );

        // 查询昨天的 AI 待办任务
        List<TaskDO> yesterdayAiTasks = taskMapper.selectList(
            new com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX<TaskDO>()
                .eq(TaskDO::getAiGenerated, 1)
                .eq(TaskDO::getStatus, 0)  // 待办
                .between(TaskDO::getCreateTime, yesterdayStart, yesterdayEnd)
        );

        // 计算今天的潜在价值
        BigDecimal todayValue = todayAiTasks.stream()
            .map(TaskDO::getExpectedRevenue)
            .filter(revenue -> revenue != null)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 计算昨天的潜在价值
        BigDecimal yesterdayValue = yesterdayAiTasks.stream()
            .map(TaskDO::getExpectedRevenue)
            .filter(revenue -> revenue != null)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 计算价值变化百分比
        String valueChange = "0%";
        if (yesterdayValue.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal change = todayValue.subtract(yesterdayValue)
                .divide(yesterdayValue, 4, RoundingMode.HALF_UP)
                .multiply(new BigDecimal("100"));
            valueChange = (change.compareTo(BigDecimal.ZERO) > 0 ? "+" : "") +
                change.intValue() + "%";
        } else if (todayValue.compareTo(BigDecimal.ZERO) > 0) {
            valueChange = "+100%";
        }

        // 格式化今日潜在价值
        String totalValue = "¥0";
        if (todayValue.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal valueInWan = todayValue.divide(new BigDecimal("10000"), 0, RoundingMode.HALF_UP);
            totalValue = "¥" + valueInWan + "w";
        }

        // 查询高优先级任务数量 (P0 + P1)
        Long highPriorityCount = taskMapper.selectCount(
            new com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX<TaskDO>()
                .eq(TaskDO::getStatus, 0)  // 待办
                .in(TaskDO::getPriority, "P0", "P1")
        );

        // 查询已完成任务数量
        Long completedCount = taskMapper.selectCount(
            new com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX<TaskDO>()
                .eq(TaskDO::getStatus, 2)  // 已完成
        );

        return AppTaskStatsRespVO.builder()
            .totalValue(totalValue)
            .valueChange(valueChange)
            .highPriorityCount(highPriorityCount.intValue())
            .completedCount(completedCount.intValue())
            .build();
    }

    @Override
    public List<TaskDO> getTaskListByCustomerId(Long customerId) {
        return taskMapper.selectListByCustomerId(customerId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchCreateTasksFromCsv(TaskBatchCreateReqVO reqVO) {
        log.info("开始批量创建任务，文件名: {}", reqVO.getFileName());

        try {
            // 1. 从 SFTP 服务器下载 CSV 文件
            SftpUtil sftpUtil = new SftpUtil(
                "192.168.156.100",
                22,
                "mysftp",
                "87654321"
            );

            String remoteFilePath = "/cl/dev/upload/data/label/canvas/" + reqVO.getFileName();
            log.info("开始从 SFTP 下载文件: {}", remoteFilePath);
            String csvContent = sftpUtil.downloadFileContent(remoteFilePath);
            log.info("文件下载成功，内容大小: {} 字节", csvContent.length());

            // 2. 解析 CSV 文件，获取客户编号列表
            List<String> customerNos = new ArrayList<>();
            String[] lines = csvContent.split("\n");
            for (String line : lines) {
                if (StrUtil.isNotBlank(line)) {
                    // CSV 格式: 客户编号&#&客户姓名
                    // 示例: 1000000010&#&吴迪
                    String trimmedLine = line.trim();
                    if (trimmedLine.contains("&#&")) {
                        String[] parts = trimmedLine.split("&#&");
                        if (parts.length > 0 && StrUtil.isNotBlank(parts[0])) {
                            customerNos.add(parts[0].trim());
                        }
                    }
                }
            }

            log.info("解析 CSV 文件完成，共 {} 个客户编号", customerNos.size());

            if (customerNos.isEmpty()) {
                log.warn("CSV 文件中没有有效的客户数据");
                return 0;
            }

            // 3. 循环客户编号，查询主办客户经理并创建任务
            int createdCount = 0;
            int skippedCount = 0;
            int duplicateCount = 0;

            for (String customerNo : customerNos) {
                try {
                    // 根据客户编号查询客户 ID
                    // 注意：这里假设 customer_no 和 customer_id 是同一个值
                    // 如果不是，需要先查询 crm_customer 表获取客户 ID
                    Long customerId = parseCustomerNo(customerNo);
                    if (customerId == null) {
                        log.warn("客户编号 {} 无法解析为有效的客户 ID，跳过", customerNo);
                        skippedCount++;
                        continue;
                    }

                    // 查询主办客户经理 ID
                    Long accountManagerId = crmCustomerAssignmentMapper.selectPrimaryAccountManagerId(customerId);
                    if (accountManagerId == null) {
                        log.warn("客户 {} (ID: {}) 没有主办客户经理，跳过", customerNo, customerId);
                        skippedCount++;
                        continue;
                    }

                    // 检查是否已存在相同的任务（去重）
                    // 去重条件：同样的客户、同样的负责人、同样的任务名称、同样的截止时间
                    boolean isDuplicate = taskMapper.existsDuplicateTask(
                        customerId,
                        accountManagerId,
                        reqVO.getTaskName(),
                        reqVO.getDeadline()
                    );

                    if (isDuplicate) {
                        log.info("任务已存在，跳过创建 - 客户: {} (ID: {}), 负责人: {}, 任务名称: {}",
                            customerNo, customerId, accountManagerId, reqVO.getTaskName());
                        duplicateCount++;
                        continue;
                    }

                    log.info("为客户 {} (ID: {}) 的主办客户经理 {} 创建任务", customerNo, customerId, accountManagerId);

                    // 创建任务
                    TaskDO task = new TaskDO();
                    task.setTitle(reqVO.getTaskName());
                    // 使用请求参数中的描述，如果未提供则使用默认描述
                    String description = reqVO.getDescription() != null ? reqVO.getDescription() :
                                       "批量创建任务 - 客户编号: " + customerNo;
                    task.setDescription(description);
                    task.setTaskType("BATCH");  // 批量任务类型
                    task.setPriority("P2");     // 普通优先级
                    task.setDeadline(reqVO.getDeadline());
                    task.setStatus(0);          // 待办状态
                    task.setAiGenerated(1);     // AI批量生成
                    task.setResponsibleUserId(accountManagerId);  // 主办客户经理
                    task.setCustomerId(customerId);               // 关联客户ID

                    // 设置默认值
                    task.setComprehensiveScore(new BigDecimal("70.00"));
                    // 根据任务名称判断分类：如果包含"到期"关键字，设为到期提醒，否则设为其他
                    String category = reqVO.getTaskName().contains("到期") ? "EXPIRE_REMINDER" : "OTHER";
                    task.setCategory(category);
                    task.setBusinessValue("MEDIUM");

                    taskMapper.insert(task);
                    createdCount++;

                } catch (Exception e) {
                    log.error("为客户 {} 创建任务失败", customerNo, e);
                    skippedCount++;
                }
            }

            log.info("批量任务创建完成，成功创建 {} 个任务，去重跳过 {} 个任务，其他跳过 {} 个客户",
                createdCount, duplicateCount, skippedCount);
            return createdCount;

        } catch (Exception e) {
            log.error("批量创建任务失败", e);
            throw new RuntimeException("批量创建任务失败: " + e.getMessage(), e);
        }
    }

    /**
     * 解析客户编号为客户 ID
     * 假设客户编号就是客户 ID，如果不是需要查询数据库
     */
    private Long parseCustomerNo(String customerNo) {
        try {
            return Long.parseLong(customerNo);
        } catch (NumberFormatException e) {
            log.warn("客户编号 {} 不是有效的数字", customerNo);
            return null;
        }
    }

    private TaskDO validateTaskExists(Long id) {
        TaskDO task = taskMapper.selectById(id);
        if (task == null) {
            throw exception(TASK_NOT_EXISTS);
        }
        return task;
    }

}
