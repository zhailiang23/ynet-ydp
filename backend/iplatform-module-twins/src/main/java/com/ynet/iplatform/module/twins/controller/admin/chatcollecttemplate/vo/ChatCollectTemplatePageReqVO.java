package com.ynet.iplatform.module.twins.controller.admin.chatcollecttemplate.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 客户留资模板分页 Request VO")
@Data
public class ChatCollectTemplatePageReqVO extends PageParam {

    @Schema(description = "模板名称", example = "李四")
    private String name;

    @Schema(description = "模板描述", example = "随便")
    private String description;

    @Schema(description = "状态：0-禁用，1-启用", example = "2")
    private Integer status;

}