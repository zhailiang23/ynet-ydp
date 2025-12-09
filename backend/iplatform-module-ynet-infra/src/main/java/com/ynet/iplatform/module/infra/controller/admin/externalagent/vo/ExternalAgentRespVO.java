package com.ynet.iplatform.module.infra.controller.admin.externalagent.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 外部智能体管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ExternalAgentRespVO {

    @Schema(description = "智能体ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "智能体编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "dify_agent_001")
    @ExcelProperty("智能体编码")
    private String code;

    @Schema(description = "智能体名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("智能体名称")
    private String name;

    @Schema(description = "智能体描述", example = "随便")
    @ExcelProperty("智能体描述")
    private String description;

    @Schema(description = "平台类型（dify, hiagent, coze等）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("平台类型（dify, hiagent, coze等）")
    private String platformType;

    @Schema(description = "访问URL", requiredMode = Schema.RequiredMode.REQUIRED, example = "https://www.iocoder.cn")
    @ExcelProperty("访问URL")
    private String accessUrl;

    @Schema(description = "API密钥", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("API密钥")
    private String apiKey;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("状态")
    private Integer status;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}