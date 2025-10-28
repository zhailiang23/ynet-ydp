-- ========================================
-- 为零售客户生成证件信息测试数据
-- 每个客户 1-3 条证件记录
-- ========================================

-- 清空旧数据（如果需要）
-- TRUNCATE TABLE crm_customer_identity;

-- ========================================
-- 客户 1: 张三 (男) - 3条证件
-- ========================================
INSERT INTO crm_customer_identity (
    id, customer_id, identity_type, identity_no, identity_name,
    issue_authority, issue_date, expiry_date, is_primary,
    identity_address, identity_front_url, identity_back_url,
    verification_status, verification_time,
    creator, create_time, updater, update_time, deleted, tenant_id
) VALUES
-- 身份证（主证件）
(1, 1, 'id_card', '110101198001011234', '张三',
 '北京市公安局朝阳分局', '2010-01-15', '2030-01-15', b'1',
 '北京市朝阳区建国路88号', NULL, NULL,
 2, '2025-01-15 10:00:00',
 '1', NOW(), '1', NOW(), b'0', 1),
-- 护照
(2, 1, 'passport', 'E12345678', '张三',
 '北京市出入境管理局', '2020-06-01', '2030-06-01', b'0',
 NULL, NULL, NULL,
 2, '2025-01-15 10:05:00',
 '1', NOW(), '1', NOW(), b'0', 1),
-- 驾驶证（使用户口簿类型代替）
(3, 1, 'household_register', '110101198001011234', '张三',
 '北京市公安局', '2015-03-01', '9999-12-31', b'0',
 '北京市朝阳区建国路88号', NULL, NULL,
 2, '2025-01-15 10:10:00',
 '1', NOW(), '1', NOW(), b'0', 1);

-- ========================================
-- 客户 2: 李四 (男) - 2条证件
-- ========================================
INSERT INTO crm_customer_identity (
    id, customer_id, identity_type, identity_no, identity_name,
    issue_authority, issue_date, expiry_date, is_primary,
    identity_address, identity_front_url, identity_back_url,
    verification_status, verification_time,
    creator, create_time, updater, update_time, deleted, tenant_id
) VALUES
-- 身份证（主证件）
(4, 2, 'id_card', '110101198502022345', '李四',
 '北京市公安局海淀分局', '2015-03-20', '2035-03-20', b'1',
 '北京市海淀区中关村大街1号', NULL, NULL,
 2, '2025-01-16 09:00:00',
 '1', NOW(), '1', NOW(), b'0', 1),
-- 护照
(5, 2, 'passport', 'E23456789', '李四',
 '北京市出入境管理局', '2021-08-15', '2031-08-15', b'0',
 NULL, NULL, NULL,
 2, '2025-01-16 09:05:00',
 '1', NOW(), '1', NOW(), b'0', 1);

-- ========================================
-- 客户 3: 王五 (女) - 1条证件
-- ========================================
INSERT INTO crm_customer_identity (
    id, customer_id, identity_type, identity_no, identity_name,
    issue_authority, issue_date, expiry_date, is_primary,
    identity_address, identity_front_url, identity_back_url,
    verification_status, verification_time,
    creator, create_time, updater, update_time, deleted, tenant_id
) VALUES
-- 身份证（主证件）
(6, 3, 'id_card', '110101199003033456', '王五',
 '北京市公安局东城分局', '2012-05-10', '2032-05-10', b'1',
 '北京市东城区王府井大街100号', NULL, NULL,
 2, '2025-01-17 08:30:00',
 '1', NOW(), '1', NOW(), b'0', 1);

-- ========================================
-- 客户 4: 赵六 (男) - 3条证件
-- ========================================
INSERT INTO crm_customer_identity (
    id, customer_id, identity_type, identity_no, identity_name,
    issue_authority, issue_date, expiry_date, is_primary,
    identity_address, identity_front_url, identity_back_url,
    verification_status, verification_time,
    creator, create_time, updater, update_time, deleted, tenant_id
) VALUES
-- 身份证（主证件）
(7, 4, 'id_card', '310101198804044567', '赵六',
 '上海市公安局浦东分局', '2018-07-01', '2038-07-01', b'1',
 '上海市浦东新区陆家嘴环路1000号', NULL, NULL,
 2, '2025-01-18 10:00:00',
 '1', NOW(), '1', NOW(), b'0', 1),
-- 护照
(8, 4, 'passport', 'E34567890', '赵六',
 '上海市出入境管理局', '2022-01-10', '2032-01-10', b'0',
 NULL, NULL, NULL,
 2, '2025-01-18 10:05:00',
 '1', NOW(), '1', NOW(), b'0', 1),
-- 港澳通行证
(9, 4, 'hk_macao_pass', 'C12345678', '赵六',
 '上海市出入境管理局', '2023-03-15', '2028-03-15', b'0',
 NULL, NULL, NULL,
 2, '2025-01-18 10:10:00',
 '1', NOW(), '1', NOW(), b'0', 1);

-- ========================================
-- 客户 5: 孙七 (女) - 2条证件
-- ========================================
INSERT INTO crm_customer_identity (
    id, customer_id, identity_type, identity_no, identity_name,
    issue_authority, issue_date, expiry_date, is_primary,
    identity_address, identity_front_url, identity_back_url,
    verification_status, verification_time,
    creator, create_time, updater, update_time, deleted, tenant_id
) VALUES
-- 身份证（主证件）
(10, 5, 'id_card', '440101199205055678', '孙七',
 '广州市公安局天河分局', '2014-09-20', '2034-09-20', b'1',
 '广州市天河区珠江新城花城大道1号', NULL, NULL,
 2, '2025-01-19 09:30:00',
 '1', NOW(), '1', NOW(), b'0', 1),
-- 台湾通行证
(11, 5, 'taiwan_pass', 'T12345678', '孙七',
 '广州市出入境管理局', '2023-06-01', '2028-06-01', b'0',
 NULL, NULL, NULL,
 2, '2025-01-19 09:35:00',
 '1', NOW(), '1', NOW(), b'0', 1);

-- ========================================
-- 客户 6: 周八 (女) - 1条证件
-- ========================================
INSERT INTO crm_customer_identity (
    id, customer_id, identity_type, identity_no, identity_name,
    issue_authority, issue_date, expiry_date, is_primary,
    identity_address, identity_front_url, identity_back_url,
    verification_status, verification_time,
    creator, create_time, updater, update_time, deleted, tenant_id
) VALUES
-- 身份证（主证件）
(12, 6, 'id_card', '330101199506066789', '周八',
 '杭州市公安局西湖分局', '2016-11-05', '2036-11-05', b'1',
 '浙江省杭州市西湖区文一西路1号', NULL, NULL,
 2, '2025-01-20 08:00:00',
 '1', NOW(), '1', NOW(), b'0', 1);

-- ========================================
-- 客户 7: 吴九 (男) - 2条证件
-- ========================================
INSERT INTO crm_customer_identity (
    id, customer_id, identity_type, identity_no, identity_name,
    issue_authority, issue_date, expiry_date, is_primary,
    identity_address, identity_front_url, identity_back_url,
    verification_status, verification_time,
    creator, create_time, updater, update_time, deleted, tenant_id
) VALUES
-- 身份证（主证件）
(13, 7, 'id_card', '420101198707077890', '吴九',
 '武汉市公安局江汉分局', '2017-04-12', '2037-04-12', b'1',
 '湖北省武汉市江汉区解放大道1号', NULL, NULL,
 2, '2025-01-21 10:30:00',
 '1', NOW(), '1', NOW(), b'0', 1),
-- 护照
(14, 7, 'passport', 'E45678901', '吴九',
 '武汉市出入境管理局', '2022-05-20', '2032-05-20', b'0',
 NULL, NULL, NULL,
 2, '2025-01-21 10:35:00',
 '1', NOW(), '1', NOW(), b'0', 1);

-- ========================================
-- 客户 8: 郑十 (男) - 3条证件
-- ========================================
INSERT INTO crm_customer_identity (
    id, customer_id, identity_type, identity_no, identity_name,
    issue_authority, issue_date, expiry_date, is_primary,
    identity_address, identity_front_url, identity_back_url,
    verification_status, verification_time,
    creator, create_time, updater, update_time, deleted, tenant_id
) VALUES
-- 身份证（主证件）
(15, 8, 'id_card', '510101198308088901', '郑十',
 '成都市公安局武侯分局', '2013-08-15', '2033-08-15', b'1',
 '四川省成都市武侯区人民南路1号', NULL, NULL,
 2, '2025-01-22 09:00:00',
 '1', NOW(), '1', NOW(), b'0', 1),
-- 护照
(16, 8, 'passport', 'E56789012', '郑十',
 '成都市出入境管理局', '2021-12-01', '2031-12-01', b'0',
 NULL, NULL, NULL,
 2, '2025-01-22 09:05:00',
 '1', NOW(), '1', NOW(), b'0', 1),
-- 军官证（模拟退役军人）
(17, 8, 'military_officer', 'J1234567', '郑十',
 '中国人民解放军', '2005-09-01', '2015-09-01', b'0',
 NULL, NULL, NULL,
 2, '2025-01-22 09:10:00',
 '1', NOW(), '1', NOW(), b'0', 1);

-- ========================================
-- 客户 9: 钱十一 (女) - 2条证件
-- ========================================
INSERT INTO crm_customer_identity (
    id, customer_id, identity_type, identity_no, identity_name,
    issue_authority, issue_date, expiry_date, is_primary,
    identity_address, identity_front_url, identity_back_url,
    verification_status, verification_time,
    creator, create_time, updater, update_time, deleted, tenant_id
) VALUES
-- 身份证（主证件）
(18, 9, 'id_card', '610101199109099012', '钱十一',
 '西安市公安局雁塔分局', '2019-02-28', '2039-02-28', b'1',
 '陕西省西安市雁塔区长安路1号', NULL, NULL,
 2, '2025-01-23 08:30:00',
 '1', NOW(), '1', NOW(), b'0', 1),
-- 港澳居民居住证
(19, 9, 'hk_macao_residence', 'H12345678', '钱十一',
 '西安市公安局', '2023-08-10', '2033-08-10', b'0',
 '陕西省西安市雁塔区长安路1号', NULL, NULL,
 2, '2025-01-23 08:35:00',
 '1', NOW(), '1', NOW(), b'0', 1);

-- ========================================
-- 客户 10: 冯十二 (男) - 1条证件
-- ========================================
INSERT INTO crm_customer_identity (
    id, customer_id, identity_type, identity_no, identity_name,
    issue_authority, issue_date, expiry_date, is_primary,
    identity_address, identity_front_url, identity_back_url,
    verification_status, verification_time,
    creator, create_time, updater, update_time, deleted, tenant_id
) VALUES
-- 身份证（主证件）
(20, 10, 'id_card', '320101198610101123', '冯十二',
 '南京市公安局鼓楼分局', '2016-12-10', '2036-12-10', b'1',
 '江苏省南京市鼓楼区中山路1号', NULL, NULL,
 2, '2025-01-24 10:00:00',
 '1', NOW(), '1', NOW(), b'0', 1);

-- ========================================
-- 数据统计查询
-- ========================================
-- SELECT
--     '客户证件信息统计' as title,
--     COUNT(*) as total_records,
--     COUNT(DISTINCT customer_id) as total_customers,
--     SUM(CASE WHEN is_primary = b'1' THEN 1 ELSE 0 END) as primary_count,
--     SUM(CASE WHEN identity_type = 'id_card' THEN 1 ELSE 0 END) as id_card_count,
--     SUM(CASE WHEN identity_type = 'passport' THEN 1 ELSE 0 END) as passport_count,
--     SUM(CASE WHEN verification_status = 2 THEN 1 ELSE 0 END) as verified_count
-- FROM crm_customer_identity;

-- ========================================
-- 按客户分组统计
-- ========================================
-- SELECT
--     c.customer_no,
--     c.customer_name,
--     COUNT(ci.id) as identity_count,
--     GROUP_CONCAT(ci.identity_type ORDER BY ci.is_primary DESC SEPARATOR ', ') as identity_types
-- FROM crm_customer c
-- LEFT JOIN crm_customer_identity ci ON c.id = ci.customer_id
-- WHERE c.customer_type = 1
-- GROUP BY c.id, c.customer_no, c.customer_name
-- ORDER BY c.id;
