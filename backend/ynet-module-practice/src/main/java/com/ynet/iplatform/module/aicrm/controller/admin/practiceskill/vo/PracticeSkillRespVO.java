package com.ynet.iplatform.module.aicrm.controller.admin.practiceskill.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - CRM智能陪练-销售技巧 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PracticeSkillRespVO {

    @Schema(description = "技巧ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10625")
    @ExcelProperty("技巧ID")
    private Long id;

    @Schema(description = "技巧名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("技巧名称")
    private String name;

    @Schema(description = "技巧分类：字典 aicrm_skill_category 值： 1.套路. 2.技巧", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("技巧分类：字典 aicrm_skill_category 值： 1.套路. 2.技巧")
    private String category;

    @Schema(description = "话术模板")
    @ExcelProperty("话术模板")
    private String scriptTemplate;

    @Schema(description = "合规规则（培训文件ID）", example = "1")
    @ExcelProperty("合规规则（培训文件ID）")
    private Long complianceRules;

    @Schema(description = "产品知识（培训文件ID）", example = "2")
    @ExcelProperty("产品知识（培训文件ID）")
    private Long relatedProducts;

    @Schema(description = "合规规则文件名称", example = "银行业务操作规范")
    @ExcelProperty("合规规则文件名称")
    private String complianceRulesName;

    @Schema(description = "产品知识文件名称", example = "个人储蓄产品介绍")
    @ExcelProperty("产品知识文件名称")
    private String relatedProductsName;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}