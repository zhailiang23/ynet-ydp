package com.ynet.iplatform.module.infra.controller.admin.chatrobot.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 对话机器人管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ChatRobotRespVO {

    @Schema(description = "机器人ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long id;

    @Schema(description = "机器人名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("机器人名称")
    private String name;

    @Schema(description = "机器人描述", example = "你说的对")
    @ExcelProperty("机器人描述")
    private String description;

    @Schema(description = "对接平台类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("对接平台类型")
    private String platformType;

    @Schema(description = "平台配置（JSON格式，存储webhook、appKey、appSecret等平台特定配置）")
    @ExcelProperty("平台配置（JSON格式，存储webhook、appKey、appSecret等平台特定配置）")
    private String platformConfig;

    @Schema(description = "绑定的智能体ID", example = "27034")
    private Long agentId;

    @Schema(description = "绑定的智能体名称", example = "客服助手")
    @ExcelProperty("绑定的智能体")
    private String agentName;

    @Schema(description = "是否启用（0=禁用，1=启用）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("是否启用（0=禁用，1=启用）")
    private Integer status;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}