package com.ynet.iplatform.module.aicrm.controller.admin.practicescript.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - CRM智能陪练-陪练剧本表（支持版本控制）分页 Request VO")
@Data
public class PracticeScriptPageReqVO extends PageParam {

    @Schema(description = "剧本编号（标识同一个剧本的不同版本）")
    private String scriptNo;

    @Schema(description = "版本号（如 v1.0, v1.1, v2.0）")
    private String version;

    @Schema(description = "是否最新版本")
    private Boolean isLatest;

    @Schema(description = "父版本ID（用于追溯版本历史）", example = "14004")
    private Long parentVersionId;

    @Schema(description = "版本说明（本次修改的内容）", example = "你说的对")
    private String versionDescription;

    @Schema(description = "版本状态：字典 aicrm_script_status", example = "1")
    private String status;

    @Schema(description = "剧本名称", example = "王五")
    private String name;

    @Schema(description = "剧本描述", example = "你猜")
    private String description;

    @Schema(description = "难度等级：字典 aicrm_difficulty_level")
    private String difficultyLevel;

    @Schema(description = "营销环节：字典 aicrm_marketing_step")
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

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}