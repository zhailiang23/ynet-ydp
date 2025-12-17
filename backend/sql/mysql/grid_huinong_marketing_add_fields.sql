-- 为 grid_huinong_marketing 表添加站点ID、客户类型、客户数量字段
ALTER TABLE `grid_huinong_marketing`
ADD COLUMN `station_id` bigint DEFAULT NULL COMMENT '站点ID' AFTER `grid_id`,
ADD COLUMN `customer_type` varchar(50) DEFAULT NULL COMMENT '客户类型' AFTER `marketing_plan`,
ADD COLUMN `customer_count` int DEFAULT NULL COMMENT '客户数量' AFTER `customer_type`;

-- 添加站点ID的索引
ALTER TABLE `grid_huinong_marketing`
ADD INDEX `idx_station_id` (`station_id`);
