package com.ynet.iplatform.module.customer.controller.admin.chatsessions.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 会话信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ChatSessionsRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "16609")
    @ExcelProperty("主键")
    private Integer id;

    @Schema(description = "用户 id", example = "14573")
    @ExcelProperty("用户 id")
    private Integer userId;

    @Schema(description = "用户名", example = "张三")
    @ExcelProperty("用户")
    private String userName;

    @Schema(description = "会话发起时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("会话发起时间")
    private LocalDateTime queriedAt;

    @Schema(description = "客服接入时间")
    @ExcelProperty("客服接入时间")
    private LocalDateTime acceptedAt;

    @Schema(description = "会话取消时间")
    @ExcelProperty("会话取消时间")
    private LocalDateTime canceledAt;

    @Schema(description = "中断时间")
    @ExcelProperty("中断时间")
    private LocalDateTime brokenAt;

    @Schema(description = "客服 id", requiredMode = Schema.RequiredMode.REQUIRED, example = "9199")
    @ExcelProperty("客服 id")
    private Integer adminId;

    @Schema(description = "客服名", example = "客服小李")
    @ExcelProperty("客服")
    private String adminName;

    @Schema(description = "会话类型", example = "2")
    @ExcelProperty("会话类型")
    private Integer type;

    @Schema(description = "频率")
    @ExcelProperty("频率")
    private Short rate;

}