package com.ynet.iplatform.module.twins.controller.admin.chatautomessages.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 快捷回复消息新增/修改 Request VO")
@Data
public class ChatAutoMessagesSaveReqVO {

    @Schema(description = "主键", example = "32040")
    private Integer id;

    @Schema(description = "消息名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "欢迎语")
    @NotEmpty(message = "消息名称不能为空")
    @Size(max = 32, message = "消息名称长度不能超过32个字符")
    private String name;

    @Schema(description = "消息类型：text-文本，navigator-链接卡片", requiredMode = Schema.RequiredMode.REQUIRED, example = "text")
    @NotEmpty(message = "消息类型不能为空")
    private String type;

    @Schema(description = "消息内容（文本类型时必填）", example = "您好，请问有什么可以帮您？")
    @Size(max = 512, message = "消息内容长度不能超过512个字符")
    private String content;

    @Schema(description = "卡片标题（链接卡片类型时必填）", example = "点击查看详情")
    @Size(max = 32, message = "卡片标题长度不能超过32个字符")
    private String navigatorTitle;

    @Schema(description = "跳转链接（链接卡片类型时必填）", example = "https://example.com")
    @Size(max = 512, message = "跳转链接长度不能超过512个字符")
    private String navigatorUrl;

}