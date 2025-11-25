package com.ynet.iplatform.module.aicrm.controller.admin.practicecase.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - CRM智能陪练-销售案例 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PracticeCaseRespVO {

    @Schema(description = "案例ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "9916")
    @ExcelProperty("案例ID")
    private Long id;

    @Schema(description = "案例标题", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("案例标题")
    private String title;

    @Schema(description = "案例详细内容（正文）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("案例详细内容（正文）")
    private String content;

    @Schema(description = "标签（多个标签逗号分隔）")
    @ExcelProperty("标签（多个标签逗号分隔）")
    private String tags;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}