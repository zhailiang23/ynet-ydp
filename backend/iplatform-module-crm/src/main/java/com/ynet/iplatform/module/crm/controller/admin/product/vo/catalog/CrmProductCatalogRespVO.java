package com.ynet.iplatform.module.crm.controller.admin.product.vo.catalog;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - CRM 产品目录信息 Response VO")
@Data
public class CrmProductCatalogRespVO {

    @Schema(description = "目录编号", example = "1")
    private Long id;

    @Schema(description = "目录名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "金融产品")
    private String name;

    @Schema(description = "父目录编号", example = "0")
    private Long parentId;

    @Schema(description = "显示顺序", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Integer sort;

    @Schema(description = "状态，见 CommonStatusEnum 枚举", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    private Integer status;

    @Schema(description = "分类级别", example = "1")
    private Integer categoryLevel;

    @Schema(description = "分类路径", example = "/1/2/3/")
    private String categoryPath;

    @Schema(description = "目录描述", example = "银行金融产品目录")
    private String description;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "时间戳格式")
    private LocalDateTime createTime;

}
