package com.ynet.iplatform.module.customer.controller.admin.chattransfers.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 转接记录 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ChatTransfersRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "10616")
    @ExcelProperty("主键")
    private Integer id;

    @Schema(description = "被转接用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "24485")
    private Integer userId;

    @Schema(description = "被转接用户名称")
    @ExcelProperty("被转接用户")
    private String userName;

    @Schema(description = "原会话 id", requiredMode = Schema.RequiredMode.REQUIRED, example = "17809")
    private Integer fromSessionId;

    @Schema(description = "新会话 id", requiredMode = Schema.RequiredMode.REQUIRED, example = "6114")
    private Integer toSessionId;

    @Schema(description = "转接人ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "13802")
    private Integer fromAdminId;

    @Schema(description = "转接人名称")
    @ExcelProperty("转接人")
    private String fromAdminName;

    @Schema(description = "转接给ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2284")
    private Integer toAdminId;

    @Schema(description = "转接给名称")
    @ExcelProperty("转接给")
    private String toAdminName;

    @Schema(description = "租户 id", requiredMode = Schema.RequiredMode.REQUIRED, example = "19220")
    @ExcelProperty("租户 id")
    private Integer customerId;

    @Schema(description = "备注", example = "你猜")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "接受时间")
    @ExcelProperty("接受时间")
    private LocalDateTime acceptedAt;

    @Schema(description = "取消时间")
    @ExcelProperty("取消时间")
    private LocalDateTime canceledAt;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createdAt;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("更新时间")
    private LocalDateTime updatedAt;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}