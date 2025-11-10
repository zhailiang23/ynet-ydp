package cn.iocoder.yudao.module.aicrm.controller.admin.practicescript.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - CRM智能陪练-陪练剧本表（支持版本控制）新增/修改 Request VO")
@Data
public class PracticeScriptSaveReqVO {

    @Schema(description = "剧本版本ID（主键）", requiredMode = Schema.RequiredMode.REQUIRED, example = "10294")
    private Long id;

    @Schema(description = "剧本编号（标识同一个剧本的不同版本）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "剧本编号（标识同一个剧本的不同版本）不能为空")
    private String scriptNo;

    @Schema(description = "版本号（如 v1.0, v1.1, v2.0）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "版本号（如 v1.0, v1.1, v2.0）不能为空")
    private String version;

    @Schema(description = "是否最新版本", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否最新版本不能为空")
    private Boolean isLatest;

    @Schema(description = "父版本ID（用于追溯版本历史）", example = "14004")
    private Long parentVersionId;

    @Schema(description = "版本说明（本次修改的内容）", example = "你说的对")
    private String versionDescription;

    @Schema(description = "版本状态：字典 aicrm_script_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "版本状态：字典 aicrm_script_status不能为空")
    private String status;

    @Schema(description = "剧本名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "剧本名称不能为空")
    private String name;

    @Schema(description = "剧本描述", example = "你猜")
    private String description;

    @Schema(description = "难度等级：字典 aicrm_difficulty_level", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "难度等级：字典 aicrm_difficulty_level不能为空")
    private String difficultyLevel;

    @Schema(description = "营销环节：字典 aicrm_marketing_step", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "营销环节：字典 aicrm_marketing_step不能为空")
    private String marketingStep;

    @Schema(description = "关联销售案例ID", example = "1521")
    private Long caseId;

    @Schema(description = "关联销售技巧ID", example = "16776")
    private Long skillId;

    @Schema(description = "关联虚拟客户ID", example = "24282")
    private Long virtualCustomerId;

    @Schema(description = "关联培训文件ID列表（多个ID逗号分隔）")
    private String materialIds;

    @Schema(description = "剧本内容（AI生成）")
    private String content;

    @Schema(description = "手工调整内容（用户编辑）")
    private String contentEdit;

    @Schema(description = "使用次数（该版本被使用的次数）", example = "23155")
    private Integer usageCount;

}