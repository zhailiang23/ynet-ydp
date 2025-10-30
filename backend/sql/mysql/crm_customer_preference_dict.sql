-- 客户偏好相关字典数据
-- 字典命名规范：字典名称以 AICRM 开头，字典类型以 aicrm_ 开头

-- 1. 投资类型字典
INSERT INTO system_dict_type (name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES ('AICRM - 投资类型', 'aicrm_investment_type', 0, '客户偏好的投资类型选项', '1', NOW(), '1', NOW(), 0, NULL);

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted)
VALUES
(1, '股票', '1', 'aicrm_investment_type', 0, 'default', '', '股票投资', '1', NOW(), '1', NOW(), 0),
(2, '基金', '2', 'aicrm_investment_type', 0, 'default', '', '基金投资', '1', NOW(), '1', NOW(), 0),
(3, '债券', '3', 'aicrm_investment_type', 0, 'default', '', '债券投资', '1', NOW(), '1', NOW(), 0),
(4, '理财产品', '4', 'aicrm_investment_type', 0, 'default', '', '银行理财产品', '1', NOW(), '1', NOW(), 0),
(5, '保险', '5', 'aicrm_investment_type', 0, 'default', '', '保险类产品', '1', NOW(), '1', NOW(), 0),
(6, '贵金属', '6', 'aicrm_investment_type', 0, 'default', '', '黄金等贵金属', '1', NOW(), '1', NOW(), 0),
(7, '外汇', '7', 'aicrm_investment_type', 0, 'default', '', '外汇交易', '1', NOW(), '1', NOW(), 0),
(8, '期货', '8', 'aicrm_investment_type', 0, 'default', '', '期货投资', '1', NOW(), '1', NOW(), 0);

-- 2. 品牌类型字典
INSERT INTO system_dict_type (name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES ('AICRM - 品牌类型', 'aicrm_brand_type', 0, '客户偏好的品牌类型选项', '1', NOW(), '1', NOW(), 0, NULL);

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted)
VALUES
(1, '国际知名品牌', '1', 'aicrm_brand_type', 0, 'default', '', '国际知名品牌', '1', NOW(), '1', NOW(), 0),
(2, '国内知名品牌', '2', 'aicrm_brand_type', 0, 'default', '', '国内知名品牌', '1', NOW(), '1', NOW(), 0),
(3, '本地品牌', '3', 'aicrm_brand_type', 0, 'default', '', '本地品牌', '1', NOW(), '1', NOW(), 0),
(4, '高端品牌', '4', 'aicrm_brand_type', 0, 'default', '', '高端品牌', '1', NOW(), '1', NOW(), 0),
(5, '性价比品牌', '5', 'aicrm_brand_type', 0, 'default', '', '性价比品牌', '1', NOW(), '1', NOW(), 0);

-- 3. 理财服务字典
INSERT INTO system_dict_type (name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES ('AICRM - 理财服务', 'aicrm_financial_service', 0, '客户希望得到的理财服务选项', '1', NOW(), '1', NOW(), 0, NULL);

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted)
VALUES
(1, '资产配置建议', '1', 'aicrm_financial_service', 0, 'default', '', '资产配置建议', '1', NOW(), '1', NOW(), 0),
(2, '投资组合管理', '2', 'aicrm_financial_service', 0, 'default', '', '投资组合管理', '1', NOW(), '1', NOW(), 0),
(3, '风险评估', '3', 'aicrm_financial_service', 0, 'default', '', '风险评估', '1', NOW(), '1', NOW(), 0),
(4, '市场分析报告', '4', 'aicrm_financial_service', 0, 'default', '', '市场分析报告', '1', NOW(), '1', NOW(), 0),
(5, '理财规划', '5', 'aicrm_financial_service', 0, 'default', '', '理财规划', '1', NOW(), '1', NOW(), 0),
(6, '税务筹划', '6', 'aicrm_financial_service', 0, 'default', '', '税务筹划', '1', NOW(), '1', NOW(), 0),
(7, '保险规划', '7', 'aicrm_financial_service', 0, 'default', '', '保险规划', '1', NOW(), '1', NOW(), 0),
(8, '退休规划', '8', 'aicrm_financial_service', 0, 'default', '', '退休规划', '1', NOW(), '1', NOW(), 0);

-- 4. 联系方式字典
INSERT INTO system_dict_type (name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES ('AICRM - 联系方式', 'aicrm_contact_method', 0, '客户希望理财经理的联系方式选项', '1', NOW(), '1', NOW(), 0, NULL);

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted)
VALUES
(1, '电话', '1', 'aicrm_contact_method', 0, 'default', '', '电话联系', '1', NOW(), '1', NOW(), 0),
(2, '短信', '2', 'aicrm_contact_method', 0, 'default', '', '短信联系', '1', NOW(), '1', NOW(), 0),
(3, '微信', '3', 'aicrm_contact_method', 0, 'default', '', '微信联系', '1', NOW(), '1', NOW(), 0),
(4, '邮件', '4', 'aicrm_contact_method', 0, 'default', '', '邮件联系', '1', NOW(), '1', NOW(), 0),
(5, '面访', '5', 'aicrm_contact_method', 0, 'default', '', '面对面拜访', '1', NOW(), '1', NOW(), 0),
(6, 'QQ', '6', 'aicrm_contact_method', 0, 'default', '', 'QQ联系', '1', NOW(), '1', NOW(), 0);

-- 5. 沙龙活动字典
INSERT INTO system_dict_type (name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES ('AICRM - 沙龙活动', 'aicrm_salon_activity', 0, '客户希望参加的沙龙活动选项', '1', NOW(), '1', NOW(), 0, NULL);

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted)
VALUES
(1, '投资策略讲座', '1', 'aicrm_salon_activity', 0, 'default', '', '投资策略讲座', '1', NOW(), '1', NOW(), 0),
(2, '理财产品推介会', '2', 'aicrm_salon_activity', 0, 'default', '', '理财产品推介会', '1', NOW(), '1', NOW(), 0),
(3, '财富管理论坛', '3', 'aicrm_salon_activity', 0, 'default', '', '财富管理论坛', '1', NOW(), '1', NOW(), 0),
(4, '高端客户酒会', '4', 'aicrm_salon_activity', 0, 'default', '', '高端客户酒会', '1', NOW(), '1', NOW(), 0),
(5, '文化艺术活动', '5', 'aicrm_salon_activity', 0, 'default', '', '文化艺术活动', '1', NOW(), '1', NOW(), 0),
(6, '健康养生讲座', '6', 'aicrm_salon_activity', 0, 'default', '', '健康养生讲座', '1', NOW(), '1', NOW(), 0),
(7, '高尔夫活动', '7', 'aicrm_salon_activity', 0, 'default', '', '高尔夫活动', '1', NOW(), '1', NOW(), 0),
(8, '亲子活动', '8', 'aicrm_salon_activity', 0, 'default', '', '亲子活动', '1', NOW(), '1', NOW(), 0);

-- 6. 兴趣爱好字典
INSERT INTO system_dict_type (name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES ('AICRM - 兴趣爱好', 'aicrm_interest_hobby', 0, '客户个人兴趣爱好选项', '1', NOW(), '1', NOW(), 0, NULL);

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted)
VALUES
(1, '运动健身', '1', 'aicrm_interest_hobby', 0, 'default', '', '运动健身', '1', NOW(), '1', NOW(), 0),
(2, '旅游', '2', 'aicrm_interest_hobby', 0, 'default', '', '旅游', '1', NOW(), '1', NOW(), 0),
(3, '阅读', '3', 'aicrm_interest_hobby', 0, 'default', '', '阅读', '1', NOW(), '1', NOW(), 0),
(4, '音乐', '4', 'aicrm_interest_hobby', 0, 'default', '', '音乐', '1', NOW(), '1', NOW(), 0),
(5, '书法绘画', '5', 'aicrm_interest_hobby', 0, 'default', '', '书法绘画', '1', NOW(), '1', NOW(), 0),
(6, '收藏', '6', 'aicrm_interest_hobby', 0, 'default', '', '收藏', '1', NOW(), '1', NOW(), 0),
(7, '美食', '7', 'aicrm_interest_hobby', 0, 'default', '', '美食', '1', NOW(), '1', NOW(), 0),
(8, '摄影', '8', 'aicrm_interest_hobby', 0, 'default', '', '摄影', '1', NOW(), '1', NOW(), 0),
(9, '园艺', '9', 'aicrm_interest_hobby', 0, 'default', '', '园艺', '1', NOW(), '1', NOW(), 0),
(10, '棋牌', '10', 'aicrm_interest_hobby', 0, 'default', '', '棋牌', '1', NOW(), '1', NOW(), 0);

-- 7. 联系时间字典
INSERT INTO system_dict_type (name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES ('AICRM - 联系时间', 'aicrm_contact_time', 0, '客户希望联系的时间选项', '1', NOW(), '1', NOW(), 0, NULL);

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted)
VALUES
(1, '工作日上午', '1', 'aicrm_contact_time', 0, 'default', '', '工作日上午(9:00-12:00)', '1', NOW(), '1', NOW(), 0),
(2, '工作日下午', '2', 'aicrm_contact_time', 0, 'default', '', '工作日下午(14:00-18:00)', '1', NOW(), '1', NOW(), 0),
(3, '工作日晚上', '3', 'aicrm_contact_time', 0, 'default', '', '工作日晚上(18:00-21:00)', '1', NOW(), '1', NOW(), 0),
(4, '周末上午', '4', 'aicrm_contact_time', 0, 'default', '', '周末上午(9:00-12:00)', '1', NOW(), '1', NOW(), 0),
(5, '周末下午', '5', 'aicrm_contact_time', 0, 'default', '', '周末下午(14:00-18:00)', '1', NOW(), '1', NOW(), 0),
(6, '周末晚上', '6', 'aicrm_contact_time', 0, 'default', '', '周末晚上(18:00-21:00)', '1', NOW(), '1', NOW(), 0),
(7, '节假日', '7', 'aicrm_contact_time', 0, 'default', '', '节假日', '1', NOW(), '1', NOW(), 0);

-- 8. 投资周期字典
INSERT INTO system_dict_type (name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES ('AICRM - 投资周期', 'aicrm_investment_period', 0, '客户投资周期偏好选项', '1', NOW(), '1', NOW(), 0, NULL);

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted)
VALUES
(1, '短期(1年以内)', '1', 'aicrm_investment_period', 0, 'default', '', '短期投资周期', '1', NOW(), '1', NOW(), 0),
(2, '中短期(1-3年)', '2', 'aicrm_investment_period', 0, 'default', '', '中短期投资周期', '1', NOW(), '1', NOW(), 0),
(3, '中期(3-5年)', '3', 'aicrm_investment_period', 0, 'default', '', '中期投资周期', '1', NOW(), '1', NOW(), 0),
(4, '中长期(5-10年)', '4', 'aicrm_investment_period', 0, 'default', '', '中长期投资周期', '1', NOW(), '1', NOW(), 0),
(5, '长期(10年以上)', '5', 'aicrm_investment_period', 0, 'default', '', '长期投资周期', '1', NOW(), '1', NOW(), 0);
