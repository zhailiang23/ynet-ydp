package cn.iocoder.yudao.module.aicrm.controller.admin.practiceskill.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - CRM智能陪练-销售技巧新增/修改 Request VO")
@Data
public class PracticeSkillSaveReqVO {

    @Schema(description = "技巧ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10625")
    private Long id;

    @Schema(description = "技巧名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "技巧名称不能为空")
    private String name;

    @Schema(description = "技巧分类：字典 aicrm_skill_category 值： 1.套路. 2.技巧", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "技巧分类：字典 aicrm_skill_category 值： 1.套路. 2.技巧不能为空")
    private String category;

    @Schema(description = "话术模板")
    private String scriptTemplate;

    @Schema(description = "合规规则说明")
    private String complianceRules;

    @Schema(description = "关联产品知识")
    private String relatedProducts;

}