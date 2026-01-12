package com.ynet.iplatform.module.infra.controller.admin.externalagent.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 外部智能体管理分页 Request VO")
@Data
public class ExternalAgentPageReqVO extends PageParam {

    @Schema(description = "智能体编码", example = "dify_agent_001")
    private String code;

    @Schema(description = "智能体名称", example = "芋艿")
    private String name;

    @Schema(description = "平台类型（dify, hiagent, coze等）", example = "1")
    private String platformType;

    @Schema(description = "状态", example = "1")
    private Integer status;

}