package com.ynet.iplatform.module.customer.dal.dataobject.chatmessages;

import lombok.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 聊天消息 DO
 */
@TableName("customer_chat_messages")
@KeySequence("customer_chat_messages_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessagesDO extends BaseDO {

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
     * 客服 id
     */
    private Integer adminId;
    /**
     * 租户 id
     */
    private Integer customerId;
    /**
     * 消息类型: text, image, video, audio, pdf, navigator, notice
     */
    private String type;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 接收时间
     */
    private LocalDateTime receivedAt;
    /**
     * 发送时间
     */
    private LocalDateTime sendAt;
    /**
     * 消息来源: 0=用户, 1=客服, 2=系统
     */
    private Integer source;
    /**
     * 会话 id
     */
    private Integer sessionId;
    /**
     * 请求 id
     */
    private String reqId;
    /**
     * 已读时间
     */
    private LocalDateTime readAt;

}
