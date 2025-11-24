package cn.iocoder.yudao.module.aicrm.controller.admin.practicescript;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.aicrm.service.practicescript.DifyScriptGeneratorClient;
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
        try {
            boolean connected = difyScriptGeneratorClient.testConnection();
            if (connected) {
                return CommonResult.success("Dify 连接成功");
            } else {
                return CommonResult.error(500, "Dify 连接失败");
            }
        } catch (Exception e) {
            log.error("测试 Dify 连接异常", e);
            return CommonResult.error(500, "测试异常: " + e.getMessage());
        }
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
