package com.ynet.iplatform.module.knowledge.controller.admin.base.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 知识库 Response VO")
@Data
@ExcelIgnoreUnannotated
public class KnowledgeBaseRespVO {

    @Schema(description = "知识库ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "28433")
    @ExcelProperty("知识库ID")
    private Long id;

    @Schema(description = "知识库名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("知识库名称")
    private String name;

    @Schema(description = "知识库描述", example = "你说的对")
    @ExcelProperty("知识库描述")
    private String description;

    @Schema(description = "状态（0正常 1停用）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("状态（0正常 1停用）")
    private Integer status;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}