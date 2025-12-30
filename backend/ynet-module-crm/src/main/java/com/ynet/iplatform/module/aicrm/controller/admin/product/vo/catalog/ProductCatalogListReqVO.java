package com.ynet.iplatform.module.aicrm.controller.admin.product.vo.catalog;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - CRM 产品目录列表 Request VO")
@Data
public class ProductCatalogListReqVO {

    @Schema(description = "目录名称，模糊匹配", example = "金融产品")
    private String name;

    @Schema(description = "状态，见 CommonStatusEnum 枚举", example = "0")
    private Integer status;

}
