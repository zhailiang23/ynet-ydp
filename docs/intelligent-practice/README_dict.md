# 虚拟客户字典数据管理

## 脚本说明

### 1. practice_virtual_customer_dict.sql（已废弃）
- **不要直接使用此脚本**，会导致重复插入数据
- 仅供参考数据结构

### 2. practice_virtual_customer_dict_safe.sql（推荐使用）
- **推荐使用此脚本**
- 会先清理旧数据，再插入新数据
- 可以安全地重复执行，不会产生重复数据

### 3. clean_duplicate_dict.sql
- 清理重复数据专用脚本
- 如果不小心产生了重复数据，使用此脚本清理

## 使用方法

### 首次安装或更新字典数据

```bash
# 方式1: 命令行
mysql -h127.0.0.1 -P3306 -uroot -p123456 ruoyi-vue-pro < practice_virtual_customer_dict_safe.sql

# 方式2: 在数据库管理工具中执行
# 打开 practice_virtual_customer_dict_safe.sql 文件
# 复制全部内容到数据库管理工具执行
```

### 清理重复数据

如果已经产生了重复数据：

```bash
mysql -h127.0.0.1 -P3306 -uroot -p123456 ruoyi-vue-pro < clean_duplicate_dict.sql
```

## 字典列表

1. **性别** (aicrm_gender)
   - 男、女

2. **职业类型** (aicrm_occupation)
   - 企业高管、企业主、专业人士、公务员、自由职业者、退休人员、其他

3. **所属行业** (aicrm_industry)
   - 金融、制造业、房地产、互联网/科技、医疗健康、教育培训、批发零售、服务业、政府机关、其他

4. **性格类型** (aicrm_personality_type)
   - 理性型、感性型、社交型、务实型、创新型

5. **风险偏好** (aicrm_risk_preference)
   - 保守型、稳健型、平衡型、成长型、激进型

## 验证数据

执行以下 SQL 验证数据是否正确：

```sql
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
FROM system_dict_data WHERE dict_type = 'aicrm_risk_preference';

-- 检查是否有重复数据
SELECT dict_type, label, COUNT(*) as count 
FROM system_dict_data 
WHERE dict_type IN ('aicrm_gender', 'aicrm_occupation', 'aicrm_industry', 'aicrm_personality_type', 'aicrm_risk_preference')
GROUP BY dict_type, label
HAVING count > 1;
```

期望结果：
- 性别字典：2 条
- 职业字典：7 条
- 所属行业字典：10 条
- 性格类型字典：5 条
- 风险偏好字典：5 条
- 无重复数据
