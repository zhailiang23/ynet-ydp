package com.ynet.iplatform.module.aicrm.controller.admin.practicescript;

import com.ynet.iplatform.framework.common.pojo.CommonResult;
import com.ynet.iplatform.module.aicrm.service.practicescript.DifyScriptGeneratorClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

/**
 * Dify 测试控制器
 */
@Tag(name = "管理后台 - Dify 测试")
@RestController
@RequestMapping("/aicrm/dify-test")
@Slf4j
public class DifyTestController {

    @Resource
    private DifyScriptGeneratorClient difyScriptGeneratorClient;

    @GetMapping("/connection")
    @Operation(summary = "测试 Dify 连接")
    public CommonResult<String> testConnection() {
        // testConnection 方法已移除，因为使用 DifyClientUtil 后配置存储在数据库中
        // 可以通过调用 generateScript 测试连接是否正常
        return CommonResult.success("请使用 /generate 接口测试 Dify 连接");
    }

    @PostMapping("/generate")
    @Operation(summary = "测试生成剧本")
    public CommonResult<String> testGenerate(@RequestParam Long scriptId) {
        try {
            String result = difyScriptGeneratorClient.generateScript(scriptId);
            return CommonResult.success(result);
        } catch (Exception e) {
            log.error("测试生成剧本异常, scriptId: {}", scriptId, e);
            return CommonResult.error(500, "生成失败: " + e.getMessage());
        }
    }
}
