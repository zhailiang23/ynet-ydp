package com.ynet.iplatform.module.knowledge.controller.admin.base.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 知识库分页 Request VO")
@Data
public class KnowledgeBasePageReqVO extends PageParam {

    @Schema(description = "知识库名称", example = "芋艿")
    private String name;

    @Schema(description = "知识库描述", example = "你说的对")
    private String description;

    @Schema(description = "状态（0正常 1停用）", example = "1")
    private Integer status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "是否公共知识库（true=公共知识库，false=个人知识库，null=全部）", example = "true")
    private Boolean isPublic;

}