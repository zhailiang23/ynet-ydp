package com.ynet.iplatform.module.infra.service.chatrobot;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.module.infra.controller.admin.chatrobot.vo.conversation.ChatRobotConversationPageReqVO;
import com.ynet.iplatform.module.infra.controller.admin.chatrobot.vo.conversation.ChatRobotConversationRespVO;

/**
 * 钉钉机器人对话 Service 接口
 *
 * @author 易诚源码
 */
public interface ChatRobotConversationService {

    /**
     * 分页查询对话列表
     *
     * @param pageReqVO 分页查询条件
     * @return 对话分页结果
     */
    PageResult<ChatRobotConversationRespVO> getConversationPage(ChatRobotConversationPageReqVO pageReqVO);

    /**
     * 获取对话详情
     *
     * @param id 对话 ID
     * @return 对话详情
     */
    ChatRobotConversationRespVO getConversation(Long id);

    /**
     * 关闭对话
     *
     * @param id 对话 ID
     */
    void closeConversation(Long id);

    /**
     * 获取未读消息总数
     *
     * @param robotId 机器人 ID
     * @return 未读消息总数
     */
    Integer getUnreadCount(Long robotId);
}
