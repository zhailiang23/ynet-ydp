package com.ynet.iplatform.module.aicrm.service.practicescript;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.ynet.iplatform.module.aicrm.dal.dataobject.practicescript.PracticeScriptDO;
import com.ynet.iplatform.module.aicrm.dal.mysql.practicescript.PracticeScriptMapper;
import com.ynet.iplatform.module.aicrm.util.DifyClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

/**
 * Dify 剧本生成服务客户端
 * 调用 Dify Workflow API 生成剧本内容
 *
 * 注意: 本类基于 DifyClientUtil 实现,需要在 infra_external_agent 表中配置智能体编码
 */
@Component
@Slf4j
public class DifyScriptGeneratorClient {

    @Resource
    private DifyClientUtil difyClientUtil;

    @Resource
    private PracticeScriptMapper practiceScriptMapper;

    /**
     * 智能体编码 - 对应 infra_external_agent 表的 code 字段
     * 默认使用 "practice-generate" 编码,可通过配置文件修改
     */
    private static final String AGENT_CODE = "practice-generate";

    /**
     * 调用 Dify 服务生成剧本
     *
     * @param scriptId 剧本ID(通过ID查询所有其他信息)
     * @return 生成的剧本内容 JSON 字符串
     * @throws Exception 调用失败时抛出异常
     */
    public String generateScript(Long scriptId) throws Exception {
        log.info("调用 Dify Workflow 生成剧本, 剧本ID: {}", scriptId);

        // 查询剧本完整信息
        PracticeScriptDO script = practiceScriptMapper.selectById(scriptId);
        if (script == null) {
            throw new Exception("剧本不存在: " + scriptId);
        }

        // 验证必填参数
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

        try {
            // 使用 Builder 构建请求参数
            DifyClientUtil.WorkflowsRunBuilder builder = new DifyClientUtil.WorkflowsRunBuilder()
                    .responseMode("blocking")                        // 响应模式: blocking(阻塞式)
                    .user("backend-system")                          // 用户标识
                    .input("case_id", script.getCaseId())            // 输入变量: 案例ID
                    .input("marketing_step", script.getMarketingStep())     // 输入变量: 营销节点
                    .input("difficulty_level", script.getDifficultyLevel()) // 输入变量: 难度等级
                    .input("material_ids", script.getMaterialIds())         // 输入变量: 培训材料ID
                    .input("skill_id", script.getSkillId())                 // 输入变量: 技巧ID
                    .input("description", script.getDescription());         // 输入变量: 剧本描述

            // 可选参数 - script_id 必须是数字,只有当 scriptNo 是纯数字时才传递
            if (StrUtil.isNotBlank(script.getScriptNo())) {
                try {
                    Long scriptIdNum = Long.parseLong(script.getScriptNo());
                    builder.input("script_id", scriptIdNum);
                } catch (NumberFormatException e) {
                    log.warn("scriptNo 不是有效的数字,跳过传递: {}", script.getScriptNo());
                }
            }

            // script_version 保持字符串类型
            if (StrUtil.isNotBlank(script.getVersion())) {
                builder.input("script_version", script.getVersion());
            }
            if (StrUtil.isNotBlank(script.getContentEdit())) {
                builder.input("manual_branch_adjustment", script.getContentEdit());
            }
            if (StrUtil.isNotBlank(script.getContent())) {
                builder.input("content", script.getContent());
            }

            JSONObject requestParams = builder.build();

            // 调用 Dify Workflow API (根据 AGENT_CODE 自动获取 URL 和 API Key)
            JSONObject responseJson = difyClientUtil.workflowsRun(AGENT_CODE, requestParams);

            // 解析响应
            JSONObject data = responseJson.getJSONObject("data");
            if (data == null) {
                log.error("Dify Workflow 未返回 data 字段, 响应: {}", responseJson.toString());
                throw new Exception("Dify Workflow 未返回 data 字段");
            }

            // 检查执行状态
            String status = data.getStr("status");
            if (!"succeeded".equals(status)) {
                String error = data.getStr("error");
                log.error("Dify Workflow 执行失败, 状态: {}, 错误: {}", status, error);
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

}
