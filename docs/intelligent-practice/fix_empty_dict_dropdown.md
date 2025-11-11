# 修复职业和所属行业下拉框为空的问题

## 问题原因

职业和所属行业下拉框为空，是因为对应的字典数据还没有导入到数据库中。

## 解决步骤

### 1. 执行字典数据 SQL 脚本

使用 MySQL 客户端连接到数据库，执行以下脚本：

```bash
# 连接到数据库
mysql -h127.0.0.1 -P3306 -uroot -p123456 ruoyi-vue-pro

# 执行字典数据脚本
source /Users/zhailiang/Documents/code/ynet-ydp/docs/intelligent-practice/practice_virtual_customer_dict.sql;
```

或者使用 Navicat/DBeaver 等数据库管理工具：
1. 打开 `docs/intelligent-practice/practice_virtual_customer_dict.sql` 文件
2. 复制所有 SQL 内容
3. 在数据库中执行

### 2. 验证字典数据是否导入成功

执行以下 SQL 检查：

```sql
-- 检查职业字典
SELECT * FROM system_dict_type WHERE type = 'aicrm_occupation';
SELECT * FROM system_dict_data WHERE dict_type = 'aicrm_occupation';

-- 检查所属行业字典
SELECT * FROM system_dict_type WHERE type = 'aicrm_industry';
SELECT * FROM system_dict_data WHERE dict_type = 'aicrm_industry';
```

应该能看到：
- `aicrm_occupation`：7 条数据（企业高管、企业主、专业人士、公务员、自由职业者、退休人员、其他）
- `aicrm_industry`：10 条数据（金融、制造业、房地产、互联网/科技、医疗健康、教育培训、批发零售、服务业、政府机关、其他）

### 3. 刷新前端页面

1. 清除浏览器缓存（Ctrl+Shift+Delete 或 Cmd+Shift+Delete）
2. 或者使用无痕模式打开
3. 重新登录系统
4. 再次访问虚拟客户管理页面

### 4. 如果还是不显示

检查前端控制台是否有错误：
1. 打开浏览器开发者工具（F12）
2. 查看 Console 标签页
3. 查看 Network 标签页，检查字典接口请求是否成功

常见问题：
- 字典接口返回 404：检查后端是否正常启动
- 字典接口返回空数组：确认数据库中字典数据是否存在
- 前端报错：检查前端代码是否有编译错误

## 快速验证命令

### 数据库验证
```bash
mysql -h127.0.0.1 -P3306 -uroot -p123456 ruoyi-vue-pro << 'EOSQL'
SELECT 
    '职业字典' as category,
    COUNT(*) as count 
FROM system_dict_data 
WHERE dict_type = 'aicrm_occupation'
UNION ALL
SELECT 
    '所属行业字典' as category,
    COUNT(*) as count 
FROM system_dict_data 
WHERE dict_type = 'aicrm_industry';
EOSQL
```

期望输出：
```
+------------------+-------+
| category         | count |
+------------------+-------+
| 职业字典         |     7 |
| 所属行业字典     |    10 |
+------------------+-------+
```

### API 验证

访问以下 URL 检查字典数据（需要登录后获取 token）：

```
GET http://localhost:48080/admin-api/system/dict-data/type?type=aicrm_occupation
GET http://localhost:48080/admin-api/system/dict-data/type?type=aicrm_industry
```

## 相关文件

- 字典数据脚本：`docs/intelligent-practice/practice_virtual_customer_dict.sql`
- 前端表单代码：`frontend/apps/web-antd/src/views/aicrm/practicevirtualcustomer/data.ts`
- 后端字典常量：`backend/ynet-module-practice/src/main/java/cn/iocoder/yudao/module/aicrm/enums/CrmDictTypeConstants.java`
