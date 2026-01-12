-- 修改 grid_huinong_marketing 表，支持多客户类型和对应数量

-- 1. 修改 customer_type 字段为 TEXT 类型，支持存储 JSON 数组
ALTER TABLE `grid_huinong_marketing`
MODIFY COLUMN `customer_type` TEXT COMMENT '客户类型（JSON数组）' AFTER `marketing_plan`;

-- 2. 修改 customer_count 字段为 TEXT 类型，支持存储 JSON 对象（客户类型->数量映射）
ALTER TABLE `grid_huinong_marketing`
MODIFY COLUMN `customer_count` TEXT COMMENT '客户数量（JSON对象，{客户类型: 数量}）' AFTER `customer_type`;

-- 3. 添加员工工号字段
ALTER TABLE `grid_huinong_marketing`
ADD COLUMN `employee_no` varchar(50) DEFAULT NULL COMMENT '员工工号' AFTER `specialist_id`;

-- 4. 添加村位置经纬度字段（独立存储，便于查询和展示）
ALTER TABLE `grid_huinong_marketing`
MODIFY COLUMN `village_location` varchar(255) DEFAULT NULL COMMENT '村位置坐标（格式：经度,纬度）' AFTER `village_name`;

-- 注释说明：
-- customer_type 存储格式示例：["传统种植户", "传统养殖户", "乡村新型经营主体"]
-- customer_count 存储格式示例：{"传统种植户": 100, "传统养殖户": 50, "乡村新型经营主体": 30}
