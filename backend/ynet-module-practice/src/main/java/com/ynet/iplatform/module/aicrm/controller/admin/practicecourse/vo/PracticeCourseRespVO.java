package com.ynet.iplatform.module.aicrm.controller.admin.practicecourse.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - CRM智能陪练-陪练课程 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PracticeCourseRespVO {

    @Schema(description = "课程ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "32088")
    @ExcelProperty("课程ID")
    private Long id;

    @Schema(description = "课程名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("课程名称")
    private String name;

    @Schema(description = "课程描述", example = "你说的对")
    @ExcelProperty("课程描述")
    private String description;

    @Schema(description = "关联陪练剧本ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "24241")
    @ExcelProperty("关联陪练剧本ID")
    private Long scriptId;

    @Schema(description = "关联剧本名称", example = "剧本名称")
    @ExcelProperty("关联剧本名称")
    private String scriptName;

    @Schema(description = "剧本版本", example = "v1.0")
    @ExcelProperty("剧本版本")
    private String scriptVersion;

    @Schema(description = "课程类型 1.标准.0.个人")
    @ExcelProperty("课程类型 1.标准.0.个人")
    private Long standard;

    @Schema(description = "课程复杂度 1.复杂.0.简单")
    @ExcelProperty("课程复杂度 1.复杂.0.简单")
    private Long hard;

    @Schema(description = "课程状态：字典 aicrm_course_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("课程状态：字典 aicrm_course_status")
    private String status;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    // 标准getter/setter方法（为了确保编译通过）
    public Long getScriptId() {
        return scriptId;
    }

    public void setScriptId(Long scriptId) {
        this.scriptId = scriptId;
    }

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    public String getScriptVersion() {
        return scriptVersion;
    }

    public void setScriptVersion(String scriptVersion) {
        this.scriptVersion = scriptVersion;
    }

}