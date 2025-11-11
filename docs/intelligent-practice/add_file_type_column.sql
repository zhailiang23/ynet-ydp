-- 给培训文件表添加文件类型字段
ALTER TABLE crm_practice_material 
ADD COLUMN file_type VARCHAR(50) NOT NULL COMMENT '文件类型' AFTER name;

-- 为已有数据设置默认值（可选，如果表中有数据的话）
UPDATE crm_practice_material SET file_type = 'training_manual' WHERE file_type IS NULL OR file_type = '';
