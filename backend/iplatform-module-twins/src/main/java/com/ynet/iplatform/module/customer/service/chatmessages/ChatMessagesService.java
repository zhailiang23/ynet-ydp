package com.ynet.iplatform.module.customer.service.chatmessages;

import java.util.List;
import com.ynet.iplatform.module.customer.dal.dataobject.chatmessages.ChatMessagesDO;

/**
 * 聊天消息 Service 接口
 */
public interface ChatMessagesService {

    /**
     * 根据会话 ID 获取消息列表
     *
     * @param sessionId 会话 ID
     * @return 消息列表
     */
    List<ChatMessagesDO> getMessagesBySessionId(Integer sessionId);

}
