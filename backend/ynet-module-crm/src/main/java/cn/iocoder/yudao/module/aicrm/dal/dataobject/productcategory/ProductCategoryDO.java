package cn.iocoder.yudao.module.aicrm.dal.dataobject.productcategory;

import java.time.LocalDate;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 产品类目表（树形结构） DO
 *
 * @author 芋道源码
 */
@TableName("crm_product_category")
@KeySequence("crm_product_category_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryDO extends BaseDO {

    public static final Long PARENT_ID_ROOT = 0L;

    /**
     * 产品类目主键
     */
    @TableId
    private Long id;
    /**
     * 类目编号
     */
    private String categoryCode;
    /**
     * 类目名称
     */
    private String categoryName;
    /**
     * 父类目ID（0表示顶级类目）
     */
    private Long parentId;
    /**
     * 类目层级（1=一级，2=二级，3=三级...）
     */
    private Integer categoryLevel;
    /**
     * 类目路径（如：/1/2/3/）
     */
    private String categoryPath;
    /**
     * 是否叶子节点（0=分类目录，1=实际产品）
     */
    private Boolean isLeaf;
    /**
     * 产品类型（字典: aicrm_product_type，deposit=存款，loan=贷款，wealth=理财，fund=基金，insurance=保险，trust=信托，metal=贵金属，creditcard=信用卡）
     */
    private String productType;
    /**
     * 产品编号
     */
    private String productCode;
    /**
     * 产品描述
     */
    private String productDesc;
    /**
     * 币种（字典: aicrm_currency_type）
     */
    private String currencyType;
    /**
     * 最低利率/收益率（%）
     */
    private BigDecimal interestRateMin;
    /**
     * 最高利率/收益率（%）
     */
    private BigDecimal interestRateMax;
    /**
     * 最短期限（天）
     */
    private Integer termMin;
    /**
     * 最长期限（天）
     */
    private Integer termMax;
    /**
     * 起购金额
     */
    private BigDecimal minAmount;
    /**
     * 最大金额
     */
    private BigDecimal maxAmount;
    /**
     * 风险等级（字典: aicrm_risk_level）
     */
    private String riskLevel;
    /**
     * 适用客户类型（字典: aicrm_customer_type_scope，retail=零售，company=对公，all=全部）
     */
    private String customerType;
    /**
     * 状态（0=停用，1=启用）
     */
    private Integer status;
    /**
     * 排序
     */
    private Integer sortOrder;
    /**
     * 备注
     */
    private String remark;


}