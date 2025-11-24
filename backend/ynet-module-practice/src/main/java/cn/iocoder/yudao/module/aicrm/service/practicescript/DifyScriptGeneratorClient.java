package cn.iocoder.yudao.module.aicrm.service.practicescript;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.iocoder.yudao.module.aicrm.config.DifyProperties;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.practicescript.PracticeScriptDO;
import cn.iocoder.yudao.module.aicrm.dal.mysql.practicescript.PracticeScriptMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

/**
 * Dify 剧本生成服务客户端
 * 调用 Dify API 生成剧本内容
 */
@Component
@Slf4j
public class DifyScriptGeneratorClient {

    @Resource
    private DifyProperties difyProperties;

    @Resource
    private PracticeScriptMapper practiceScriptMapper;

    /**
     * 调用 Dify 服务生成剧本
     *
     * @param scriptId 剧本ID(通过ID查询所有其他信息)
     * @return 生成的剧本内容 JSON 字符串
     * @throws Exception 调用失败时抛出异常
     */
    public String generateScript(Long scriptId) throws Exception {
        // 检查是否启用 Dify
        if (!difyProperties.getEnabled()) {
            throw new Exception("Dify 服务未启用");
        }

        String apiUrl = difyProperties.getApiUrl();
        String apiKey = difyProperties.getApiKey();

        if (apiUrl == null || apiUrl.isEmpty()) {
            throw new Exception("Dify API URL 未配置");
        }
        if (apiKey == null || apiKey.isEmpty()) {
            throw new Exception("Dify API Key 未配置");
        }

        // 查询剧本完整信息
        PracticeScriptDO script = practiceScriptMapper.selectById(scriptId);
        if (script == null) {
            throw new Exception("剧本不存在: " + scriptId);
        }

        // 构建请求 URL - workflow 应用使用 /workflows/run 接口
        String url = apiUrl + "/workflows/run";

        // 构建请求参数 - 根据 Dify /parameters 接口返回的参数定义
        JSONObject requestBody = new JSONObject();

        // inputs: 传递给工作流的输入变量
        JSONObject inputs = new JSONObject();

        // 必填参数
        if (script.getCaseId() == null) {
            throw new Exception("案例ID (case_id) 不能为空");
        }
        if (StrUtil.isBlank(script.getMarketingStep())) {
            throw new Exception("营销节点 (marketing_step) 不能为空");
        }
        if (StrUtil.isBlank(script.getDifficultyLevel())) {
            throw new Exception("难度等级 (difficulty_level) 不能为空");
        }
        if (StrUtil.isBlank(script.getMaterialIds())) {
            throw new Exception("培训材料ID (material_ids) 不能为空");
        }
        if (script.getSkillId() == null) {
            throw new Exception("技巧ID (skill_id) 不能为空");
        }
        if (StrUtil.isBlank(script.getDescription())) {
            throw new Exception("剧本描述 (description) 不能为空");
        }

        inputs.set("case_id", script.getCaseId());
        inputs.set("marketing_step", script.getMarketingStep());
        inputs.set("difficulty_level", script.getDifficultyLevel());
        inputs.set("material_ids", script.getMaterialIds());
        inputs.set("skill_id", script.getSkillId());
        inputs.set("description", script.getDescription());

        // 可选参数 - script_id 必须是数字,只有当 scriptNo 是纯数字时才传递
        if (StrUtil.isNotBlank(script.getScriptNo())) {
            try {
                Long scriptIdNum = Long.parseLong(script.getScriptNo());
                inputs.set("script_id", scriptIdNum);
            } catch (NumberFormatException e) {
                log.warn("scriptNo 不是有效的数字,跳过传递: {}", script.getScriptNo());
            }
        }
        // script_version 保持字符串类型
        if (StrUtil.isNotBlank(script.getVersion())) {
            inputs.set("script_version", script.getVersion());
        }
        if (StrUtil.isNotBlank(script.getContentEdit())) {
            inputs.set("manual_branch_adjustment", script.getContentEdit());
        }
        if (StrUtil.isNotBlank(script.getContent())) {
            inputs.set("content", script.getContent());
        }

        requestBody.set("inputs", inputs);

        // response_mode: blocking(阻塞式,等待完整响应) 或 streaming(流式响应)
        requestBody.set("response_mode", "blocking");

        // user: 用户标识
        requestBody.set("user", difyProperties.getUser());

        log.info("调用 Dify Workflow 生成剧本, URL: {}, 剧本ID: {}", url, scriptId);
        log.info("Dify 请求参数: {}", requestBody.toString());

        // 发送 HTTP 请求
        try (HttpResponse response = HttpRequest.post(url)
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .timeout(difyProperties.getTimeout())
                .execute()) {

            int status = response.getStatus();
            String body = response.body();

            log.info("Dify Workflow 响应状态: {}, 响应体长度: {}", status, body != null ? body.length() : 0);

            if (status != 200) {
                log.error("Dify Workflow 调用失败, 响应: {}", body);
                throw new Exception("调用 Dify Workflow 失败, HTTP状态码: " + status + ", 响应: " + body);
            }

            // 解析响应
            JSONObject responseJson = JSONUtil.parseObj(body);

            // Dify Workflow API 响应格式:
            // {
            //   "workflow_run_id": "xxx",
            //   "task_id": "xxx",
            //   "data": {
            //     "id": "xxx",
            //     "workflow_id": "xxx",
            //     "status": "succeeded",
            //     "outputs": {
            //       "剧本内容": "生成的剧本JSON字符串"
            //     },
            //     "error": null,
            //     "elapsed_time": 1.5,
            //     "total_tokens": 1000,
            //     "total_steps": 3,
            //     "created_at": 1234567890,
            //     "finished_at": 1234567891
            //   }
            // }

            // 获取 data.outputs 中的剧本内容
            JSONObject data = responseJson.getJSONObject("data");
            if (data == null) {
                log.error("Dify Workflow 未返回 data 字段, 响应: {}", responseJson.toString());
                throw new Exception("Dify Workflow 未返回 data 字段");
            }

            // 检查执行状态
            String status_str = data.getStr("status");
            if (!"succeeded".equals(status_str)) {
                String error = data.getStr("error");
                log.error("Dify Workflow 执行失败, 状态: {}, 错误: {}", status_str, error);
                throw new Exception("Dify Workflow 执行失败: " + error);
            }

            JSONObject outputs = data.getJSONObject("outputs");
            if (outputs == null || outputs.isEmpty()) {
                log.error("Dify Workflow 未返回 outputs, 响应: {}", responseJson.toString());
                throw new Exception("Dify Workflow 未返回 outputs");
            }

            // 从 outputs 中获取剧本内容
            // 根据 workflow 的输出变量名获取，这里假设输出变量名为第一个 key
            String scriptContent = null;
            for (String key : outputs.keySet()) {
                Object value = outputs.get(key);
                if (value != null) {
                    scriptContent = value.toString();
                    break;
                }
            }

            if (scriptContent == null || scriptContent.isEmpty()) {
                log.error("Dify Workflow outputs 为空, 响应: {}", responseJson.toString());
                throw new Exception("Dify Workflow 未返回剧本内容");
            }

            log.info("剧本生成成功, 内容长度: {}, 耗时: {}秒, tokens: {}",
                    scriptContent.length(),
                    data.getDouble("elapsed_time"),
                    data.getInt("total_tokens"));

            return scriptContent;

        } catch (Exception e) {
            log.error("调用 Dify Workflow 异常", e);
            throw new Exception("调用 Dify Workflow 异常: " + e.getMessage(), e);
        }
    }

    /**
     * 测试 Dify 连接
     *
     * @return 是否连接成功
     */
    public boolean testConnection() {
        try {
            String url = difyProperties.getApiUrl() + "/parameters";
            log.info("测试 Dify 连接, URL: {}", url);

            try (HttpResponse response = HttpRequest.get(url)
                    .header("Authorization", "Bearer " + difyProperties.getApiKey())
                    .timeout(10000) // 10秒超时
                    .execute()) {

                int status = response.getStatus();
                log.info("Dify 连接测试响应状态: {}", status);
                return status == 200;
            }
        } catch (Exception e) {
            log.error("Dify 连接测试失败", e);
            return false;
        }
    }
}
