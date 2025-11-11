# 销售技巧表单增强说明

## 修改概述

将销售技巧管理的"合规规则"和"产品知识"字段改为下拉选择，选项来自培训文件管理。

## 修改内容

### 1. 前端表单修改

**文件**: `frontend/apps/web-antd/src/views/aicrm/practiceskill/data.ts`

#### 查询条件简化
- 只保留"技巧名称"和"技巧分类"两个查询条件
- 技巧分类改为下拉选择框，使用字典 `aicrm_skill_category`

#### 列表字段简化
移除以下三列：
- 话术模板
- 合规规则说明
- 关联产品知识

保留字段：
- 技巧ID
- 技巧名称
- 技巧分类（使用 CellDict 渲染器显示标签）
- 创建时间
- 操作

#### 创建表单修改

1. **技巧分类**
   - 从 Input 改为 Select 下拉框
   - 使用字典 `aicrm_skill_category` 的选项

2. **话术模板**
   - 从 Input 改为 Textarea（4行）

3. **合规规则**
   - 从 Textarea 改为 ApiSelect 下拉框
   - 选项来源：`fileType='rules_and_regulations'` 的培训文件
   - 保存值：培训文件的 ID

4. **产品知识**
   - 从 Textarea 改为 ApiSelect 下拉框
   - 选项来源：`fileType='product_information'` 的培训文件
   - 保存值：培训文件的 ID

### 2. 后端数据模型修改

#### DO 类修改
**文件**: `backend/ynet-module-practice/.../practiceskill/PracticeSkillDO.java`

```java
// 修改前
private String complianceRules; // 合规规则说明
private String relatedProducts; // 关联产品知识

// 修改后
private Long complianceRules; // 合规规则（培训文件ID）
private Long relatedProducts; // 产品知识（培训文件ID）
```

#### VO 类修改
**文件**:
- `PracticeSkillSaveReqVO.java`
- `PracticeSkillRespVO.java`

字段类型从 `String` 改为 `Long`，注释改为"培训文件ID"。

### 3. 数据库表结构修改

**SQL 脚本**: `docs/intelligent-practice/modify_practice_skill_fields.sql`

```sql
-- 先清空旧数据（因为字段类型从 VARCHAR 改为 BIGINT）
UPDATE crm_practice_skill SET compliance_rules = NULL, related_products = NULL;

-- 修改字段类型
ALTER TABLE crm_practice_skill MODIFY COLUMN compliance_rules BIGINT COMMENT '合规规则（培训文件ID）';
ALTER TABLE crm_practice_skill MODIFY COLUMN related_products BIGINT COMMENT '产品知识（培训文件ID）';
```

### 4. 前端 API 类型定义修改

**文件**: `frontend/apps/web-antd/src/api/aicrm/practiceskill/index.ts`

```typescript
export interface PracticeSkill {
  complianceRules: number; // 合规规则（培训文件ID）
  relatedProducts: number; // 产品知识（培训文件ID）
}
```

## 使用说明

### 前置条件

1. 确保培训文件管理中已添加以下类型的文件：
   - 文件类型为"规则规范"的文件（用于合规规则下拉框）
   - 文件类型为"产品信息"的文件（用于产品知识下拉框）

2. 如果下拉框为空，请：
   - 进入"培训文件管理"页面
   - 添加对应类型的培训文件

### 创建销售技巧

1. 点击"新增销售技巧"按钮
2. 填写技巧名称
3. 选择技巧分类（下拉选择）
4. 输入话术模板（可选）
5. 从下拉框选择合规规则文件（可选）
6. 从下拉框选择产品知识文件（可选）
7. 点击"确定"保存

### 字段说明

- **合规规则**：保存的是培训文件的 ID，实际关联到文件类型为"规则规范"的培训文件
- **产品知识**：保存的是培训文件的 ID，实际关联到文件类型为"产品信息"的培训文件

## 技术实现

### 下拉框数据加载

使用 `ApiSelect` 组件动态加载数据：

```typescript
{
  fieldName: 'complianceRules',
  label: '合规规则',
  component: 'ApiSelect',
  componentProps: {
    placeholder: '请选择合规规则文件',
    allowClear: true,
    api: async () => {
      const result = await getPracticeMaterialPage({
        pageNo: 1,
        pageSize: 1000,
        fileType: 'rules_and_regulations',
      });
      return result.list.map((item: any) => ({
        label: item.name,
        value: item.id,
      }));
    },
  },
}
```

## 数据迁移注意事项

⚠️ **重要提示**：
- 执行数据库迁移脚本会清空现有的 `compliance_rules` 和 `related_products` 字段数据
- 如果生产环境有重要数据，请先备份后再执行迁移
- 迁移后需要重新配置所有销售技巧的合规规则和产品知识

## 验证清单

- [ ] 数据库字段类型已修改为 BIGINT
- [ ] 后端 DO 和 VO 字段类型已修改为 Long
- [ ] 前端 TypeScript 类型定义已修改为 number
- [ ] 培训文件管理中已添加"规则规范"类型的文件
- [ ] 培训文件管理中已添加"产品信息"类型的文件
- [ ] 创建销售技巧时合规规则下拉框显示正常
- [ ] 创建销售技巧时产品知识下拉框显示正常
- [ ] 保存后可以正确回显选中的文件
