-- 零售客户表补充字段 SQL
-- 基于老版本 acrm_f_ci_person 表对比生成
-- 执行前请备份数据库

USE `ruoyi-vue-pro`;

-- ============================================
-- 1. 个人基本信息相关字段
-- ============================================

-- 曾用名
ALTER TABLE crm_retail_customer ADD COLUMN used_name VARCHAR(70) DEFAULT NULL COMMENT '曾用名' AFTER nickname;

-- 姓氏
ALTER TABLE crm_retail_customer ADD COLUMN surname VARCHAR(20) DEFAULT NULL COMMENT '姓氏' AFTER used_name;

-- 拼音全名
ALTER TABLE crm_retail_customer ADD COLUMN pinyin_name VARCHAR(80) DEFAULT NULL COMMENT '拼音全名' AFTER surname;

-- 拼音缩写
ALTER TABLE crm_retail_customer ADD COLUMN pinyin_abbr VARCHAR(20) DEFAULT NULL COMMENT '拼音缩写' AFTER pinyin_name;

-- 人员称谓
ALTER TABLE crm_retail_customer ADD COLUMN person_title VARCHAR(20) DEFAULT NULL COMMENT '人员称谓(先生/女士/小姐等)' AFTER pinyin_abbr;

-- 公民身份
ALTER TABLE crm_retail_customer ADD COLUMN citizenship VARCHAR(20) DEFAULT NULL COMMENT '公民身份' AFTER nationality;

-- 出生地
ALTER TABLE crm_retail_customer ADD COLUMN birth_locale VARCHAR(50) DEFAULT NULL COMMENT '出生地' AFTER birthday;

-- 户口类型
ALTER TABLE crm_retail_customer ADD COLUMN household_type VARCHAR(20) DEFAULT NULL COMMENT '户口类型(农业/非农业)' AFTER domicile_place;

-- 健康状况
ALTER TABLE crm_retail_customer ADD COLUMN health_status VARCHAR(20) DEFAULT NULL COMMENT '健康状况' AFTER marital_status;

-- 政治面貌
ALTER TABLE crm_retail_customer ADD COLUMN political_status VARCHAR(20) DEFAULT NULL COMMENT '政治面貌' AFTER religion;

-- 年龄段
ALTER TABLE crm_retail_customer ADD COLUMN age_range VARCHAR(2) DEFAULT NULL COMMENT '年龄段' AFTER age;

-- ============================================
-- 2. 教育工作相关字段
-- ============================================

-- 毕业学校
ALTER TABLE crm_retail_customer ADD COLUMN graduate_school VARCHAR(80) DEFAULT NULL COMMENT '毕业学校' AFTER degree;

-- 专业
ALTER TABLE crm_retail_customer ADD COLUMN major VARCHAR(80) DEFAULT NULL COMMENT '专业' AFTER graduate_school;

-- 毕业日期
ALTER TABLE crm_retail_customer ADD COLUMN graduation_date DATE DEFAULT NULL COMMENT '毕业日期' AFTER major;

-- 职业状态
ALTER TABLE crm_retail_customer ADD COLUMN career_status VARCHAR(20) DEFAULT NULL COMMENT '职业状态(在职/离职/退休等)' AFTER occupation_type;

-- 职业开始日期
ALTER TABLE crm_retail_customer ADD COLUMN career_start_date DATE DEFAULT NULL COMMENT '职业开始日期' AFTER career_status;

-- 单位性质
ALTER TABLE crm_retail_customer ADD COLUMN employer_type VARCHAR(20) DEFAULT NULL COMMENT '单位性质(国企/私企/外企等)' AFTER employer_name;

-- 年收入范围
ALTER TABLE crm_retail_customer ADD COLUMN annual_income_range VARCHAR(20) DEFAULT NULL COMMENT '年收入范围' AFTER annual_income;

-- 技术职称
ALTER TABLE crm_retail_customer ADD COLUMN technical_title VARCHAR(10) DEFAULT NULL COMMENT '技术职称' AFTER position;

-- 简历
ALTER TABLE crm_retail_customer ADD COLUMN resume VARCHAR(500) DEFAULT NULL COMMENT '简历' AFTER career_start_date;

-- ============================================
-- 3. 投资理财相关字段
-- ============================================

-- 基金持有
ALTER TABLE crm_retail_customer ADD COLUMN fund_holdings DECIMAL(15,2) DEFAULT NULL COMMENT '基金持有金额' AFTER assets;

-- 总投资
ALTER TABLE crm_retail_customer ADD COLUMN total_investment DECIMAL(15,2) DEFAULT NULL COMMENT '总投资金额' AFTER fund_holdings;

-- 投资性质
ALTER TABLE crm_retail_customer ADD COLUMN investment_nature VARCHAR(10) DEFAULT NULL COMMENT '投资性质' AFTER total_investment;

-- 持股金额
ALTER TABLE crm_retail_customer ADD COLUMN stock_holdings DECIMAL(15,2) DEFAULT NULL COMMENT '持股金额' AFTER investment_nature;

-- ============================================
-- 4. 客户关系相关字段
-- ============================================

-- 个人客户类型 (已有 retail_customer_type,可能重复,但保留以兼容老系统)
ALTER TABLE crm_retail_customer ADD COLUMN person_customer_type VARCHAR(5) DEFAULT NULL COMMENT '个人客户类型(老系统字段)' AFTER retail_customer_type;

-- 个人行为评价
ALTER TABLE crm_retail_customer ADD COLUMN person_conduct_eval VARCHAR(10) DEFAULT NULL COMMENT '个人行为评价' AFTER reputation_score;

-- 客户银行关系
ALTER TABLE crm_retail_customer ADD COLUMN customer_bank_relation VARCHAR(10) DEFAULT NULL COMMENT '客户银行关系' AFTER person_conduct_eval;

-- 公司关联度
ALTER TABLE crm_retail_customer ADD COLUMN company_relation_degree VARCHAR(10) DEFAULT NULL COMMENT '公司关联度' AFTER customer_bank_relation;

-- 个体类型
ALTER TABLE crm_retail_customer ADD COLUMN individual_type VARCHAR(10) DEFAULT NULL COMMENT '个体类型' AFTER company_relation_degree;

-- ============================================
-- 5. 其他信息字段
-- ============================================

-- 居住时长类型
ALTER TABLE crm_retail_customer ADD COLUMN residence_duration_type VARCHAR(10) DEFAULT NULL COMMENT '居住时长类型' AFTER residence_type;

-- 纳税情况
ALTER TABLE crm_retail_customer ADD COLUMN tax_payment VARCHAR(10) DEFAULT NULL COMMENT '纳税情况' AFTER annual_income_range;

-- 工资账户银行
ALTER TABLE crm_retail_customer ADD COLUMN salary_account_bank VARCHAR(10) DEFAULT NULL COMMENT '工资账户银行' AFTER payroll_company_name;

-- 开始日期
ALTER TABLE crm_retail_customer ADD COLUMN start_date DATE DEFAULT NULL COMMENT '开始日期' AFTER become_customer_date;

-- 登记结束日期
ALTER TABLE crm_retail_customer ADD COLUMN registration_end_date DATE DEFAULT NULL COMMENT '登记结束日期' AFTER start_date;

-- 登记开始日期
ALTER TABLE crm_retail_customer ADD COLUMN registration_start_date DATE DEFAULT NULL COMMENT '登记开始日期' AFTER registration_end_date;

-- 登记类型
ALTER TABLE crm_retail_customer ADD COLUMN registration_type VARCHAR(10) DEFAULT NULL COMMENT '登记类型' AFTER registration_start_date;

-- 登记号
ALTER TABLE crm_retail_customer ADD COLUMN registration_no VARCHAR(50) DEFAULT NULL COMMENT '登记号' AFTER registration_type;

-- ============================================
-- 6. 数据迁移相关字段 (用于追溯老系统数据)
-- ============================================

-- ETL日期
ALTER TABLE crm_retail_customer ADD COLUMN etl_date DATE DEFAULT NULL COMMENT 'ETL导入日期' AFTER ext_field3;

-- 交易序列号 (老系统)
ALTER TABLE crm_retail_customer ADD COLUMN old_tx_seq_no VARCHAR(32) DEFAULT NULL COMMENT '老系统交易序列号' AFTER etl_date;

-- 老系统客户ID (用于数据追溯)
ALTER TABLE crm_retail_customer ADD COLUMN old_cust_id VARCHAR(50) DEFAULT NULL COMMENT '老系统客户ID' AFTER old_tx_seq_no;

-- 最后更新系统 (老系统)
ALTER TABLE crm_retail_customer ADD COLUMN old_last_update_sys VARCHAR(20) DEFAULT NULL COMMENT '老系统最后更新系统' AFTER old_cust_id;

-- ============================================
-- 7. 添加索引 (提升查询性能)
-- ============================================

-- 拼音名称索引 (用于模糊查询)
CREATE INDEX idx_pinyin_name ON crm_retail_customer(pinyin_name);

-- 拼音缩写索引 (用于快速查询)
CREATE INDEX idx_pinyin_abbr ON crm_retail_customer(pinyin_abbr);

-- 毕业学校索引
CREATE INDEX idx_graduate_school ON crm_retail_customer(graduate_school);

-- 专业索引
CREATE INDEX idx_major ON crm_retail_customer(major);

-- 老系统客户ID索引 (用于数据追溯)
CREATE INDEX idx_old_cust_id ON crm_retail_customer(old_cust_id);

-- 注册号索引
CREATE INDEX idx_registration_no ON crm_retail_customer(registration_no);

-- ============================================
-- 说明
-- ============================================

-- 1. 系统字段说明:
--    - last_update_tm -> update_time (框架已有)
--    - last_update_user -> updater (框架已有)
--    - last_update_sys -> 添加为 old_last_update_sys (保留老系统信息)

-- 2. 字段命名规范:
--    - 遵循新系统下划线命名规范
--    - 部分字段名进行了优化以提高可读性
--    - 老系统特有字段添加 old_ 前缀

-- 3. 数据类型调整:
--    - int(11) -> DECIMAL(15,2) (金额字段)
--    - varchar -> VARCHAR (统一大写)
--    - date/datetime 保持不变

-- 4. 注释:
--    - 所有字段都添加了中文注释
--    - 部分字段提供了取值范围说明

-- 执行完成后,建议:
-- 1. 检查表结构: DESC crm_retail_customer;
-- 2. 验证索引: SHOW INDEX FROM crm_retail_customer;
-- 3. 更新相关的 Java 实体类和 VO 类
-- 4. 更新前端页面显示字段
