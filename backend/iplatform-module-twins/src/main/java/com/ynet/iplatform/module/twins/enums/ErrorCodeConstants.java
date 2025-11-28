package com.ynet.iplatform.module.twins.enums;

import com.ynet.iplatform.framework.common.exception.ErrorCode;

/**
 * Twins 模块错误码枚举类
 *
 * twins 模块，使用 1-090-000-000 段
 */
public interface ErrorCodeConstants {

    // ========== 客服信息 1-090-001-000 ==========
    ErrorCode CUSTOMER_ADMIN_NOT_EXISTS = new ErrorCode(1_090_001_000, "客服信息不存在");

    // ========== 接入用户 1-090-002-000 ==========
    ErrorCode CUSTOMER_USER_NOT_EXISTS = new ErrorCode(1_090_002_000, "接入用户不存在");

    // ========== 客户留资模板 1-090-003-000 ==========
    ErrorCode CHAT_COLLECT_TEMPLATE_NOT_EXISTS = new ErrorCode(1_090_003_000, "客户留资模板不存在");

    // ========== 快捷回复消息 1-090-004-000 ==========
    ErrorCode CHAT_AUTO_MESSAGES_NOT_EXISTS = new ErrorCode(1_090_004_000, "快捷回复消息不存在");

    // ========== 客户留资信息 1-090-005-000 ==========
    ErrorCode CHAT_COLLECT_INFO_NOT_EXISTS = new ErrorCode(1_090_005_000, "客户留资信息不存在");

}
