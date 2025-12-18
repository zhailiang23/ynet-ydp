-- 为社区信息表增加坐标字段
ALTER TABLE `grid_community_info`
ADD COLUMN `longitude` DECIMAL(10,6) DEFAULT NULL COMMENT '经度' AFTER `adjustment_reason`,
ADD COLUMN `latitude` DECIMAL(10,6) DEFAULT NULL COMMENT '纬度' AFTER `longitude`;

-- 添加坐标索引以提高查询性能
CREATE INDEX `idx_coordinates` ON `grid_community_info` (`longitude`, `latitude`);
