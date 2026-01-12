package com.ynet.iplatform.module.grid.dal.dataobject.info;

import lombok.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.tenant.core.db.TenantBaseDO;

/**
 * 网格信息 DO
 *
 * @author 易诚源码
 */
@TableName("grid_info")
@KeySequence("grid_info_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GridInfoDO extends TenantBaseDO {

    /**
     * 网格ID
     */
    @TableId
    private Long id;
    /**
     * 网格编码
     */
    private String gridCode;
    /**
     * 网格名称
     */
    private String gridName;
    /**
     * 网格类型
     */
    private String gridType;
    /**
     * 站点类型（惠农站点专用）
     */
    private String stationType;
    /**
     * 所属机构ID
     */
    private Long orgId;
    /**
     * 责任人用户ID
     */
    private Long managerUserId;
    /**
     * 团队人数
     */
    private Integer teamCount;
    /**
     * 父网格ID
     */
    private Long parentId;
    /**
     * 中心点坐标
     */
    private byte[] centerPoint;
    /**
     * 边界几何
     */
    private byte[] boundaryGeometry;
    /**
     * 半径(米)
     */
    private Integer radiusMeters;
    /**
     * 居民数量
     */
    private Integer residentCount;
    /**
     * 商户数量
     */
    private Integer merchantCount;
    /**
     * 状态
     */
    private String status;
    /**
     * 网格营销站点标识（惠农站点专用）: REQUIRED(必选)/OPTIONAL(可选)
     */
    private String gridMarketingFlag;
    /**
     * 联系人（惠农站点专用）
     */
    private String contactPerson;
    /**
     * 联系电话（惠农站点专用）
     */
    private String contactPhone;
    /**
     * 数据来源（惠农站点专用）: IMPORTED(导入)/MANUAL(手动)
     */
    private String dataSource;
    /**
     * 导入批次号（惠农站点专用）
     */
    private String importBatch;
    /**
     * 导入时间（惠农站点专用）
     */
    private LocalDateTime importTime;
    /**
     * 资源标签(JSON数组)
     */
    private String resourceTags;
    /**
     * 组织类型
     */
    private String orgType;
    /**
     * 管理层级
     */
    private String managementLevel;
    /**
     * 所属区域
     */
    private String region;
    /**
     * 行政片区
     */
    private String district;
    /**
     * 网点类型
     */
    private String outletType;
    /**
     * 位置名称
     */
    private String locationName;
    /**
     * 经度
     */
    private java.math.BigDecimal longitude;
    /**
     * 纬度
     */
    private java.math.BigDecimal latitude;

}
