package cn.iocoder.yudao.module.aicrm.controller.admin.practicecourse.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - CRM智能陪练-陪练课程新增/修改 Request VO")
@Data
public class PracticeCourseSaveReqVO {

    @Schema(description = "课程ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "32088")
    private Long id;

    @Schema(description = "课程名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "课程名称不能为空")
    private String name;

    @Schema(description = "课程描述", example = "你说的对")
    private String description;

    @Schema(description = "关联陪练剧本ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "24241")
    @NotNull(message = "关联陪练剧本ID不能为空")
    private Long scriptId;

    @Schema(description = "课程类型 1.标准.0.个人")
    private Long standard;

    @Schema(description = "课程复杂度 1.复杂.0.简单")
    private Long hard;

    @Schema(description = "课程状态：字典 aicrm_course_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "课程状态：字典 aicrm_course_status不能为空")
    private String status;

}