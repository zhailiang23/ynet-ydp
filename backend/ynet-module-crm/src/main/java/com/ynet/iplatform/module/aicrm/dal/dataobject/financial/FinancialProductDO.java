package com.ynet.iplatform.module.aicrm.dal.dataobject.financial;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ynet.iplatform.framework.common.enums.CommonStatusEnum;
import com.ynet.iplatform.framework.tenant.core.db.TenantBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * CRM 金融产品 DO
 *
 * @author 易诚源码
 */
@TableName("fin_product")
@KeySequence("fin_product_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
public class FinancialProductDO extends TenantBaseDO {

    /**
     * 产品编号
     */
    @TableId
    private Long id;

    /**
     * 产品代码
     */
    private String productCode;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品目录ID
     *
     * 关联 crm_product_catalog.id
     */
    private Long catalogId;

    /**
     * 产品分类
     *
     * 枚举值：WEALTH-理财、FUND-基金、INSURANCE-保险、BOND-债券、PRECIOUS_METAL-贵金属
     */
    private String category;

    /**
     * 风险等级
     *
     * 枚举值：R1-R5 或 PR1-PR5
     */
    private String riskLevel;

    /**
     * 预期收益率（%）
     */
    private BigDecimal expectedReturn;

    /**
     * 收益类型
     *
     * 如：业绩比较基准、近一年收益、预定利率
     */
    private String returnType;

    /**
     * 产品期限
     *
     * 如：360天、10年
     */
    private String duration;

    /**
     * 期限天数（用于排序）
     */
    private Integer durationDays;

    /**
     * 起购金额
     */
    private BigDecimal minimumInvestment;

    /**
     * 金额单位
     *
     * 默认：元
     */
    private String investmentUnit;

    /**
     * 产品状态
     *
     * 枚举 {@link CommonStatusEnum}
     * 0=停售 1=在售
     */
    private Integer status;

    /**
     * 是否热销
     *
     * 0=否 1=是
     */
    private Integer isHot;

    /**
     * 是否新品
     *
     * 0=否 1=是
     */
    private Integer isNew;

    /**
     * 产品描述
     */
    private String description;

    /**
     * 产品特色
     *
     * JSON 格式
     */
    private String features;

    /**
     * 销售渠道
     *
     * 如：自营、代销
     */
    private String saleChannel;

    /**
     * 产品标签
     *
     * JSON 数组格式，如：["高收益", "低风险", "AI推荐"]
     * 注意：在 DO 中为 String 类型，VO 中为 List<String> 类型
     */
    private String tags;

    /**
     * AI 匹配度
     *
     * 取值范围：0-100
     */
    private Integer aiMatchScore;

    /**
     * AI 关键词
     *
     * 逗号分隔
     */
    private String aiKeywords;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 轮播图片 URL
     */
    private String bannerImage;

}
