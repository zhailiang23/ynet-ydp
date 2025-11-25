package com.ynet.iplatform.module.aicrm.controller.admin.productcategory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 产品类目表（树形结构）新增/修改 Request VO")
@Data
public class ProductCategorySaveReqVO {

    @Schema(description = "产品类目主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "19694")
    private Long id;

    @Schema(description = "类目编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "类目编号不能为空")
    private String categoryCode;

    @Schema(description = "类目名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "类目名称不能为空")
    private String categoryName;

    @Schema(description = "父类目ID（0表示顶级类目）", example = "3319")
    private Long parentId;

    @Schema(description = "类目层级（1=一级，2=二级，3=三级...）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "类目层级（1=一级，2=二级，3=三级...）不能为空")
    private Integer categoryLevel;

    @Schema(description = "类目路径（如：/1/2/3/）")
    private String categoryPath;

    @Schema(description = "是否叶子节点（0=分类目录，1=实际产品）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否叶子节点（0=分类目录，1=实际产品）不能为空")
    private Boolean isLeaf;

    @Schema(description = "产品类型（字典: aicrm_product_type，deposit=存款，loan=贷款，wealth=理财，fund=基金，insurance=保险，trust=信托，metal=贵金属，creditcard=信用卡）", example = "1")
    private String productType;

    @Schema(description = "产品编号")
    private String productCode;

    @Schema(description = "产品描述")
    private String productDesc;

    @Schema(description = "币种（字典: aicrm_currency_type）", example = "2")
    private String currencyType;

    @Schema(description = "最低利率/收益率（%）")
    private BigDecimal interestRateMin;

    @Schema(description = "最高利率/收益率（%）")
    private BigDecimal interestRateMax;

    @Schema(description = "最短期限（天）")
    private Integer termMin;

    @Schema(description = "最长期限（天）")
    private Integer termMax;

    @Schema(description = "起购金额")
    private BigDecimal minAmount;

    @Schema(description = "最大金额")
    private BigDecimal maxAmount;

    @Schema(description = "风险等级（字典: aicrm_risk_level）")
    private String riskLevel;

    @Schema(description = "适用客户类型（字典: aicrm_customer_type_scope，retail=零售，company=对公，all=全部）", example = "2")
    private String customerType;

    @Schema(description = "状态（0=停用，1=启用）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "状态（0=停用，1=启用）不能为空")
    private Integer status;

    @Schema(description = "排序", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "排序不能为空")
    private Integer sortOrder;

    @Schema(description = "备注", example = "随便")
    private String remark;

}