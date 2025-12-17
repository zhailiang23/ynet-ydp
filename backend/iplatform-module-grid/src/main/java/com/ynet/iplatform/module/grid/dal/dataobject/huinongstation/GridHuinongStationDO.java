package com.ynet.iplatform.module.grid.dal.dataobject.huinongstation;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.tenant.core.db.TenantBaseDO;

/**
 * 惠农站点信息 DO
 *
 * @author 易诚源码
 */
@TableName("grid_huinong_station")
@KeySequence("grid_huinong_station_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GridHuinongStationDO extends TenantBaseDO {

    /**
     * 站点ID
     */
    @TableId
    private Long id;
    /**
     * 站点编号
     */
    private String stationCode;
    /**
     * 站点名称
     */
    private String stationName;
    /**
     * 站点类型
     */
    private String stationType;
    /**
     * 站点坐标
     */
    private byte[] location;
    /**
     * 站点地址
     */
    private String address;
    /**
     * 关联的网格ID
     */
    private Long gridId;
    /**
     * 网格营销站点标识: REQUIRED(必选)/OPTIONAL(可选)
     */
    private String gridMarketingFlag;
    /**
     * 联系人
     */
    private String contactPerson;
    /**
     * 联系电话
     */
    private String contactPhone;
    /**
     * 负责惠农专员ID (关联 system_users)
     */
    private Long specialistId;
    /**
     * 站点状态: ACTIVE/INACTIVE
     */
    private String status;
    /**
     * 数据来源: IMPORTED/MANUAL
     */
    private String dataSource;
    /**
     * 导入批次号
     */
    private String importBatch;
    /**
     * 导入时间
     */
    private LocalDateTime importTime;

}