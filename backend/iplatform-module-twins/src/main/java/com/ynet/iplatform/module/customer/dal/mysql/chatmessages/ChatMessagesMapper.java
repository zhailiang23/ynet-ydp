package com.ynet.iplatform.module.customer.dal.mysql.chatmessages;

import java.util.List;

import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.customer.dal.dataobject.chatmessages.ChatMessagesDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 聊天消息 Mapper
 */
@Mapper
public interface ChatMessagesMapper extends BaseMapperX<ChatMessagesDO> {

    default List<ChatMessagesDO> selectBySessionId(Integer sessionId) {
        return selectList(ChatMessagesDO::getSessionId, sessionId);
    }

}
