package com.ynet.iplatform.module.aicrm.dal.dataobject.customerimportantevent;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户重要事件表（零售+对公共用） DO
 *
 * @author 芋道源码
 */
@TableName("crm_customer_important_event")
@KeySequence("crm_customer_important_event_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerImportantEventDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 客户ID（关联客户主表）
     */
    private Long customerId;
    /**
     * 事件名称
     */
    private String eventName;
    /**
     * 事件状态（字典：aicrm_event_status，如：未发生、进行中、已完成）
     */
    private String eventStatus;
    /**
     * 事件发生日期
     */
    private LocalDate eventDate;
    /**
     * 事件内容
     */
    private String eventContent;
    /**
     * 维护人ID（关联用户表）
     */
    private Long maintainerId;
    /**
     * 维护人姓名
     */
    private String maintainerName;
    /**
     * 最近维护日期
     */
    private LocalDateTime maintainTime;
    /**
     * 事件类型（字典：aicrm_event_type，如：生日、结婚、生子、升职、购房等）
     */
    private String eventType;
    /**
     * 事件级别（字典：aicrm_event_level，如：重要、一般、普通）
     */
    private String eventLevel;
    /**
     * 事件来源（字典：aicrm_event_source，如：客户告知、系统识别、客户经理录入）
     */
    private String eventSource;
    /**
     * 是否提醒（0-否，1-是）
     */
    private Boolean remindFlag;
    /**
     * 提醒时间
     */
    private LocalDateTime remindTime;
    /**
     * 附件地址
     */
    private String attachmentUrl;
    /**
     * 备注
     */
    private String remark;


}
