package com.ynet.iplatform.module.crm.controller.admin.product.vo.catalog;

import com.ynet.iplatform.framework.common.enums.CommonStatusEnum;
import com.ynet.iplatform.framework.common.validation.InEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "管理后台 - CRM 产品目录创建/修改 Request VO")
@Data
public class CrmProductCatalogSaveReqVO {

    @Schema(description = "目录编号", example = "1")
    private Long id;

    @Schema(description = "目录名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "金融产品")
    @NotBlank(message = "目录名称不能为空")
    @Size(max = 100, message = "目录名称长度不能超过 100 个字符")
    private String name;

    @Schema(description = "父目录编号", example = "0")
    private Long parentId;

    @Schema(description = "显示顺序", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "显示顺序不能为空")
    private Integer sort;

    @Schema(description = "状态，见 CommonStatusEnum 枚举", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    @NotNull(message = "状态不能为空")
    @InEnum(value = CommonStatusEnum.class, message = "状态必须是 {value}")
    private Integer status;

    @Schema(description = "目录描述", example = "银行金融产品目录")
    @Size(max = 500, message = "目录描述长度不能超过 500 个字符")
    private String description;

}
