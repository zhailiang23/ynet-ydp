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

}
