package com.ynet.iplatform.module.aicrm.controller.admin.practiceconversation;

import cn.hutool.json.JSONObject;
import com.ynet.iplatform.framework.common.pojo.CommonResult;
import com.ynet.iplatform.module.aicrm.service.practiceconversation.DifyChatClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import jakarta.annotation.Resource;

/**
 * Dify Chat 测试控制器
 */
@Tag(name = "管理后台 - Dify Chat 测试")
@RestController
@RequestMapping("/aicrm/dify-chat-test")
@Slf4j
public class DifyChatTestController {

    @Resource
    private DifyChatClient difyChatClient;

    @PostMapping("/send")
    @Operation(summary = "测试发送对话消息")
    public CommonResult<DifyChatClient.DifyChatResponse> testSendMessage(
            @RequestParam Long roleId,
            @RequestParam Long scriptId,
            @RequestParam String message,
            @RequestParam(required = false) String conversationId) {
        try {
            DifyChatClient.DifyChatResponse response = difyChatClient.sendMessage(
                    roleId, scriptId, message, conversationId);
            return CommonResult.success(response);
        } catch (Exception e) {
            log.error("测试 Dify Chat 异常, roleId: {}, scriptId: {}", roleId, scriptId, e);
            return CommonResult.error(500, "对话失败: " + e.getMessage());
        }
    }

    @PostMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @Operation(summary = "流式对话接口 (SSE)")
    public SseEmitter streamChat(@RequestBody JSONObject requestBody) {
        SseEmitter emitter = new SseEmitter(60000L); // 60秒超时

        new Thread(() -> {
            try {
                // 1. 解析请求参数
                String message = requestBody.getStr("message");
                Long roleId = requestBody.getLong("roleId");
                Long scriptId = requestBody.getLong("scriptId");
                String conversationId = requestBody.getStr("conversationId");

                log.info("流式对话请求: message={}, roleId={}, scriptId={}, conversationId={}",
                        message, roleId, scriptId, conversationId);

                // 2. 调用 Dify Chat API
                DifyChatClient.DifyChatResponse response = difyChatClient.sendMessage(
                        roleId, scriptId, message, conversationId);

                // 3. 模拟流式返回 (分批发送)
                String answer = response.getAnswer();
                int chunkSize = 5; // 每次发送5个字符

                log.info("开始发送流式数据,总长度: {}, 分块大小: {}", answer.length(), chunkSize);

                for (int i = 0; i < answer.length(); i += chunkSize) {
                    int end = Math.min(i + chunkSize, answer.length());
                    String chunk = answer.substring(i, end);

                    // 发送数据片段
                    JSONObject data = new JSONObject();
                    data.set("type", "data");
                    data.set("content", chunk);
                    String dataStr = data.toString();

                    log.debug("发送数据片段: {}", dataStr);
                    emitter.send(SseEmitter.event().data(dataStr));

                    // 模拟延迟
                    Thread.sleep(50);
                }

                log.info("流式数据发送完成,共发送 {} 个片段", (answer.length() + chunkSize - 1) / chunkSize);

                // 4. 发送完成事件
                JSONObject doneEvent = new JSONObject();
                doneEvent.set("type", "done");
                doneEvent.set("conversationId", response.getConversationId());
                emitter.send(SseEmitter.event().data(doneEvent.toString()));

                emitter.complete();
                log.info("流式对话完成");

            } catch (Exception e) {
                log.error("流式对话异常", e);
                try {
                    JSONObject errorEvent = new JSONObject();
                    errorEvent.set("type", "error");
                    errorEvent.set("message", e.getMessage());
                    emitter.send(SseEmitter.event().data(errorEvent.toString()));
                } catch (Exception ex) {
                    log.error("发送错误事件失败", ex);
                }
                emitter.completeWithError(e);
            }
        }).start();

        return emitter;
    }
}
