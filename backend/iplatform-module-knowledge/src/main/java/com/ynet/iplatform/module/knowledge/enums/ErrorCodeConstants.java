package com.ynet.iplatform.module.knowledge.enums;

import com.ynet.iplatform.framework.common.exception.ErrorCode;

/**
 * Knowledge 模块错误码枚举类
 *
 * knowledge 模块，使用 1-095-000-000 段
 */
public interface ErrorCodeConstants {

    // ========== 知识库 1-095-001-000 ==========
    ErrorCode KNOWLEDGE_BASE_NOT_EXISTS = new ErrorCode(1_095_001_000, "知识库不存在");
    ErrorCode BASE_NOT_EXISTS = new ErrorCode(1_095_001_000, "知识库不存在");

    // ========== 知识库文件 1-095-002-000 ==========
    ErrorCode KONWLEDGE_FILE_NOT_EXISTS = new ErrorCode(1_095_002_000, "知识库文件不存在");

}
