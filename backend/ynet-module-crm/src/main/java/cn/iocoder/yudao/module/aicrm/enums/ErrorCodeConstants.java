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

}
