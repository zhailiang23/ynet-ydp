package cn.iocoder.yudao.module.aicrm.controller.admin.customergroupassignment.vo;
import java.time.LocalDate;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 客户归属客群关系表（只记录关系，客群信息通过关联查询）新增/修改 Request VO")
@Data
public class CustomerGroupAssignmentSaveReqVO {

    @Schema(description = "归属客群关系主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "15059")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer 主表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "28157")
    @NotNull(message = "客户ID（关联 crm_customer 主表）不能为空")
    private Long customerId;

    @Schema(description = "客群ID（关联 crm_customer_group_info.id）", requiredMode = Schema.RequiredMode.REQUIRED, example = "23256")
    @NotNull(message = "客群ID（关联 crm_customer_group_info.id）不能为空")
    private Long groupId;

    @Schema(description = "分配日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "分配日期不能为空")
    private LocalDate assignDate;

    @Schema(description = "分配操作人用户ID（关联 system_users.id）", example = "24800")
    private Long assignOperatorId;

    @Schema(description = "归属状态（0=已失效，1=生效中）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "归属状态（0=已失效，1=生效中）不能为空")
    private Integer status;

    @Schema(description = "备注", example = "你猜")
    private String remark;

}