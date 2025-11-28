package com.ynet.iplatform.module.customer.dal.dataobject.chatsessions;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 会话信息 DO
 *
 * @author 芋道源码
 */
@TableName("customer_chat_sessions")
@KeySequence("customer_chat_sessions_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatSessionsDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Integer id;
    /**
     * 用户 id
     */
    private Integer userId;
    /**
     * 会话发起时间
     */
    private LocalDateTime queriedAt;
    /**
     * 客服接入时间
     */
    private LocalDateTime acceptedAt;
    /**
     * 会话取消时间
     */
    private LocalDateTime canceledAt;
    /**
     * 中断时间
     */
    private LocalDateTime brokenAt;
    /**
     * 租户 id
     */
    private Integer customerId;
    /**
     * 客服 id
     */
    private Integer adminId;
    /**
     * 会话类型
     */
    private Integer type;
    /**
     * 频率
     */
    private Short rate;


}