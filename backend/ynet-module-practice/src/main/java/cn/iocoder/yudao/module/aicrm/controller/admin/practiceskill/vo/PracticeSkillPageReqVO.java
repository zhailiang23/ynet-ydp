package cn.iocoder.yudao.module.aicrm.controller.admin.practiceskill.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - CRM智能陪练-销售技巧分页 Request VO")
@Data
public class PracticeSkillPageReqVO extends PageParam {

    @Schema(description = "技巧名称", example = "赵六")
    private String name;

    @Schema(description = "技巧分类：字典 aicrm_skill_category 值： 1.套路. 2.技巧")
    private String category;

    @Schema(description = "话术模板")
    private String scriptTemplate;

    @Schema(description = "合规规则说明")
    private String complianceRules;

    @Schema(description = "关联产品知识")
    private String relatedProducts;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}