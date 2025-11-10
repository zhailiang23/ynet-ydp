# 智能陪练模块 - 数据字典

## 文档说明

本文档详细描述了智能陪练模块的所有数据库表及其字段定义。

## 表清单

| 序号 | 表名 | 说明 | 所属模块 |
|------|------|------|----------|
| 1 | aicrm_practice_virtual_customer_template | 虚拟客户模板 | 虚拟客户模块 |
| 2 | aicrm_practice_scene | 对练场景 | 场景与剧本模块 |
| 3 | aicrm_practice_sales_script | 销售剧本 | 场景与剧本模块 |
| 4 | aicrm_practice_script_version | 剧本版本 | 场景与剧本模块 |
| 5 | aicrm_practice_script_branch | 剧本分支 | 场景与剧本模块 |
| 6 | aicrm_practice_case_library | 案例库 | 场景与剧本模块 |
| 7 | aicrm_practice_case_tag | 案例标签 | 场景与剧本模块 |
| 8 | aicrm_practice_case_tag_relation | 案例标签关联 | 场景与剧本模块 |
| 9 | aicrm_practice_sales_routine | 销售套路 | 销售套路与技巧模块 |
| 10 | aicrm_practice_sales_skill | 销售技巧 | 销售套路与技巧模块 |
| 11 | aicrm_practice_compliance_rule | 合规规则 | 销售套路与技巧模块 |
| 12 | aicrm_practice_session | 对练会话 | 对练会话模块 |
| 13 | aicrm_practice_dialogue | 对话记录 | 对练会话模块 |
| 14 | aicrm_practice_evaluation_dimension | 评估维度 | 评估与打分模块 |
| 15 | aicrm_practice_evaluation_metric | 评估指标 | 评估与打分模块 |
| 16 | aicrm_practice_session_evaluation | 会话评估 | 评估与打分模块 |
| 17 | aicrm_practice_evaluation_detail | 评估明细 | 评估与打分模块 |
| 18 | aicrm_practice_improvement_suggestion | 改进建议 | 评估与打分模块 |
| 19 | aicrm_practice_learning_resource | 学习资源 | 评估与打分模块 |
| 20 | aicrm_practice_growth_track | 成长轨迹 | 评估与打分模块 |
| 21 | aicrm_practice_workflow_config | 工作流配置 | 工作流编排模块 |
| 22 | aicrm_practice_operation_log | 操作日志 | 系统管理模块 |

---

## 1. 虚拟客户模块

### 1.1 aicrm_practice_virtual_customer_template (虚拟客户模板)

**表说明**: 存储虚拟客户的模板配置，用于在对练时动态生成虚拟客户角色。

| 字段名 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
| id | BIGINT | 是 | 自增 | 主键ID |
| name | VARCHAR(100) | 是 | - | 模板名称 |
| description | TEXT | 否 | - | 模板描述 |
| age_min | INT | 否 | - | 年龄范围-最小值 |
| age_max | INT | 否 | - | 年龄范围-最大值 |
| occupation | VARCHAR(100) | 否 | - | 职业 |
| risk_preference | VARCHAR(20) | 否 | - | 风险偏好：conservative(保守型), moderate(稳健型), aggressive(进取型) |
| income_level | DECIMAL(15,2) | 否 | - | 收入水平 |
| asset_scale | DECIMAL(15,2) | 否 | - | 资产规模 |
| investment_experience | VARCHAR(20) | 否 | - | 投资经验：none(无经验), beginner(初级), intermediate(中级), expert(专家) |
| personality_traits | JSON | 否 | - | 性格特征，JSON格式存储 |
| custom_attributes | JSON | 否 | - | 自定义属性，JSON格式存储 |
| status | TINYINT | 是 | 1 | 状态：0-禁用，1-启用 |
| creator | VARCHAR(64) | 否 | '' | 创建者 |
| create_time | DATETIME | 是 | CURRENT_TIMESTAMP | 创建时间 |
| updater | VARCHAR(64) | 否 | '' | 更新者 |
| update_time | DATETIME | 是 | CURRENT_TIMESTAMP | 更新时间 |
| deleted | BIT(1) | 是 | b'0' | 是否删除 |
| tenant_id | BIGINT | 是 | 0 | 租户ID |

**索引**:
- PRIMARY KEY: id
- INDEX: status
- INDEX: tenant_id

---

## 2. 场景与剧本模块

### 2.1 aicrm_practice_scene (对练场景)

**表说明**: 存储各种对练场景配置，如拓客、需求挖掘、异议处理等。

| 字段名 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
| id | BIGINT | 是 | 自增 | 主键ID |
| name | VARCHAR(100) | 是 | - | 场景名称 |
| scene_type | VARCHAR(50) | 是 | - | 场景类型：prospecting(拓客), needs_discovery(需求挖掘), objection_handling(异议处理), closing(促成签约), complex(复杂场景) |
| description | TEXT | 否 | - | 场景描述 |
| target_skills | JSON | 否 | - | 目标技能，JSON数组格式 |
| keywords | JSON | 否 | - | 关键词，JSON数组格式 |
| difficulty_level | INT | 否 | 1 | 难度级别：1-5 |
| status | TINYINT | 是 | 1 | 状态：0-禁用，1-启用 |
| creator | VARCHAR(64) | 否 | '' | 创建者 |
| create_time | DATETIME | 是 | CURRENT_TIMESTAMP | 创建时间 |
| updater | VARCHAR(64) | 否 | '' | 更新者 |
| update_time | DATETIME | 是 | CURRENT_TIMESTAMP | 更新时间 |
| deleted | BIT(1) | 是 | b'0' | 是否删除 |
| tenant_id | BIGINT | 是 | 0 | 租户ID |

**索引**:
- PRIMARY KEY: id
- INDEX: scene_type
- INDEX: status
- INDEX: tenant_id

### 2.2 aicrm_practice_sales_script (销售剧本)

**表说明**: 存储销售剧本的主信息。

| 字段名 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
| id | BIGINT | 是 | 自增 | 主键ID |
| name | VARCHAR(100) | 是 | - | 剧本名称 |
| scene_id | BIGINT | 否 | - | 关联场景ID |
| current_version_id | BIGINT | 否 | - | 当前版本ID |
| description | TEXT | 否 | - | 剧本描述 |
| tags | JSON | 否 | - | 标签，JSON数组格式 |
| status | TINYINT | 是 | 1 | 状态：0-禁用，1-启用 |
| creator | VARCHAR(64) | 否 | '' | 创建者 |
| create_time | DATETIME | 是 | CURRENT_TIMESTAMP | 创建时间 |
| updater | VARCHAR(64) | 否 | '' | 更新者 |
| update_time | DATETIME | 是 | CURRENT_TIMESTAMP | 更新时间 |
| deleted | BIT(1) | 是 | b'0' | 是否删除 |
| tenant_id | BIGINT | 是 | 0 | 租户ID |

**索引**:
- PRIMARY KEY: id
- INDEX: scene_id
- INDEX: status
- INDEX: tenant_id

### 2.3 aicrm_practice_script_version (剧本版本)

**表说明**: 存储剧本的版本信息，支持版本管理和回溯。

| 字段名 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
| id | BIGINT | 是 | 自增 | 主键ID |
| script_id | BIGINT | 是 | - | 剧本ID |
| version_number | VARCHAR(20) | 是 | - | 版本号 |
| change_log | TEXT | 否 | - | 变更日志 |
| content | JSON | 否 | - | 剧本内容，JSON格式 |
| avg_compliance_score | DECIMAL(5,2) | 否 | - | 平均合规得分 |
| usage_count | INT | 否 | 0 | 使用次数 |
| is_active | TINYINT | 否 | 0 | 是否当前版本：0-否，1-是 |
| creator | VARCHAR(64) | 否 | '' | 创建者 |
| create_time | DATETIME | 是 | CURRENT_TIMESTAMP | 创建时间 |
| updater | VARCHAR(64) | 否 | '' | 更新者 |
| update_time | DATETIME | 是 | CURRENT_TIMESTAMP | 更新时间 |
| deleted | BIT(1) | 是 | b'0' | 是否删除 |
| tenant_id | BIGINT | 是 | 0 | 租户ID |

**索引**:
- PRIMARY KEY: id
- INDEX: script_id
- INDEX: is_active
- INDEX: tenant_id

### 2.4 aicrm_practice_script_branch (剧本分支)

**表说明**: 存储剧本的情节分支，支持动态剧情。

| 字段名 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
| id | BIGINT | 是 | 自增 | 主键ID |
| version_id | BIGINT | 是 | - | 版本ID |
| branch_name | VARCHAR(100) | 是 | - | 分支名称 |
| trigger_condition | VARCHAR(200) | 否 | - | 触发条件 |
| content | JSON | 否 | - | 分支内容，JSON格式 |
| parent_branch_id | BIGINT | 否 | - | 父分支ID |
| sequence | INT | 否 | 0 | 排序 |
| creator | VARCHAR(64) | 否 | '' | 创建者 |
| create_time | DATETIME | 是 | CURRENT_TIMESTAMP | 创建时间 |
| updater | VARCHAR(64) | 否 | '' | 更新者 |
| update_time | DATETIME | 是 | CURRENT_TIMESTAMP | 更新时间 |
| deleted | BIT(1) | 是 | b'0' | 是否删除 |
| tenant_id | BIGINT | 是 | 0 | 租户ID |

**索引**:
- PRIMARY KEY: id
- INDEX: version_id
- INDEX: parent_branch_id
- INDEX: tenant_id

### 2.5 aicrm_practice_case_library (案例库)

**表说明**: 存储多领域案例，用于培训和改进建议。

| 字段名 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
| id | BIGINT | 是 | 自增 | 主键ID |
| title | VARCHAR(200) | 是 | - | 案例标题 |
| case_type | VARCHAR(50) | 是 | - | 案例类型 |
| background | TEXT | 否 | - | 背景描述 |
| problem | TEXT | 否 | - | 问题描述 |
| solution | TEXT | 否 | - | 解决方案 |
| outcome | TEXT | 否 | - | 结果 |
| lessons_learned | TEXT | 否 | - | 经验教训 |
| difficulty | VARCHAR(20) | 否 | 'medium' | 难度：easy(简单), medium(中等), hard(困难) |
| status | TINYINT | 是 | 1 | 状态：0-禁用，1-启用 |
| creator | VARCHAR(64) | 否 | '' | 创建者 |
| create_time | DATETIME | 是 | CURRENT_TIMESTAMP | 创建时间 |
| updater | VARCHAR(64) | 否 | '' | 更新者 |
| update_time | DATETIME | 是 | CURRENT_TIMESTAMP | 更新时间 |
| deleted | BIT(1) | 是 | b'0' | 是否删除 |
| tenant_id | BIGINT | 是 | 0 | 租户ID |

**索引**:
- PRIMARY KEY: id
- INDEX: case_type
- INDEX: status
- INDEX: tenant_id

### 2.6 aicrm_practice_case_tag (案例标签)

**表说明**: 存储案例标签，用于案例分类和检索。

| 字段名 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
| id | BIGINT | 是 | 自增 | 主键ID |
| tag_name | VARCHAR(50) | 是 | - | 标签名称 |
| tag_type | VARCHAR(20) | 是 | - | 标签类型：scene(场景), customer(客户), strategy(策略) |
| color | VARCHAR(20) | 否 | - | 标签颜色 |
| sort | INT | 否 | 0 | 排序 |
| creator | VARCHAR(64) | 否 | '' | 创建者 |
| create_time | DATETIME | 是 | CURRENT_TIMESTAMP | 创建时间 |
| updater | VARCHAR(64) | 否 | '' | 更新者 |
| update_time | DATETIME | 是 | CURRENT_TIMESTAMP | 更新时间 |
| deleted | BIT(1) | 是 | b'0' | 是否删除 |
| tenant_id | BIGINT | 是 | 0 | 租户ID |

**索引**:
- PRIMARY KEY: id
- INDEX: tag_type
- INDEX: tenant_id

### 2.7 aicrm_practice_case_tag_relation (案例标签关联)

**表说明**: 存储案例与标签的关联关系。

| 字段名 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
| id | BIGINT | 是 | 自增 | 主键ID |
| case_id | BIGINT | 是 | - | 案例ID |
| tag_id | BIGINT | 是 | - | 标签ID |
| creator | VARCHAR(64) | 否 | '' | 创建者 |
| create_time | DATETIME | 是 | CURRENT_TIMESTAMP | 创建时间 |
| deleted | BIT(1) | 是 | b'0' | 是否删除 |
| tenant_id | BIGINT | 是 | 0 | 租户ID |

**索引**:
- PRIMARY KEY: id
- UNIQUE KEY: (case_id, tag_id)
- INDEX: case_id
- INDEX: tag_id
- INDEX: tenant_id

---

## 3. 销售套路与技巧模块

### 3.1 aicrm_practice_sales_routine (销售套路)

**表说明**: 存储销售套路配置，如"三步促成法"等。

| 字段名 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
| id | BIGINT | 是 | 自增 | 主键ID |
| name | VARCHAR(100) | 是 | - | 套路名称 |
| description | TEXT | 否 | - | 描述 |
| steps | JSON | 否 | - | 步骤，JSON数组格式 |
| applicable_scenes | JSON | 否 | - | 适用场景，JSON数组格式 |
| related_compliance_rules | JSON | 否 | - | 关联合规规则，JSON数组格式 |
| related_products | JSON | 否 | - | 关联产品，JSON数组格式 |
| status | TINYINT | 是 | 1 | 状态：0-禁用，1-启用 |
| creator | VARCHAR(64) | 否 | '' | 创建者 |
| create_time | DATETIME | 是 | CURRENT_TIMESTAMP | 创建时间 |
| updater | VARCHAR(64) | 否 | '' | 更新者 |
| update_time | DATETIME | 是 | CURRENT_TIMESTAMP | 更新时间 |
| deleted | BIT(1) | 是 | b'0' | 是否删除 |
| tenant_id | BIGINT | 是 | 0 | 租户ID |

**索引**:
- PRIMARY KEY: id
- INDEX: status
- INDEX: tenant_id

### 3.2 aicrm_practice_sales_skill (销售技巧)

**表说明**: 存储销售技巧和话术模板。

| 字段名 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
| id | BIGINT | 是 | 自增 | 主键ID |
| name | VARCHAR(100) | 是 | - | 技巧名称 |
| skill_type | VARCHAR(50) | 是 | - | 技巧类型 |
| description | TEXT | 否 | - | 描述 |
| template_content | TEXT | 否 | - | 话术模板 |
| example | TEXT | 否 | - | 示例 |
| related_compliance_rules | JSON | 否 | - | 关联合规规则，JSON数组格式 |
| related_knowledge | JSON | 否 | - | 关联产品知识，JSON数组格式 |
| status | TINYINT | 是 | 1 | 状态：0-禁用，1-启用 |
| creator | VARCHAR(64) | 否 | '' | 创建者 |
| create_time | DATETIME | 是 | CURRENT_TIMESTAMP | 创建时间 |
| updater | VARCHAR(64) | 否 | '' | 更新者 |
| update_time | DATETIME | 是 | CURRENT_TIMESTAMP | 更新时间 |
| deleted | BIT(1) | 是 | b'0' | 是否删除 |
| tenant_id | BIGINT | 是 | 0 | 租户ID |

**索引**:
- PRIMARY KEY: id
- INDEX: skill_type
- INDEX: status
- INDEX: tenant_id

### 3.3 aicrm_practice_compliance_rule (合规规则)

**表说明**: 存储合规规则，用于实时话术检查。

| 字段名 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
| id | BIGINT | 是 | 自增 | 主键ID |
| rule_code | VARCHAR(50) | 是 | - | 规则编码 |
| rule_name | VARCHAR(100) | 是 | - | 规则名称 |
| description | TEXT | 否 | - | 规则描述 |
| prohibited_terms | JSON | 否 | - | 禁用术语，JSON数组格式 |
| warning_terms | JSON | 否 | - | 警告术语，JSON数组格式 |
| severity | VARCHAR(20) | 否 | 'medium' | 严重程度：low(低), medium(中), high(高), critical(严重) |
| suggestion | TEXT | 否 | - | 改进建议 |
| status | TINYINT | 是 | 1 | 状态：0-禁用，1-启用 |
| creator | VARCHAR(64) | 否 | '' | 创建者 |
| create_time | DATETIME | 是 | CURRENT_TIMESTAMP | 创建时间 |
| updater | VARCHAR(64) | 否 | '' | 更新者 |
| update_time | DATETIME | 是 | CURRENT_TIMESTAMP | 更新时间 |
| deleted | BIT(1) | 是 | b'0' | 是否删除 |
| tenant_id | BIGINT | 是 | 0 | 租户ID |

**索引**:
- PRIMARY KEY: id
- UNIQUE KEY: (rule_code, tenant_id)
- INDEX: severity
- INDEX: status
- INDEX: tenant_id

---

## 4. 对练会话模块

### 4.1 aicrm_practice_session (对练会话)

**表说明**: 存储对练会话的主记录。

| 字段名 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
| id | BIGINT | 是 | 自增 | 主键ID |
| session_code | VARCHAR(50) | 是 | - | 会话编号 |
| user_id | BIGINT | 是 | - | 用户ID |
| scene_id | BIGINT | 否 | - | 场景ID |
| script_id | BIGINT | 否 | - | 剧本ID |
| script_version_id | BIGINT | 否 | - | 剧本版本ID |
| virtual_customer_id | BIGINT | 否 | - | 虚拟客户模板ID |
| virtual_customer_profile | JSON | 否 | - | 虚拟客户画像，JSON格式 |
| session_status | VARCHAR(20) | 是 | 'ongoing' | 会话状态：ongoing(进行中), completed(已完成), abandoned(已放弃) |
| start_time | DATETIME | 是 | - | 开始时间 |
| end_time | DATETIME | 否 | - | 结束时间 |
| duration_seconds | INT | 否 | - | 持续时长(秒) |
| overall_score | DECIMAL(5,2) | 否 | - | 总分 |
| creator | VARCHAR(64) | 否 | '' | 创建者 |
| create_time | DATETIME | 是 | CURRENT_TIMESTAMP | 创建时间 |
| updater | VARCHAR(64) | 否 | '' | 更新者 |
| update_time | DATETIME | 是 | CURRENT_TIMESTAMP | 更新时间 |
| deleted | BIT(1) | 是 | b'0' | 是否删除 |
| tenant_id | BIGINT | 是 | 0 | 租户ID |

**索引**:
- PRIMARY KEY: id
- UNIQUE KEY: (session_code, tenant_id)
- INDEX: user_id
- INDEX: scene_id
- INDEX: session_status
- INDEX: start_time
- INDEX: tenant_id

### 4.2 aicrm_practice_dialogue (对话记录)

**表说明**: 存储对练会话中的每轮对话。

| 字段名 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
| id | BIGINT | 是 | 自增 | 主键ID |
| session_id | BIGINT | 是 | - | 会话ID |
| speaker_role | VARCHAR(20) | 是 | - | 发言角色：user(用户), virtual_customer(虚拟客户) |
| content | TEXT | 是 | - | 对话内容 |
| emotion | JSON | 否 | - | 情绪，JSON格式 |
| dialogue_time | DATETIME | 是 | - | 对话时间 |
| sequence | INT | 是 | - | 对话序号 |
| compliance_check_result | JSON | 否 | - | 合规检查结果，JSON格式 |
| creator | VARCHAR(64) | 否 | '' | 创建者 |
| create_time | DATETIME | 是 | CURRENT_TIMESTAMP | 创建时间 |
| deleted | BIT(1) | 是 | b'0' | 是否删除 |
| tenant_id | BIGINT | 是 | 0 | 租户ID |

**索引**:
- PRIMARY KEY: id
- INDEX: session_id
- INDEX: dialogue_time
- INDEX: tenant_id

---

## 5. 评估与打分模块

### 5.1 aicrm_practice_evaluation_dimension (评估维度)

**表说明**: 存储评估维度配置，如沟通逻辑、专业能力、合规表现等。

| 字段名 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
| id | BIGINT | 是 | 自增 | 主键ID |
| dimension_code | VARCHAR(50) | 是 | - | 维度编码 |
| dimension_name | VARCHAR(100) | 是 | - | 维度名称 |
| description | TEXT | 否 | - | 描述 |
| weight | INT | 是 | 1 | 权重 |
| sort | INT | 否 | 0 | 排序 |
| status | TINYINT | 是 | 1 | 状态：0-禁用，1-启用 |
| creator | VARCHAR(64) | 否 | '' | 创建者 |
| create_time | DATETIME | 是 | CURRENT_TIMESTAMP | 创建时间 |
| updater | VARCHAR(64) | 否 | '' | 更新者 |
| update_time | DATETIME | 是 | CURRENT_TIMESTAMP | 更新时间 |
| deleted | BIT(1) | 是 | b'0' | 是否删除 |
| tenant_id | BIGINT | 是 | 0 | 租户ID |

**索引**:
- PRIMARY KEY: id
- UNIQUE KEY: (dimension_code, tenant_id)
- INDEX: status
- INDEX: tenant_id

### 5.2 aicrm_practice_evaluation_metric (评估指标)

**表说明**: 存储评估指标配置，每个维度下的具体评估指标。

| 字段名 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
| id | BIGINT | 是 | 自增 | 主键ID |
| dimension_id | BIGINT | 是 | - | 维度ID |
| metric_code | VARCHAR(50) | 是 | - | 指标编码 |
| metric_name | VARCHAR(100) | 是 | - | 指标名称 |
| description | TEXT | 否 | - | 描述 |
| weight | INT | 是 | 1 | 权重 |
| max_score | DECIMAL(5,2) | 是 | 100.00 | 最高分 |
| scoring_criteria | JSON | 否 | - | 评分标准，JSON格式 |
| sort | INT | 否 | 0 | 排序 |
| status | TINYINT | 是 | 1 | 状态：0-禁用，1-启用 |
| creator | VARCHAR(64) | 否 | '' | 创建者 |
| create_time | DATETIME | 是 | CURRENT_TIMESTAMP | 创建时间 |
| updater | VARCHAR(64) | 否 | '' | 更新者 |
| update_time | DATETIME | 是 | CURRENT_TIMESTAMP | 更新时间 |
| deleted | BIT(1) | 是 | b'0' | 是否删除 |
| tenant_id | BIGINT | 是 | 0 | 租户ID |

**索引**:
- PRIMARY KEY: id
- UNIQUE KEY: (metric_code, tenant_id)
- INDEX: dimension_id
- INDEX: status
- INDEX: tenant_id

### 5.3 aicrm_practice_session_evaluation (会话评估)

**表说明**: 存储对练会话的评估结果。

| 字段名 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
| id | BIGINT | 是 | 自增 | 主键ID |
| session_id | BIGINT | 是 | - | 会话ID |
| total_score | DECIMAL(5,2) | 是 | - | 总分 |
| dimension_scores | JSON | 否 | - | 维度得分，JSON格式 |
| radar_chart_data | JSON | 否 | - | 雷达图数据，JSON格式 |
| strengths | JSON | 否 | - | 优势，JSON数组格式 |
| weaknesses | JSON | 否 | - | 弱点，JSON数组格式 |
| compliance_issues | JSON | 否 | - | 合规问题，JSON数组格式 |
| creator | VARCHAR(64) | 否 | '' | 创建者 |
| create_time | DATETIME | 是 | CURRENT_TIMESTAMP | 创建时间 |
| updater | VARCHAR(64) | 否 | '' | 更新者 |
| update_time | DATETIME | 是 | CURRENT_TIMESTAMP | 更新时间 |
| deleted | BIT(1) | 是 | b'0' | 是否删除 |
| tenant_id | BIGINT | 是 | 0 | 租户ID |

**索引**:
- PRIMARY KEY: id
- UNIQUE KEY: (session_id, tenant_id)
- INDEX: total_score
- INDEX: tenant_id

### 5.4 aicrm_practice_evaluation_detail (评估明细)

**表说明**: 存储评估的详细得分和反馈。

| 字段名 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
| id | BIGINT | 是 | 自增 | 主键ID |
| evaluation_id | BIGINT | 是 | - | 评估ID |
| dimension_id | BIGINT | 是 | - | 维度ID |
| metric_id | BIGINT | 是 | - | 指标ID |
| score | DECIMAL(5,2) | 是 | - | 得分 |
| feedback | TEXT | 否 | - | 反馈 |
| evidence | JSON | 否 | - | 证据，JSON格式 |
| creator | VARCHAR(64) | 否 | '' | 创建者 |
| create_time | DATETIME | 是 | CURRENT_TIMESTAMP | 创建时间 |
| deleted | BIT(1) | 是 | b'0' | 是否删除 |
| tenant_id | BIGINT | 是 | 0 | 租户ID |

**索引**:
- PRIMARY KEY: id
- INDEX: evaluation_id
- INDEX: dimension_id
- INDEX: metric_id
- INDEX: tenant_id

### 5.5 aicrm_practice_improvement_suggestion (改进建议)

**表说明**: 存储针对每次对练的个性化改进建议。

| 字段名 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
| id | BIGINT | 是 | 自增 | 主键ID |
| session_id | BIGINT | 是 | - | 会话ID |
| evaluation_id | BIGINT | 是 | - | 评估ID |
| suggestion_type | VARCHAR(50) | 是 | - | 建议类型 |
| priority | VARCHAR(20) | 否 | 'medium' | 优先级：low(低), medium(中), high(高) |
| content | TEXT | 是 | - | 建议内容 |
| related_case | JSON | 否 | - | 关联案例，JSON格式 |
| related_resources | JSON | 否 | - | 关联资源，JSON数组格式 |
| creator | VARCHAR(64) | 否 | '' | 创建者 |
| create_time | DATETIME | 是 | CURRENT_TIMESTAMP | 创建时间 |
| updater | VARCHAR(64) | 否 | '' | 更新者 |
| update_time | DATETIME | 是 | CURRENT_TIMESTAMP | 更新时间 |
| deleted | BIT(1) | 是 | b'0' | 是否删除 |
| tenant_id | BIGINT | 是 | 0 | 租户ID |

**索引**:
- PRIMARY KEY: id
- INDEX: session_id
- INDEX: evaluation_id
- INDEX: priority
- INDEX: tenant_id

### 5.6 aicrm_practice_learning_resource (学习资源)

**表说明**: 存储学习资源，如课程、模板、文章等。

| 字段名 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
| id | BIGINT | 是 | 自增 | 主键ID |
| resource_type | VARCHAR(20) | 是 | - | 资源类型：course(课程), template(模板), article(文章), video(视频) |
| title | VARCHAR(200) | 是 | - | 标题 |
| description | TEXT | 否 | - | 描述 |
| content | LONGTEXT | 否 | - | 内容 |
| url | VARCHAR(500) | 否 | - | 链接 |
| tags | JSON | 否 | - | 标签，JSON数组格式 |
| related_skills | JSON | 否 | - | 关联技能，JSON数组格式 |
| status | TINYINT | 是 | 1 | 状态：0-禁用，1-启用 |
| creator | VARCHAR(64) | 否 | '' | 创建者 |
| create_time | DATETIME | 是 | CURRENT_TIMESTAMP | 创建时间 |
| updater | VARCHAR(64) | 否 | '' | 更新者 |
| update_time | DATETIME | 是 | CURRENT_TIMESTAMP | 更新时间 |
| deleted | BIT(1) | 是 | b'0' | 是否删除 |
| tenant_id | BIGINT | 是 | 0 | 租户ID |

**索引**:
- PRIMARY KEY: id
- INDEX: resource_type
- INDEX: status
- INDEX: tenant_id

### 5.7 aicrm_practice_growth_track (成长轨迹)

**表说明**: 记录用户的成长轨迹数据，支持跨周期对比分析。

| 字段名 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
| id | BIGINT | 是 | 自增 | 主键ID |
| user_id | BIGINT | 是 | - | 用户ID |
| session_id | BIGINT | 是 | - | 会话ID |
| score | DECIMAL(5,2) | 是 | - | 得分 |
| dimension_scores | JSON | 否 | - | 各维度得分，JSON格式 |
| high_frequency_issues | JSON | 否 | - | 高频问题类型，JSON数组格式 |
| practice_date | DATE | 是 | - | 练习日期 |
| period_type | VARCHAR(20) | 否 | 'daily' | 周期类型：daily(每日), weekly(每周), monthly(每月), quarterly(每季度) |
| creator | VARCHAR(64) | 否 | '' | 创建者 |
| create_time | DATETIME | 是 | CURRENT_TIMESTAMP | 创建时间 |
| deleted | BIT(1) | 是 | b'0' | 是否删除 |
| tenant_id | BIGINT | 是 | 0 | 租户ID |

**索引**:
- PRIMARY KEY: id
- INDEX: user_id
- INDEX: session_id
- INDEX: practice_date
- INDEX: period_type
- INDEX: tenant_id

---

## 6. 工作流编排模块

### 6.1 aicrm_practice_workflow_config (工作流配置)

**表说明**: 存储工作流配置，对接HiAgent平台。

| 字段名 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
| id | BIGINT | 是 | 自增 | 主键ID |
| workflow_name | VARCHAR(100) | 是 | - | 工作流名称 |
| workflow_type | VARCHAR(50) | 是 | - | 工作流类型 |
| description | TEXT | 否 | - | 描述 |
| config_content | JSON | 否 | - | 配置内容，JSON格式 |
| hiagent_workflow_id | VARCHAR(100) | 否 | - | HiAgent工作流ID |
| is_editable | TINYINT | 否 | 1 | 是否可编辑：0-否，1-是 |
| status | TINYINT | 是 | 1 | 状态：0-禁用，1-启用 |
| creator | VARCHAR(64) | 否 | '' | 创建者 |
| create_time | DATETIME | 是 | CURRENT_TIMESTAMP | 创建时间 |
| updater | VARCHAR(64) | 否 | '' | 更新者 |
| update_time | DATETIME | 是 | CURRENT_TIMESTAMP | 更新时间 |
| deleted | BIT(1) | 是 | b'0' | 是否删除 |
| tenant_id | BIGINT | 是 | 0 | 租户ID |

**索引**:
- PRIMARY KEY: id
- INDEX: workflow_type
- INDEX: status
- INDEX: tenant_id

---

## 7. 系统管理模块

### 7.1 aicrm_practice_operation_log (操作日志)

**表说明**: 记录系统操作日志。

| 字段名 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
| id | BIGINT | 是 | 自增 | 主键ID |
| module | VARCHAR(50) | 是 | - | 模块 |
| operation_type | VARCHAR(50) | 是 | - | 操作类型 |
| operation_desc | VARCHAR(200) | 否 | - | 操作描述 |
| user_id | BIGINT | 是 | - | 操作用户ID |
| username | VARCHAR(64) | 否 | - | 操作用户名 |
| request_params | JSON | 否 | - | 请求参数，JSON格式 |
| response_result | JSON | 否 | - | 响应结果，JSON格式 |
| ip_address | VARCHAR(50) | 否 | - | IP地址 |
| user_agent | VARCHAR(500) | 否 | - | 用户代理 |
| operation_time | DATETIME | 是 | - | 操作时间 |
| execution_time | INT | 否 | - | 执行时长(毫秒) |
| success | TINYINT | 是 | 1 | 是否成功：0-失败，1-成功 |
| error_msg | TEXT | 否 | - | 错误信息 |
| create_time | DATETIME | 是 | CURRENT_TIMESTAMP | 创建时间 |
| deleted | BIT(1) | 是 | b'0' | 是否删除 |
| tenant_id | BIGINT | 是 | 0 | 租户ID |

**索引**:
- PRIMARY KEY: id
- INDEX: user_id
- INDEX: module
- INDEX: operation_type
- INDEX: operation_time
- INDEX: success
- INDEX: tenant_id

---

## 附录：枚举值说明

### 风险偏好 (risk_preference)
- `conservative`: 保守型
- `moderate`: 稳健型
- `aggressive`: 进取型

### 投资经验 (investment_experience)
- `none`: 无经验
- `beginner`: 初级
- `intermediate`: 中级
- `expert`: 专家

### 场景类型 (scene_type)
- `prospecting`: 拓客
- `needs_discovery`: 需求挖掘
- `objection_handling`: 异议处理
- `closing`: 促成签约
- `complex`: 复杂场景

### 会话状态 (session_status)
- `ongoing`: 进行中
- `completed`: 已完成
- `abandoned`: 已放弃

### 发言角色 (speaker_role)
- `user`: 用户
- `virtual_customer`: 虚拟客户

### 严重程度 (severity)
- `low`: 低
- `medium`: 中
- `high`: 高
- `critical`: 严重

### 资源类型 (resource_type)
- `course`: 课程
- `template`: 模板
- `article`: 文章
- `video`: 视频

### 周期类型 (period_type)
- `daily`: 每日
- `weekly`: 每周
- `monthly`: 每月
- `quarterly`: 每季度

### 难度等级 (difficulty)
- `easy`: 简单
- `medium`: 中等
- `hard`: 困难

### 优先级 (priority)
- `low`: 低
- `medium`: 中
- `high`: 高

### 标签类型 (tag_type)
- `scene`: 场景
- `customer`: 客户
- `strategy`: 策略

---

## 版本历史

| 版本 | 日期 | 说明 | 作者 |
|------|------|------|------|
| v1.0 | 2025-11-06 | 初始版本 | Claude |
