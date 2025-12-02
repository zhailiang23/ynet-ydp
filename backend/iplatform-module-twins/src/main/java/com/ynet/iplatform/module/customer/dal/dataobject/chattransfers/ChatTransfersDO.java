package com.ynet.iplatform.module.customer.dal.dataobject.chattransfers;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 转接记录 DO
 *
 * @author 易诚源码
 */
@TableName("customer_chat_transfers")
@KeySequence("customer_chat_transfers_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatTransfersDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Integer id;
    /**
     * 被转接用户
     */
    private Integer userId;
    /**
     * 原会话 id
     */
    private Integer fromSessionId;
    /**
     * 新会话 id
     */
    private Integer toSessionId;
    /**
     * 转接人
     */
    private Integer fromAdminId;
    /**
     * 转接给
     */
    private Integer toAdminId;
    /**
     * 租户 id
     */
    private Integer customerId;
    /**
     * 备注
     */
    private String remark;
    /**
     * 接受时间
     */
    private LocalDateTime acceptedAt;
    /**
     * 取消时间
     */
    private LocalDateTime canceledAt;
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;


}