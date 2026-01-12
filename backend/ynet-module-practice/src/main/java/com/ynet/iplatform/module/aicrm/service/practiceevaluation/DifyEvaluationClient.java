package com.ynet.iplatform.module.aicrm.service.practiceevaluation;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ynet.iplatform.module.aicrm.util.DifyClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

/**
 * Dify 评估客户端
 * 调用 Dify Workflow API 进行课程评估
 *
 * 注意: 本类基于 DifyClientUtil 实现,需要在 infra_external_agent 表中配置智能体编码
 */
@Component
@Slf4j
public class DifyEvaluationClient {

    @Resource
    private DifyClientUtil difyClientUtil;

    /**
     * 智能体编码 - 对应 infra_external_agent 表的 code 字段
     * 默认使用 "practice-evaluate" 编码,可通过配置文件修改
     */
    private static final String AGENT_CODE = "practice-evaluate";

    /**
     * 调用 Dify 评估服务
     *
     * @param trainingContent 陪练对话内容
     * @param scriptId 剧本ID (可选)
     * @param courseList 所有课程表信息
     * @return Dify 评估响应
     * @throws Exception 调用失败时抛出异常
     */
    public DifyEvaluationResponse evaluateTraining(String trainingContent, String scriptId, String courseList) throws Exception {
        log.info("调用 Dify 评估 API, scriptId: {}, 对话内容长度: {}, 课程列表长度: {}",
                scriptId, trainingContent != null ? trainingContent.length() : 0, courseList != null ? courseList.length() : 0);

        try {
            // 使用 Builder 构建请求参数
            DifyClientUtil.WorkflowsRunBuilder builder = new DifyClientUtil.WorkflowsRunBuilder()
                    .responseMode("blocking")                    // 响应模式: blocking(阻塞式)
                    .user("backend-system")                      // 用户标识
                    .input("training_content", trainingContent)  // 输入变量: 陪练对话内容
                    .input("course", courseList);                // 输入变量: 所有课程表信息

            // scriptId 是可选参数
            if (StrUtil.isNotBlank(scriptId)) {
                builder.input("scriptId", scriptId);
            }

            JSONObject requestParams = builder.build();

            // 调用 Dify Workflow API (根据 AGENT_CODE 自动获取 URL 和 API Key)
            JSONObject responseJson = difyClientUtil.workflowsRun(AGENT_CODE, requestParams);

            // 解析响应
            JSONObject data = responseJson.getJSONObject("data");
            if (data == null) {
                throw new Exception("Dify 评估响应中缺少 data 字段");
            }

            // 检查执行状态
            String status = data.getStr("status");
            if (!"succeeded".equals(status)) {
                String error = data.getStr("error");
                log.error("Dify Workflow 执行失败, 状态: {}, 错误: {}", status, error);
                throw new Exception("Dify Workflow 执行失败: " + error);
            }

            JSONObject outputs = data.getJSONObject("outputs");
            if (outputs == null) {
                throw new Exception("Dify 评估响应中缺少 outputs 字段");
            }

            // 获取评估结果 (Dify workflow 返回的是 text 字段)
            // 实际响应格式: {"data": {"outputs": {"text": "JSON字符串"}}}
            String evaluationResultStr = outputs.getStr("text");
            if (StrUtil.isBlank(evaluationResultStr)) {
                // 兼容旧格式: evaluation_result
                evaluationResultStr = outputs.getStr("evaluation_result");
                if (StrUtil.isBlank(evaluationResultStr)) {
                    // 尝试直接获取对象
                    JSONObject evaluationResultObj = outputs.getJSONObject("evaluation_result");
                    if (evaluationResultObj != null) {
                        evaluationResultStr = evaluationResultObj.toString();
                    } else {
                        throw new Exception("Dify 评估响应中缺少 text 或 evaluation_result 字段");
                    }
                }
            }

            log.info("Dify 评估成功, 结果长度: {}", evaluationResultStr.length());

            // 去除可能存在的 Markdown 代码块标记
            // Dify 有时会返回 ```json ... ``` 格式
            evaluationResultStr = evaluationResultStr.trim();
            if (evaluationResultStr.startsWith("```json")) {
                evaluationResultStr = evaluationResultStr.substring(7); // 移除 ```json
            } else if (evaluationResultStr.startsWith("```")) {
                evaluationResultStr = evaluationResultStr.substring(3); // 移除 ```
            }
            if (evaluationResultStr.endsWith("```")) {
                evaluationResultStr = evaluationResultStr.substring(0, evaluationResultStr.length() - 3); // 移除结尾的 ```
            }
            evaluationResultStr = evaluationResultStr.trim();

            log.debug("清理后的评估结果字符串: {}", evaluationResultStr.substring(0, Math.min(200, evaluationResultStr.length())));

            // 解析评估结果
            JSONObject evaluationJson = JSONUtil.parseObj(evaluationResultStr);

            // 封装响应
            DifyEvaluationResponse evalResponse = new DifyEvaluationResponse();
            evalResponse.setWorkflowRunId(responseJson.getStr("workflow_run_id"));
            evalResponse.setTaskId(responseJson.getStr("task_id"));
            evalResponse.setEvaluationResult(evaluationJson);

            return evalResponse;

        } catch (Exception e) {
            log.error("调用 Dify 评估异常", e);
            throw new Exception("调用 Dify 评估异常: " + e.getMessage(), e);
        }
    }

    /**
     * Dify 评估响应封装
     */
    public static class DifyEvaluationResponse {
        private String workflowRunId;
        private String taskId;
        private JSONObject evaluationResult;

        public String getWorkflowRunId() {
            return workflowRunId;
        }

        public void setWorkflowRunId(String workflowRunId) {
            this.workflowRunId = workflowRunId;
        }

        public String getTaskId() {
            return taskId;
        }

        public void setTaskId(String taskId) {
            this.taskId = taskId;
        }

        public JSONObject getEvaluationResult() {
            return evaluationResult;
        }

        public void setEvaluationResult(JSONObject evaluationResult) {
            this.evaluationResult = evaluationResult;
        }
    }
}
