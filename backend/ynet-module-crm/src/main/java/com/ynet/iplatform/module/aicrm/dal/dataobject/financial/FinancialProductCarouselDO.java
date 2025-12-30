package com.ynet.iplatform.module.aicrm.dal.dataobject.financial;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ynet.iplatform.framework.common.enums.CommonStatusEnum;
import com.ynet.iplatform.framework.tenant.core.db.TenantBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * CRM 金融产品轮播推荐 DO
 *
 * @author 易诚源码
 */
@TableName("fin_product_carousel")
@KeySequence("fin_product_carousel_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
public class FinancialProductCarouselDO extends TenantBaseDO {

    /**
     * 轮播编号
     */
    @TableId
    private Long id;

    /**
     * 轮播标题
     */
    private String title;

    /**
     * 轮播副标题
     */
    private String subtitle;

    /**
     * 轮播图片 URL
     */
    private String imageUrl;

    /**
     * 链接类型
     *
     * product=产品详情、url=外部链接
     */
    private String linkType;

    /**
     * 关联产品ID
     *
     * 当 linkType=product 时有效，关联 fin_product.id
     */
    private Long linkId;

    /**
     * 外部链接URL
     *
     * 当 linkType=url 时有效
     */
    private String linkUrl;

    /**
     * 角标文字
     *
     * 如：早报精选、AI 洞察
     */
    private String badgeText;

    /**
     * 角标颜色
     */
    private String badgeColor;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 状态
     *
     * 枚举 {@link CommonStatusEnum}
     * 0=禁用 1=启用
     */
    private Integer status;

}
