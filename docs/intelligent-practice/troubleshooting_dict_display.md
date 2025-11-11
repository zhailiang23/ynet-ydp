# 字典值显示问题排查指南

## 问题现象
列表中的字典字段显示为原始值（如 `male`, `executive`），而不是中文标签（如"男"，"企业高管"）。

## 排查步骤

### 1. 检查数据库中的字典数据

```bash
mysql -h127.0.0.1 -P3306 -uroot -p123456 ruoyi-vue-pro << 'EOSQL'
-- 检查字典数量
SELECT 
    '性别字典' as category, COUNT(*) as count 
FROM system_dict_data WHERE dict_type = 'aicrm_gender'
UNION ALL
SELECT '职业字典' as category, COUNT(*) as count 
FROM system_dict_data WHERE dict_type = 'aicrm_occupation'
UNION ALL
SELECT '所属行业字典' as category, COUNT(*) as count 
FROM system_dict_data WHERE dict_type = 'aicrm_industry'
UNION ALL
SELECT '性格类型字典' as category, COUNT(*) as count 
FROM system_dict_data WHERE dict_type = 'aicrm_personality_type'
UNION ALL
SELECT '风险偏好字典' as category, COUNT(*) as count 
FROM system_dict_data WHERE dict_type = 'aicrm_risk_preference'
UNION ALL
SELECT '培训文件类型字典' as category, COUNT(*) as count 
FROM system_dict_data WHERE dict_type = 'aicrm_practice_material_file_type';

-- 查看具体数据
SELECT type, name FROM system_dict_type WHERE type LIKE 'aicrm_%';
EOSQL
```

**期望结果：**
- 性别字典：2 条
- 职业字典：7 条
- 所属行业字典：10 条
- 性格类型字典：5 条
- 风险偏好字典：5 条
- 培训文件类型字典：3 条

### 2. 检查后端 API 是否返回字典数据

打开浏览器开发者工具（F12），切换到 Network 标签：

1. 刷新页面
2. 查找字典相关的 API 请求，URL 类似：
   ```
   /admin-api/system/dict-data/type?type=aicrm_gender
   /admin-api/system/dict-data/type?type=aicrm_occupation
   ```

3. 检查响应数据：
   - 状态码应该是 200
   - 响应应该包含字典项数据

**示例正确响应：**
```json
{
  "code": 0,
  "data": [
    {
      "label": "男",
      "value": "male",
      "colorType": "primary"
    },
    {
      "label": "女",
      "value": "female",
      "colorType": "danger"
    }
  ]
}
```

### 3. 检查前端控制台错误

在浏览器开发者工具的 Console 标签中：
- 查看是否有红色错误信息
- 特别注意与 `dict` 或 `DictTag` 相关的错误

常见错误：
- `Cannot read property 'xxx' of undefined`
- `Dict type 'aicrm_xxx' not found`

### 4. 清除前端缓存

```bash
# 方法1: 强制刷新
Windows: Ctrl + F5
Mac: Cmd + Shift + R

# 方法2: 清除浏览器缓存
Windows: Ctrl + Shift + Delete
Mac: Cmd + Shift + Delete

# 方法3: 使用无痕模式
Windows: Ctrl + Shift + N
Mac: Cmd + Shift + N
```

### 5. 重启前端开发服务器

```bash
cd frontend

# 停止当前运行的服务器（Ctrl+C）
# 然后重新启动
pnpm dev:antd
```

### 6. 检查前端代码配置

确认 `data.ts` 文件中列配置正确：

**虚拟客户列表** (`views/aicrm/practicevirtualcustomer/data.ts`)：
```typescript
{
  field: 'gender',
  title: '性别',
  minWidth: 80,
  cellRender: { name: 'DictTag', props: { dictType: 'aicrm_gender' } },
}
```

**培训文件列表** (`views/aicrm/practicematerial/data.ts`)：
```typescript
{
  field: 'fileType',
  title: '文件类型',
  minWidth: 120,
  cellRender: { name: 'DictTag', props: { dictType: 'aicrm_practice_material_file_type' } },
}
```

### 7. 验证字典类型名称是否一致

确保三处的字典类型名称完全一致：
1. **数据库** - `system_dict_data.dict_type` 字段
2. **后端常量** - `CrmDictTypeConstants.java`
3. **前端配置** - `data.ts` 中的 `dictType`

例如性别字典：
- 数据库：`aicrm_gender`
- 后端：`String GENDER = "aicrm_gender";`
- 前端：`dictType: 'aicrm_gender'`

## 快速修复命令

### 重新导入字典数据
```bash
cd /Users/zhailiang/Documents/code/ynet-ydp/docs/intelligent-practice
mysql -h127.0.0.1 -P3306 -uroot -p123456 ruoyi-vue-pro < practice_virtual_customer_dict_safe.sql
mysql -h127.0.0.1 -P3306 -uroot -p123456 ruoyi-vue-pro < practice_material_file_type_dict.sql
```

### 验证所有字典
```bash
mysql -h127.0.0.1 -P3306 -uroot -p123456 ruoyi-vue-pro -e "
SELECT 
    dt.type as dict_type,
    dt.name as dict_name,
    COUNT(dd.id) as item_count
FROM system_dict_type dt
LEFT JOIN system_dict_data dd ON dt.type = dd.dict_type
WHERE dt.type LIKE 'aicrm_%'
GROUP BY dt.type, dt.name
ORDER BY dt.type;
" 2>&1 | grep -v Warning
```

## 常见问题解答

### Q1: 为什么有些字典显示正常，有些不显示？
**A:** 可能是部分字典数据未导入或导入失败。执行上面的"验证所有字典"命令检查。

### Q2: 字典显示为 `[object Object]`
**A:** 前端配置错误，检查是否正确使用了 `DictTag` 组件。

### Q3: 字典显示空白
**A:** 
1. 检查数据库中是否有对应的字典数据
2. 检查后端 API 是否返回数据
3. 检查前端是否正确调用了字典 API

### Q4: 刷新后字典恢复正常，但过一会儿又不显示了
**A:** 可能是前端缓存问题，尝试：
1. 禁用浏览器扩展（特别是广告拦截器）
2. 使用无痕模式测试
3. 检查前端控制台是否有警告信息

## 联系支持

如果以上步骤都无法解决问题，请提供以下信息：
1. 浏览器控制台的错误信息截图
2. Network 标签中字典 API 请求的响应数据
3. 数据库中字典数据的查询结果
