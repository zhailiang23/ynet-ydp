package cn.iocoder.yudao.module.aicrm.controller.admin.customerdelegation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 客户托管结束 Request VO")
@Data
public class CustomerDelegationEndReqVO {

    @Schema(description = "托管ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "托管ID不能为空")
    private Long id;

    @Schema(description = "结束原因", example = "出差结束")
    @Size(max = 500, message = "结束原因长度不能超过500字符")
    private String endReason;

}
