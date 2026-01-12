package com.ynet.iplatform.module.infra.service.chatrobot;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.module.infra.controller.admin.chatrobot.vo.message.ChatRobotMessagePageReqVO;
import com.ynet.iplatform.module.infra.controller.admin.chatrobot.vo.message.ChatRobotMessageRespVO;
import com.ynet.iplatform.module.infra.controller.admin.chatrobot.vo.message.ChatRobotMessageSendReqVO;

/**
 * 钉钉机器人消息 Service 接口
 *
 * @author 易诚源码
 */
public interface ChatRobotMessageService {

    /**
     * 分页查询消息列表
     *
     * @param pageReqVO 分页查询条件
     * @return 消息分页结果
     */
    PageResult<ChatRobotMessageRespVO> getMessagePage(ChatRobotMessagePageReqVO pageReqVO);

    /**
     * 发送消息
     *
     * @param sendReqVO 发送消息请求
     * @return 消息 ID
     */
    Long sendMessage(ChatRobotMessageSendReqVO sendReqVO);

    /**
     * 标记对话的所有消息为已读
     *
     * @param conversationId 对话 ID
     */
    void markMessagesAsRead(String conversationId);

    /**
     * 轮询获取新消息
     *
     * @param conversationId 对话 ID
     * @param lastMessageId  最后一条消息 ID
     * @return 新消息列表
     */
    PageResult<ChatRobotMessageRespVO> pollNewMessages(String conversationId, Long lastMessageId);
}
