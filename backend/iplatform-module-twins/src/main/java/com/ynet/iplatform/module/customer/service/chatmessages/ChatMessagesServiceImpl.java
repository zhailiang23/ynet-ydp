package com.ynet.iplatform.module.customer.service.chatmessages;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import com.ynet.iplatform.module.customer.dal.dataobject.chatmessages.ChatMessagesDO;
import com.ynet.iplatform.module.customer.dal.mysql.chatmessages.ChatMessagesMapper;

/**
 * 聊天消息 Service 实现类
 */
@Service
@Validated
public class ChatMessagesServiceImpl implements ChatMessagesService {

    @Resource
    private ChatMessagesMapper chatMessagesMapper;

    @Override
    public List<ChatMessagesDO> getMessagesBySessionId(Integer sessionId) {
        return chatMessagesMapper.selectBySessionId(sessionId);
    }

}
