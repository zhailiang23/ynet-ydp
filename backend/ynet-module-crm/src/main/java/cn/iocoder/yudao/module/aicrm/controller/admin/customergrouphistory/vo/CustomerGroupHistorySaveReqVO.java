package cn.iocoder.yudao.module.aicrm.controller.admin.customergrouphistory.vo;
import java.time.LocalDate;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 客户归属客群调整历史表（记录调整当时的客群信息快照）新增/修改 Request VO")
@Data
public class CustomerGroupHistorySaveReqVO {

    @Schema(description = "调整历史主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "13119")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer 主表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "9371")
    @NotNull(message = "客户ID（关联 crm_customer 主表）不能为空")
    private Long customerId;

    @Schema(description = "调整日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "调整日期不能为空")
    private LocalDate adjustDate;

    @Schema(description = "调整原因", example = "不喜欢")
    private String adjustReason;

    @Schema(description = "调整前客群ID", example = "4086")
    private Long beforeGroupId;

    @Schema(description = "调整前客户群编号")
    private String beforeGroupCode;

    @Schema(description = "调整前客户群名称", example = "张三")
    private String beforeGroupName;

    @Schema(description = "调整前客户群分类")
    private String beforeGroupCategory;

    @Schema(description = "调整前客群管理员用户ID", example = "8057")
    private Long beforeManagerUserId;

    @Schema(description = "调整后客群ID", example = "20827")
    private Long afterGroupId;

    @Schema(description = "调整后客户群编号")
    private String afterGroupCode;

    @Schema(description = "调整后客户群名称", example = "李四")
    private String afterGroupName;

    @Schema(description = "调整后客户群分类")
    private String afterGroupCategory;

    @Schema(description = "调整后客群管理员用户ID", example = "21638")
    private Long afterManagerUserId;

    @Schema(description = "调整操作人用户ID（关联 system_users.id）", example = "6685")
    private Long adjustOperatorId;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

}