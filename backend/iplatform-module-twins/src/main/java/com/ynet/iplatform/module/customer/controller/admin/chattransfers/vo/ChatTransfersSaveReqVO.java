package com.ynet.iplatform.module.customer.controller.admin.chattransfers.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 转接记录新增/修改 Request VO")
@Data
public class ChatTransfersSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "10616")
    private Integer id;

    @Schema(description = "被转接用户", requiredMode = Schema.RequiredMode.REQUIRED, example = "24485")
    @NotNull(message = "被转接用户不能为空")
    private Integer userId;

    @Schema(description = "原会话 id", requiredMode = Schema.RequiredMode.REQUIRED, example = "17809")
    @NotNull(message = "原会话 id不能为空")
    private Integer fromSessionId;

    @Schema(description = "新会话 id", requiredMode = Schema.RequiredMode.REQUIRED, example = "6114")
    @NotNull(message = "新会话 id不能为空")
    private Integer toSessionId;

    @Schema(description = "转接人", requiredMode = Schema.RequiredMode.REQUIRED, example = "13802")
    @NotNull(message = "转接人不能为空")
    private Integer fromAdminId;

    @Schema(description = "转接给", requiredMode = Schema.RequiredMode.REQUIRED, example = "2284")
    @NotNull(message = "转接给不能为空")
    private Integer toAdminId;

    @Schema(description = "租户 id", requiredMode = Schema.RequiredMode.REQUIRED, example = "19220")
    @NotNull(message = "租户 id不能为空")
    private Integer customerId;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "接受时间")
    private LocalDateTime acceptedAt;

    @Schema(description = "取消时间")
    private LocalDateTime canceledAt;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "创建时间不能为空")
    private LocalDateTime createdAt;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "更新时间不能为空")
    private LocalDateTime updatedAt;

}