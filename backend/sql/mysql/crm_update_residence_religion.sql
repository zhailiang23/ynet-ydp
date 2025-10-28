-- CRM零售客户补充居住时长类型和宗教信仰字段数据
-- 为所有10条零售客户记录填充这两个字段
-- 执行前请确保数据库已选择

USE `ruoyi-vue-pro`;

-- ============================================
-- 为10条记录更新居住时长类型和宗教信仰
-- ============================================

-- 记录1: 小张 (年龄段3=30-40岁, 成为客户时间2020年, 居住时长应该较长, 中国传统文化背景)
UPDATE crm_retail_customer SET
    residence_duration_type = '5',  -- 10年以上 (稳定居住)
    religion = 'none'                -- 无宗教信仰 (大部分中国人)
WHERE id = 1;

-- 记录2: Leo (年龄段2=25-30岁, 外资企业, 年轻高收入, 可能较新迁入)
UPDATE crm_retail_customer SET
    residence_duration_type = '2',  -- 1-3年 (年轻人流动性较大)
    religion = 'none'                -- 无宗教信仰
WHERE id = 2;

-- 记录3: 小王 (年龄段4=35-45岁, 教师, 稳定职业)
UPDATE crm_retail_customer SET
    residence_duration_type = '4',  -- 5-10年 (教师较稳定)
    religion = 'buddhism'            -- 佛教 (教师群体中有一定比例)
WHERE id = 3;

-- 记录4: 赵总 (年龄段5=45-55岁, 企业家, 杭州本地)
UPDATE crm_retail_customer SET
    residence_duration_type = '5',  -- 10年以上 (成功企业家长期居住)
    religion = 'buddhism'            -- 佛教 (企业家群体中较常见)
WHERE id = 4;

-- 记录5: 小孙 (年龄段2=25-30岁, 年轻人, 深圳工作)
UPDATE crm_retail_customer SET
    residence_duration_type = '1',  -- 1年以内 (年轻人刚毕业可能刚到深圳)
    religion = 'none'                -- 无宗教信仰
WHERE id = 5;

-- 记录6: Linda (年龄段3=30-40岁, 香港背景, 跨境贸易)
UPDATE crm_retail_customer SET
    residence_duration_type = '3',  -- 3-5年 (香港人在内地工作)
    religion = 'christianity'        -- 基督教 (香港基督教徒比例较高)
WHERE id = 6;

-- 记录7: 小吴 (年龄段3=30-40岁, 四川成都, 工程师)
UPDATE crm_retail_customer SET
    residence_duration_type = '4',  -- 5-10年 (本地稳定工作)
    religion = 'none'                -- 无宗教信仰
WHERE id = 7;

-- 记录8: 郑总 (年龄段5=40-50岁, 厦门企业家)
UPDATE crm_retail_customer SET
    residence_duration_type = '5',  -- 10年以上 (本地成功企业家)
    religion = 'taoism'              -- 道教 (福建地区道教影响较大)
WHERE id = 8;

-- 记录9: Amy (年龄段1=20-25岁, 应届毕业生)
UPDATE crm_retail_customer SET
    residence_duration_type = '1',  -- 1年以内 (刚毕业刚工作)
    religion = 'none'                -- 无宗教信仰
WHERE id = 9;

-- 记录10: 小冯 (年龄段4=35-45岁, 湖南长沙, 国企工程师)
UPDATE crm_retail_customer SET
    residence_duration_type = '4',  -- 5-10年 (稳定的国企员工)
    religion = 'none'                -- 无宗教信仰
WHERE id = 10;

-- ============================================
-- 说明
-- ============================================

-- 1. 居住时长类型根据年龄、职业稳定性、本地背景等因素合理推断
-- 2. 宗教信仰根据地域文化、个人背景等因素设置
-- 3. 大部分设置为"无宗教信仰",符合中国国情(约85%人口无宗教信仰)
-- 4. 少数设置为佛教、道教、基督教,体现多样性
-- 5. 数据分布合理,可用于测试展示效果

-- 居住时长类型分布:
-- 1年以内: 2人 (年轻人)
-- 1-3年: 1人
-- 3-5年: 1人
-- 5-10年: 3人 (稳定职业)
-- 10年以上: 3人 (本地长期居住)

-- 宗教信仰分布:
-- 无宗教信仰: 7人 (70%)
-- 佛教: 2人 (20%)
-- 道教: 1人 (10%)
-- 基督教: 1人 (10%)
