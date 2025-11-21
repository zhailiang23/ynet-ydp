# AI 服务提示词模板化更新说明

## 更新概述

将 AI 聊天服务的提示词从硬编码改为从数据库读取,实现提示词的动态管理和版本控制。

## 更新时间

2025-11-21

## 涉及文件

1. `ai/ai_service.py` - 统一 AI 服务
2. `ai/http_qa_agent.py` - HTTP 问答 Agent 服务

## 主要变更

### 1. 对话服务提示词

**之前**: 提示词硬编码在 `create_practice_prompt()` 函数中

**现在**: 从数据库表 `crm_practice_prompt_template` 读取模板 `USER_GREETING_PROMPT`

**变量替换**:
- `{{virtual_customer_profile}}` - 虚拟客户画像信息(姓名+画像)
- `{{script_content}}` - 课程剧本内容

### 2. 评估服务提示词

**之前**: 评估提示词硬编码在 `/evaluate` 接口中

**现在**: 从数据库表 `crm_practice_prompt_template` 读取模板 `EVALUATION_SYSTEM_PROMPT`

**变量替换**:
- `{{conversation_history}}` - 学员与AI的对话历史
- `{{available_courses}}` - 可推荐的课程列表(TODO: 待实现从数据库查询)

### 3. 兜底机制

如果数据库中未找到对应的提示词模板,系统会自动使用默认的硬编码提示词,确保服务不中断。

**兜底函数**:
- `_get_default_practice_prompt()` - 默认对话提示词
- `_get_default_evaluation_prompt()` - 默认评估提示词

## 数据库表结构

### 表名: `crm_practice_prompt_template`

| 字段 | 类型 | 说明 |
|------|------|------|
| id | bigint | 主键 |
| code | varchar(100) | 模板编码(唯一标识) |
| name | varchar(200) | 模板名称 |
| type | tinyint | 模板类型 |
| content | text | 提示词内容(支持变量) |
| variables | varchar(500) | 变量列表(JSON数组) |
| description | varchar(500) | 模板说明 |
| status | tinyint | 状态(1=启用,0=禁用) |
| version | int | 版本号 |
| tenant_id | bigint | 租户ID(0=全局共享) |
| deleted | bit(1) | 删除标记 |

### 现有模板

1. **USER_GREETING_PROMPT** - AI 陪练提示词
   - 变量: `["virtual_customer_profile", "script_content"]`
   - 用途: 智能陪练对话

2. **EVALUATION_SYSTEM_PROMPT** - 课程评估提示词
   - 变量: `["conversation_history", "available_courses"]`
   - 用途: AI 评估学员表现

3. **SCRIPT_CREATE_SYSTEM_PROMPT** - 剧本生成提示词
   - 变量: `["case_content", "material_content", "skill_content", ...]`
   - 用途: 根据案例、材料、技能生成剧本

4. **SCRIPT_UPDATE_SYSTEM_PROMPT** - 剧本优化提示词
   - 用途: 优化现有剧本内容

## 使用方法

### 查询提示词模板

```python
from ai_service import query_prompt_template

template = query_prompt_template("USER_GREETING_PROMPT")
if template:
    print(f"模板名称: {template['name']}")
    print(f"变量列表: {template['variables']}")
    print(f"内容: {template['content']}")
```

### 生成对话提示词

```python
from ai_service import create_practice_prompt

prompt = create_practice_prompt(
    course_script="场景: 客户咨询理财产品...",
    virtual_customer_name="张女士",
    virtual_customer_profile="45岁,有一定理财经验,风险偏好稳健"
)
```

## 优势

1. **灵活性**: 可以在数据库中直接修改提示词,无需重启服务
2. **版本控制**: 支持提示词的版本管理和历史记录
3. **统一管理**: 所有提示词集中在数据库中管理
4. **A/B测试**: 可以针对不同租户使用不同的提示词模板
5. **兜底保障**: 即使数据库查询失败,也有默认提示词保证服务可用

## 测试验证

已通过以下测试:

1. ✓ `ai_service.py` 模块导入成功
2. ✓ `http_qa_agent.py` 模块导入成功
3. ✓ 成功查询到数据库中的提示词模板
4. ✓ 提示词变量替换功能正常
5. ✓ 兜底机制可正常工作

## 后续优化建议

1. **课程推荐**: 实现 `available_courses` 变量的数据库查询
2. **缓存机制**: 添加提示词模板的缓存,减少数据库查询
3. **提示词版本**: 支持根据版本号加载特定版本的提示词
4. **提示词审核**: 添加提示词的审核流程,防止恶意修改
5. **性能监控**: 监控提示词的使用效果和AI响应质量

## 注意事项

1. 提示词模板使用 `tenant_id = 0` 表示全局共享
2. 变量格式为 `{{variable_name}}`,使用双大括号
3. 确保数据库中的提示词模板 `status = 1` (启用状态)
4. 修改提示词后建议进行充分测试,确保AI响应质量
