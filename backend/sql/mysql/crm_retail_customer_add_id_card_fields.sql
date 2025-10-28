-- 为零售客户表添加证件类型和证件号码字段

-- 添加证件相关字段
ALTER TABLE `crm_retail_customer`
ADD COLUMN `id_card_type` VARCHAR(50) NULL COMMENT '证件类型(如:身份证、护照等)' AFTER `birthday`,
ADD COLUMN `id_card_no` VARCHAR(100) NULL COMMENT '证件号码' AFTER `id_card_type`;

-- 为现有测试数据填充示例值
UPDATE `crm_retail_customer` SET
  id_card_type = '身份证',
  id_card_no = CONCAT(
    -- 6位地区码
    CASE
      WHEN native_place LIKE '%北京%' THEN '110101'
      WHEN native_place LIKE '%上海%' THEN '310101'
      WHEN native_place LIKE '%广东%' THEN '440101'
      WHEN native_place LIKE '%浙江%' THEN '330101'
      WHEN native_place LIKE '%江苏%' THEN '320101'
      WHEN native_place LIKE '%湖北%' THEN '420101'
      ELSE '430101'
    END,
    -- 8位出生日期
    CASE
      WHEN birthday IS NOT NULL THEN DATE_FORMAT(birthday, '%Y%m%d')
      ELSE '19900101'
    END,
    -- 3位顺序码
    LPAD(FLOOR(100 + RAND() * 900), 3, '0'),
    -- 1位校验码
    CASE WHEN RAND() > 0.5 THEN 'X' ELSE FLOOR(RAND() * 10) END
  )
WHERE id_card_type IS NULL;
