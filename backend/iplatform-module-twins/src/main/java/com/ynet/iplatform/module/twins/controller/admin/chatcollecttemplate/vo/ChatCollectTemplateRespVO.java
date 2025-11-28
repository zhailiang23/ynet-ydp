package com.ynet.iplatform.module.twins.controller.admin.chatcollecttemplate.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 客户留资模板 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ChatCollectTemplateRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "4564")
    @ExcelProperty("主键ID")
    private Integer id;

    @Schema(description = "模板名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("模板名称")
    private String name;

    @Schema(description = "模板描述", example = "随便")
    @ExcelProperty("模板描述")
    private String description;

    @Schema(description = "模板链接", requiredMode = Schema.RequiredMode.REQUIRED, example = "https://www.iocoder.cn")
    @ExcelProperty("模板链接")
    private String url;

    @Schema(description = "状态：0-禁用，1-启用", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("状态：0-禁用，1-启用")
    private Integer status;

    @Schema(description = "排序", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("排序")
    private Integer sort;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}