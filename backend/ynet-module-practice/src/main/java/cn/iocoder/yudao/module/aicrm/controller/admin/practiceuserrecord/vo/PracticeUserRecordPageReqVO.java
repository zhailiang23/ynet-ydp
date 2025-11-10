package cn.iocoder.yudao.module.aicrm.controller.admin.practiceuserrecord.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - CRM智能陪练-用户陪练记录分页 Request VO")
@Data
public class PracticeUserRecordPageReqVO extends PageParam {

    @Schema(description = "关联陪练课程ID", example = "12485")
    private Long courseId;

    @Schema(description = "参与用户ID", example = "19776")
    private Long userId;

    @Schema(description = "虚拟用户ID", example = "6026")
    private Long vcustomerId;

    @Schema(description = "记录编号（唯一）")
    private String recordNo;

    @Schema(description = "记录状态：字典 aicrm_record_status", example = "1")
    private String status;

    @Schema(description = "开始时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] startTime;

    @Schema(description = "结束时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] endTime;

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

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}