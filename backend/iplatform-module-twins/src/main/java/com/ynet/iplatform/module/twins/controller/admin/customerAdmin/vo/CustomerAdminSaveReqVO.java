package com.ynet.iplatform.module.twins.controller.admin.customerAdmin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 客服信息新增/修改 Request VO")
@Data
public class CustomerAdminSaveReqVO {

    @Schema(description = "主键", example = "783")
    private Integer id;

    @Schema(description = "客户 id (用户 id)", example = "100")
    private Integer customerId;

    @Schema(description = "用户名", example = "赵六")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "创建时间")
    private LocalDateTime createdAt;

    @Schema(description = "修改时间")
    private LocalDateTime updatedAt;

}