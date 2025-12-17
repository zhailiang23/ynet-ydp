package com.ynet.iplatform.module.grid.dal.dataobject.datasynclog;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 数据同步记录 DO
 *
 * @author 易诚源码
 */
@TableName("grid_data_sync_log")
@KeySequence("grid_data_sync_log_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GridDataSyncLogDO extends BaseDO {

    /**
     * 同步记录ID
     */
    @TableId
    private Long id;
    /**
     * 同步类型: ESB_LINGXI/DW_BATCH
     */
    private String syncType;
    /**
     * 同步方向: PULL/PUSH
     */
    private String syncDirection;
    /**
     * 实体类型: GRID/CUSTOMER/STATION/ACTIVITY
     */
    private String entityType;
    /**
     * 实体ID
     */
    private Long entityId;
    /**
     * 同步状态: SUCCESS/FAILED/PENDING
     */
    private String syncStatus;
    /**
     * 同步时间
     */
    private LocalDateTime syncTime;
    /**
     * 请求数据
     */
    private String requestData;
    /**
     * 响应数据
     */
    private String responseData;
    /**
     * 错误信息
     */
    private String errorMessage;
    /**
     * 重试次数
     */
    private Integer retryCount;


}