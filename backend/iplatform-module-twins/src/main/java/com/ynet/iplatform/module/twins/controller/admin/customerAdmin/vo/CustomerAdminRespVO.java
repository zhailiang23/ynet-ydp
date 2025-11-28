package com.ynet.iplatform.module.twins.controller.admin.customerAdmin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 客服信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CustomerAdminRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "783")
    @ExcelProperty("主键")
    private Integer id;

    @Schema(description = "客户 id (用户 id)", example = "100")
    @ExcelProperty("用户 ID")
    private Integer customerId;

    @Schema(description = "用户名", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("用户名")
    private String username;

    @Schema(description = "密码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("密码")
    private String password;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createdAt;

    @Schema(description = "修改时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("修改时间")
    private LocalDateTime updatedAt;

}