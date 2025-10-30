package cn.iocoder.yudao.module.aicrm.controller.admin.productcategory.vo;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 产品类目表（树形结构） Response VO")
@Data
@ExcelIgnoreUnannotated
public class ProductCategoryRespVO {

    @Schema(description = "产品类目主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "19694")
    @ExcelProperty("产品类目主键")
    private Long id;

    @Schema(description = "类目编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("类目编号")
    private String categoryCode;

    @Schema(description = "类目名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("类目名称")
    private String categoryName;

    @Schema(description = "父类目ID（0表示顶级类目）", example = "3319")
    @ExcelProperty("父类目ID（0表示顶级类目）")
    private Long parentId;

    @Schema(description = "类目层级（1=一级，2=二级，3=三级...）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("类目层级（1=一级，2=二级，3=三级...）")
    private Integer categoryLevel;

    @Schema(description = "类目路径（如：/1/2/3/）")
    @ExcelProperty("类目路径（如：/1/2/3/）")
    private String categoryPath;

    @Schema(description = "是否叶子节点（0=分类目录，1=实际产品）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("是否叶子节点（0=分类目录，1=实际产品）")
    private Boolean isLeaf;

    @Schema(description = "产品类型（字典: aicrm_product_type，deposit=存款，loan=贷款，wealth=理财，fund=基金，insurance=保险，trust=信托，metal=贵金属，creditcard=信用卡）", example = "1")
    @ExcelProperty("产品类型（字典: aicrm_product_type，deposit=存款，loan=贷款，wealth=理财，fund=基金，insurance=保险，trust=信托，metal=贵金属，creditcard=信用卡）")
    private String productType;

    @Schema(description = "产品编号")
    @ExcelProperty("产品编号")
    private String productCode;

    @Schema(description = "产品描述")
    @ExcelProperty("产品描述")
    private String productDesc;

    @Schema(description = "币种（字典: aicrm_currency_type）", example = "2")
    @ExcelProperty("币种（字典: aicrm_currency_type）")
    private String currencyType;

    @Schema(description = "最低利率/收益率（%）")
    @ExcelProperty("最低利率/收益率（%）")
    private BigDecimal interestRateMin;

    @Schema(description = "最高利率/收益率（%）")
    @ExcelProperty("最高利率/收益率（%）")
    private BigDecimal interestRateMax;

    @Schema(description = "最短期限（天）")
    @ExcelProperty("最短期限（天）")
    private Integer termMin;

    @Schema(description = "最长期限（天）")
    @ExcelProperty("最长期限（天）")
    private Integer termMax;

    @Schema(description = "起购金额")
    @ExcelProperty("起购金额")
    private BigDecimal minAmount;

    @Schema(description = "最大金额")
    @ExcelProperty("最大金额")
    private BigDecimal maxAmount;

    @Schema(description = "风险等级（字典: aicrm_risk_level）")
    @ExcelProperty("风险等级（字典: aicrm_risk_level）")
    private String riskLevel;

    @Schema(description = "适用客户类型（字典: aicrm_customer_type_scope，retail=零售，company=对公，all=全部）", example = "2")
    @ExcelProperty("适用客户类型（字典: aicrm_customer_type_scope，retail=零售，company=对公，all=全部）")
    private String customerType;

    @Schema(description = "状态（0=停用，1=启用）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("状态（0=停用，1=启用）")
    private Integer status;

    @Schema(description = "排序", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("排序")
    private Integer sortOrder;

    @Schema(description = "备注", example = "随便")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}