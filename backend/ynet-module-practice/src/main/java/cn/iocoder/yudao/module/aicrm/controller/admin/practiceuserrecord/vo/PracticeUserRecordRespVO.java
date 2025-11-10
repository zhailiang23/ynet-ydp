package cn.iocoder.yudao.module.aicrm.controller.admin.practiceuserrecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - CRM智能陪练-用户陪练记录 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PracticeUserRecordRespVO {

    @Schema(description = "记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "23127")
    @ExcelProperty("记录ID")
    private Long id;

    @Schema(description = "关联陪练课程ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "12485")
    @ExcelProperty("关联陪练课程ID")
    private Long courseId;

    @Schema(description = "参与用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19776")
    @ExcelProperty("参与用户ID")
    private Long userId;

    @Schema(description = "虚拟用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "6026")
    @ExcelProperty("虚拟用户ID")
    private Long vcustomerId;

    @Schema(description = "记录编号（唯一）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("记录编号（唯一）")
    private String recordNo;

    @Schema(description = "记录状态：字典 aicrm_record_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("记录状态：字典 aicrm_record_status")
    private String status;

    @Schema(description = "开始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("开始时间")
    private LocalDateTime startTime;

    @Schema(description = "结束时间")
    @ExcelProperty("结束时间")
    private LocalDateTime endTime;

    @Schema(description = "实际时长（分钟）")
    @ExcelProperty("实际时长（分钟）")
    private Integer duration;

    @Schema(description = "总评分")
    @ExcelProperty("总评分")
    private BigDecimal totalScore;

    @Schema(description = "各维度得分（JSON格式）")
    @ExcelProperty("各维度得分（JSON格式）")
    private String dimensionScores;

    @Schema(description = "完成进度（%）")
    @ExcelProperty("完成进度（%）")
    private BigDecimal completionRate;

    @Schema(description = "AI 总结评价")
    @ExcelProperty("AI 总结评价")
    private String aiSummary;

    @Schema(description = "优点总结（JSON格式）")
    @ExcelProperty("优点总结（JSON格式）")
    private String strengths;

    @Schema(description = "待改进点（JSON格式）")
    @ExcelProperty("待改进点（JSON格式）")
    private String weaknesses;

    @Schema(description = "改进建议（JSON格式）")
    @ExcelProperty("改进建议（JSON格式）")
    private String recommendations;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}