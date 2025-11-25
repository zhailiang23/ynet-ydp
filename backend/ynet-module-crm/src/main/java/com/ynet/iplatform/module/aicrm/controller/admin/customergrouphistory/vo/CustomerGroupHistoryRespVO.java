package com.ynet.iplatform.module.aicrm.controller.admin.customergrouphistory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 客户归属客群调整历史表（记录调整当时的客群信息快照） Response VO")
@Data
@ExcelIgnoreUnannotated
public class CustomerGroupHistoryRespVO {

    @Schema(description = "调整历史主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "13119")
    @ExcelProperty("调整历史主键")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer 主表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "9371")
    @ExcelProperty("客户ID（关联 crm_customer 主表）")
    private Long customerId;

    @Schema(description = "调整日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("调整日期")
    private LocalDate adjustDate;

    @Schema(description = "调整原因", example = "不喜欢")
    @ExcelProperty("调整原因")
    private String adjustReason;

    @Schema(description = "调整前客群ID", example = "4086")
    @ExcelProperty("调整前客群ID")
    private Long beforeGroupId;

    @Schema(description = "调整前客户群编号")
    @ExcelProperty("调整前客户群编号")
    private String beforeGroupCode;

    @Schema(description = "调整前客户群名称", example = "张三")
    @ExcelProperty("调整前客户群名称")
    private String beforeGroupName;

    @Schema(description = "调整前客户群分类")
    @ExcelProperty("调整前客户群分类")
    private String beforeGroupCategory;

    @Schema(description = "调整前客群管理员用户ID", example = "8057")
    @ExcelProperty("调整前客群管理员用户ID")
    private Long beforeManagerUserId;

    @Schema(description = "调整后客群ID", example = "20827")
    @ExcelProperty("调整后客群ID")
    private Long afterGroupId;

    @Schema(description = "调整后客户群编号")
    @ExcelProperty("调整后客户群编号")
    private String afterGroupCode;

    @Schema(description = "调整后客户群名称", example = "李四")
    @ExcelProperty("调整后客户群名称")
    private String afterGroupName;

    @Schema(description = "调整后客户群分类")
    @ExcelProperty("调整后客户群分类")
    private String afterGroupCategory;

    @Schema(description = "调整后客群管理员用户ID", example = "21638")
    @ExcelProperty("调整后客群管理员用户ID")
    private Long afterManagerUserId;

    @Schema(description = "调整操作人用户ID（关联 system_users.id）", example = "6685")
    @ExcelProperty("调整操作人用户ID（关联 system_users.id）")
    private Long adjustOperatorId;

    @Schema(description = "备注", example = "你说的对")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
