-- 惠农贷款目标客户热力图测试数据
-- 创建 100 条测试数据，分布在郑州市及周边地区

-- 1. 首先确保有足够的站点和网格数据
INSERT INTO grid_huinong_station (id, station_code, station_name, station_type, location, address, grid_id, grid_marketing_flag, status, data_source, create_time, update_time, creator, updater, deleted, tenant_id)
VALUES
-- 郑州市区站点（必选）
(101, 'ZZ001', '郑州中原区站', 'HUINONG', ST_GeomFromText('POINT(34.748207 113.612912)', 4326), '郑州市中原区', 101, 'REQUIRED', 'ACTIVE', 'MANUAL', NOW(), NOW(), '1', '1', 0, 1),
(102, 'ZZ002', '郑州二七区站', 'HUINONG', ST_GeomFromText('POINT(34.724062 113.640284)', 4326), '郑州市二七区', 102, 'REQUIRED', 'ACTIVE', 'MANUAL', NOW(), NOW(), '1', '1', 0, 1),
(103, 'ZZ003', '郑州金水区站', 'HUINONG', ST_GeomFromText('POINT(34.764984 113.673912)', 4326), '郑州市金水区', 103, 'REQUIRED', 'ACTIVE', 'MANUAL', NOW(), NOW(), '1', '1', 0, 1),
(104, 'ZZ004', '郑州惠济区站', 'HUINONG', ST_GeomFromText('POINT(34.857234 113.603281)', 4326), '郑州市惠济区', 104, 'OPTIONAL', 'ACTIVE', 'MANUAL', NOW(), NOW(), '1', '1', 0, 1),
(105, 'ZZ005', '郑州管城区站', 'HUINONG', ST_GeomFromText('POINT(34.751173 113.707845)', 4326), '郑州市管城区', 105, 'REQUIRED', 'ACTIVE', 'MANUAL', NOW(), NOW(), '1', '1', 0, 1),
-- 新郑、中牟站点（可选）
(106, 'XZ001', '新郑薛店站', 'HUINONG', ST_GeomFromText('POINT(34.397281 113.712345)', 4326), '新郑市薛店镇', 106, 'OPTIONAL', 'ACTIVE', 'MANUAL', NOW(), NOW(), '1', '1', 0, 1),
(107, 'ZM001', '中牟官渡站', 'HUINONG', ST_GeomFromText('POINT(34.725891 113.973456)', 4326), '中牟县官渡镇', 107, 'OPTIONAL', 'ACTIVE', 'MANUAL', NOW(), NOW(), '1', '1', 0, 1),
-- 荥阳、巩义站点（必选）
(108, 'XY001', '荥阳豫龙站', 'HUINONG', ST_GeomFromText('POINT(34.791234 113.393456)', 4326), '荥阳市豫龙镇', 108, 'REQUIRED', 'ACTIVE', 'MANUAL', NOW(), NOW(), '1', '1', 0, 1),
(109, 'GY001', '巩义站街站', 'HUINONG', ST_GeomFromText('POINT(34.762345 112.983456)', 4326), '巩义市站街镇', 109, 'REQUIRED', 'ACTIVE', 'MANUAL', NOW(), NOW(), '1', '1', 0, 1),
-- 登封站点（可选）
(110, 'DF001', '登封大金店站', 'HUINONG', ST_GeomFromText('POINT(34.454321 113.033456)', 4326), '登封市大金店镇', 110, 'OPTIONAL', 'ACTIVE', 'MANUAL', NOW(), NOW(), '1', '1', 0, 1)
ON DUPLICATE KEY UPDATE station_name = VALUES(station_name);

-- 2. 创建 100 个客户基本信息
-- 使用存储过程批量插入
DELIMITER //

DROP PROCEDURE IF EXISTS create_test_customers //

CREATE PROCEDURE create_test_customers()
BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE customer_id BIGINT;
    DECLARE grid_id_val INT;
    DECLARE lat_val DECIMAL(10,6);
    DECLARE lng_val DECIMAL(10,6);
    DECLARE customer_name_val VARCHAR(50);
    DECLARE category_val VARCHAR(50);
    DECLARE is_applied_val BOOLEAN;
    DECLARE is_approved_val BOOLEAN;
    DECLARE loan_amount_val DECIMAL(15,2);
    DECLARE loan_balance_val DECIMAL(15,2);

    -- 客户类别数组
    DECLARE categories VARCHAR(500) DEFAULT '传统种植户,传统养殖户,乡村新型经营主体,农资经营主体,个体工商户,乡村服务业,工薪阶层,乡村特定需求,其他（返乡创业、电商等）';

    WHILE i <= 100 DO
        -- 随机选择一个网格（对应站点）
        SET grid_id_val = 101 + (i MOD 10);

        -- 根据网格生成位置（在站点附近随机分布，半径约5公里）
        CASE grid_id_val
            WHEN 101 THEN SET lat_val = 34.748207 + (RAND() - 0.5) * 0.09, lng_val = 113.612912 + (RAND() - 0.5) * 0.09;
            WHEN 102 THEN SET lat_val = 34.724062 + (RAND() - 0.5) * 0.09, lng_val = 113.640284 + (RAND() - 0.5) * 0.09;
            WHEN 103 THEN SET lat_val = 34.764984 + (RAND() - 0.5) * 0.09, lng_val = 113.673912 + (RAND() - 0.5) * 0.09;
            WHEN 104 THEN SET lat_val = 34.857234 + (RAND() - 0.5) * 0.09, lng_val = 113.603281 + (RAND() - 0.5) * 0.09;
            WHEN 105 THEN SET lat_val = 34.751173 + (RAND() - 0.5) * 0.09, lng_val = 113.707845 + (RAND() - 0.5) * 0.09;
            WHEN 106 THEN SET lat_val = 34.397281 + (RAND() - 0.5) * 0.09, lng_val = 113.712345 + (RAND() - 0.5) * 0.09;
            WHEN 107 THEN SET lat_val = 34.725891 + (RAND() - 0.5) * 0.09, lng_val = 113.973456 + (RAND() - 0.5) * 0.09;
            WHEN 108 THEN SET lat_val = 34.791234 + (RAND() - 0.5) * 0.09, lng_val = 113.393456 + (RAND() - 0.5) * 0.09;
            WHEN 109 THEN SET lat_val = 34.762345 + (RAND() - 0.5) * 0.09, lng_val = 112.983456 + (RAND() - 0.5) * 0.09;
            ELSE SET lat_val = 34.454321 + (RAND() - 0.5) * 0.09, lng_val = 113.033456 + (RAND() - 0.5) * 0.09;
        END CASE;

        -- 生成客户姓名
        SET customer_name_val = CONCAT('测试客户', LPAD(i, 3, '0'));

        -- 随机选择客户类别
        SET category_val = SUBSTRING_INDEX(SUBSTRING_INDEX(categories, ',', FLOOR(1 + RAND() * 9)), ',', -1);

        -- 插入客户基本信息
        INSERT INTO grid_customer (grid_id, customer_type, customer_name, id_card, phone, phone_verified, address, location, status, source, create_time, update_time, creator, updater, deleted, tenant_id)
        VALUES (
            grid_id_val,
            category_val,
            customer_name_val,
            CONCAT('4101', YEAR(NOW()), LPAD(i, 10, '0')),
            CONCAT('139', LPAD(i, 8, '0')),
            1,
            CONCAT('郑州市测试地址', i, '号'),
            ST_GeomFromText(CONCAT('POINT(', lat_val, ' ', lng_val, ')'), 4326),
            'ACTIVE',
            'IMPORT',
            NOW(),
            NOW(),
            '1',
            '1',
            0,
            1
        );

        SET customer_id = LAST_INSERT_ID();

        -- 随机生成贷款相关数据
        SET is_applied_val = (RAND() > 0.3); -- 70% 已进件
        SET is_approved_val = IF(is_applied_val AND RAND() > 0.2, 1, 0); -- 进件中 80% 已批准
        SET loan_amount_val = FLOOR(50000 + RAND() * 450000); -- 5万-50万
        SET loan_balance_val = IF(is_approved_val, loan_amount_val * (0.3 + RAND() * 0.7), 0); -- 30%-100% 余额

        -- 插入贷款信息
        INSERT INTO grid_huinong_customer_loan (
            customer_id, customer_category, subdivision_type, business_address, gender,
            customer_situation, business_years, current_financing, credit_demand,
            demand_month, demand_period, business_progress, customer_source,
            is_applied, apply_time, is_approved, approve_time,
            loan_product_name, loan_amount, credit_limit, loan_balance, overdue_status,
            create_time, update_time, creator, updater, deleted, tenant_id
        )
        VALUES (
            customer_id,
            category_val,
            CASE FLOOR(RAND() * 3)
                WHEN 0 THEN '规模经营'
                WHEN 1 THEN '小微经营'
                ELSE '个体经营'
            END,
            CONCAT('郑州市经营地址', i, '号'),
            IF(RAND() > 0.5, '男', '女'),
            CASE FLOOR(RAND() * 3)
                WHEN 0 THEN '信用良好'
                WHEN 1 THEN '有贷款记录'
                ELSE '首次贷款'
            END,
            FLOOR(1 + RAND() * 20), -- 1-20年
            CASE FLOOR(RAND() * 3)
                WHEN 0 THEN '无融资'
                WHEN 1 THEN '有银行贷款'
                ELSE '有民间借贷'
            END,
            CONCAT(FLOOR(5 + RAND() * 50), '万元'),
            FLOOR(1 + RAND() * 12), -- 1-12月
            CASE FLOOR(RAND() * 3)
                WHEN 0 THEN '上旬'
                WHEN 1 THEN '中旬'
                ELSE '下旬'
            END,
            IF(is_approved_val, '已放款', IF(is_applied_val, '审批中', '建档完成')),
            IF(RAND() > 0.5, '行内客户', '外拓客户'),
            is_applied_val,
            IF(is_applied_val, DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 90) DAY), NULL),
            is_approved_val,
            IF(is_approved_val, DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 60) DAY), NULL),
            '惠农e贷',
            loan_amount_val,
            loan_amount_val * 1.2,
            loan_balance_val,
            IF(is_approved_val AND RAND() > 0.9, '逾期', '正常'),
            NOW(),
            NOW(),
            '1',
            '1',
            0,
            1
        );

        SET i = i + 1;
    END WHILE;
END //

DELIMITER ;

-- 3. 执行存储过程创建测试数据
CALL create_test_customers();

-- 4. 清理存储过程
DROP PROCEDURE IF EXISTS create_test_customers;

-- 5. 查询统计信息
SELECT '数据创建完成' AS message;
SELECT
    '总客户数' AS metric,
    COUNT(*) AS value
FROM grid_customer
WHERE customer_name LIKE '测试客户%'
UNION ALL
SELECT
    '总贷款记录数' AS metric,
    COUNT(*) AS value
FROM grid_huinong_customer_loan
WHERE customer_id IN (SELECT id FROM grid_customer WHERE customer_name LIKE '测试客户%')
UNION ALL
SELECT
    '已进件客户数' AS metric,
    COUNT(*) AS value
FROM grid_huinong_customer_loan
WHERE is_applied = 1 AND customer_id IN (SELECT id FROM grid_customer WHERE customer_name LIKE '测试客户%')
UNION ALL
SELECT
    '已批准客户数' AS metric,
    COUNT(*) AS value
FROM grid_huinong_customer_loan
WHERE is_approved = 1 AND customer_id IN (SELECT id FROM grid_customer WHERE customer_name LIKE '测试客户%')
UNION ALL
SELECT
    '贷款余额总计' AS metric,
    CAST(SUM(loan_balance) AS UNSIGNED) AS value
FROM grid_huinong_customer_loan
WHERE customer_id IN (SELECT id FROM grid_customer WHERE customer_name LIKE '测试客户%');
