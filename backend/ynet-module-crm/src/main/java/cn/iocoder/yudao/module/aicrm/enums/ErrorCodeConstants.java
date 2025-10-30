package cn.iocoder.yudao.module.aicrm.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * CRM 错误码枚举类
 *
 * crm 系统，使用 1-020-000-000 段
 */
public interface ErrorCodeConstants {

    // ========== 客户主表 1-020-000-000 ==========
    ErrorCode CUSTOMER_NOT_EXISTS = new ErrorCode(1_020_000_000, "客户不存在");

    // ========== 零售客户 1-020-001-000 ==========
    ErrorCode RETAIL_CUSTOMER_NOT_EXISTS = new ErrorCode(1_020_001_000, "零售客户不存在");

    // ========== 对公客户 1-020-002-000 ==========
    ErrorCode COMPANY_CUSTOMER_NOT_EXISTS = new ErrorCode(1_020_002_000, "对公客户不存在");

    // ========== 客户证件信息 1-020-003-000 ==========
    ErrorCode CUSTOMER_IDENTITY_NOT_EXISTS = new ErrorCode(1_020_003_000, "客户证件信息不存在");

    // ========== 客户工作或经营信息 1-020-004-000 ==========
    ErrorCode CUSTOMER_WORK_NOT_EXISTS = new ErrorCode(1_020_004_000, "客户工作或经营信息不存在");

    // ========== 客户家庭信息 1-020-005-000 ==========
    ErrorCode CUSTOMER_FAMILY_NOT_EXISTS = new ErrorCode(1_020_005_000, "客户家庭信息不存在");

    // ========== 客户家庭成员信息 1-020-006-000 ==========
    ErrorCode CUSTOMER_FAMILY_MEMBER_NOT_EXISTS = new ErrorCode(1_020_006_000, "客户家庭成员信息不存在");

    // ========== 客户归属关系表 1-020-007-000 ==========
    ErrorCode CUSTOMER_ASSIGNMENT_NOT_EXISTS = new ErrorCode(1_020_007_000, "客户归属关系不存在");

    // ========== 客户归属调整历史表 1-020-008-000 ==========
    ErrorCode CUSTOMER_ASSIGNMENT_HISTORY_NOT_EXISTS = new ErrorCode(1_020_008_000, "客户归属调整历史不存在");

    // ========== 客户大事记 1-020-009-000 ==========
    ErrorCode CUSTOMER_IMPORTANT_EVENT_NOT_EXISTS = new ErrorCode(1_020_009_000, "客户大事记不存在");

    // ========== 客户偏好 1-020-010-000 ==========
    ErrorCode CUSTOMER_PREFERENCE_NOT_EXISTS = new ErrorCode(1_020_010_000, "客户偏好不存在");

    // ========== 客户网格归属 1-020-011-000 ==========
    ErrorCode CUSTOMER_GRID_ASSIGNMENT_NOT_EXISTS = new ErrorCode(1_020_011_000, "客户网格归属不存在");

    // ========== 客户网格归属历史 1-020-012-000 ==========
    ErrorCode CUSTOMER_GRID_HISTORY_NOT_EXISTS = new ErrorCode(1_020_012_000, "客户网格归属历史不存在");

    // ========== 客户群组归属 1-020-013-000 ==========
    ErrorCode CUSTOMER_GROUP_ASSIGNMENT_NOT_EXISTS = new ErrorCode(1_020_013_000, "客户群组归属不存在");

    // ========== 客户群组归属历史 1-020-014-000 ==========
    ErrorCode CUSTOMER_GROUP_HISTORY_NOT_EXISTS = new ErrorCode(1_020_014_000, "客户群组归属历史不存在");

    // ========== 客户存款账户 1-020-020-000 ==========
    ErrorCode CUSTOMER_ACCOUNT_DEPOSIT_NOT_EXISTS = new ErrorCode(1_020_020_000, "客户存款账户不存在");

    // ========== 客户信用卡账户 1-020-021-000 ==========
    ErrorCode CUSTOMER_ACCOUNT_CREDITCARD_NOT_EXISTS = new ErrorCode(1_020_021_000, "客户信用卡账户不存在");

    // ========== 客户贷款账户 1-020-022-000 ==========
    ErrorCode CUSTOMER_ACCOUNT_LOAN_NOT_EXISTS = new ErrorCode(1_020_022_000, "客户贷款账户不存在");

    // ========== 客户理财账户 1-020-023-000 ==========
    ErrorCode CUSTOMER_ACCOUNT_WEALTH_NOT_EXISTS = new ErrorCode(1_020_023_000, "客户理财账户不存在");

    // ========== 客户基金账户 1-020-024-000 ==========
    ErrorCode CUSTOMER_ACCOUNT_FUND_NOT_EXISTS = new ErrorCode(1_020_024_000, "客户基金账户不存在");

    // ========== 客户保险账户 1-020-025-000 ==========
    ErrorCode CUSTOMER_ACCOUNT_INSURANCE_NOT_EXISTS = new ErrorCode(1_020_025_000, "客户保险账户不存在");

    // ========== 客户信托账户 1-020-026-000 ==========
    ErrorCode CUSTOMER_ACCOUNT_TRUST_NOT_EXISTS = new ErrorCode(1_020_026_000, "客户信托账户不存在");

    // ========== 客户贵金属账户 1-020-027-000 ==========
    ErrorCode CUSTOMER_ACCOUNT_METAL_NOT_EXISTS = new ErrorCode(1_020_027_000, "客户贵金属账户不存在");

}
