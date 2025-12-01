package com.ynet.iplatform.module.knowledge.controller.admin.file.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 知识库文件新增/修改 Request VO")
@Data
public class KonwledgeFileSaveReqVO {

    @Schema(description = "文件ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "17777")
    private Long id;

}