package com.ynet.iplatform.module.aicrm.service.practiceevaluation;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ynet.iplatform.module.aicrm.config.DifyProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

/**
 * Dify 评估客户端
 * 调用 Dify Workflow API 进行课程评估
 */
@Component
@Slf4j
public class DifyEvaluationClient {

    @Resource
    private DifyProperties difyProperties;

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
        if (!difyProperties.getEnabled()) {
            throw new Exception("Dify 服务未启用");
        }

        String apiUrl = difyProperties.getApiUrl();
        String evaluationApiKey = difyProperties.getEvaluationApiKey();

        if (StrUtil.isBlank(apiUrl)) {
            throw new Exception("Dify API URL 未配置");
        }
        if (StrUtil.isBlank(evaluationApiKey)) {
            throw new Exception("Dify 评估应用 API Key 未配置");
        }

        // 构建请求 URL - workflow 应用使用 /workflows/run 接口
        String url = apiUrl + "/workflows/run";

        // 构建请求参数
        JSONObject requestBody = new JSONObject();

        // inputs: 传递给 workflow 的输入变量
        JSONObject inputs = new JSONObject();
        inputs.set("training_content", trainingContent);
        if (StrUtil.isNotBlank(scriptId)) {
            inputs.set("scriptId", scriptId);
        }
        inputs.set("course", courseList);
        requestBody.set("inputs", inputs);

        // response_mode: blocking(阻塞式)
        requestBody.set("response_mode", "blocking");

        // user: 用户标识
        requestBody.set("user", difyProperties.getUser());

        log.info("调用 Dify 评估 API, scriptId: {}, 对话内容长度: {}, 课程列表长度: {}",
                scriptId, trainingContent != null ? trainingContent.length() : 0, courseList != null ? courseList.length() : 0);
        log.debug("Dify 评估请求参数: {}", requestBody.toString());

        // 发送 HTTP 请求
        try (HttpResponse response = HttpRequest.post(url)
                .header("Authorization", "Bearer " + evaluationApiKey)
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .timeout(difyProperties.getTimeout())
                .execute()) {

            int status = response.getStatus();
            String body = response.body();

            log.info("Dify 评估响应状态: {}, 响应体长度: {}", status, body != null ? body.length() : 0);

            if (status != 200) {
                log.error("Dify 评估调用失败, 响应: {}", body);
                throw new Exception("调用 Dify 评估失败, HTTP状态码: " + status + ", 响应: " + body);
            }

            // 解析响应
            JSONObject responseJson = JSONUtil.parseObj(body);
            log.info("Dify 评估响应 JSON: {}", responseJson.toString());

            // 检查是否有错误
            if (responseJson.containsKey("code")) {
                String errorCode = responseJson.getStr("code");
                String errorMessage = responseJson.getStr("message");
                throw new Exception("Dify 评估调用失败: " + errorCode + " - " + errorMessage);
            }

            // Dify Workflow API 响应格式:
            // {
            //   "workflow_run_id": "xxx",
            //   "task_id": "xxx",
            //   "data": {
            //     "outputs": {
            //       "evaluation_result": "JSON字符串"
            //     }
            //   }
            // }

            JSONObject data = responseJson.getJSONObject("data");
            if (data == null) {
                throw new Exception("Dify 评估响应中缺少 data 字段");
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
