-- 修复客户表中的字典数据
-- 将中文标签转换为字典值

-- 1. 修复客户等级字段 (customer_level)
UPDATE crm_customer SET customer_level = 'normal' WHERE customer_level = '普通客户';
UPDATE crm_customer SET customer_level = 'vip' WHERE customer_level = 'VIP客户';
UPDATE crm_customer SET customer_level = 'gold' WHERE customer_level = '金卡客户';
UPDATE crm_customer SET customer_level = 'diamond' WHERE customer_level = '钻石卡客户';
UPDATE crm_customer SET customer_level = 'strategic' WHERE customer_level = '战略客户';
UPDATE crm_customer SET customer_level = 'key' WHERE customer_level = '重点客户';
-- 将未定义的等级映射到gold
UPDATE crm_customer SET customer_level = 'gold' WHERE customer_level = '白金卡客户';
UPDATE crm_customer SET customer_level = 'gold' WHERE customer_level = '银卡客户';

-- 2. 修复信用等级字段 (credit_level) - 已经是正确的值(AAA/AA/A等)，无需修改

-- 3. 修复客户来源字段 (customer_source)
UPDATE crm_customer SET customer_source = 'branch' WHERE customer_source = '网点开发';
UPDATE crm_customer SET customer_source = 'telemarketing' WHERE customer_source = '电话营销';
UPDATE crm_customer SET customer_source = 'online' WHERE customer_source = '网络营销';
UPDATE crm_customer SET customer_source = 'referral' WHERE customer_source IN ('老客介绍', '客户推荐');
UPDATE crm_customer SET customer_source = 'advertising' WHERE customer_source = '广告推广';
UPDATE crm_customer SET customer_source = 'exhibition' WHERE customer_source = '展会活动';
UPDATE crm_customer SET customer_source = 'partner' WHERE customer_source = '合作伙伴';
UPDATE crm_customer SET customer_source = 'other' WHERE customer_source IN ('其他渠道', '代发工资', '其他');
