package com.ynet.iplatform.module.aicrm.controller.admin.practicescript.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Schema(description = "管理后台 - 生成剧本内容 Request VO")
@Data
public class GenerateScriptContentReqVO {

    @Schema(description = "剧本ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "剧本ID不能为空")
    private Long scriptId;

    @Schema(description = "关联案例ID", example = "1")
    private Long caseId;

    @Schema(description = "关联培训文件ID列表（多个ID逗号分隔）", example = "1,2,3")
    private String materialIds;

    @Schema(description = "关联销售技巧ID", example = "1")
    private Long skillId;

    @Schema(description = "营销环节", requiredMode = Schema.RequiredMode.REQUIRED, example = "initial_contact")
    @NotNull(message = "营销环节不能为空")
    private String marketingStep;

    @Schema(description = "难度等级", requiredMode = Schema.RequiredMode.REQUIRED, example = "intermediate")
    @NotNull(message = "难度等级不能为空")
    private String difficultyLevel;

    @Schema(description = "剧本描述", example = "个性化陪练剧本")
    private String scriptDescription;

}
