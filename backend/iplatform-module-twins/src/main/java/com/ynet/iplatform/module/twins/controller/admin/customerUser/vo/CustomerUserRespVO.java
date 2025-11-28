package com.ynet.iplatform.module.twins.controller.admin.customerUser.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 接入用户 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CustomerUserRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "20429")
    @ExcelProperty("主键")
    private Integer id;

    @Schema(description = "用户名", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("用户名")
    private String username;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createdAt;

    @Schema(description = "修改时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("修改时间")
    private LocalDateTime updatedAt;

}