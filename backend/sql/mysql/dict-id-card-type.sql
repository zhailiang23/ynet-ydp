-- 证件类型字典

-- 1. 创建字典类型
INSERT INTO system_dict_type (name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES ('AICRM 证件类型', 'aicrm_id_card_type', 0, '客户证件类型', '1', NOW(), '1', NOW(), b'0', NULL);

SET @dict_type_id = LAST_INSERT_ID();

-- 2. 创建字典数据
INSERT INTO system_dict_data (dict_type, label, value, sort, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted)
VALUES
  (@dict_type_id, '居民身份证', '01', 1, 0, 'default', '', '中华人民共和国居民身份证', '1', NOW(), '1', NOW(), b'0'),
  (@dict_type_id, '护照', '02', 2, 0, 'primary', '', '护照', '1', NOW(), '1', NOW(), b'0'),
  (@dict_type_id, '军官证', '03', 3, 0, 'success', '', '军官证', '1', NOW(), '1', NOW(), b'0'),
  (@dict_type_id, '士兵证', '04', 4, 0, 'success', '', '士兵证', '1', NOW(), '1', NOW(), b'0'),
  (@dict_type_id, '港澳居民来往内地通行证', '05', 5, 0, 'info', '', '港澳居民来往内地通行证', '1', NOW(), '1', NOW(), b'0'),
  (@dict_type_id, '台湾居民来往大陆通行证', '06', 6, 0, 'info', '', '台湾居民来往大陆通行证', '1', NOW(), '1', NOW(), b'0'),
  (@dict_type_id, '外国人居留证', '07', 7, 0, 'warning', '', '外国人居留证', '1', NOW(), '1', NOW(), b'0'),
  (@dict_type_id, '统一社会信用代码', '08', 8, 0, 'danger', '', '企业统一社会信用代码', '1', NOW(), '1', NOW(), b'0'),
  (@dict_type_id, '营业执照', '09', 9, 0, 'danger', '', '营业执照', '1', NOW(), '1', NOW(), b'0'),
  (@dict_type_id, '其他', '99', 99, 0, 'default', '', '其他证件类型', '1', NOW(), '1', NOW(), b'0');
