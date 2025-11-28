package com.ynet.iplatform.module.twins.service.statistics;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ynet.iplatform.module.twins.controller.admin.statistics.vo.TwinsOverviewRespVO;
import com.ynet.iplatform.module.twins.dal.dataobject.customerAdmin.CustomerAdminDO;
import com.ynet.iplatform.module.twins.dal.dataobject.chatcollectinfo.ChatCollectInfoDO;
import com.ynet.iplatform.module.twins.dal.dataobject.chatautomessages.ChatAutoMessagesDO;
import com.ynet.iplatform.module.twins.dal.mysql.customerAdmin.CustomerAdminMapper;
import com.ynet.iplatform.module.twins.dal.mysql.chatcollectinfo.ChatCollectInfoMapper;
import com.ynet.iplatform.module.twins.dal.mysql.chatautomessages.ChatAutoMessagesMapper;
import com.ynet.iplatform.module.twins.dal.mysql.customeradminchatsettings.CustomerAdminChatSettingsMapper;
import com.ynet.iplatform.module.customer.dal.dataobject.chatsessions.ChatSessionsDO;
import com.ynet.iplatform.module.customer.dal.mysql.chatsessions.ChatSessionsMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 数字分身统计服务实现类
 */
@Service
@Validated
public class TwinsStatisticsServiceImpl implements TwinsStatisticsService {

    @Resource
    private CustomerAdminMapper customerAdminMapper;

    @Resource
    private CustomerAdminChatSettingsMapper customerAdminChatSettingsMapper;

    @Resource
    private ChatSessionsMapper chatSessionsMapper;

    @Resource
    private ChatCollectInfoMapper chatCollectInfoMapper;

    @Resource
    private ChatAutoMessagesMapper chatAutoMessagesMapper;

    @Override
    public TwinsOverviewRespVO getOverview() {
        TwinsOverviewRespVO result = new TwinsOverviewRespVO();

        // 获取当前月份的第一天
        LocalDateTime monthStart = LocalDateTime.now()
                .with(TemporalAdjusters.firstDayOfMonth())
                .withHour(0).withMinute(0).withSecond(0).withNano(0);

        // 获取上月的时间范围
        LocalDateTime lastMonthStart = monthStart.minusMonths(1);
        LocalDateTime lastMonthEnd = monthStart;

        // 1. 总员工数 (客服数量)
        Long totalEmployees = customerAdminMapper.selectCount(new LambdaQueryWrapper<CustomerAdminDO>());
        result.setTotalEmployees(totalEmployees.intValue());

        // 2. 活跃 AI 数量 (is_ai_enabled = 1)
        Long activeAiCount = customerAdminChatSettingsMapper.countActiveAi();
        result.setActiveAiCount(activeAiCount.intValue());

        // 3. 总对话数和本月对话数
        Long totalSessions = chatSessionsMapper.selectCount(new LambdaQueryWrapper<ChatSessionsDO>());
        result.setTotalSessions(totalSessions.intValue());

        Long monthSessions = chatSessionsMapper.selectCount(new LambdaQueryWrapper<ChatSessionsDO>()
                .ge(ChatSessionsDO::getQueriedAt, monthStart));
        result.setMonthSessions(monthSessions.intValue());

        // 4. 总留资数和本月留资数
        Long totalCollectInfos = chatCollectInfoMapper.selectCount(new LambdaQueryWrapper<ChatCollectInfoDO>());
        result.setTotalCollectInfos(totalCollectInfos.intValue());

        Long monthCollectInfos = chatCollectInfoMapper.selectCount(new LambdaQueryWrapper<ChatCollectInfoDO>()
                .ge(ChatCollectInfoDO::getCreateTime, monthStart));
        result.setMonthCollectInfos(monthCollectInfos.intValue());

        // 5. 客户满意度 (rate 的平均值)
        List<ChatSessionsDO> sessionsWithRate = chatSessionsMapper.selectList(new LambdaQueryWrapper<ChatSessionsDO>()
                .isNotNull(ChatSessionsDO::getRate)
                .gt(ChatSessionsDO::getRate, 0));
        if (!sessionsWithRate.isEmpty()) {
            double avgRate = sessionsWithRate.stream()
                    .mapToInt(s -> s.getRate().intValue())
                    .average()
                    .orElse(0);
            result.setAvgSatisfaction(BigDecimal.valueOf(avgRate).setScale(1, RoundingMode.HALF_UP));
        } else {
            result.setAvgSatisfaction(BigDecimal.ZERO);
        }

        // 6. 平均响应时间 (从 queried_at 到 accepted_at 的时间差，单位秒)
        List<ChatSessionsDO> sessionsWithAccepted = chatSessionsMapper.selectList(new LambdaQueryWrapper<ChatSessionsDO>()
                .isNotNull(ChatSessionsDO::getAcceptedAt));
        if (!sessionsWithAccepted.isEmpty()) {
            double avgResponseSeconds = sessionsWithAccepted.stream()
                    .mapToLong(s -> Duration.between(s.getQueriedAt(), s.getAcceptedAt()).getSeconds())
                    .average()
                    .orElse(0);
            result.setAvgResponseTime(BigDecimal.valueOf(avgResponseSeconds).setScale(1, RoundingMode.HALF_UP));
        } else {
            result.setAvgResponseTime(BigDecimal.ZERO);
        }

        // 7. 留资类型统计 (按 customer_chat_collect_info.template_id 分组，关联 customer_chat_auto_messages 获取名称)
        List<ChatCollectInfoDO> allCollectInfos = chatCollectInfoMapper.selectList(new LambdaQueryWrapper<>());

        // 按 template_id 分组
        Map<Integer, List<ChatCollectInfoDO>> infosByTemplate = allCollectInfos.stream()
                .filter(info -> info.getTemplateId() != null && info.getTemplateId() > 0)
                .collect(Collectors.groupingBy(ChatCollectInfoDO::getTemplateId));

        // 获取所有涉及的 template_id 对应的 auto_messages 记录
        Set<Integer> templateIds = infosByTemplate.keySet();
        Map<Integer, ChatAutoMessagesDO> autoMessagesMap = new HashMap<>();
        if (!templateIds.isEmpty()) {
            List<ChatAutoMessagesDO> autoMessages = chatAutoMessagesMapper.selectList(
                    new LambdaQueryWrapper<ChatAutoMessagesDO>()
                            .in(ChatAutoMessagesDO::getId, templateIds));
            autoMessagesMap = autoMessages.stream()
                    .collect(Collectors.toMap(ChatAutoMessagesDO::getId, m -> m));
        }

        List<TwinsOverviewRespVO.CollectInfoTypeStats> typeStatsList = new ArrayList<>();
        for (Map.Entry<Integer, List<ChatCollectInfoDO>> entry : infosByTemplate.entrySet()) {
            Integer templateId = entry.getKey();
            List<ChatCollectInfoDO> infosForTemplate = entry.getValue();

            TwinsOverviewRespVO.CollectInfoTypeStats stats = new TwinsOverviewRespVO.CollectInfoTypeStats();
            stats.setTemplateId(templateId);

            // 从 auto_messages 获取名称
            ChatAutoMessagesDO autoMessage = autoMessagesMap.get(templateId);
            stats.setName(autoMessage != null ? autoMessage.getName() : "未知类型");

            // 总数
            stats.setTotal(infosForTemplate.size());

            // 本月数
            int monthCount = (int) infosForTemplate.stream()
                    .filter(info -> info.getCreateTime() != null && !info.getCreateTime().isBefore(monthStart))
                    .count();
            stats.setMonthCount(monthCount);

            // 上月数
            int lastMonthCount = (int) infosForTemplate.stream()
                    .filter(info -> info.getCreateTime() != null
                            && !info.getCreateTime().isBefore(lastMonthStart)
                            && info.getCreateTime().isBefore(lastMonthEnd))
                    .count();
            stats.setLastMonthCount(lastMonthCount);

            typeStatsList.add(stats);
        }
        result.setCollectInfoTypeStats(typeStatsList);

        // 8. 员工使用排行 (按本月对话数排序)
        List<CustomerAdminDO> admins = customerAdminMapper.selectList(new LambdaQueryWrapper<>());
        List<ChatSessionsDO> monthSessionsList = chatSessionsMapper.selectList(new LambdaQueryWrapper<ChatSessionsDO>()
                .ge(ChatSessionsDO::getQueriedAt, monthStart));
        List<ChatCollectInfoDO> monthCollectInfoList = chatCollectInfoMapper.selectList(new LambdaQueryWrapper<ChatCollectInfoDO>()
                .ge(ChatCollectInfoDO::getCreateTime, monthStart));

        // 按客服 ID 统计本月对话数
        Map<Integer, Long> sessionCountByAdmin = monthSessionsList.stream()
                .filter(s -> s.getAdminId() != null && s.getAdminId() > 0)
                .collect(Collectors.groupingBy(ChatSessionsDO::getAdminId, Collectors.counting()));

        // 按客服 ID 统计本月留资数
        Map<Integer, Long> collectInfoCountByAdmin = monthCollectInfoList.stream()
                .filter(info -> info.getAdminId() != null && info.getAdminId() > 0)
                .collect(Collectors.groupingBy(ChatCollectInfoDO::getAdminId, Collectors.counting()));

        List<TwinsOverviewRespVO.EmployeeRankItem> rankList = admins.stream()
                .map(admin -> {
                    TwinsOverviewRespVO.EmployeeRankItem item = new TwinsOverviewRespVO.EmployeeRankItem();
                    item.setAdminId(admin.getId());
                    item.setName(admin.getUsername());
                    item.setDepartment(""); // 暂无部门字段
                    item.setMonthSessions(sessionCountByAdmin.getOrDefault(admin.getId(), 0L).intValue());
                    item.setMonthCollectInfos(collectInfoCountByAdmin.getOrDefault(admin.getId(), 0L).intValue());
                    return item;
                })
                .sorted((a, b) -> b.getMonthSessions() - a.getMonthSessions()) // 按本月对话数降序
                .limit(10) // 取前 10 名
                .collect(Collectors.toList());

        result.setEmployeeRankList(rankList);

        return result;
    }
}
