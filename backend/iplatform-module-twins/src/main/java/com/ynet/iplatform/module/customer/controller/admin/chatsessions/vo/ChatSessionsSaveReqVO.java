package com.ynet.iplatform.module.customer.controller.admin.chatsessions.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 会话信息新增/修改 Request VO")
@Data
public class ChatSessionsSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "16609")
    private Integer id;

    @Schema(description = "用户 id", example = "14573")
    private Integer userId;

    @Schema(description = "会话发起时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "会话发起时间不能为空")
    private LocalDateTime queriedAt;

    @Schema(description = "客服接入时间")
    private LocalDateTime acceptedAt;

    @Schema(description = "会话取消时间")
    private LocalDateTime canceledAt;

    @Schema(description = "中断时间")
    private LocalDateTime brokenAt;

    @Schema(description = "客服 id", requiredMode = Schema.RequiredMode.REQUIRED, example = "9199")
    @NotNull(message = "客服 id不能为空")
    private Integer adminId;

    @Schema(description = "会话类型", example = "2")
    private Integer type;

    @Schema(description = "频率")
    private Short rate;

}