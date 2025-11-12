package cn.iocoder.yudao.module.aicrm.controller.admin.practiceuserrecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - CRM智能陪练-用户陪练记录新增/修改 Request VO")
@Data
public class PracticeUserRecordSaveReqVO {

    @Schema(description = "记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "23127")
    private Long id;

    @Schema(description = "关联陪练课程ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "12485")
    @NotNull(message = "关联陪练课程ID不能为空")
    private Long courseId;

    @Schema(description = "虚拟用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "6026")
    @NotNull(message = "虚拟用户ID不能为空")
    private Long vcustomerId;

    @Schema(description = "参与用户ID", example = "1")
    private Long userId;

    @Schema(description = "练习记录状态", example = "in_progress")
    private String status;

    @Schema(description = "开始时间")
    private LocalDateTime startTime;

    @Schema(description = "结束时间")
    private LocalDateTime endTime;

}