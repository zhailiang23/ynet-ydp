package com.ynet.iplatform.module.crm.dal.dataobject.product;

import com.ynet.iplatform.framework.common.enums.CommonStatusEnum;
import com.ynet.iplatform.framework.tenant.core.db.TenantBaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * CRM 产品目录 DO
 *
 * @author 易诚源码
 */
@TableName("crm_product_catalog")
@KeySequence("crm_product_catalog_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
public class CrmProductCatalogDO extends TenantBaseDO {

    public static final Long PARENT_ID_ROOT = 0L;

    /**
     * 目录编号
     */
    @TableId
    private Long id;
    /**
     * 目录名称
     */
    private String name;
    /**
     * 父目录编号
     *
     * 关联 {@link #id}
     */
    private Long parentId;
    /**
     * 显示顺序
     */
    private Integer sort;
    /**
     * 状态
     *
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;
    /**
     * 分类级别
     *
     * 1 表示一级，2 表示二级，以此类推
     */
    private Integer categoryLevel;
    /**
     * 分类路径
     *
     * 格式如 /1/2/3/
     */
    private String categoryPath;
    /**
     * 目录描述
     */
    private String description;

}
