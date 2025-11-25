package com.ynet.iplatform.module.aicrm.controller.admin.practicematerial.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - CRM智能陪练-培训文件 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PracticeMaterialRespVO {

    @Schema(description = "文件ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "9877")
    @ExcelProperty("文件ID")
    private Long id;

    @Schema(description = "文件名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("文件名称")
    private String name;

    @Schema(description = "文件类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "training_manual")
    @ExcelProperty("文件类型")
    private String fileType;

    @Schema(description = "文件URL", requiredMode = Schema.RequiredMode.REQUIRED, example = "https://www.iocoder.cn")
    @ExcelProperty("文件URL")
    private String fileUrl;

    @Schema(description = "文件大小（字节）")
    @ExcelProperty("文件大小（字节）")
    private Long fileSize;

    @Schema(description = "文件内容(纯文本)")
    @ExcelProperty("文件内容(纯文本)")
    private String content;

    @Schema(description = "文件内容(富文本)")
    @ExcelProperty("文件内容(富文本)")
    private String contentRich;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "关联课程名称", example = "理财产品销售陪练")
    @ExcelProperty("关联课程名称")
    private String courseName;

    @Schema(description = "培训人数", example = "10")
    @ExcelProperty("培训人数")
    private Integer trainingCount;

}