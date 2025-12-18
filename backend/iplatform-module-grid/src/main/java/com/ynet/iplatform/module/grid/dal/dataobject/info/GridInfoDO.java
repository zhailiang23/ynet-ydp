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

}
