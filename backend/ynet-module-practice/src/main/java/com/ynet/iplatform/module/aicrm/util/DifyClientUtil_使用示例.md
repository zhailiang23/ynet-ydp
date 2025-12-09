# DifyClientUtil 使用示例

## 概述

`DifyClientUtil` 是一个统一的 Dify API 调用工具类,支持:
- **Chat Messages API** - 对话应用 (支持多轮对话)
- **Workflows Run API** - 工作流应用

## 核心特性

- ✅ 根据智能体编码自动获取 URL 和 API Key
- ✅ 自动验证智能体状态和配置
- ✅ 统一的错误处理和日志记录
- ✅ 支持 JSON 格式的请求参数
- ✅ 提供参数构建辅助类

## 前置条件

确保 `infra_external_agent` 表中已配置外部智能体:

```sql
INSERT INTO infra_external_agent (code, name, platform_type, access_url, api_key, status)
VALUES ('dify_chat', 'Dify对话应用', 'dify', 'http://192.168.152.47:181/v1', 'app-BpOSZNjIowvZbfZEPj1zkAMs', 0);

INSERT INTO infra_external_agent (code, name, platform_type, access_url, api_key, status)
VALUES ('dify_workflow', 'Dify工作流应用', 'dify', 'http://192.168.152.47:181/v1', 'app-heWY9luKOhh2jKjCZYV2ZUA4', 0);
```

## 使用方法

### 1. 注入工具类

```java
@Service
public class YourService {

    @Resource
    private DifyClientUtil difyClientUtil;

    // ... 你的业务方法
}
```

### 2. 调用 Chat Messages API (对话应用)

#### 方式一: 使用 Builder (推荐)

```java
import com.ynet.iplatform.module.aicrm.util.DifyClientUtil;
import cn.hutool.json.JSONObject;

public void sendChatMessage(Long roleId, Long scriptId, String userMessage, String conversationId) throws Exception {
    // 使用 Builder 构建请求参数
    JSONObject requestParams = new DifyClientUtil.ChatMessagesBuilder()
            .query(userMessage)                      // 用户消息
            .responseMode("blocking")                // 响应模式: blocking(阻塞式) 或 streaming(流式)
            .user("backend-system")                  // 用户标识
            .conversationId(conversationId)          // 会话ID (可选,用于继续对话)
            .input("role_id", roleId)                // 输入变量
            .input("script_id", scriptId)            // 输入变量
            .build();

    // 调用 Dify Chat API
    JSONObject response = difyClientUtil.chatMessages("dify_chat", requestParams);

    // 处理响应
    String answer = response.getStr("answer");
    String newConversationId = response.getStr("conversation_id");
    String messageId = response.getStr("message_id");

    // ... 你的业务逻辑
}
```

#### 方式二: 手动构建 JSON

```java
public void sendChatMessage(String userMessage) throws Exception {
    // 手动构建请求参数
    JSONObject requestParams = new JSONObject();
    requestParams.set("query", userMessage);
    requestParams.set("response_mode", "blocking");
    requestParams.set("user", "backend-system");

    // 可选: 添加输入变量
    JSONObject inputs = new JSONObject();
    inputs.set("role_id", 123L);
    inputs.set("script_id", 456L);
    requestParams.set("inputs", inputs);

    // 调用 Dify Chat API
    JSONObject response = difyClientUtil.chatMessages("dify_chat", requestParams);

    // 处理响应
    String answer = response.getStr("answer");
}
```

### 3. 调用 Workflows Run API (工作流应用)

#### 方式一: 使用 Builder (推荐)

```java
public String generateScript(Long scriptId, String caseId, String marketingStep) throws Exception {
    // 使用 Builder 构建请求参数
    JSONObject requestParams = new DifyClientUtil.WorkflowsRunBuilder()
            .responseMode("blocking")                     // 响应模式: blocking(阻塞式) 或 streaming(流式)
            .user("backend-system")                       // 用户标识
            .input("case_id", caseId)                     // 工作流输入变量
            .input("marketing_step", marketingStep)       // 工作流输入变量
            .input("difficulty_level", "medium")          // 工作流输入变量
            .input("material_ids", "1,2,3")               // 工作流输入变量
            .input("skill_id", 10L)                       // 工作流输入变量
            .input("description", "剧本描述")              // 工作流输入变量
            .build();

    // 调用 Dify Workflow API
    JSONObject response = difyClientUtil.workflowsRun("dify_workflow", requestParams);

    // 处理响应
    // Dify Workflow 响应格式:
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
    //     "elapsed_time": 1.5,
    //     "total_tokens": 1000
    //   }
    // }

    JSONObject data = response.getJSONObject("data");
    String status = data.getStr("status");

    if (!"succeeded".equals(status)) {
        String error = data.getStr("error");
        throw new Exception("Dify Workflow 执行失败: " + error);
    }

    JSONObject outputs = data.getJSONObject("outputs");
    // 获取输出结果 (输出变量名取决于 workflow 配置)
    String scriptContent = outputs.getStr("剧本内容");

    return scriptContent;
}
```

#### 方式二: 手动构建 JSON

```java
public void runWorkflow() throws Exception {
    // 手动构建请求参数
    JSONObject requestParams = new JSONObject();
    requestParams.set("response_mode", "blocking");
    requestParams.set("user", "backend-system");

    // 工作流输入变量
    JSONObject inputs = new JSONObject();
    inputs.set("case_id", "C001");
    inputs.set("marketing_step", "需求挖掘");
    inputs.set("difficulty_level", "medium");
    requestParams.set("inputs", inputs);

    // 调用 Dify Workflow API
    JSONObject response = difyClientUtil.workflowsRun("dify_workflow", requestParams);

    // 处理响应
    JSONObject data = response.getJSONObject("data");
    JSONObject outputs = data.getJSONObject("outputs");
}
```

### 4. 评估服务示例

```java
public void evaluateTraining(String trainingContent, String scriptId, String courseList) throws Exception {
    // 构建请求参数
    JSONObject requestParams = new DifyClientUtil.WorkflowsRunBuilder()
            .responseMode("blocking")
            .user("backend-system")
            .input("training_content", trainingContent)   // 陪练对话内容
            .input("scriptId", scriptId)                  // 剧本ID (可选)
            .input("course", courseList)                  // 所有课程表信息
            .build();

    // 调用评估服务
    JSONObject response = difyClientUtil.workflowsRun("dify_evaluation", requestParams);

    // 解析评估结果
    JSONObject data = response.getJSONObject("data");
    JSONObject outputs = data.getJSONObject("outputs");

    // 获取评估结果 (可能是 text 或 evaluation_result 字段)
    String evaluationResultStr = outputs.getStr("text");
    if (StrUtil.isBlank(evaluationResultStr)) {
        evaluationResultStr = outputs.getStr("evaluation_result");
    }

    // 去除可能存在的 Markdown 代码块标记
    evaluationResultStr = evaluationResultStr.trim();
    if (evaluationResultStr.startsWith("```json")) {
        evaluationResultStr = evaluationResultStr.substring(7);
    } else if (evaluationResultStr.startsWith("```")) {
        evaluationResultStr = evaluationResultStr.substring(3);
    }
    if (evaluationResultStr.endsWith("```")) {
        evaluationResultStr = evaluationResultStr.substring(0, evaluationResultStr.length() - 3);
    }
    evaluationResultStr = evaluationResultStr.trim();

    // 解析评估结果 JSON
    JSONObject evaluationJson = JSONUtil.parseObj(evaluationResultStr);

    // ... 你的业务逻辑
}
```

## 响应格式

### Chat Messages API 响应格式

```json
{
  "event": "message",
  "message_id": "xxx",
  "conversation_id": "xxx",
  "mode": "chat",
  "answer": "AI的回复内容",
  "metadata": {},
  "created_at": 1234567890
}
```

### Workflows Run API 响应格式

```json
{
  "workflow_run_id": "xxx",
  "task_id": "xxx",
  "data": {
    "id": "xxx",
    "workflow_id": "xxx",
    "status": "succeeded",
    "outputs": {
      "输出变量名": "输出内容"
    },
    "error": null,
    "elapsed_time": 1.5,
    "total_tokens": 1000,
    "total_steps": 3,
    "created_at": 1234567890,
    "finished_at": 1234567891
  }
}
```

## 错误处理

工具类会自动处理以下错误:

1. **智能体不存在** - "智能体不存在: {agentCode}"
2. **智能体未启用** - "智能体未启用: {agentCode}"
3. **配置缺失** - "智能体访问URL未配置" / "智能体API密钥未配置"
4. **HTTP 错误** - "调用 Dify API 失败, HTTP状态码: {status}"
5. **API 错误** - "Dify API 调用失败: {errorCode} - {errorMessage}"
6. **网络异常** - "调用 Dify API 异常: {message}"

建议在调用时捕获异常并记录日志:

```java
try {
    JSONObject response = difyClientUtil.chatMessages("dify_chat", requestParams);
    // 处理响应
} catch (Exception e) {
    log.error("调用 Dify API 失败", e);
    // 你的错误处理逻辑
}
```

## 配置管理

所有 Dify 配置都存储在 `infra_external_agent` 表中:

| 字段 | 说明 | 示例 |
|------|------|------|
| code | 智能体编码 (唯一标识) | dify_chat |
| name | 智能体名称 | Dify对话应用 |
| platform_type | 平台类型 | dify |
| access_url | 访问URL | http://192.168.152.47:181/v1 |
| api_key | API密钥 | app-BpOSZNjIowvZbfZEPj1zkAMs |
| status | 状态 (0=开启, 1=关闭) | 0 |

修改配置后无需重启服务,工具类会自动使用最新配置。

## 最佳实践

1. **使用 Builder 模式** - 更清晰、更安全的参数构建
2. **设置合理的超时时间** - 默认 10 分钟,工作流可能需要更长时间
3. **记录详细日志** - 工具类已内置详细日志,INFO 级别记录关键信息,DEBUG 级别记录请求参数
4. **错误处理** - 始终捕获异常并提供友好的错误提示
5. **会话管理** - 对话应用使用 conversation_id 实现多轮对话
6. **配置管理** - 通过数据库统一管理所有 Dify 配置,便于维护和切换

## 迁移指南

### 从 DifyChatClient 迁移

**旧代码:**
```java
@Resource
private DifyChatClient difyChatClient;

DifyChatClient.DifyChatResponse response = difyChatClient.sendMessage(roleId, scriptId, userMessage, conversationId);
String answer = response.getAnswer();
```

**新代码:**
```java
@Resource
private DifyClientUtil difyClientUtil;

JSONObject requestParams = new DifyClientUtil.ChatMessagesBuilder()
        .query(userMessage)
        .responseMode("blocking")
        .user("backend-system")
        .conversationId(conversationId)
        .input("role_id", roleId)
        .input("script_id", scriptId)
        .build();

JSONObject response = difyClientUtil.chatMessages("dify_chat", requestParams);
String answer = response.getStr("answer");
```

### 从 DifyEvaluationClient 迁移

**旧代码:**
```java
@Resource
private DifyEvaluationClient difyEvaluationClient;

DifyEvaluationClient.DifyEvaluationResponse response = difyEvaluationClient.evaluateTraining(trainingContent, scriptId, courseList);
JSONObject evaluationResult = response.getEvaluationResult();
```

**新代码:**
```java
@Resource
private DifyClientUtil difyClientUtil;

JSONObject requestParams = new DifyClientUtil.WorkflowsRunBuilder()
        .responseMode("blocking")
        .user("backend-system")
        .input("training_content", trainingContent)
        .input("scriptId", scriptId)
        .input("course", courseList)
        .build();

JSONObject response = difyClientUtil.workflowsRun("dify_evaluation", requestParams);
// 解析响应 (见上文评估服务示例)
```

### 从 DifyScriptGeneratorClient 迁移

**旧代码:**
```java
@Resource
private DifyScriptGeneratorClient difyScriptGeneratorClient;

String scriptContent = difyScriptGeneratorClient.generateScript(scriptId);
```

**新代码:**
```java
@Resource
private DifyClientUtil difyClientUtil;

// 先查询剧本信息
PracticeScriptDO script = practiceScriptMapper.selectById(scriptId);

// 构建请求参数
JSONObject requestParams = new DifyClientUtil.WorkflowsRunBuilder()
        .responseMode("blocking")
        .user("backend-system")
        .input("case_id", script.getCaseId())
        .input("marketing_step", script.getMarketingStep())
        .input("difficulty_level", script.getDifficultyLevel())
        .input("material_ids", script.getMaterialIds())
        .input("skill_id", script.getSkillId())
        .input("description", script.getDescription())
        .build();

JSONObject response = difyClientUtil.workflowsRun("dify_script", requestParams);
JSONObject outputs = response.getJSONObject("data").getJSONObject("outputs");
String scriptContent = outputs.getStr("剧本内容"); // 根据实际输出变量名调整
```

## 总结

`DifyClientUtil` 提供了一个简单、统一的方式来调用 Dify API:
- ✅ 减少重复代码
- ✅ 统一配置管理
- ✅ 更好的错误处理
- ✅ 更清晰的使用方式
