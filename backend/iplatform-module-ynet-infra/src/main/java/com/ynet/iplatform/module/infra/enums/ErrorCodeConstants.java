package com.ynet.iplatform.module.infra.enums;

import com.ynet.iplatform.framework.common.exception.ErrorCode;

/**
 * Ynet Infra 错误码枚举类
 *
 * ynet-infra 系统，使用 1-099-000-000 段
 */
public interface ErrorCodeConstants {

    // ========== 外部智能体管理 1-099-001-000 ==========
    ErrorCode EXTERNAL_AGENT_NOT_EXISTS = new ErrorCode(1_099_001_000, "外部智能体不存在");

    // ========== 对话机器人管理 1-099-002-000 ==========
    ErrorCode CHAT_ROBOT_NOT_EXISTS = new ErrorCode(1_099_002_000, "对话机器人不存在");
    ErrorCode CHAT_ROBOT_CONVERSATION_NOT_EXISTS = new ErrorCode(1_099_002_001, "对话不存在");

}
