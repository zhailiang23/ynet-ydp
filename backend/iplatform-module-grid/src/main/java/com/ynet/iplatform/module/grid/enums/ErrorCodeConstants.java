package com.ynet.iplatform.module.grid.enums;

import com.ynet.iplatform.framework.common.exception.ErrorCode;

/**
 * Grid 错误码枚举类
 *
 * grid 网格营销模块,使用 1-020-000-000 段
 */
public interface ErrorCodeConstants {

    // ========== 网格信息模块 1-020-001-000 ==========
    ErrorCode GRID_INFO_NOT_EXISTS = new ErrorCode(1_020_001_000, "网格不存在");
    ErrorCode INFO_NOT_EXISTS = GRID_INFO_NOT_EXISTS; // 简短别名
    ErrorCode GRID_INFO_CODE_DUPLICATE = new ErrorCode(1_020_001_001, "网格编号已存在");

    // ========== 网格客户模块 1-020-002-000 ==========
    ErrorCode GRID_CUSTOMER_NOT_EXISTS = new ErrorCode(1_020_002_000, "客户不存在");
    ErrorCode CUSTOMER_NOT_EXISTS = GRID_CUSTOMER_NOT_EXISTS; // 简短别名
    ErrorCode GRID_CUSTOMER_PHONE_DUPLICATE = new ErrorCode(1_020_002_001, "客户手机号已存在");

    // ========== 网格活动记录模块 1-020-003-000 ==========
    ErrorCode GRID_ACTIVITY_LOG_NOT_EXISTS = new ErrorCode(1_020_003_000, "活动记录不存在");
    ErrorCode ACTIVITY_LOG_NOT_EXISTS = GRID_ACTIVITY_LOG_NOT_EXISTS; // 简短别名

    // ========== 惠农扩展模块 1-020-004-000 ==========
    ErrorCode GRID_HUINONG_EXTENSION_NOT_EXISTS = new ErrorCode(1_020_004_000, "惠农网格扩展不存在");
    ErrorCode HUINONG_EXTENSION_NOT_EXISTS = GRID_HUINONG_EXTENSION_NOT_EXISTS; // 简短别名
    ErrorCode GRID_HUINONG_CUSTOMER_LOAN_NOT_EXISTS = new ErrorCode(1_020_004_001, "惠农贷款客户不存在");
    ErrorCode HUINONG_CUSTOMER_LOAN_NOT_EXISTS = GRID_HUINONG_CUSTOMER_LOAN_NOT_EXISTS; // 简短别名
    ErrorCode GRID_HUINONG_STATION_NOT_EXISTS = new ErrorCode(1_020_004_002, "惠农站点不存在");
    ErrorCode HUINONG_STATION_NOT_EXISTS = GRID_HUINONG_STATION_NOT_EXISTS; // 简短别名
    ErrorCode GRID_HUINONG_STATION_CODE_DUPLICATE = new ErrorCode(1_020_004_003, "惠农站点编号已存在");
    ErrorCode GRID_HUINONG_MARKETING_NOT_EXISTS = new ErrorCode(1_020_004_004, "惠农营销信息不存在");
    ErrorCode HUINONG_MARKETING_NOT_EXISTS = GRID_HUINONG_MARKETING_NOT_EXISTS; // 简短别名

    // ========== 厅堂客户模块 1-020-005-000 ==========
    ErrorCode GRID_TINGTANG_CUSTOMER_NOT_EXISTS = new ErrorCode(1_020_005_000, "厅堂客户不存在");
    ErrorCode TINGTANG_CUSTOMER_NOT_EXISTS = GRID_TINGTANG_CUSTOMER_NOT_EXISTS; // 简短别名

    // ========== 社区客户模块 1-020-006-000 ==========
    ErrorCode GRID_COMMUNITY_CUSTOMER_NOT_EXISTS = new ErrorCode(1_020_006_000, "社区客户不存在");
    ErrorCode COMMUNITY_CUSTOMER_NOT_EXISTS = GRID_COMMUNITY_CUSTOMER_NOT_EXISTS; // 简短别名

    // ========== 零贷客户模块 1-020-007-000 ==========
    ErrorCode GRID_ZERODAI_CUSTOMER_NOT_EXISTS = new ErrorCode(1_020_007_000, "零贷客户不存在");
    ErrorCode ZERODAI_CUSTOMER_NOT_EXISTS = GRID_ZERODAI_CUSTOMER_NOT_EXISTS; // 简短别名
    ErrorCode GRID_ZERODAI_MARKET_DESCRIPTION_NOT_EXISTS = new ErrorCode(1_020_007_001, "零贷市场描述不存在");
    ErrorCode ZERODAI_MARKET_DESCRIPTION_NOT_EXISTS = GRID_ZERODAI_MARKET_DESCRIPTION_NOT_EXISTS; // 简短别名

    // ========== 客户档案保护模块 1-020-008-000 ==========
    ErrorCode GRID_CUSTOMER_ARCHIVE_PROTECTION_NOT_EXISTS = new ErrorCode(1_020_008_000, "客户档案保护记录不存在");
    ErrorCode CUSTOMER_ARCHIVE_PROTECTION_NOT_EXISTS = GRID_CUSTOMER_ARCHIVE_PROTECTION_NOT_EXISTS; // 简短别名

    // ========== 统计报表模块 1-020-009-000 ==========
    ErrorCode GRID_STATISTICS_NOT_EXISTS = new ErrorCode(1_020_009_000, "统计记录不存在");
    ErrorCode STATISTICS_NOT_EXISTS = GRID_STATISTICS_NOT_EXISTS; // 简短别名

    // ========== 数据同步模块 1-020-010-000 ==========
    ErrorCode GRID_DATA_SYNC_LOG_NOT_EXISTS = new ErrorCode(1_020_010_000, "数据同步记录不存在");
    ErrorCode DATA_SYNC_LOG_NOT_EXISTS = GRID_DATA_SYNC_LOG_NOT_EXISTS; // 简短别名

    // ========== 社区信息模块 1-020-011-000 ==========
    ErrorCode COMMUNITY_NOT_EXISTS = new ErrorCode(1_020_011_000, "社区不存在");
    ErrorCode COMMUNITY_CODE_DUPLICATE = new ErrorCode(1_020_011_001, "社区编号已存在");

    // ========== 同业信息模块 1-020-012-000 ==========
    ErrorCode COMPETITOR_INFO_NOT_EXISTS = new ErrorCode(1_020_012_000, "同业信息不存在");

    // ========== 关键人信息模块 1-020-013-000 ==========
    ErrorCode KEY_PERSON_NOT_EXISTS = new ErrorCode(1_020_013_000, "关键人信息不存在");
}
