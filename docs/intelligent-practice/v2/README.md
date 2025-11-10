# 智能陪练模块 - 数据库设计文档

## 文档概述

本目录包含智能陪练模块的完整数据库设计文档，基于产品需求"智能陪练(50分)"进行设计。

## 文档列表

1. **database-er-diagram.md** - 数据库ER图
   - 包含完整的实体关系图(Mermaid格式)
   - 展示各个表之间的关系
   - 包含核心数据表说明

2. **intelligent-practice-schema.sql** - 数据库建表脚本
   - MySQL建表语句
   - 包含所有表的完整定义
   - 包含索引和约束

3. **data-dictionary.md** - 数据字典
   - 详细的表结构说明
   - 字段定义和类型
   - 枚举值说明

## 功能模块

智能陪练模块包含以下核心功能：

### 1. 角色扮演对练
- **虚拟客户动态生成**: 基于大模型智能体动态生成多元虚拟客户
- **多场景模拟构建**: 支持拓客、需求挖掘、异议处理、促成签约等场景
- **实时交互对练引擎**: 实时对话演练，模拟客户提问和情绪反馈

### 2. 评估与打分
- **多维度量化评估**: 沟通逻辑、专业能力、合规表现等维度评估
- **合规实时校验与压力测试**: 实时校验话术，模拟极端场景
- **个性化改进方案生成**: 结合评估数据输出改进建议
- **成长轨迹追溯**: 记录历次对练数据，生成成长曲线

### 3. 销售套路、技巧、剧本管理
- **套路技巧自定义配置**: 支持上传/编辑销售套路和技巧
- **剧本动态生成与编辑**: 自动生成对练剧本，支持手动调整
- **剧本版本迭代管理**: 记录剧本更新历史，支持版本对比
- **案例库标签化管理**: 多领域案例打标签，支持检索

### 4. 后台管理
- **权限管理**: 用户管理、组织管理、功能权限
- **日志管理**: 系统操作日志

### 5. 工作流编排
- **工作流**: 对接HiAgent平台，支持工作流编辑调优

## 数据表结构

共22张表，分为7个模块：

### 虚拟客户模块 (1张表)
- `aicrm_practice_virtual_customer_template` - 虚拟客户模板

### 场景与剧本模块 (7张表)
- `aicrm_practice_scene` - 对练场景
- `aicrm_practice_sales_script` - 销售剧本
- `aicrm_practice_script_version` - 剧本版本
- `aicrm_practice_script_branch` - 剧本分支
- `aicrm_practice_case_library` - 案例库
- `aicrm_practice_case_tag` - 案例标签
- `aicrm_practice_case_tag_relation` - 案例标签关联

### 销售套路与技巧模块 (3张表)
- `aicrm_practice_sales_routine` - 销售套路
- `aicrm_practice_sales_skill` - 销售技巧
- `aicrm_practice_compliance_rule` - 合规规则

### 对练会话模块 (2张表)
- `aicrm_practice_session` - 对练会话
- `aicrm_practice_dialogue` - 对话记录

### 评估与打分模块 (7张表)
- `aicrm_practice_evaluation_dimension` - 评估维度
- `aicrm_practice_evaluation_metric` - 评估指标
- `aicrm_practice_session_evaluation` - 会话评估
- `aicrm_practice_evaluation_detail` - 评估明细
- `aicrm_practice_improvement_suggestion` - 改进建议
- `aicrm_practice_learning_resource` - 学习资源
- `aicrm_practice_growth_track` - 成长轨迹

### 工作流编排模块 (1张表)
- `aicrm_practice_workflow_config` - 工作流配置

### 系统管理模块 (1张表)
- `aicrm_practice_operation_log` - 操作日志

## 设计原则

1. **遵循项目规范**: 所有表使用统一的标准字段(id, creator, create_time, updater, update_time, deleted, tenant_id)
2. **支持多租户**: 所有表包含tenant_id字段
3. **灵活性**: 大量使用JSON字段存储复杂数据结构
4. **可扩展性**: 表结构设计考虑未来扩展需求
5. **性能优化**: 合理设置索引，优化查询性能

## 使用说明

### 1. 查看ER图
打开 `database-er-diagram.md` 文件，使用支持Mermaid的工具查看ER图：
- GitHub会自动渲染Mermaid图表
- VS Code安装Markdown Preview Mermaid Support插件
- 在线工具: https://mermaid.live/

### 2. 执行建表脚本
```bash
# 连接到MySQL数据库
mysql -h localhost -u root -p

# 选择数据库
use ruoyi-vue-pro;

# 执行建表脚本
source /path/to/intelligent-practice-schema.sql
```

### 3. 查看数据字典
打开 `data-dictionary.md` 文件查看详细的表结构说明和字段定义。

## 注意事项

1. **字符集**: 所有表使用 `utf8mb4` 字符集，支持emoji和特殊字符
2. **存储引擎**: 使用 InnoDB 存储引擎
3. **JSON字段**: 需要MySQL 5.7+版本支持
4. **时区**: 建议统一使用UTC时区或Asia/Shanghai时区
5. **备份**: 建议定期备份数据库

## 后续工作

1. 开发Java后端代码（Entity, Mapper, Service, Controller）
2. 开发前端页面（Vue 3 + Ant Design Vue）
3. 编写单元测试
4. 性能测试和优化
5. 准备初始化数据（评估维度、评估指标、合规规则等）

## 版本历史

| 版本 | 日期 | 说明 | 作者 |
|------|------|------|------|
| v1.0 | 2025-11-06 | 初始版本 | Claude |

## 联系方式

如有问题或建议，请联系项目团队。
