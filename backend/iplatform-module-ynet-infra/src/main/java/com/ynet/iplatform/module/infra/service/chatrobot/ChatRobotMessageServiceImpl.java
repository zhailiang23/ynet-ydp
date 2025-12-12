package com.ynet.iplatform.module.infra.service.chatrobot;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;
import com.ynet.iplatform.module.infra.controller.admin.chatrobot.vo.message.ChatRobotMessagePageReqVO;
import com.ynet.iplatform.module.infra.controller.admin.chatrobot.vo.message.ChatRobotMessageRespVO;
import com.ynet.iplatform.module.infra.controller.admin.chatrobot.vo.message.ChatRobotMessageSendReqVO;
import com.ynet.iplatform.module.infra.dal.dataobject.chatrobot.ChatRobotConversationDO;
import com.ynet.iplatform.module.infra.dal.dataobject.chatrobot.ChatRobotDO;
import com.ynet.iplatform.module.infra.dal.dataobject.chatrobot.ChatRobotMessageDO;
import com.ynet.iplatform.module.infra.dal.mysql.chatrobot.ChatRobotConversationMapper;
import com.ynet.iplatform.module.infra.dal.mysql.chatrobot.ChatRobotMapper;
import com.ynet.iplatform.module.infra.dal.mysql.chatrobot.ChatRobotMessageMapper;
import com.ynet.iplatform.module.infra.service.chatrobot.dingtalk.DingTalkMessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 钉钉机器人消息 Service 实现类
 *
 * @author 易诚源码
 */
@Slf4j
@Service
@Validated
public class ChatRobotMessageServiceImpl implements ChatRobotMessageService {

    @Resource
    private ChatRobotMessageMapper messageMapper;

    @Resource
    private ChatRobotConversationMapper conversationMapper;

    @Resource
    private ChatRobotMapper robotMapper;

    @Resource
    private DingTalkMessageSender messageSender;

    @Override
    public PageResult<ChatRobotMessageRespVO> getMessagePage(ChatRobotMessagePageReqVO pageReqVO) {
        PageResult<ChatRobotMessageDO> pageResult = messageMapper.selectPage(pageReqVO);
        return BeanUtils.toBean(pageResult, ChatRobotMessageRespVO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long sendMessage(ChatRobotMessageSendReqVO sendReqVO) {
        // 1. 获取机器人名称作为发送者名称
        ChatRobotDO robot = robotMapper.selectById(sendReqVO.getRobotId());
        String senderName = robot != null ? robot.getName() : "机器人";

        // 2. 保存消息到数据库
        ChatRobotMessageDO message = ChatRobotMessageDO.builder()
                .robotId(sendReqVO.getRobotId())
                .conversationId(sendReqVO.getConversationId())
                .senderId(String.valueOf(sendReqVO.getRobotId())) // 使用机器人 ID 作为发送者 ID
                .senderName(senderName) // 使用机器人名称作为发送者名称
                .content(sendReqVO.getContent())
                .messageType(sendReqVO.getMessageType())
                .source(1) // 1=管理员
                .isRead(true) // 管理员发送的消息默认已读
                .sendTime(LocalDateTime.now())
                .build();
        messageMapper.insert(message);

        // 3. 调用钉钉 API 发送消息
        boolean success = messageSender.sendMessage(
                sendReqVO.getRobotId(),
                sendReqVO.getConversationId(),
                sendReqVO.getContent(),
                sendReqVO.getMessageType()
        );

        if (!success) {
            throw new RuntimeException("发送消息失败");
        }

        // 4. 更新对话统计
        updateConversationAfterSend(sendReqVO.getConversationId(), sendReqVO.getContent());

        return message.getId();
    }

    @Override
    public void markMessagesAsRead(String conversationId) {
        // 更新所有未读消息为已读
        LambdaUpdateWrapper<ChatRobotMessageDO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(ChatRobotMessageDO::getConversationId, conversationId)
                .eq(ChatRobotMessageDO::getIsRead, false)
                .set(ChatRobotMessageDO::getIsRead, true);
        messageMapper.update(null, updateWrapper);

        // 更新对话的未读数为 0
        LambdaQueryWrapper<ChatRobotConversationDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ChatRobotConversationDO::getConversationId, conversationId);
        ChatRobotConversationDO conversation = conversationMapper.selectOne(queryWrapper);

        if (conversation != null) {
            conversation.setUnreadCount(0);
            conversationMapper.updateById(conversation);
        }
    }

    @Override
    public PageResult<ChatRobotMessageRespVO> pollNewMessages(String conversationId, Long lastMessageId) {
        // 查询 ID 大于 lastMessageId 的所有消息
        LambdaQueryWrapper<ChatRobotMessageDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ChatRobotMessageDO::getConversationId, conversationId)
                .gt(lastMessageId != null, ChatRobotMessageDO::getId, lastMessageId)
                .orderByAsc(ChatRobotMessageDO::getSendTime);

        List<ChatRobotMessageDO> messages = messageMapper.selectList(wrapper);
        return new PageResult<>(BeanUtils.toBean(messages, ChatRobotMessageRespVO.class), (long) messages.size());
    }

    /**
     * 发送消息后更新对话统计
     *
     * @param conversationId 对话 ID
     * @param content        消息内容
     */
    private void updateConversationAfterSend(String conversationId, String content) {
        LambdaQueryWrapper<ChatRobotConversationDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ChatRobotConversationDO::getConversationId, conversationId);
        ChatRobotConversationDO conversation = conversationMapper.selectOne(wrapper);

        if (conversation != null) {
            conversation.setLastMessageTime(LocalDateTime.now());
            conversation.setLastMessageContent(content.length() > 500 ? content.substring(0, 500) : content);
            conversation.setTotalMessageCount(conversation.getTotalMessageCount() + 1);
            conversationMapper.updateById(conversation);
        }
    }
}
