-- 为评分因子表添加结果配置字段
-- 执行时间: 2025-01-XX

-- 添加影响方式字段
ALTER TABLE aicrm_task_scoring_factor
ADD COLUMN impact_type VARCHAR(20) NOT NULL DEFAULT 'score' COMMENT '影响方式: direct-直接设定评分, weight-调整评分权重, score-评分值加减' AFTER logic_type;

-- 添加分值调整字段
ALTER TABLE aicrm_task_scoring_factor
ADD COLUMN score_adjustment INT NOT NULL DEFAULT 10 COMMENT '增加/减少分值 (-100到100)' AFTER impact_type;

-- 验证字段是否添加成功
SELECT
    COLUMN_NAME,
    DATA_TYPE,
    COLUMN_DEFAULT,
    COLUMN_COMMENT
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_SCHEMA = DATABASE()
    AND TABLE_NAME = 'aicrm_task_scoring_factor'
    AND COLUMN_NAME IN ('impact_type', 'score_adjustment');
