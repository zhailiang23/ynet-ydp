-- ----------------------------
-- 字典数据：客户证件类型 (customer_identity_type)
-- 用于零售客户证件信息管理
-- ----------------------------

-- 插入字典类型
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted_time, deleted)
VALUES (NULL, '客户证件类型', 'customer_identity_type', 0, '零售客户证件类型字典', '1', NOW(), '1', NOW(), NULL, b'0')
ON DUPLICATE KEY UPDATE
    name = '客户证件类型',
    status = 0,
    remark = '零售客户证件类型字典',
    updater = '1',
    update_time = NOW();

-- 插入字典数据项
INSERT INTO system_dict_data (id, sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted) VALUES
(NULL, 1, '居民身份证', 'id_card', 'customer_identity_type', 0, 'primary', '', '中华人民共和国居民身份证', '1', NOW(), '1', NOW(), b'0'),
(NULL, 2, '护照', 'passport', 'customer_identity_type', 0, 'success', '', '中华人民共和国护照', '1', NOW(), '1', NOW(), b'0'),
(NULL, 3, '港澳通行证', 'hk_macao_pass', 'customer_identity_type', 0, 'info', '', '往来港澳通行证', '1', NOW(), '1', NOW(), b'0'),
(NULL, 4, '台湾通行证', 'taiwan_pass', 'customer_identity_type', 0, 'info', '', '大陆居民往来台湾通行证', '1', NOW(), '1', NOW(), b'0'),
(NULL, 5, '军官证', 'military_officer', 'customer_identity_type', 0, 'warning', '', '中国人民解放军军官证', '1', NOW(), '1', NOW(), b'0'),
(NULL, 6, '士兵证', 'military_soldier', 'customer_identity_type', 0, 'warning', '', '中国人民解放军士兵证', '1', NOW(), '1', NOW(), b'0'),
(NULL, 7, '武警证', 'armed_police', 'customer_identity_type', 0, 'warning', '', '中国人民武装警察证', '1', NOW(), '1', NOW(), b'0'),
(NULL, 8, '港澳居民居住证', 'hk_macao_residence', 'customer_identity_type', 0, 'success', '', '港澳居民居住证', '1', NOW(), '1', NOW(), b'0'),
(NULL, 9, '台湾居民居住证', 'taiwan_residence', 'customer_identity_type', 0, 'success', '', '台湾居民居住证', '1', NOW(), '1', NOW(), b'0'),
(NULL, 10, '外国人永久居留证', 'foreign_permanent', 'customer_identity_type', 0, 'danger', '', '中华人民共和国外国人永久居留身份证', '1', NOW(), '1', NOW(), b'0'),
(NULL, 11, '户口簿', 'household_register', 'customer_identity_type', 0, 'default', '', '居民户口簿', '1', NOW(), '1', NOW(), b'0'),
(NULL, 99, '其他证件', 'other', 'customer_identity_type', 0, 'default', '', '其他类型证件', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE
    label = VALUES(label),
    status = VALUES(status),
    color_type = VALUES(color_type),
    remark = VALUES(remark),
    updater = '1',
    update_time = NOW();
