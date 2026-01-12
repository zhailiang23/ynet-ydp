-- =====================================================
-- 社区管理模块：添加地址字段
-- =====================================================

USE `ynet-iplatform`;

-- 添加地址字段
ALTER TABLE `grid_community_info`
ADD COLUMN `address` VARCHAR(500) NULL COMMENT '社区地址' AFTER `latitude`;

-- 验证字段添加
SELECT COLUMN_NAME, COLUMN_TYPE, COLUMN_COMMENT
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_SCHEMA = 'ynet-iplatform'
  AND TABLE_NAME = 'grid_community_info'
  AND COLUMN_NAME IN ('longitude', 'latitude', 'address');
