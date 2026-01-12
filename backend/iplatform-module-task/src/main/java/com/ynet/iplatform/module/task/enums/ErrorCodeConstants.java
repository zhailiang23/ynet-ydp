package com.ynet.iplatform.module.task.enums;

import com.ynet.iplatform.framework.common.exception.ErrorCode;

/**
 * Task 错误码枚举类
 *
 * task 系统，使用 1-020-000-000 段
 */
public interface ErrorCodeConstants {

    // ========== AI智能任务 1-020-001-000 ==========
    ErrorCode TASK_NOT_EXISTS = new ErrorCode(1_020_001_000, "任务不存在");

}
