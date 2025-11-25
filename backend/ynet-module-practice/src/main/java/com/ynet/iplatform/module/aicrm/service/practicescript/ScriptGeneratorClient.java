package com.ynet.iplatform.module.aicrm.service.practicescript;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 剧本生成服务客户端
 * 调用 AI 服务生成剧本内容
 */
@Component
@Slf4j
public class ScriptGeneratorClient {

    @Value("${script.generator.url:http://localhost:8001}")
    private String generatorUrl;

    /**
     * 调用 AI 服务生成剧本
     *
     * @param scriptId 剧本ID(通过ID查询所有其他信息)
     * @return 生成的剧本内容 JSON 字符串
     * @throws Exception 调用失败时抛出异常
     */
    public String generateScript(Long scriptId) throws Exception {
        // 构建请求参数
        JSONObject requestBody = new JSONObject();
        requestBody.set("script_id", scriptId);

        log.info("调用 AI 服务生成剧本, URL: {}, 剧本ID: {}", generatorUrl + "/generate-script", scriptId);

        // 发送 HTTP 请求
        try (HttpResponse response = HttpRequest.post(generatorUrl + "/generate-script")
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .timeout(600000) // 10分钟超时 (AI生成剧本可能需要较长时间)
                .execute()) {

            int status = response.getStatus();
            String body = response.body();

            log.info("AI 服务响应状态: {}, 响应体长度: {}", status, body != null ? body.length() : 0);

            if (status != 200) {
                throw new Exception("调用 AI 服务失败, HTTP状态码: " + status + ", 响应: " + body);
            }

            // 解析响应
            JSONObject responseJson = JSONUtil.parseObj(body);
            if (!responseJson.getBool("success", false)) {
                throw new Exception("AI 服务返回失败: " + responseJson.getStr("message"));
            }

            // 获取剧本内容
            Object scriptContent = responseJson.get("script_content");
            if (scriptContent == null) {
                throw new Exception("AI 服务未返回剧本内容");
            }

            // 将剧本内容转为 JSON 字符串
            String contentJson = JSONUtil.toJsonStr(scriptContent);
            log.info("剧本生成成功, 内容长度: {}", contentJson.length());

            return contentJson;
        }
    }
}
