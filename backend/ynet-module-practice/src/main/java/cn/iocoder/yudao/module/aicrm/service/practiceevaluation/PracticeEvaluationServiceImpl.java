package cn.iocoder.yudao.module.aicrm.service.practiceevaluation;

import cn.iocoder.yudao.module.aicrm.controller.admin.practiceuserrecord.vo.PracticeEvaluationRespVO;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.practiceconversation.PracticeConversationDO;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.practicecourse.PracticeCourseDO;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.practiceuserrecord.PracticeUserRecordDO;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.practicevirtualcustomer.PracticeVirtualCustomerDO;
import cn.iocoder.yudao.module.aicrm.dal.mysql.practiceuserrecord.PracticeUserRecordMapper;
import cn.iocoder.yudao.module.aicrm.service.practiceconversation.PracticeConversationService;
import cn.iocoder.yudao.module.aicrm.service.practicecourse.PracticeCourseService;
import cn.iocoder.yudao.module.aicrm.service.practiceevaluation.dto.AiEvaluationRequest;
import cn.iocoder.yudao.module.aicrm.service.practiceevaluation.dto.AiEvaluationResponse;
import cn.iocoder.yudao.module.aicrm.service.practicevirtualcustomer.PracticeVirtualCustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
    private PracticeConversationService practiceConversationService;

    @Resource
    private PracticeCourseService practiceCourseService;

    @Resource
    private PracticeVirtualCustomerService practiceVirtualCustomerService;

    @Value("${ai.evaluation.service.url:http://localhost:8000/evaluate}")
    private String aiEvaluationServiceUrl;

    @Value("${ai.evaluation.service.timeout:300000}")
    private int aiEvaluationServiceTimeout; // 默认 5 分钟超时

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final Random random = new Random();

    public PracticeEvaluationServiceImpl() {
        // 配置 ObjectMapper 使用下划线命名策略 (snake_case)
        this.objectMapper = new ObjectMapper();
        this.objectMapper.setPropertyNamingStrategy(com.fasterxml.jackson.databind.PropertyNamingStrategies.SNAKE_CASE);

        // 配置 RestTemplate 超时时间
        this.restTemplate = new RestTemplate();
        this.restTemplate.getInterceptors().add((request, body, execution) -> {
            log.info("发送 HTTP 请求到: {}, 方法: {}", request.getURI(), request.getMethod());
            long startTime = System.currentTimeMillis();
            try {
                var response = execution.execute(request, body);
                long duration = System.currentTimeMillis() - startTime;
                log.info("HTTP 请求完成, 耗时: {} ms, 状态码: {}", duration, response.getStatusCode());
                return response;
            } catch (Exception e) {
                long duration = System.currentTimeMillis() - startTime;
                log.error("HTTP 请求失败, 耗时: {} ms, 错误: {}", duration, e.getMessage());
                throw e;
            }
        });
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

            // 调用 AI 评估服务
            log.info("调用 AI 评估服务, recordId: {}", recordId);
            AiEvaluationRequest request = new AiEvaluationRequest();
            request.setRecordId(recordId);

            AiEvaluationResponse aiResponse = callAiEvaluationService(request);
            PracticeEvaluationRespVO evaluation = convertToRespVO(aiResponse);

            // 计算平均分(取整)
            int avgScore = (evaluation.getCommunicationScore() +
                           evaluation.getProfessionalismScore() +
                           evaluation.getComplianceScore()) / 3;

            // 将评估结果转换为 JSON 并保存到数据库
            String aiSummaryJson = objectMapper.writeValueAsString(evaluation);

            // 更新练习记录
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
     * 调用AI评估服务
     */
    private AiEvaluationResponse callAiEvaluationService(AiEvaluationRequest request) {
        try {
            log.info("准备调用 AI 评估服务, URL: {}, 超时时间: {} ms", aiEvaluationServiceUrl, aiEvaluationServiceTimeout);
            log.info("请求参数: recordId={}", request.getRecordId());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<AiEvaluationRequest> entity = new HttpEntity<>(request, headers);

            log.info("开始发送 POST 请求到 AI 服务...");
            AiEvaluationResponse response = restTemplate.postForObject(aiEvaluationServiceUrl, entity, AiEvaluationResponse.class);

            if (response == null) {
                log.error("AI 评估服务返回 null");
                throw new RuntimeException("AI 评估服务返回空响应");
            }

            log.info("AI 评估服务调用成功, 沟通分: {}, 专业分: {}, 合规分: {}",
                response.getCommunicationScore(),
                response.getProfessionalismScore(),
                response.getComplianceScore());

            return response;
        } catch (Exception e) {
            log.error("调用AI评估服务失败, URL: {}, 错误类型: {}, 错误信息: {}",
                aiEvaluationServiceUrl, e.getClass().getSimpleName(), e.getMessage(), e);
            throw new RuntimeException("AI评估服务调用失败: " + e.getMessage());
        }
    }

    /**
     * 将AI响应转换为前端VO
     */
    private PracticeEvaluationRespVO convertToRespVO(AiEvaluationResponse aiResponse) {
        PracticeEvaluationRespVO vo = new PracticeEvaluationRespVO();

        vo.setCommunicationScore(aiResponse.getCommunicationScore());
        vo.setProfessionalismScore(aiResponse.getProfessionalismScore());
        vo.setComplianceScore(aiResponse.getComplianceScore());
        vo.setManagerFeedback(aiResponse.getManagerFeedback());

        // 转换合规问题列表
        if (aiResponse.getComplianceIssues() != null) {
            List<PracticeEvaluationRespVO.ComplianceIssue> complianceIssues = aiResponse.getComplianceIssues().stream()
                .map(issue -> {
                    PracticeEvaluationRespVO.ComplianceIssue voIssue = new PracticeEvaluationRespVO.ComplianceIssue();
                    voIssue.setType(issue.getType());
                    voIssue.setDescription(issue.getDescription());
                    voIssue.setStatus(issue.getStatus());
                    return voIssue;
                })
                .collect(Collectors.toList());
            vo.setComplianceIssues(complianceIssues);
        }

        // 转换量化指标列表
        if (aiResponse.getQuantifiedMetrics() != null) {
            List<PracticeEvaluationRespVO.QuantifiedMetric> metrics = aiResponse.getQuantifiedMetrics().stream()
                .map(metric -> {
                    PracticeEvaluationRespVO.QuantifiedMetric voMetric = new PracticeEvaluationRespVO.QuantifiedMetric();
                    voMetric.setDimension(metric.getDimension());
                    voMetric.setTarget(metric.getTarget());
                    voMetric.setActual(metric.getActual());
                    voMetric.setDeviation(metric.getDeviation());
                    return voMetric;
                })
                .collect(Collectors.toList());
            vo.setQuantifiedMetrics(metrics);
        }

        // 转换提升建议列表
        if (aiResponse.getImprovementSuggestions() != null) {
            List<PracticeEvaluationRespVO.ImprovementSuggestion> suggestions = aiResponse.getImprovementSuggestions().stream()
                .map(suggestion -> {
                    PracticeEvaluationRespVO.ImprovementSuggestion voSuggestion = new PracticeEvaluationRespVO.ImprovementSuggestion();
                    voSuggestion.setText(suggestion.getText());

                    if (suggestion.getResource() != null) {
                        PracticeEvaluationRespVO.Resource voResource = new PracticeEvaluationRespVO.Resource();
                        voResource.setLabel(suggestion.getResource().getLabel());
                        voResource.setUrl(suggestion.getResource().getUrl());
                        voSuggestion.setResource(voResource);
                    }

                    return voSuggestion;
                })
                .collect(Collectors.toList());
            vo.setImprovementSuggestions(suggestions);
        }

        return vo;
    }

    /**
     * 生成随机评估数据 (用于演示)
     */
    private PracticeEvaluationRespVO generateRandomEvaluation() {
        PracticeEvaluationRespVO evaluation = new PracticeEvaluationRespVO();

        // 随机生成三个维度得分 (60-95之间)
        evaluation.setCommunicationScore(60 + random.nextInt(36));
        evaluation.setProfessionalismScore(60 + random.nextInt(36));
        evaluation.setComplianceScore(60 + random.nextInt(36));

        // 根据得分生成评估反馈
        int avgScore = (evaluation.getCommunicationScore() +
                       evaluation.getProfessionalismScore() +
                       evaluation.getComplianceScore()) / 3;

        if (avgScore >= 85) {
            evaluation.setManagerFeedback("本次培训表现优秀！沟通逻辑清晰,专业能力扎实,合规意识强。继续保持!");
        } else if (avgScore >= 75) {
            evaluation.setManagerFeedback("本次培训表现良好,各项指标均达标。建议继续加强薄弱环节的练习。");
        } else {
            evaluation.setManagerFeedback("本次个性化培训表现中规中矩,建议针对薄弱环节加强练习。");
        }

        // 合规校验结果
        List<PracticeEvaluationRespVO.ComplianceIssue> complianceIssues = new ArrayList<>();
        if (evaluation.getComplianceScore() >= 70) {
            PracticeEvaluationRespVO.ComplianceIssue issue = new PracticeEvaluationRespVO.ComplianceIssue();
            issue.setType("合规通过");
            issue.setDescription("本次演练合规表现良好。");
            issue.setStatus("通过");
            complianceIssues.add(issue);
        } else {
            PracticeEvaluationRespVO.ComplianceIssue issue1 = new PracticeEvaluationRespVO.ComplianceIssue();
            issue1.setType("风险提示不足");
            issue1.setDescription("在产品介绍时未充分提示风险。");
            issue1.setStatus("需校准");
            complianceIssues.add(issue1);

            PracticeEvaluationRespVO.ComplianceIssue issue2 = new PracticeEvaluationRespVO.ComplianceIssue();
            issue2.setType("合规话术");
            issue2.setDescription("部分话术表述不够合规,需要调整。");
            issue2.setStatus("需校准");
            complianceIssues.add(issue2);
        }
        evaluation.setComplianceIssues(complianceIssues);

        // 量化指标
        List<PracticeEvaluationRespVO.QuantifiedMetric> metrics = new ArrayList<>();

        PracticeEvaluationRespVO.QuantifiedMetric metric1 = new PracticeEvaluationRespVO.QuantifiedMetric();
        metric1.setDimension("自定义指标1");
        metric1.setTarget("≥70%");
        int actual1 = 60 + random.nextInt(31);
        metric1.setActual(actual1 + "%");
        metric1.setDeviation(actual1 >= 70 ? "达标" : "未达标");
        metrics.add(metric1);

        PracticeEvaluationRespVO.QuantifiedMetric metric2 = new PracticeEvaluationRespVO.QuantifiedMetric();
        metric2.setDimension("自定义指标2");
        metric2.setTarget("≥75%");
        int actual2 = 60 + random.nextInt(31);
        metric2.setActual(actual2 + "%");
        metric2.setDeviation(actual2 >= 75 ? "达标" : "有待提升");
        metrics.add(metric2);

        evaluation.setQuantifiedMetrics(metrics);

        // 提升建议
        List<PracticeEvaluationRespVO.ImprovementSuggestion> suggestions = new ArrayList<>();

        PracticeEvaluationRespVO.ImprovementSuggestion suggestion1 = new PracticeEvaluationRespVO.ImprovementSuggestion();
        suggestion1.setText("根据个性化需求加强练习。");

        PracticeEvaluationRespVO.Resource resource1 = new PracticeEvaluationRespVO.Resource();
        resource1.setLabel("个性化学习路径");
        resource1.setUrl("#");
        suggestion1.setResource(resource1);
        suggestions.add(suggestion1);

        // 根据薄弱环节添加建议
        if (evaluation.getCommunicationScore() < 75) {
            PracticeEvaluationRespVO.ImprovementSuggestion suggestion2 = new PracticeEvaluationRespVO.ImprovementSuggestion();
            suggestion2.setText("加强沟通技巧训练,提升逻辑表达能力。");

            PracticeEvaluationRespVO.Resource resource2 = new PracticeEvaluationRespVO.Resource();
            resource2.setLabel("沟通技巧课程");
            resource2.setUrl("#");
            suggestion2.setResource(resource2);
            suggestions.add(suggestion2);
        }

        if (evaluation.getProfessionalismScore() < 75) {
            PracticeEvaluationRespVO.ImprovementSuggestion suggestion3 = new PracticeEvaluationRespVO.ImprovementSuggestion();
            suggestion3.setText("深化产品知识学习,提升专业素养。");

            PracticeEvaluationRespVO.Resource resource3 = new PracticeEvaluationRespVO.Resource();
            resource3.setLabel("产品知识库");
            resource3.setUrl("#");
            suggestion3.setResource(resource3);
            suggestions.add(suggestion3);
        }

        if (evaluation.getComplianceScore() < 75) {
            PracticeEvaluationRespVO.ImprovementSuggestion suggestion4 = new PracticeEvaluationRespVO.ImprovementSuggestion();
            suggestion4.setText("强化合规意识,熟悉监管要求和话术规范。");

            PracticeEvaluationRespVO.Resource resource4 = new PracticeEvaluationRespVO.Resource();
            resource4.setLabel("合规培训课程");
            resource4.setUrl("#");
            suggestion4.setResource(resource4);
            suggestions.add(suggestion4);
        }

        evaluation.setImprovementSuggestions(suggestions);

        return evaluation;
    }
}
