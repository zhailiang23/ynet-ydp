package com.ynet.iplatform.module.infra.controller.admin.chatrobot.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 对话机器人管理新增/修改 Request VO")
@Data
public class ChatRobotSaveReqVO {

    @Schema(description = "机器人ID", example = "1024")
    private Long id;

    @Schema(description = "机器人名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "机器人名称不能为空")
    private String name;

    @Schema(description = "机器人描述", example = "你说的对")
    private String description;

    @Schema(description = "对接平台类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "对接平台类型不能为空")
    private String platformType;

    @Schema(description = "平台配置（JSON格式，存储webhook、appKey、appSecret等平台特定配置）")
    private String platformConfig;

    @Schema(description = "绑定的智能体", example = "27034")
    private Long agentId;

    @Schema(description = "是否启用（0=禁用，1=启用）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "是否启用（0=禁用，1=启用）不能为空")
    private Integer status;

}