package com.ynet.iplatform.module.twins.controller.admin.chatcollectinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 客户留资信息新增/修改 Request VO")
@Data
public class ChatCollectInfoSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "15399")
    private Integer id;

}