package com.ynet.iplatform.module.aicrm.service.practiceevaluation;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.ynet.iplatform.module.aicrm.controller.admin.practiceuserrecord.vo.PracticeEvaluationRespVO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.practiceconversation.PracticeConversationDO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.practicecourse.PracticeCourseDO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.practiceuserrecord.PracticeUserRecordDO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.practicevirtualcustomer.PracticeVirtualCustomerDO;
import com.ynet.iplatform.module.aicrm.dal.mysql.practiceconversation.PracticeConversationMapper;
import com.ynet.iplatform.module.aicrm.dal.mysql.practicecourse.PracticeCourseMapper;
import com.ynet.iplatform.module.aicrm.dal.mysql.practiceuserrecord.PracticeUserRecordMapper;
import com.ynet.iplatform.module.aicrm.service.practiceconversation.PracticeConversationService;
import com.ynet.iplatform.module.aicrm.service.practicecourse.PracticeCourseService;
import com.ynet.iplatform.module.aicrm.service.practicevirtualcustomer.PracticeVirtualCustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 练习评估服务实现
 *
 * @author 系统
 */
@Service
@Slf4j
public class PracticeEvaluationServiceImpl implements PracticeEvaluationService {

    @Resource
    private PracticeUserRecordMapper practiceUserRecordMapper;

    @Resource
    private PracticeConversationMapper practiceConversationMapper;

    @Resource
    private PracticeCourseMapper practiceCourseMapper;

    @Resource
    private PracticeConversationService practiceConversationService;

    @Resource
    private PracticeCourseService practiceCourseService;

    @Resource
    private PracticeVirtualCustomerService practiceVirtualCustomerService;

    @Resource
    private DifyEvaluationClient difyEvaluationClient;

    private final ObjectMapper objectMapper;

    public PracticeEvaluationServiceImpl() {
        // 配置 ObjectMapper 使用下划线命名策略 (snake_case)
        this.objectMapper = new ObjectMapper();
        this.objectMapper.setPropertyNamingStrategy(com.fasterxml.jackson.databind.PropertyNamingStrategies.SNAKE_CASE);
    }

    @Override
    public PracticeEvaluationRespVO evaluatePractice(Long recordId) {
        log.info("开始评估练习记录, recordId: {}", recordId);

        try {
            // 1. 获取练习记录
            PracticeUserRecordDO record = practiceUserRecordMapper.selectById(recordId);
            if (record == null) {
                log.error("练习记录不存在, recordId: {}", recordId);
                throw new RuntimeException("练习记录不存在");
            }

            // 2. 获取对话记录
            List<PracticeConversationDO> conversations = practiceConversationService.getConversationListByRecordId(recordId);
            if (conversations == null || conversations.isEmpty()) {
                log.warn("练习记录没有对话内容, recordId: {}", recordId);
                throw new RuntimeException("练习记录没有对话内容");
            }

            // 3. 组织对话内容
            StringBuilder trainingContent = new StringBuilder();
            for (PracticeConversationDO conversation : conversations) {
                String role = "user".equals(conversation.getRole()) ? "学员" : "客户";
                trainingContent.append(role).append(": ").append(conversation.getMessageContent()).append("\n");
            }

            // 4. 获取所有课程列表
            List<PracticeCourseDO> allCourses = practiceCourseMapper.selectList();
            JSONArray courseArray = new JSONArray();
            for (PracticeCourseDO course : allCourses) {
                JSONObject courseObj = new JSONObject();
                courseObj.set("id", course.getId());
                courseObj.set("name", course.getName());
                courseObj.set("description", course.getDescription());
                courseObj.set("scriptId", course.getScriptId());  // 剧本ID
                courseArray.add(courseObj);
            }
            String courseList = courseArray.toString();

            // 5. 调用 Dify 评估服务
            log.info("调用 Dify 评估服务, recordId: {}, 对话条数: {}, 课程数: {}",
                    recordId, conversations.size(), allCourses.size());

            DifyEvaluationClient.DifyEvaluationResponse difyResponse = difyEvaluationClient.evaluateTraining(
                    trainingContent.toString(),
                    record.getCourseId() != null ? record.getCourseId().toString() : null,
                    courseList
            );

            // 6. 解析评估结果
            JSONObject evaluationResult = difyResponse.getEvaluationResult();
            PracticeEvaluationRespVO evaluation = parseDifyEvaluationResult(evaluationResult);

            // 7. 计算平均分(取整)
            int avgScore = (evaluation.getCommunicationScore() +
                           evaluation.getProfessionalismScore() +
                           evaluation.getComplianceScore()) / 3;

            // 8. 将评估结果转换为 JSON 并保存到数据库
            String aiSummaryJson = objectMapper.writeValueAsString(evaluation);

            // 9. 更新练习记录
            PracticeUserRecordDO updateRecord = new PracticeUserRecordDO();
            updateRecord.setId(recordId);
            updateRecord.setTotalScore(new BigDecimal(avgScore));
            updateRecord.setAiSummary(aiSummaryJson);
            practiceUserRecordMapper.updateById(updateRecord);

            log.info("评估完成并保存, recordId: {}, 总分: {}, 沟通: {}, 专业: {}, 合规: {}",
                recordId, avgScore,
                evaluation.getCommunicationScore(),
                evaluation.getProfessionalismScore(),
                evaluation.getComplianceScore());

            return evaluation;

        } catch (Exception e) {
            log.error("评估练习记录失败, recordId: {}", recordId, e);
            throw new RuntimeException("评估失败: " + e.getMessage());
        }
    }

    /**
     * 解析 Dify 评估结果
     */
    private PracticeEvaluationRespVO parseDifyEvaluationResult(JSONObject evaluationResult) {
        PracticeEvaluationRespVO vo = new PracticeEvaluationRespVO();

        // Dify 返回的结构: {"evaluationResult": {...}}
        // 需要先获取 evaluationResult 内层对象
        JSONObject innerResult = evaluationResult.getJSONObject("evaluationResult");
        if (innerResult == null) {
            log.warn("评估结果中缺少 evaluationResult 字段，使用空结果");
            return vo;
        }

        // 提取维度分数 (从 dimensionScores 对象中获取)
        JSONObject dimensionScores = innerResult.getJSONObject("dimensionScores");
        if (dimensionScores != null) {
            vo.setCommunicationScore(dimensionScores.getInt("communication_score", 0));
            vo.setProfessionalismScore(dimensionScores.getInt("product_knowledge_score", 0)); // Dify用的是product_knowledge_score
            vo.setComplianceScore(innerResult.getJSONObject("basicInfo") != null ?
                    innerResult.getJSONObject("basicInfo").getInt("complianceScore", 0) : 0);
        }

        // 提取 AI 总结和反馈
        vo.setManagerFeedback(innerResult.getStr("aiSummary", ""));

        // 提取合规问题
        JSONArray complianceIssuesArr = evaluationResult.getJSONArray("compliance_issues");
        if (complianceIssuesArr != null) {
            List<PracticeEvaluationRespVO.ComplianceIssue> complianceIssues = new ArrayList<>();
            for (int i = 0; i < complianceIssuesArr.size(); i++) {
                JSONObject issueJson = complianceIssuesArr.getJSONObject(i);
                PracticeEvaluationRespVO.ComplianceIssue issue = new PracticeEvaluationRespVO.ComplianceIssue();
                issue.setType(issueJson.getStr("type"));
                issue.setDescription(issueJson.getStr("description"));
                issue.setStatus(issueJson.getStr("status"));
                complianceIssues.add(issue);
            }
            vo.setComplianceIssues(complianceIssues);
        }

        // 提取量化指标
        JSONArray metricsArr = evaluationResult.getJSONArray("quantified_metrics");
        if (metricsArr != null) {
            List<PracticeEvaluationRespVO.QuantifiedMetric> metrics = new ArrayList<>();
            for (int i = 0; i < metricsArr.size(); i++) {
                JSONObject metricJson = metricsArr.getJSONObject(i);
                PracticeEvaluationRespVO.QuantifiedMetric metric = new PracticeEvaluationRespVO.QuantifiedMetric();
                metric.setDimension(metricJson.getStr("dimension"));
                metric.setTarget(metricJson.getStr("target"));
                metric.setActual(metricJson.getStr("actual"));
                metric.setDeviation(metricJson.getStr("deviation"));
                metrics.add(metric);
            }
            vo.setQuantifiedMetrics(metrics);
        }

        // 提取提升建议
        JSONArray suggestionsArr = evaluationResult.getJSONArray("improvement_suggestions");
        if (suggestionsArr != null) {
            List<PracticeEvaluationRespVO.ImprovementSuggestion> suggestions = new ArrayList<>();
            for (int i = 0; i < suggestionsArr.size(); i++) {
                JSONObject suggestionJson = suggestionsArr.getJSONObject(i);
                PracticeEvaluationRespVO.ImprovementSuggestion suggestion = new PracticeEvaluationRespVO.ImprovementSuggestion();
                suggestion.setText(suggestionJson.getStr("text"));

                JSONObject resourceJson = suggestionJson.getJSONObject("resource");
                if (resourceJson != null) {
                    PracticeEvaluationRespVO.Resource resource = new PracticeEvaluationRespVO.Resource();
                    resource.setLabel(resourceJson.getStr("label"));
                    resource.setUrl(resourceJson.getStr("url"));
                    suggestion.setResource(resource);
                }

                suggestions.add(suggestion);
            }
            vo.setImprovementSuggestions(suggestions);
        }

        return vo;
    }
}
