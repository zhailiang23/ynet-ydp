-- ----------------------------
-- 知识库文件表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `knowledge_file` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '文件ID',
    `knowledge_base_id` bigint NOT NULL COMMENT '知识库ID',
    `file_name` varchar(255) NOT NULL COMMENT '文件名称',
    `file_type` varchar(50) NOT NULL COMMENT '文件类型',
    `file_url` varchar(500) DEFAULT NULL COMMENT '文件URL',
    `file_size` bigint DEFAULT NULL COMMENT '文件大小（字节）',
    `status` tinyint NOT NULL DEFAULT '0' COMMENT '文件状态（0处理中 1处理完成 2处理失败）',
    `creator` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id` bigint NOT NULL DEFAULT '0' COMMENT '租户编号',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_knowledge_base_id` (`knowledge_base_id`) USING BTREE COMMENT '知识库ID索引',
    INDEX `idx_status` (`status`) USING BTREE COMMENT '状态索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='知识库文件表';
