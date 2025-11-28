package com.ynet.iplatform.module.twins.controller.admin.chatautomessages.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 客户留资消息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ChatAutoMessagesRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "32040")
    @ExcelProperty("主键")
    private Integer id;

    @Schema(description = "名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("名称")
    private String name;

    @Schema(description = "类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("类型")
    private String type;

    @Schema(description = "内容")
    @ExcelProperty("内容")
    private String content;

    @Schema(description = "卡片标题（链接卡片类型时使用）", example = "点击查看详情")
    @ExcelProperty("卡片标题")
    private String navigatorTitle;

    @Schema(description = "跳转链接（链接卡片类型时使用）", example = "https://example.com")
    @ExcelProperty("跳转链接")
    private String navigatorUrl;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}