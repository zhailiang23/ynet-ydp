package cn.iocoder.yudao.module.aicrm.controller.admin.practiceuserrecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - CRM智能陪练-用户陪练记录新增/修改 Request VO")
@Data
public class PracticeUserRecordSaveReqVO {

    @Schema(description = "记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "23127")
    private Long id;

    @Schema(description = "关联陪练课程ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "12485")
    @NotNull(message = "关联陪练课程ID不能为空")
    private Long courseId;

    @Schema(description = "参与用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19776")
    @NotNull(message = "参与用户ID不能为空")
    private Long userId;

    @Schema(description = "虚拟用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "6026")
    @NotNull(message = "虚拟用户ID不能为空")
    private Long vcustomerId;

    @Schema(description = "记录编号（唯一）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "记录编号（唯一）不能为空")
    private String recordNo;

    @Schema(description = "记录状态：字典 aicrm_record_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "记录状态：字典 aicrm_record_status不能为空")
    private String status;

    @Schema(description = "开始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "开始时间不能为空")
    private LocalDateTime startTime;

    @Schema(description = "结束时间")
    private LocalDateTime endTime;

    @Schema(description = "实际时长（分钟）")
    private Integer duration;

    @Schema(description = "总评分")
    private BigDecimal totalScore;

    @Schema(description = "各维度得分（JSON格式）")
    private String dimensionScores;

    @Schema(description = "完成进度（%）")
    private BigDecimal completionRate;

    @Schema(description = "AI 总结评价")
    private String aiSummary;

    @Schema(description = "优点总结（JSON格式）")
    private String strengths;

    @Schema(description = "待改进点（JSON格式）")
    private String weaknesses;

    @Schema(description = "改进建议（JSON格式）")
    private String recommendations;

}