-- ========================================================
-- 重构：将 grid_huinong_station 表合并到 grid_info 表
-- 执行日期：2025-12-23
-- 说明：将惠农站点管理功能迁移到统一的网格管理表
-- ========================================================

-- Phase 1: 添加新字段到 grid_info 表
-- ========================================================

ALTER TABLE grid_info
ADD COLUMN station_type VARCHAR(50) COMMENT '站点类型' AFTER grid_type,
ADD COLUMN grid_marketing_flag VARCHAR(20) DEFAULT 'OPTIONAL' COMMENT '网格营销站点标识: REQUIRED(必选)/OPTIONAL(可选)' AFTER status,
ADD COLUMN contact_person VARCHAR(100) COMMENT '联系人' AFTER grid_marketing_flag,
ADD COLUMN contact_phone VARCHAR(20) COMMENT '联系电话' AFTER contact_person,
ADD COLUMN data_source VARCHAR(20) DEFAULT 'MANUAL' COMMENT '数据来源: IMPORTED(导入)/MANUAL(手动)' AFTER contact_phone,
ADD COLUMN import_batch VARCHAR(50) COMMENT '导入批次号' AFTER data_source,
ADD COLUMN import_time DATETIME COMMENT '导入时间' AFTER import_batch;

-- Phase 2: 迁移数据从 grid_huinong_station 到 grid_info
-- ========================================================

-- 注意：这个迁移脚本假设 grid_huinong_station 中的每条记录都已经创建了对应的 grid_info 记录
-- 只需要更新 grid_info 中的新增字段

UPDATE grid_info gi
INNER JOIN grid_huinong_station hs ON gi.id = hs.grid_id
SET
    gi.grid_code = hs.station_code,
    gi.grid_name = hs.station_name,
    gi.station_type = hs.station_type,
    gi.location_name = hs.address,
    gi.center_point = hs.location,
    gi.manager_user_id = hs.specialist_id,
    gi.grid_marketing_flag = hs.grid_marketing_flag,
    gi.contact_person = hs.contact_person,
    gi.contact_phone = hs.contact_phone,
    gi.data_source = hs.data_source,
    gi.import_batch = hs.import_batch,
    gi.import_time = hs.import_time,
    gi.status = hs.status,
    gi.update_time = NOW()
WHERE gi.grid_type = 'HUINONG' AND hs.deleted = 0;

-- Phase 3: 验证数据迁移
-- ========================================================

-- 检查迁移的记录数
SELECT
    COUNT(*) AS migrated_count,
    '预期数量应与 grid_huinong_station 中未删除的记录数相等' AS note
FROM grid_info
WHERE grid_type = 'HUINONG';

SELECT
    COUNT(*) AS source_count,
    '源表中未删除的记录数' AS note
FROM grid_huinong_station
WHERE deleted = 0;

-- 对比关键字段
SELECT
    gi.id AS grid_id,
    gi.grid_code,
    hs.station_code,
    gi.grid_name,
    hs.station_name,
    gi.grid_marketing_flag,
    hs.grid_marketing_flag,
    '检查字段映射是否正确' AS note
FROM grid_info gi
INNER JOIN grid_huinong_station hs ON gi.id = hs.grid_id
WHERE gi.grid_type = 'HUINONG' AND hs.deleted = 0
LIMIT 10;

-- Phase 4: 删除 grid_huinong_station 表（谨慎执行）
-- ========================================================

-- 注意：在确认数据迁移无误后，再执行此步骤
-- 建议先备份 grid_huinong_station 表

-- 备份表（可选）
-- CREATE TABLE grid_huinong_station_backup AS SELECT * FROM grid_huinong_station;

-- 删除表（谨慎执行，确认数据迁移无误后再执行）
-- DROP TABLE IF EXISTS grid_huinong_station;

-- ========================================================
-- 重构完成说明
-- ========================================================
-- 1. grid_info 表新增了 7 个字段，用于存储惠农站点特有信息
-- 2. 原有的 grid_huinong_station 数据已迁移到 grid_info
-- 3. gridType = 'HUINONG' 标识这是一个惠农站点
-- 4. 字段映射关系：
--    - station_code → grid_code
--    - station_name → grid_name
--    - address → location_name
--    - location → center_point
--    - specialist_id → manager_user_id
-- 5. 删除表前请确保：
--    - 后端代码已更新并测试通过
--    - 前端功能验证正常
--    - 已做好数据备份
