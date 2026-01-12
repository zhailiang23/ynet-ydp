package com.ynet.iplatform.module.infra.service.chatrobot;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;
import com.ynet.iplatform.module.infra.controller.admin.chatrobot.vo.conversation.ChatRobotConversationPageReqVO;
import com.ynet.iplatform.module.infra.controller.admin.chatrobot.vo.conversation.ChatRobotConversationRespVO;
import com.ynet.iplatform.module.infra.dal.dataobject.chatrobot.ChatRobotConversationDO;
import com.ynet.iplatform.module.infra.dal.mysql.chatrobot.ChatRobotConversationMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.module.infra.enums.ErrorCodeConstants.CHAT_ROBOT_CONVERSATION_NOT_EXISTS;

/**
 * 钉钉机器人对话 Service 实现类
 *
 * @author 易诚源码
 */
@Slf4j
@Service
@Validated
public class ChatRobotConversationServiceImpl implements ChatRobotConversationService {

    @Resource
    private ChatRobotConversationMapper conversationMapper;

    @Override
    public PageResult<ChatRobotConversationRespVO> getConversationPage(ChatRobotConversationPageReqVO pageReqVO) {
        PageResult<ChatRobotConversationDO> pageResult = conversationMapper.selectPage(pageReqVO);
        return BeanUtils.toBean(pageResult, ChatRobotConversationRespVO.class);
    }

    @Override
    public ChatRobotConversationRespVO getConversation(Long id) {
        ChatRobotConversationDO conversation = conversationMapper.selectById(id);
        if (conversation == null) {
            throw exception(CHAT_ROBOT_CONVERSATION_NOT_EXISTS);
        }
        return BeanUtils.toBean(conversation, ChatRobotConversationRespVO.class);
    }

    @Override
    public void closeConversation(Long id) {
        ChatRobotConversationDO conversation = conversationMapper.selectById(id);
        if (conversation == null) {
            throw exception(CHAT_ROBOT_CONVERSATION_NOT_EXISTS);
        }

        // 更新状态为已关闭
        conversation.setStatus(1);
        conversationMapper.updateById(conversation);
    }

    @Override
    public Integer getUnreadCount(Long robotId) {
        LambdaQueryWrapper<ChatRobotConversationDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ChatRobotConversationDO::getRobotId, robotId)
                .select(ChatRobotConversationDO::getUnreadCount);

        return conversationMapper.selectList(wrapper).stream()
                .mapToInt(ChatRobotConversationDO::getUnreadCount)
                .sum();
    }
}
