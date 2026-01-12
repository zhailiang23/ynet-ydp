package com.ynet.iplatform.module.grid.dal.dataobject.activitylog;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 网格活动日志 DO
 *
 * @author 易诚源码
 */
@TableName("grid_activity_log")
@KeySequence("grid_activity_log_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GridActivityLogDO extends BaseDO {

    /**
     * 活动ID
     */
    @TableId
    private Long id;
    /**
     * 网格ID
     */
    private Long gridId;
    /**
     * 客户ID
     */
    private Long customerId;
    /**
     * 活动类型
     */
    private String activityType;
    /**
     * 活动标题
     */
    private String activityTitle;
    /**
     * 活动内容
     */
    private String activityContent;
    /**
     * 活动日期
     */
    private LocalDate activityDate;
    /**
     * 位置坐标
     */
    private byte[] location;
    /**
     * 地址
     */
    private String address;
    /**
     * 员工ID
     */
    private Long staffId;
    /**
     * 结果
     */
    private String result;
    /**
     * 附件 (JSON)
     */
    private String attachments;

}
