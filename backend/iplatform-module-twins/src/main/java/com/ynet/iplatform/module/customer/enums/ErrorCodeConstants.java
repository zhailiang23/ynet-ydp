package com.ynet.iplatform.module.customer.enums;

import com.ynet.iplatform.framework.common.exception.ErrorCode;

/**
 * Customer 模块错误码枚举类
 *
 * customer 模块，使用 1-091-000-000 段
 */
public interface ErrorCodeConstants {

    // ========== 会话信息 1-091-001-000 ==========
    ErrorCode CHAT_SESSIONS_NOT_EXISTS = new ErrorCode(1_091_001_000, "会话信息不存在");

    // ========== 转接记录 1-091-002-000 ==========
    ErrorCode CHAT_TRANSFERS_NOT_EXISTS = new ErrorCode(1_091_002_000, "转接记录不存在");

}
