package com.ynet.iplatform.module.aicrm.dal.dataobject.taskaction;

import lombok.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 任务行动 DO
 *
 * @author 易诚源码
 */
@TableName("aicrm_task_action")
@KeySequence("aicrm_task_action_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskActionDO extends BaseDO {

    /**
     * 行动ID
     */
    @TableId
    private Long id;
    /**
     * 任务ID
     */
    private Long taskId;
    /**
     * 行动类型（CALL-打电话、SMS-发短信、EMAIL-发邮件、VISIT-拜访、MEETING-会议、OTHER-其他）
     */
    private String actionType;
    /**
     * 行动时间
     */
    private LocalDateTime actionTime;
    /**
     * 行动人ID
     */
    private Long actionUserId;
    /**
     * 行动人姓名
     */
    private String actionUserName;
    /**
     * 备注
     */
    private String remark;

}
