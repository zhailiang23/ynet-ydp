package com.ynet.iplatform.module.infra.dal.dataobject.chatrobot;

import lombok.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 钉钉机器人对话 DO
 *
 * @author 易诚源码
 */
@TableName("infra_chat_robot_conversation")
@KeySequence("infra_chat_robot_conversation_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRobotConversationDO extends BaseDO {

    /**
     * 对话ID
     */
    @TableId
    private Long id;
    /**
     * 机器人ID
     */
    private Long robotId;
    /**
     * 钉钉对话ID（群聊或单聊的唯一标识）
     */
    private String conversationId;
    /**
     * 对话类型：1=单聊，2=群聊
     */
    private Integer conversationType;
    /**
     * 对话标题（群聊名称或用户昵称）
     */
    private String conversationTitle;
    /**
     * 发送者ID（单聊时记录用户ID）
     */
    private String senderId;
    /**
     * 发送者昵称
     */
    private String senderName;
    /**
     * 最后消息时间
     */
    private LocalDateTime lastMessageTime;
    /**
     * 最后消息内容
     */
    private String lastMessageContent;
    /**
     * 消息总数
     */
    private Integer totalMessageCount;
    /**
     * 未读消息数
     */
    private Integer unreadCount;
    /**
     * 对话状态：0=进行中，1=已关闭
     */
    private Integer status;

}
