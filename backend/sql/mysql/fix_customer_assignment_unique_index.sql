-- 修复客户归属表的唯一索引
-- 问题: 原索引不包含status字段,导致无法插入同user_id的新主办(即使旧记录已失效)
-- 解决: 删除原索引,创建包含status的唯一索引

USE `ruoyi-vue-pro`;

-- 1. 删除旧的唯一索引
ALTER TABLE `crm_customer_assignment`
DROP INDEX `uk_customer_assignment`;

-- 2. 创建新的唯一索引(包含status字段)
-- 说明: 同一客户的同一类型归属,同一用户在同一状态下只能有一条记录
ALTER TABLE `crm_customer_assignment`
ADD UNIQUE INDEX `uk_customer_assignment`(
  `customer_id` ASC,
  `assignment_type` ASC,
  `user_id` ASC,
  `status` ASC,
  `deleted` ASC
) USING BTREE
COMMENT '客户归属唯一索引（同一客户的同一类型归属，同一客户经理在同一状态下只能有一条有效记录）';
