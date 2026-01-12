package com.ynet.iplatform.module.infra.dal.dataobject.chatrobot;

import lombok.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 钉钉机器人消息 DO
 *
 * @author 易诚源码
 */
@TableName("infra_chat_robot_message")
@KeySequence("infra_chat_robot_message_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRobotMessageDO extends BaseDO {

    /**
     * 消息ID
     */
    @TableId
    private Long id;
    /**
     * 机器人ID（冗余字段，便于查询）
     */
    private Long robotId;
    /**
     * 钉钉对话ID
     */
    private String conversationId;
    /**
     * 钉钉消息ID（接收的消息有此字段）
     */
    private String messageId;
    /**
     * 发送者ID
     */
    private String senderId;
    /**
     * 发送者昵称
     */
    private String senderName;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 消息类型：text, image, file, audio, video
     */
    private String messageType;
    /**
     * 消息来源：0=钉钉用户，1=管理员（以机器人身份发送），2=系统
     */
    private Integer source;
    /**
     * 是否已读
     */
    @TableField(value = "is_read", updateStrategy = FieldStrategy.ALWAYS)
    private Boolean isRead;
    /**
     * 发送时间
     */
    private LocalDateTime sendTime;

}
