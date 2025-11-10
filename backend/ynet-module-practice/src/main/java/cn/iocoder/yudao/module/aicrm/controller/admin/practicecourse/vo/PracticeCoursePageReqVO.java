package cn.iocoder.yudao.module.aicrm.controller.admin.practicecourse.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - CRM智能陪练-陪练课程分页 Request VO")
@Data
public class PracticeCoursePageReqVO extends PageParam {

    @Schema(description = "课程名称", example = "张三")
    private String name;

    @Schema(description = "课程描述", example = "你说的对")
    private String description;

    @Schema(description = "关联陪练剧本ID", example = "24241")
    private Long scriptId;

    @Schema(description = "课程类型 1.标准.0.个人")
    private Long standard;

    @Schema(description = "课程复杂度 1.复杂.0.简单")
    private Long hard;

    @Schema(description = "课程状态：字典 aicrm_course_status", example = "2")
    private String status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}