package com.ynet.iplatform.module.aicrm.controller.admin.practicecase.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - CRM智能陪练-销售案例新增/修改 Request VO")
@Data
public class PracticeCaseSaveReqVO {

    @Schema(description = "案例ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "9916")
    private Long id;

    @Schema(description = "案例标题", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "案例标题不能为空")
    private String title;

    @Schema(description = "案例详细内容（正文）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "案例详细内容（正文）不能为空")
    private String content;

    @Schema(description = "标签（多个标签逗号分隔）")
    private String tags;

}