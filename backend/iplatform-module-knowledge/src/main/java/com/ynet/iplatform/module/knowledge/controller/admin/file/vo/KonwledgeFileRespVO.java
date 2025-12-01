package com.ynet.iplatform.module.knowledge.controller.admin.file.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 知识库文件 Response VO")
@Data
@ExcelIgnoreUnannotated
public class KonwledgeFileRespVO {

    @Schema(description = "文件ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "文件名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("文件名称")
    private String fileName;

    @Schema(description = "文件类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("文件类型")
    private String fileType;

    @Schema(description = "文件大小（字节）")
    @ExcelProperty("文件大小（字节）")
    private Long fileSize;

    @Schema(description = "文件状态（0处理中 1处理完成 2处理失败）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("文件状态（0处理中 1处理完成 2处理失败）")
    private Integer status;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}