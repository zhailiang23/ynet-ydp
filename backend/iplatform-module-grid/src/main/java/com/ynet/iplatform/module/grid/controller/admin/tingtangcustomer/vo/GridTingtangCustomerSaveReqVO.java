package com.ynet.iplatform.module.grid.controller.admin.tingtangcustomer.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.math.BigDecimal;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 厅堂客户新增/修改 Request VO")
@Data
public class GridTingtangCustomerSaveReqVO {

    @Schema(description = "厅堂客户ID", example = "11627")
    private Long id;

    @Schema(description = "客户名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotBlank(message = "客户名称不能为空")
    private String customerName;

    @Schema(description = "性别", requiredMode = Schema.RequiredMode.REQUIRED, example = "男")
    @NotBlank(message = "性别不能为空")
    private String gender;

    @Schema(description = "客群类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "信贷客户")
    @NotBlank(message = "客群类型不能为空")
    private String customerGroup;

    @Schema(description = "身份证号", requiredMode = Schema.RequiredMode.REQUIRED, example = "410102199001011234")
    @NotBlank(message = "身份证号不能为空")
    private String idCard;

    @Schema(description = "联系电话", example = "13800138000")
    private String phone;

    @Schema(description = "地址", example = "河南省郑州市金水区")
    private String address;

    @Schema(description = "经度", requiredMode = Schema.RequiredMode.REQUIRED, example = "113.625368")
    @NotNull(message = "经度不能为空")
    private BigDecimal longitude;

    @Schema(description = "纬度", requiredMode = Schema.RequiredMode.REQUIRED, example = "34.746611")
    @NotNull(message = "纬度不能为空")
    private BigDecimal latitude;

    @Schema(description = "是否建档", example = "true")
    private Boolean isArchived;

    @Schema(description = "CRM客户号", example = "CRM001")
    private String crmCustomerId;

}
